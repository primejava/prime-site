package org.primejava.cms.flow.enums;

public enum ReadStatusEnum {
    UNREAD(1, "未读"), READ(2, "已读");
    int    value;
    String name;

    ReadStatusEnum(int value, String name) {
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

    
}
