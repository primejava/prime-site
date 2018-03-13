package org.primejava.cms.service;

import java.io.IOException;

import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Attachment;

public interface AttachmentService {
	public void add(Attachment attachment);

	public Pager<Attachment> findAttachments(Pager<Attachment> page);

	public Attachment findAttachmentById(String id);
	public Attachment findAttachmentByName(String filePath);
	public long findLastModify();
	
	public void addWordIndex(String path)  throws IOException;
}
