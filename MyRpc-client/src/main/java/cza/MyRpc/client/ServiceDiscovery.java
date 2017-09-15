
    /**  
    * @Title: ServiceDiscovery.java
    * @Package cza.MyRpc.client
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月8日上午11:50:28
    * @version V1.0  
    */
    
package cza.MyRpc.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import cza.MyRpc.common.ZkContant;


/**
    * @ClassName: ServiceDiscovery
    * @Description: 服务发现程序
    * @author mufeng
    * @date 2017年2月8日上午11:50:28
    *
    */

public class ServiceDiscovery {
	private static ServiceDiscovery discovery;
	private static Object lock=new Object();
	private Map<String,List<String>>serviceList;
	private ZooKeeper zooKeeper;
	private CountDownLatch latch=new CountDownLatch(1);
	
	private ServiceDiscovery(){
		connectServer("120.77.65.75:2181,120.77.65.75:2182,120.77.65.75:2183");
		discoveryService();
	}
	
	public static ServiceDiscovery instance(){
		if(discovery==null){
			synchronized (lock) {
				if(discovery==null){
					discovery=new ServiceDiscovery();
				}
			}
		}
		return discovery;
	}
	
	public void connectServer(String url){
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
	
	public void discoveryService(){
		serviceList=new HashMap<>();
		//获取父节点下面的所有service节点
		String parentPath=ZkContant.ZK_PARENT_PATH;
		try {
			List<String> serviceNodeList  =zooKeeper.getChildren(parentPath, false);
			for(String serviceNode:serviceNodeList){
				System.out.println("service==>"+serviceNode);
				//获取service节点下面所有的addr节点
				String servicePath=parentPath+"/"+serviceNode;
				List<String> addrNodeList  =zooKeeper.getChildren(servicePath,new Watcher() {
					@Override
					public void process(WatchedEvent event) {
						if(event.getType() == Event.EventType.NodeChildrenChanged){
							discoveryService();
						}
					}
				});
				for(String addr:addrNodeList){
					String serviceAddrPath=servicePath+"/"+addr;
					byte[] bytes =zooKeeper.getData(serviceAddrPath,false,null);
					String addrStr=new String(bytes);
					System.out.println("addrStr==>"+addrStr);
					List<String>addrs=serviceList.get(serviceNode);
					if(addrs==null){
						addrs=new ArrayList<>();
					}
					addrs.add(addrStr);
					serviceList.put(serviceNode, addrs);
				}
			}
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getServiceAddr(String servieName){
		if(serviceList!=null){
			List<String>addrs=serviceList.get(servieName);
			if(addrs==null){
				return null;
			}else{
				int size=addrs.size();
				return addrs.get(ThreadLocalRandom.current().nextInt(size));
			}
		}
		return null;
	}
}
