package org.primejava.cms.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.primejava.basic.dao.BaseDao;
import org.primejava.cms.dao.UserDao;
import org.primejava.cms.imp.dao.ImportOrExportDao;
import org.primejava.cms.imp.pojo.ExcelErrorBean;
import org.primejava.cms.imp.pojo.ImportMapUtils;
import org.primejava.cms.imp.pojo.ImportRecord;
import org.primejava.cms.imp.pojo.ImportRecordBean;
import org.primejava.cms.model.ImportOrExportParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component("ImportUser")
public class ImportUser extends AbstractImport implements Imports<ImportRecord> {

	protected static Logger                   LOGGER = LoggerFactory.getLogger(ImportUser.class);

    @Autowired
    private ImportOrExportDao                 importOrExportDao;
    @Autowired
    private UserDao                        userDao;

    @Autowired
    private BaseDao                         commonDao;
	@Override
	public ImportRecord importByType(ImportRecord result, HSSFSheet sheet,
			List<ImportOrExportParam> importParams) {
		 Iterator<Row> rowIterator = sheet.iterator();
	        int rowIndex = 1;
	        if (rowIterator.hasNext()) {
	            rowIterator.next(); // 第一行不是表头
	        }
	        long startTime = System.currentTimeMillis();
	        List<ExcelErrorBean> errorList = new ArrayList<ExcelErrorBean>();
	        List<String> insertList = new ArrayList<>();
	        List<String> updateList = new ArrayList<>();
	        //这一步的意义何在？
	        initializationMap(importParams, result.getGraduationYear());// 初始化Map集合
	        while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();
	            validateRow(row, errorList, importParams);
	            if (null == errorList || errorList.isEmpty()) {
	                buildSql(row, rowIndex, importParams, insertList, updateList);
	            }
	            rowIndex++;
	        }
	        for(String s:insertList){
	        	System.out.println(s);
	        }
	        for(String s:updateList){
	        	System.out.println(s);
	        }
	        ImportRecordBean insertRecord = new ImportRecordBean();
	        ImportRecordBean updateRecord = new ImportRecordBean();
	        if (!insertList.isEmpty()) {
	            LOGGER.info("执行批量插入");
	            insertRecord = userDao.saveOrUpdateUser(insertList);
	        }
	        if (!updateList.isEmpty()) {
	            LOGGER.info("执行批量修改");
	            updateRecord = userDao.saveOrUpdateUser(updateList);
	        }
	        insertOrUpdateRecord(result, rowIndex, errorList, insertRecord, updateRecord, 1);
	        LOGGER.info(String.format("总记录数为：%s, 验证格式正确的数量：%s ", result.getTotalRecord(), result.getSuccessCount()));
	        long endTime = System.currentTimeMillis();
	        LOGGER.info("执行时间为： " + (endTime - startTime));
	        return result;
	}
	private String generatedUuid(){
		UUID uuid  =  UUID.randomUUID();
	       String id=uuid.toString().replaceAll("\\-", "");
	       return "'"+id+"'";
	}
    /**
     * 生成Sql.有的是插入，有的是更新 <br/>
     * 
     * @author mmzhao
     * @param row
     * @param rowIndex
     * @param importParams
     * @return
     */
    private void buildSql(Row row, int rowIndex, List<ImportOrExportParam> importParams, List<String> insertList,
            List<String> updateList) {
        StringBuilder insertProperty = new StringBuilder("id,password");
        StringBuilder insertValue = new StringBuilder(generatedUuid()).append(",'000000'");
        StringBuilder updateParam = new StringBuilder(" SET ");
        Map<String, StringBuilder> sqlMap = new HashMap<String, StringBuilder>();
        Map<String, List<Object[]>> map = new HashMap<String, List<Object[]>>();
        Map<String, String> endSqlMap = new HashMap<String, String>();
        List<String> tables = new ArrayList<>();

        for (ImportOrExportParam importParam : importParams) {
            if (!StringUtils.isEmpty(importParam.getFieldProperty())) {
                buildInsertSql(sqlMap, importParam, insertProperty, insertValue, row);
                buildUpdateSql(sqlMap, importParam, updateParam, row);
            }
            if (!StringUtils.isEmpty(importParam.getTable()) && !tables.contains(importParam.getTable())) {
                tables.add(importParam.getTable());
            }
            if (importParam.isRelevance()) {
                String endSql = "";
                List<Object[]> list = new ArrayList<>();
                if (!StringUtils.isEmpty(importParam.getDataSource())) {
                    list = importOrExportDao.findList(importParam.getDataSource() + "'" + translates(row, importParam)
                                                      + "'"); 
                }else{
                    list = importOrExportDao.findList("SELECT id FROM " + importParam.getTable() + " WHERE "
                            + importParam.getFieldProperty() + " ='"
                            + translates(row, importParam) + "'");
                }
				String condition = checkAndGetString(row, importParam.getColumnNumber());
				endSql = " WHERE (" + importParam.getFieldProperty() + "='" + condition + "')";
				map.put(importParam.getTable(), list);
                endSqlMap.put(importParam.getTable(), endSql);
            }
        }
        addSql(tables, map, sqlMap, insertList, updateList, updateParam, endSqlMap);
    }

    /**
     * 功能: 拼接SQL.<br/>
     * date: 2015年6月24日 下午4:42:24 <br/>
     * 
     * @author mmzhao@wisdombud.com
     * @param tables
     * @param map
     * @param sqlMap
     * @param insertList
     * @param updateList
     * @param updateParam
     */
    private void addSql(List<String> tables, Map<String, List<Object[]>> map, Map<String, StringBuilder> sqlMap,
            List<String> insertList, List<String> updateList, StringBuilder updateParam, Map<String, String> endSqlMap) {
        StringBuilder insertProperty = new StringBuilder();
        StringBuilder insertValue = new StringBuilder();
        for (String table : tables) {
            StringBuilder insert = new StringBuilder("INSERT INTO ");
            if (null == map.get(table) || map.get(table).isEmpty()) {
                if (null == sqlMap.get(table + "Property") || null == sqlMap.get(table + "Value")) {
                    continue;
                }
                insert.append(table);
                insertList.add(insert.toString() + "(" + sqlMap.get(table + "Property").toString() + ") VALUES ("
                               + sqlMap.get(table + "Value").toString() + ")");
                continue;
            }
            StringBuilder update = new StringBuilder("UPDATE ");
            update.append(table);
            updateParam = sqlMap.get(table);
            updateList.add(update.toString()
                           + updateParam.toString().substring(0, updateParam.toString().lastIndexOf(","))
                           + endSqlMap.get(table));
        }
    }
    private void buildInsertSql(Map<String, StringBuilder> sqlMap, ImportOrExportParam importParam,
            StringBuilder insertProperty, StringBuilder insertValue, Row row) {

        if (null != sqlMap.get(importParam.getTable() + "Property")) {
            insertProperty = sqlMap.get(importParam.getTable() + "Property");
        } else {
            insertProperty = new StringBuilder("id,password");
        }
        insertProperty.append("," + importParam.getFieldProperty());
        sqlMap.put(importParam.getTable() + "Property", insertProperty);
        //这条语句执行完，会获取到这张表的所有的属性组成的一条插入语句。，键是表名。
        if (null != sqlMap.get(importParam.getTable() + "Value")) {
            insertValue = sqlMap.get(importParam.getTable() + "Value");
        } else {
        	//这条语句给id，和ver和状态赋值
            insertValue =new StringBuilder(generatedUuid()).append(",'000000'");
        }
        if (!StringUtils.isEmpty(importParam.getDataType())) {
            switch (importParam.getDataType()) {
                case "Date":
                    insertValue.append(",TO_DATE('" + translates(row, importParam) + "','SYYYY-MM-DD HH24:MI:SS')");
                    break;

                default:
                    insertValue.append(",'" + translates(row, importParam) + "'");
                    break;
            }
        }
        //此处insertValue是连接以后的insertValue。
        sqlMap.put(importParam.getTable() + "Value", insertValue);
    }
    
    private void buildUpdateSql(Map<String, StringBuilder> sqlMap, ImportOrExportParam importParam,
            StringBuilder updateParam, Row row) {
        if (null != sqlMap.get(importParam.getTable())) {
            updateParam = sqlMap.get(importParam.getTable());
        } else {
            updateParam = new StringBuilder(" SET ");
        }
        if (!StringUtils.isEmpty(importParam.getDataType())) {
            switch (importParam.getDataType()) {
                case "Date":
                    updateParam.append(importParam.getFieldProperty() + "=TO_DATE('" + translates(row, importParam)
                                       + "','SYYYY-MM-DD HH24:MI:SS'),");
                    break;
                default:
                    updateParam.append(importParam.getFieldProperty() + "='" + translates(row, importParam) + "',");
                    break;
            }
        }
        sqlMap.put(importParam.getTable(), updateParam);
    }
    
    /**
     * 初始化map集合. <br/>
     * 
     * @author mmzhao
     */
    protected void initializationMap(List<ImportOrExportParam> importParams, String graduationYear) {
        ImportMapUtils.IMPORT_PARAM_MAP = findImportParamMap(importParams);
        for (ImportOrExportParam importParam : importParams) {
            if (JSON_TYPE_CONTENT == importParam.getMethod()) {
                String[] results = importParam.getDataSource().split(",");
                Object obj[] = new Object[results.length];
                List<Object[]> list = new ArrayList<>();
                if (results.length <= 1) {
                    obj = results[0].split(":");
                    list.add(obj);
                }
                if (results.length > 1) {
                    for (int i = 0; i < results.length; i++) {
                        obj = results[i].split(":");
                        list.add(obj);
                    }
                }
                ImportMapUtils.MAP.put(importParam.getFieldProperty(), list);
            }
            if (SQL_TYPE_CONTENT == importParam.getMethod()) {
                ImportMapUtils.MAP.put(importParam.getFieldProperty(),
                                       importOrExportDao.findList(importParam.getDataSource()));
            }
        }
    }


}
