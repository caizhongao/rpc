
    /**  
    * @Title: MessageHandler.java
    * @Package cza.MyRpc.client
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年9月15日下午12:22:33
    * @version V1.0  
    */
    
package cza.MyRpc.client;

import cza.MyRpc.common.RespFuture;
import cza.MyRpc.common.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
    * @ClassName: MessageHandler
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年9月15日下午12:22:33
    *
    */

public class MessageHandler extends SimpleChannelInboundHandler<Response>{
    /* (非 Javadoc)
    * 
    * 
    * @param ctx
    * @param msg
    * @throws Exception
    * @see io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty.channel.ChannelHandlerContext, java.lang.Object)
    */
    
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Response msg) throws Exception {
		RespFuture future=RpcClient.getInstance().getFutureMap().get(msg.getRequestId());
		future.done(msg);
	}
}
