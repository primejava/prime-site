package org.primejava.cms.dao;

import org.primejava.basic.dao.BaseDao;
import org.primejava.cms.model.Contents;
import org.springframework.stereotype.Repository;

@Repository("contentsDao")
public class ContentsDaoImpl extends BaseDao<Contents> implements ContentsDao {
	
	@Override
	public void saveContents(String id, Contents contents) {
		if (null == contents) {
			return;
		}
		//不能用load
		Contents oldContents = findById(id, Contents.class);
		if (null != oldContents) {
			deleteById(id);
		}
		contents.setId(id);
		add(contents);
	}
	

}
