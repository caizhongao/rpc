
    /**  
    * @Title: ServiceProxy.java
    * @Package cza.MyRpc.client
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月8日下午2:56:34
    * @version V1.0  
    */
    
package cza.MyRpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


import cza.MyRpc.common.Request;
import cza.MyRpc.common.RespFuture;
import cza.MyRpc.common.Response;


/**
    * @ClassName: ServiceProxy
    * @Description: 服务代理程序，所有的服务调用会经过此代理
    * @author mufeng
    * @date 2017年2月8日下午2:56:34
    *
    */

public class ServiceProxy implements InvocationHandler {
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Request request=new Request();
		request.setArgs(args);
		request.setArgTypes(method.getParameterTypes());
		request.setMethod(method.getName());
		request.setServiceName(method.getDeclaringClass().getName());
		RespFuture future=RpcClient.getInstance().send(request);
		return future.getValue().getResult();
	}

}
