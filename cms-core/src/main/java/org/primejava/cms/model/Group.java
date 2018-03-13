package org.primejava.cms.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

//流程用户组
@Entity
@Table(name = "t_group")
public class Group {
	public String id;
	private String name;
	private String groupType;

	private List<String> users;

	public Group() {
	}

	public Group(String id) {
		this.id = id;
	}

	public Group(String id, String name, String type) {
		this.id = id;
		this.name = name;
		this.groupType = type;
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
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupType() {
		return this.groupType;
	}

	public void setGroupType(String type) {
		this.groupType = type;
	}

	@Transient
	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}
}
