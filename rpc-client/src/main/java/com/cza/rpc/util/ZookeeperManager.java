
    /**  
    * @Title: ZookeeperManager.java
    * @Package com.cza.rpc.zookeeper
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年9月27日下午3:29:09
    * @version V1.0  
    */
    
package com.cza.rpc.util;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cza.rpc.server.registry.ServiceRegistry;
import com.esotericsoftware.minlog.Log;

/**
    * @ClassName: ZookeeperManager
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年9月27日下午3:29:09
    *
    */

public class ZookeeperManager {
	private final Logger log = LoggerFactory.getLogger(ZookeeperManager.class);
	private static Object lock=new Object();
	private static ZookeeperManager instance;
	private  CountDownLatch latch=new CountDownLatch(1);
	private  ZooKeeper zooKeeper;
	
	private ZookeeperManager(){
		try {
			zooKeeper=new ZooKeeper(PropertyUtil.instance().getProperty("zk.url").toString(), 5000, new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					latch.countDown();
				}
			});
			latch.await();
		} catch (Exception e) {
			log.info("connect zk exception:",e);
		}
	}
	
	
	public static ZookeeperManager instance(){
		if(instance==null){
			synchronized (lock) {
				if(instance==null){
					instance=new ZookeeperManager();
				}
			}
		}
		return instance;
	}
	
	
	public ZooKeeper getZookeeper(){
		return zooKeeper;
	}
	
	
}
