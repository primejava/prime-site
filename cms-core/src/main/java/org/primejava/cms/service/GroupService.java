package org.primejava.cms.service;

import java.util.List;

import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Group;

public interface GroupService {
	List<Group> findUserGroupByType(String type);

	void findGroups(Pager<Group> page);

	Group load(String id);
	/**
	 * 单纯的更新本身
	 * @param old
	 */
	void update(Group old);
	/**
	 * 更新组和用户
	 * @param old
	 */
	void updateGroup(Group old);

	void add(Group group);

	Group findGroup(String id);
}
