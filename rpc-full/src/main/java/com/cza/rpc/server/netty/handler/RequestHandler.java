
    /**  
    * @Title: RpcHandler.java
    * @Package cza.MyRpc.server
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月8日上午11:08:11
    * @version V1.0  
    */
    
package com.cza.rpc.server.netty.handler;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cza.rpc.dto.Request;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
    * @ClassName: RpcHandler
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月8日上午11:08:11
    *
    */


public class RequestHandler extends SimpleChannelInboundHandler<Request>{
	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
	
    /* (非 Javadoc)
    * 
    * 
    * @param ctx
    * @param msg
    * @throws Exception
    * @see io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty.channel.ChannelHandlerContext, java.lang.Object)
    */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
		//handleRequest(msg,ctx);
		log.info("trace requestId:{},cost:{}",msg.getRequestId(),System.currentTimeMillis()-msg.getStartTime());
		msg.setStartTime(System.currentTimeMillis());
		RequestExecutor.instance().handleRequest(msg, ctx);
		
	}
	
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
