package org.primejava.cms.dao;

import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Attachment;
import org.springframework.stereotype.Repository;
@Repository("attachmentDao")
public class AttachmentDaoImpl extends BaseDao<Attachment> implements AttachmentDao {

	@Override
	public Pager<Attachment> findAttachments(Pager<Attachment> page) {
		String hql="from Attachment";
		return this.find(page,hql);
	}

	@Override
	public long findLastModify() {
		String hql="select max(lastModify) from Attachment ";
		return (long) this.queryObject(hql);
	}

	@Override
	public Attachment findAttachmentByName(String filePath) {
		String hql=" from Attachment where new_name=?";
		return (Attachment) this.queryObject(hql, filePath);
	}

}
