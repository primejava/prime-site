package org.primejava.cms.common;

import java.util.Map;

import org.junit.Test;
import org.primejava.basic.util.EnumUtils;
import org.primejava.cms.flow.enums.GroupTypeEnum;

import GroupTypeEnum.DocTypeEnum;

public class TestEnum {

	@Test
	public void testEnum() {
		Map<String, String> result = EnumUtils.enumProp2Map(
				GroupTypeEnum.class, "name", "value");
		for (String key : result.keySet()) {
			System.out.println("key = " + key + "; value = " + result.get(key));
		}
	}
	
}
