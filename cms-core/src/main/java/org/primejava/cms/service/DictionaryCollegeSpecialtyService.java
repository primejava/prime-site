package org.primejava.cms.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.primejava.cms.dto.TreeNode;
import org.primejava.cms.model.DictionaryCollege;
import org.primejava.cms.model.DictionaryCollegeSpecialty;
import org.primejava.cms.model.DictionarySpecialty;
import org.primejava.cms.pojo.CollegeSpecialty;

public interface DictionaryCollegeSpecialtyService {
	 /**
     * 构建学历学院专业节点数. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * @author xs
     * @param graduationYear
     * @return
     */
    List<TreeNode> buildAllNodes(short graduationYear);

    /**
     * 功能: TODO.<br/>
     * date: 2016年4月20日 下午5:37:22 <br/>
     * @author mmzhao@wisdombud.com
     * @param graduationYear
     * @return
     */
    List<TreeNode> buildNodes(short graduationYear);

    /**
     * 删除学院或专业. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * @author xs
     * @param collegeSpecialty
     */
    void delete(CollegeSpecialty collegeSpecialty);

    /**
     * 查询出所有学院专业等. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * @author xs
     * @return
     */
    List<CollegeSpecialty> findAllCollegeSpecialty(short graduationYear);

    /**
     * 构建学历学院节点树. <br/>
     * date: 2016年2月24日 上午11:35:23 <br/>
     * @author mjzhao@wisdombud.com
     * @return
     */
    List<TreeNode> findCollegeNodes();

    /**
     * 默认查询出所有学院. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * @author xs
     * @return
     */
    List<DictionaryCollege> findColleges();

    /**
     * 根据学年查询出所有学院. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * @author xs
     * @return
     */
    List<DictionaryCollege> findColleges(short graduationYear);

    /**
     * 根据学年和学历查询学院. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * @author xs
     * @param graduationYear
     * @param educations
     * @return
     */
    List<DictionaryCollege> findColleges(short graduationYear, String... educations);

    /**
     * 功能: 得到所有的学院专业（学生信息导入）.<br/>
     * date: 2016年4月8日 下午3:22:50 <br/>
     * @author mmzhao@wisdombud.com
     * @return
     */
    List<CollegeSpecialty> findCollegeSpecialties(String graduationYear);

    DictionaryCollegeSpecialty findCollegeSpecialty(String collegeId, String specialtyId, Short year, String education);

    /**
     * 得到学院专业Map.<br/>
     * date: 2016年2月17日 下午1:42:18 <br/>
     * @author mjzhao@wisdombud.com
     * @param education
     * @param year
     * @return
     */
    List<Map<String, List<DictionaryCollegeSpecialty>>> findCollegeSpecialtyMap(String education, String year);

    /**
     * 根据所选学年和多种学历、多种学院查询学院的专业. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * @author xs
     * @param graduationYear
     * @param educations
     * @param collegeIds
     * @return
     */
    List<DictionarySpecialty> findSpecialties(short graduationYear, List<String> educations, List<String> collegeIds);

    /**
     * 根据学年、学院、学历查询专业. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * @author xs
     * @param graduationYear
     * @param educations
     * @param collegeId
     * @return
     */
    List<DictionarySpecialty> findSpecialties(short graduationYear, String education, String collegeId);

    /**
     * 根据学年学历得到专业.<br/>
     * date: 2016年2月23日 下午5:29:23 <br/>
     * @author mjzhao@wisdombud.com
     * @param education
     * @param graduationYear
     */
    List<DictionaryCollegeSpecialty> findSpecialtiesByEducation(String graduationYear, String education);

    /**
     * 保存学院或专业. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * @author xs
     * @param collegeSpecialty
     */
    void save(CollegeSpecialty collegeSpecialty);

    /**
     * 根据指定的年份复制上一年的学院和专业.<br/>
     * date: 2016年1月29日 上午9:53:36 <br/>
     * @author mjzhao@wisdombud.com
     * @param graduationYear
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    void saveCopyCollegeSpecialty(short graduationYear) throws IllegalAccessException, InvocationTargetException;

    List<TreeNode>  buildAllNodes2(short s);

}
