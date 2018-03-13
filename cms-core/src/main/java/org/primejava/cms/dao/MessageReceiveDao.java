package org.primejava.cms.dao;

import java.util.List;

import org.primejava.basic.dao.IBaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.MessageReceive;

public interface MessageReceiveDao extends IBaseDao<MessageReceive>{

	Pager<MessageReceive>  findMessageReceive(Pager<MessageReceive> page,
			MessageReceive messageReceive);

	List<MessageReceive> findMessageReceive(MessageReceive messageReceive);

	void deleteMessageReceiveByContentId(String id);

}
