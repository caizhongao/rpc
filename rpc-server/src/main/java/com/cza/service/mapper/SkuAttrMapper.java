package com.cza.service.mapper;

import com.cza.service.dto.TSkuAttr;
import java.util.List;

public abstract interface SkuAttrMapper
{
  public abstract List<TSkuAttr> batchQuerySkuAttrs(List<Long> paramList);
}

/* Location:           C:\Users\Administrator.SKY-20141119XLJ\Downloads\rpc-server-0.0.1-SNAPSHOT.jar
 * Qualified Name:     com.cza.service.mapper.SkuAttrMapper
 * JD-Core Version:    0.6.2
 */