/*     */ package com.cza.service.vo;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SkuVo
/*     */ {
/*     */   private Long sid;
/*     */   private BigDecimal price;
/*     */   private String barcode;
/*     */   private List<SkuAttrVo> attrs;
/*     */   private String goodsName;
/*     */   private String skuPic;
/*     */   private Long number;
/*     */   private Long gid;
/*     */   private Long stock;
/*     */ 
/*     */   public Long getGid()
/*     */   {
/*  45 */     return this.gid;
/*     */   }
/*     */ 
/*     */   public void setGid(Long gid)
/*     */   {
/*  58 */     this.gid = gid;
/*     */   }
/*     */ 
/*     */   public String getSkuPic()
/*     */   {
/*  70 */     return this.skuPic;
/*     */   }
/*     */ 
/*     */   public void setSkuPic(String skuPic)
/*     */   {
/*  82 */     this.skuPic = skuPic;
/*     */   }
/*     */ 
/*     */   public Long getStock()
/*     */   {
/*  93 */     return this.stock;
/*     */   }
/*     */ 
/*     */   public void setStock(Long stock)
/*     */   {
/* 104 */     this.stock = stock;
/*     */   }
/*     */ 
/*     */   public String getGoodsName()
/*     */   {
/* 114 */     return this.goodsName;
/*     */   }
/*     */ 
/*     */   public void setGoodsName(String goodsName)
/*     */   {
/* 124 */     this.goodsName = goodsName;
/*     */   }
/*     */ 
/*     */   public Long getSid()
/*     */   {
/* 133 */     return this.sid;
/*     */   }
/*     */ 
/*     */   public void setSid(Long sid)
/*     */   {
/* 142 */     this.sid = sid;
/*     */   }
/*     */ 
/*     */   public BigDecimal getPrice()
/*     */   {
/* 150 */     return this.price;
/*     */   }
/*     */ 
/*     */   public void setPrice(BigDecimal price)
/*     */   {
/* 158 */     this.price = price;
/*     */   }
/*     */ 
/*     */   public String getBarcode()
/*     */   {
/* 166 */     return this.barcode;
/*     */   }
/*     */ 
/*     */   public void setBarcode(String barcode)
/*     */   {
/* 174 */     this.barcode = barcode;
/*     */   }
/*     */ 
/*     */   public List<SkuAttrVo> getAttrs()
/*     */   {
/* 182 */     return this.attrs;
/*     */   }
/*     */ 
/*     */   public void setAttrs(List<SkuAttrVo> attrs)
/*     */   {
/* 190 */     this.attrs = attrs;
/*     */   }
/*     */ 
/*     */   public Long getNumber()
/*     */   {
/* 201 */     return this.number;
/*     */   }
/*     */ 
/*     */   public void setNumber(Long number)
/*     */   {
/* 212 */     this.number = number;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 229 */     return "SkuVo [sid=" + this.sid + ", price=" + this.price + ", barcode=" + this.barcode + ", attrs=" + this.attrs + ", goodsName=" + this.goodsName + ", skuPic=" + this.skuPic + ", number=" + this.number + ", stock=" + this.stock + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator.SKY-20141119XLJ\Downloads\rpc-server-0.0.1-SNAPSHOT.jar
 * Qualified Name:     com.cza.service.vo.SkuVo
 * JD-Core Version:    0.6.2
 */