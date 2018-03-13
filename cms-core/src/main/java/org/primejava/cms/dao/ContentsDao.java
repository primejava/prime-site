package org.primejava.cms.dao;

import org.primejava.cms.model.Contents;

public interface ContentsDao {
    /**
     * 保存内容. <br/>
     * @author yx
     * @param id
     * @param contents
     */
    void saveContents(String id, Contents contents);

	void deleteById(String id);
    
}
