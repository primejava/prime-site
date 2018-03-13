package org.primejava.cms.service;

import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Leave;
import org.primejava.cms.model.User;

public interface LeaveService {

	void saveOrUpdateLeave(Leave leave);

	Pager<Leave>  findLeaves(Pager<Leave> page, User user);
	
	Pager<Leave>  findApplyHistories(Pager<Leave> page, User user);
	
	Leave load(String id);

	void saveAudit(String leaveId, Integer audit, String comment, User user);

}
