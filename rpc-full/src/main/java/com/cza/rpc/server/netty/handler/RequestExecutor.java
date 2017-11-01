
    /**  
    * @Title: Excutor.java
    * @Package cza.MyRpc.server
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年9月22日下午12:08:22
    * @version V1.0  
    */
    
package com.cza.rpc.server.netty.handler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cza.rpc.dto.Request;
import com.cza.rpc.dto.Response;
import com.cza.rpc.server.registry.ServiceManager;
import com.cza.rpc.util.PropertyUtil;

import io.netty.channel.ChannelHandlerContext;

/**
    * @ClassName: Excutor
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年9月22日下午12:08:22
    *
    */

public class RequestExecutor {
	private final Logger log = LoggerFactory.getLogger(RequestExecutor.class);
	private static RequestExecutor executor;
	private static Object lock=new Object();
	
	private ExecutorService fixedThreadPool = null; 
	private Long sleep_time=1L;
	private BlockingQueue<RequestTask> taskList = new LinkedBlockingQueue<>(100);
	
	private RequestExecutor(){}
	
	public static RequestExecutor instance(){
		return executor;
	}
	
	
	public static void init(){
		if(executor==null){
			synchronized (lock) {
				if(executor==null){
					executor=new RequestExecutor();
					executor.start();
				}
			}
		}
	}
	
	private void start(){
		Integer num=Integer.valueOf(PropertyUtil.instance().getProperty("thread.number").toString());
		sleep_time=Long.valueOf(PropertyUtil.instance().getProperty("sleep.time").toString());
		log.info("init thread size:{}",num);
		fixedThreadPool=Executors.newFixedThreadPool(num);
		for(int i=0;i<num;i++){
			fixedThreadPool.execute(new MessageTask());
		}
	}
	
	
	public void handleRequest(Request request,ChannelHandlerContext ctx){
		if(!taskList.offer(new RequestTask(ctx, request))){
			log.info("taskList is full!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				log.info("sleep has expcetion:",e);
			}
		}
	}
	
	class RequestTask{
		private ChannelHandlerContext context;
		 private Request request;
		    /**
		     * 创建一个新的实例 RequestTask.
		     *
		     * @param context
		     * @param request
		     */
		 public RequestTask(ChannelHandlerContext context, Request request) {
			super();
			this.context = context;
			this.request = request;
		 }
			
		public ChannelHandlerContext getContext() {
			return context;
		}
		public void setContext(ChannelHandlerContext context) {
			this.context = context;
		}
		public Request getRequest() {
			return request;
		}
		public void setRequest(Request request) {
			this.request = request;
		}
		 
	}
	
	 class MessageTask implements Runnable{
		public void run() {
			while(true){
				RequestTask task=taskList.poll();
				if(task==null){
					try {
						Thread.sleep(sleep_time);
					} catch (InterruptedException e) {
						log.info("sleep erro:",e);
					}
					continue;
				}
				ChannelHandlerContext context=task.getContext();
				Request request=task.getRequest();
				log.info("excute begin requestId:{},cost time:{}",request.getRequestId(),System.currentTimeMillis()-request.getStartTime());
				Object service=ServiceManager.instance().getService(request.getServiceName());
				Response resp=null;
				try {
					request.setStartTime(System.currentTimeMillis());
					Object result=service.getClass().getMethod(request.getMethod(),request.getArgTypes()).invoke(service, request.getArgs());
					log.info("invoke query,requestId:{},cost:{}",request.getRequestId(),System.currentTimeMillis()-request.getStartTime());
					resp=new Response(request.getRequestId(),null,result,System.currentTimeMillis());
				} catch (Exception e) {
					log.info("invoke exception,",e);
					resp=new Response(request.getRequestId(),e,null,System.currentTimeMillis());
				}
				context.writeAndFlush(resp);
			}
		}
	}
	
	
}
