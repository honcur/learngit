/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sinux.core.persistence.DataEntity;
import java.util.List;
import com.sinux.modules.sys.utils.excel.annotation.ExcelField;

/**
 * 产品入库单Entity
 * @author xiaolm
 * @version 2018-07-23
 */
public class PimInWarehouseOrder extends DataEntity<PimInWarehouseOrder> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 单号
	 */
	private String orderNumber;
	/**
	 * 入库时间
	 */
	private Date inTime;
	/**
	 * 入库仓库ID
	 */
	private String warehouseId;
	/**
	 * 仓库管理员
	 */
	private String warehouseKeeperId;
	/**
	 * 经手人
	 */
	private String ownerUserId;
	/**
	 * 入库类型
	 */
	private Integer orderType;
	/**
	 * 入库描述
	 */
	private String description;
	/**
	 * 流程状态
	 */
	private Integer processState;
	/**
	 * 对象本身列
	 */
	private List<PimInWarehouseOrder> pimInWarehouseOrderList;
	
	public PimInWarehouseOrder() {
		super();
	}

	public PimInWarehouseOrder(String id){
		super(id);
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	
	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	public String getWarehouseKeeperId() {
		return warehouseKeeperId;
	}

	public void setWarehouseKeeperId(String warehouseKeeperId) {
		this.warehouseKeeperId = warehouseKeeperId;
	}
	
	public String getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}
	
	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getProcessState() {
		return processState;
	}

	public void setProcessState(Integer processState) {
		this.processState = processState;
	}
	
	
	public List<PimInWarehouseOrder> getPimInWarehouseOrderList(){
		return pimInWarehouseOrderList;
	}
	
	public void setPimInWarehouseOrderList(List<PimInWarehouseOrder> pimInWarehouseOrderList){
		this.pimInWarehouseOrderList = pimInWarehouseOrderList;
	}
}