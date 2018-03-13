package org.primejava.cms.controller;

import javax.inject.Inject;

import org.primejava.cms.service.FlowManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dashBoard")
public class DashBoardController {
	
	private FlowManagerService flowService;
	
	
	@RequestMapping("/dashBoard")
	public String dashBoard(Model model) {
//		Pager<ProcessDefinition> page=new Pager<ProcessDefinition>();
//		page.setPageSize(3);
//		flowService.findFlowDefinitions(page, null);
//		model.addAttribute("bpmProcesses",page.getResult());
	    return "dashboard/dashboard";
	}
	
	public FlowManagerService getFlowService() {
		return flowService;
	}
	@Inject
	public void setFlowService(FlowManagerService flowService) {
		this.flowService = flowService;
	}
}
