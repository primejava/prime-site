package org.primejava.cms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.primejava.basic.model.Pager;
import org.primejava.cms.model.CommonRole;
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
 * @author liguo
 *
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController  extends AbstractCommonController{
	@Autowired
	private RoleService roleService;
	
		@RequestMapping("/roles")
		public String list(@ModelAttribute Pager<CommonRole> page,Model model,CommonRole role) {
			roleService.findRoles(page,role);
			model.addAttribute("page",page);
		    return "role/list";
		}
		
		@RequestMapping(value="/input",method=RequestMethod.GET)
		public String input(@RequestParam(value = "id", required = false) String id,Model model) {
			CommonRole role=null;
			if(id!=null){
				role= roleService.findRole(id);
			}else{
				role=new CommonRole();
				role.setDepartments(new ArrayList<String>());
			}
			model.addAttribute("model",role);
		    return "role/input";
		}
		
		@RequestMapping(value="/input",method=RequestMethod.POST)
		public String input(@Valid CommonRole role,BindingResult br,Model model){
			if(br.hasErrors()) {
				return "role/input";
			}
			System.out.println(role.getDepartments());
			roleService.saveRole(role);
//			if(StringUtils.isEmpty(role.getId())){
//				roleService.saveRole(role);
//			}else{
//				CommonRole old=roleService.findRole(role.getId());
//				BeanUtils.copyProperties(role, old, new String[]{"id"});
//				roleService.saveRole(old);
//			}
			return "redirect:/admin/role/roles";
		}
		
		@RequestMapping(value = "/buildRoleTree", method = RequestMethod.POST)
	    public void buildRoleTree(HttpServletResponse resp) {
	        List<CommonRole> opers = roleService.findAll();
	        sendResponseMsg(resp,new Gson().toJson(opers));
	    }
		
}
