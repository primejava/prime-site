package org.primejava.cms.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("msgManager")
public class MsgManagerImpl implements MsgManager{
	@Autowired   
	@Qualifier("emailSender")
	private MsgSender emailSender;
	@Autowired   
	@Qualifier("smsSender")
	private MsgSender smsSender;
    @Value("${msgSender}")  
	private String whitch;
	
	public void sendMsg(){		
		if(whitch.equals("mail")){
			emailSender.send();
		}else{
			smsSender.send();
		}
	}
}
