package org.primejava.cms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 角色对象，用来对应可以访问的功能，系统中为了简单值定义了管理员，发布人员和审核人员
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_role")
public class Role {
	/**
	 * 角色id
	 */
	private String id;
	/**
	 * 角色的名称，中文
	 */
	private String name;

	public Role() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
