/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.entity;


import com.sinux.core.persistence.DataEntity;
import java.util.List;
import com.sinux.modules.sys.utils.excel.annotation.ExcelField;

/**
 * 产品组成Entity
 * @author ygy
 * @version 2018-07-24
 */
public class PimProductComponent extends DataEntity<PimProductComponent> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 产品ID
	 */
	private String productId;
	/**
	 * 组件ID
	 */
	private String componentId;
	/**
	 * 数量
	 */
	private Integer amount;
	/**
	 * 备注描述
	 */
	private String description;
	/**
	 * 用途
	 */
	private String purpose;
	/**
	 * 对象本身列
	 */
	private List<PimProductComponent> pimProductComponentList;
	
	public PimProductComponent() {
		super();
	}

	public PimProductComponent(String id){
		super(id);
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	
	public List<PimProductComponent> getPimProductComponentList(){
		return pimProductComponentList;
	}
	
	public void setPimProductComponentList(List<PimProductComponent> pimProductComponentList){
		this.pimProductComponentList = pimProductComponentList;
	}
}