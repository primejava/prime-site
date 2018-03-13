package org.primejava.cms.imp.service;

import java.util.List;

import org.primejava.cms.imp.pojo.ImportRecord;
import org.primejava.cms.model.Attachment;
import org.primejava.cms.model.ImportOrExportParam;

/**
 * ClassName: ImportService. <br/>
 * Function: TODO <br/>
 * date: 2015年5月4日 下午4:56:47 <br/>
 * 
 * @author mmzhao
 * @version
 * @since JDK 1.6
 */
public interface ImportExportService {

    /**
     * 导入数据. <br/>
     * 
     * @author mmzhao
     * @param excelUrl
     * @param attachmentId
     * @param category
     * @param graduationYear
     * @return
     */
    ImportRecord importByType(Attachment attachment, String type, String category);

    /**
     * 根据类型查询参数集合. <br/>
     * 
     * @author mmzhao
     * @param type
     * @return
     */
    List<ImportOrExportParam> findImportParams(String type, String category);


    Attachment findAttachment(String excelName);
}
