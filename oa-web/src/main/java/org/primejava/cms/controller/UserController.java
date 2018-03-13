package org.primejava.cms.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.primejava.basic.model.Pager;
import org.primejava.basic.model.SystemContext;
import org.primejava.basic.query.PropertyFilter;
import org.primejava.basic.util.CapacityUtil;
import org.primejava.cms.imp.pojo.ImportRecord;
import org.primejava.cms.imp.service.ImportExportService;
import org.primejava.cms.model.Attachment;
import org.primejava.cms.model.CommonRole;
import org.primejava.cms.model.User;
import org.primejava.cms.service.AttachmentService;
import org.primejava.cms.service.RoleService;
import org.primejava.cms.service.UserService;
import org.primejava.cms.spring.MessageHelper;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
//springmvc的数据传递还是按照孔浩的来好，页面就按照这个人的来
@Controller
@RequestMapping("/admin/user")
public class UserController {
	private UserService userService;
	
    private MessageHelper messageHelper;
	@Autowired
	private AttachmentService attachmentService;
    @Autowired
    private ImportExportService               importService;
	@Autowired
	private RoleService roleService;
	//第一个参数设置分页和排序，第二个参数用于查询
	@RequestMapping("/users")
	public String list(@ModelAttribute Pager<User> page,@RequestParam Map<String, Object> parameterMap,Model model) {
	    List<PropertyFilter> propertyFilters = PropertyFilter
	                .buildFromMap(parameterMap);
	    page=userService.findUser(page,propertyFilters);
	     Gson gson = new Gson();  
        String result = gson.toJson(page);  
        System.out.println(result);  
		model.addAttribute("page",page);
	    return "user/list";
	}
	
	//@RequestParam表示参数为baidu.do?sss ;pathValue表示baidu/sss
	@RequestMapping(value="/input",method=RequestMethod.GET)
	public String input(@RequestParam(value = "id", required = false) String id,Model model) {
		User user=null;
		if(id!=null){
			user= userService.load(id);
		}else{
			user=new User();
		}
	    List<CommonRole> roles = roleService.findAll();
		model.addAttribute("roles",roles);
		model.addAttribute("model",user);
	    return "user/input";
	}
	
	@RequestMapping(value="/importUser",method=RequestMethod.GET)
	public String importUser(){
		
		return "user/importUser";
	}
	
	@RequestMapping(value="/exportUserXls",method=RequestMethod.GET)
	public void exportUserXls(HttpServletResponse resp){
		  String tempPath = SystemContext.getRealPath()+"/template/userexport.xls";  
		  List<User> users=userService.findAll();
		  userService.exportUser(resp,tempPath,users);
	}

	
	@RequestMapping(value="/uploadUserData", method = RequestMethod.POST)
	public void uploadUserData(@RequestParam("file") MultipartFile attach,HttpServletRequest request,HttpServletResponse resp) throws Exception{
			resp.setContentType("text/plain;charset=utf-8");
			Attachment att = new Attachment();
			String ext = FilenameUtils.getExtension(attach.getOriginalFilename());
			att.setType(attach.getContentType());
			att.setNewName(String.valueOf(new Date().getTime())+"."+ext);
			att.setName(FilenameUtils.getBaseName(attach.getOriginalFilename()));
			att.setFileSize(CapacityUtil.toMKByte(attach.getSize()));
		    String uploadFolder = request.getSession().getServletContext().getRealPath("upload");  
		    File targetFile = new File(uploadFolder, att.getNewName()); 
		    att.setPath(targetFile.getAbsolutePath());
		    att.setUrl("/upload/"+att.getNewName());
		    att.setUploadTime(new Date());
		    if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	        try {  
	        	attach.transferTo(targetFile);  
	        	attachmentService.add(att);
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        sendSuccessMsg(resp,new Gson().toJson(att), "上传成功！");
	}
	
	@RequestMapping(value="/importUserData", method = RequestMethod.POST)
	public String importUserData(Attachment attachment,HttpServletResponse resp,Model model){
		System.out.println(attachment.getId());
		System.out.println(attachment.getPath());
		ImportRecord importRecord=this.importService.importByType(attachment, "user", "import");
		model.addAttribute(importRecord);
		model.addAttribute("showResult",true);
		return "user/importUser";
		//sendSuccessMsg(resp,new Gson().toJson(importRecord), "导入成功！");
	}
	
	private void sendSuccessMsg(HttpServletResponse resp,String data, String msg) {
		 Map<String, Object> responseMap = new HashMap<String, Object>();
	        responseMap.put("success", true);
	        responseMap.put("data", data);
	        responseMap.put("message", msg);
	        sendResponseMsg(resp,new Gson().toJson(responseMap));
	}

	private void sendResponseMsg(HttpServletResponse response, String jsonMsg) {
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(jsonMsg);
            writer.flush();
        } catch (IOException e) {
        } finally {
            if (null != writer) {
                writer.close();
                writer = null;
            }
        }
	}

	@RequestMapping("/input")
	public String input(@Valid User accountInfo,BindingResult br,Model model){
		//在服务器端进行验证
		if(br.hasErrors()) {
			return "user/input";
		}
		if(!StringUtils.isEmpty(accountInfo.getId())){
			System.out.println(accountInfo.getId());
			User old=userService.load(accountInfo.getId());
			BeanUtils.copyProperties(accountInfo, old, new String[]{"id"});
			userService.update(old);
		}else{
			accountInfo.setCreateDate(new Date());
			userService.add(accountInfo);
		}
		return "redirect:/admin/user/users";
	}
	
	@RequestMapping(value="/deleteUsers", method = RequestMethod.POST)
	public String deleteUsers(@RequestParam("selectedItem")List<String> items ,RedirectAttributes redirectAttributes){
		for(String id:items){
			userService.deleteById(id);
		}
        messageHelper.addFlashMessage(redirectAttributes,
                "core.success.delete", "删除成功");
        return "redirect:/admin/user/users";
	}
	
	@RequestMapping("/checkUsername")
	@ResponseBody
	public boolean checkUsername(@RequestParam("username") String username,@RequestParam(value = "id", required = false) String id){
		User user=null;
	    if (!StringUtils.isEmpty(id)) {
	    	user=userService.findUserByIdAndName(id,username);
	    	return user!=null;
        }else{
        	user=userService.findUserByName(username);
        	return user==null;
        }
	} 
	
	@RequestMapping("/disable")
	public String disable(@RequestParam("id") String id,RedirectAttributes redirectAttributes){
		User user=userService.load(id);
		user.setStatus(0);
		userService.update(user);
	    messageHelper.addFlashMessage(redirectAttributes,
	                "core.success.update", "操作成功");
		return "redirect:/admin/user/users";
	}
	
	@RequestMapping("/active")
	public String active(@RequestParam("id") String id,RedirectAttributes redirectAttributes){
		User user=userService.load(id);
		user.setStatus(1);
		userService.update(user);
	    messageHelper.addFlashMessage(redirectAttributes,
                "core.success.update", "操作成功");
		return "redirect:/admin/user/users";
	}
	
	public MessageHelper getMessageHelper() {
		return messageHelper;
	}
	@Inject
	public void setMessageHelper(MessageHelper messageHelper) {
		this.messageHelper = messageHelper;
	}

	public UserService getUserService() {
		return userService;
	}

	@Inject
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
