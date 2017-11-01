
    /**  
    * @Title: MessageHandler.java
    * @Package cza.MyRpc.client
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年9月15日下午12:22:33
    * @version V1.0  
    */
    
package com.cza.rpc.consumer.netty.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cza.rpc.consumer.netty.client.RpcClient;
import com.cza.rpc.dto.RespFuture;
import com.cza.rpc.dto.Response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
    * @ClassName: MessageHandler
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年9月15日下午12:22:33
    *
    */

public class ResponseHandler extends SimpleChannelInboundHandler<Response>{
	private static final Logger log = LoggerFactory.getLogger(ResponseHandler.class);
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Response msg){
		try {
			log.info("resp trace requestId:{},cost:{}",msg.getRequestId(),System.currentTimeMillis()-msg.getStartTime());
			RespFuture future=RpcClient.instance().getFutureMap().get(msg.getRequestId());
			if(future!=null){
				future.done(msg);
			}else{
				log.warn("requestId:{} has been done!",msg.getRequestId());
			}
		} catch (Exception e) {
			log.error("future.done resp:{} has exception,",msg,e);
		}
	}
}
