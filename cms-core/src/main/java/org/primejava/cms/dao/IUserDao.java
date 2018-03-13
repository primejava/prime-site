package org.primejava.cms.dao;

import java.util.List;

import org.primejava.basic.dao.IBaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.basic.query.PropertyFilter;
import org.primejava.cms.model.CommonRoleUser;
import org.primejava.cms.model.User;
import org.primejava.cms.model.UserGroup;

public interface IUserDao extends IBaseDao<User>{
	public Pager<User> findUser(Pager<User> pager, List<PropertyFilter> propertyFilters);

	public User findUserByName(String username);

	public User findUserByIdAndName(String id, String username);

	public List<User> findAll();

	public Pager<User>  findUserBySQL(Pager<User> page,
			String sql);

	
	public void findHibernate();

	public List<CommonRoleUser> findRoleUser(String id);

	public List<UserGroup> findUerGroup(String id);
	
}
