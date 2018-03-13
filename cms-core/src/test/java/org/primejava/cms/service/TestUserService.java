package org.primejava.cms.service;

import java.util.ArrayList;
import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primejava.cms.dao.IUserDao;
import org.primejava.cms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-beans.xml")
public class TestUserService {
	//假设dao层没有写好，如何测试service
	@Autowired
	private UserService userService;
	@Autowired
	private IUserDao userDao;
	@Test
	public void TestMock(){
		//这个UserDao，假设什么都没有不写，只是写一个接口及其接口方法
		IUserDao userDao=EasyMock.createMock(IUserDao.class);
        //设定这个接口方法所返回的数据
        User u=new User();
        u.setNickname("煞笔");
        List<User> users=Lists.newArrayList();
        users.add(u);
        EasyMock.expect(userDao.findAll()).andReturn(users);
        EasyMock.replay(userDao);//录制
        List<User> results= userDao.findAll();
        for(User item:results){
        	System.out.println(item.getNickname());
        }
	}
	
	@Test
	public void TestFindAll(){
		//要理解当我们测试service的时候，我们在测试什么，我们可以把service理解为包裹dao的外衣，现在就是要把外衣剥掉，直接的观察dao的运行。
		IUserDao userDao=EasyMock.createMock(IUserDao.class);
		List<User> users=new ArrayList<User>();
        EasyMock.expect(userDao.findAll()).andReturn(users).times(1);
        EasyMock.replay(userDao);
        userDao.findAll();
//		userService.findAll();
		EasyMock.verify(userDao);
	}
	
	@Test
	public void TestFindAll2(){
        EasyMock.expect(userDao.findAll()).andReturn(new ArrayList<User>()).times(1);
        EasyMock.replay(userDao);
		userService.findAll();
		EasyMock.verify(userDao);
	}
	
}
