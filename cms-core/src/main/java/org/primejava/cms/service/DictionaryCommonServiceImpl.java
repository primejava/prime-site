package org.primejava.cms.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.dao.DictionaryCommonsDao;
import org.primejava.cms.model.DictionaryCommons;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dictionaryCommonService")
public class DictionaryCommonServiceImpl implements DictionaryCommonService {
	@Autowired
	private BaseDao commonDao;
	@Autowired
	private DictionaryCommonsDao dictionaryCommonsDao;

	@Override
	public void deleteDictionaryCommons(final String id) {
		this.dictionaryCommonsDao.deleteById(id);
	}

	@Override
	public Map<String, DictionaryCommons> findCommonsMap(final String code) {
		final List<DictionaryCommons> list = this.commonDao.findByClass(
				DictionaryCommons.class, "code", code);
		final Map<String, DictionaryCommons> commonsMap = new HashMap<>();
		for (final DictionaryCommons commonDic : list) {
			commonsMap.put(commonDic.getValue(), commonDic);
		}
		return commonsMap;
	}

	@Override
	public DictionaryCommons findDictionaryCommons(final String id) {
		return this.dictionaryCommonsDao.load(id);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @author xs
	 * @param code
	 * @return
	 * @see com.wisdombud.education.base.business.DictionaryCommonService#findDictionaryValues(java.lang.String)
	 */

	@Override
	public List<DictionaryCommons> findDictionaryValues(final String code) {
		// TODO Auto-generated method stub
		return this.commonDao
				.findByClass(DictionaryCommons.class, "code", code);
	}

	@Override
	public Map<String, Object> findDictionaryValuesMap(final String code) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<DictionaryCommons> list = this.commonDao.findByClass(
				DictionaryCommons.class, "code", code);
		for (final DictionaryCommons dictionaryCommons : list) {
			map.put(dictionaryCommons.getId(), dictionaryCommons.getValue());
		}
		return map;
	}

	@Override
	public void findPageDictionaryCommons(
			final Pager<DictionaryCommons> page,
			final DictionaryCommons dictionaryCommons) {
		this.dictionaryCommonsDao.findPageDictionaryCommons(page,
				dictionaryCommons);
	}

	@Override
	public void saveOrUpdateDictionaryCommons(
			final DictionaryCommons dictionaryCommons)
			throws IllegalAccessException, InvocationTargetException {
		if (StringUtils.isBlank(dictionaryCommons.getId())) {
			final String maxId = this.dictionaryCommonsDao
					.findMaxIdByCode(dictionaryCommons);
			if (StringUtils.isNotBlank(maxId)) {
				final Integer newId = Integer.valueOf(maxId) + 1;
				dictionaryCommons.setId(newId.toString());
				dictionaryCommons.setPreset(false);
				this.dictionaryCommonsDao.saveOrUpdate(dictionaryCommons);
			}
			return;
		}
		final DictionaryCommons target = this.dictionaryCommonsDao
				.load(dictionaryCommons.getId());
		if (null == target) {
			return;
		}
		dictionaryCommons.setPreset(target.getPreset());
		BeanUtils.copyProperties(target, dictionaryCommons);
		this.dictionaryCommonsDao.update(target);
	}

}
