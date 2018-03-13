package org.primejava.cms.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.basic.query.PropertyFilter;
import org.primejava.basic.util.CommonDateUtils;
import org.primejava.cms.dao.IUserDao;
import org.primejava.cms.flow.SynchronizationFlowUserManger;
import org.primejava.cms.model.CmsException;
import org.primejava.cms.model.CommonRoleUser;
import org.primejava.cms.model.User;
import org.primejava.cms.model.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("userService")
public class UserServiceImpl implements UserService {
	private static Logger                         LOGGER = Logger.getLogger(UserServiceImpl.class);
	private IUserDao userDao;
    @Autowired
    private BaseDao                     commonDao;
	 private SynchronizationFlowUserManger flowUserManager;
	public SynchronizationFlowUserManger getFlowUserManager() {
		return flowUserManager;
	}
	@Inject
	public void setFlowUserManager(SynchronizationFlowUserManger flowUserManager) {
		this.flowUserManager = flowUserManager;
	}
	public IUserDao getUserDao() {
		return userDao;
	}
	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Pager<User> findUser(Pager<User> pager, List<PropertyFilter> propertyFilters) {
		return userDao.findUser(pager,propertyFilters);
	}

	@Override
	public User load(String id) {
		User user=userDao.load(id);
		List<CommonRoleUser> roleUsers = userDao.findRoleUser(id);
        if (!roleUsers.isEmpty()) {
            user.setRoles(new ArrayList<String>());
            for (CommonRoleUser roleUser : roleUsers) {
                user.getRoles().add(roleUser.getRoleId());
            }
        }
		return user;
	}
	@Override
	public User findUserByName(String username) {
		User user=userDao.findUserByName(username);
		return user;
	}
	@Override
	public void add(User user) {
		userDao.add(user);
	    saveRoleUsers(user);
		flowUserManager.saveOrUpdateUser(user);
	}
	private void saveRoleUsers(User user) {
		if (null != user.getRoles() && !user.getRoles().isEmpty()) {
            List<CommonRoleUser> oldRoleUsers = userDao.findRoleUser(user.getId());
            if (!oldRoleUsers.isEmpty()) {
                commonDao.deleteAll(oldRoleUsers);
            }
            for (String roleId : user.getRoles()) {
            	CommonRoleUser ru=new CommonRoleUser(roleId, user.getId());
            	commonDao.saveOrUpdate(ru);
            }
        }
	}
	
	@Override
	public User findUserByIdAndName(String id, String username) {
		User user=userDao.findUserByIdAndName(id,username);
		return user;
	}
	@Override
	public void deleteById(String id) {
		User user=userDao.load(id);
        List<CommonRoleUser> roleUsers = userDao.findRoleUser(id);
        if (null != roleUsers && !roleUsers.isEmpty()) {
            commonDao.deleteAll(roleUsers);
        }
        List<UserGroup> userGroups = userDao.findUerGroup(id);
        if (null != userGroups && !userGroups.isEmpty()) {
            commonDao.deleteAll(userGroups);
        }
        flowUserManager.removeUser(id);
		userDao.deleteById(id);
	}
	@Override
	public void update(User u) {
		userDao.update(u);		
		saveRoleUsers(u);
		flowUserManager.saveOrUpdateUser(u);
	}
	@Override
	public List<User> findAll() {
		List<User> users=userDao.findAll();
		return users;
	}
	@Override
	public User login(String username, String password) {
		User user = userDao.findUserByName(username);
		if(user==null) throw new CmsException("用户名或者密码不正确");
			if(!password.equals(user.getPassword())) {
				throw new CmsException("用户名或者密码不正确");
			}
		if(user.getStatus()==0) throw new CmsException("用户已经停用，请与管理员联系");
		return user;
	}
	
	@Override
	public Pager<User> findUserBySQL(Pager<User> page, String propertyFilters) {
		return userDao.findUserBySQL(page,propertyFilters);
	}
	@Override
	public void exportUser(HttpServletResponse response, String uploadPath,
			List<User> users) {
        try {
            OutputStream out = response.getOutputStream();
            response.reset();
            response.setHeader("content-disposition",
                               "attachment;filename="
                                       + new String((StringUtils.join("用户信息-", CommonDateUtils
                                               .dateToString(CommonDateUtils.now()))).getBytes("gb2312"), "ISO8859-1")
                                       + ".xls");
            response.setContentType("APPLICATION/msexcel");
            InputStream in = new FileInputStream(new File(uploadPath));
            XLSTransformer transformer = new XLSTransformer();
            Workbook workbook = transformer.transformXLS(in, buildExcelList(users));
            workbook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            LOGGER.error("Export in-out is error", e);
        } catch (ParsePropertyException e) {
            // TODO Auto-generated catch block
            LOGGER.error("Export parse property is error", e);
        } catch (InvalidFormatException e) {
            // TODO Auto-generated catch block
            LOGGER.error("Export format is error", e);
        }		
	}
    protected Map<String, Object> buildExcelList(List<User> users) {
        Map<String, Object> maps = Maps.newHashMap();
        maps.put("user", users);
        return maps;
    }

}
