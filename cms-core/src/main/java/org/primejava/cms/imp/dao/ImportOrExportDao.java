package org.primejava.cms.imp.dao;

import java.util.List;

import org.primejava.basic.dao.IBaseDao;
import org.primejava.cms.model.Attachment;
import org.primejava.cms.model.ImportOrExportParam;

public interface ImportOrExportDao extends IBaseDao<ImportOrExportParam>{
	 /**
     * 执行单条sql得到数组集合. <br/>
     * 
     * @author mmzhao
     * @param sql
     * @return
     */
    List<Object[]> findList(String sql);

    /**
     * 根据类型查询导入或导出参数集合. <br/>
     * 
     * @author mmzhao
     * @param type
     * @return
     */
    List<ImportOrExportParam> findImportParams(String type, String category, List<String> ids);

    /**
     * 根据条件模糊查询附件. <br/>
     * 
     * @author mmzhao
     * @param string
     * @return
     */
    List<Attachment> findAttachments(String url);
}
