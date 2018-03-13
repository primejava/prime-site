package org.primejava.cms.dao;

import org.primejava.basic.dao.IBaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Leave;
import org.primejava.cms.model.User;

public interface LeaveDao extends IBaseDao<Leave>{

	Pager<Leave> findLeaves(Pager<Leave> page, User user);

}
