/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.entity.ext;



import java.util.ArrayList;
import java.util.List;

import com.sinux.modules.product.entity.PimInWarehouseOrder;

/**
 * 产品入库单Entity
 * @author xiaolm
 * @version 2018-07-23
 */
public class PimInWarehouseOrderExt extends PimInWarehouseOrder {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 入库产品实体。当前整机产品只能是一个,如果是附件没有限制。
	 */
	private List<PimProductEntityExt> productEntitys = new ArrayList<>();
	

	
	private List<String> ids;
	
	public List<String> getIds(){
		return ids;
	}
	
	public void setIds(List<String> ids){
		this.ids = ids;
	}

	

	public List<PimProductEntityExt> getProductEntitys() {
		return productEntitys;
	}

	public void setProductEntitys(List<PimProductEntityExt> productEntitys) {
		this.productEntitys = productEntitys;
	}

	

	
	
	
	
}