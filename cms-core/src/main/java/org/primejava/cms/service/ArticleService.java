package org.primejava.cms.service;

import java.util.List;

import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Article;

public interface ArticleService {

	Pager<Article> findArticles(Pager<Article> page, String id);
	
	Pager<Article> findArticleByKey(Pager<Article> page, String key);
	
	Article findArticleById(String id);

	void saveOrUpdate(Article article);
	
	List<String> readAllTag();

	void writeTags(List<String> tags);
	//根据tag查询出该文章被哪些人订阅
	List<String> findUsersByTag(List<String> tagLists);


}
