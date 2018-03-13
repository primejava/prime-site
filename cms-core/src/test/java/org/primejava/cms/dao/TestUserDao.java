package org.primejava.cms.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primejava.basic.model.Pager;
import org.primejava.basic.query.PropertyFilter;
import org.primejava.basic.test.util.AbstractDbUnitTestCase;
import org.primejava.basic.test.util.EntitiesHelper;
import org.primejava.basic.util.HibernateUtils;
import org.primejava.cms.model.User;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestUserDao extends AbstractDbUnitTestCase{
	@Inject
	private SessionFactory sessionFactory;
	@Inject
	private IUserDao userDao;

	//意义在于，把表清空以后去测试，会发现你对dao的测试，不会对数据库产生影响
	@Before
	public void setUp() throws SQLException, IOException, DatabaseUnitException {
		//此时最好不要使用Spring的Transactional来管理，因为dbunit是通过jdbc来处理connection，再使用spring在一些编辑操作中会造成事务shisu
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
		this.backupAllTable();
		IDataSet ds = createDateSet("t_user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
	}
	
	@Test
	public void testLoadUserName() throws DatabaseUnitException, SQLException {
		User au = EntitiesHelper.getBaseUser();
		String username = "admin123456";
		User eu = userDao.findUserByName(username);
		EntitiesHelper.assertUser(eu, au);
	}
	
	@Test
	public void testListUser() {
		Pager<User> pages=new Pager<User>();
		pages.setPageNo(3);
		pages.setPageSize(5);
		pages.setDefaultOrder("username", Pager.ASC);
		pages = userDao.findUser(pages,null);
		for(User u:pages.getResult()){
			System.out.println(u.getUsername());
		}
	}
	
	@Test
	public void testPageFilterUser() {
		Pager<User> pages=new Pager<User>();
		pages.setPageNo(1);
		pages.setPageSize(15);
		Map<String, Object> parameterMap =new HashMap<String, Object>();
		parameterMap.put("filter_LIKES_username", "11");
		parameterMap.put("filter_EQI_status", 0);
		List<PropertyFilter> propertyFilters = PropertyFilter
                .buildFromMap(parameterMap);
		pages = userDao.findUser(pages,propertyFilters);
		for(User u:pages.getResult()){
			System.out.println(u.getUsername());
		}
	}
	
	@Test
	public void testHibernateUtil(){
		Map<String, Object> parameterMap =new HashMap<String, Object>();
		parameterMap.put("filter_LIKES_username", "admin");
		parameterMap.put("filter_EQS_status", ((Integer)1).toString());
	    List<PropertyFilter> propertyFilters = PropertyFilter
                .buildFromMap(parameterMap);
	    Object[] result=HibernateUtils.buildFilterConditionAlias(propertyFilters);
	    System.out.println(result);
	}
	
	
	@Test
	public void testfindUserByName(){
		User user=userDao.findUserByName("lalala");
		System.out.println(user.getUsername());
	}
	
	
	
	@After
	public void tearDown() throws DatabaseUnitException, SQLException, IOException {
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession(); 
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
		this.resumeTable();
	}
}
