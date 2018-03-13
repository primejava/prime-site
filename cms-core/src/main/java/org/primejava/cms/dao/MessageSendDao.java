package org.primejava.cms.dao;

import org.primejava.basic.dao.IBaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.MessageSend;

public interface MessageSendDao extends IBaseDao<MessageSend>{

	Pager<MessageSend> findMessageSend(Pager<MessageSend> page,
			MessageSend messageSend);

}
