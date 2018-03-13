package org.primejava.cms.service;

import java.util.List;

import org.primejava.basic.model.Pager;
import org.primejava.cms.model.CommonRole;
import org.primejava.cms.model.Role;

public interface RoleService {
	void findRoles(Pager<CommonRole> page,CommonRole role);
    /**
     * 查询角色对象，包括角色下的菜单和学院信息. <br/>
     * @author xs
     * @param id
     * @return
     */
	CommonRole findRole(String id);
    
    public void saveRole(CommonRole role);
    
    public void removeRole(String id);
    
	List<CommonRole> findAll();
    
    
}
