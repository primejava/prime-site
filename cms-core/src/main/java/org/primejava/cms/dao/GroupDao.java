package org.primejava.cms.dao;

import java.util.List;

import org.primejava.basic.dao.IBaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Group;
import org.primejava.cms.model.UserGroup;

public interface GroupDao extends IBaseDao<Group>{

	void findGroups(Pager<Group> pager);

	List<Group> findUserGroupByType(String type);

	List<UserGroup> findUserGroups(String id);


}
