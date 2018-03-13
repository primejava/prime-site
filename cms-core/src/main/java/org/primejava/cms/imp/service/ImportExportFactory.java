package org.primejava.cms.imp.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dbunit.ant.Export;
import org.primejava.cms.imp.Imports;
import org.primejava.cms.model.CmsException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * ClassName: ImportFactory. <br/>
 * Function: TODO <br/>
 * date: 2015年5月6日 上午10:07:01 <br/>
 * 
 * @author mmzhao
 * @version
 * @since JDK 1.6
 */
public class ImportExportFactory {

    private static volatile ImportExportFactory factory;

    private ImportExportFactory() {
    }

    public static ImportExportFactory instance() {
        if (null == factory) {
            synchronized (ImportExportFactory.class) {
                if (null == factory) {
                    factory = new ImportExportFactory();
                }
            }
        }
        return factory;
    }

    public static synchronized Imports buildImportMap(String type, String category) throws CmsException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (StringUtils.isBlank(type)) {
            throw new CmsException("buildImportOrExportMap parameter type is null!");
        }
        WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();   
        Map map = (Map) applicationContext.getBean(category);
        String className = (String) map.get(type);
        System.out.println(className);
        if (StringUtils.isBlank(className)) {
            throw new CmsException(String.format("not find %s importOrExport class!", type));
        }
        return (Imports) applicationContext.getBean(className);
    }

    public static synchronized Export buildExportMap(String type, String category) throws CmsException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (StringUtils.isBlank(type)) {
            throw new CmsException("buildImportOrExportMap parameter type is null!");
        }
        WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();   
        Map map = (Map) applicationContext.getBean(category);
        String className = (String) map.get(type);
        if (StringUtils.isBlank(className)) {
            throw new CmsException(String.format("not find %s importOrExport class!", type));
        }
        return (Export) applicationContext.getBean(className);
        // return (Imports) Class.forName(className).newInstance();
    }



}
