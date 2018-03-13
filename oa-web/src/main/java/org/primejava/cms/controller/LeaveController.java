package org.primejava.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.activiti.engine.task.Comment;
import org.primejava.basic.model.Pager;
import org.primejava.cms.flow.FlowStatusEnum;
import org.primejava.cms.flow.LeaveFlowService;
import org.primejava.cms.model.Leave;
import org.primejava.cms.model.User;
import org.primejava.cms.service.LeaveService;
import org.primejava.cms.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/leave")
public class LeaveController extends AbstractCommonController{
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private LeaveFlowService leaveFlowService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/comment")
	public void comment(HttpServletResponse resp,HttpServletRequest request) {
        String businessId = request.getParameter("id");
        sendArrayMsg(resp,leaveFlowService.getTaskComments(businessId));
    }

	@RequestMapping("/leaves")
	public String list(@ModelAttribute Pager<Leave> page, Model model,
			HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		model.addAttribute("page", leaveService.findLeaves(page, user));
		return "student/leave/list";
	}
	

	@RequestMapping("/leaveHistorys")
	public String leaveHistorys(@ModelAttribute Pager<Leave> page, Model model,
			HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		model.addAttribute("page", leaveService.findApplyHistories(page, user));
		return "student/leave/historys";
	}

	@RequestMapping(value = "ApplingLeaveFlow", method = RequestMethod.GET)
	public String ApplingLeaveFlow(@ModelAttribute Pager<Leave> page,
			Model model, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		leaveFlowService.findApplyFlow(page, user.getId());
		for (Leave l : page.getResult()) {
			l.setUser(user);
		}
		model.addAttribute("page", page);

		return "student/leave/applingLeaveFlow";
	}

	@RequestMapping("/auditList")
	public String auditList(@ModelAttribute Pager<Leave> page, Model model,
			HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		// 要级联查出这个流程的业务对象的用户
		leaveFlowService.findAuditFlows(page, user.getId());
		model.addAttribute("page", page);
		return "teacher/leave/handleLeaveFlow";
	}
	
	@RequestMapping("/auditPass")
	public String auditPass(@ModelAttribute Pager<Leave> page, Model model,
			HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		// 要级联查出这个流程的业务对象的用户
		Leave leave=new Leave();
		
		leave.setStatus(FlowStatusEnum.PASS.getValue());
		leaveFlowService.findAuditHistories(page, leave,user.getId());
		model.addAttribute("page", page);
		return "teacher/leave/auditPass";
	}
	
	@RequestMapping("/auditBack")
	public String auditBack(@ModelAttribute Pager<Leave> page, Model model,
			HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		// 要级联查出这个流程的业务对象的用户
		Leave leave=new Leave();
		leave.setStatus(FlowStatusEnum.BACK.getValue());
		leaveFlowService.findAuditHistories(page, leave,user.getId());
		model.addAttribute("page", page);
		return "teacher/leave/auditPass";
	}

	@RequestMapping(value = "input", method = RequestMethod.GET)
	public String input(@RequestParam(value = "id", required = false) String id,Model model) {
		Leave leave=null;
		if(id!=null){
			leave= leaveService.load(id);
		}else{
			leave=new Leave();
		}
		model.addAttribute("model", leave);
		return "student/leave/input";
	}

	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view(Model model, @RequestParam("id") String leaveId) {
		Leave leave = leaveFlowService.findLeaveById(leaveId);
		List<Comment> comments = leaveFlowService.getTaskComments(leaveId);
		leave.setComments(comments);
		model.addAttribute("model", leave);
		return "student/leave/show";
	}

	@RequestMapping(value = "input", method = RequestMethod.POST)
	public String input(@Valid Leave leave, BindingResult br, Model model,
			HttpSession session) {
		System.out.println(leave.getId());
		if (br.hasErrors()) {
			return "student/leave/input";
		}
		User user = (User) session.getAttribute("loginUser");
		leave.setUser(user);
		// 吗的怪了。。。---比着用户那里看看。
		Leave old = null;
		if (StringUtils.isEmpty(leave.getId())) {
			old = new Leave();
			BeanUtils.copyProperties(leave, old);
		} else {
			old = leaveService.load(leave.getId());
			BeanUtils.copyProperties(leave, old, new String[] { "id" });
		}
		//这个每次都是启动流程的方法，不是重新提交的方法
		leaveService.saveOrUpdateLeave(old);
		return "redirect:/admin/leave/leaves";
	}

	@RequestMapping(value = "auditPass", method = RequestMethod.POST)
	public String auditPass(@RequestParam("leaveId") String leaveId,
			@RequestParam("status") String status,
			@RequestParam("name") String comment, HttpSession session) {
		Integer audit = null;
		if (status.equals("pass")) {
			audit = FlowStatusEnum.PASS.getValue();
		} else {
			audit = FlowStatusEnum.BACK.getValue();
		}
		User user = (User) session.getAttribute("loginUser");
		leaveService.saveAudit(leaveId, audit, comment, user);
		return "redirect:/admin/leave/auditList";
	}

}
