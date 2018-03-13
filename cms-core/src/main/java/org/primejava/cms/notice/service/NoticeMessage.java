/**
 * Project Name:com.wisdombud.education.forestry.public
 * File Name:NoticeMessage.java
 * Package Name:com.wisdombud.education.forestry.notice.business
 * Date:2014年11月10日 上午11:19:07
 * Copyright (c) 2014, www.wisdombud.com All Rights Reserved.
 */

package org.primejava.cms.notice.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.util.CommonDateUtils;
import org.primejava.cms.model.CommonConfig;
import org.primejava.cms.model.NoticeHistory;
import org.primejava.cms.notice.MailManager;
import org.primejava.cms.pojo.EmailForwardPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;


@Component("noticeMessage")
public class NoticeMessage {

    private static Logger LOGGER = Logger.getLogger(NoticeMessage.class);
    private String        noticeId;

    @Autowired
    private BaseDao     commonDao;

    public void execute() {
        if (StringUtils.isBlank(noticeId)) {
            LOGGER.info("not find notice message!");
            return;
        }
        NoticeHistory notice = (NoticeHistory) commonDao.findById(noticeId, NoticeHistory.class);
        if (null == notice) {
            LOGGER.info("not find notice history!");
            return;
        }
        if (notice.getNotice() == 0) {
            LOGGER.info("not find notice object!");
            return;
        }
            sendEmail(notice);
    
    }

    private void sendEmail(final NoticeHistory notice) {
        final EmailForwardPojo emailForwardPojo = buildMail(notice);
        if (null == emailForwardPojo) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                MailManager.getInstance().sendMailToUser(emailForwardPojo);
            }
        }).start();
    }

   
    private EmailForwardPojo buildMail(NoticeHistory notice) {
        boolean isEmail = Boolean.parseBoolean(getConfigValue("IS_EMAIL"));
        if (!isEmail) {
            LOGGER.info("email close status!");
            return null;
        }
        EmailForwardPojo pojo = new EmailForwardPojo();
        // 发送账户
        if (StringUtils.isNotBlank(notice.getSendEmail())) {
            pojo.setUser(notice.getSendUserEmail());
            pojo.setPassword(notice.getSendUserPassword());
            pojo.setEmailFrom(notice.getSendEmail());
        } else {
            pojo.setUser(getConfigValue("MAIL_USER"));
            pojo.setPassword(getConfigValue("MAIL_PASSWORD"));
            pojo.setEmailFrom(getConfigValue("MAIL_USER"));
        }
        // 服务器
        pojo.setMailHost(getConfigValue("MAIL_SERVER"));
        pojo.setMailHostPort(Integer.parseInt(getConfigValue("MAIL_PORT")));
        // 开启登录校验
        pojo.setLogonValidate(true);
        // 发送时间
        pojo.setSendTime(CommonDateUtils.now());
        // 接收人
        pojo.setDestinations(Lists.newArrayList(StringUtils.split(notice.getEmails(), ",")));
        pojo.setSubject(notice.getEmailTitle());
        pojo.setMessage(notice.getEmailContent());
        LOGGER.info(String.format("%s send email to %s", pojo.getUser(), notice.getEmails()));
        return pojo;
    }

    private String getConfigValue(String enField) throws NullPointerException {
        CommonConfig config = (CommonConfig) commonDao.findUniqueBy(CommonConfig.class, "enField", enField);
        if (null == config) {
            throw new NullPointerException(String.format("not find config paramter %s value", enField));
        }
        return config.getValue();
    }

    /**
     * noticeId.
     * 
     * @return the noticeId
     * @since JDK 1.6
     */
    public String getNoticeId() {
        return noticeId;
    }

    /**
     * noticeId.
     * 
     * @param noticeId the noticeId to set
     * @since JDK 1.6
     */
    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

}
