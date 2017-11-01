/*     */ package com.cza.service.dto;
/*     */ 
/*     */ public class TCategoryAttr
/*     */ {
/*     */   private Long caid;
/*     */   private Long cid;
/*     */   private String attrName;
/*     */   private Integer status;
/*     */ 
/*     */   public Integer getStatus()
/*     */   {
/*  36 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Integer status)
/*     */   {
/*  45 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public Long getCaid()
/*     */   {
/*  53 */     return this.caid;
/*     */   }
/*     */ 
/*     */   public void setCaid(Long caid)
/*     */   {
/*  61 */     this.caid = caid;
/*     */   }
/*     */ 
/*     */   public Long getCid()
/*     */   {
/*  69 */     return this.cid;
/*     */   }
/*     */ 
/*     */   public void setCid(Long cid)
/*     */   {
/*  77 */     this.cid = cid;
/*     */   }
/*     */ 
/*     */   public String getAttrName()
/*     */   {
/*  85 */     return this.attrName;
/*     */   }
/*     */ 
/*     */   public void setAttrName(String attrName)
/*     */   {
/*  93 */     this.attrName = attrName;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 107 */     return "TCategoryAttr [caid=" + this.caid + ", cid=" + this.cid + ", attrName=" + this.attrName + ", status=" + this.status + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator.SKY-20141119XLJ\Downloads\rpc-server-0.0.1-SNAPSHOT.jar
 * Qualified Name:     com.cza.service.dto.TCategoryAttr
 * JD-Core Version:    0.6.2
 */