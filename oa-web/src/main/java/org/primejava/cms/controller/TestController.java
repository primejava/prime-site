package org.primejava.cms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.primejava.basic.model.Pager;
import org.primejava.cms.model.CommonRole;
import org.primejava.cms.service.DictionaryCollegeSpecialtyService;
import org.primejava.cms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

/**
 * 流程用户组管理
 * 
 * @author liguo
 *
 */
@Controller
@RequestMapping("/admin/test")
public class TestController extends AbstractCommonController {
	@Autowired
	private DictionaryCollegeSpecialtyService collegeSpecialtyService;

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input(
			@RequestParam(value = "id", required = false) String id, Model model) {
		return "test/grid";
	}

	@RequestMapping(value = "/collegeSpecialties", method = RequestMethod.GET)
	public void buildCollegeSpecialties(HttpServletResponse resp) {
		sendArrayMsg(resp,
				this.collegeSpecialtyService.buildAllNodes2((short) 2016));
	}

}
