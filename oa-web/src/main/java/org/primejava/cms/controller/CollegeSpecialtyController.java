package org.primejava.cms.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primejava.basic.model.Pager;
import org.primejava.cms.model.Article;
import org.primejava.cms.pojo.CollegeSpecialty;
import org.primejava.cms.service.DictionaryCollegeSpecialtyService;
import org.primejava.cms.service.DictionaryCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

/**
 * 学院专业管理
 * 
 * @author liguo
 *
 */
@Controller
@RequestMapping("/admin/college")
public class CollegeSpecialtyController extends AbstractCommonController{
	@Autowired
	private DictionaryCollegeSpecialtyService collegeSpecialtyService;

	@Autowired
	private DictionaryCommonService dictionaryService;
	
	@RequestMapping(value="/buildTree", method = RequestMethod.POST)
	public void buildTree(HttpServletResponse resp,@RequestParam(value = "graduationYear", required = false) Short graduationYear) {
		System.out.println(graduationYear);
        sendArrayMsg(resp,this.collegeSpecialtyService.buildAllNodes(graduationYear));
    }
	
	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String list(@ModelAttribute Pager<Article> page, Model model,
			HttpSession session) {
		
		return "college/input";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(HttpServletResponse resp,CollegeSpecialty collegeSpecialty) {
        this.collegeSpecialtyService.save(collegeSpecialty);
        sendSuccessMsg(resp);
    }
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
    public void remove(HttpServletResponse resp,CollegeSpecialty collegeSpecialty) {
        this.collegeSpecialtyService.save(collegeSpecialty);
        sendSuccessMsg(resp);
    }
	
	@RequestMapping(value = "/buildCollegeTree", method = RequestMethod.POST)
	 public void buildCollegeTree(HttpServletResponse resp)
	  {
	    sendResponseMsg(resp, new Gson().toJson(this.collegeSpecialtyService.findColleges()));
	  }
	
	@RequestMapping(value = "/collegeSpecialties", method = RequestMethod.GET)
	public void buildCollegeSpecialties(HttpServletResponse resp) {
		sendArrayMsg(resp,
				this.collegeSpecialtyService.buildAllNodes((short) 2016));
	}
}
