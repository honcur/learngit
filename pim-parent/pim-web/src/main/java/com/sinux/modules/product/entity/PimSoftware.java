/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.entity;


import com.sinux.core.persistence.DataEntity;
import java.util.List;
import com.sinux.modules.sys.utils.excel.annotation.ExcelField;

/**
 * 软件Entity
 * @author xiaolm
 * @version 2018-07-24
 */
public class PimSoftware extends DataEntity<PimSoftware> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 软件名称
	 */
	private String softwareName;
	/**
	 * 软件编号
	 */
	private String softwareNumber;
	/**
	 * 是否禁用
	 */
	private Long isAvailable;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 对象本身列
	 */
	private List<PimSoftware> pimSoftwareList;
	
	public PimSoftware() {
		super();
	}

	public PimSoftware(String id){
		super(id);
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}
	
	public String getSoftwareNumber() {
		return softwareNumber;
	}

	public void setSoftwareNumber(String softwareNumber) {
		this.softwareNumber = softwareNumber;
	}
	
	public Long getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Long isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public List<PimSoftware> getPimSoftwareList(){
		return pimSoftwareList;
	}
	
	public void setPimSoftwareList(List<PimSoftware> pimSoftwareList){
		this.pimSoftwareList = pimSoftwareList;
	}
}