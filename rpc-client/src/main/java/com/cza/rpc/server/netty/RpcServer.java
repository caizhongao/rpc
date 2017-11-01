
    /**  
    * @Title: RpcServer.java
    * @Package cza.MyRpc.server
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月7日下午6:18:26
    * @version V1.0  
    */
    
package com.cza.rpc.server.netty;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.cza.rpc.dto.Request;
import com.cza.rpc.dto.Response;
import com.cza.rpc.serialize.RpcDecoder;
import com.cza.rpc.serialize.RpcEncoder;
import com.cza.rpc.server.netty.handler.RequestHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
    * @ClassName: RpcServer
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月7日下午6:18:26
    *
    */

public class RpcServer{
	private final Integer port=20881;
	private static RpcServer instance;
	private static Object lock=new Object();
	
	private  RpcServer(){}
	
	public static RpcServer instance(){
		return instance;
	}
	
	public static void init(){
		if(instance==null){
			synchronized (lock) {
				if(instance==null){
					instance=new RpcServer();
					instance.startServer();
				}
			}
		}
	}
	
	
	private void startServer(){
		EventLoopGroup bossGroup = new NioEventLoopGroup(3); // (1)
		EventLoopGroup workerGroup = new NioEventLoopGroup(3);
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<Channel>() {
					@Override
					protected void initChannel(Channel ch) throws Exception {
						ChannelPipeline cl = ch.pipeline();
						cl.addLast(new RpcDecoder(Response.class));
						cl.addLast(new RpcEncoder());
						cl.addLast(new RequestHandler());
					}
				});
		bootstrap.bind(port);
	} 


}
