package com.cza.service.mapper;

import com.cza.service.dto.TSku;
import java.util.List;

public abstract interface SkuMapper
{
  public abstract List<TSku> listSkus(TSku paramTSku);
}

/* Location:           C:\Users\Administrator.SKY-20141119XLJ\Downloads\rpc-server-0.0.1-SNAPSHOT.jar
 * Qualified Name:     com.cza.service.mapper.SkuMapper
 * JD-Core Version:    0.6.2
 */