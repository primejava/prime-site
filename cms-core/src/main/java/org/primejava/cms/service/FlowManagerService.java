package org.primejava.cms.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.primejava.basic.model.Pager;
import org.primejava.basic.query.PropertyFilter;
import org.springframework.web.multipart.MultipartFile;


public interface FlowManagerService {
	/**
	 * 分页查询流程定义
	 * @param page
	 * @param propertyFilters
	 * @return
	 */
	public abstract Pager<ProcessDefinition> findFlowDefinitions(Pager<ProcessDefinition> page, List<PropertyFilter> propertyFilters);
	/**
	 * 删除一个流程
	 * @param id
	 */
	  public abstract void deleteFlow(String id);
//	  
//	  public abstract void saveFlowDeploy(String paramString1, String paramString2);
//	  
	 /**
	  * 部署一个流程
	  * @param flow
	 * @throws FileNotFoundException 
	 * @throws IOException 
	  */
	public abstract void saveFlowDeploy(MultipartFile flow) throws FileNotFoundException, IOException;
	/**
	 * 根据流程定义查询流程实例
	 * @param page
	 * @param defineId
	 * @param status
	 */
	public abstract void findFlowInstances(Pager<HistoricProcessInstance> page,
			String defineId, String status);

//	  public abstract void deleteInstance(String paramString1, String paramString2);


}
