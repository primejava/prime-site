package org.primejava.cms.service;

import org.apache.commons.lang3.StringUtils;
import org.primejava.basic.model.Pager;
import org.primejava.cms.dao.EnterpriseDao;
import org.primejava.cms.flow.EnterpriseRegisterFlowService;
import org.primejava.cms.flow.FlowStatusEnum;
import org.primejava.cms.model.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("enterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService{
	
    @Autowired
    private EnterpriseDao                 enterpriseDao;
    
    @Autowired
    private EnterpriseRegisterFlowService enterpriseRegisterFlowService;

	@Override
	public String loginUserVerification(String userName, String password) {
		
		return null;
	}

	@Override
	public void findAuditRegister(Pager<Enterprise> page,String userId) {
		enterpriseRegisterFlowService.findAuditFlows(page, userId);
	}

	@Override
	public void findAuditRegisterHistories(Pager<Enterprise> page,String userId) {
		enterpriseRegisterFlowService.findAuditHistoryFlows(page, userId);
	}

	@Override
	public Enterprise saveOrUpdateEnterprise(Enterprise enterprise) {
		if(StringUtils.isBlank(enterprise.getId())){
			enterprise.setStatus(FlowStatusEnum.APPLY.getValue());	
		}
		Enterprise oldEnterprise=enterpriseDao.saveOrUpdate(enterprise);
		enterpriseRegisterFlowService.startFlow(oldEnterprise.getId());
		return oldEnterprise;
	}

}
