
    /**  
    * @Title: Myservice.java
    * @Package cza.MyRpc
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年1月22日下午4:24:47
    * @version V1.0  
    */
    
package cza.MyRpc;

import cza.MyRpc.server.RpcService;

/**
    * @ClassName: Myservice
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年1月22日下午4:24:47
    *
    */
@RpcService
public class MyserviceImpl implements Myservice{
	public String say(String word){
		System.out.println(word);
		return word;
	}
}
