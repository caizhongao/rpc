
    /**  
    * @Title: ServiceProxy.java
    * @Package cza.MyRpc.client
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月8日下午2:56:34
    * @version V1.0  
    */
    
package com.cza.rpc.consumer.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cza.rpc.consumer.netty.client.RpcClient;
import com.cza.rpc.dto.Request;
import com.cza.rpc.dto.RespFuture;
import com.cza.rpc.dto.Response;



/**
    * @ClassName: ServiceProxy
    * @Description: 服务代理程序，所有的服务调用会经过此代理
    * @author mufeng
    * @date 2017年2月8日下午2:56:34
    *
    */

public class ServiceProxy implements InvocationHandler {
	private static final Logger log = LoggerFactory.getLogger(ServiceProxy.class);
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Long startTime=System.currentTimeMillis();
		Request request=new Request();
		request.setStartTime(startTime);
		request.setArgs(args);
		request.setArgTypes(method.getParameterTypes());
		request.setMethod(method.getName());
		request.setServiceName(method.getDeclaringClass().getName());
		RespFuture future=RpcClient.instance().send(request);
		Response resp=future.getValue();
		if(resp.getError()!=null){
			throw resp.getError();
		}
		log.info("invoke rpc service requestId:{},cost:{}",resp.getRequestId(),System.currentTimeMillis()-startTime);
		return resp.getResult();
	}

}
