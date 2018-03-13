package org.primejava.cms.dao;

import java.util.List;

import org.primejava.basic.dao.IBaseDao;
import org.primejava.cms.model.DictionaryCollege;
import org.primejava.cms.model.DictionaryCollegeSpecialty;
import org.primejava.cms.model.DictionarySpecialty;

public interface DictionaryCollegeSpecialtyDao extends IBaseDao<DictionaryCollegeSpecialty>{
	/**
     * 根据学院ID删除学院.<br/>
     * date: 2016年1月22日 上午11:49:38 <br/>
     * @author mjzhao@wisdombud.com
     * @param collegeIds
     */
    void deleteCollegesByIds(String... collegeIds);

    /**
     * 根据专业ID删除专业.<br/>
     * date: 2016年1月22日 上午11:24:16 <br/>
     * @author mjzhao@wisdombud.com
     * @param specialtyId
     */
    void deleteCollegeSpecialtyBySpecialtyId(String specialtyId);

    /**
     * 根据专业ID删除专业.<br/>
     * date: 2016年1月22日 上午11:24:21 <br/>
     * @author mjzhao@wisdombud.com
     * @param specialtyIds
     */
    void deleteSpecialtyByIds(Object... specialtyIds);

    List<DictionaryCollege> findAllColleges();

    DictionaryCollegeSpecialty findByCollegeId(String collegeId);

    DictionaryCollegeSpecialty findBySpecialtyId(String specialtyId);

    /**
     * 功能: 根据学院名称查询学院对象.<br/>
     * date: 2016年4月15日 下午1:55:19 <br/>
     * @author mmzhao@wisdombud.com
     * @param name
     * @return
     */
    DictionaryCollege findCollegeByName(String name);

    List<DictionaryCollege> findColleges(Short graduationYear, String... educations);

    /**
     * 查询出年份的学院.<br/>
     * date: 2016年1月22日 下午1:51:57 <br/>
     * @author mjzhao@wisdombud.com
     * @param graduationYear
     * @return
     */
    List<DictionaryCollege> findCollegesByYear(Short graduationYear);

    /**
     * 查询出年度的学院与专业.<br/>
     * date: 2016年1月29日 上午9:55:40 <br/>
     * @author mjzhao@wisdombud.com
     * @param graduationYear
     * @return
     */
    List<DictionaryCollegeSpecialty> findCollegeSpecialtiesByYear(short graduationYear);

    DictionaryCollegeSpecialty findCollegeSpecialty(String collegeId, String specialtyId, Short year, String education);

    /**
     * 查询出学院和专业的关联对象.<br/>
     * date: 2016年1月22日 上午10:24:45 <br/>
     * @author mjzhao@wisdombud.com
     * @param year
     * @param education
     * @param collegeId
     * @return
     */
    DictionaryCollegeSpecialty findCollegeSpecialtyByCollegeId(Short year, String education, String collegeId);

    /**
     * 根据学历和学院ID查询学院的所有关联.<br/>
     * date: 2016年1月22日 上午11:41:00 <br/>
     * @author mjzhao@wisdombud.com
     * @param collegeId
     * @param education
     * @return
     */
    List<DictionaryCollegeSpecialty> findCollegeSpecialtyByCollegeId(String collegeId, String education);

    DictionaryCollegeSpecialty findCollegeSpecialtyBySpecialtyId(Short year, String education, String collegeId,
            String specialtyId);

    List<DictionaryCollegeSpecialty> findCollegeSpecialtyByYear(Short graduationYear);

    List<DictionaryCollegeSpecialty> findCollegeSpecialtys(DictionaryCollegeSpecialty collegeSpecialty,
            boolean isCollege);

    List<DictionarySpecialty> findSpecialties(Short graduationYear, List<String> educations, List<String> collegeIds);

    /**
     * 功能: TODO.<br/>
     * date: 2016年4月15日 下午2:06:17 <br/>
     * @author mmzhao@wisdombud.com
     * @param year
     * @param education
     * @param collegeId
     * @return
     */
    List<DictionaryCollegeSpecialty> findSpecialties(Short year, String education, String collegeId);

    List<DictionaryCollegeSpecialty> findSpecialtiesByEducation(String graduationYear, String education);

    /**
     * 功能: 根据专业名称和学院id查询专业对象.<br/>
     * date: 2016年1月28日 上午11:58:03 <br/>
     * @author mjzhao@wisdombud.com
     * @param name
     * @param parentId
     * @return
     */
    DictionarySpecialty findSpecialtyByNameAndParentId(String name, String parentId);

}
