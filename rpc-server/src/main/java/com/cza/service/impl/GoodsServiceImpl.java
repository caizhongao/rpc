package com.cza.service.impl;

import com.cza.rpc.annotation.RpcService;

import com.cza.service.GoodsService;
import com.cza.service.dto.TCategoryAttr;
import com.cza.service.dto.TGoods;
import com.cza.service.dto.TSku;
import com.cza.service.dto.TSkuAttr;
import com.cza.service.dto.TSkuStock;
import com.cza.service.mapper.CategoryAttrMapper;
import com.cza.service.mapper.GoodsMapper;
import com.cza.service.mapper.SkuAttrMapper;
import com.cza.service.mapper.SkuMapper;
import com.cza.service.mapper.SkuStockMapper;
import com.cza.service.vo.GoodsVo;
import com.cza.service.vo.SkuAttrVo;
import com.cza.service.vo.SkuVo;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RpcService
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private CategoryAttrMapper attrMapper;

	@Autowired
	private SkuStockMapper stockMapper;

	@Autowired
	private SkuMapper skuMapper;

	@Autowired
	private SkuAttrMapper skuAttrMapper;
	private static final Logger log = LoggerFactory.getLogger(GoodsServiceImpl.class);

	public GoodsVo queryGoods(Long gid) {
		GoodsVo goodsVo = new GoodsVo();
		try {
			GoodsVo param = new GoodsVo();
			param.setGid(gid);
			TGoods goods = this.goodsMapper.queryGoods(param);
			goodsVo.setCid(goods.getCid());
			goodsVo.setGid(goods.getGid());
			goodsVo.setGoodsCode(goods.getGoodsCode());
			goodsVo.setGoodsName(goods.getGoodsName());
			goodsVo.setGoodsPic(goods.getGoodsPic());
			goodsVo.setPrice(goods.getPrice());
			goodsVo.setStatus(goods.getStatus());
			goodsVo.setSales(goods.getSales());

			Long goodsStock = Long.valueOf(0L);
			TSku skuParam = new TSku();
			skuParam.setGid(goods.getGid());
			skuParam.setStatus(Integer.valueOf(0));
			List<TSku> skus = this.skuMapper.listSkus(skuParam);
			List<SkuVo> skuVoList = new ArrayList<>();
			List<TSkuStock> stocks = null;
			List<TSkuAttr> attrs = null;
			List<TCategoryAttr> caList = null;
			if ((skus != null) && (skus.size() > 0)) {
				List<Long> skuIds = new ArrayList<>();
				for (TSku sku : skus) {
					skuIds.add(sku.getSid());
				}
				stocks = this.stockMapper.batchQuerySkuStock(skuIds);
				attrs = this.skuAttrMapper.batchQuerySkuAttrs(skuIds);
				List<Long> caIds = new ArrayList<>();
				if ((attrs != null) && (attrs.size() > 0)) {
					for (TSkuAttr attr : attrs) {
						caIds.add(attr.getCaid());
					}
					caList = this.attrMapper.batchQueryCategoryAttrs(caIds);
				}
				for (TSku sku : skus) {
					SkuVo skuVo = new SkuVo();
					skuVo.setPrice(sku.getPrice());
					skuVo.setBarcode(sku.getBarcode());
					skuVo.setSid(sku.getSid());
					skuVo.setSkuPic(sku.getSkuPic());
					skuVo.setNumber(Long.valueOf(0L));
					skuVo.setStock(Long.valueOf(0L));
					if ((stocks != null) && (stocks.size() > 0)) {
						for (TSkuStock stock : stocks) {
							if (stock.getSid().equals(sku.getSid())) {
								skuVo.setNumber(stock.getNumber());
								skuVo.setStock(stock.getStock());
								goodsStock = Long.valueOf(goodsStock.longValue() + stock.getStock().longValue());
								break;
							}
						}
					}
					List<SkuAttrVo> attrVoList = new ArrayList<>();
					if ((attrs != null) && (attrs.size() > 0)) {
						for (TSkuAttr attr : attrs) {
							if (attr.getSid().equals(sku.getSid())) {
								SkuAttrVo attrVo = new SkuAttrVo();
								attrVo.setAttrId(attr.getCaid());
								attrVo.setAttrValue(attr.getAttrValue());
								if (((caList != null ? 1 : 0) & (caList.size() > 0 ? 1 : 0)) != 0) {
									for (TCategoryAttr ca : caList) {
										if (ca.getCaid().equals(attr.getCaid())) {
											attrVo.setAttrName(ca.getAttrName());
											break;
										}
									}
								}
								attrVoList.add(attrVo);
							}
						}
					}
					skuVo.setAttrs(attrVoList);
					skuVoList.add(skuVo);
				}
			}
			goodsVo.setStock(goodsStock);
			goodsVo.setSkus(skuVoList);
		} catch (Exception e) {
			log.error("查询商品异常!", e);
		}
		return goodsVo;
	}
}
