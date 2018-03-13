package org.primejava.cms.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.primejava.basic.model.Pager;
import org.primejava.cms.model.DictionaryCommons;

public interface DictionaryCommonService {

    void deleteDictionaryCommons(String id);


    /**
     * 功能: 查询得到一个公共的map集合（key值为公用表的value值）.<br/>
     * @author mmzhao@wisdombud.com
     * @return
     */
    Map<String, DictionaryCommons> findCommonsMap(String code);

    /**
     * 根据ID查询字典表数据. <br/>
     * @author yx
     * @param id
     * @return
     */
    DictionaryCommons findDictionaryCommons(String id);


    /**
     * 根据字典表的编码得到相应的字典码表对象集合. <br/>
     * @author xs
     * @param code 编码
     * @return 字典码表对象集合
     */
    public List<DictionaryCommons> findDictionaryValues(String code);

    public Map<String, Object> findDictionaryValuesMap(String code);


    /**
     * 分页查询字典表数据. <br/>
     * @author yx
     * @param page
     * @param dictionaryCommons
     */
    void findPageDictionaryCommons(Pager<DictionaryCommons> page, DictionaryCommons dictionaryCommons);

    /**
     * 功能: 查询得到一个专业的map集合（key值为专业名称）.<br/>
     * @author mmzhao@wisdombud.com
     * @return
     */
    // Map<String, DictionarySpecialty> findSpecialtiesMapByValue();


    /**
     * 根据学历和学院获取所有专业对象集合. <br/>
     * @author mmzhao@wisdombud.com
     * @return
     */
    // public List<DictionarySpecialty> findSpecialties(DictionarySpecialty specialty);

    /**
     * 功能: 查询得到每个学院的专业集合的MAP集合.<br/>
     * @author mmzhao@wisdombud.com
     * @return
     */
    // public List<Map<String, List<DictionarySpecialty>>> findCollegeSpecialtyMap(String education);

    void saveOrUpdateDictionaryCommons(DictionaryCommons dictionaryCommons) throws IllegalAccessException,
            InvocationTargetException;
}
