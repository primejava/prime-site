package org.primejava.cms.service;

import java.util.ArrayList;
import java.util.List;

import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.dao.GroupDao;
import org.primejava.cms.flow.SynchronizationFlowUserManger;
import org.primejava.cms.model.Group;
import org.primejava.cms.model.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("groupService")
public class GroupServiceImpl implements GroupService {
	@Autowired
	private BaseDao commonDao;
	@Autowired
	private GroupDao groupDao;
	@Autowired
	private SynchronizationFlowUserManger synchronizationFlowUserManger;
	@Override
	public List<Group> findUserGroupByType(String type) {
		return groupDao.findUserGroupByType(type);
	}

	@Override
	public void findGroups(Pager<Group> pager) {
		groupDao.findGroups(pager);
	}

	@Override
	public Group load(String id) {
		return groupDao.load(id);
	}

	@Override
	public void update(Group group) {
		groupDao.update(group);
	}

	@Override
	public void add(Group group) {
		groupDao.add(group);
		if ((group.getUsers() != null) && (!group.getUsers().isEmpty())) {
			List<UserGroup> oldUserGroups = this.groupDao.findUserGroups(group
					.getId());
			if (!oldUserGroups.isEmpty()) {
				for (UserGroup ug : oldUserGroups) {
					this.commonDao.delete(ug);
				}
			}
			List<UserGroup> userGroups = new ArrayList<UserGroup>();
			for (String userId : group.getUsers()) {
				userGroups.add(new UserGroup(group.getId(), userId));
			}
			for (UserGroup ug : userGroups) {
				this.commonDao.add(ug);
			}
		}
		synchronizationFlowUserManger.saveOrUpdateGroup(group);
	}

	@Override
	public Group findGroup(String id) {
		Group group = groupDao.load(id);
		if (group == null) {
			return null;
		}
		group.setUsers(new ArrayList<String>());
		List<UserGroup> ugs = groupDao.findUserGroups(id);
		if (ugs != null && !ugs.isEmpty()) {
			for (UserGroup ug : ugs) {
				group.getUsers().add(ug.getUserId());
			}
		}
		return group;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateGroup(Group group) {
		groupDao.update(group);
		if ((group.getUsers() != null) && (!group.getUsers().isEmpty())) {
			List<UserGroup> oldUserGroups = this.groupDao.findUserGroups(group
					.getId());
			if (!oldUserGroups.isEmpty()) {
				for (UserGroup ug : oldUserGroups) {
					this.commonDao.delete(ug);
				}
			}
			List<UserGroup> userGroups = new ArrayList<UserGroup>();
			for (String userId : group.getUsers()) {
				userGroups.add(new UserGroup(group.getId(), userId));
			}
			for (UserGroup ug : userGroups) {
				this.commonDao.add(ug);
			}
		}
		synchronizationFlowUserManger.saveOrUpdateGroup(group);
	}

}
