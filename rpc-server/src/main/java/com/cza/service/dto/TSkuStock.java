/*    */ package com.cza.service.dto;
/*    */ 
/*    */ public class TSkuStock
/*    */ {
/*    */   private Long sid;
/*    */   private Long number;
/*    */   private Long stock;
/*    */ 
/*    */   public Long getSid()
/*    */   {
/* 32 */     return this.sid;
/*    */   }
/*    */ 
/*    */   public void setSid(Long sid)
/*    */   {
/* 40 */     this.sid = sid;
/*    */   }
/*    */ 
/*    */   public Long getNumber()
/*    */   {
/* 48 */     return this.number;
/*    */   }
/*    */ 
/*    */   public void setNumber(Long number)
/*    */   {
/* 56 */     this.number = number;
/*    */   }
/*    */ 
/*    */   public Long getStock()
/*    */   {
/* 64 */     return this.stock;
/*    */   }
/*    */ 
/*    */   public void setStock(Long stock)
/*    */   {
/* 72 */     this.stock = stock;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 85 */     return "TSkuStock [sid=" + this.sid + ", number=" + this.number + ", stock=" + this.stock + "]";
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator.SKY-20141119XLJ\Downloads\rpc-server-0.0.1-SNAPSHOT.jar
 * Qualified Name:     com.cza.service.dto.TSkuStock
 * JD-Core Version:    0.6.2
 */