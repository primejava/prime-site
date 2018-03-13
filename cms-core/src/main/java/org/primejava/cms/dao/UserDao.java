package org.primejava.cms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.basic.query.PropertyFilter;
import org.primejava.basic.util.HibernateUtils;
import org.primejava.cms.imp.pojo.ImportRecordBean;
import org.primejava.cms.model.CommonRoleUser;
import org.primejava.cms.model.User;
import org.primejava.cms.model.UserGroup;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {
	
	@Override
	public Pager<User> findUser(Pager<User> pager,
			List<PropertyFilter> propertyFilters) {
		if(propertyFilters!=null&&propertyFilters.size()!=0){
			Object[] res=HibernateUtils.buildFilterConditionAlias(propertyFilters);
			String hql="from User"+res[0];
			Map<String,Object> alias=(Map<String, Object>) res[1];
			return this.findByAlias(pager,hql,alias);
		}
		return this.find(pager,"from User");
	}
	
	@Override
	public User findUserByName(String username) {
		String hql = "from User where username=?";
		return (User)this.queryObject(hql, username);
	}

	@Override
	public User findUserByIdAndName(String id, String username) {
		String hql = "from User where username=? and id=?";
		return (User)this.queryObject(hql, new Object[]{username,id});
	}

	@Override
	public List<User> findAll() {
		return this.list("from User");
	}

	@Override
	public Pager<User>  findUserBySQL(Pager<User> page,
			String sql) {
		StringBuilder hql= new StringBuilder("select * from t_user where  ");
		hql.append(sql);
		Pager<User>  result=this.findBySql(page, hql.toString(), User.class, true);
		return result;
	}

	@Override
	public void findHibernate() {
		Criteria queryCriteria=this.getSession().createCriteria(User.class);
		 Disjunction disjunction = Restrictions.disjunction();
		  Criterion cirterion = Restrictions.sqlRestriction("email = '976084806@qq.com'");
		  disjunction.add(cirterion);
		  cirterion = Restrictions.sqlRestriction("username = 'baby'");
		  disjunction.add(cirterion);
		  cirterion = Restrictions.sqlRestriction("phone = '123'");
		  disjunction.add(cirterion);
		  
		  Disjunction disjunction2 = Restrictions.disjunction();
		  cirterion=Restrictions.eq("status", 1);
		  disjunction2.add(cirterion);
		  cirterion=Restrictions.eq("nickname", "123");
		  disjunction2.add(cirterion);
		  Conjunction conjunction2 = Restrictions.conjunction();
		  cirterion = Restrictions.eq("username", "789");
		  conjunction2.add(cirterion);
		  cirterion = Restrictions.eq("username", "910");
		  conjunction2.add(cirterion);
//		  disjunction.add(disjunction2);
		  conjunction2.add(disjunction2);
		  disjunction.add(conjunction2);
		  
		  queryCriteria.add(disjunction);
		  System.out.println(queryCriteria.toString());
		  List<User> users=queryCriteria.list();
		  for(User u:users){
			  System.out.println(u.getUsername());
		  }
	}

	public ImportRecordBean saveOrUpdateUser(List<String> sqls) {
	    int saveOrUpdateCount = 0;
        int errorCount = 0;
        for (String sql : sqls) {
            try {
            	this.
                createSQLQuery(sql).executeUpdate();
            } catch (Exception e) {
                errorCount++;
                continue;
            }
            saveOrUpdateCount++;
        }
        ImportRecordBean recordBean = new ImportRecordBean();
        recordBean.setSaveOrUpdateCount(saveOrUpdateCount);
        recordBean.setErrorCount(errorCount);
        recordBean.setSum(sqls.size());
        return recordBean;
	}

	@SuppressWarnings("unchecked")
	public List<String> findUsersByTag(String tag) {
		String hql="select id from User where tags like :tag";
		Map<String,Object> maps=new HashMap<String, Object>();
		maps.put("tag", "%"+tag+"%");
		Query q=this.createHqlQuery(hql, maps);
		return q.list();
	}

	@Override
	public List<CommonRoleUser> findRoleUser(String id) {
		 return createHqlQuery("from CommonRoleUser where userId=?", id).list();
	}

	@Override
	public List<UserGroup> findUerGroup(String id) {
		 return createHqlQuery("from CommonUserGroup where userId=?", id).list();
	}


}
