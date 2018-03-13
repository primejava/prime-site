package org.primejava.cms.notice;

import org.springframework.stereotype.Component;

@Component("emailSender")
public class EmailSender implements MsgSender{
	@Override
	public void send() {
		System.out.println("发送邮件");
	}

}
