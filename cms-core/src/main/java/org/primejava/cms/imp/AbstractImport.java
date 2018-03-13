/**
 * Project Name:cn.gov.bjedu.base
 * File Name:AbstractAnalysis.java
 * Package Name:cn.gov.bjedu.question.analyze
 * Date:2015年4月30日 下午12:05:44
 * Copyright (c) 2015, www.wisdombud.com All Rights Reserved.
 */

package org.primejava.cms.imp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.primejava.basic.model.DateConstant;
import org.primejava.basic.util.CommonDateUtils;
import org.primejava.cms.imp.pojo.ExcelErrorBean;
import org.primejava.cms.imp.pojo.ImportMapUtils;
import org.primejava.cms.imp.pojo.ImportRecord;
import org.primejava.cms.imp.pojo.ImportRecordBean;
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
public abstract class AbstractImport {

    protected static Logger logger            = LoggerFactory.getLogger(AbstractImport.class);

    protected int           JSON_TYPE_CONTENT = 1; //枚举类型
    protected int           SQL_TYPE_CONTENT  = 2; //sql类型
    protected int           FUNCTION_CONTENT  = 3; //函数类型

    /**
     * 根据插入参数表的集合得到其map集合. <br/>
     * 
     * @author mmzhao
     * @param importParams
     * @return
     */
    protected Map<String, ImportOrExportParam> findImportParamMap(List<ImportOrExportParam> importParams) {
        Map<String, ImportOrExportParam> importParamMap = new HashMap<>();
        for (ImportOrExportParam importParam : importParams) {
            importParamMap.put(importParam.getFieldProperty(), importParam);
        }
        return importParamMap;
    }

    /**
     * 根据身份证号得到加密后的密码. <br/>
     * 
     * @author mmzhao
     * @param identityCard
     * @return
     */
    protected String encrypt(String identityCard) {
        String rawPassword = "";
        if (StringUtils.isNotBlank(identityCard) && identityCard.length() >= 6) {
            rawPassword = identityCard.substring(identityCard.length() - 6, identityCard.length());
        }
        if (StringUtils.isNotBlank(rawPassword)) {
            return rawPassword; // 密码
        }
        return rawPassword;
    }



    protected ImportOrExportParam getImportParam(String value) {
        return ImportMapUtils.IMPORT_PARAM_MAP.get(value);
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
            ImportRecordBean insertRecord, ImportRecordBean updateRecord, int x) {
        if (null == insertRecord) {
            insertRecord = new ImportRecordBean();
        }
        if (null == updateRecord) {
            updateRecord = new ImportRecordBean();
        }
        result.setTotalRecord(rowIndex - 1);
        result.setInsertRecord(insertRecord.getSaveOrUpdateCount() / x);
        result.setErrorMsgs(errorList);
        result.setUpdateCount(updateRecord.getSaveOrUpdateCount() / x);
        result.setSuccessCount((insertRecord.getSaveOrUpdateCount() + updateRecord.getSaveOrUpdateCount()) / x);
        result.setErrorRecord((result.getTotalRecord() - result.getSuccessCount()) / x);
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
        if (importParam.getMethod() == 6) {
            if (StringUtils.isNotBlank(importParam.getDataSource())) {
                return importParam.getDataSource();
            }
            if (importParam.getDataType().equals("Date")) {
                return CommonDateUtils.dateToString(CommonDateUtils.now(), DateConstant.YYMMDDHHMMSS);
            }
            return null;
        }
        if (StringUtils.isBlank(key) || StringUtils.isBlank(importParam.getDataType())) {
            return value;
        }
        return getValueByDataType(value, key, row, importParam);
    }

    /**
     * 功能: 根据数据类型翻译.<br/>
     * date: 2015年6月24日 下午3:05:20 <br/>
     * 
     * @author mmzhao@wisdombud.com
     * @param row
     * @param key
     * @param importParam
     * @return
     */
    private String getValueByDataType(String value, String key, Row row, ImportOrExportParam importParam) {

        switch (importParam.getDataType()) {
            case "int":
                value = translateBySource(row, key, importParam);
                break;
            case "Short":
                value = Short.valueOf(key).toString();
                break;
            case "Date":
                value = CommonDateUtils.dateToString(CommonDateUtils.stringToDate(key, importParam.getDataSource()),
                                                     DateConstant.YYMMDDHHMMSS);
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
        if (importParam.getMethod()>0
            && null != ImportMapUtils.MAP.get(importParam.getFieldProperty())) {
            for (Object[] obj : ImportMapUtils.MAP.get(importParam.getFieldProperty())) {
                if (obj[0].equals(key)) {
                    value = obj[1].toString();
                    break;
                }
            }
            return value;
        }
        value = getValueByFieldProperty(value, row, importParam);
        if (StringUtils.isNotBlank(importParam.getDataSource())) {
            switch (importParam.getDataSource()) {
                case "-":
                    value = key + '-';
                    break;
                case "--":
                    value = key.length() > 3 ? key.substring(0, 4) + "-" + key.substring(4) : key;
                    break;
            }
        }
        return value;
    }

    /**
     * 功能: 根据数据对应的数据库字段翻译.<br/>
     * date: 2015年6月24日 下午2:48:59 <br/>
     * 
     * @author mmzhao@wisdombud.com
     * @param row
     * @param importParam
     */
    private String getValueByFieldProperty(String value, Row row, ImportOrExportParam importParam) {
        switch (importParam.getFieldProperty()) {
            case "REMARK":
                value = translate(row, getImportParam("REMARK"));
                break;
            case "RESERVED_TWO":
                value = translate(row, getImportParam("REMARK"));
                value = value.substring(value.indexOf(" ") + 1);
                break;

        }
        return value;
    }

    /**
     * 功能: 指定数据库字段翻译.<br/>
     * date: 2015年6月24日 下午2:48:59 <br/>
     * 
     * @author mmzhao@wisdombud.com
     * @param row
     * @param importParam
     */
    private String getStringByFieldProperty(String key, Row row) {
        String value = translate(row, getImportParam(key));
        if (null == getImportParam(key)) {
            return value;
        }
        return translateBySource(row, value, getImportParam(key));
    }






    private int getSubString(String str, String key) {
        int count = 0;
        int index = 0;
        while ((index = str.indexOf(key, index)) != -1) {
            index = index + key.length();
            count++;
        }
        return count;
    }

    private String addressAdd(String address) {
        if (StringUtils.isBlank(address)) {
            return "";
        }
        if (getSubString(address, "内蒙古") > 0) {
            return address.replace("内蒙古", "内蒙古自治区");
        }
        if (getSubString(address, "广西") > 0) {
            return address.replace("广西", "广西壮族自治区");
        }
        if (getSubString(address, "西藏") > 0) {
            return address.replace("西藏", "西藏自治区");
        }
        if (getSubString(address, "宁夏") > 0) {
            return address.replace("宁夏", "宁夏回族自治区");
        }
        if (getSubString(address, "新疆") > 0) {
            return address.replace("新疆", "新疆维吾尔自治区");
        }
        return address;
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
     * 验证Excel每一行. <br/>
     * 
     * @author mmzhao
     * @param r
     * @param errorBeans
     */
    protected void validateRow(Row row, List<ExcelErrorBean> errorBeans, List<ImportOrExportParam> importParams) {
        int rowNumber = row.getRowNum() + 1;
        for (ImportOrExportParam importParam : importParams) {
            if (importParam.isRequired()) {
                validateColumn(row, errorBeans, rowNumber, importParam.getColumnNumber(), importParam.getColumnName() + "不能为空;");
            }
        }
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

}
