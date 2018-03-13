package org.primejava.cms.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.dbunit.DatabaseUnitException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primejava.basic.test.util.AbstractDbUnitTestCase;
import org.primejava.cms.notice.service.NoticeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestNotice extends AbstractDbUnitTestCase {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private NoticeMessage noticeMessage;

	@Before
	public void setUp() throws SQLException, IOException, DatabaseUnitException {
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory,
				new SessionHolder(s));
	}

	@Test
	public void sendEmail() {
		noticeMessage.setNoticeId("1");
		noticeMessage.execute();
		//多线程下的测试
		try {
			Thread.currentThread().sleep(TimeUnit.MINUTES.toMillis(3));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws DatabaseUnitException, SQLException,
			IOException {
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager
				.getResource(sessionFactory);
		Session s = holder.getSession();
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
	}
}
