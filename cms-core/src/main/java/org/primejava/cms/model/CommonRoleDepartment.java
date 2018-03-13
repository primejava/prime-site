package org.primejava.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "commonrole_department")
public class CommonRoleDepartment {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 2451379266402670421L;
	/**
	 * 主键ID
	 */
	public String id;
	private String roleId;
	private String departmentId;

    /**
     * Creates a new instance of CommonRoleDepartment.
     */

    public CommonRoleDepartment() {
        // TODO Auto-generated constructor stub

    }

    /**
     * Creates a new instance of CommonRoleDepartment.
     * @param id
     * @param departmentId2
     */

    public CommonRoleDepartment(final String roleId, final String departmentId) {
        // TODO Auto-generated constructor stub
        this.roleId = roleId;
        this.departmentId = departmentId;
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

	@Column(name = "DEPARTMENT_ID", length = 36)
	public String getDepartmentId() {
		return this.departmentId;
	}

	@Column(name = "ROLE_ID", length = 36)
	public String getRoleId() {
		return this.roleId;
	}

	public void setDepartmentId(final String departmentId) {
		this.departmentId = departmentId;
	}

	public void setRoleId(final String roleId) {
		this.roleId = roleId;
	}

}
