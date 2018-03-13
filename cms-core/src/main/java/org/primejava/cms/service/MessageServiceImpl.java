package org.primejava.cms.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.basic.util.CommonDateUtils;
import org.primejava.cms.dao.ContentsDao;
import org.primejava.cms.dao.MessageReceiveDao;
import org.primejava.cms.dao.MessageSendDao;
import org.primejava.cms.dao.UserDao;
import org.primejava.cms.flow.enums.ReadStatusEnum;
import org.primejava.cms.model.Contents;
import org.primejava.cms.model.MessageReceive;
import org.primejava.cms.model.MessageSend;
import org.primejava.cms.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageSendDao messageSendDao;
	@Autowired
	private MessageReceiveDao messageReceiveDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ContentsDao contentsDao;
	@Autowired
	private BaseDao commonDao;
    @Override
    public Pager<MessageReceive>  findMessageReceive(User currentUser,Pager<MessageReceive> page, MessageReceive messageReceive) {
        messageReceive.setReceiveUser(currentUser.getId());
        return messageReceiveDao.findMessageReceive(page, messageReceive);
    }
	
    @Override
    public Pager<MessageSend>  findMessageSend(User currentUser,Pager<MessageSend> page, MessageSend messageSend) {
        messageSend.setSendUser(currentUser.getId());
        page= messageSendDao.findMessageSend(page, messageSend);
        for (Object msgSend : page.getResult()) {
            MessageReceive messageReceive = new MessageReceive();
            messageReceive.setSendId(((MessageSend) msgSend).getId());
            Integer readNumber = 0;
            Integer receiverNumber = 0;
            List<MessageReceive> mrs=messageReceiveDao.findMessageReceive(messageReceive);
            for (int i = 0; i < mrs.size(); i++) {
                if (null != mrs.get(i).getReadTime()) {
                    readNumber++;
                }
            }
            ((MessageSend) msgSend).setReadNumber(readNumber);
            for (int i = 0; i < mrs.size(); i++) {
                if (null != mrs.get(i).getSender()) {
                    receiverNumber++;
                }
            }
            ((MessageSend) msgSend).setReceiverNumber(receiverNumber);
        }
		return page;
    }
    
    
    
	@Override
	public void saveOrUpdateMessageSend(User currentUser,
			MessageSend messageSend) throws IllegalAccessException,
			InvocationTargetException {
		if (StringUtils.isBlank(messageSend.getContent().getId())) {
			messageSend.setSendUser(currentUser.getId());
			messageSend.setSendTime(CommonDateUtils.now());
			messageSend = messageSendDao.add(messageSend);
			contentsDao.saveContents(messageSend.getId(), messageSend.getContent());
			if (StringUtils.isNotBlank(messageSend.getReceiver())) {
				String[] receiverId = messageSend.getReceiver().split(",");
				for (int i = 0; i < receiverId.length; i++) {
					MessageReceive messageReceive = new MessageReceive();
					messageReceive.setTitle(messageSend.getTitle());
					messageReceive.setSender(currentUser.getUsername());
					messageReceive.setContents(messageSend.getContent());
					messageReceive.setSendId(messageSend.getId());
					messageReceive.setReceiveUser(receiverId[i]);
					messageReceive.setCategory(messageSend.getCategory());
					this.saveOrUpdateMessageReceive(messageReceive);
				}
				return;
			}
			//下面是给所有人发送
	        	List users = new ArrayList<>();
                users = userDao.findAll();
            for (int i = 0; i < users.size(); i++) {
                MessageReceive messageReceive = new MessageReceive();
                messageReceive.setTitle(messageSend.getTitle());
            
                messageReceive.setContents(messageSend.getContent());
                messageReceive.setSendId(messageSend.getId());
                messageReceive.setReceiveUser(((User) users.get(i)).getId());
                messageReceive.setCategory(messageSend.getCategory());
                this.saveOrUpdateMessageReceive(messageReceive);
            }
            return;
		}
		//下面是更新
	    MessageSend oldMessageSend = messageSendDao.load(messageSend.getContent().getId());
        BeanUtils.copyProperties(oldMessageSend, messageSend);
        messageSendDao.update(oldMessageSend);
	}

	@Override
	public void saveOrUpdateMessageReceive(MessageReceive messageReceive)
			throws IllegalAccessException, InvocationTargetException {
		if (StringUtils.isBlank(messageReceive.getId())) {
			messageReceive.setSendTime(CommonDateUtils.now());
			messageReceive.setStatus(ReadStatusEnum.UNREAD.getValue());
			messageReceiveDao.add(messageReceive);
			return;
		}
		MessageReceive oldMessageReceive = messageReceiveDao
				.load(messageReceive.getId());
		messageReceive.setCategory(oldMessageReceive.getCategory());
		BeanUtils.copyProperties(oldMessageReceive, messageReceive);
		if (null == oldMessageReceive.getReadTime()) {
			oldMessageReceive.setReadTime(CommonDateUtils.now());
			oldMessageReceive.setStatus(ReadStatusEnum.READ.getValue());
		}
		messageReceiveDao.update(oldMessageReceive);

	}
	// 不能把session对象传到service里面，没法测试了
	// @Override
	// public void saveOrUpdateMessageSend(MessageSend messageSend)
	// throws IllegalAccessException, InvocationTargetException {
	// HttpServletRequest request =
	// ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	// User user = (User) request.getSession().getAttribute("loginUser");
	//
	// }
	

    @Override
    public MessageSend findMessageSend(String id) {
    	MessageSend m=messageSendDao.load(id);
        Contents contents = (Contents) commonDao.findById(id, Contents.class);
        m.setContent(contents);
        return m;
    }

    @Override
    public MessageReceive findMessageReceive(String id) {
    	MessageReceive m=messageReceiveDao.load(id);
        Contents contents = (Contents) commonDao.findById(m.getSendId(), Contents.class);
        System.out.println(contents.getContent());
        m.setContents(contents);
 		return  m;
    }

    @Override
    public void deleteMessageSend(String id) {
        messageSendDao.deleteById(id);
        contentsDao.deleteById(id);
        messageReceiveDao.deleteMessageReceiveByContentId(id);
    }

    @Override
    public void deleteMessageReceive(String id) {
        messageReceiveDao.deleteById(id);
    }


}
