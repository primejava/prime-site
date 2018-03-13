package org.primejava.cms.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.log4j.Logger;
import org.primejava.basic.model.Pager;
import org.primejava.basic.query.PropertyFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("flowManagerService")
public class FlowManagerServiceImpl implements FlowManagerService {
	private static Logger logger = Logger
			.getLogger(FlowManagerServiceImpl.class);

	private RepositoryService repositoryService;

	private RuntimeService runtimeService;

	protected HistoryService historyService;

	@Override
	public Pager<ProcessDefinition> findFlowDefinitions(
			Pager<ProcessDefinition> page, List<PropertyFilter> propertyFilters) {
		ProcessDefinitionQuery processDefinitionQuery = (ProcessDefinitionQuery) this.repositoryService
				.createProcessDefinitionQuery().orderByProcessDefinitionId()
				.desc();
		page.setTotalCount((int) processDefinitionQuery.count());
		List<ProcessDefinition> results = processDefinitionQuery.listPage(
				(int) page.getStart(), page.getPageSize());
		page.setResult(results);
		return page;
	}

	@Override
	public void deleteFlow(String deploymentId) {
		try {
			this.repositoryService.deleteDeployment(deploymentId);
		} catch (Exception e) {
			logger.error("find flow instance run! delete flow file faild! ");
		}
	}

	
	@Override
	public void saveFlowDeploy(MultipartFile file) throws FileNotFoundException, IOException {
        String fileName = file.getOriginalFilename();
        repositoryService.createDeployment().addInputStream(fileName, file.getInputStream()).deploy();
	}
	
	
	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	@Inject
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public RuntimeService getRuntimeService() {
		return runtimeService;
	}

	@Inject
	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	public HistoryService getHistoryService() {
		return historyService;
	}

	@Inject
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	@Override
	public void findFlowInstances(Pager<HistoricProcessInstance> page,
			String defineId, String status) {
		HistoricProcessInstanceQuery query = (HistoricProcessInstanceQuery) this.historyService
				.createHistoricProcessInstanceQuery()
				.processDefinitionId(defineId)
				.orderByProcessInstanceStartTime().desc();
		if("running".equals(status)){
			query.unfinished();
		}else{
			query.finished();
		}
	    page.setTotalCount((int)query.count());
	    page.setResult(query.listPage((int)page.getStart(), page.getPageSize()));	
	}



}
