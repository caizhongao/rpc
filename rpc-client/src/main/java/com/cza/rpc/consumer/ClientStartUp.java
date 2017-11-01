
    /**  
    * @Title: RpcClient.java
    * @Package cza.MyRpc.client
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月8日下午2:52:02
    * @version V1.0  
    */
    
package com.cza.rpc.consumer;

import java.lang.reflect.Field;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.cza.rpc.annotation.RpcConsumer;
import com.cza.rpc.consumer.discovery.ServiceDiscovery;
import com.cza.rpc.consumer.netty.client.RpcClient;
import com.cza.rpc.consumer.proxy.ServiceFactory;
import com.esotericsoftware.minlog.Log;



/**
    * @ClassName: RpcClient
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月8日下午2:52:02
    *
    */

public class ClientStartUp implements ApplicationContextAware, InitializingBean{

	@Override
	public void afterPropertiesSet(){
		//发现服务
		ServiceDiscovery.init();
		//初始化客户端连接
		RpcClient.init();
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext){
		//注册服务
		try {
			for(String beanName:applicationContext.getBeanDefinitionNames()){
				Object bean=applicationContext.getBean(beanName);
				Field[] fileds=bean.getClass().getDeclaredFields();
				for(Field filed:fileds){
					if(filed.isAnnotationPresent(RpcConsumer.class)){
						filed.setAccessible(true);
						filed.set(bean,ServiceFactory.getService(filed.getType()));
					}
				}
			}
		} catch (Exception e) {
			Log.info("init rpc consumer exception:",e);
		}
	}

	
}
