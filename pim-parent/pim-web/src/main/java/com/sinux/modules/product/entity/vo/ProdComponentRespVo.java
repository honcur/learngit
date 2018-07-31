package com.sinux.modules.product.entity.vo;

import java.util.List;

import com.sinux.core.persistence.DataEntity;
import com.sinux.modules.product.entity.PimProductType;

/**
 * @author ygy
 * 
 * 产品组件相关信息的实体类
 *
 */
public class ProdComponentRespVo extends DataEntity<ProdComponentRespVo>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productId;
	private String productName;
	private String componentId;
	private String componentName;
	private String componentNumber;
	private String componentGraph;
	private String componentAmount;
	private String componentPuprpose;
	private String componentDescription;
	private List<PimProductType> typeList;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getComponentNumber() {
		return componentNumber;
	}
	public void setComponentNumber(String componentNumber) {
		this.componentNumber = componentNumber;
	}
	public String getComponentGraph() {
		return componentGraph;
	}
	public void setComponentGraph(String componentGraph) {
		this.componentGraph = componentGraph;
	}
	public String getComponentAmount() {
		return componentAmount;
	}
	public void setComponentAmount(String componentAmount) {
		this.componentAmount = componentAmount;
	}
	public String getComponentPuprpose() {
		return componentPuprpose;
	}
	public void setComponentPuprpose(String componentPuprpose) {
		this.componentPuprpose = componentPuprpose;
	}
	public String getComponentDescription() {
		return componentDescription;
	}
	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}
	public List<PimProductType> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<PimProductType> typeList) {
		this.typeList = typeList;
	}
	
	
}
