/**
 * Project Name:com.wisdombud.education.public
 * File Name:ExcelErrorBean.java
 * Package Name:com.wisdombud.education.support
 * Date:2014年9月24日 下午6:02:24
 * Copyright (c) 2014, www.wisdombud.com All Rights Reserved.
 */

package org.primejava.cms.imp.pojo;

/**
 * ClassName: ExcelErrorBean. <br/>
 * Function: TODO <br/>
 * date: 2014年9月24日 下午6:02:24 <br/>
 * @author mmzhao
 * @version
 * @since JDK 1.6
 */
public class ExcelErrorBean {
    private int    rowIndex;
    private int    columnIndex;
    private String errorMsg;

    /**
     * Creates a new instance of ExcelErrorBean.
     */

    public ExcelErrorBean() {
    }

    /**
     * Creates a new instance of ExcelErrorBean.
     * @param rowIndex
     * @param errorMsg
     */

    public ExcelErrorBean(int rowIndex, String errorMsg) {
        this.rowIndex = rowIndex;
        this.errorMsg = errorMsg;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ExcelErrorBean [rowIndex=" + rowIndex + ", columnIndex=" + columnIndex + ", errorMsg=" + errorMsg + "]";
    }

}
