package org.primejava.cms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jersey.repackaged.com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.primejava.basic.dao.BaseDao;
import org.primejava.cms.model.DictionaryCollege;
import org.primejava.cms.model.DictionaryCollegeSpecialty;
import org.primejava.cms.model.DictionarySpecialty;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Maps;

@Repository(value = "collegeSpecialtyDao")
public class DictionaryCollegeSpecialtyDaoImpl  extends BaseDao<DictionaryCollegeSpecialty> implements DictionaryCollegeSpecialtyDao{
	 	
		@Override
	    public void deleteCollegesByIds(final String... collegeIds) {
	        final HashMap params = Maps.newHashMap();
	        params.put("ids", collegeIds);
	        createHqlQuery("delete DictionaryCollege where id in (:ids)", params).executeUpdate();
	    }

	    @Override
	    public void deleteCollegeSpecialtyBySpecialtyId(final String specialtyId) {
	        createHqlQuery("delete DictionaryCollegeSpecialty where specialtyId = ? ", specialtyId).executeUpdate();
	    }

	    @Override
	    public void deleteSpecialtyByIds(final Object... specialtyIds) {
	        final HashMap params = Maps.newHashMap();
	        params.put("ids", specialtyIds);
	        createHqlQuery("delete DictionarySpecialty where id in (:ids)", params).executeUpdate();
	    }

	    @Override
	    public List<DictionaryCollege> findAllColleges() {
	        return createHqlQuery("from DictionaryCollege order by id").list();
	    }

	    @Override
	    public DictionaryCollegeSpecialty findByCollegeId(final String collegeId) {
	        return (DictionaryCollegeSpecialty) createHqlQuery("from DictionaryCollegeSpecialty where collegeId = ? ",
	                collegeId).uniqueResult();
	    }

	    @Override
	    public DictionaryCollegeSpecialty findBySpecialtyId(final String specialtyId) {
	        return (DictionaryCollegeSpecialty) createHqlQuery("from DictionaryCollegeSpecialty where specialtyId = ? ",
	                specialtyId).uniqueResult();
	    }

	    @Override
	    public DictionaryCollege findCollegeByName(final String name) {
	        final StringBuilder hql = new StringBuilder();
	        hql.append("from DictionaryCollege where name=?");
	        return (DictionaryCollege) createHqlQuery(hql.toString(), name).uniqueResult();
	    }

	    @Override
	    public List<DictionaryCollege> findColleges(final Short graduationYear, final String... educations) {
	        final HashMap params = Maps.newHashMap();
	        final StringBuilder hql = new StringBuilder();
	        hql.append(" select college from DictionaryCollege college, DictionaryCollegeSpecialty collegeSpecialty ");
	        hql.append(" where college.id = collegeSpecialty.collegeId and collegeSpecialty.year = :year and collegeSpecialty.education in(:educations) and collegeSpecialty.specialtyId is null ");
	        hql.append(" order by collegeSpecialty.sort ");
	        params.put("year", graduationYear);
	        params.put("educations", Lists.newArrayList(educations));
	        return createHqlQuery(hql.toString(), params).list();
	    }

	    @Override
	    public List<DictionaryCollege> findCollegesByYear(final Short graduationYear) {
	        return createHqlQuery("from DictionaryCollege where year = ?", graduationYear).list();
	    }

	    @Override
	    public List<DictionaryCollegeSpecialty> findCollegeSpecialtiesByYear(final short graduationYear) {
	        return createHqlQuery("from DictionaryCollegeSpecialty where year=? order by id", graduationYear).list();
	    }

	    @Override
	    public DictionaryCollegeSpecialty findCollegeSpecialty(final String collegeId, final String specialtyId,
	            final Short year, final String education) {
	        final Map<String, Object> params = new HashMap<String, Object>();
	        final StringBuilder hql = new StringBuilder();
	        hql.append(" from DictionaryCollegeSpecialty where 1=1 ");
	        if (StringUtils.isNotBlank(collegeId)) {
	            hql.append(" and collegeId = :collegeId");
	            params.put("collegeId", collegeId);
	        }
	        if (StringUtils.isNotBlank(specialtyId)) {
	            hql.append(" and specialtyId = :specialtyId");
	            params.put("specialtyId", specialtyId);
	        } else {
	            hql.append(" and specialtyId is null");
	        }
	        if (null != year) {
	            hql.append(" and year = :year");
	            params.put("year", year);
	        }
	        if (StringUtils.isNotBlank(education)) {
	            hql.append(" and education = :education");
	            params.put("education", education);
	        }
	        return (DictionaryCollegeSpecialty) createHqlQuery(hql.toString(), params).uniqueResult();
	    }

	    @Override
	    public DictionaryCollegeSpecialty findCollegeSpecialtyByCollegeId(final Short year, final String education,
	            final String collegeId) {
	        return (DictionaryCollegeSpecialty) createHqlQuery(
	                "from DictionaryCollegeSpecialty where year = ? and education = ? and collegeId = ? and specialtyId is null",
	                year, education, collegeId).uniqueResult();
	    }

	    @Override
	    public List<DictionaryCollegeSpecialty> findCollegeSpecialtyByCollegeId(final String collegeId,
	            final String education) {
	        return createHqlQuery("from DictionaryCollegeSpecialty where collegeId = ? and education = ?", collegeId,
	                education).list();
	    }

	    @Override
	    public DictionaryCollegeSpecialty findCollegeSpecialtyBySpecialtyId(final Short year, final String education,
	            final String collegeId, final String specialtyId) {
	        return (DictionaryCollegeSpecialty) createHqlQuery(
	                "from DictionaryCollegeSpecialty where year = ? and education = ? and specialtyId=? ", year, education,
	                specialtyId).uniqueResult();
	    }

	    @Override
	    public List<DictionaryCollegeSpecialty> findCollegeSpecialtyByYear(final Short graduationYear) {
	        return createHqlQuery("from DictionaryCollegeSpecialty where year = ? order by specialtyId", graduationYear)
	                .list();
	    }

	    @Override
	    public List<DictionaryCollegeSpecialty> findCollegeSpecialtys(final DictionaryCollegeSpecialty collegeSpecialty,
	            final boolean isCollege) {
	        final Map<String, Object> params = new HashMap<String, Object>();
	        final StringBuilder hql = new StringBuilder();
	        hql.append(" from DictionaryCollegeSpecialty where 1=1 ");
	        if (StringUtils.isNotBlank(collegeSpecialty.getEducation())) {
	            hql.append(" and education = :education");
	            params.put("education", collegeSpecialty.getEducation());
	        }
	        if (null != collegeSpecialty.getYear() && 0 != collegeSpecialty.getYear()) {
	            hql.append(" and year = :year");
	            params.put("year", collegeSpecialty.getYear());
	        }
	        if (StringUtils.isNotBlank(collegeSpecialty.getCollegeId())) {
	            hql.append(" and collegeId = :collegeId");
	            params.put("collegeId", collegeSpecialty.getCollegeId());
	        }
	        if (isCollege) {
	            hql.append(" and specialtyId is null");
	        }
	        return createHqlQuery(hql.toString(), params).list();
	    }

	    @Override
	    public List<DictionarySpecialty> findSpecialties(final Short graduationYear, final List<String> educations,
	            final List<String> collegeIds) {
	        final StringBuilder hql = new StringBuilder();
	        final HashMap params = Maps.newHashMap();
	        hql.append("select specialty from DictionarySpecialty specialty, DictionaryCollegeSpecialty collegeSpecialty ");
	        hql.append(" where specialty.id = collegeSpecialty.specialtyId and collegeSpecialty.year = :year and collegeSpecialty.education in (:educations) ");
	        if (null != collegeIds) {
	            hql.append(" and collegeSpecialty.collegeId in (:collegeIds) ");
	            params.put("collegeIds", collegeIds);
	        }
	        hql.append(" order by collegeSpecialty.sort ");
	        params.put("year", graduationYear);
	        params.put("educations", educations);
	        return createHqlQuery(hql.toString(), params).list();
	    }

	    @Override
	    public List<DictionaryCollegeSpecialty> findSpecialties(final Short year, final String education,
	            final String collegeId) {
	        final StringBuilder hql = new StringBuilder();
	        final Map<String, Object> params = Maps.newHashMap();
	        hql.append(" from DictionaryCollegeSpecialty where 1=1 ");
	        if (null != year) {
	            hql.append(" and year = :year ");
	            params.put("year", year);
	        }
	        if (StringUtils.isNotBlank(education)) {
	            hql.append(" and education = :education ");
	            params.put("education", education);
	        }
	        if (StringUtils.isNotBlank(collegeId)) {
	            hql.append(" and collegeId = :collegeId ");
	            params.put("collegeId", collegeId);
	        }
	        return createHqlQuery(hql.toString(), params).list();
	    }

	    @Override
	    public List<DictionaryCollegeSpecialty> findSpecialtiesByEducation(final String graduationYear,
	            final String education) {
	        final Map<String, Object> params = new HashMap<String, Object>();
	        final StringBuilder hql = new StringBuilder();
	        hql.append(" from DictionaryCollegeSpecialty where 1=1");
	        if (StringUtils.isNotBlank(graduationYear)) {
	            final Short year = Short.parseShort(graduationYear);
	            hql.append(" and year =:year ");
	            params.put("year", year);
	        }
	        if (StringUtils.isNotBlank(education)) {
	            hql.append(" and education = :education ");
	            params.put("education", education);
	        }
	        hql.append(" and specialtyId is not null");
	        return createHqlQuery(hql.toString(), params).list();
	    }

	    @Override
	    public DictionarySpecialty findSpecialtyByNameAndParentId(final String name, final String parentId) {
	        final StringBuilder hql = new StringBuilder();
	        hql.append(" select specialty from DictionarySpecialty specialty,DictionaryCollegeSpecialty collegeSpecialty ");
	        hql.append(" where specialty.id=collegeSpecialty.specialtyId and specialty.name=? and collegeSpecialty.collegeId=? ");
	        return (DictionarySpecialty) createHqlQuery(hql.toString(), name, parentId).uniqueResult();
	    }
}
