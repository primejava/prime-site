package org.primejava.cms.flow.enums;

import java.util.ArrayList;
import java.util.List;

public enum GroupTypeEnum {

	COLLEGE_TEACHER("collegeTeacher", "学院组类型"), TEACHER("Teacher", "就业组类型");

	String name;
	String value;

	private GroupTypeEnum(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}

	public static List<GroupTypeEnum> getAllEnums() {
		List<GroupTypeEnum> list = new ArrayList<GroupTypeEnum>();
		for (GroupTypeEnum enumEntity : values()) {
			list.add(enumEntity);
		}
		return list;
	}
}
