package org.primejava.cms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.primejava.basic.model.Pager;
import org.primejava.basic.util.CapacityUtil;
import org.primejava.basic.util.EnumUtils;
import org.primejava.basic.util.IoUtils;
import org.primejava.cms.lucene.DocumentEntity;
import org.primejava.cms.lucene.GetSearchResult;
import org.primejava.cms.model.Attachment;
import org.primejava.cms.office2pdf.DocConverterUtil;
import org.primejava.cms.service.AttachmentService;
import org.primejava.cms.spring.MessageHelper;
import org.primejava.cms.util.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import GroupTypeEnum.DocTypeEnum;

import com.google.gson.Gson;

@Controller
@RequestMapping("/admin/attachment")
public class AttachmentController {
	
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
    private MessageHelper messageHelper;
	@RequestMapping(value = "/listByList", method = RequestMethod.GET)
	public String listByList(@ModelAttribute Pager<Attachment> page, Model model,
			HttpSession session) {
		page=attachmentService.findAttachments(page);
		model.addAttribute("page", page);
		return "attachment/list";
	}
	
	@RequestMapping(value = "/listByGrid", method = RequestMethod.GET)
	public String listByGrid(@ModelAttribute Pager<Attachment> page, @RequestParam(value = "pageNo", required = false) Integer pageNo,Model model,
			HttpSession session) {
		if(pageNo!=null){
			page.setPageNo(pageNo);
		}
		page=attachmentService.findAttachments(page);
		for(Attachment attachment:page.getResult()){
			String suffix=FilenameUtils.getExtension(attachment.getNewName());
			attachment.setType(suffix);
		}
		model.addAttribute("page", page);
		return "attachment/grid";
	}
	
	@RequestMapping(value="/preview",method=RequestMethod.GET)
	public String preview(@RequestParam(value = "id") String id,@RequestParam(value = "pageNo", required = false) Integer pageNo,HttpServletRequest request, Model model){
		Attachment attachment = attachmentService.findAttachmentById(id);
		String suffix=FilenameUtils.getExtension(attachment.getNewName());
		List<String> allEnums=EnumUtils.enum2Name(DocTypeEnum.class);
		String enumsString=StringUtils.join(allEnums, ",");
		System.out.println(enumsString);
		model.addAttribute("isDoc",enumsString.contains(suffix.toUpperCase()));
		model.addAttribute(attachment);
		model.addAttribute("pageNo",pageNo);
	    String uploadFolder = request.getSession().getServletContext().getRealPath("upload");  
		model.addAttribute("uploadFolder",uploadFolder);
		return "attachment/preview";
	}
	
    @RequestMapping("download")
    public void download(@RequestParam("id") String id,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	Attachment attachment = attachmentService.findAttachmentById(id);
        InputStream is = null;
        try {
            ServletUtils.setFileDownloadHeader(request, response,
            		attachment.getName());
            is =  new FileInputStream(attachment.getPath());
            IoUtils.copyStream(is, response.getOutputStream());
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
    
    
    
    @RequestMapping(value="/convert",method=RequestMethod.GET)
	public String convert(@RequestParam(value = "id") String id,HttpServletRequest request, Model model,RedirectAttributes redirectAttributes){
		Attachment attachment = attachmentService.findAttachmentById(id);
		String sourceFilePath=attachment.getPath();
		String destFilePath=FilenameUtils.getFullPath(sourceFilePath)+"doc"+File.separator+FilenameUtils.getName(sourceFilePath)+".pdf";
		File destFile= new File(destFilePath);
		int result=0;
		if(!destFile.exists()){
			result=office2pdf(sourceFilePath, destFilePath);
		}
		if(result!=0){
		      messageHelper.addFlashMessage(redirectAttributes,
		                "core.success.delete", "转换失败");
		      return "attachment/viewDoc";
		}
		String swfFilePath=destFilePath+".swf";
		File swfFile= new File(swfFilePath);
		if(swfFile.exists()){
			messageHelper.addFlashMessage(redirectAttributes,
	                "core.success.delete", "转换成功");
			model.addAttribute(attachment);
			model.addAttribute("swfFile",swfFilePath);
			String path="/upload/doc/"+FilenameUtils.getName(swfFilePath);
			System.out.println(path);
			model.addAttribute("swfFile",path);
	      return "attachment/viewDoc";
		}
		int swfResult=pdf2swf(destFilePath,swfFilePath);
		if(swfResult!=0){
		      messageHelper.addFlashMessage(redirectAttributes,
		                "core.success.delete", "转换失败");
		      return "attachment/viewDoc";
		}else{
			messageHelper.addFlashMessage(redirectAttributes,
	                "core.success.delete", "转换成功");
			model.addAttribute(attachment);
			String path="/upload/doc/"+FilenameUtils.getName(swfFilePath);
			model.addAttribute("swfFile",path);
	      return "attachment/viewDoc";
		}
	}
    
	@RequestMapping(value="/upload",method=RequestMethod.GET)
	public String upload(){
		return "attachment/upload";
	}
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public void upload(@RequestParam("file") MultipartFile attach,HttpServletRequest request,HttpServletResponse resp) throws Exception{
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
	
	
	@RequestMapping(value="/buildIndex",method=RequestMethod.GET)
	public void buildIndex(HttpServletRequest request,HttpServletResponse resp) throws IOException{
		String uploadFolder = request.getSession().getServletContext().getRealPath("upload");  
		attachmentService.addWordIndex(uploadFolder);
	}
	
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(@RequestParam(value = "word") String word,Model model,@ModelAttribute Pager<DocumentEntity> page,HttpServletRequest request,HttpServletResponse resp) throws Exception{
		GetSearchResult gsr = new GetSearchResult();
		String uploadFolder = request.getSession().getServletContext().getRealPath("upload");  
		List<DocumentEntity> result = gsr.getResult(word,uploadFolder, page.getPageNo(), 200);
		System.out.println(result.size());
		page.setResult(result);
		model.addAttribute("page", page);
		return "attachment/lucene";
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
	
	public static int office2pdf(String sourceFilePath, String destFilePath) {
		try {
			DocConverterUtil.setHost("127.0.0.1");
			DocConverterUtil.setPort(8100);
			DocConverterUtil.setConfigFileRelativePath("config/docService.properties");
			int office2pdf = DocConverterUtil.officeToPdf(sourceFilePath, destFilePath);
			return office2pdf;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int pdf2swf(String sourceFilePath, String destFilePath) {
		try {
			DocConverterUtil.setConfigFileRelativePath("config/docService.properties");
			int pdfToSwf = DocConverterUtil.pdfToSwf(sourceFilePath, destFilePath);
			System.out.println(pdfToSwf);
			return pdfToSwf;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
