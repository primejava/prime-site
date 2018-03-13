package org.primejava.cms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.primejava.basic.model.Pager;
import org.primejava.basic.util.EnumUtils;
import org.primejava.cms.flow.enums.GroupTypeEnum;
import org.primejava.cms.model.Group;
import org.primejava.cms.model.User;
import org.primejava.cms.service.GroupService;
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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 流程用户组管理
 * @author liguo
 *
 */
@Controller
@RequestMapping("/admin/group")
public class GroupController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	
		@RequestMapping("/groups")
		public String list(@ModelAttribute Pager<Group> page,Model model) {
			groupService.findGroups(page);
			model.addAttribute("page",page);
			Map<String, String> groupTypes = EnumUtils.enumProp2Map(
					GroupTypeEnum.class, "name", "value");
			model.addAttribute("groupTypes",groupTypes);
		    return "group/list";
		}
		
		@RequestMapping(value="/updateWithUser",method=RequestMethod.GET)
		public String updateWithUser(){
			
			 return "group/assignUser";
		}
		
		@RequestMapping(value="/input",method=RequestMethod.GET)
		public String input(@RequestParam(value = "id", required = false) String id,Model model) {
			Group group=null;
			if(id!=null){
				group= groupService.findGroup(id);
			}else{
				group=new Group();
				group.setUsers(new ArrayList<String>());
			}
			
			model.addAttribute("model",group);
			Map<String, String> groupTypes = EnumUtils.enumProp2Map(
					GroupTypeEnum.class, "name", "value");
			model.addAttribute("groupTypes",groupTypes);
		    return "group/input";
		}
		
		@RequestMapping(value="/input",method=RequestMethod.POST)
		public String input(@Valid Group group,BindingResult br,Model model){
			if(br.hasErrors()) {
				return "group/input";
			}
			if(StringUtils.isEmpty(group.getId())){
				groupService.add(group);
			}else{
				Group old=groupService.load(group.getId());
				BeanUtils.copyProperties(group, old, new String[]{"id"});
				groupService.updateGroup(old);
			}
			return "redirect:/admin/group/groups";
		}
		
		@RequestMapping(value="/buildUserTree")
		public @ResponseBody List<User> buildUserTree(){
			List<User> us=userService.findAll();
			return us;
		}
		
}
