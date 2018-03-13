package org.primejava.cms.service;

import java.util.ArrayList;
import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primejava.basic.model.Pager;
import org.primejava.cms.dao.IUserDao;
import org.primejava.cms.model.Enterprise;
import org.primejava.cms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-beans.xml")
public class TestRegisterService {
	@Autowired
	private EnterpriseService enterpriseService;
	@Test
	public void TestMock(){
		Pager<Enterprise> page=new Pager<Enterprise>();
		enterpriseService.findAuditRegister(page, "297e6c35562265130156226ae4c60001");
		for(Enterprise e:page.getResult()){
			System.out.println(e.getName());
		}
	}
	
}
