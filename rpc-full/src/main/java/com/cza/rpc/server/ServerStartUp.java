
    /**  
    * @Title: ServerInit.java
    * @Package cza.MyRpc.server
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年9月22日下午1:59:42
    * @version V1.0  
    */
    
package com.cza.rpc.server;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.cza.rpc.annotation.RpcService;
import com.cza.rpc.server.netty.RpcServer;
import com.cza.rpc.server.netty.handler.RequestExecutor;
import com.cza.rpc.server.registry.ServiceManager;
import com.cza.rpc.server.registry.ServiceRegistry;
import com.cza.rpc.util.IpUtil;
import com.cza.rpc.util.PropertyUtil;

/**
    * @ClassName: ServerInit
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年9月22日下午1:59:42
    *
    */

public class ServerStartUp implements ApplicationContextAware, InitializingBean{
	private final Logger log = LoggerFactory.getLogger(ServerStartUp.class);
    /* (非 Javadoc)
    * 
    * 
    * @throws Exception
    * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
    */
	@Override
	public void afterPropertiesSet(){
		RpcServer.init();
		RequestExecutor.init();
	}

	    
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		//注册服务
		ServiceRegistry registry=new ServiceRegistry();
		Map<String, Object> serviceBeanMap=applicationContext.getBeansWithAnnotation(RpcService.class);
		for(Object bean:serviceBeanMap.values()){
			String serviceName=bean.getClass().getInterfaces()[0].getName();
			ServiceManager.instance().putService(serviceName, bean);
			registry.RegisterService(serviceName,getRegistryAddr());
		}
	}
	
	public String getRegistryAddr(){
		return IpUtil.LOCAL_IP+":"+PropertyUtil.instance().getProperty("registry.port").toString();
	}

}
