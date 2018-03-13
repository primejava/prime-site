package org.primejava.cms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primejava.basic.model.Pager;
import org.primejava.basic.query.PropertyFilter;
import org.primejava.cms.dao.QueryDao;
import org.primejava.cms.model.QueryEntity;
import org.primejava.cms.model.User;
import org.primejava.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/query")
public class QueryController {
	@Autowired
	private UserService userService;
	@Autowired
	private QueryDao queryDao;
	@RequestMapping("/query")
	public String query() {
		return "query/query";
	}
	
	@RequestMapping(value = "/postQuerySql", method = RequestMethod.POST)
	public String postQuerySql(@ModelAttribute Pager<User> page,Model model,HttpServletRequest request, HttpServletResponse response){
		String sql = request.getParameter("sql");
		System.out.println(sql);
		Pager<User> result=userService.findUserBySQL(page,sql);
		for(User u:result.getResult()){
			System.out.println(u.getUsername());
		}
		model.addAttribute("page",result);
	    return "query/list";
	}
	
	
	
	@RequestMapping(value = "getColumnsNameAndType", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> getColumnsNameAndType() {
		Map<String,String> result=queryDao.getColumnsNameAndTypeByClass(User.class);
		return result;
	}
	
	@RequestMapping(value = "getColumnsNameAndType2", method = RequestMethod.GET)
	@ResponseBody
	public List<QueryEntity> getColumnsNameAndType2() {
		List<QueryEntity> result=queryDao.getQueryEntitysByClass(User.class);
		return result;
	}
	
	
	@RequestMapping(value = "/postQuerySql2", method = RequestMethod.POST)
	public String postQuerySql2(@ModelAttribute Pager<User> page,@RequestParam Map<String, Object> parameterMap,Model model) {
		System.out.println("后台。。");
		model.addAttribute("page",userService.findUser(page,null));
		return "query/list";
	}
	
	@RequestMapping("/users")
	public String list(@ModelAttribute Pager<User> page,@RequestParam Map<String, Object> parameterMap,Model model) {
	    List<PropertyFilter> propertyFilters = PropertyFilter
	                .buildFromMap(parameterMap);
		model.addAttribute("page",userService.findUser(page,propertyFilters));
	    return "query/list";
	}
	
	@RequestMapping(value = "/postQuerySqlAJAX", method = RequestMethod.POST)
	@ResponseBody
	public String postQuerySql2(@ModelAttribute Pager<User> page,Model model,HttpServletRequest request, HttpServletResponse response){
		return "{\"success\":true,\"meg\":\"success\"}";
	}
	
	@RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String id) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("success",true);
        map.put("msg","ok");
        return map;
    }
	
}
