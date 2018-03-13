package org.primejava.cms.controller;

import javax.servlet.http.HttpSession;

import org.primejava.cms.model.NoticeHistory;
import org.primejava.cms.model.User;
import org.primejava.cms.service.EmailService;
import org.primejava.cms.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/admin/email")
public class EmailController {
	@Autowired
	private GroupService groupService;

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input() {
		return "email/input";
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String send(@RequestParam("receiver") String receiver,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		NoticeHistory noticeHistory = emailService.addNoticeHistory(user.getId(),receiver,subject,content);
		System.out.println(noticeHistory.getId());
		emailService.sendEmail(noticeHistory.getId());
		return "redirect:/admin/group/groups";
	}

}
