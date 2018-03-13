package org.primejava.cms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Article;
import org.primejava.cms.model.Contents;
import org.primejava.cms.model.User;
import org.primejava.cms.service.ArticleService;
import org.primejava.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 流程用户组管理
 * 
 * @author liguo
 *
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;

	@RequestMapping(value="/queryData1",method=RequestMethod.GET)
	@ResponseBody
	public String queryData1(Integer id){
		System.out.println(id);
		return "jsonData";
	}
	
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public String list(@ModelAttribute Pager<Article> page, Model model,
			HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		page = articleService.findArticles(page, user.getId());
		model.addAttribute("page", page);
		return "article/list";
	}

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input(@RequestParam(value = "id", required = false) String id,Model model) {
		Article article = new Article();
		if(StringUtils.isNotBlank(id)){
			article=articleService.findArticleById(id);
		}else{
			article.setContents(new Contents());
		}
		model.addAttribute(article);
		return "article/input";
	}

	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public String publish(Article article, HttpSession session) {
		System.out.println(article.getKeyword());
		User user = (User) session.getAttribute("loginUser");
		article.setUser(user);
		article.setAuthor(user.getUsername());
		article.setPublishDate(new Date());
		articleService.saveOrUpdate(article);
		return "redirect:/admin/article/articles";
	}

	@RequestMapping(value = "/preview", method = RequestMethod.GET)
	public String preview(@RequestParam(value = "id") String id, Model model) {
		Article article = articleService.findArticleById(id);
		model.addAttribute(article);
		List<String> tags=articleService.readAllTag();
		model.addAttribute("tags",tags);
		return "article/preview";
	}
	
	@RequestMapping(value = "/previewByKey", method = RequestMethod.GET)
	public String previewByKey(@RequestParam(value = "key") String key,@ModelAttribute Pager<Article> page, Model model) {
		page= articleService.findArticleByKey(page,key);
		model.addAttribute("page", page);
		return "article/list";
	}
	
	@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
	public String subscribe( HttpSession session, Model model){
		User user = (User) session.getAttribute("loginUser");
		System.out.println(user.getUsername());
		String tag=user.getTags();
		if(tag==null){
			List<String>  tags=articleService.readAllTag();
			tag=StringUtils.join(tags, ",");
			model.addAttribute("tag",tag);
		}else{
			model.addAttribute("tag",tag);
		}
		return "article/subscribe";
	}
	
	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	public String subscribe(@RequestParam(value = "tag") String tag,HttpSession session){
		User user = (User) session.getAttribute("loginUser");
		System.out.println(tag);
		user.setTags(tag);
		userService.update(user);
		return "redirect:/admin/article/articles";
	}
}
