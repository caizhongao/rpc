
    /**  
    * @Title: ServiceFactory.java
    * @Package cza.MyRpc.client
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月8日下午3:03:18
    * @version V1.0  
    */
    
package cza.MyRpc.client;

import java.lang.reflect.Proxy;


/**
    * @ClassName: ServiceFactory
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月8日下午3:03:18
    *
    */

public class ServiceFactory {
	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<?> interfaceClass){
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new ServiceProxy());
	}
}
