/**
 * Project Name:com.wisdombud.education.public
 * File Name:ImportResult.java
 * Package Name:com.wisdombud.education.support
 * Date:2014年9月25日 下午2:55:04
 * Copyright (c) 2014, www.wisdombud.com All Rights Reserved.
 */

package org.primejava.cms.imp.pojo;

import java.util.List;

/**
 * ClassName: ImportResult. <br/>
 * Function: TODO <br/>
 * date: 2014年9月25日 下午2:55:04 <br/>
 * 
 * @author mmzhao
 * @version
 * @since JDK 1.6
 */
public class ImportRecord {
    private int                  totalRecord  = 0;
    private int                  insertRecord = 0;
    private int                  errorRecord  = 0;
    private int                  successCount = 0;
    private int                  updateCount  = 0;
    private List<ExcelErrorBean> errorMsgs;
    private String               attachmentId;
    private String               excelName;

    private String               graduationYear;

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public List<ExcelErrorBean> getErrorMsgs() {
        return errorMsgs;
    }

    public void setErrorMsgs(List<ExcelErrorBean> errorMsgs) {
        this.errorMsgs = errorMsgs;
    }

    public int getInsertRecord() {
        return insertRecord;
    }

    public void setInsertRecord(int insertRecord) {
        this.insertRecord = insertRecord;
    }

    public int getErrorRecord() {
        return errorRecord;
    }

    public void setErrorRecord(int errorRecord) {
        this.errorRecord = errorRecord;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

}
