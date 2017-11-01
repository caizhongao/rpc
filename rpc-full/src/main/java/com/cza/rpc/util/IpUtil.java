
    /**  
    * @Title: CustomSystemUtil.java
    * @Package com.cza.rpc.common
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年9月25日下午12:09:52
    * @version V1.0  
    */
    
package com.cza.rpc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cza.rpc.consumer.netty.handler.ResponseHandler;

/**
 * 系统工具类，用于获取系统相关信息
 * Created by kagome.
 */
public class IpUtil {
	private static final Logger log = LoggerFactory.getLogger(ResponseHandler.class);
    public static String LOCAL_IP = getIp(); // 内网IP

    /**
     * 获得外网IP
     * @return 外网IP
     * @throws IOException 
     */
    private static String getIp(){
    	if(Boolean.valueOf(PropertyUtil.instance().getProperty("internet.ip").toString())){
    		InputStream ins = null; 
			try {
				//http://ip.chinaz.com/getip.aspx
				URL url=new URL("http://members.3322.org/dyndns/getip");
				URLConnection con = url.openConnection();
				ins=con.getInputStream();
				InputStreamReader isReader = new InputStreamReader(ins, "GB2312");
				BufferedReader bReader = new BufferedReader(isReader);  
				StringBuffer webContent = new StringBuffer();  
				String str = null;  
				while ((str = bReader.readLine()) != null) {  
				    webContent.append(str);                                                                                           
				}  
				if(webContent!=null){
					return webContent.toString();
				}
			} catch (Exception e) {
				log.info("getInternetIp exception:",e);
			}finally {
				if (ins != null) {  
			        try {
						ins.close();
					} catch (IOException e) {
						log.info("close InputStream exception:",e);
					}  
			    } 
			}
    	}else{
    		try {
				return InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				log.info("getLocalHost exception:",e);
			}
    	}
    	return null;
    }
}
