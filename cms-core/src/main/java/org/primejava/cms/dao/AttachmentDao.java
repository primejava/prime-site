package org.primejava.cms.dao;

import org.primejava.basic.dao.IBaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Attachment;

public interface AttachmentDao extends IBaseDao<Attachment>{

	Pager<Attachment> findAttachments(Pager<Attachment> page);

	long findLastModify();

	Attachment findAttachmentByName(String filePath);
}
