package org.primejava.cms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "t_article")
public class Article {
	private String id;
	private String title;
	/**
	 * 关键字:通过|来分割不同的关键字
	 */
	private String keyword;
	/**
	 * 文章的状态，默认为0表示未发表，1表示已发布
	 */
	private int status;
	/**
	 * 是否是推荐文章,0表示不推荐，1表示推荐
	 */
	private int recommend;
	/**
	 * 文章的内容
	 */
	private Contents contents;

	/**
	 * 文章的发布时间，用来进行排序的
	 */
	private Date publishDate;
	/**
	 * 文章的创建时间
	 */
	private Date createDate;
	/**
	 * 文章的作者名称
	 */
	private String author;

	/**
	 * 文章的发布者
	 */
	private User user;

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

	@Column(name = "TITLE", length = 127)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	@Transient
	public Contents getContents() {
		return contents;
	}

	public void setContents(Contents contents) {
		this.contents = contents;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PUBLISH_TIME", length = 0)
	public Date getPublishDate() {
		return publishDate;
	}

	@ManyToOne
	@JoinColumn(name = "uid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
