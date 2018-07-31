/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.entity;


import com.sinux.core.persistence.DataEntity;
import java.util.List;
import com.sinux.modules.sys.utils.excel.annotation.ExcelField;

/**
 * 仓库Entity
 * @author xiaolm
 * @version 2018-07-30
 */
public class PimWarehouse extends DataEntity<PimWarehouse> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 仓库名称
	 */
	private String warehouseName;
	/**
	 * warehouse_keeper_id
	 */
	private String warehouseKeeperId;
	/**
	 * 仓库编号
	 */
	private String warehouseNumber;
	/**
	 * 是否禁用
	 */
	private Integer isAvailable;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 对象本身列
	 */
	private List<PimWarehouse> pimWarehouseList;
	
	public PimWarehouse() {
		super();
	}

	public PimWarehouse(String id){
		super(id);
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	
	public String getWarehouseKeeperId() {
		return warehouseKeeperId;
	}

	public void setWarehouseKeeperId(String warehouseKeeperId) {
		this.warehouseKeeperId = warehouseKeeperId;
	}
	
	public String getWarehouseNumber() {
		return warehouseNumber;
	}

	public void setWarehouseNumber(String warehouseNumber) {
		this.warehouseNumber = warehouseNumber;
	}
	
	public Integer getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public List<PimWarehouse> getPimWarehouseList(){
		return pimWarehouseList;
	}
	
	public void setPimWarehouseList(List<PimWarehouse> pimWarehouseList){
		this.pimWarehouseList = pimWarehouseList;
	}
}