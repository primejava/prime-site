package org.primejava.cms.flow;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class AbstractAuditEntity implements Cloneable {
	private String id;
	private Integer audit; // 审核结果
	private String auditer; // 审核人
	private Date auditTime; // 审核时间
	private Task task;
	private List<Comment> comments;

	@Id
	@Column(name = "id", updatable = false, length = 36)
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDHexGenerator")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Transient
	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	@Transient
	public String getAuditer() {
		return auditer;
	}

	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}

	@Transient
	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	@Transient
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@Transient
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Transient
	public String getAuditString() {
		return FlowStatusEnum.valueByIndex(this.getAudit()).getName();
	}

	@Transient
	public String getLastComment() {
		if (this.comments.isEmpty()) {
			return "";
		}
		return this.comments.get(this.comments.size() - 1).getFullMessage();
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @author xs xiaoshan@wisdombud.com
	 * @return
	 * @throws CloneNotSupportedException
	 * @see java.lang.Object#clone()
	 */

	@Override
	protected Object clone() throws CloneNotSupportedException {

		// TODO Auto-generated method stub
		return super.clone();
	}
}
