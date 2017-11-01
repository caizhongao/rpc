
    /**  
    * @Title: MyAction.java
    * @Package cza.MyRpc.service
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年9月20日下午4:22:52
    * @version V1.0  
    */
    
package com.cza.action;

import org.springframework.stereotype.Service;

import com.cza.rpc.annotation.RpcConsumer;
import com.cza.service.GoodsService;
import com.cza.service.vo.GoodsVo;

/**
    * @ClassName: MyAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年9月20日下午4:22:52
    *
    */
@Service
public class MyAction {
	@RpcConsumer
	private GoodsService myservice;
	public GoodsVo queryGoods(Long gid){
		GoodsVo goodsVo=myservice.queryGoods(gid);
		return goodsVo;
	}
	
	
}
