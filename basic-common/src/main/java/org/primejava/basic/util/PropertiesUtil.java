package org.primejava.basic.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
	private static PropertiesUtil util = null;
	private static Map<String,Properties> props = null;
	private PropertiesUtil(){
		
	}
	
	public static PropertiesUtil getInstance() {
		if(util==null) {
			props = new HashMap<String, Properties>();
			util = new PropertiesUtil();
		}
		return util;
	}
	
	public Properties load(String name) {
		if(props.get(name)!=null) {
			return props.get(name);
		} else {
			Properties prop = new Properties();
			try {
				prop.load(PropertiesUtil.class.getResourceAsStream("/"+name+".properties"));
				props.put(name, prop);
				return prop;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
    public void writeProperties(String name,Properties properties,String keyname,String keyvalue) {      
    	 OutputStream fos=null;
        try {
            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
             fos = new FileOutputStream("/"+name+".properties",true);
            properties.setProperty(keyname, keyvalue);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            properties.store(fos, "Update '" + keyname + "' value");
        } catch (IOException e) {
            System.err.println("属性文件更新错误");
        }finally{
        	try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}
