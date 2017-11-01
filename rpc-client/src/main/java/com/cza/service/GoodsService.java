
    /**  
    * @Title: GoodsService.java
    * @Package com.cza.service.goods
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月22日下午6:15:11
    * @version V1.0  
    */
    
package com.cza.service;


import com.cza.service.vo.GoodsVo;


/**
    * @ClassName: GoodsService
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月22日下午6:15:11
    *
    */

public interface GoodsService {
				
	    /**
	    * @Title: queryGoods
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param param
	    * @param @return    参数
	    * @return ServiceResponse<List<GoodsVo>>    返回类型
	    * @throws
	    */
		GoodsVo queryGoods(Long gid);
	
}
