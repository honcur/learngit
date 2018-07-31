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
 * 产品实体与软件安装记录Entity
 * @author xiaolm
 * @version 2018-07-30
 */
public class PimSoftwareProductEntity extends DataEntity<PimSoftwareProductEntity> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 产品实体ID
	 */
	private String productEntityId;
	/**
	 * 软件ID
	 */
	private String softwareId;
	/**
	 * 软件名称
	 */
	private String softwateName;
	/**
	 * 软件编号
	 */
	private String softwareNumber;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 评测情况
	 */
	private String detectionDescription;
	/**
	 * 操作时间
	 */
	private Date operationDate;
	/**
	 * 对象本身列
	 */
	private List<PimSoftwareProductEntity> pimSoftwareProductEntityList;
	
	public PimSoftwareProductEntity() {
		super();
	}

	public PimSoftwareProductEntity(String id){
		super(id);
	}

	public String getProductEntityId() {
		return productEntityId;
	}

	public void setProductEntityId(String productEntityId) {
		this.productEntityId = productEntityId;
	}
	
	public String getSoftwareId() {
		return softwareId;
	}

	public void setSoftwareId(String softwareId) {
		this.softwareId = softwareId;
	}
	
	public String getSoftwateName() {
		return softwateName;
	}

	public void setSoftwateName(String softwateName) {
		this.softwateName = softwateName;
	}
	
	public String getSoftwareNumber() {
		return softwareNumber;
	}

	public void setSoftwareNumber(String softwareNumber) {
		this.softwareNumber = softwareNumber;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDetectionDescription() {
		return detectionDescription;
	}

	public void setDetectionDescription(String detectionDescription) {
		this.detectionDescription = detectionDescription;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	
	
	public List<PimSoftwareProductEntity> getPimSoftwareProductEntityList(){
		return pimSoftwareProductEntityList;
	}
	
	public void setPimSoftwareProductEntityList(List<PimSoftwareProductEntity> pimSoftwareProductEntityList){
		this.pimSoftwareProductEntityList = pimSoftwareProductEntityList;
	}
}