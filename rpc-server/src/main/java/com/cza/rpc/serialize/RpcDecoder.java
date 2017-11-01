
    /**  
    * @Title: RpcDecoder.java
    * @Package cza.MyRpc.server
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月10日上午10:50:56
    * @version V1.0  
    */
    
package com.cza.rpc.serialize;

import java.util.List;

import com.cza.rpc.dto.Request;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
    * @ClassName: RpcDecoder
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月10日上午10:50:56
    *
    */

public class RpcDecoder extends ByteToMessageDecoder{
	
	private Class<?> decodeClass;
	
	public RpcDecoder(Class<?> decodeClass) {
		super();
		this.decodeClass = decodeClass;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (dataLength < 0) {
            ctx.close();
        }
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);
        Object obj = SerializationUtil.deserialize(data,decodeClass);
        out.add(obj);
	}

}
