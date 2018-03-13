package org.primejava.cms.flow;

import java.util.List;

import org.activiti.engine.task.Comment;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Leave;
import org.primejava.cms.model.User;

public interface LeaveFlowService {
	/**
	 * 根据用户启动流程
	 * @param businessId
	 * @param userId
	 */
    void startFlow(String businessId, String userId);
    /**
     * 查询用户启动的请假流程
     * @param page
     * @param id
     */
	void findApplyFlow(Pager<Leave> page, String userId);
	/**
	 * 查出该我审核的流程
	 * @param page
	 * @param userId
	 */
	void findAuditFlows(Pager<Leave> page, String userId);
	void addTaskComment(String leaveId, String userId, String comment);
	 List<Comment> getTaskComments(String businessId);
	void auditTask(String leaveId, String userId, String noticeId, Integer audit);
	/**
	 * 查询请假对象，带有流程信息
	 * @param id
	 * @return
	 */
	Leave findLeaveById(String id);
	void operateTask(String businessId, String userId);
	
	Pager<Leave> findApplyHistories(Pager<Leave> page, User user);
	/**
	 * 审核历史
	 * @param page
	 * @param id
	 */
	void findAuditHistories(Pager<Leave> page, Leave leave,String id);
}
