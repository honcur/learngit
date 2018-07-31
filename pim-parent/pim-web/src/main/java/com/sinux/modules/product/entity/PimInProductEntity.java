/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.entity;


import com.sinux.core.persistence.DataEntity;
import java.util.List;
import com.sinux.modules.sys.utils.excel.annotation.ExcelField;

/**
 * 入库单与产品实体关系Entity
 * @author xiaolm
 * @version 2018-07-30
 */

public class PimInProductEntity extends DataEntity<PimInProductEntity> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 入库单ID
	 */
	private String orderId;
	/**
	 * 产品实体ID
	 */
	private String productEntityId;
	/**
	 * 所属整机产品ID
	 */
	private String ownershipId;
	/**
	 * 对象本身列
	 */
	private List<PimInProductEntity> pimInProductEntityList;
	
	public PimInProductEntity() {
		super();
	}

	public PimInProductEntity(String id){
		super(id);
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getProductEntityId() {
		return productEntityId;
	}

	public void setProductEntityId(String productEntityId) {
		this.productEntityId = productEntityId;
	}
	
	public String getOwnershipId() {
		return ownershipId;
	}

	public void setOwnershipId(String ownershipId) {
		this.ownershipId = ownershipId;
	}
	
	
	public List<PimInProductEntity> getPimInProductEntityList(){
		return pimInProductEntityList;
	}
	
	public void setPimInProductEntityList(List<PimInProductEntity> pimInProductEntityList){
		this.pimInProductEntityList = pimInProductEntityList;
	}
}