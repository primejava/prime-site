package org.primejava.cms.controller;

import javax.servlet.http.HttpSession;

import org.primejava.cms.model.Enterprise;
import org.primejava.cms.model.User;
import org.primejava.cms.service.EnterpriseService;
import org.primejava.cms.service.UserService;
import org.primejava.cms.web.CmsSessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register() {
		return "register";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username,String password,Model model,HttpSession session) {
		User loginUser=null;
		try {
		loginUser = userService.login(username, password);
		} catch (Exception e) {
			
			return "/login";
			//return "用户名：admin 密码 000000";
		}
		session.setAttribute("loginUser", loginUser);
		CmsSessionContext.addSessoin(session);
		return "redirect:/admin/dashBoard/dashBoard";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(Enterprise enterprise,HttpSession session) {
		enterpriseService.saveOrUpdateEnterprise(enterprise);
		return "redirect:/admin/dashBoard/dashBoard";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		CmsSessionContext.removeSession(session);
		session.invalidate();
		return "redirect:/login/login";
	}
}
