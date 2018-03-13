package org.primejava.cms.dao;

import org.primejava.basic.dao.BaseDao;
import org.primejava.cms.model.Enterprise;
import org.springframework.stereotype.Repository;

@Repository("enterpriseDao")
public class EnterpriseDaoImpl extends BaseDao<Enterprise> implements EnterpriseDao{

}
