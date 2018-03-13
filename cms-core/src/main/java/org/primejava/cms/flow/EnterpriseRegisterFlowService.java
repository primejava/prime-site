package org.primejava.cms.flow;

import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Enterprise;

public interface EnterpriseRegisterFlowService {
	 /**
     * 启动流程. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * 
     * @author xs xiaoshan@wisdombud.com
     * @param businessId 业务ID
     * @param userId 用户ID
     * @param college 学院
     * @param employType
     */
    void startFlow(String businessId);

    /**
     * 普通操作. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * 
     * @author xs xiaoshan@wisdombud.com
     * @param businessId 业务ID
     * @param userId 用户ID
     * @param variables 参数MAP
     */
    void handlerTask(String businessId, String userId, String messageId, Integer audit);

    /**
     * 审核记录，按申请时间倒序(老师). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * 
     * @author xs xiaoshan@wisdombud.com
     * @param page 分页对象
     * @param userId 用户ID
     */
    void findAuditFlows(Pager<Enterprise> page, String userId);

    /**
     * 已审核记录，按申请时间倒序(老师). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * 
     * @author xs xiaoshan@wisdombud.com
     * @param page 分页对象
     * @param userId 用户ID
     */
    void findAuditHistoryFlows(Pager<Enterprise> page, String userId);
}
