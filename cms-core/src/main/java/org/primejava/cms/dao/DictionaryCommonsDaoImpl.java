package org.primejava.cms.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.DictionaryCommons;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

@Repository(value = "dictionaryCommonsDao")
public class DictionaryCommonsDaoImpl  extends BaseDao<DictionaryCommons> implements DictionaryCommonsDao{

    @Override
    public String findMaxIdByCode(final DictionaryCommons dictionaryCommons) {
        final StringBuilder hql = new StringBuilder();
        final Map<String, Object> values = Maps.newHashMap();
        values.put("code", dictionaryCommons.getCode());
        hql.append(" from DictionaryCommons where code = :code order by id desc ");
        final List<DictionaryCommons> dics = createHqlQuery(hql.toString(), values).list();
        if (dics.isEmpty()) {
            return null;
        }
        return dics.get(0).getId();
    }

    @Override
    public void findPageDictionaryCommons(Pager<DictionaryCommons> page,
            final DictionaryCommons dictionaryCommons) {
        final StringBuilder hql = new StringBuilder();
        final Map<String, Object> values = Maps.newHashMap();
        hql.append(" from DictionaryCommons where 1=1 ");
        if (StringUtils.isNotBlank(dictionaryCommons.getCode())) {
            hql.append(" and code = :code ");
            values.put("code", dictionaryCommons.getCode());
        }
        hql.append(" order by id ");
        page=this.find(page,hql.toString(), values);
    }

}
