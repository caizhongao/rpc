
    /**  
    * @Title: test.java
    * @Package cza.MyRpc.client
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月8日下午2:44:11
    * @version V1.0  
    */
    
package cza.MyRpc.client;

import java.io.IOException;
import java.lang.reflect.Proxy;

import cza.MyRpc.Myservice;

/**
    * @ClassName: test
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng 
    * @date 2017年2月8日下午2:44:11
    *
    */

public class test {
	public static void main(String[] args) throws IOException {
		ServiceDiscovery.instance();
		Myservice mySevice=ServiceFactory.getService(Myservice.class);
		Long start=System.currentTimeMillis();
		mySevice.say("nihao@!");
		Long end=System.currentTimeMillis();
		System.out.println("====>"+(end-start));
		
		
		start=System.currentTimeMillis();
		mySevice.say("nihao@!");
		end=System.currentTimeMillis();
		System.out.println("====>"+(end-start));
		
		
		start=System.currentTimeMillis();
		mySevice.say("nihao@!");
		end=System.currentTimeMillis();
		System.out.println("====>"+(end-start)); 
		
		start=System.currentTimeMillis();
		mySevice.say("nihao@!");
		end=System.currentTimeMillis();
		System.out.println("====>"+(end-start));
		System.in.read(); 
	}
}
