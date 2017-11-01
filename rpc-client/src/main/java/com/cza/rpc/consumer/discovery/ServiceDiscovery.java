
    /**  
    * @Title: ServiceDiscovery.java
    * @Package cza.MyRpc.client
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月8日上午11:50:28
    * @version V1.0  
    */
    
package com.cza.rpc.consumer.discovery;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.util.StringUtils;

import com.cza.rpc.util.PropertyUtil;
import com.cza.rpc.util.TransportAddress;
import com.cza.rpc.util.ZookeeperManager;
import com.esotericsoftware.minlog.Log;



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
	private ServiceDiscovery(){}
	
	public static ServiceDiscovery init(){
		if(discovery==null){
			synchronized (lock) {
				if(discovery==null){
					discovery=new ServiceDiscovery();
					discovery.discoveryService();
				}
			}
		}
		return discovery;
	}
	
	private void discoveryService(){
		//获取父节点下面的所有service节点
		ZooKeeper zooKeeper=ZookeeperManager.instance().getZookeeper();
		String parentPath=PropertyUtil.instance().getProperty("zk.parentpath").toString();
		try {
			List<String> serviceNodeList  =zooKeeper.getChildren(parentPath, false);
			for(String serviceNode:serviceNodeList){
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
					List<TransportAddress>addrs=ServiceAddrManager.instance().getServiceAddrs(serviceNode);
					if(addrs==null){
						addrs=new ArrayList<>();
					}
					if(!StringUtils.isEmpty(addrStr)){
						addrs.add(new TransportAddress(addr.split(":")[0],Integer.valueOf(addr.split(":")[1])));
						ServiceAddrManager.instance().putServiceAddrs(serviceNode, addrs);
					}
					
				}
			}
		} catch (Exception e) {
			Log.info("discoveryService exception:",e);
		} 
	
	}
	

}
