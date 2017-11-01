
    /**  
    * @Title: BaseFuture.java
    * @Package com.cza.rpc.common
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年9月15日上午11:36:07
    * @version V1.0  
    */
    
package com.cza.rpc.dto;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cza.rpc.consumer.netty.client.RpcClient;

/**
    * @ClassName: BaseFuture
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年9月15日上午11:36:07
    *
    */

public class RespFuture {
	private Response resp;
	private CountDownLatch latch=new CountDownLatch(1);
	private volatile boolean flag=false;
	private long requestId;
	private static final Logger log = LoggerFactory.getLogger(RespFuture.class);
	
	/**
	 * 创建一个新的实例 RespFuture.
	 *
	 */

	public RespFuture(Long requestId) {
		this.requestId=requestId;
	}
	
	public Response getValue(){
		try {
			latch.await();
		} catch (InterruptedException e) {
			log.info("getValue exception:",e);
		}
		return resp;
	}
	//完成调用
	public void done(Response resp){
		this.resp=resp;
		RpcClient.instance().getFutureMap().remove(requestId);
		latch.countDown();
	}
	
	//判断调用是否完成
	public boolean isDone(){
		return flag;
	}
	
	
}
