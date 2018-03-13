package org.primejava.cms.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primejava.basic.dao.BaseDao;
import org.primejava.cms.dao.DictionaryCollegeSpecialtyDao;
import org.primejava.cms.dto.TreeNode;
import org.primejava.cms.model.DictionaryCollege;
import org.primejava.cms.model.DictionaryCollegeSpecialty;
import org.primejava.cms.model.DictionaryCommons;
import org.primejava.cms.model.DictionarySpecialty;
import org.primejava.cms.pojo.CollegeSpecialty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GroupTypeEnum.DictionaryCommonsEnum;

import com.google.common.collect.Lists;

@Service("collegeSpecialtyService")
public class DictionaryCollegeSpecialtyServiceImpl implements DictionaryCollegeSpecialtyService {

    @Autowired
    private DictionaryCollegeSpecialtyDao collegeSpecialtyDao;
    @Autowired
    private BaseDao                    commonDao;
    @Autowired
    private DictionaryCommonService       dictionaryService;
//    @Autowired
//    private CommonBaseService             baseService;

    /**
     * @see com.wisdombud.education.base.business.DictionaryCollegeSpecialtyService#buildAllNodes(short)
     */

    @Override
    public List<TreeNode> buildAllNodes(final short graduationYear) {
        // TODO Auto-generated method stub
        final List<TreeNode> nodes = Lists.newArrayList();
        final List<String> list = Lists.newArrayList();
        final List<DictionaryCommons> educations = this.dictionaryService
                .findDictionaryValues(DictionaryCommonsEnum.EDUCATION.getCode());
        if (educations.isEmpty()) {
            return nodes;
        }
        for (final DictionaryCommons education : educations) {
            list.add(education.getId());
            final TreeNode node = new TreeNode(education.getId(), education.getValue());
            nodes.add(node);
        }
        final List<DictionaryCollegeSpecialty> collegeSpecialties = this.collegeSpecialtyDao
                .findCollegeSpecialtyByYear(graduationYear);
        if (collegeSpecialties.isEmpty()) {
            return nodes;
        }
        for (final DictionaryCollegeSpecialty collegeSpecialty : collegeSpecialties) {
            if (!list.contains(collegeSpecialty.getEducation())) {
                continue;
            }
            if (StringUtils.isBlank(collegeSpecialty.getSpecialtyId())) {
                final TreeNode node = new TreeNode(collegeSpecialty.getId(), collegeSpecialty.getName());
                node.setParentId(collegeSpecialty.getEducation());
                node.setIndex(collegeSpecialty.getSort());
                if (!nodes.contains(node)) {
                    nodes.add(node);
                }
                continue;
            }
            final TreeNode childrenNode = new TreeNode(collegeSpecialty.getId(), collegeSpecialty.getName());
            childrenNode.setParentId(findParentNode(collegeSpecialties, collegeSpecialty.getCollegeId(),
                    collegeSpecialty.getEducation()).getId());
            childrenNode.setIndex(collegeSpecialty.getSort());
            nodes.add(childrenNode);
        }
        Collections.sort(nodes);
        return nodes;
    }
    
    public List<TreeNode> buildAllNodes2(short graduationYear) {
        // TODO Auto-generated method stub
        List<TreeNode> nodes=Lists.newArrayList();
        List<DictionaryCommons> educations= dictionaryService.findDictionaryValues(DictionaryCommonsEnum.EDUCATION.getCode());
        if(educations.isEmpty()){
           return nodes;
        }
        for(DictionaryCommons education:educations){
            TreeNode node=new TreeNode(education.getId(),education.getValue());
            nodes.add(node);
        }
        List<DictionaryCollegeSpecialty> collegeSpecialties=collegeSpecialtyDao.findCollegeSpecialtiesByYear(graduationYear);
        if(collegeSpecialties.isEmpty()){
            return nodes;
         }
         for(DictionaryCollegeSpecialty collegeSpecialty:collegeSpecialties){
             DictionaryCollege college=(DictionaryCollege) commonDao.findById(collegeSpecialty.getCollegeId(),DictionaryCollege.class);
             if(null==college){
                 continue;
             }
             TreeNode node=new TreeNode(college.getId(),college.getName());
             node.setParentId(collegeSpecialty.getEducation());
             node.setSort(collegeSpecialty.getSort());
             if(!nodes.contains(node)){
                 nodes.add(node);
             }
             if(StringUtils.isBlank(collegeSpecialty.getSpecialtyId())){
                 continue;
             }
             DictionarySpecialty specialty=(DictionarySpecialty) commonDao.findById(collegeSpecialty.getSpecialtyId(),DictionarySpecialty.class);
             if(null==specialty){
                 continue;
             }
             TreeNode childrenNode=new TreeNode(specialty.getId(),specialty.getName());
             childrenNode.setParentId(collegeSpecialty.getCollegeId());
             childrenNode.setSort(collegeSpecialty.getSort());
             TreeNode parentNode=findParentNode2(nodes,collegeSpecialty.getCollegeId(),collegeSpecialty.getEducation());
             if(null!=parentNode){
                 parentNode.getChildren().add(childrenNode);
             }
         }
         Collections.sort(nodes);
         return nodes;
    }
    
    private TreeNode findParentNode2(List<TreeNode> nodes, String collegeId, String education) {
        // TODO Auto-generated method stub
        for(TreeNode node:nodes){
            if(!node.getId().equals(collegeId)||!node.getParentId().equals(education)){
                continue;
            }
            if(null==node.getChildren()){
                node.setChildren(new ArrayList());
            }
            return node;
        }
        return null;
    }

    @Override
    public List<TreeNode> buildNodes(final short graduationYear) {
        final List<TreeNode> nodes = Lists.newArrayList();
        final List<DictionaryCommons> educations = this.dictionaryService
                .findDictionaryValues(DictionaryCommonsEnum.EDUCATION.getCode());
        if (educations.isEmpty()) {
            return nodes;
        }
        for (final DictionaryCommons education : educations) {
            final TreeNode node = new TreeNode(education.getId(), education.getValue());
            nodes.add(node);
        }
        final List<DictionaryCollegeSpecialty> collegeSpecialties = this.collegeSpecialtyDao
                .findCollegeSpecialtiesByYear(graduationYear);
        if (collegeSpecialties.isEmpty()) {
            return nodes;
        }
        for (final DictionaryCollegeSpecialty collegeSpecialty : collegeSpecialties) {
            final DictionaryCollege college = (DictionaryCollege) this.commonDao.findById(
                    collegeSpecialty.getCollegeId(), DictionaryCollege.class);
            if (null == college) {
                continue;
            }
            final TreeNode node = new TreeNode(college.getId(), college.getName());
            node.setParentId(collegeSpecialty.getEducation());
            node.setSort(collegeSpecialty.getSort());
            if (!nodes.contains(node)) {
                nodes.add(node);
            }
            if (StringUtils.isBlank(collegeSpecialty.getSpecialtyId())) {
                continue;
            }
            final DictionarySpecialty specialty = (DictionarySpecialty) this.commonDao.findById(
                    collegeSpecialty.getSpecialtyId(), DictionarySpecialty.class);
            if (null == specialty) {
                continue;
            }
            final TreeNode childrenNode = new TreeNode(specialty.getId(), specialty.getName());
            childrenNode.setParentId(collegeSpecialty.getCollegeId());
            childrenNode.setSort(collegeSpecialty.getSort());
            final TreeNode parentNode = findParentNodes(nodes, collegeSpecialty.getCollegeId(),
                    collegeSpecialty.getEducation());
            if (null != parentNode) {
                parentNode.getChildren().add(childrenNode);
            }
        }
        Collections.sort(nodes);
        return nodes;
    }

    /**
     * @see com.wisdombud.education.base.business.DictionaryCollegeSpecialtyService#delete(com.wisdombud.education.dictionary.pojo.CollegeSpecialty)
     */

    @Override
    public void delete(final CollegeSpecialty collegeSpecialty) {
        // TODO Auto-generated method stub
        final DictionaryCollegeSpecialty dictionaryCollegeSpecialty = (DictionaryCollegeSpecialty) this.commonDao
                .findById(collegeSpecialty.getId(), DictionaryCollegeSpecialty.class);
        if (dictionaryCollegeSpecialty.getSpecialtyId() == null) {
            // 如果删除的是学院，则要先删除该学院下的专业
            final List<DictionaryCollegeSpecialty> collegeSpecialties = this.collegeSpecialtyDao.findSpecialties(
                    dictionaryCollegeSpecialty.getYear(), dictionaryCollegeSpecialty.getEducation(),
                    dictionaryCollegeSpecialty.getCollegeId());
            this.commonDao.deleteAll(collegeSpecialties);
            return;
        }
        this.commonDao.delete(dictionaryCollegeSpecialty);
    }

    @Override
    public List<CollegeSpecialty> findAllCollegeSpecialty(final short graduationYear) {
        // TODO Auto-generated method stub
        final List<CollegeSpecialty> collegeSpecialties = Lists.newArrayList();
        final List<DictionaryCollegeSpecialty> dictionaryCollegeSpecialties = this.collegeSpecialtyDao
                .findCollegeSpecialtyByYear(graduationYear);
        if (dictionaryCollegeSpecialties.isEmpty()) {
            return Lists.newArrayList();
        }
        for (final DictionaryCollegeSpecialty dictionaryCollegeSpecialty : dictionaryCollegeSpecialties) {
            if (StringUtils.isBlank(dictionaryCollegeSpecialty.getSpecialtyId())) {
                final DictionaryCollege college = (DictionaryCollege) this.commonDao.findById(
                        dictionaryCollegeSpecialty.getCollegeId(), DictionaryCollege.class);
                if (null == college) {
                    continue;
                }
                final CollegeSpecialty tempCollege = new CollegeSpecialty();
                BeanUtils.copyProperties(college, tempCollege);
                tempCollege.setCode(college.getUndergraduateCode());
                tempCollege.setEducation(dictionaryCollegeSpecialty.getEducation());
                tempCollege.setParentId(dictionaryCollegeSpecialty.getEducation());
                tempCollege.setIsCollege(true);
                if (!collegeSpecialties.contains(tempCollege)) {
                    collegeSpecialties.add(tempCollege);
                }
                continue;
            }
            final DictionarySpecialty specialty = (DictionarySpecialty) this.commonDao.findById(
                    dictionaryCollegeSpecialty.getSpecialtyId(), DictionarySpecialty.class);
            if (null == specialty) {
                continue;
            }
            final CollegeSpecialty tempSpecialty = new CollegeSpecialty();
            BeanUtils.copyProperties(specialty, tempSpecialty);
            tempSpecialty.setEducation(dictionaryCollegeSpecialty.getEducation());
            tempSpecialty.setIsCollege(false);
            tempSpecialty.setParentId(dictionaryCollegeSpecialty.getCollegeId());
            tempSpecialty.setYear(dictionaryCollegeSpecialty.getYear());
            collegeSpecialties.add(tempSpecialty);
        }
        return collegeSpecialties;
    }

    /**
     * @see com.wisdombud.education.base.business.DictionaryCollegeSpecialtyService#findCollegeNodes()
     */

    @Override
    public List<TreeNode> findCollegeNodes() {
        // TODO Auto-generated method stub
        final List<TreeNode> nodes = Lists.newArrayList();
        final List<DictionaryCommons> educations = this.dictionaryService
                .findDictionaryValues(DictionaryCommonsEnum.EDUCATION.getCode());
        if (educations.isEmpty()) {
            return nodes;
        }
        final List<DictionaryCollege> colleges = this.collegeSpecialtyDao.findAllColleges();
        if (colleges.isEmpty()) {
            return nodes;
        }
        for (final DictionaryCollege college : colleges) {
            if (null != college) {
                final TreeNode node = new TreeNode(college.getId(), college.getName());
                if (!nodes.contains(node)) {
                    nodes.add(node);
                }
                continue;
            }
        }
        Collections.sort(nodes);
        return nodes;
    }

    /**
     * @see com.wisdombud.education.base.business.DictionaryCollegeSpecialtyService#findColleges()
     */

    @Override
    public List<DictionaryCollege> findColleges() {
        return this.collegeSpecialtyDao.findAllColleges();
    }

    /**
     * @see com.wisdombud.education.base.business.DictionaryCollegeSpecialtyService#findColleges(short)
     */

    @Override
    public List<DictionaryCollege> findColleges(final short graduationYear) {
        return findColleges(graduationYear);
    }

    /**
     * @see com.wisdombud.education.base.business.DictionaryCollegeSpecialtyService#findColleges(short,
     *      java.lang.String[])
     */

    @Override
    public List<DictionaryCollege> findColleges(final short graduationYear, final String... educations) {
        // TODO Auto-generated method stub
        return this.collegeSpecialtyDao.findColleges(graduationYear, educations);
    }

    @Override
    public List<CollegeSpecialty> findCollegeSpecialties(final String graduationYear) {
        final List<CollegeSpecialty> list = Lists.newArrayList();
        final List<DictionaryCollegeSpecialty> collegeSpecialties = this.collegeSpecialtyDao
                .findCollegeSpecialtiesByYear(Short.parseShort(graduationYear));
        if (collegeSpecialties.isEmpty()) {
            return list;
        }
        for (final DictionaryCollegeSpecialty collegeSpecialty : collegeSpecialties) {
            list.add(getCollegeSpecialty(collegeSpecialty, collegeSpecialty.getCollegeId(),
                    collegeSpecialty.getSpecialtyId()));
        }
        return getList(list);
    }

    @Override
    public DictionaryCollegeSpecialty findCollegeSpecialty(final String collegeId, final String specialtyId,
            final Short year, final String education) {
        // TODO Auto-generated method stub
        return this.collegeSpecialtyDao.findCollegeSpecialty(collegeId, specialtyId, year, education);
    }

    /**
     * @see com.wisdombud.education.base.business.DictionaryCollegeSpecialtyService#findCollegeSpecialtyMap(java.lang.String,
     *      java.lang.String)
     */

    @Override
    public List<Map<String, List<DictionaryCollegeSpecialty>>> findCollegeSpecialtyMap(final String education,
            final String year) {
        // TODO Auto-generated method stub
        final List<Map<String, List<DictionaryCollegeSpecialty>>> list = new ArrayList<>();
        final DictionaryCollegeSpecialty collegeSpecialty = new DictionaryCollegeSpecialty();
        if (StringUtils.isNotBlank(year)) {
            collegeSpecialty.setYear(Short.parseShort(year));
        }
        if (StringUtils.isNotBlank(education)) {
            collegeSpecialty.setEducation(education);
        }
        final List<DictionaryCollegeSpecialty> colleges = findCollegeSpecialtys(collegeSpecialty, true);
        for (final DictionaryCollegeSpecialty college : colleges) {
            final Map<String, List<DictionaryCollegeSpecialty>> map = new HashMap<>();
            if (null == college || StringUtils.isBlank(college.getName())
                    || StringUtils.isBlank(college.getCollegeId())) {
                continue;
            }
            collegeSpecialty.setCollegeId(college.getCollegeId());
            map.put(college.getName(), findCollegeSpecialtys(collegeSpecialty, false));
            list.add(map);
        }
        return list;
    }

    List<DictionaryCollegeSpecialty> findCollegeSpecialtys(final DictionaryCollegeSpecialty collegeSpecialty,
            final boolean isCollege) {
        return this.collegeSpecialtyDao.findCollegeSpecialtys(collegeSpecialty, isCollege);
    }

    private DictionaryCollegeSpecialty findParentNode(final List<DictionaryCollegeSpecialty> nodes,
            final String collegeId, final String education) {
        for (final DictionaryCollegeSpecialty collegeSpecialty : nodes) {
            if (collegeSpecialty.getCollegeId().equals(collegeId) && collegeSpecialty.getEducation().equals(education)
                    && StringUtils.isBlank(collegeSpecialty.getSpecialtyId())) {
                return collegeSpecialty;
            }
        }
        return new DictionaryCollegeSpecialty();
    }

    private TreeNode findParentNodes(final List<TreeNode> nodes, final String collegeId, final String education) {
        // TODO Auto-generated method stub
        for (final TreeNode node : nodes) {
            if (!node.getId().equals(collegeId) || !node.getParentId().equals(education)) {
                continue;
            }
            if (null == node.getChildren()) {
                node.setChildren(new ArrayList());
            }
            return node;
        }
        return null;
    }

    /**
     * @see com.wisdombud.education.base.business.DictionaryCollegeSpecialtyService#findSpecialties(short,
     *      java.util.List, java.util.List)
     */

    @Override
    public List<DictionarySpecialty> findSpecialties(final short graduationYear, final List<String> educations,
            final List<String> collegeIds) {
        // TODO Auto-generated method stub
        return this.collegeSpecialtyDao.findSpecialties(graduationYear, educations, collegeIds);
    }

    /**
     * @see com.wisdombud.education.base.business.DictionaryCollegeSpecialtyService#findSpecialties(short,
     *      java.lang.String, java.lang.String)
     */

    @Override
    public List<DictionarySpecialty> findSpecialties(final short graduationYear, final String education,
            final String collegeId) {
        // TODO Auto-generated method stub
        return this.collegeSpecialtyDao.findSpecialties(graduationYear, Lists.newArrayList(education),
                Lists.newArrayList(collegeId));
    }

    /**
     * @see com.wisdombud.education.base.business.DictionaryCollegeSpecialtyService#findSpecialtiesByEducation(java.lang.String,
     *      java.lang.String)
     */

    @Override
    public List<DictionaryCollegeSpecialty> findSpecialtiesByEducation(final String graduationYear,
            final String education) {
        // TODO Auto-generated method stub
        return this.collegeSpecialtyDao.findSpecialtiesByEducation(graduationYear, education);
    }

    private CollegeSpecialty getCollegeSpecialty(final DictionaryCollegeSpecialty collegeSpecialty,
            final String collegeId, final String specialtyId) {
        final DictionaryCollege college = (DictionaryCollege) this.commonDao.findById(collegeId,
                DictionaryCollege.class);
        if (null == college) {
            return null;
        }
        final CollegeSpecialty result = new CollegeSpecialty();
        result.setYear(collegeSpecialty.getYear());
        result.setEducation(collegeSpecialty.getEducation());
        if (StringUtils.isBlank(specialtyId)) {
            result.setIsCollege(true);
            result.setId(college.getId());
            result.setName(college.getName());
            return result;
        }
        final DictionarySpecialty specialty = (DictionarySpecialty) this.commonDao.findById(specialtyId,
                DictionarySpecialty.class);
        if (null == specialty) {
            return null;
        }
        result.setIsCollege(false);
        result.setId(specialty.getId());
        result.setParentId(college.getId());
        result.setName(specialty.getName());
        return result;
    }

    private List<CollegeSpecialty> getList(final List<CollegeSpecialty> list) {
        final List<String> specialties = Lists.newArrayList();
        final List<CollegeSpecialty> specialtys = Lists.newArrayList();
        final Map<String, Integer> specialtyMap = new HashMap<>();
        // for (CollegeSpecialty collegeSpecialty : list) {
        // if (null == specialtyMap.get(collegeSpecialty.getName())) {
        // specialtyMap.put(collegeSpecialty.getName(), 1);
        // continue;
        // }
        // if (specialtyMap.get(collegeSpecialty.getName()) == 1) {
        // specialties.add(collegeSpecialty.getName());
        // }
        // }
        for (final CollegeSpecialty collegeSpecialty : list) {
            if (StringUtils.isNotBlank(collegeSpecialty.getName()) && !specialties.contains(collegeSpecialty.getName())) {
                specialtys.add(collegeSpecialty);
            }
        }
        return specialtys;
    }


    @Override
    public void save(final CollegeSpecialty collegeSpecialty) {
    	Calendar a=Calendar.getInstance();
        final String graduationYear = String.valueOf(a.get(Calendar.YEAR));
        if (Integer.parseInt(graduationYear) > collegeSpecialty.getYear()) { // 如果修改的学院或专业年份小于配置参数的年份，则不予保存或修改
            return;
        }
        // 保存学院或专业信息
        if (StringUtils.isBlank(collegeSpecialty.getId())) {
            final String id = saveCollegeOrSpecialty(collegeSpecialty); // 保存学院或专业信息，并返回生成的id
            final DictionaryCollegeSpecialty collegeSpecialtyPo = new DictionaryCollegeSpecialty();
            if (collegeSpecialty.getIsCollege()) { // 如果是学院，只保存学院的ID
                collegeSpecialtyPo.setCollegeId(id);
            } else { // 如果是专业，同时保存学院ID和专业的ID
                collegeSpecialty.setParentId(((DictionaryCollegeSpecialty) this.commonDao.findById(
                        collegeSpecialty.getParentId(), DictionaryCollegeSpecialty.class)).getCollegeId());
                collegeSpecialtyPo.setCollegeId(collegeSpecialty.getParentId());
                collegeSpecialtyPo.setSpecialtyId(id);
            }
            collegeSpecialtyPo.setEducation(collegeSpecialty.getEducation());
            collegeSpecialtyPo.setYear(collegeSpecialty.getYear());
            collegeSpecialtyPo.setName(collegeSpecialty.getName());
            collegeSpecialtyPo.setSort(collegeSpecialty.getSort());
            this.commonDao.add(collegeSpecialtyPo);
            return;
        }
        // 修改学院或专业信息
        final DictionaryCollegeSpecialty collegeSpecailtyPo = (DictionaryCollegeSpecialty) this.commonDao.findById(
                collegeSpecialty.getId(), DictionaryCollegeSpecialty.class);
        collegeSpecailtyPo.setName(collegeSpecialty.getName());
        collegeSpecailtyPo.setSort(collegeSpecialty.getSort());
        this.commonDao.update(collegeSpecailtyPo);
        if (collegeSpecialty.getIsCollege()) { // 修改学院信息
            final DictionaryCollege collegePo = (DictionaryCollege) this.commonDao.findById(
                    collegeSpecailtyPo.getCollegeId(), DictionaryCollege.class);
            collegePo.setName(collegeSpecialty.getName());
            this.commonDao.update(collegePo);
            return;
        }
        // 修改专业信息
        final DictionarySpecialty specialtyPo = (DictionarySpecialty) this.commonDao.findById(
                collegeSpecailtyPo.getSpecialtyId(), DictionarySpecialty.class);
        specialtyPo.setName(collegeSpecialty.getName());
        this.commonDao.update(specialtyPo);
    }

    private String saveCollegeOrSpecialty(final CollegeSpecialty collegeSpecialty) {
        if (collegeSpecialty.getIsCollege()) {
            // if (isRepeat(collegeSpecialty,
            // collegeSpecialtyDao.findCollegeByName(collegeSpecialty.getName())))
            // { // 判断是否重复
            // return "该学院名称已存在！";
            // }
            final DictionaryCollege uniqueCollege = this.collegeSpecialtyDao.findCollegeByName(collegeSpecialty
                    .getName());
            if (null != uniqueCollege) {
                return uniqueCollege.getId();
            }
            final DictionaryCollege college = new DictionaryCollege();
            college.setName(collegeSpecialty.getName());
            college.setUndergraduateCode(collegeSpecialty.getCode());
            college.setDoctorCode(collegeSpecialty.getDoctorCode());
            college.setMasterCode(collegeSpecialty.getMasterCode());
            this.commonDao.add(college);
            return college.getId(); 
        }
        // if (isRepeat(collegeSpecialty,
        // collegeSpecialtyDao.findSpecialtyByNameAndParentId(collegeSpecialty.getName(),
        // collegeSpecialty.getParentId()))) { // 判断是否重复
        // return "该专业名称已存在！";
        // }
        final DictionarySpecialty uniqueSpecialty = this.collegeSpecialtyDao.findSpecialtyByNameAndParentId(
                collegeSpecialty.getName(), collegeSpecialty.getParentId());
        if (null != uniqueSpecialty) {
            return uniqueSpecialty.getId();
        }
        final DictionarySpecialty specialty = new DictionarySpecialty();
        specialty.setName(collegeSpecialty.getName());
        specialty.setCode(collegeSpecialty.getCode());
        this.commonDao.add(specialty);
        return specialty.getId();
    }

    /**
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see com.wisdombud.education.base.business.DictionaryCollegeSpecialtyService#saveCopyCollegeSpecialty(short)
     */

    @Override
    public void saveCopyCollegeSpecialty(final short graduationYear) throws IllegalAccessException,
            InvocationTargetException {
        // TODO Auto-generated method stub
        final List<DictionaryCollegeSpecialty> collegeSpecialties = this.collegeSpecialtyDao
                .findCollegeSpecialtiesByYear((short) (graduationYear - 1));
        for (final DictionaryCollegeSpecialty collegeSpecialty : collegeSpecialties) {
            final DictionaryCollegeSpecialty newCollegeSpecialty = (DictionaryCollegeSpecialty) collegeSpecialty
                    .clone();
            newCollegeSpecialty.setId(null);
            newCollegeSpecialty.setYear(graduationYear);
            this.commonDao.add(newCollegeSpecialty);
        }
        // 更新参数
//        final CommonConfig config = this.baseService.findConfigByKey("collegeCopyYear");
//        if (config == null) {
//            return;
//        }
//        config.setValue(graduationYear + "");
//        this.baseService.saveConfig(config);
//        this.baseService.refreshConfig();
    }
}
