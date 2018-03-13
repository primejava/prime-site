/**
 * Project Name:com.wisdombud.education.public
 * File Name:ConvertMapUtil.java
 * Package Name:com.wisdombud.education.support
 * Date:2014年9月24日 下午7:57:39
 * Copyright (c) 2014, www.wisdombud.com All Rights Reserved.
 */

package org.primejava.cms.imp.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primejava.cms.model.ImportOrExportParam;

/**
 * ClassName: ConvertMapUtil. <br/>
 * Function: TODO <br/>
 * date: 2014年9月24日 下午7:57:39 <br/>
 * 
 * @author mmzhao
 * @version
 * @since JDK 1.6
 */
public final class ImportMapUtils {
    private ImportMapUtils() {
    }
    public static Map<String, String>              SEX_MAP              = new HashMap<String, String>();     // 性别map
   
    public static Map<String, String>              EDUCATION_MAP        = new HashMap<String, String>();     // 学历map
  
    public static Map<String, ImportOrExportParam> IMPORT_PARAM_MAP     = new HashMap<String, ImportOrExportParam>();     // 参数map
    public static Map<String, List<Object[]>>      MAP                  = new HashMap<String, List<Object[]>>();     // 转义map
    public static Map<String, String>              STRING_MAP           = new HashMap<String, String>();     // 转义map
    public static Map<String, RelevanceTableField> RELEVANCE_MAP        = new HashMap<String, RelevanceTableField>();     // 转义map

}
