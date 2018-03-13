package org.primejava.cms.controller;

import javax.servlet.http.HttpSession;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 流程用户组管理
 * 
 * @author liguo
 *
 */
@Controller
@RequestMapping("/admin/echart")
public class EchartController {
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String list(@ModelAttribute Pager<Article> page, Model model,
			HttpSession session) {
		
		return "echart/grid";
	}
}
