package org.primejava.cms.service;

import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Enterprise;

public interface EnterpriseService extends LoginService{
    /**
     * 查询待审核的单位注册记录
     * 
     * @author xs
     * @param page
     */
    void findAuditRegister(Pager<Enterprise> page,String userId);
    /**
     * 审核单位注册的历史记录. <br/>
     * 
     * @author xs
     * @param page
     */
    void findAuditRegisterHistories(Pager<Enterprise> page,String userId);
    
    /**
     * 保存一个单位的注册信息. <br/>
     * 
     * @author mmzhao
     * @param Enterprise
     * @return
     * @throws IOException
     */
    Enterprise saveOrUpdateEnterprise(Enterprise enterprise);
}
