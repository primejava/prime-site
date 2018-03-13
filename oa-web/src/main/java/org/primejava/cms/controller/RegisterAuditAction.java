package org.primejava.cms.controller;

import javax.servlet.http.HttpSession;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Enterprise;
import org.primejava.cms.model.User;
import org.primejava.cms.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/registerAudit")
public class RegisterAuditAction extends AbstractCommonController{
	@Autowired
	private EnterpriseService enterpriseService;
	
	@RequestMapping("/auditHistory")
	public String auditHistory(@ModelAttribute Pager<Enterprise> page, Model model,
			HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		enterpriseService.findAuditRegisterHistories(page, user.getId());
		model.addAttribute("page", page);
		return "teacher/register/auditHistory";
	}
	
	@RequestMapping("/auditPending")
	public String auditPending(@ModelAttribute Pager<Enterprise> page, Model model,
			HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		enterpriseService.findAuditRegister(page, user.getId());
		model.addAttribute("page", page);
		return "teacher/register/auditPending";
	}
}
