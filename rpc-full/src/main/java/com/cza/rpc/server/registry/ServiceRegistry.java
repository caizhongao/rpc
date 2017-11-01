
    /**  
    * @Title: ServiceRegistry.java
    * @Package cza.MyRpc.server
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年1月22日下午5:21:47
    * @version V1.0  
    */
    
package com.cza.rpc.server.registry;


import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cza.rpc.util.PropertyUtil;
import com.cza.rpc.util.ZookeeperManager;


/**
    * @ClassName: ServiceRegistry
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年1月22日下午5:21:47
    *
    */

public class ServiceRegistry {
	private final Logger log = LoggerFactory.getLogger(ServiceRegistry.class);
	
	public void RegisterService(String serviceName,String addr){
		try {
			ZooKeeper zooKeeper=ZookeeperManager.instance().getZookeeper();
			log.info("registerService param==>serviceName:{},addr:{}",serviceName,addr);
			//创建目录（持久）
			String parentPath=PropertyUtil.instance().getProperty("zk.parentpath").toString();
			if(zooKeeper.exists(parentPath, true)==null){
				log.info("create zk parentPath:{}",parentPath);
				zooKeeper.create(parentPath, parentPath.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
			//创建service目录（持久）
			String servicePath=parentPath+"/"+serviceName;
			if(zooKeeper.exists(servicePath, true)==null){
				log.info("create zk servicePath:{}",servicePath);
				zooKeeper.create(servicePath, servicePath.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
			//创建service节点（临时）
			String serviceAddrPath=servicePath+"/"+addr;
			log.info("create zk serviceAddrPath:{}",serviceAddrPath);
			zooKeeper.create(serviceAddrPath, addr.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		} catch (Exception e) {
			log.info("create zk path exception:",e);
		} 
	}
}
