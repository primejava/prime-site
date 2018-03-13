package org.primejava.cms.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.util.CommonDateUtils;
import org.primejava.cms.model.NoticeHistory;
import org.primejava.cms.notice.service.NoticeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service("emailServcie")
public class EmailServiceImpl implements EmailService {
	@Autowired
	private BaseDao commonDao;
	@Autowired
	private NoticeMessage noticeMessage;
	@Override
	public NoticeHistory addNoticeHistory(String senderId, String address,
			String title, String content) {
		NoticeHistory notice = new NoticeHistory();
        notice.setNotice(1);
        List<String> emails = Lists.newArrayList();
        emails.add(address);
        notice.setEmails(StringUtils.join(emails, ","));
        notice.setEmailTitle("邮件标题");
        notice.setEmailContent(content);
        notice.setUserId(senderId);
        notice.setNoticeTime(CommonDateUtils.now());
        return (NoticeHistory) commonDao.add(notice);
	}

	@Override
	public void sendEmail(String historyId) {
		noticeMessage.setNoticeId(historyId);
		noticeMessage.execute();
	}

}
