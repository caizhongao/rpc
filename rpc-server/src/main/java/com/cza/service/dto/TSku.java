/*     */ package com.cza.service.dto;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ 
/*     */ public class TSku
/*     */ {
/*     */   private Long sid;
/*     */   private Long gid;
/*     */   private String goodsName;
/*     */   private String barcode;
/*     */   private BigDecimal price;
/*     */   private Long createTime;
/*     */   private Long updateTime;
/*     */   private String skuPic;
/*     */   private Integer status;
/*     */ 
/*     */   public Integer getStatus()
/*     */   {
/*  42 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Integer status)
/*     */   {
/*  51 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public Long getSid()
/*     */   {
/*  59 */     return this.sid;
/*     */   }
/*     */ 
/*     */   public void setSid(Long sid)
/*     */   {
/*  67 */     this.sid = sid;
/*     */   }
/*     */ 
/*     */   public Long getGid()
/*     */   {
/*  75 */     return this.gid;
/*     */   }
/*     */ 
/*     */   public void setGid(Long gid)
/*     */   {
/*  83 */     this.gid = gid;
/*     */   }
/*     */ 
/*     */   public String getGoodsName()
/*     */   {
/*  91 */     return this.goodsName;
/*     */   }
/*     */ 
/*     */   public void setGoodsName(String goodsName)
/*     */   {
/*  99 */     this.goodsName = goodsName;
/*     */   }
/*     */ 
/*     */   public String getBarcode()
/*     */   {
/* 107 */     return this.barcode;
/*     */   }
/*     */ 
/*     */   public void setBarcode(String barcode)
/*     */   {
/* 115 */     this.barcode = barcode;
/*     */   }
/*     */ 
/*     */   public BigDecimal getPrice()
/*     */   {
/* 123 */     return this.price;
/*     */   }
/*     */ 
/*     */   public void setPrice(BigDecimal price)
/*     */   {
/* 131 */     this.price = price;
/*     */   }
/*     */ 
/*     */   public Long getCreateTime()
/*     */   {
/* 139 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Long createTime)
/*     */   {
/* 147 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   public Long getUpdateTime()
/*     */   {
/* 155 */     return this.updateTime;
/*     */   }
/*     */ 
/*     */   public void setUpdateTime(Long updateTime)
/*     */   {
/* 163 */     this.updateTime = updateTime;
/*     */   }
/*     */ 
/*     */   public String getSkuPic()
/*     */   {
/* 171 */     return this.skuPic;
/*     */   }
/*     */ 
/*     */   public void setSkuPic(String skuPic)
/*     */   {
/* 179 */     this.skuPic = skuPic;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 193 */     return "TSku [sid=" + this.sid + ", gid=" + this.gid + ", goodsName=" + this.goodsName + ", barcode=" + this.barcode + ", price=" + this.price + ", createTime=" + this.createTime + ", updateTime=" + this.updateTime + ", skuPic=" + this.skuPic + ", status=" + this.status + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator.SKY-20141119XLJ\Downloads\rpc-server-0.0.1-SNAPSHOT.jar
 * Qualified Name:     com.cza.service.dto.TSku
 * JD-Core Version:    0.6.2
 */