package org.primejava.cms.dao;

import org.primejava.basic.dao.IBaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Article;

public interface ArticleDao extends IBaseDao<Article>{

	Pager<Article> findArticles(Pager<Article> page, String id);

	Pager<Article> findArticleByKey(Pager<Article> page, String key);

}
