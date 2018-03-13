package org.primejava.cms.service;

import java.lang.reflect.InvocationTargetException;

import org.primejava.basic.model.Pager;
import org.primejava.cms.model.MessageReceive;
import org.primejava.cms.model.MessageSend;
import org.primejava.cms.model.User;

public interface MessageService {
	public void saveOrUpdateMessageSend(User currentUser,MessageSend messageSend)
			throws IllegalAccessException, InvocationTargetException;

	void saveOrUpdateMessageReceive(MessageReceive messageReceive)
			throws IllegalAccessException, InvocationTargetException;

	Pager<MessageReceive>  findMessageReceive(User currentUser, Pager<MessageReceive> page,
			MessageReceive messageReceive);

	Pager<MessageSend>  findMessageSend(User currentUser, Pager<MessageSend> page,
			MessageSend messageSend);

	MessageSend findMessageSend(String id);

	MessageReceive findMessageReceive(String id);

	void deleteMessageSend(String id);

	void deleteMessageReceive(String id);
}
