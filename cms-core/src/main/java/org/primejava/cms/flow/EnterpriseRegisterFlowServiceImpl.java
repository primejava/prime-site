package org.primejava.cms.flow;

import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.HistoricIdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.query.NativeQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.NativeProcessInstanceQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.NativeTaskQuery;
import org.activiti.engine.task.Task;
import org.primejava.basic.model.Pager;
import org.primejava.cms.dao.EnterpriseDao;
import org.primejava.cms.model.Enterprise;
import org.primejava.cms.model.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

@Service("enterpriseRegisterFlowService")
public class EnterpriseRegisterFlowServiceImpl extends AbstractFlowService<Enterprise> implements EnterpriseRegisterFlowService{
    
	private final static String DEFINITIONKEY = "ENTERPRISE_REGISTER";

    @Autowired
    private EnterpriseDao       enterpriseDao;
    
	@Override
	public void startFlow(String businessId) {
		 this.startFlow(getDefinitionKey(DEFINITIONKEY), businessId, businessId, null);
	}

	@Override
	public void handlerTask(String businessId, String userId, String messageId,
			Integer audit) {
		 Map<String, Object> loaclVariables = Maps.newHashMap();
        loaclVariables.put(FlowContants.MESSAGE, messageId);
        loaclVariables.put(FlowContants.AUDIT, audit);
        this.handlerTask(getDefinitionKey(DEFINITIONKEY), businessId, userId, loaclVariables, (Map) Maps.newHashMap());
	}
	
	//查询需要审核的任务
	@Override
	public void findAuditFlows(Pager<Enterprise> page, String userId) {
		//这样查询的是直接分配给用户的，不行，要根据组来查询
        //this.findAuditFlows(getDefinitionKey(DEFINITIONKEY), page, userId);
		NativeTaskQuery query = (NativeTaskQuery) buildTaskQuery(false, userId,
				getDefinitionKey(DEFINITIONKEY));
		NativeTaskQuery countQuery = (NativeTaskQuery) buildTaskQuery(true,
				userId, getDefinitionKey(DEFINITIONKEY));
		page.setTotalCount((int) countQuery.count());
		// 获取到审核任务
		List<Task> tasks = query.listPage((int) page.getStart(),
				page.getPageSize());
		if (tasks.isEmpty()) {
			return;
		}
		// 把任务封装到业务对象中，封装的并不是任务而是根据任务获取到流程实例
		for (Task task : tasks) {
			ProcessInstance instance = runtimeService
					.createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).active()
					.singleResult();
			// Leave entity = getEntity(instance.getBusinessKey());
			AbstractAuditEntity entity = getEntity(instance.getBusinessKey());
			if (null == entity) {
				continue;
			}
			entity.setTask(task);
			page.getResult().add((Enterprise) entity);
		}

	}
	
	private NativeQuery buildTaskQuery(Boolean isCount, String userId,
			String definitionFlowKey) {
		// 主要的sql在下面，这里只是传参数
		NativeQuery taskQuery = taskService.createNativeTaskQuery().sql(
				builAuditerTaskSql(isCount));
		taskQuery.parameter("KEY", definitionFlowKey).parameter("BOOS_ID",
				userId);
		return taskQuery;
	}
	
	/**
	 * 查询审核人需要审核的任务，从Task表中查询，根据流程的key查询到liu
	 * 
	 * @param isCount
	 * @return
	 */
	private String builAuditerTaskSql(Boolean isCount) {
		StringBuilder sql = new StringBuilder(" SELECT ");
		sql.append(isCount ? " COUNT(task.ID_) " : " task.* ");
		sql.append(" FROM ");
		sql.append(managementService.getTableName(HistoricTaskInstance.class))
				.append(" task, ");// 历史任务（就是要审核的）
		sql.append(
				managementService.getTableName(HistoricProcessInstance.class))
				.append(" proc, ");// 历史流程实例
		sql.append(
				managementService.getTableName(ProcessDefinitionEntity.class))
				.append(" definition, ");
		sql.append(
				managementService
						.getTableName(HistoricIdentityLinkEntity.class))
				.append(" indet ");
		// sql.append(" t_user user ");
		sql.append(" WHERE task.PROC_INST_ID_ =proc.ID_ ");// 任务的流程实例ID
															// //下面是任务的审核人
		sql.append(" and task.ID_=indet.TASK_ID_ "
				+ "and (indet.USER_ID_=#{BOOS_ID} or indet.GROUP_ID_ in( select member.GROUP_ID_ from ACT_ID_MEMBERSHIP member WHERE member.USER_ID_=#{BOOS_ID} )) ");
		sql.append(" and definition.ID_  = proc.PROC_DEF_ID_  ");
		sql.append(" and definition.SUSPENSION_STATE_ = 1  ");
		sql.append(" and definition.KEY_ = #{KEY} ");// 根据key找到流程定义
		sql.append(" and task.END_TIME_ is NULL ");// 要求是进行中的未审核的

		// 为了一些条件查询
		// sql.append(" and proc.BUSINESS_KEY_=leave.id ");
		// sql.append(" and leave.uid=user.id ");
		// sql.append(" and user.name=#{USERNAME} ");
		System.out.println(sql);
		return sql.toString();
	}
	
	/**
	 * @param isCount
	 *            是否是为了统计数目
	 * @param userId
	 * @return
	 */
	private NativeProcessInstanceQuery historyQuery(boolean isCount,
			String userId) {
		NativeProcessInstanceQuery taskQuery = runtimeService
				.createNativeProcessInstanceQuery().sql(
						buildAuditTaskSql(isCount));
		taskQuery.parameter("KEY", getDefinitionKey(DEFINITIONKEY));
		taskQuery.parameter("APPLY_USER", FlowContants.APPLY_USER).parameter(
				"USER_ID", userId);
		return taskQuery;
	}

	/**
	 * 解释：结果就是获得了这个用户的所有流程实例（如果不传入Key的话就是所有，传入key，就仅仅是这个请假流程的所有流程实例）
	 * 
	 * @param isCount
	 * @return
	 */
	private String buildAuditTaskSql(boolean isCount) {
		StringBuilder sql = new StringBuilder(" SELECT ");
		sql.append(isCount ? " COUNT(DISTINCT exec.ID_) " : " DISTINCT exec.* ");
		sql.append(" FROM ");
		sql.append(managementService.getTableName(Execution.class)).append(
				" exec, ");
		sql.append(managementService.getTableName(VariableInstanceEntity.class))
				.append(" vari, ");
		sql.append(
				managementService.getTableName(ProcessDefinitionEntity.class))
				.append(" definition ");
		sql.append(" WHERE exec.PROC_DEF_ID_ =definition.ID_ ");// 1.1 根据流程定义来查
		sql.append(" AND exec.PROC_INST_ID_ = vari.PROC_INST_ID_ ");// 1.2
																	// 根据流程实例来查
		sql.append(" AND exec.PARENT_ID_ IS NULL ");// 这一步又是要限定什么？

		sql.append(" AND definition.KEY_ = #{KEY} "); // 2.1 根据key获得流程定义ID
		sql.append(" AND definition.SUSPENSION_STATE_ = 1 ");// 2.2 要求不能挂起

		sql.append(" AND vari.NAME_ = #{APPLY_USER} AND vari.TEXT_ = #{USER_ID} "); // 3.1根据用户ID获得流程实例ID
		sql.append(" AND vari.EXECUTION_ID_ = vari.PROC_INST_ID_ ");// 这一句是为了限定什么???

		sql.append(" ORDER BY exec.ID_ DESC");
		return sql.toString();
	}

	/**
	 * 把查询出来的所有流程实例封装成业务对象的集合
	 * 
	 * @param page
	 * @param query
	 */
	private void buildPageProcessTransformObject(Pager<Enterprise> page,
			NativeProcessInstanceQuery query) {
		List<ProcessInstance> processInstances = query.listPage(
				(int) page.getStart(), page.getPageSize());
		for (ProcessInstance processInstance : processInstances) {
			// 从act_ru_execution找到对应的act_hi_procinst
			HistoricProcessInstance instance = historyService
					.createHistoricProcessInstanceQuery()
					.processInstanceId(processInstance.getId()).singleResult();
			if (null == instance) {
				continue;
			}
			// 找到了这条流程实例对应的业务对象了
			AbstractAuditEntity entity = enterpriseDao.load(instance
					.getBusinessKey());
			if (null == entity) {
				continue;
			}
			Task task = taskService.createTaskQuery()
					.processInstanceId(processInstance.getId()).active()
					.singleResult();
			entity.setTask(task);
			page.getResult().add((Enterprise) entity);
		}
	}

	@Override
	public void findAuditHistoryFlows(Pager<Enterprise> page, String userId) {
        this.findAuditHistoryFlows(getDefinitionKey(DEFINITIONKEY), page, userId);

	}

	@Override
	protected Enterprise getEntity(String id) {
		AbstractAuditEntity entity = enterpriseDao.load(id);
		if (null == entity) {
			return null;
		}
		return (Enterprise) entity;
	}

}
