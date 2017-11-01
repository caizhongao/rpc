/*     */ package com.cza.service.dto;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ 
/*     */ public class TGoods
/*     */ {
/*     */   private Long gid;
/*     */   private String goodsName;
/*     */   private String goodsCode;
/*     */   private String goodsPic;
/*     */   private Long cid;
/*     */   private BigDecimal price;
/*     */   private Long createTime;
/*     */   private Long updateTime;
/*     */   private String status;
/*     */   private Long sales;
/*     */   private Integer goodsIndex;
/*     */ 
/*     */   public Integer getGoodsIndex()
/*     */   {
/*  45 */     return this.goodsIndex;
/*     */   }
/*     */ 
/*     */   public void setGoodsIndex(Integer goodsIndex)
/*     */   {
/*  57 */     this.goodsIndex = goodsIndex;
/*     */   }
/*     */ 
/*     */   public Long getSales()
/*     */   {
/*  68 */     return this.sales;
/*     */   }
/*     */ 
/*     */   public void setSales(Long sales)
/*     */   {
/*  79 */     this.sales = sales;
/*     */   }
/*     */ 
/*     */   public String getStatus()
/*     */   {
/*  89 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(String status)
/*     */   {
/*  99 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public String getGoodsPic()
/*     */   {
/* 108 */     return this.goodsPic;
/*     */   }
/*     */ 
/*     */   public void setGoodsPic(String goodsPic)
/*     */   {
/* 117 */     this.goodsPic = goodsPic;
/*     */   }
/*     */ 
/*     */   public Long getGid()
/*     */   {
/* 125 */     return this.gid;
/*     */   }
/*     */ 
/*     */   public void setGid(Long gid)
/*     */   {
/* 133 */     this.gid = gid;
/*     */   }
/*     */ 
/*     */   public String getGoodsName()
/*     */   {
/* 141 */     return this.goodsName;
/*     */   }
/*     */ 
/*     */   public void setGoodsName(String goodsName)
/*     */   {
/* 149 */     this.goodsName = goodsName;
/*     */   }
/*     */ 
/*     */   public String getGoodsCode()
/*     */   {
/* 157 */     return this.goodsCode;
/*     */   }
/*     */ 
/*     */   public void setGoodsCode(String goodsCode)
/*     */   {
/* 165 */     this.goodsCode = goodsCode;
/*     */   }
/*     */ 
/*     */   public Long getCid()
/*     */   {
/* 173 */     return this.cid;
/*     */   }
/*     */ 
/*     */   public void setCid(Long cid)
/*     */   {
/* 181 */     this.cid = cid;
/*     */   }
/*     */ 
/*     */   public BigDecimal getPrice()
/*     */   {
/* 189 */     return this.price;
/*     */   }
/*     */ 
/*     */   public void setPrice(BigDecimal price)
/*     */   {
/* 197 */     this.price = price;
/*     */   }
/*     */ 
/*     */   public Long getCreateTime()
/*     */   {
/* 205 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Long createTime)
/*     */   {
/* 213 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   public Long getUpdateTime()
/*     */   {
/* 221 */     return this.updateTime;
/*     */   }
/*     */ 
/*     */   public void setUpdateTime(Long updateTime)
/*     */   {
/* 229 */     this.updateTime = updateTime;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 245 */     return "TGoods [gid=" + this.gid + ", goodsName=" + this.goodsName + ", goodsCode=" + this.goodsCode + ", goodsPic=" + this.goodsPic + ", cid=" + this.cid + ", price=" + this.price + ", createTime=" + this.createTime + ", updateTime=" + this.updateTime + ", status=" + this.status + ", sales=" + this.sales + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator.SKY-20141119XLJ\Downloads\rpc-server-0.0.1-SNAPSHOT.jar
 * Qualified Name:     com.cza.service.dto.TGoods
 * JD-Core Version:    0.6.2
 */