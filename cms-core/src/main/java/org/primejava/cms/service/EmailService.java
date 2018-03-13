package org.primejava.cms.service;

import org.primejava.cms.model.NoticeHistory;

public interface EmailService {
	
	public NoticeHistory addNoticeHistory(String senderId,String address,String title,String content);
	
	public void sendEmail(String historyId);

}
