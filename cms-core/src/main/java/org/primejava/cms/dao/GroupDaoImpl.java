package org.primejava.cms.dao;

import java.util.List;

import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Group;
import org.primejava.cms.model.UserGroup;
import org.springframework.stereotype.Repository;

@Repository("groupDao")
public class GroupDaoImpl extends BaseDao<Group> implements GroupDao{

	@Override
	public void findGroups(Pager<Group> pager) {
		this.find(pager,"from Group");
	}

	@Override
	public List<Group> findUserGroupByType(String type) {
		return list("from Group where groupType=?", type);
	}

	@Override
	public List<UserGroup> findUserGroups(String id) {
		String hql="from UserGroup where groupId=? ";
		return this.getSession().createQuery(hql).setParameter(0,id).list();
	}
}
