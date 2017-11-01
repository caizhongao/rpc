
    /**  
    * @Title: RpcEncoder.java
    * @Package cza.MyRpc.server
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月10日上午10:48:49
    * @version V1.0  
    */
    
package com.cza.rpc.serialize;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
    * @ClassName: RpcEncoder
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月10日上午10:48:49
    *
    */

public class RpcEncoder extends MessageToByteEncoder{
	
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        byte[] data = SerializationUtil.serialize(msg);
        out.writeInt(data.length);
        out.writeBytes(data);
	}

		 


}
