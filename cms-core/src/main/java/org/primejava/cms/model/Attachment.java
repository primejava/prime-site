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
@Table(name = "t_attachment")
public class Attachment {
	public static final String OBJECT_ID = "objectId";
	private String id;
	private String objectId;
	private String name;
	private String newName;
	private String type;
	private String path;
	private String url;
	private String fileSize;
	private Date uploadTime;
	//上次索引的时间
	private long lastModify;
	public Attachment() {
	}

	public Attachment(String id) {
		this.setId(id);
	}

	public Attachment(String id, String objectId, String name, String newName,
			String type, String path, String url, String fileSize,
			Date uploadTime) {
		this.setId(id);
		this.objectId = objectId;
		this.name = name;
		this.newName = newName;
		this.type = type;
		this.path = path;
		this.url = url;
		this.fileSize = fileSize;
		this.uploadTime = uploadTime;
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
	
	@Column(name = "object_id", length = 40)
	public String getObjectId() {
		return this.objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	@Column(name = "name", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "new_name", length = 64)
	public String getNewName() {
		return this.newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	@Column(name = "type", length = 40)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "path")
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "file_size", length = 20)
	public String getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "upload_time", length = 19)
	public Date getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public long getLastModify() {
		return lastModify;
	}

	public void setLastModify(long lastModify) {
		this.lastModify = lastModify;
	}


}
