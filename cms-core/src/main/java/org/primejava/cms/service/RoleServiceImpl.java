package org.primejava.cms.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.dao.CommonRoleDao;
import org.primejava.cms.model.CommonRole;
import org.primejava.cms.model.CommonRoleDepartment;
import org.primejava.cms.model.CommonRoleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private BaseDao                     commonDao;
	@Autowired
	private  CommonRoleDao roleDao;
	
	@Override
	public void findRoles(Pager<CommonRole> page,CommonRole role) {
		roleDao.findPageAllRole(role, page);
	}

	@Override
	public CommonRole findRole(String id) {
	    CommonRole role = roleDao.load(id);
//        List<CommonRoleMenu> roleMenus = roleDao.findRoleMenus(id);
//        if (null != role && !roleMenus.isEmpty()) {
//            role.setMenus(new ArrayList<String>());
//            for (CommonRoleMenu roleMenu : roleMenus) {
//                role.getMenus().add(roleMenu.getMenuId());
//            }
//        }
        List<CommonRoleDepartment> roleDepartments = roleDao.findRoleDepartments(id);
        if (!roleDepartments.isEmpty()) {
            role.setDepartments(new ArrayList<String>());
            for (CommonRoleDepartment roleDepartment : roleDepartments) {
                role.getDepartments().add(roleDepartment.getDepartmentId());
            }
        }
        return role;
	}

	@Override
	public void saveRole(CommonRole role) {
		   String id = role.getId();
	        if (StringUtils.isNotBlank(id)) {
	            this.roleDao.update(role);
	        } else {
	            System.out.println("添加");
	        	this.roleDao.add(role);
	            id = role.getId();
	        }
//	        final List<CommonRoleMenu> oldRoleMenus = this.roleDao.findRoleMenus(id);
//	        if (!oldRoleMenus.isEmpty()) {
//	            this.commonDao.deleteAll(oldRoleMenus);
//	        }
//	        if (null != role.getMenus() && !role.getMenus().isEmpty()) {
//	            final List<CommonRoleMenu> roleMenus = Lists.newArrayList();
//	            for (final String menuId : role.getMenus()) {
//	                roleMenus.add(new CommonRoleMenu(id, menuId));
//	            }
//	            this.commonDao.saveOrUpdate(roleMenus);
//	        }
	        
	
	        final List<CommonRoleDepartment> oldRoleDepartments = this.roleDao.findRoleDepartments(id);
	        if (!oldRoleDepartments.isEmpty()) {
	            this.commonDao.deleteAll(oldRoleDepartments);
	        }
	        if (null != role.getDepartments() && !role.getDepartments().isEmpty()) {
	            for (final String departmentId : role.getDepartments()) {
	            	CommonRoleDepartment department=new CommonRoleDepartment(id, departmentId);
	                this.commonDao.add(department);
	            }
	        }
	}

	@Override
	public void removeRole(String id) {
        final CommonRole role = this.roleDao.load(id);
//        if (null != role.getMenus() && !role.getMenus().isEmpty()) {
//            final List<CommonRoleMenu> roleMenus = this.roleDao.findRoleMenus(id);
//            if (!roleMenus.isEmpty()) {
//                this.commonDao.deleteAll(roleMenus);
//            }
//        }
        if (null != role.getDepartments() && !role.getDepartments().isEmpty()) {
            final List<CommonRoleDepartment> roleDepartments = this.roleDao.findRoleDepartments(id);
            if (!roleDepartments.isEmpty()) {
                this.commonDao.deleteAll(roleDepartments);
            }
        }
        final List<CommonRoleUser> roleUsers = this.roleDao.findRoleUser(id);
        if (!roleUsers.isEmpty()) {
            this.commonDao.deleteAll(roleUsers);
        }
        this.roleDao.deleteById(role.getId());
	}

	@Override
	public List<CommonRole> findAll() {
		return roleDao.findAll();
	}

}
