package org.primejava.cms.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.basic.util.CommonDateUtils;
import org.primejava.cms.dao.LeaveDao;
import org.primejava.cms.flow.FlowStatusEnum;
import org.primejava.cms.flow.LeaveFlowService;
import org.primejava.cms.model.Leave;
import org.primejava.cms.model.NoticeHistory;
import org.primejava.cms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {
	@Autowired
	private LeaveDao leaveDao;
	@Autowired
	private BaseDao commonDao;
	@Autowired
	private LeaveFlowService leaveFlowService;
	@Override
	public void saveOrUpdateLeave(Leave leave) {
		if(StringUtils.isEmpty(leave.getId())){
			System.out.println("启动流程");
			leave.setStatus(FlowStatusEnum.APPLY.getValue());
			leave=leaveDao.add(leave);
			leaveFlowService.startFlow(leave.getId(),leave.getUser().getId());
		}else{
			System.out.println("重新提交");
			leave.setStatus(FlowStatusEnum.APPLY.getValue());
			leaveDao.update(leave);
			leaveFlowService.operateTask(leave.getId(),leave.getUser().getId());
		}
		
	}
	@Override
	public Pager<Leave> findLeaves(Pager<Leave> page, User user) {
		return leaveDao.findLeaves(page, user);
	}
	@Override
	public Leave load(String id) {
		return leaveDao.load(id);
	}
	@Override
	public void saveAudit(String leaveId, Integer audit, String comment,User user) {
		Leave leave=leaveDao.load(leaveId);
		leaveFlowService.addTaskComment(leaveId,user.getId(),comment);
		String noticeId=buildNoticeHistory(user.getId(),comment,leave);
		leaveFlowService.auditTask(leaveId,user.getId(),noticeId,
                  audit);
		leave.setStatus(audit);
		leaveDao.update(leave);
	}
	

    private String buildNoticeHistory(String userId,String comment,Leave leave) {
        NoticeHistory notice = new NoticeHistory();
        notice.setNotice(1);
        List<String> emails = Lists.newArrayList();
        User applyUser=leave.getUser();
        if (!StringUtils.isEmpty(applyUser.getEmail()) && !emails.contains(applyUser.getEmail())) {
            emails.add(applyUser.getEmail());
        }
        notice.setEmails(StringUtils.join(emails, ","));
        notice.setEmailTitle("邮件标题");
        notice.setEmailContent(comment);
        notice.setUserId(userId);
        notice.setNoticeTime(CommonDateUtils.now());
        commonDao.add(notice);
        return notice.getId();
    }
    
	@Override
	public Pager<Leave> findApplyHistories(Pager<Leave> page, User user) {
		Pager<Leave> results=leaveFlowService.findApplyHistories(page,user);
		return results;
	}


}
