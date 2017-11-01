
    /**  
    * @Title: ServiceManager.java
    * @Package cza.MyRpc.server
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年9月22日下午2:01:30
    * @version V1.0  
    */
    
package com.cza.rpc.consumer.discovery;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;

import com.cza.rpc.util.TransportAddress;

/**
    * @ClassName: ServiceManager
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年9月22日下午2:01:30
    *
    */

public class ServiceAddrManager {
	private static ConcurrentMap<String,List<TransportAddress>> serviceAddrMap=new ConcurrentHashMap<>();;
	private static ServiceAddrManager manager;
	private static Object lock=new Object();
	
	public static ServiceAddrManager instance(){
		if(manager==null){
			synchronized (lock) {
				if(manager==null){
					manager=new ServiceAddrManager();
				}
			}
		}
		return manager;
	}
	
	public void putServiceAddrs(String beanClass,List<TransportAddress> addrs){
		serviceAddrMap.put(beanClass, addrs);
	}
	
	public List<TransportAddress> getServiceAddrs(String beanClass){
		return serviceAddrMap.get(beanClass);
	}
	
	public TransportAddress getServiceAddr(String beanClass){
		if(serviceAddrMap!=null){
			List<TransportAddress>addrs=getServiceAddrs(beanClass);
			if(addrs==null){
				return null;
			}else{
				int size=addrs.size();
				return addrs.get(ThreadLocalRandom.current().nextInt(size));
			}
		}
		return null;
	}
}
