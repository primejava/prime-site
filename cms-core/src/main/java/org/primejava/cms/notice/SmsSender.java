package org.primejava.cms.notice;

import org.springframework.stereotype.Component;

@Component("smsSender")
public class SmsSender implements MsgSender{

	@Override
	public void send() {
		System.out.println("发送短信");		
	}

}
