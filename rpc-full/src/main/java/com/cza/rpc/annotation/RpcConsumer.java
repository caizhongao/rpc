
    /**  
    * @Title: RpcService.java
    * @Package cza.MyRpc.server
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年1月22日下午4:38:05
    * @version V1.0  
    */
    
package com.cza.rpc.annotation;
import java.lang.annotation.RetentionPolicy;


import java.lang.annotation.Retention;


/**
    * @ClassName: RpcService
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年1月22日下午4:38:05
    *
    */
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcConsumer{
}
