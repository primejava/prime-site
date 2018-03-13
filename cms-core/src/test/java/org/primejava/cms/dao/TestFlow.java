package org.primejava.cms.dao;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.inject.Inject;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primejava.basic.model.Pager;
import org.primejava.basic.test.util.AbstractDbUnitTestCase;
import org.primejava.cms.service.FlowManagerService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestFlow extends AbstractDbUnitTestCase {
	@Inject
	private RepositoryService repositoryService;

	@Inject
	private RuntimeService runtimeService;

	@Inject
	private TaskService taskService;

	@Inject
	private HistoryService historyService;

	@Inject
	private IdentityService identityService;

	@Inject
	private FlowManagerService iFlowManagerService;

	/**
	 * 部署流程资源：bpmn、form
	 */
	@Test
	public void deployResources() throws FileNotFoundException {
		// 部署流程
		String processFilePath = this.getClass().getClassLoader()
				.getResource("diagrams").getPath();
		File file = new File(processFilePath);
		File[] flows = file.listFiles();
		for (File flow : flows) {
			if (StringUtils.endsWith(flow.getName(), ".png")) {
				continue;
			}
			FileInputStream inputStream = new FileInputStream(flow.getPath());
			assertNotNull(inputStream);
			repositoryService.createDeployment()
					.addInputStream(flow.getName(), inputStream).deploy();
		}

	}

	@Test
	public void TestDeploy() throws FileNotFoundException {
		String processFilePath = this.getClass().getClassLoader()
				.getResource("diagrams").getPath();
		File file = new File(processFilePath);
		File[] flows = file.listFiles();
		for (File flow : flows) {
			if (StringUtils.endsWith(flow.getName(), ".png")) {
				continue;
			}
			// iFlowManagerService.saveFlowDeploy(flow);
		}
	}

	/**
	 * 分页查询所有流程
	 */
	@Test
	public void findPageFlowDefinitionsTest() {
		Pager<ProcessDefinition> pages = new Pager<ProcessDefinition>();
		pages.setPageNo(1);
		pages.setPageSize(5);
		pages = iFlowManagerService.findFlowDefinitions(pages, null);
		for (ProcessDefinition p : pages.getResult()) {
			System.out.println(p.getId());
		}
	}

	/**
	 * 删除一个流程
	 */
	@Test
	public void TestDeleteOneFlow() {
		String flowId = "1";
		iFlowManagerService.deleteFlow(flowId);
	}
	@Test
	public void testHistoricProcessInstance() {
		Pager<HistoricProcessInstance> page=new Pager<HistoricProcessInstance>();
		iFlowManagerService.findFlowInstances(page, "myProcess:1:17504", "running");
		for (HistoricProcessInstance p : page.getResult()) {
			System.out.println(p);
		}
	}

}
