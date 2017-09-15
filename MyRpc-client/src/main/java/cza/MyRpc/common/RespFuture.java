
    /**  
    * @Title: BaseFuture.java
    * @Package cza.MyRpc.common
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年9月15日上午11:36:07
    * @version V1.0  
    */
    
package cza.MyRpc.common;

import cza.MyRpc.client.RpcClient;

/**
    * @ClassName: BaseFuture
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年9月15日上午11:36:07
    *
    */

public class RespFuture {
	private Response resp;
	
	private volatile boolean flag=false;

	
	public Response getValue() throws InterruptedException {
		while(!isDone()){
		}
		RpcClient.getInstance().getFutureMap().remove(resp.getRequestId());
		return resp;
	}
	//完成调用
	public void done(Response resp){
		this.resp=resp;
		flag=true;
	}
	
	//判断调用是否完成
	public boolean isDone(){
		return flag;
	}
}
