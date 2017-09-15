
    /**  
    * @Title: ServiceRegistry.java
    * @Package cza.MyRpc.server
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年1月22日下午5:21:47
    * @version V1.0  
    */
    
package cza.MyRpc.server;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import cza.MyRpc.common.ZkContant;

/**
    * @ClassName: ServiceRegistry
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年1月22日下午5:21:47
    *
    */

public class ServiceRegistry {
	private  CountDownLatch latch=new CountDownLatch(1);
	
	private  ZooKeeper zooKeeper;
	public ServiceRegistry(String url){
		 try {
			zooKeeper=new ZooKeeper("120.77.65.75:2181,120.77.65.75:2182,120.77.65.75:2183", 5000, new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					latch.countDown();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		 try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void RegisterService(String serviceName,String addr){
		try {
			//创建目录（持久）
			String parentPath=ZkContant.ZK_PARENT_PATH;
			if(zooKeeper.exists(parentPath, true)==null){
				zooKeeper.create(parentPath, parentPath.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
			//创建service目录（持久）
			String servicePath=parentPath+"/"+serviceName;
			if(zooKeeper.exists(servicePath, true)==null){
				zooKeeper.create(servicePath, servicePath.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
			//创建service节点（临时）
			String serviceAddrPath=servicePath+"/"+addr;
			zooKeeper.create(serviceAddrPath, addr.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		ServiceRegistry registry=new ServiceRegistry("120.77.65.75:2181,120.77.65.75:2182,120.77.65.75:2183");
		registry.RegisterService("userService", "192.168.1.1:80");
		registry.RegisterService("userService", "192.168.1.2:80");
		System.in.read();
	}
}
