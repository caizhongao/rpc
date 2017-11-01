
    /**  
    * @Title: PropertyUtil.java
    * @Package com.cza.common
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月3日下午6:01:30
    * @version V1.0  
    */
    
package com.cza.rpc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
    * @ClassName: PropertyUtil
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月3日下午6:01:30
    *
    */

public class PropertyUtil {
	private static final Logger log = LoggerFactory.getLogger(PropertyUtil.class); 
	private static PropertyUtil instance;
	private static Object lock=new Object();
	
	private Properties properties;
	
	private PropertyUtil(){
		InputStream is=null;
		try{
			properties=new Properties();
			is=PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties");
			properties.load(is);
			is.close();
		} catch (IOException e) {
			log.error("初始化properties报错!",e);
			try {
				if(is!=null){
					is.close();
				}
			} catch (IOException e1) {
				log.error("关闭流报错!",e);
			}
		}
	}
	
	
	public Object getProperty(String key){
		if(properties!=null){
			return properties.get(key);
		}
		return null;
	}
	
	
	
	public static PropertyUtil instance(){
		if(instance==null){
			synchronized (lock) {
				if(instance==null){
					instance=new PropertyUtil();
				}
			}
		}
		return instance;
	}
}
