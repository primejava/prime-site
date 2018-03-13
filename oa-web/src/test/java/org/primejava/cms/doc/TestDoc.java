package org.primejava.cms.doc;

import java.util.Map;

import org.junit.Test;
import org.primejava.basic.util.EnumUtils;
import org.primejava.cms.flow.enums.GroupTypeEnum;
import org.primejava.cms.office2pdf.DocConverterUtil;

public class TestDoc {
public static void main(String[] args) {
		
		microsoftOfficeDocTest();	// 微软 Office 文档转换测试
//		wpsOfficeDocTest();			// 金山wps 文档转换测试
//		openOfficeDocTest();		// apache 开源openOffice 文档转换测试
	}
	
	public static void microsoftOfficeDocTest() {
		
		String sourceFilePath = "C:/Users/liguo/Desktop/FlexPaper+swfTools仿文档在线阅读.docx";
		String pdfFilePath = "C:/Users/liguo/Desktop/FlexPaper+swfTools仿文档在线阅读.docx.pdf";

	
		office2pdf(sourceFilePath, pdfFilePath);
		pdf2swf(pdfFilePath, null);
	}
	
	public static void wpsOfficeDocTest() {
		
		String basePath = System.getProperty("user.dir").replace("\\", "/") +"/WebContent/";
		
		String sourceFilePath = basePath + "docs/wps/Mysql_性能优化教程（内部材料）.wps";
		String pdfFilePath = basePath + "docs/wps/Mysql_性能优化教程（内部材料）.wps.pdf";
		String swfFilePath = basePath + "docs/wps/Mysql_性能优化教程（内部材料）.wps.pdf.swf";
//		String sourceFilePath = basePath + "docs/wps/天翼RTC研发项目-研发进展及周报.et";
//		String pdfFilePath = basePath + "docs/wps/天翼RTC研发项目-研发进展及周报.et.pdf";
//		String swfFilePath = basePath + "docs/wps/天翼RTC研发项目-研发进展及周报.et.pdf.swf";
//		String sourceFilePath = basePath + "docs/wps/消息化解决方案.dps";
//		String pdfFilePath = basePath + "docs/wps/消息化解决方案.dps.pdf";
//		String swfFilePath = basePath + "docs/wps/消息化解决方案.dps.pdf.swf";
		
		office2pdf(sourceFilePath, pdfFilePath);
		pdf2swf(pdfFilePath, null);
	}
	
	public static void openOfficeDocTest() {
		
		String basePath = System.getProperty("user.dir").replace("\\", "/") +"/WebContent/";
		
		String sourceFilePath = basePath + "docs/openoffice/MySQL性能优化的最佳20条经验.odt";
		String pdfFilePath = basePath + "docs/openoffice/MySQL性能优化的最佳20条经验.odt.pdf";
		String swfFilePath = basePath + "docs/openoffice/MySQL性能优化的最佳20条经验.odt.pdf.swf";
//		String sourceFilePath = basePath + "docs/openoffice/天翼RTC研发项目-研发进展及周报-2013-9-13.ods";
//		String pdfFilePath = basePath + "docs/openoffice/天翼RTC研发项目-研发进展及周报-2013-9-13.ods.pdf";
//		String swfFilePath = basePath + "docs/openoffice/天翼RTC研发项目-研发进展及周报-2013-9-13.ods.pdf.swf";
//		String sourceFilePath = basePath + "docs/openoffice/图形文档.odp";
//		String pdfFilePath = basePath + "docs/openoffice/图形文档.odp.pdf";
//		String swfFilePath = basePath + "docs/openoffice/图形文档.odp.pdf.swf";
		
//		office2pdf(sourceFilePath, pdfFilePath);
		pdf2swf(pdfFilePath, null);
	}
	
	private static String docServiceConfigRelativePath = "config/docService.properties";
	
	public static void office2pdf(String sourceFilePath, String destFilePath) {
		
		try {
			DocConverterUtil.setHost("127.0.0.1");
			DocConverterUtil.setPort(8100);
			DocConverterUtil.setConfigFileRelativePath(docServiceConfigRelativePath);
			
			int office2pdf = DocConverterUtil.officeToPdf(sourceFilePath, destFilePath);
			System.out.println(office2pdf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pdf2swf(String sourceFilePath, String destFilePath) {
		
//		String sourceFilePath = "D:\\test\\Spring_Security3.pdf";
//		String destFilePath = "D:\\test\\Spring_Security3.swf";
		
		try {
			
			DocConverterUtil.setConfigFileRelativePath(docServiceConfigRelativePath);
			int pdfToSwf = DocConverterUtil.pdfToSwf(sourceFilePath, destFilePath);
			System.out.println(pdfToSwf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
