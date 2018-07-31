/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.entity;


import com.sinux.core.persistence.DataEntity;
import java.util.List;
import com.sinux.modules.sys.utils.excel.annotation.ExcelField;

/**
 * 产品型号Entity
 * @author gebp
 * @version 2018-07-24
 */
public class PimProductType extends DataEntity<PimProductType> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * type_name
	 */
	private String typeName;
	/**
	 * description
	 */
	private String description;
	/**
	 * 对象本身列
	 */
	private List<PimProductType> pimProductTypeList;
	
	public PimProductType() {
		super();
	}

	public PimProductType(String id){
		super(id);
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public List<PimProductType> getPimProductTypeList(){
		return pimProductTypeList;
	}
	
	public void setPimProductTypeList(List<PimProductType> pimProductTypeList){
		this.pimProductTypeList = pimProductTypeList;
	}
}