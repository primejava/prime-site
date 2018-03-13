package org.primejava.cms.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.primejava.basic.model.Pager;
import org.primejava.basic.query.PropertyFilter;
import org.primejava.cms.service.FlowManagerService;
import org.primejava.cms.spring.MessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//springmvc的数据传递还是按照孔浩的来好，页面就按照这个人的来
@Controller
@RequestMapping("/admin/flow")
public class FlowController {

	private FlowManagerService flowService;
	
    private MessageHelper messageHelper;

	@RequestMapping("/flows")
	public String list(@ModelAttribute Pager<ProcessDefinition> page,@RequestParam Map<String, Object> parameterMap,Model model) {
	    List<PropertyFilter> propertyFilters = PropertyFilter
                .buildFromMap(parameterMap);
		model.addAttribute("page",flowService.findFlowDefinitions(page, propertyFilters));
	    return "flow/list";
	}
	
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String deleteFlow(@RequestParam("id")String id ,RedirectAttributes redirectAttributes){
		flowService.deleteFlow(id);
        messageHelper.addFlashMessage(redirectAttributes,
                "core.success.delete", "删除成功");
        return "redirect:/admin/flow/flows";
	}
	
	@RequestMapping(value="/upload", method = RequestMethod.GET)
	public String upload(){
		  return "flow/upload";
	}
	
    /**
     * 上传发布流程定义.
     */
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public String processUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) throws Exception {
        flowService.saveFlowDeploy(file);
        messageHelper.addFlashMessage(redirectAttributes,
                "core.success.delete", "部署成功");
        return "redirect:/admin/flow/flows";
    }
    
    @RequestMapping(value="/instances", method = RequestMethod.GET)
    public String instances(@RequestParam("id")String defineId,@ModelAttribute Pager<HistoricProcessInstance> page,Model model){
    	flowService.findFlowInstances(page,defineId,"running");
    	model.addAttribute("page", page);
        return "flow/instances";
    }
	
	public MessageHelper getMessageHelper() {
		return messageHelper;
	}
	@Inject
	public void setMessageHelper(MessageHelper messageHelper) {
		this.messageHelper = messageHelper;
	}
	public FlowManagerService getFlowService() {
		return flowService;
	}
	@Inject
	public void setFlowService(FlowManagerService flowService) {
		this.flowService = flowService;
	}

}
