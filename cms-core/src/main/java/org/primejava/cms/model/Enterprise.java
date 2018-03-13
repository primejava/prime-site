package org.primejava.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.primejava.cms.flow.AbstractAuditEntity;

@Entity
@Table(name = "t_enterprise")
public class Enterprise extends AbstractAuditEntity implements IUser {

	private String userName;
	private String contactName;
	private String contactMobilePhone;
	private String contactEmail;
	private String name;
	private Integer status;
	private String password;

	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Contents contents;
	private Attachment attachment;

	@Transient
	public Contents getContents() {
		return contents;
	}

	public void setContents(Contents contents) {
		this.contents = contents;
	}

	@Transient
	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	@Override
	@Column(name = "USER_NAME", length = 36)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "CONTACT_NAME", length = 36)
	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@Column(name = "CONTACT_MOBILE_PHONE", length = 36)
	public String getContactMobilePhone() {
		return contactMobilePhone;
	}

	public void setContactMobilePhone(String contactMobilePhone) {
		this.contactMobilePhone = contactMobilePhone;
	}

	@Column(name = "CONTACT_EMAIL", length = 64)
	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	@Override
	@Column(name = "NAME", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	@Column(name = "PASSWORD")
	public String getPassword() {
		return this.password;
	}

}
