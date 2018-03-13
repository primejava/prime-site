package org.primejava.cms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.CommonRole;
import org.primejava.cms.model.CommonRoleDepartment;
import org.primejava.cms.model.CommonRoleUser;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class CommonRoleDaoImpl  extends BaseDao<CommonRole> implements CommonRoleDao {
	 /**
     * TODO 简单描述该方法的实现功能（可选）.
     * @author xs
     * @param role
     * @param page
     * @see com.wisdombud.education.base.dao.CommonRoleDao#findPageAllRole(com.wisdombud.education.base.pojo.CommonRole,
     *      com.wisdombud.product.commons.entity.PageEntity)
     */

    @Override
    public void findPageAllRole(final CommonRole role, final Pager<CommonRole> page) {
        // TODO Auto-generated method stub
        final Map<String, Object> params = new HashMap<String, Object>();
        final StringBuilder hql = new StringBuilder(" from CommonRole where 1=1 ");
        if (StringUtils.isNotBlank(role.getName())) {
            hql.append(" and name like :name ");
            params.put("name", role.getName());
        }
        this.findByAlias(page,hql.toString(), params);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * @author xs
     * @param id
     * @return
     * @see com.wisdombud.education.base.dao.CommonRoleDao#findRoleDepartments(java.lang.String)
     */

    @Override
    public List<CommonRoleDepartment> findRoleDepartments(final String id) {
        // TODO Auto-generated method stub
        return createHqlQuery("from CommonRoleDepartment where roleId=?", id).list();
    }


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * @author xs
     * @param id
     * @return
     * @see com.wisdombud.education.base.dao.CommonRoleDao#findRoleUser(java.lang.String)
     */

    @Override
    public List<CommonRoleUser> findRoleUser(final String id) {
        // TODO Auto-generated method stub
        return createHqlQuery("from CommonRoleUser where roleId=?", id).list();
    }

	@Override
	public List<CommonRole> findAll() {
	       Criteria criteria = getSession().createCriteria(CommonRole.class);
	        return criteria.list();
	}
}
