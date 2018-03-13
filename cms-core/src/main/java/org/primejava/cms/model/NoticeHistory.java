package org.primejava.cms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "t_notice_history")
public class NoticeHistory {

    private Integer notice;
    private String  emailTitle;
    private String  emailContent;
    private String  emails;
    private String  sendUserEmail;
    private String  sendUserPassword;
    private String  sendEmail;
    private String  smsContent;
    private String  smses;
    private String  userId;
    private Date    noticeTime;
    /**
     * 主键ID
     */
    public String id;

    /**
     * Hibernate3.6以后,UUIDHexGenerator(uuid)已不推荐使用，改用UUIDGenerator(org.hibernate
     * .id.UUIDGenerator)
     */
    @Id
    @Column(name = "ID", updatable = false)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDHexGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    /**
     * notice.
     * @return the notice
     * @since JDK 1.6
     */
    @Column(name = "NOTICE")
    public Integer getNotice() {
        return notice;
    }

    /**
     * notice.
     * @param notice the notice to set
     * @since JDK 1.6
     */
    public void setNotice(Integer notice) {
        this.notice = notice;
    }

    /**
     * emailTitle.
     * @return the emailTitle
     * @since JDK 1.6
     */
    @Column(name = "EMAIL_TITLE")
    public String getEmailTitle() {
        return emailTitle;
    }

    /**
     * emailTitle.
     * @param emailTitle the emailTitle to set
     * @since JDK 1.6
     */
    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle;
    }

    /**
     * emailContent.
     * @return the emailContent
     * @since JDK 1.6
     */
    @Column(name = "EMAIL_CONTENT")
    public String getEmailContent() {
        return emailContent;
    }

    /**
     * emailContent.
     * @param emailContent the emailContent to set
     * @since JDK 1.6
     */
    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    /**
     * emails.
     * @return the emails
     * @since JDK 1.6
     */
    @Column(name = "EMAILS")
    public String getEmails() {
        return emails;
    }

    /**
     * emails.
     * @param emails the emails to set
     * @since JDK 1.6
     */
    public void setEmails(String emails) {
        this.emails = emails;
    }

    /**
     * sendUserEmail.
     * @return the sendUserEmail
     * @since JDK 1.6
     */
    @Column(name = "SEND_USER_EMAIL")
    public String getSendUserEmail() {
        return sendUserEmail;
    }

    /**
     * sendUserEmail.
     * @param sendUserEmail the sendUserEmail to set
     * @since JDK 1.6
     */
    public void setSendUserEmail(String sendUserEmail) {
        this.sendUserEmail = sendUserEmail;
    }

    /**
     * sendUserPassword.
     * @return the sendUserPassword
     * @since JDK 1.6
     */
    @Column(name = "SEND_USER_PASSWORD")
    public String getSendUserPassword() {
        return sendUserPassword;
    }

    /**
     * sendUserPassword.
     * @param sendUserPassword the sendUserPassword to set
     * @since JDK 1.6
     */
    public void setSendUserPassword(String sendUserPassword) {
        this.sendUserPassword = sendUserPassword;
    }

    /**
     * sendEmail.
     * @return the sendEmail
     * @since JDK 1.6
     */
    @Column(name = "SEND_EMAIL")
    public String getSendEmail() {
        return sendEmail;
    }

    /**
     * sendEmail.
     * @param sendEmail the sendEmail to set
     * @since JDK 1.6
     */
    public void setSendEmail(String sendEmail) {
        this.sendEmail = sendEmail;
    }

    /**
     * smsContent.
     * @return the smsContent
     * @since JDK 1.6
     */
    @Column(name = "SMS_CONTENT")
    public String getSmsContent() {
        return smsContent;
    }

    /**
     * smsContent.
     * @param smsContent the smsContent to set
     * @since JDK 1.6
     */
    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    /**
     * smses.
     * @return the smses
     * @since JDK 1.6
     */
    @Column(name = "SMSES")
    public String getSmses() {
        return smses;
    }

    /**
     * smses.
     * @param smses the smses to set
     * @since JDK 1.6
     */
    public void setSmses(String smses) {
        this.smses = smses;
    }

    /**
     * userId.
     * @return the userId
     * @since JDK 1.6
     */
    @Column(name = "USER_ID")
    public String getUserId() {
        return userId;
    }

    /**
     * userId.
     * @param userId the userId to set
     * @since JDK 1.6
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * noticeTime.
     * @return the noticeTime
     * @since JDK 1.6
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NOTICE_TIME")
    public Date getNoticeTime() {
        return noticeTime;
    }

    /**
     * noticeTime.
     * @param noticeTime the noticeTime to set
     * @since JDK 1.6
     */
    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

}
