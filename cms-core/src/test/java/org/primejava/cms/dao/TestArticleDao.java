package org.primejava.cms.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.dbunit.DatabaseUnitException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primejava.basic.model.Pager;
import org.primejava.basic.test.util.AbstractDbUnitTestCase;
import org.primejava.cms.model.Article;
import org.primejava.cms.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestArticleDao extends AbstractDbUnitTestCase{
	@Inject
	private SessionFactory sessionFactory;
	
	@Autowired
	private ArticleService articleService;
	
	@Before
	public void setUp() throws SQLException, IOException, DatabaseUnitException {
		//此时最好不要使用Spring的Transactional来管理，因为dbunit是通过jdbc来处理connection，再使用spring在一些编辑操作中会造成事务shisu
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
	}
	
	@Test
	public void testfindArticle() {
		Pager<Article> pages=new Pager<Article>();
		pages=articleService.findArticleByKey(pages, "技术");
		for(Article a:pages.getResult()){
			System.out.println(a.getTitle());
		}
		
	}
	
	@Test
	public void testfindTag() {
		List<String> tags=new ArrayList<String>();
		tags.add("不错");
		tags.add("技术");
		List<String> users=articleService.findUsersByTag(tags);
		for(String s:users){
			System.out.println(s);
		}
	}
	
	@After
	public void tearDown() throws DatabaseUnitException, SQLException, IOException {
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession(); 
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
		//this.resumeTable();
	}
}
