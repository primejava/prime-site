package org.primejava.cms.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.NativeHistoricTaskInstanceQuery;
import org.activiti.engine.impl.persistence.entity.HistoricIdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.query.NativeQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.NativeProcessInstanceQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.NativeTaskQuery;
import org.activiti.engine.task.Task;
import org.primejava.basic.model.Pager;
import org.primejava.cms.dao.LeaveDao;
import org.primejava.cms.model.Leave;
import org.primejava.cms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("leaveFlowService")
public class LeaveFlowServiceImpl extends AbstractFlowService<Leave> implements
		LeaveFlowService {
	private final static String DEFINITIONKEY = "myProcess";
	@Autowired
	private LeaveDao leaveDao;

	@Override
	public void startFlow(String businessId, String userId) {
		Map<String, Object> variables = new HashMap<String, Object>();
		this.startFlow(DEFINITIONKEY, businessId, userId, variables);
	}

	@Override
	protected Leave getEntity(String id) {
		AbstractAuditEntity entity = leaveDao.load(id);
		if (null == entity) {
			return null;
		}
		return (Leave) entity;
	}

	@Override
	public void findApplyFlow(Pager<Leave> page, String userId) {
		NativeProcessInstanceQuery query = historyQuery(false, userId);
		NativeProcessInstanceQuery countQuery = historyQuery(true, userId);
		page.setTotalCount((int) countQuery.count());
		buildPageProcessTransformObject(page, query);
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
	private void buildPageProcessTransformObject(Pager<Leave> page,
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
			AbstractAuditEntity entity = leaveDao.load(instance
					.getBusinessKey());
			if (null == entity) {
				continue;
			}
			Task task = taskService.createTaskQuery()
					.processInstanceId(processInstance.getId()).active()
					.singleResult();
			entity.setTask(task);
			page.getResult().add((Leave) entity);
		}
	}

	@Override
	public void findAuditFlows(Pager<Leave> page, String userId) {
		NativeTaskQuery query = (NativeTaskQuery) buildTaskQuery(false, userId,
				getDefinitionkey());
		NativeTaskQuery countQuery = (NativeTaskQuery) buildTaskQuery(true,
				userId, getDefinitionkey());
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
			page.getResult().add((Leave) entity);
			// page.getResult().add(entity);
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

	public static String getDefinitionkey() {
		return DEFINITIONKEY;
	}

	@Override
	public void addTaskComment(String leaveId, String userId, String comment) {
		this.addTaskComment(getDefinitionkey(), leaveId, userId, comment);
	}

	@Override
	public void auditTask(String leaveId, String userId, String noticeId,
			Integer audit) {
		String definitionFlowKey = getDefinitionkey();
		Map<String, Object> loaclVariables = new HashMap<String, Object>();
		loaclVariables.put(FlowContants.AUDIT, audit);
	    loaclVariables.put(FlowContants.MESSAGE, noticeId);
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(FlowContants.AUDIT, audit);
		this.handlerTask(definitionFlowKey, leaveId, userId, loaclVariables,
				variables);
	}

	@Override
	public Leave findLeaveById(String id) {
		Leave leave = getEntity(id);
		// ProcessInstance processInstance =
		// runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(leave.getId()).singleResult();
		Task task = taskService.createTaskQuery()
				.processInstanceBusinessKey(leave.getId()).singleResult();
		leave.setTask(task);
		return leave;
	}

	@Override
	public List<Comment> getTaskComments(String businessId) {
		String definitionFlowKey = getDefinitionkey();
		return this.getTaskComments(definitionFlowKey, businessId);
	}

	// 退回后重新提交申请,有bug
	@Override
	public void operateTask(String businessId, String userId) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(FlowContants.AUDIT, FlowStatusEnum.APPLY.getValue());
		this.handlerAssignTask(getDefinitionkey(), businessId,
				userId, variables);
	}

	@Override
	public Pager<Leave> findApplyHistories(Pager<Leave> page, User user) {
        String definitionFlowKey = getDefinitionkey();
        this.findApplyHistoryFlows(definitionFlowKey, page, user.getId());
		return page;
	}
	

    private String buildCollegeTeacherTaskSql(Boolean isCount, Leave leave) {
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql.append(isCount ? " COUNT(task.ID_) " : " task.* ");
        sql.append(" FROM ");
        sql.append(managementService.getTableName(HistoricProcessInstance.class)).append(" proc, ");
        sql.append(managementService.getTableName(ProcessDefinitionEntity.class)).append(" definition, ");
        sql.append(managementService.getTableName(HistoricTaskInstance.class)).append(" task, ");
        sql.append(managementService.getTableName(HistoricIdentityLinkEntity.class)).append(" indet, ");
        sql.append(" t_user student, t_leave employ ");
        sql.append(" WHERE definition.ID_ = proc.PROC_DEF_ID_ and definition.SUSPENSION_STATE_ = 1 ");
        sql.append(" and proc.ID_=task.PROC_INST_ID_ and proc.BUSINESS_KEY_=employ.id and student.id=employ.uid ");
        sql.append(" and definition.KEY_ = #{KEY} ");
        if (leave.getStatus() == FlowStatusEnum.APPLY.getValue()) {
            sql.append(" and task.END_TIME_ is NULL ");
        }
        sql.append(" and task.ID_=indet.TASK_ID_ and (indet.USER_ID_=#{USER_ID} or indet.GROUP_ID_ in( select member.GROUP_ID_ from ACT_ID_MEMBERSHIP member WHERE member.USER_ID_=#{USER_ID} )) ");
        if (leave.getStatus() != FlowStatusEnum.APPLY.getValue()) {
            sql.append(" and proc.ID_ in (select var.PROC_INST_ID_ from ACT_HI_VARINST var where task.ID_=var.TASK_ID_ and var.NAME_=#{AUDIT} and var.TEXT_=#{STATUS}) ");
        }  
        
        return sql.toString();
    }

    private NativeQuery buildTeacherTaskQuery(Boolean isCount,  String userId, Leave leave,
            String definitionFlowKey) {
        NativeQuery taskQuery = taskService.createNativeTaskQuery().sql(buildCollegeTeacherTaskSql(isCount,
        		leave));
        if (leave.getStatus() != FlowStatusEnum.APPLY.getValue()) {
            taskQuery = historyService.createNativeHistoricTaskInstanceQuery()
                    .sql(buildCollegeTeacherTaskSql(isCount, leave));
        }
        taskQuery.parameter("KEY", definitionFlowKey).parameter("USER_ID", userId);
        if (leave.getStatus() != FlowStatusEnum.APPLY.getValue()) {
            taskQuery.parameter("AUDIT", FlowContants.AUDIT).parameter("STATUS", leave.getStatus().toString());
        }
        return taskQuery;
    }

	@Override
	public void findAuditHistories(Pager<Leave> page,Leave leave, String userId) {
		 String definitionFlowKey = getDefinitionkey();		
	     NativeHistoricTaskInstanceQuery query = (NativeHistoricTaskInstanceQuery) buildTeacherTaskQuery(false, userId, leave,definitionFlowKey);
	     NativeHistoricTaskInstanceQuery countQuery = (NativeHistoricTaskInstanceQuery) buildTeacherTaskQuery(true, userId,leave, definitionFlowKey);
	     page.setTotalCount((int) countQuery.count());
	        List<HistoricTaskInstance> tasks = query.listPage((int) page.getStart(), page.getPageSize());
	        if (tasks.isEmpty()) {
	            return;
	        }
	      page.setResult(new ArrayList<Leave>());
	      for (HistoricTaskInstance task : tasks) {
	            HistoricProcessInstance instance = historyService.createHistoricProcessInstanceQuery()
	                    .processDefinitionKey(definitionFlowKey).processInstanceId(task.getProcessInstanceId())
	                    .singleResult();
	            if (null == instance) {
	                continue;
	            }
	 
				// 找到了这条流程实例对应的业务对象了
				AbstractAuditEntity entity = leaveDao.load(instance
						.getBusinessKey());          
	            if (null == entity) {
	                continue;
	            }
	            entity.setAuditTime(task.getEndTime());
	            HistoricVariableInstance veriableInstance = historyService.createHistoricVariableInstanceQuery()
	                    .processInstanceId(instance.getId()).taskId(task.getId()).variableName(FlowContants.AUDIT)
	                    .singleResult();
	            if (null != veriableInstance) {
	                entity.setAudit(Integer.parseInt(veriableInstance.getValue().toString()));
	            }
	            page.getResult().add((Leave) entity);
	        }	     
	    		 
	}

}
