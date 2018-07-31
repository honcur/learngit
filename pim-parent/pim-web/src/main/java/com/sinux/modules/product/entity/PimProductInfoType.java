/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.entity;


import com.sinux.core.persistence.DataEntity;
import java.util.List;
import com.sinux.modules.sys.utils.excel.annotation.ExcelField;

/**
 * 产品信息类型Entity
 * @author ygy
 * @version 2018-07-26
 */
public class PimProductInfoType extends DataEntity<PimProductInfoType> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * product_id
	 */
	private String productId;
	/**
	 * type_id
	 */
	private String typeId;
	/**
	 * 对象本身列
	 */
	private List<PimProductInfoType> pimProductInfoTypeList;
	
	public PimProductInfoType() {
		super();
	}

	public PimProductInfoType(String id){
		super(id);
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	
	public List<PimProductInfoType> getPimProductInfoTypeList(){
		return pimProductInfoTypeList;
	}
	
	public void setPimProductInfoTypeList(List<PimProductInfoType> pimProductInfoTypeList){
		this.pimProductInfoTypeList = pimProductInfoTypeList;
	}
}