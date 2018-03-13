package org.primejava.cms.dao;

import java.util.HashMap;
import java.util.Map;

import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Article;
import org.springframework.stereotype.Repository;

@Repository("articleDao")
public class ArticleDaoImpl extends BaseDao<Article> implements ArticleDao {

	@Override
	public Pager<Article> findArticles(Pager<Article> page, String id) {
		String hql = "from Article where user.id=?";
		if(id==null){
			hql = "from Article";
			return this.find(page, hql);
		}
		return this.find(page, hql, id);
	}

	@Override
	public Pager<Article> findArticleByKey(Pager<Article> page, String key) {
		String hql = "from Article where keyword  LIKE :param";
		Map<String, Object> alias=new HashMap<String, Object>();
		alias.put("param", "%"+key+"%");
		return this.findByAlias(page, hql, alias);
	}

}
