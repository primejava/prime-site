package org.primejava.cms.imp;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.primejava.cms.imp.pojo.ImportRecord;
import org.primejava.cms.model.ImportOrExportParam;

public interface Imports<T> {

    /**
     * 根据类型导入数据. <br/>
     * @author mmzhao
     * @param excelUrl
     * @param attachmentId
     * @param category
     * @return
     */
    T importByType(ImportRecord result, HSSFSheet sheet, List<ImportOrExportParam> importParams);

}
