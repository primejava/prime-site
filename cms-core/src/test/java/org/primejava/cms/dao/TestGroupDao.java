package org.primejava.cms.dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.inject.Inject;

import org.dbunit.DatabaseUnitException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primejava.basic.test.util.AbstractDbUnitTestCase;
import org.primejava.cms.model.Group;
import org.primejava.cms.model.User;
import org.primejava.cms.service.GroupService;
import org.primejava.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestGroupDao extends AbstractDbUnitTestCase{
	@Inject
	private SessionFactory sessionFactory;
	
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	
	@Before
	public void setUp() throws SQLException, IOException, DatabaseUnitException {
		//此时最好不要使用Spring的Transactional来管理，因为dbunit是通过jdbc来处理connection，再使用spring在一些编辑操作中会造成事务shisu
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
//		this.backupAllTable();
//		IDataSet ds = createDateSet("t_user");
//		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
	}
	
	@Test
	public void testfindGroup() {
		Group g=groupService.findGroup("402880e7545875fe0154587640270000");
		for(String u:g.getUsers()){
			System.out.println(u);
		}
	}
	

	@Test
	public void testUpdateGroup() {
		Group g=groupService.findGroup("402880e7545875fe0154587640270000");
		User u=userService.load("2");
		g.getUsers().clear();
		g.getUsers().add(u.getId());
		groupService.updateGroup(g);
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
