
    /**  
    * @Title: test.java
    * @Package cza.MyRpc.client
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月8日下午2:44:11
    * @version V1.0  
    */
    
package com.cza;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cza.action.MyAction;
import com.cza.rpc.util.PropertyUtil;
import com.cza.service.vo.GoodsVo;



/**
    * @ClassName: test
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng 
    * @date 2017年2月8日下午2:44:11
    *
    */

public class AppMain {
	private static final Logger log = LoggerFactory.getLogger(AppMain.class);
	public static Long[] goodsIds={33703l,33704l,33705l,33706l,33707l,33708l,33709l,33710l,33711l,33712l,33713l,33714l,33715l,33716l};
	static ExecutorService fixedThreadPool =null ;
	public static void main(String[] args) throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InterruptedException, ExecutionException {
		Integer number=Integer.valueOf(PropertyUtil.instance().getProperty("thread.number").toString());
		log.info("init thread number:{}",number);
		fixedThreadPool= Executors.newFixedThreadPool(number); 
		@SuppressWarnings("resource")
		ApplicationContext context=new ClassPathXmlApplicationContext("application.xml");
		MyAction action=context.getBean(MyAction.class);
		Long start=System.currentTimeMillis();
		List<Future<Integer>>flist=new ArrayList<>();
		for(int i=50;i>0;i--){
			flist.add(fixedThreadPool.submit(new task(action,"test_"+i)));
		}
		for(Future<Integer> f:flist){
			f.get();
		}
		log.info("总耗时:{}",System.currentTimeMillis()-start);
	}

	
	static class task implements Callable<Integer>{
		private MyAction action;
		private String word;
		
		
		    /**
		     * 创建一个新的实例 task.
		     *
		     * @param action
		     */
		    
		public task(MyAction action,String word) {
			super();
			this.action = action;
			this.word=word;
		}

		@Override
		public Integer call() throws Exception {
			long startT=System.currentTimeMillis();
			for(int i=50;i>0;i--){
				try {
					action.queryGoods(AppMain.goodsIds[ThreadLocalRandom.current().nextInt(13)]);
				} catch (Exception e) {
					log.info("queryGoods has exception:",e);
				}
			}
			log.info("線程執行結束，thread:{}，耗时:{}",word,System.currentTimeMillis()-startT);
			return 0;
		}
	}
}
