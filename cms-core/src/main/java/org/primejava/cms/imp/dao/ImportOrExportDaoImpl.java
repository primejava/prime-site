package org.primejava.cms.imp.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primejava.basic.dao.BaseDao;
import org.primejava.cms.model.Attachment;
import org.primejava.cms.model.ImportOrExportParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
@Repository("importOrExportDao")
public class ImportOrExportDaoImpl extends BaseDao<ImportOrExportParam> implements ImportOrExportDao {
    protected static Logger LOGGER = LoggerFactory.getLogger(ImportOrExportDaoImpl.class);
	@Override
	public List<Object[]> findList(String sql) {
		 try {
	            return createSQLQuery(sql).list();
	        } catch (Exception e) {
	            LOGGER.info("执行sql异常", e);
	            LOGGER.info("执行sql " + sql, e);
	        }
	        return null;
	}

	@Override
	public List<ImportOrExportParam> findImportParams(String type,
			String category, List<String> ids) {
		 final StringBuilder hql = new StringBuilder(" From ImportOrExportParam Where 1=1 ");
	        Map<String, Object> params = Maps.newHashMap();
	        if (StringUtils.isNotBlank(type)) {
	            hql.append(" and type = :type");
	            params.put("type", type);
	        }
	        if (StringUtils.isNotBlank(category)) {
	            hql.append(" and category = :category");
	            params.put("category", category);
	        }
	        if (null != ids && !ids.isEmpty()) {
	            hql.append(" and id in ( :ids)");
	            params.put("ids", ids);
	        }
	        hql.append(" order by columnNumber ASC");
	        return createHqlQuery(hql.toString(), params).list();
	}

	@Override
	public List<Attachment> findAttachments(String url) {
	       final StringBuilder hql = new StringBuilder(" From Attachment Where 1=1 ");
	        Map<String, Object> params = Maps.newHashMap();
	        if (StringUtils.isNotBlank(url)) {
	            hql.append(" and url like :url");
	            params.put("url", assemblyPercent(url));
	        }
	        return createHqlQuery(hql.toString(), params).list();
	}
}
