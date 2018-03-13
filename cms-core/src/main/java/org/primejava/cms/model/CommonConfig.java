package org.primejava.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_com_config")
public class CommonConfig {

	private String enField;
	private String name;
	private String value;
	private Short type;
	private String dataType;

	public CommonConfig() {
	}

	public CommonConfig(String id) {
		this.id = id;
	}

	/**
	 * 主键ID
	 */
	public String id;

	/**
	 * Hibernate3.6以后,UUIDHexGenerator(uuid)已不推荐使用，改用UUIDGenerator(org.hibernate
	 * .id.UUIDGenerator)
	 */
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

	public CommonConfig(String id, String name, String value, Boolean enabled) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	@Column(name = "NAME", length = 63)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "VALUE")
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "EN_FIELD")
	public String getEnField() {
		return enField;
	}

	public void setEnField(String enField) {
		this.enField = enField;
	}

	@Column(name = "TYPE")
	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "DATA_TYPE")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

}
