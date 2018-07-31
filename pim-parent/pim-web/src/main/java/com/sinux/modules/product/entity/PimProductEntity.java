/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sinux.core.persistence.DataEntity;
import java.util.List;
import com.sinux.modules.sys.utils.excel.annotation.ExcelField;
import com.sinux.pim.common.entity.CommonContent;

/**
 * 产品实体Entity
 * @author xiaolm
 * @version 2018-07-25
 */
public class PimProductEntity extends DataEntity<PimProductEntity> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 产品编号
	 */
	private String entityNumber;
	/**
	 * 产品实体阶段
	 */
	private Integer entityState;
	/**
	 * 批次号
	 */
	private String batchNumber;
	/**
	 * 产品所在仓库ID
	 */
	private String warehouseId;
	/**
	 * 软件版本ID
	 */
	private String softwareVersionId;
	/**
	 * 产品基本信息ID
	 */
	private String productId;
	/**
	 * 产品编码
	 */
	private String productNumber;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * physical_warehouse
	 */
	private String physicalWarehouse;
	/**
	 * 流程状态
	 */
	private Long processState;
	/**
	 * 产品主机ID
	 */
	private String ownershipId;
	/**
	 * 更换主机产品时间
	 */
	private Date updateOwnershipDate;
	/**
	 * 是否附属件
	 */
	private Integer isAttached;
	/**
	 * 对象本身列
	 */
	private List<PimProductEntity> pimProductEntityList;
	/**
	 * 模糊查询
	 */
	private String keywords;
	
	public PimProductEntity() {
		super();
	}

	public PimProductEntity(String id){
		super(id);
	}

	public String getEntityNumber() {
		return entityNumber;
	}

	public void setEntityNumber(String entityNumber) {
		this.entityNumber = entityNumber;
	}
	
	public Integer getEntityState() {
		return entityState;
	}

	public void setEntityState(Integer entityState) {
		this.entityState = entityState;
	}
	
	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	
	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	public String getSoftwareVersionId() {
		return softwareVersionId;
	}

	public void setSoftwareVersionId(String softwareVersionId) {
		this.softwareVersionId = softwareVersionId;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPhysicalWarehouse() {
		return physicalWarehouse;
	}

	public void setPhysicalWarehouse(String physicalWarehouse) {
		this.physicalWarehouse = physicalWarehouse;
	}
	
	public Long getProcessState() {
		return processState;
	}

	public void setProcessState(Long processState) {
		this.processState = processState;
	}
	
	public String getOwnershipId() {
		return ownershipId;
	}

	public void setOwnershipId(String ownershipId) {
		this.ownershipId = ownershipId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateOwnershipDate() {
		return updateOwnershipDate;
	}

	public void setUpdateOwnershipDate(Date updateOwnershipDate) {
		this.updateOwnershipDate = updateOwnershipDate;
	}
	
	public Integer getIsAttached() {
		return isAttached;
	}

	public void setIsAttached(Integer isAttached) {
		this.isAttached = isAttached;
	}
	
	public Boolean isAttached() {
		if(CommonContent.BOOLEAN_TRUE.equals(isAttached)) {
			return true;
		}
		return false;
	}
	
	public List<PimProductEntity> getPimProductEntityList(){
		return pimProductEntityList;
	}
	
	public void setPimProductEntityList(List<PimProductEntity> pimProductEntityList){
		this.pimProductEntityList = pimProductEntityList;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	
}