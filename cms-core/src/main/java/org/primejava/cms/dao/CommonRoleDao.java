package org.primejava.cms.dao;

import java.util.List;

import org.primejava.basic.dao.IBaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.CommonRole;
import org.primejava.cms.model.CommonRoleDepartment;
import org.primejava.cms.model.CommonRoleUser;

public interface CommonRoleDao extends IBaseDao<CommonRole>{
	  /**
     * 这里用一句话描述这个方法的作用. <br/>
     * @author xs
     * @param role
     * @param page
     */
    void findPageAllRole(CommonRole role, Pager<CommonRole> page);

    /**
     * 查询角色与部门的关联信息. <br/>
     * @author xs
     * @param id
     * @return
     */
    List<CommonRoleDepartment> findRoleDepartments(String id);


    /**
     * 查询与角色相关的用户关联. <br/>
     * @author xs
     * @param id
     * @return
     */
    List<CommonRoleUser> findRoleUser(String id);

	List<CommonRole> findAll();
}
