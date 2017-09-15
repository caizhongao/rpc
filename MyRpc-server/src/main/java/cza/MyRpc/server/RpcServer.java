
    /**  
    * @Title: RpcServer.java
    * @Package cza.MyRpc.server
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月7日下午6:18:26
    * @version V1.0  
    */
    
package cza.MyRpc.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cza.MyRpc.common.Request;
import cza.MyRpc.common.Response;
import cza.MyRpc.common.RpcDecoder;
import cza.MyRpc.common.RpcEncoder;
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

public class RpcServer implements ApplicationContextAware, InitializingBean{
	private final Integer port=20880;
	public static Map<String,Object> serviceMap=new HashMap<String,Object>();
    /* (非 Javadoc)
    * 
    * 
    * @throws Exception
    * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
    */
	@Override
	public void afterPropertiesSet(){
		EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<Channel>() {
						@Override
						protected void initChannel(Channel ch) throws Exception {
							ChannelPipeline cl = ch.pipeline();
							cl.addLast(new RpcDecoder(Request.class));
							cl.addLast(new RpcEncoder(Response.class));
							cl.addLast(new RpcHandler());
						}
					});
			ChannelFuture future = bootstrap.bind(port).sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param applicationContext
	    * @throws BeansException
	    * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	    */
	    
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		//注册服务
		ServiceRegistry registry=new ServiceRegistry("120.77.65.75:2181,120.77.65.75:2182,120.77.65.75:2183");
		Map<String, Object> serviceBeanMap=applicationContext.getBeansWithAnnotation(RpcService.class);
		for(Object bean:serviceBeanMap.values()){
			String serviceName=bean.getClass().getInterfaces()[0].getName();
			serviceMap.put(serviceName, bean);
			try {
				registry.RegisterService(serviceName,InetAddress.getLocalHost().getHostAddress()+":"+port );
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
	}

}
