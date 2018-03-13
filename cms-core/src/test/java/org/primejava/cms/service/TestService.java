package org.primejava.cms.service;

import java.util.ArrayList;
import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primejava.basic.model.Pager;
import org.primejava.cms.dao.IUserDao;
import org.primejava.cms.model.CommonRole;
import org.primejava.cms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-beans.xml")
public class TestService {
	@Autowired
	private RoleService roleService;

	@Test
	public void Test() {
		Pager<CommonRole> page=new Pager<CommonRole>();
		CommonRole role=new CommonRole();
		roleService.findRoles(page, role);
	}

}
