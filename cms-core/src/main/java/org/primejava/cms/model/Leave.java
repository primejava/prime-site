package org.primejava.cms.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.primejava.cms.flow.AbstractAuditEntity;

/**
 * 自动在数据库生成表，呵呵，只要运行就有了
 * @author liguo
 *
 */
@Entity
@Table(name = "t_leave")
public class Leave extends AbstractAuditEntity{
	private User user;
	private String cause;
	private Integer status;


	public Leave(String cause) {
		super();
		this.cause = cause;
	}

	public Leave() {
	}

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="uid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}




}
