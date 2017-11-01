
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

import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
    * @ClassName: RpcService
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年1月22日下午4:38:05
    *
    */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component // 表明可被 Spring 扫描
public @interface RpcService{
}
