package org.primejava.cms.dao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import org.primejava.cms.model.Contents;
import org.primejava.cms.model.MessageSend;
import org.primejava.cms.model.User;
import org.primejava.cms.service.MessageService;
import org.primejava.cms.service.UserService;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestMessageDao extends AbstractDbUnitTestCase{
	@Inject
	private SessionFactory sessionFactory;
	@Inject
	private MessageService messageService;

	@Inject
	private UserService userService;
	@Before
	public void setUp() throws SQLException, IOException, DatabaseUnitException {
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
	}
	

	
	@Test
	public void testPages() {
		User currentUser=userService.findUserByName("baby");
		MessageSend messageSend=new MessageSend();
		messageSend.setTitle("消息测试标题");
		Contents contents=new Contents();
		contents.setContent("消息测试啦");
		messageSend.setContent(contents);
		messageSend.setReceiver("3f7343251cc74d2c98c383751744c5c3,402880e75471a29f015471a46d7f0000,402880e75471a29f015471a49b360001");
		try {
			messageService.saveOrUpdateMessageSend(currentUser, messageSend);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
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
