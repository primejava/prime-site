package org.primejava.cms.dao;

import org.primejava.basic.dao.IBaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.DictionaryCommons;

public interface DictionaryCommonsDao extends IBaseDao<DictionaryCommons> {
	String findMaxIdByCode(DictionaryCommons dictionaryCommons);

	/**
	 * 分页查询字典表数据. <br/>
	 * 
	 * @author yx
	 * @param group
	 * @param page
	 */
	void findPageDictionaryCommons(Pager<DictionaryCommons> page,
			DictionaryCommons dictionaryCommons);

}
