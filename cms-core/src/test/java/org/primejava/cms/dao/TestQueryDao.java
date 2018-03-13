package org.primejava.cms.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.dbunit.DatabaseUnitException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primejava.basic.test.util.AbstractDbUnitTestCase;
import org.primejava.cms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestQueryDao extends AbstractDbUnitTestCase{
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private QueryDao queryDao;
	@Before
	public void setUp() throws SQLException, IOException, DatabaseUnitException {
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
	}
	
	@Test
	public void testgetColumnsByClass(){
		List<String> results=queryDao.getColumnsByClass(User.class);
		for(String s:results){
			System.out.println(s);
		}
	}
	@Test
	public void testgetColumnsAndTypeByClass(){
		Map<String, String> results=queryDao.getColumnsNameAndTypeByClass(User.class);
		for(String s:results.keySet()){
			System.out.println(s+":"+results.get(s));
		}
	}

	@After
	public void tearDown() throws DatabaseUnitException, SQLException, IOException {
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession(); 
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
	}
}
