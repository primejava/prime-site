package org.primejava.cms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.MessageReceive;
import org.springframework.stereotype.Repository;
@Repository("messageReceiveDao")
public class MessageReceiveDaoImpl extends BaseDao<MessageReceive> implements MessageReceiveDao {
	@Override
	public Pager<MessageReceive>  findMessageReceive(Pager<MessageReceive> page,
			MessageReceive messageReceive) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        hql.append(" from MessageReceive where 1=1 ");
        hql.append(" and receiveUser = :receiveUser");
        params.put("receiveUser", messageReceive.getReceiveUser());
        if (null != messageReceive.getStatus()) {
            hql.append(" and status = :status");
            params.put("status", messageReceive.getStatus());
        }
        hql.append(" order by sendTime desc ");
        return  this.findByAlias(page, hql.toString(), params);
	}

    @Override
    public List<MessageReceive> findMessageReceive(MessageReceive messageReceive) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        hql.append(" from MessageReceive where 1=1");
        if (null != messageReceive.getSendId()) {
            hql.append(" and sendId = :sendId");
            params.put("sendId", messageReceive.getSendId());
        }
        if (null != messageReceive.getStatus()) {
            hql.append(" and status = :status");
            params.put("status", messageReceive.getStatus());
        }
        return createHqlQuery(hql.toString(), params).list();
    }

	@Override
	public void deleteMessageReceiveByContentId(String id) {
		 this.batchExecute("delete from MessageReceive where sendId=? ", id);		
	}
}
