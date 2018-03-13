package org.primejava.cms.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.primejava.basic.model.Pager;
import org.primejava.basic.query.PropertyFilter;
import org.primejava.cms.model.User;

public interface UserService {
	
	/**
	 * 列表用户
	 * @param page 
	 * @param propertyFilters 
	 */
	public Pager<User> findUser(Pager<User> page, List<PropertyFilter> propertyFilters);
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	public User load(String id);
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public User findUserByName(String username);
	
	public void add(User accountInfo);
	/**
	 * 根据用户名和ID查找用户
	 * @param id
	 * @param username
	 * @return
	 */
	public User findUserByIdAndName(String id, String username);
	public void deleteById(String id);
	public void update(User old);
	public List<User> findAll();
	public User login(String username, String password);
	public Pager<User> findUserBySQL(Pager<User> page, String sql);
	public void exportUser(HttpServletResponse resp, String tempPath,
			List<User> users);
	
}
