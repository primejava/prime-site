/**
 * 
 */
package org.primejava.basic.dao;

import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.primejava.basic.model.Pager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
@Repository("commonDao")
public class BaseDao<T> implements IBaseDao<T> {
	
	private SessionFactory sessionFactory;
	/**
	 * 创建一个Class的对象来获取泛型的class
	 */
	private Class<?> clz;
	
	public Class<?> getClz() {
		if(clz==null) {
			//获取泛型的Class对象
			clz = ((Class<?>)
					(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Inject
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#add(java.lang.Object)
	 */
	@Override
	public T add(T t) {
		getSession().save(t);
		return t;
	}
	
	@Override
	public T saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
		return t;
	}
	
	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#update(java.lang.Object)
	 */
	@Override
	public void update(T t) {
		getSession().update(t);
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#delete(int)
	 */
	@Override
	public void deleteById(String id) {
		getSession().delete(this.load(id));
	}
	
	public void delete(Object obj){
		getSession().delete(obj);
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#load(int)
	 */
	@Override
	public T load(String id) {
		return (T)getSession().load(getClz(), id);
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#list(java.lang.String, java.lang.Object[])
	 */
	public List<T> list(String hql, Object[] args) {
		return this.list(hql, args, null);
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#list(java.lang.String, java.lang.Object)
	 */
	public List<T> list(String hql, Object arg) {
		return this.list(hql, new Object[]{arg});
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#list(java.lang.String)
	 */
	public List<T> list(String hql) {
		return this.list(hql,null);
	}
	//初始化排序，根据什么排序，肾虚还是降序
	private String initSort(Pager pager,String hql) {
		 if (pager.isOrderEnabled()) {
			  for (int i = 0; i < pager.getOrderBys().size(); i++) {
	                String orderBy = (String) pager.getOrderBys().get(i);
	                String order = (String) pager.getOrders().get(i);
	                hql+=" order by "+orderBy;
	                if ("ASC".equals(order)) {
	                	hql+=" asc ,";
	                } else {
	                	hql+=" desc ,";
	                }
	            }
			  hql= hql.substring(0,hql.length()-1);
		 }
		return hql;
	}
	
	@SuppressWarnings("rawtypes")
	private void setAliasParameter(Query query,Map<String,Object> alias) {
		if(alias!=null) {
			Set<String> keys = alias.keySet();
			for(String key:keys) {
				Object val = alias.get(key);
				if(val instanceof Collection) {
					//查询条件是列表
					query.setParameterList(key, (Collection)val);
				} else {
					query.setParameter(key, val);
				}
			}
		}
	}
	
	private void setParameter(Query query,Object[] args) {
		if(args!=null&&args.length>0) {
			int index = 0;
			for(Object arg:args) {
				query.setParameter(index++, arg);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#list(java.lang.String, java.lang.Object[], java.util.Map)
	 */
	public List<T> list(String hql, Object[] args, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.list();
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#list(java.lang.String, java.util.Map)
	 */
	public List<T> listByAlias(String hql, Map<String, Object> alias) {
		return this.list(hql, null, alias);
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#find(java.lang.String, java.lang.Object[])
	 */
	public Pager<T> find(Pager<T> pager,String hql, Object[] args) {
		return this.find(pager,hql, args, null);
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#find(java.lang.String, java.lang.Object)
	 */
	public Pager<T> find(Pager<T> pager,String hql, Object arg) {
		return this.find(pager,hql, new Object[]{arg});
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#find(java.lang.String)
	 */
	public Pager<T> find(Pager<T> pager,String hql) {
		return this.find(pager,hql,null);
	}
	
	//设置分页的大小和
	@SuppressWarnings("rawtypes")
	private void setPagers(Query query,Pager pages) {
		Integer pageSize = pages.getPageSize();
		//从第几页开始查，查出这一页的数据
		query.setFirstResult((int) pages.getStart()).setMaxResults(pageSize);
	}
	
	private String getCountHql(String hql,boolean isHql) {
		String e = hql.substring(hql.indexOf("from"));
		String c = "select count(*) "+e;
		if(isHql)
			c = c.replaceAll("fetch", "");
		return c;
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#find(java.lang.String, java.lang.Object[], java.util.Map)
	 */
	public Pager<T> find(Pager<T> pager,String hql, Object[] args, Map<String, Object> alias) {
		hql = initSort(pager,hql);
		String cq = getCountHql(hql,true);
		Query cquery = getSession().createQuery(cq);
		Query query = getSession().createQuery(hql);
		//设置别名参数
		setAliasParameter(query, alias);
		setAliasParameter(cquery, alias);
		//设置参数
		setParameter(query, args);
		setParameter(cquery, args);
		setPagers(query,pager);
		List<T> datas = query.list();
		pager.setResult(datas);
		long total = (Long)cquery.uniqueResult();
		pager.setTotalCount(total);
		return pager;
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#find(java.lang.String, java.util.Map)
	 */
	public Pager<T> findByAlias(Pager<T> pager,String hql, Map<String, Object> alias) {
		return this.find(pager,hql,null, alias);
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#queryObject(java.lang.String, java.lang.Object[])
	 */
	public Object queryObject(String hql, Object[] args) {
		return this.queryObject(hql, args,null);
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#queryObject(java.lang.String, java.lang.Object)
	 */
	public Object queryObject(String hql, Object arg) {
		return this.queryObject(hql, new Object[]{arg});
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#queryObject(java.lang.String)
	 */
	public Object queryObject(String hql) {
		return this.queryObject(hql,null);
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#updateByHql(java.lang.String, java.lang.Object[])
	 */
	public void updateByHql(String hql, Object[] args) {
		Query query = getSession().createQuery(hql);
		setParameter(query, args);
		query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#updateByHql(java.lang.String, java.lang.Object)
	 */
	public void updateByHql(String hql, Object arg) {
		this.updateByHql(hql,new Object[]{arg});
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#updateByHql(java.lang.String)
	 */
	public void updateByHql(String hql) {
		this.updateByHql(hql,null);
	}
	
	public SQLQuery createSQLQuery(final String sql, final Object... values) {
        SQLQuery query = getSession().createSQLQuery(sql);
        fillParams(query, values);
        return query;
    }
	
    protected Query createHqlQuery(final String queryString, final Map<String, ?> values) {
        Query query = getSession().createQuery(queryString);
        fillParams(query, values);
        return query;
    }
    
    /**
     * 根据查询HQL与参数列表创建Query对象.
     * 与find()函数可进行更加灵活的操作.
     *
     * @param values 数量可变的参数,按顺序绑定.
     */
    protected Query createHqlQuery(final String queryString, final Object... values) {
        Query query = getSession().createQuery(queryString);
        fillParams(query, values);
        return query;
    }

    /**
     * 填充参数. <br/>
     *
     * @author ghlin
     * @param query query
     * @param params 条件
     */
    private void fillParams(final Query query, final Object... params) {
        if (null == query || null == params) {
            return;
        }
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
    }
    /**
     * 填充参数. <br/>
     *
     * @author ghlin
     * @param query query
     * @param params 条件
     */
    private void fillParams(final Query query, final Map<String, ?> params) {
        if (null == query || null == params) {
            return;
        }
        for (Map.Entry<String, ?> entry : params.entrySet()) {
            if (null == entry.getValue() || StringUtils.isEmpty(entry.getValue().toString().trim())) {
                continue;
            }
            if (entry.getValue() instanceof Collection<?>) {
                query.setParameterList(entry.getKey(), (Collection<?>) entry.getValue());
            } else if (entry.getValue() instanceof Object[]) {
                query.setParameterList(entry.getKey(), (Object[]) entry.getValue());
            } else {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
    }
	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#listBySql(java.lang.String, java.lang.Object[], java.lang.Class, boolean)
	 */
	public <N extends Object>List<N> listBySql(String sql, Object[] args, Class<?> clz,
			boolean hasEntity) {
		return this.listBySql(sql, args, null, clz, hasEntity);
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#listBySql(java.lang.String, java.lang.Object, java.lang.Class, boolean)
	 */
	public <N extends Object>List<N> listBySql(String sql, Object arg, Class<?> clz,
			boolean hasEntity) {
		return this.listBySql(sql, new Object[]{arg}, clz, hasEntity);
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#listBySql(java.lang.String, java.lang.Class, boolean)
	 */
	public <N extends Object>List<N> listBySql(String sql, Class<?> clz, boolean hasEntity) {
		return this.listBySql(sql, null, clz, hasEntity);
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#listBySql(java.lang.String, java.lang.Object[], java.util.Map, java.lang.Class, boolean)
	 */
	public <N extends Object>List<N> listBySql(String sql, Object[] args,
			Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
		SQLQuery sq = getSession().createSQLQuery(sql);
		setAliasParameter(sq, alias);
		setParameter(sq, args);
		if(hasEntity) {
			sq.addEntity(clz);
		} else 
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		return sq.list();
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#listBySql(java.lang.String, java.util.Map, java.lang.Class, boolean)
	 */
	public <N extends Object>List<N> listByAliasSql(String sql, Map<String, Object> alias,
			Class<?> clz, boolean hasEntity) {
		return this.listBySql(sql, null, alias, clz, hasEntity);
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#findBySql(java.lang.String, java.lang.Object[], java.lang.Class, boolean)
	 */
	public <N extends Object>Pager<N> findBySql(Pager<N> pager,String sql, Object[] args, Class<?> clz,
			boolean hasEntity) {
		return this.findBySql(pager,sql, args, null, clz, hasEntity);
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#findBySql(java.lang.String, java.lang.Object, java.lang.Class, boolean)
	 */
	public <N extends Object>Pager<N> findBySql(Pager<N> pager,String sql, Object arg, Class<?> clz,
			boolean hasEntity) {
		return this.findBySql(pager,sql, new Object[]{arg}, clz, hasEntity);
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#findBySql(java.lang.String, java.lang.Class, boolean)
	 */
	public <N extends Object>Pager<N> findBySql(Pager<N> pager,String sql, Class<?> clz, boolean hasEntity) {
		return this.findBySql(pager,sql, null, clz, hasEntity);
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#findBySql(java.lang.String, java.lang.Object[], java.util.Map, java.lang.Class, boolean)
	 */
	public <N extends Object>Pager<N> findBySql(Pager<N> pager,String sql, Object[] args,
			Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
		sql = initSort(pager,sql);
		String cq = getCountHql(sql,false);
		SQLQuery sq = getSession().createSQLQuery(sql);
		SQLQuery cquery = getSession().createSQLQuery(cq);
		setAliasParameter(sq, alias);
		setAliasParameter(cquery, alias);
		setParameter(sq, args);
		setParameter(cquery, args);
		Pager<N> pages = new Pager<N>();
		setPagers(sq, pages);
		if(hasEntity) {
			sq.addEntity(clz);
		} else {
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		}
		List<N> datas = sq.list();
		pages.setResult(datas);
		long total = ((BigInteger)cquery.uniqueResult()).longValue();
		pages.setTotalCount(total);
		return pages;
	}

	/* (non-Javadoc)
	 * @see org.primejava.baisc.dao.IBaseDao#findBySql(java.lang.String, java.util.Map, java.lang.Class, boolean)
	 */
	public <N extends Object>Pager<N> findByAliasSql(Pager<N> pager,String sql, Map<String, Object> alias,
			Class<?> clz, boolean hasEntity) {
		return this.findBySql(pager,sql, null, alias, clz, hasEntity);
	}

	public Object queryObject(String hql, Object[] args,
			Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.uniqueResult();
	}

	public Object queryObjectByAlias(String hql, Map<String, Object> alias) {
		return this.queryObject(hql,null,alias);
	}

	public T findById(String id, Class<T> entityClass) {
        return (T) getSession().get(entityClass, id);
	}
	public T findUniqueBy(Class<T> entityClass, String propertyName, Object value){
	        Criterion criterion = Restrictions.eq(propertyName, value);
	        Criteria criteria = getSession().createCriteria(entityClass);
	        criteria.add(criterion);
	        return (T) criteria.uniqueResult();	
	}
	
    public int batchExecute(final String hql, final Object... values) {
       return createHqlQuery(hql, values).executeUpdate();
   }
    
    public List<T> findByClass(Class<T> entityClass, String propertyName, Object value) {
        Criterion criterion = Restrictions.eq(propertyName, value);
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.add(criterion);
        return criteria.list();
    }

	@Override
	public void deleteAll(Collection<T> entities) {
		 for (Object entity : entities) {
	            getSession().delete(entity);
	            getSession().flush();
	     }		
	}
	   protected String assemblyPercent(String value) {
	        return org.apache.commons.lang3.StringUtils.join("%", value, "%");
	    }

	public void saveOrUpdateCollection(Collection<T> entities) {
		 for (T entity : entities) {
	            saveOrUpdate(entity);
	     }		
	}

}
