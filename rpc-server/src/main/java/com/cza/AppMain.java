
    /**  
    * @Title: test.java
    * @Package cza.MyRpc.client
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月8日下午2:44:11
    * @version V1.0  
    */
    
package com.cza;

import org.springframework.context.support.ClassPathXmlApplicationContext;




/**
    * @ClassName: test
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng 
    * @date 2017年2月8日下午2:44:11
    *
    */

public class AppMain {
	  public static void main(String[] args){
	    new ClassPathXmlApplicationContext("application.xml").start();
	  }
}
