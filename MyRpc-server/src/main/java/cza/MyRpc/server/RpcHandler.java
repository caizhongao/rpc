
    /**  
    * @Title: RpcHandler.java
    * @Package cza.MyRpc.server
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月8日上午11:08:11
    * @version V1.0  
    */
    
package cza.MyRpc.server;

import java.lang.reflect.InvocationTargetException;

import cza.MyRpc.common.Request;
import cza.MyRpc.common.Response;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
    * @ClassName: RpcHandler
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月8日上午11:08:11
    *
    */

public class RpcHandler extends SimpleChannelInboundHandler<Request>{

	
	
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
		Response resp=new Response();
		resp.setResult(handle(msg));
		resp.setRequestId(msg.getRequestId());
		ctx.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
	}
	private Object handle(Request msg){
		Object obj=RpcServer.serviceMap.get(msg.getServiceName());
		Object result=null;
		try {
			result = obj.getClass().getMethod(msg.getMethod(),msg.getArgTypes()).invoke(obj, msg.getArgs());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
