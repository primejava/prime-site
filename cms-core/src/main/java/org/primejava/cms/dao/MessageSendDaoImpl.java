package org.primejava.cms.dao;

import java.util.HashMap;
import java.util.Map;

import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.MessageSend;
import org.springframework.stereotype.Repository;

@Repository("messageSendDao")
public class MessageSendDaoImpl extends BaseDao<MessageSend> implements
		MessageSendDao {

	@Override
	public Pager<MessageSend> findMessageSend(Pager<MessageSend> page,
			MessageSend messageSend) {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" from MessageSend where 1=1 ");

		hql.append(" and sendUser = :sendUser");
		params.put("sendUser", messageSend.getSendUser());

		hql.append(" order by sendTime desc ");
		return this.findByAlias(page, hql.toString(), params);
	}

}
