/*     */ package com.cza.service.vo;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.util.List;
/*     */ 
/*     */ public class GoodsVo
/*     */ {
/*     */   private Long gid;
/*     */   private String goodsName;
/*     */   private String goodsCode;
/*     */   private String goodsPic;
/*     */   private Long cid;
/*     */   private String categoryName;
/*     */   private BigDecimal price;
/*     */   private Long createTime;
/*     */   private Long updateTime;
/*     */   private Long sales;
/*     */   private Long stock;
/*     */   private String status;
/*     */   private List<SkuVo> skus;
/*     */   private Integer goodsIndex;
/*     */   private String searchKey;
/*  43 */   private Integer pageNum = Integer.valueOf(1);
/*  44 */   private Integer pageSize = Integer.valueOf(15);
/*  45 */   private Integer start = Integer.valueOf(0);
/*     */ 
/*  48 */   private Integer scrollPage = Integer.valueOf(0);
/*     */   private String scrollId;
/*     */   private Long count;
/*     */ 
/*     */   public Long getCount()
/*     */   {
/*  88 */     return this.count;
/*     */   }
/*     */ 
/*     */   public void setCount(Long count)
/*     */   {
/* 108 */     this.count = count;
/*     */   }
/*     */ 
/*     */   public Integer getScrollPage()
/*     */   {
/* 127 */     return this.scrollPage;
/*     */   }
/*     */ 
/*     */   public void setScrollPage(Integer scrollPage)
/*     */   {
/* 146 */     this.scrollPage = scrollPage;
/*     */   }
/*     */ 
/*     */   public String getScrollId()
/*     */   {
/* 164 */     return this.scrollId;
/*     */   }
/*     */ 
/*     */   public void setScrollId(String scrollId)
/*     */   {
/* 181 */     this.scrollId = scrollId;
/*     */   }
/*     */ 
/*     */   public String getSearchKey()
/*     */   {
/* 197 */     return this.searchKey;
/*     */   }
/*     */ 
/*     */   public void setSearchKey(String searchKey)
/*     */   {
/* 213 */     this.searchKey = searchKey;
/*     */   }
/*     */ 
/*     */   public Integer getPageNum()
/*     */   {
/* 228 */     return this.pageNum;
/*     */   }
/*     */ 
/*     */   public void setPageNum(Integer pageNum)
/*     */   {
/* 243 */     this.pageNum = pageNum;
/*     */   }
/*     */ 
/*     */   public Integer getPageSize()
/*     */   {
/* 258 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(Integer pageSize)
/*     */   {
/* 273 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public Integer getStart()
/*     */   {
/* 288 */     return this.start;
/*     */   }
/*     */ 
/*     */   public void setStart(Integer start)
/*     */   {
/* 303 */     this.start = start;
/*     */   }
/*     */ 
/*     */   public Integer getGoodsIndex()
/*     */   {
/* 317 */     return this.goodsIndex;
/*     */   }
/*     */ 
/*     */   public void setGoodsIndex(Integer goodsIndex)
/*     */   {
/* 331 */     this.goodsIndex = goodsIndex;
/*     */   }
/*     */ 
/*     */   public Long getStock()
/*     */   {
/* 344 */     return this.stock;
/*     */   }
/*     */ 
/*     */   public void setStock(Long stock)
/*     */   {
/* 357 */     this.stock = stock;
/*     */   }
/*     */ 
/*     */   public Long getSales()
/*     */   {
/* 365 */     return this.sales;
/*     */   }
/*     */ 
/*     */   public void setSales(Long sales)
/*     */   {
/* 373 */     this.sales = sales;
/*     */   }
/*     */ 
/*     */   public String getCategoryName()
/*     */   {
/* 385 */     return this.categoryName;
/*     */   }
/*     */ 
/*     */   public void setCategoryName(String categoryName)
/*     */   {
/* 397 */     this.categoryName = categoryName;
/*     */   }
/*     */ 
/*     */   public String getStatus()
/*     */   {
/* 408 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(String status)
/*     */   {
/* 419 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public List<SkuVo> getSkus()
/*     */   {
/* 429 */     return this.skus;
/*     */   }
/*     */ 
/*     */   public void setSkus(List<SkuVo> skus)
/*     */   {
/* 439 */     this.skus = skus;
/*     */   }
/*     */ 
/*     */   public String getGoodsPic()
/*     */   {
/* 448 */     return this.goodsPic;
/*     */   }
/*     */ 
/*     */   public void setGoodsPic(String goodsPic)
/*     */   {
/* 457 */     this.goodsPic = goodsPic;
/*     */   }
/*     */ 
/*     */   public Long getGid()
/*     */   {
/* 465 */     return this.gid;
/*     */   }
/*     */ 
/*     */   public void setGid(Long gid)
/*     */   {
/* 473 */     this.gid = gid;
/*     */   }
/*     */ 
/*     */   public String getGoodsName()
/*     */   {
/* 481 */     return this.goodsName;
/*     */   }
/*     */ 
/*     */   public void setGoodsName(String goodsName)
/*     */   {
/* 489 */     this.goodsName = goodsName;
/*     */   }
/*     */ 
/*     */   public String getGoodsCode()
/*     */   {
/* 497 */     return this.goodsCode;
/*     */   }
/*     */ 
/*     */   public void setGoodsCode(String goodsCode)
/*     */   {
/* 505 */     this.goodsCode = goodsCode;
/*     */   }
/*     */ 
/*     */   public Long getCid()
/*     */   {
/* 513 */     return this.cid;
/*     */   }
/*     */ 
/*     */   public void setCid(Long cid)
/*     */   {
/* 521 */     this.cid = cid;
/*     */   }
/*     */ 
/*     */   public BigDecimal getPrice()
/*     */   {
/* 529 */     return this.price;
/*     */   }
/*     */ 
/*     */   public void setPrice(BigDecimal price)
/*     */   {
/* 537 */     this.price = price;
/*     */   }
/*     */ 
/*     */   public Long getCreateTime()
/*     */   {
/* 545 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Long createTime)
/*     */   {
/* 553 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   public Long getUpdateTime()
/*     */   {
/* 561 */     return this.updateTime;
/*     */   }
/*     */ 
/*     */   public void setUpdateTime(Long updateTime)
/*     */   {
/* 569 */     this.updateTime = updateTime;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 594 */     return "GoodsVo [gid=" + this.gid + ", goodsName=" + this.goodsName + ", goodsCode=" + this.goodsCode + ", goodsPic=" + this.goodsPic + ", cid=" + this.cid + ", categoryName=" + this.categoryName + ", price=" + this.price + ", createTime=" + this.createTime + ", updateTime=" + this.updateTime + ", sales=" + this.sales + ", stock=" + this.stock + ", status=" + this.status + ", skus=" + this.skus + ", goodsIndex=" + this.goodsIndex + ", searchKey=" + this.searchKey + ", pageNum=" + this.pageNum + ", pageSize=" + this.pageSize + ", start=" + this.start + ", scrollPage=" + this.scrollPage + ", scrollId=" + this.scrollId + ", count=" + this.count + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator.SKY-20141119XLJ\Downloads\rpc-server-0.0.1-SNAPSHOT.jar
 * Qualified Name:     com.cza.service.vo.GoodsVo
 * JD-Core Version:    0.6.2
 */