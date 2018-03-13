package org.primejava.cms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_messagesend")
public class MessageSend implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8803896926980482917L;
	public String id;
	private String title;
	private String receiver;
	private String receiverName;
	private Date sendTime;
	private Integer sendAll;

	private String sendUser;

	private Contents content;
	private String category;

	private Integer receiverNumber;
	private Integer readNumber;
	//用于系统发送消息
	public static User SYSTEM=new User("000000", "系统", null, null, null, null, 1);
	
	@Column(name = "SENDER")
	public String getSendUser() {
		return sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDHexGenerator")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "TITLE", length = 64)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "RECEIVER", length = 880)
	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "RECEIVER_NAME", length = 2430)
	public String getReceiverName() {
		return this.receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SEND_TIME", length = 0)
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "SEND_ALL")
	public Integer getSendAll() {
		return this.sendAll;
	}

	public void setSendAll(Integer sendAll) {
		this.sendAll = sendAll;
	}

	@Column(name = "CATEGORY")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Transient
	public Integer getReceiverNumber() {
		return receiverNumber;
	}

	public void setReceiverNumber(Integer receiverNumber) {
		this.receiverNumber = receiverNumber;
	}

	@Transient
	public Integer getReadNumber() {
		return readNumber;
	}

	public void setReadNumber(Integer readNumber) {
		this.readNumber = readNumber;
	}

	@Transient
	public Contents getContent() {
		return content;
	}

	public void setContent(Contents content) {
		this.content = content;
	}

}
