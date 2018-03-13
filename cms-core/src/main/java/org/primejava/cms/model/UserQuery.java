package org.primejava.cms.model;

import java.util.Map;

public class UserQuery {

    private String              relation;
    private String              column;
    private String              operate;
    private String              condition;
    private Integer             method;
    private Map<String, String> operates;
    private Map<String, String> conditions;
    private String              tableName;

    public Map<String, String> getOperates() {
        return operates;
    }

    public void setOperates(Map<String, String> operates) {
        this.operates = operates;
    }

    public Map<String, String> getConditions() {
        return conditions;
    }

    public void setConditions(Map<String, String> conditions) {
        this.conditions = conditions;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "StudentQuery [relation=" + relation + ", column=" + column + ", operate=" + operate + ", condition="
               + condition + "]";
    }

}
