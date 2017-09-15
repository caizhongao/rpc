
    /**  
    * @Title: RpcEncoder.java
    * @Package cza.MyRpc.server
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月10日上午10:48:49
    * @version V1.0  
    */
    
package cza.MyRpc.common;

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

	   private Class<?> genericClass;

	    public RpcEncoder(Class<?> genericClass) {
	        this.genericClass = genericClass;
	    }
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param ctx
	    * @param msg
	    * @param out
	    * @throws Exception
	    * @see io.netty.handler.codec.MessageToByteEncoder#encode(io.netty.channel.ChannelHandlerContext, java.lang.Object, io.netty.buffer.ByteBuf)
	    */
	    
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		if (genericClass.isInstance(msg)) {
            byte[] data = SerializationUtil.serialize(msg);
            out.writeInt(data.length);
            out.writeBytes(data);
        }

	}

}
