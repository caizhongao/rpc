
    /**  
    * @Title: RpcClient.java
    * @Package cza.MyRpc.client
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月8日下午2:52:02
    * @version V1.0  
    */
    
package cza.MyRpc.client;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import cza.MyRpc.common.RespFuture;
import cza.MyRpc.common.Request;
import cza.MyRpc.common.Response;
import cza.MyRpc.common.RpcDecoder;
import cza.MyRpc.common.RpcEncoder;
import cza.MyRpc.common.TransportAddress;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
    * @ClassName: RpcClient
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月8日下午2:52:02
    *
    */

public class RpcClient{
	
	private  static RpcClient client;
	private static Object lock=new Object();
	
	private AtomicLong atomicLong=new AtomicLong(1);
	
	private Bootstrap bootstrap;
	
	private ConcurrentMap<TransportAddress,Channel> channelMap=new ConcurrentHashMap<>();
	
	
	private ConcurrentMap<Long,RespFuture> futureMap=new ConcurrentHashMap<>();
	/**
	 * 创建一个新的实例 RpcClient.
	 *
	 */

	
	
	public static RpcClient getInstance(){
		if(client==null){
			synchronized (lock) {
				if(client==null){
					client=new RpcClient();
				}
			}
		}
		return client;
	}
	
	
	/**
	* @return channelMap
	*/
	
	public ConcurrentMap<TransportAddress, Channel> getChannelMap() {
		return channelMap;
	}

	
	/**
	 * @param channelMap the channelMap to set
	 */
	
	public void setChannelMap(ConcurrentMap<TransportAddress, Channel> channelMap) {
		this.channelMap = channelMap;
	}

	
	/**
	* @return futureMap
	*/
	
	public ConcurrentMap<Long, RespFuture> getFutureMap() {
		return futureMap;
	}

	
	/**
	 * @param futureMap the futureMap to set
	 */
	
	public void setFutureMap(ConcurrentMap<Long, RespFuture> futureMap) {
		this.futureMap = futureMap;
	}

	private RpcClient() {
		EventLoopGroup group = new NioEventLoopGroup();
		bootstrap=new Bootstrap();
		try {
			bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {

				@Override
				protected void initChannel(Channel ch) throws Exception {
					ChannelPipeline cl=ch.pipeline();
					cl.addLast(new RpcEncoder(Request.class));
					cl.addLast(new RpcDecoder(Response.class));
					cl.addLast(new MessageHandler());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	    /**
	     * 创建一个新的实例 RpcClient.
	     *
	     * @param port
	     * @param host
	     */
	public void addConnect(TransportAddress... addresses) {
		try {
			for(TransportAddress address:addresses){
				ChannelFuture future=bootstrap.connect(address.getAddr(), address.getPort()).sync();
				Channel channel=future.channel();
				channelMap.put(address, channel);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
		public Channel getChannel(TransportAddress address){
			Channel channel=channelMap.get(address);
			if(channel==null){
				addConnect(address);
				channel=channelMap.get(address);
			}
			return channel;
		}
		
		public RespFuture send(Request request){
			request.setRequestId(atomicLong.getAndIncrement());
			RespFuture future=new RespFuture();
			futureMap.put(request.getRequestId(), future);
			try {
				ServiceDiscovery discovery=ServiceDiscovery.instance();
				String addr=discovery.getServiceAddr(request.getServiceName());
				if(addr==null){
					future.done(new Response(request.getRequestId(),new RuntimeException("请求的服务不存在"),null));
				}else{
					TransportAddress address=new TransportAddress(addr.split(":")[0],Integer.valueOf(addr.split(":")[1]));
					Channel channel=getChannel(address);
					if(channel==null){
						future.done(new Response(request.getRequestId(),new RuntimeException("服务节点不存在!"),null)); 
					}else{
						channel.writeAndFlush(request);
					}
				}
			} catch (Exception e) {
				future.done(new Response(request.getRequestId(),e,null)); 
				e.printStackTrace();
			}
			return future;
		}
	
}
