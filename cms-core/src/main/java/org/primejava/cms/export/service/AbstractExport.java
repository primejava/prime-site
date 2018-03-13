/**
 * Project Name:cn.gov.bjedu.base
 * File Name:AbstractAnalysis.java
 * Package Name:cn.gov.bjedu.question.analyze
 * Date:2015年4月30日 下午12:05:44
 * Copyright (c) 2015, www.wisdombud.com All Rights Reserved.
 */

package org.primejava.cms.export.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.primejava.basic.model.DateConstant;
import org.primejava.basic.model.SystemContext;
import org.primejava.basic.util.CommonDateUtils;
import org.primejava.cms.imp.pojo.ExcelErrorBean;
import org.primejava.cms.imp.pojo.ImportMapUtils;
import org.primejava.cms.imp.pojo.ImportRecord;
import org.primejava.cms.imp.pojo.ImportRecordBean;
import org.primejava.cms.imp.pojo.RelevanceTableField;
import org.primejava.cms.model.DictionaryCommons;
import org.primejava.cms.model.ImportOrExportParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName: AbstractAnalysis. <br/>
 * Function: TODO <br/>
 * date: 2015年4月30日 下午12:05:44 <br/>
 * 
 * @author xs
 * @version
 * @since JDK 1.6
 */
public abstract class AbstractExport {

    protected static Logger LOGGER            = LoggerFactory.getLogger(AbstractExport.class);

    protected int           JSON_TYPE_CONTENT = 1;
    protected int           SQL_TYPE_CONTENT  = 2;
    protected int           FUNCTION_CONTENT  = 3;
    protected int           STRING_CONTENT    = 4;
    protected int           OTHER_CONTENT     = 5;
    protected int           DEFAULT_CONTENT   = 6;

    /**
     * 根据插入参数表的集合得到其map集合. <br/>
     * 
     * @author mmzhao
     * @param importParams
     * @return
     */
    protected Map<String, ImportOrExportParam> findImportParamMap(List<ImportOrExportParam> importParams) {
        Map<String, ImportOrExportParam> importParamMap = new HashMap<String, ImportOrExportParam>();
        for (ImportOrExportParam importParam : importParams) {
            importParamMap.put(importParam.getFieldProperty(), importParam);
        }
        return importParamMap;
    }

    protected Map<String, RelevanceTableField> findRelevanceMap(List<RelevanceTableField> relevanceTableFields) {
        Map<String, RelevanceTableField> map = new HashMap<String, RelevanceTableField>();
        for (RelevanceTableField tableField : relevanceTableFields) {
            map.put(tableField.getTable(), tableField);
        }
        return map;
    }

    /**
     * 批量插入或修改记录. <br/>
     * 
     * @author mmzhao
     * @param studentBeans
     * @param result
     * @param rowIndex
     * @param errorList
     */
    protected void insertOrUpdateRecord(ImportRecord result, int rowIndex, List<ExcelErrorBean> errorList,
            ImportRecordBean insertRecord, ImportRecordBean updateRecord) {
        if (null == insertRecord) {
            insertRecord = new ImportRecordBean();
        }
        if (null == updateRecord) {
            updateRecord = new ImportRecordBean();
        }
        result.setTotalRecord(rowIndex - 1);
        result.setInsertRecord(insertRecord.getSaveOrUpdateCount());
        result.setErrorMsgs(errorList);
        result.setUpdateCount(updateRecord.getSaveOrUpdateCount());
        result.setSuccessCount(insertRecord.getSaveOrUpdateCount() + updateRecord.getSaveOrUpdateCount());
        result.setErrorRecord(result.getTotalRecord() - result.getSuccessCount());
    }

    /**
     * 根据字典表翻译. <br/>
     * 
     * @author mmzhao
     * @param excelValue
     * @param dictionaryCommons
     * @param value
     * @return
     */
    protected String translate(String excelValue, DictionaryCommons dictionaryCommons) {
        if (StringUtils.isNotBlank(excelValue) && null != dictionaryCommons) {
            return dictionaryCommons.getId();
        }
        return "";
    }

    /**
     * 根据参数表判断该列是否需要翻译及如何翻译. <br/>
     * 
     * @author mmzhao
     * @param row
     * @param importParam
     * @return
     */
    protected String translates(Row row, ImportOrExportParam importParam) {
        String value = "";
        String key = checkAndGetString(row, importParam.getColumnNumber());
        if (StringUtils.isBlank(key) || StringUtils.isBlank(importParam.getDataType())) {
            return value;
        }
        switch (importParam.getDataType()) {
            case "int":
                value = translateBySource(row, key, importParam);
                break;
            case "Short":
                value = Short.valueOf(key).toString();
                break;
            case "Date":
                value = CommonDateUtils.dateToString(CommonDateUtils.stringToDate(key, importParam.getDataSource()),
                                                     DateConstant.YYMMDD);
                break;
            case "String":
                value = translateBySource(row, key, importParam);
                break;
            default:
                value = key;
                break;
        }
        return value;
    }

    /**
     * 根据数据来源判断翻译所用方法. <br/>
     * 
     * @author mmzhao
     * @param row
     * @param key
     * @param dataSource
     * @return
     */
    protected String translateBySource(Row row, String key, ImportOrExportParam importParam) {
        String value = key;
        if (null == importParam || StringUtils.isBlank(importParam.getFieldProperty())) {
            return key;
        }
        if (importParam.getMethod()>0) {
            for (Object[] obj : ImportMapUtils.MAP.get(importParam.getFieldProperty())) {
                if (obj[0].equals(key)) {
                    value = obj[1].toString();
                }
            }
        }
        switch (importParam.getFieldProperty()) {
            case "-":
                value = key + '-';
                break;
            case "--":
                value = key.length() > 3 ? key.substring(0, 4) + "-" + key.substring(4) : key;
                break;
        }
        return value;
    }

    /**
     * 根据参数表翻译. <br/>
     * 
     * @author mmzhao
     * @param excelValue
     * @param dictionaryCommons
     * @param value
     * @return
     */
    protected String translate(Row row, ImportOrExportParam importParam) {
        if (null != importParam && importParam.getColumnNumber()>0) {
            return checkAndGetString(row, importParam.getColumnNumber());
        }
        return "";
    }

    /**
     * 验证Excel指定行列是否为空. <br/>
     * 
     * @author mmzhao
     * @param row
     * @param errorBeans
     * @param rowNumber
     * @param columnNumber
     * @param errorMsg
     */
    protected void validateColumn(Row row, List<ExcelErrorBean> errorBeans, int rowNumber, int columnNumber,
            String errorMsg) {
        if (StringUtils.isBlank(checkAndGetString(row, columnNumber))) {
            errorBeans.add(getErrorBean(rowNumber, columnNumber + 1, errorMsg));
        }
    }

    protected ExcelErrorBean getErrorBean(int rowNumber, int columnNumber, String errorMsg) {
        ExcelErrorBean errorBean = new ExcelErrorBean();
        errorBean.setRowIndex(rowNumber);
        errorBean.setColumnIndex(columnNumber);
        errorBean.setErrorMsg(errorMsg);
        return errorBean;
    }

    /**
     * 检测指定行的列是否为空. <br/>
     * 
     * @author mmzhao
     * @param row
     * @param cellIndex
     * @return
     */
    protected String checkAndGetString(Row row, final int cellIndex) {
        String value = "";
        Cell cell = row.getCell(cellIndex);
        if (cell != null) {
            cell.setCellType(Cell.CELL_TYPE_STRING); // 防止 Cannot get a text
                                                     // value from a numeric
                                                     // cell
            value = cell.getStringCellValue().trim();
        }
        return value;
    }

    protected String templateDir = "/template";

    protected WritableWorkbook createWorkBook(OutputStream out, String templateName) throws IOException, BiffException {
        // 按照excel模板格式导出
        String templateFileRoot = SystemContext.getRealPath()+this.templateDir;
        Workbook wb = Workbook.getWorkbook(new File(templateFileRoot, templateName));
        /***************************************************************************
         * 以下两行用于修复 导出时
         * jxl.write.biff.WriteAccessRecord.<init>(WriteAccessRecord.java:59)
         * java.lang.ArrayIndexOutOfBoundsException 的bug,根据API的说明
         * When writing worksheets, it uses the value from the WorkbookSettings
         * object,
         * if this is not set (null) this is hard coded as
         * Java Excel API + Version number
         ***************************************************************************/
        WorkbookSettings settings = new WorkbookSettings();
        settings.setWriteAccess(null);
        WritableWorkbook exportExcel = Workbook.createWorkbook(out, wb, settings);

        return exportExcel;
    }

    protected abstract String genExportFileName(String name);

    protected abstract String genTemplateName();

}
