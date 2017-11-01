
    /**  
    * @Title: ServiceTransport.java
    * @Package cza.MyRpc.client
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年9月20日下午4:55:51
    * @version V1.0  
    */
    
package com.cza.rpc.consumer.netty.client;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cza.rpc.consumer.discovery.ServiceAddrManager;
import com.cza.rpc.consumer.netty.handler.ResponseHandler;
import com.cza.rpc.dto.Request;
import com.cza.rpc.dto.RespFuture;
import com.cza.rpc.dto.Response;
import com.cza.rpc.serialize.RpcDecoder;
import com.cza.rpc.serialize.RpcEncoder;
import com.cza.rpc.util.TransportAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
    * @ClassName: ServiceTransport
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年9月20日下午4:55:51
    *
    */

public class RpcClient {
	private Bootstrap bootstrap;
	private static RpcClient transport;
	private static Object lock=new Object();
	private ConcurrentMap<TransportAddress,Channel> channelMap=new ConcurrentHashMap<>();
	private static final Logger log = LoggerFactory.getLogger(RpcClient.class);
	
	private ConcurrentMap<Long,RespFuture> futureMap=new ConcurrentHashMap<>();
	
	private AtomicLong atomicLong=new AtomicLong(1);
	
	public static void init(){
		if(transport==null){
			synchronized (lock) {
				if(transport==null){
					transport=new RpcClient();
				}
			}
		}
	}

	public static RpcClient instance(){
		return transport;
	}
	public Channel addConnect(TransportAddress... addresses) {
		try {
			for(TransportAddress address:addresses){
				ChannelFuture future=bootstrap.connect(address.getAddr(), address.getPort()).sync();
				Channel channel=future.channel();
				channelMap.put(address, channel);
				return channel;
			}
		} catch (InterruptedException e) {
			log.info("addConnect exception:",e);
		}
		return null;
	}

	private RpcClient() {
		EventLoopGroup group = new NioEventLoopGroup(5);
		bootstrap=new Bootstrap();
		try {
			//初始化客户端
			bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {

				@Override
				protected void initChannel(Channel ch) throws Exception {
					ChannelPipeline cl=ch.pipeline();
					cl.addLast(new RpcEncoder());
					cl.addLast(new RpcDecoder(Response.class));
					cl.addLast(new ResponseHandler());
				}
			});
		} catch (Exception e) {
			log.info("TransportService exception:",e);
		}
		
	}
	
	public Channel getChannel(TransportAddress address){
		Channel channel=channelMap.get(address);
		if(channel==null){
			channel=addConnect(address);
		}
		return channel;
	}
	
	public RespFuture send(Request request){
		Long requestId=atomicLong.getAndIncrement();
		request.setRequestId(requestId);
		RespFuture future=new RespFuture(requestId);
		futureMap.put(requestId, future);
		try {
			TransportAddress address=ServiceAddrManager.instance().getServiceAddr(request.getServiceName());
			if(address==null){
				future.done(new Response(requestId,new RuntimeException("请求的服务不存在"),null,System.currentTimeMillis()));
			}else{
				Channel channel=getChannel(address);
				if(channel==null){
					future.done(new Response(requestId,new RuntimeException("服务节点不存在!"),null,System.currentTimeMillis())); 
				}else{
					channel.writeAndFlush(request);
				}
			}
		} catch (Exception e) {
			future.done(new Response(request.getRequestId(),e,null,System.currentTimeMillis())); 
			log.info("send request exception:",e);
		}
		return future;
	}
	
	
	public ConcurrentMap<TransportAddress, Channel> getChannelMap() {
		return channelMap;
	}

	public void setChannelMap(ConcurrentMap<TransportAddress, Channel> channelMap) {
		this.channelMap = channelMap;
	}

	public ConcurrentMap<Long, RespFuture> getFutureMap() {
		return futureMap;
	}

	public void setFutureMap(ConcurrentMap<Long, RespFuture> futureMap) {
		this.futureMap = futureMap;
	}
}
