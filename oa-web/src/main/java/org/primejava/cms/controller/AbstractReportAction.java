package org.primejava.cms.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.primejava.basic.model.SystemContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;

public abstract class AbstractReportAction<T>{
 

  private static final long serialVersionUID = 1L;
  private static final String REPORT_PATH = "/jasper";
  @Autowired
  private DataSource dataSource;
  private Connection connection;
  private String type;
  private String random;
  private Map<String, Object> reportParameters = Maps.newHashMap();
  
  public Connection getConnection()
    throws SQLException
  {
    return this.dataSource.getConnection();
  }
  
  public DataSource getDataSource(){
	  return this.dataSource;
  }
  public void setConnection(Connection connection)
  {
    this.connection = connection;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  public Map<String, Object> getReportParameters()
  {
    return this.reportParameters;
  }
  
  public void setReportParameters(Map<String, Object> reportParameters)
  {
    this.reportParameters = reportParameters;
  }
  
  protected String getReportPath()
  {
    return SystemContext.getRealPath()+REPORT_PATH;
  }
  
  public String getRandom()
  {
    return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
  }
  
  public void setRandom(String random)
  {
    this.random = random;
  }
}
