package org.primejava.cms.model;

import java.util.List;
import java.util.Map;

public class QueryEntity {

	private String id;// 字段名
	private String label;// 字段要显示的标签
	private String type;// 字段类型
	private String input;// 前台输入类型-输入还是下拉
	private Map<String, Integer> values;// 下拉框里面的值
	private List<String> operators;// 大于等于。。。

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public Map<String, Integer> getValues() {
		return values;
	}

	public void setValues(Map<String, Integer> values) {
		this.values = values;
	}

	public List<String> getOperators() {
		return operators;
	}

	public void setOperators(List<String> operators) {
		this.operators = operators;
	}

}
