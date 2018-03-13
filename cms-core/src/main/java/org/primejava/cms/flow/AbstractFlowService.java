package org.primejava.cms.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.query.NativeQuery;
import org.activiti.engine.query.Query;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primejava.basic.model.Pager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ClassName: AbstractFlowService. <br/>
 * Function: TODO <br/>
 * date: 2014年8月4日 下午7:42:44 <br/>
 * 
 * @author xs xiaoshan@wisdombud.com
 * @version
 * @since JDK 1.6
 */
@SuppressWarnings("hiding")
public abstract class AbstractFlowService<T> {

	protected static Logger logger = Logger
			.getLogger(AbstractFlowService.class);

	@Autowired
	protected RepositoryService repositoryService;

	@Autowired
	protected RuntimeService runtimeService;

	@Autowired
	protected TaskService taskService;

	@Autowired
	protected HistoryService historyService;

	@Autowired
	protected IdentityService identityService;

	@Autowired
	protected ManagementService managementService;

	/**
	 * 开始流程. <br/>
	 * 
	 * @author xs
	 * @param entityId
	 *            实体ID
	 */
	protected void startFlow(String definitionFlowKey, String businessId,
			String userId, Map<String, Object> variables) {
		identityService.setAuthenticatedUserId(userId);
		ProcessInstance processInstance = null;
		if (variables == null || variables.isEmpty()) {
			processInstance = runtimeService.startProcessInstanceByKey(
					definitionFlowKey, businessId);
		} else {
			processInstance = runtimeService.startProcessInstanceByKey(
					definitionFlowKey, businessId, variables);
		}
		logger.info("流程启动ID:" + processInstance.getId());
	}

	/**
	 * 处理任务. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param businessId
	 * @param userId
	 * @param loaclVariables
	 *            任务范围参数
	 * @param variables
	 *            流程参数
	 */
	protected void handlerTask(String definitionFlowKey, String businessId,
			String userId, Map<String, Object> loaclVariables,
			Map<String, Object> variables) {
		// TODO Auto-generated method stub
		handlerTask(definitionFlowKey, businessId, userId, loaclVariables,
				variables, "");
	}

	protected void handlerTask(String definitionFlowKey, String businessId,
			String userId, Map<String, Object> loaclVariables,
			Map<String, Object> variables, String comment) {
		// TODO Auto-generated method stub
		Task task = taskService.createTaskQuery()
				.processDefinitionKey(definitionFlowKey)
				.taskCandidateUser(userId)
				.processInstanceBusinessKey(businessId).active().singleResult();
		if (null == task) {
			logger.error(String.format("未发现%s的待处理任务，业务ID: %s", userId,
					businessId));
			return;
		}
		if (StringUtils.isNotBlank(comment)) {
			taskService.addComment(task.getId(), task.getProcessInstanceId(),
					comment);
		}
		taskService.claim(task.getId(), userId);
		if (null != loaclVariables) {
			taskService.setVariablesLocal(task.getId(), loaclVariables);
		}
		taskService.complete(task.getId(), variables);
	}

	/**
	 * 处理任务. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param businessId
	 * @param userId
	 * @param variables
	 *            流程参数
	 */
	protected void handlerTask(String definitionFlowKey, String businessId,
			String userId, Map<String, Object> variables) {
		// TODO Auto-generated method stub
		handlerTask(definitionFlowKey, businessId, userId, variables, "");
	}

	/**
	 * 处理任务. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param businessId
	 * @param userId
	 * @param variables
	 *            流程参数
	 */
	protected void handlerTask(String definitionFlowKey, String businessId,
			String userId, Map<String, Object> variables, String comment) {
		// TODO Auto-generated method stub
		Task task = taskService.createTaskQuery()
				.processDefinitionKey(definitionFlowKey)
				.taskCandidateUser(userId)
				.processInstanceBusinessKey(businessId).active().singleResult();
		if (null == task) {
			logger.error(String.format("未发现%s的待处理任务，业务ID: %s", userId,
					businessId));
			return;
		}
		if (StringUtils.isNotBlank(comment)) {
			taskService.addComment(task.getId(), task.getProcessInstanceId(),
					comment);
		}
		taskService.claim(task.getId(), userId);
		taskService.complete(task.getId(), variables);
	}

	/**
	 * 申请人的申请记录（包含结束和未结束的流程），带参数查询. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param page
	 * @param userId
	 * @param variables
	 */
	protected void findApplyFlows(String definitionFlowKey, Pager<T> page,
			String userId, Map<String, Object> variables) {
		// TODO Auto-generated method stub
		HistoricProcessInstanceQuery query = historyService
				.createHistoricProcessInstanceQuery().processDefinitionKey(
						definitionFlowKey);
		if (null != variables && !variables.isEmpty()) {
			for (Map.Entry<String, Object> entry : variables.entrySet()) {
				query.variableValueEquals(entry.getKey(), entry.getValue());
			}
		}
		query.variableValueEquals(FlowContants.APPLY_USER, userId);
		query.orderByProcessInstanceStartTime().desc();
		buildPageInstanceTransformObject(page, query);
	}

	/**
	 * 指定处理人处理任务
	 * 
	 * @param definitionFlowKey
	 * @param businessId
	 * @param userId
	 * @param variables
	 */
	protected void handlerAssignTask(String definitionFlowKey,
			String businessId, String userId, Map<String, Object> variables) {
		handlerAssignTask(definitionFlowKey, businessId, userId, null,
				variables);
	}

	/**
	 * 指定处理人处理任务
	 * 
	 * @param definitionFlowKey
	 * @param businessId
	 * @param userId
	 * @param loaclVariables
	 * @param variables
	 */
	protected void handlerAssignTask(String definitionFlowKey,
			String businessId, String userId,
			Map<String, Object> loaclVariables, Map<String, Object> variables) {
		// TODO Auto-generated method stub
		Task task = taskService.createTaskQuery()
				.processDefinitionKey(definitionFlowKey)
				.processInstanceBusinessKey(businessId).taskAssignee(userId)
				.active().singleResult();
		if (null == task) {
			logger.error(String.format("未发现%s的任务，业务ID: %s", userId, businessId));
			return;
		}
		taskService.claim(task.getId(), userId);
		taskService.setVariablesLocal(task.getId(), loaclVariables);
		taskService.complete(task.getId(), variables);
	}

	/**
	 * 申请人的申请记录. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param page
	 * @param userId
	 */
	protected void findApplyFlows(String definitionFlowKey, Pager<T> page,
			String userId) {
		// TODO Auto-generated method stub
		findApplyFlows(definitionFlowKey, page, userId, null);
	}

	/**
	 * 申请正在运行的记录（只有正在运行的流程），带参数. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param page
	 * @param userId
	 * @param variables
	 */
	protected void findApplyAuditingFlow(String definitionFlowKey,
			Pager<T> page, String userId, Map<String, Object> variables) {
		// TODO Auto-generated method stub
		ProcessInstanceQuery query = runtimeService
				.createProcessInstanceQuery()
				.processDefinitionKey(definitionFlowKey).active();
		if (null != variables && !variables.isEmpty()) {
			for (Map.Entry<String, Object> entry : variables.entrySet()) {
				query.variableValueEquals(entry.getKey(), entry.getValue());
			}
		}
		query.variableValueEquals(FlowContants.APPLY_USER, userId);
		query.orderByProcessInstanceId().desc();
		buildPageInstanceTransformObject(page, query);
	}

	/**
	 * 申请正在运行的记录（只有正在运行的流程）. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param page
	 * @param userId
	 */
	protected void findApplyAuditingFlow(String definitionFlowKey,
			Pager<T> page, String userId) {
		// TODO Auto-generated method stub
		findApplyAuditingFlow(definitionFlowKey, page, userId, null);
	}

	/**
	 * 申请人的申请的历史记录(所有结束的流程)，带参数查询. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param page
	 * @param userId
	 * @param variables
	 */
	protected void findApplyHistoryFlows(String definitionFlowKey,
			Pager<T> page, String userId, Map<String, Object> variables) {
		// TODO Auto-generated method stub
		HistoricProcessInstanceQuery query = historyService
				.createHistoricProcessInstanceQuery().involvedUser(userId)
				.finished().orderByProcessInstanceStartTime().desc();
		if (StringUtils.isNotBlank(definitionFlowKey)) {
			query.processDefinitionKey(definitionFlowKey);
		}
		if (null != variables && !variables.isEmpty()) {
			for (Map.Entry<String, Object> entry : variables.entrySet()) {
				query.variableValueEquals(entry.getKey(), entry.getValue());
			}
		}
		buildPageInstanceTransformObject(page, query);
	}

	/**
	 * 申请人的申请的历史记录(所有结束的流程). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param page
	 * @param userId
	 */
	protected void findApplyHistoryFlows(String definitionFlowKey,
			Pager<T> page, String userId) {
		// TODO Auto-generated method stub
		findApplyHistoryFlows(definitionFlowKey, page, userId, null);
	}

	/**
	 * 审核人需要处理的流程. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param page
	 * @param userId
	 */
	protected void findAuditFlows(String definitionFlowKey, Pager<T> page,
			String userId) {
		findAuditFlows(definitionFlowKey, page, userId, null);
	}

	/**
	 * 审核人需要处理的流程(带参数). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param page
	 * @param userId
	 * @param variables
	 */
	protected void findAuditFlows(String definitionFlowKey, Pager<T> page,
			String userId, Map<String, Object> variables) {
		findAuditFlows(definitionFlowKey, page, userId, variables, null);
	}

	/**
	 * 审核人需要处理的流程(带参数). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param page
	 * @param userId
	 * @param variables
	 *            包含的参数条件
	 * @param notEqualVariables
	 *            不包含的参数条件
	 */
	protected void findAuditFlows(String definitionFlowKey, Pager<T> page,
			String userId, Map<String, Object> variables,
			Map<String, Object> notEqualVariables) {
		// TODO Auto-generated method stub
		TaskQuery query = taskService.createTaskQuery()
				.processDefinitionKey(definitionFlowKey)
				.taskCandidateUser(userId).active().orderByTaskCreateTime()
				.desc();
		if (null != variables && !variables.isEmpty()) {
			for (Map.Entry<String, Object> entry : variables.entrySet()) {
				query.processVariableValueEquals(entry.getKey(),
						entry.getValue());
			}
		}
		if (null != notEqualVariables && !notEqualVariables.isEmpty()) {
			for (Map.Entry<String, Object> entry : notEqualVariables.entrySet()) {
				query.processVariableValueNotEquals(entry.getKey(),
						entry.getValue());
			}
		}
		buildPageTaskTransformObject(page, query);
	}

	protected void buildPageTaskTransformObject(Pager<T> page, Query query) {
		page.setTotalCount((int) query.count());
		List<Task> tasks = query.listPage((int) page.getStart(),
				page.getPageSize());
		buildPageFillTask(page, tasks);
	}

	protected void buildPageTaskTransformObject(Pager<T> page, NativeQuery query) {
		List<Task> tasks = query.listPage((int) page.getStart(),
				page.getPageSize());
		buildPageFillTask(page, tasks);
	}

	private void buildPageFillTask(Pager<T> page, List<Task> tasks) {
		for (Task task : tasks) {
			ProcessInstance instance = runtimeService
					.createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).active()
					.singleResult();
			AbstractAuditEntity entity = (AbstractAuditEntity) getEntity(instance
					.getBusinessKey());
			if (null == entity) {
				continue;
			}
			page.getResult().add((T) entity);
		}
	}

	/**
	 * 审核人处理过的流程. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param page
	 * @param userId
	 */
	protected void findAuditHistoryFlows(String definitionFlowKey,
			Pager<T> page, String userId) {
		findAuditHistoryFlows(definitionFlowKey, page, userId, null);
	}

	/**
	 * 审核人处理过的流程(带参数). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param page
	 * @param userId
	 * @param variables
	 */
	protected void findAuditHistoryFlows(String definitionFlowKey,
			Pager<T> page, String userId, Map<String, Object> localVariables) {
		findAuditHistoryFlows(definitionFlowKey, page, userId, localVariables,
				null);
	}

	/**
	 * 审核人处理过的流程(带参数). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param page
	 * @param userId
	 * @param variables
	 *            包含的参数条件
	 * @param notEqualVariables
	 *            不包含的参数条件
	 */
	protected void findAuditHistoryFlows(String definitionFlowKey,
			Pager<T> page, String userId, Map<String, Object> localVariables,
			Map<String, Object> variables) {
		// TODO Auto-generated method stub
		HistoricTaskInstanceQuery query = historyService
				.createHistoricTaskInstanceQuery()
				.processDefinitionKey(definitionFlowKey)
				.orderByHistoricTaskInstanceEndTime().desc();
		if (StringUtils.isNotBlank(userId)) {
			query.taskAssignee(userId);
		}
		if (null != localVariables && !localVariables.isEmpty()) {
			for (Map.Entry<String, Object> entry : localVariables.entrySet()) {
				query.taskVariableValueEquals(entry.getKey(), entry.getValue());
			}
		}
		if (null != variables && !variables.isEmpty()) {
			for (Map.Entry<String, Object> entry : variables.entrySet()) {
				query.processVariableValueEquals(entry.getKey(),
						entry.getValue());
			}
		}
		buildPageHistoricTaskTransformObject(page, query);
	}

	protected void buildPageHistoricTaskTransformObject(Pager<T> page,
			Query query) {
		page.setTotalCount((int) query.count());
		List<HistoricTaskInstance> taskInstances = query.listPage(
				(int) page.getStart(), page.getPageSize());
		buildPageFillHistoricTask(page, taskInstances);
	}

	protected void buildPageHistoricTaskTransformObject(Pager<T> page,
			NativeQuery query) {
		List<HistoricTaskInstance> taskInstances = query.listPage(
				(int) page.getStart(), page.getPageSize());
		buildPageFillHistoricTask(page, taskInstances);
	}

	private void buildPageFillHistoricTask(Pager<T> page,
			List<HistoricTaskInstance> taskInstances) {
		for (HistoricTaskInstance taskInstance : taskInstances) {
			HistoricProcessInstance instance = historyService
					.createHistoricProcessInstanceQuery()
					.processInstanceId(taskInstance.getProcessInstanceId())
					.singleResult();
			if (null == instance) {
				continue;
			}
			AbstractAuditEntity entity = (AbstractAuditEntity) getEntity(instance
					.getBusinessKey());
			if (null == entity) {
				continue;
			}
			entity.setAuditTime(taskInstance.getEndTime());
			entity.setAuditer(taskInstance.getAssignee());
			entity.setComments(taskService.getTaskComments(taskInstance.getId()));
			HistoricVariableInstance veriableInstance = historyService
					.createHistoricVariableInstanceQuery()
					.processInstanceId(instance.getId())
					.taskId(taskInstance.getId())
					.variableName(FlowContants.AUDIT).singleResult();
			if (null != veriableInstance) {
				entity.setAudit(Integer.parseInt(veriableInstance.getValue()
						.toString()));
			}
			try {
				page.getResult().add((T) entity.clone());
			} catch (CloneNotSupportedException e) {
				logger.error("clone object faild !", e);
			}
		}
	}

	protected void buildPageInstanceTransformObject(Pager<T> page,
			NativeQuery query) {
		List<Object> processInstances = query.listPage((int) page.getStart(),
				page.getPageSize());
		buildPageFillHistoricProcessInstances(page, processInstances);
	}

	protected void buildPageInstanceTransformObject(Pager<T> page, Query query) {
		page.setTotalCount((int) query.count());
		List<Object> processInstances = query.listPage((int) page.getStart(),
				page.getPageSize());
		buildPageFillHistoricProcessInstances(page, processInstances);
	}

	private void buildPageFillHistoricProcessInstances(Pager<T> page,
			List<Object> processInstances) {
		if (processInstances.isEmpty()) {
			return;
		}
		page.setResult(new ArrayList<T>());
		for (Object object : processInstances) {
			String businessId = "";
			if (object instanceof ProcessInstance) {
				ProcessInstance instance = (ProcessInstance) object;
				businessId = instance.getBusinessKey();
			}
			if (object instanceof HistoricProcessInstance) {
				HistoricProcessInstance instance = (HistoricProcessInstance) object;
				businessId = instance.getBusinessKey();
			}
			AbstractAuditEntity entity = (AbstractAuditEntity) getEntity(businessId);
			if (null == entity) {
				continue;
			}
			if (object instanceof ProcessInstance) {
				ProcessInstance instance = (ProcessInstance) object;
				Task task = taskService.createTaskQuery()
						.processInstanceId(instance.getId()).active()
						.singleResult();
				entity.setTask(task);
			}
			if (object instanceof HistoricProcessInstance) {
				HistoricProcessInstance instance = (HistoricProcessInstance) object;
				HistoricTaskInstance task = historyService
						.createHistoricTaskInstanceQuery()
						.processInstanceId(instance.getId())
						.orderByHistoricTaskInstanceEndTime().desc().finished()
						.list().isEmpty() ? null : historyService
						.createHistoricTaskInstanceQuery()
						.processInstanceId(instance.getId())
						.orderByHistoricTaskInstanceEndTime().desc().finished()
						.list().get(0);
				if (task != null) {
					entity.setAuditTime(task.getEndTime());
					entity.setAuditer(task.getAssignee());
					entity.setComments(taskService.getTaskComments(task.getId()));
					HistoricVariableInstanceQuery variableQuery = historyService
							.createHistoricVariableInstanceQuery()
							.processInstanceId(instance.getId())
							.taskId(task.getId())
							.variableName(FlowContants.AUDIT);
					HistoricVariableInstance veriableInstance = variableQuery
							.singleResult();
					if (null == veriableInstance) {
						List<HistoricVariableInstance> veriables = historyService
								.createHistoricVariableInstanceQuery()
								.processInstanceId(instance.getId())
								.variableName(FlowContants.AUDIT).list();
						if (!veriables.isEmpty()) {
							veriableInstance = veriables.get(0);
						}
					}
					if (null != veriableInstance) {
						entity.setAudit(Integer.parseInt(veriableInstance
								.getValue().toString()));
					}
				}
			}
			page.getResult().add((T) entity);
		}
	}

	/**
	 * 获得流程实例的业务对象 <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param id
	 * @return
	 */
	protected abstract T getEntity(String id);

	/**
	 * 获得流程对应的KEY. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @return
	 */
	public String getDefinitionKey(String definitionKey) {
		return definitionKey;
	}

	/**
	 * 增加流程备注. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param businessId
	 * @param userId
	 * @param comment
	 */
	public void addTaskComment(String definitionFlowKey, String businessId,
			String userId, String comment) {
		Task task = taskService.createTaskQuery()
				.processDefinitionKey(definitionFlowKey)
				.taskCandidateUser(userId)
				.processInstanceBusinessKey(businessId).active().singleResult();
		if (null == task) {
			logger.error(String.format("未发现%s的待处理任务，业务ID: %s", userId,
					businessId));
			return;
		}
		Authentication.setAuthenticatedUserId(userId);
		taskService.addComment(task.getId(), task.getProcessInstanceId(),
				definitionFlowKey, comment);
	}

	/**
	 * 获取流程所有备注信息. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author xs
	 * @param definitionFlowKey
	 * @param businessId
	 * @return
	 */
	public List<Comment> getTaskComments(String definitionFlowKey,
			String businessId) {
		HistoricProcessInstance processInstance = historyService
				.createHistoricProcessInstanceQuery()
				.processDefinitionKey(definitionFlowKey)
				.processInstanceBusinessKey(businessId).singleResult();
		List<Comment> comments = taskService
				.getProcessInstanceComments(processInstance.getId());
		if (null == comments) {
			return new ArrayList<Comment>();
		}
		return comments;
	}

	/**
	 * 功能: 根据businessId删除流程实例.<br/>
	 * date: 2015年8月20日 下午2:20:47 <br/>
	 * 
	 * @author xgliu@wisdombud.com
	 * @param definitionFlowKey
	 * @param businessId
	 */
	protected void deleteFlowInstance(String definitionFlowKey,
			String businessId) {
		List<HistoricProcessInstance> processInstances = historyService
				.createHistoricProcessInstanceQuery()
				.processDefinitionKey(definitionFlowKey)
				.processInstanceBusinessKey(businessId).list();
		if (null != processInstances && processInstances.size() > 0)
			for (HistoricProcessInstance processInstance : processInstances) {
				historyService.deleteHistoricProcessInstance(processInstance
						.getId());
			}
	}
}
