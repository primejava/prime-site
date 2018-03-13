package org.primejava.cms.flow;

public enum FlowStatusEnum {
	DELETE(-3, "删除"), SAVE(-1, "保存"), APPLY(1, "申请"), PASS(2, "通过"), NOT_PASS(
			3, "未通过"), TURN(4, "转派"), BACK(5, "退回");

	int value;
	String name;

	FlowStatusEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	// 获取名字
	public String getName() {
		return name;
	}

	// 根据value获取enum对象
	public static FlowStatusEnum valueByIndex(int index) {
		for (FlowStatusEnum status : FlowStatusEnum.values()) {
			if (status.getValue() == index) {
				return status;
			}
		}
		return null;
	}

	public static FlowStatusEnum[] audits() {
		return new FlowStatusEnum[] { FlowStatusEnum.PASS,
				FlowStatusEnum.NOT_PASS };
	}
}
