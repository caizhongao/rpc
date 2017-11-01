
    /**  
    * @Title: ServiceManager.java
    * @Package cza.MyRpc.server
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年9月22日下午2:01:30
    * @version V1.0  
    */
    
package com.cza.rpc.server.registry;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
    * @ClassName: ServiceManager
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年9月22日下午2:01:30
    *
    */

public class ServiceManager {
	private static ConcurrentMap<String,Object> serviceMap=new ConcurrentHashMap<>();;
	private static ServiceManager manager;
	private static Object lock=new Object();
	
	public static ServiceManager instance(){
		if(manager==null){
			synchronized (lock) {
				if(manager==null){
					manager=new ServiceManager();
				}
			}
		}
		return manager;
	}
	
	public void putService(String beanClass,Object bean){
		serviceMap.put(beanClass, bean);
	}
	
	public Object getService(String beanClass){
		return serviceMap.get(beanClass);
	}
}
