package org.primejava.cms.pojo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class EmailForwardPojo extends AbstractFowardPojo {
	private static final long serialVersionUID = 5858127647435150433L;
	private static final int DEFAULT_SMTP_HOST_PORT = 25;
	private static final boolean DEFAULT_SMTP_VALIDATE = true;
	private String subject;
	private String emailFrom;
	private Date sendTime;
	private List<String> attachmentFiles;
	private String user;
	private String password;
	private String mailHost;
	private int mailHostPort = 25;
	private boolean logonValidate = true;
	private List<String> ccUsers;
	private List<String> bccUsers;

	public final List<String> getBccUsers() {
		return this.bccUsers;
	}

	public final void setBccUsers(List<String> bccUsers) {
		this.bccUsers = bccUsers;
	}

	public final List<String> getCcUsers() {
		return this.ccUsers;
	}

	public final void setCcUsers(List<String> ccUsers) {
		this.ccUsers = ccUsers;
	}

	public final String getSubject() {
		return this.subject;
	}

	public final void setSubject(String subject) {
		this.subject = subject;
	}

	public final String getEmailFrom() {
		return this.emailFrom;
	}

	public final void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public final Date getSendTime() {
		return this.sendTime;
	}

	public final void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public final String getUser() {
		return this.user;
	}

	public final void setUser(String user) {
		this.user = user;
	}

	public final String getPassword() {
		return this.password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final String getMailHost() {
		return this.mailHost;
	}

	public final void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	public final int getMailHostPort() {
		return this.mailHostPort;
	}

	public final void setMailHostPort(int mailHostPort) {
		this.mailHostPort = mailHostPort;
	}

	public final boolean isLogonValidate() {
		return this.logonValidate;
	}

	public final void setLogonValidate(boolean logonValidate) {
		this.logonValidate = logonValidate;
	}

	public final List<String> getAttachmentFiles() {
		return this.attachmentFiles;
	}

	public final void setAttachmentFiles(List<String> attachmentFiles) {
		this.attachmentFiles = attachmentFiles;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.appendSuper(super.toString())
				.append("destinations", this.destinations)
				.append("ccUsers", this.ccUsers)
				.append("subject", this.subject)
				.append("sendTime", this.sendTime)
				.append("emailFrom", this.emailFrom)
				.append("password", this.password)
				.append("mailHost", this.mailHost)
				.append("bccUsers", this.bccUsers)
				.append("logonValidate", this.logonValidate)
				.append("message", this.message)
				.append("mailHostPort", this.mailHostPort)
				.append("attachmentFiles", this.attachmentFiles)
				.append("user", this.user).toString();
	}
}
