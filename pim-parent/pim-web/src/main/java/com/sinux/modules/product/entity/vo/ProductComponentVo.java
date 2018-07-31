package com.sinux.modules.product.entity.vo;

import java.util.List;

import com.sinux.core.persistence.DataEntity;

/**
 * @author ygy
 * 
 * 自定义产品配套关系组合实体类
 *
 */
public class ProductComponentVo extends DataEntity<ProductComponentVo>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//产品id
	private String productId;
	
	//组件id
	private String componentId;
	
	//组件产品型号集合
	private List<String> typeNameList;
	
	//组件名称
	private String componentName;
	
	//组件编码
	private String componentCoding;
	
	//组件图号
	private String componentGraphNumber;
	
	//组件用途
	private String componentPurpose;
	
	//组件数量
	private int componentAmount;
	
	//备注
	private String Remarks;

	public List<String> getTypeNameList() {
		return typeNameList;
	}

	public void setTypeNameList(List<String> typeNameList) {
		this.typeNameList = typeNameList;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentCoding() {
		return componentCoding;
	}

	public void setComponentCoding(String componentCoding) {
		this.componentCoding = componentCoding;
	}

	public String getComponentGraphNumber() {
		return componentGraphNumber;
	}

	public void setComponentGraphNumber(String componentGraphNumber) {
		this.componentGraphNumber = componentGraphNumber;
	}

	public String getComponentPurpose() {
		return componentPurpose;
	}

	public void setComponentPurpose(String componentPurpose) {
		this.componentPurpose = componentPurpose;
	}

	public int getComponentAmount() {
		return componentAmount;
	}

	public void setComponentAmount(int componentAmount) {
		this.componentAmount = componentAmount;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
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
	
	
}
