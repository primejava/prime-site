package org.primejava.cms.dao;

import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Leave;
import org.primejava.cms.model.User;
import org.springframework.stereotype.Repository;
@Repository("LeaveDao")
public class LeaveDaoImpl extends BaseDao<Leave> implements LeaveDao{

	@Override
	public Pager<Leave> findLeaves(Pager<Leave> pager, User user) {
		String hql="from Leave where user.id=?";
		return this.find(pager,hql,user.getId());
	}

}
