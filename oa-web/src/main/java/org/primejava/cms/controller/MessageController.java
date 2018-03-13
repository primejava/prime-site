package org.primejava.cms.controller;

import javax.servlet.http.HttpSession;

import org.primejava.basic.model.Pager;
import org.primejava.cms.model.MessageReceive;
import org.primejava.cms.model.User;
import org.primejava.cms.service.MessageService;
import org.primejava.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 流程用户组管理
 * 
 * @author liguo
 *
 */
@Controller
@RequestMapping("/admin/message")
public class MessageController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@ModelAttribute Pager<MessageReceive> page, Model model,
			HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		page = messageService.findMessageReceive(user, page,new MessageReceive());
		model.addAttribute("page", page);
		return "message/list";
	}



	@RequestMapping(value = "/preview", method = RequestMethod.GET)
	public String preview(@RequestParam(value = "id") String id, Model model) {
		MessageReceive messageReceive = messageService.findMessageReceive(id);
		model.addAttribute(messageReceive);
		return "message/preview";
	}
}
