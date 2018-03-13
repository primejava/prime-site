package org.primejava.cms.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRResultSetDataSource;

import org.primejava.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/admin/report")
public class ReportController extends AbstractReportAction<Object> {
	@Autowired
	private UserService userServcie;
	
//	@RequestMapping(value = "/getpdfReport", method = RequestMethod.GET)
//	public ModelAndView doSalesReportPDF(ModelAndView modelAndView)
//			throws ClassNotFoundException, SQLException {
//		Map<String, Object> parameterMap = new HashMap<String, Object>();
//		parameterMap.put("datasource", getDataSource());
//		modelAndView = new ModelAndView("pdfReport", parameterMap);
//		return modelAndView;
//	}
	
	@RequestMapping(value = "/getpdfReport", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(ModelAndView modelAndView)
			throws ClassNotFoundException, SQLException {
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		ResultSet resultSet = null;
		String query = "select * from t_user";
		Statement statement;
		statement = this.getConnection().createStatement();
		resultSet = statement.executeQuery(query);
		JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(
				resultSet);
		parameterMap.put("datasource", resultSetDataSource);
		modelAndView = new ModelAndView("pdfReport", parameterMap);
		return modelAndView;
	}
}
