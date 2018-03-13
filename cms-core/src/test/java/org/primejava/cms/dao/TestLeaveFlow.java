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
import org.primejava.basic.model.Pager;
import org.primejava.basic.test.util.AbstractDbUnitTestCase;
import org.primejava.cms.flow.LeaveFlowService;
import org.primejava.cms.model.Leave;
import org.primejava.cms.model.User;
import org.primejava.cms.service.LeaveService;
import org.primejava.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestLeaveFlow extends AbstractDbUnitTestCase{
	@Inject
	private SessionFactory sessionFactory;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private LeaveFlowService leaveFlowService;
	@Autowired
	private UserService userService;
	

	@Before
	public void setUp() throws SQLException, IOException, DatabaseUnitException {
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
	}
	
    @Test
    public void TestApplyLeave() {
    	User user=userService.load("1");
    	Leave leave=new Leave("有事情");
    	leave.setUser(user);
    	//applyUser怎么付给他的。。。
    	leaveService.saveOrUpdateLeave(leave);
    }

    @Test
    public void TestFindApplyLeave(){
    	User user=userService.load("1");
    	Pager<Leave> page=new Pager<Leave>();
    	leaveFlowService.findApplyFlow(page, user.getId());
    	for(Leave l:page.getResult()){
    		System.out.println(l.getCause()+":"+l.getTask().getName());
    	}
    }
    
    @Test
    public void TestFindAuditLeave(){
    	User user=userService.load("1");
    	Pager<Leave> page=new Pager<Leave>();
    	leaveFlowService.findApplyFlow(page, user.getId());
    	for(Leave l:page.getResult()){
    		System.out.println(l.getCause()+":"+l.getTask().getName());
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
