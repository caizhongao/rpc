package com.cza.service.mapper;

import com.cza.service.dto.TGoods;
import com.cza.service.vo.GoodsVo;

public abstract interface GoodsMapper
{
  public abstract TGoods queryGoods(GoodsVo paramGoodsVo);
}

/* Location:           C:\Users\Administrator.SKY-20141119XLJ\Downloads\rpc-server-0.0.1-SNAPSHOT.jar
 * Qualified Name:     com.cza.service.mapper.GoodsMapper
 * JD-Core Version:    0.6.2
 */