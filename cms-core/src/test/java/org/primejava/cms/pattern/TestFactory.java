package org.primejava.cms.pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.primejava.basic.test.util.AbstractDbUnitTestCase;
import org.primejava.cms.notice.MsgManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestFactory extends AbstractDbUnitTestCase {
	@Autowired
	private MsgManager msgManager;

	@Test
	public void sendEmail() {
		msgManager.sendMsg();
	}

}
