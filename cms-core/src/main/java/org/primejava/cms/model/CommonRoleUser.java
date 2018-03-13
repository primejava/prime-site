package org.primejava.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "com_role_user")
public class CommonRoleUser {
	/**
	 * 主键ID
	 */
	public String id;

	private String roleId;
	private String userId;
	
    public CommonRoleUser() {
        // TODO Auto-generated constructor stub

    }
    /**
     * Creates a new instance of CommonRoleUser.
     * @param roleId
     * @param id
     */

    public CommonRoleUser(String roleId, String userId) {
        // TODO Auto-generated constructor stub
        this.roleId = roleId;
        this.userId = userId;
    }

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

	@Column(name = "ROLE_ID", length = 36)
	public String getRoleId() {
		return this.roleId;
	}

	@Column(name = "USER_ID", length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setRoleId(final String roleId) {
		this.roleId = roleId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

}
