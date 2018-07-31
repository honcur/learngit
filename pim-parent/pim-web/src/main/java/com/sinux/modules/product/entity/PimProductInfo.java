/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.entity;


import com.sinux.core.persistence.DataEntity;
import java.util.List;
import com.sinux.modules.sys.utils.excel.annotation.ExcelField;

/**
 * 产品基本信息Entity
 * @author ygy
 * @version 2018-07-20
 */
public class PimProductInfo extends DataEntity<PimProductInfo> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 *  产品类型id
	 */
	private String productTypeId;
	/**
	 * 分类id
	 */
	private String categoryId;
	/**
	 * 产品编码
	 */
	private String productNumber;
	/**
	 * 产品代号
	 */
	private String productCode;
	/**
	 * 制造商
	 */
	private String manufacturer;
	/**
	 * 是否附属件
	 */
	private Long isAttached;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 流程状态
	 */
	private Long processState;
	/**
	 * 产品来源
	 */
	private String productSource;
	/**
	 * 对象列本身
	 */
	private List<PimProductInfo> pimProductInfoList;
	
	/**
	 * 模糊查询关键字
	 */
	private String keywords;
	
	/**
	 * 图号
	 */
	private String graphNumber;
	
	/**
	 * 产品对应的型号
	 * 
	 */
	private List<PimProductType> pimProductTypes;
	
	/**
	 * 该产品对应的组件信息
	 */
	private PimProductComponent pimProductComponent;
	
	
	public PimProductComponent getPimProductComponent() {
		return pimProductComponent;
	}

	public void setPimProductComponent(PimProductComponent pimProductComponent) {
		this.pimProductComponent = pimProductComponent;
	}

	public List<PimProductType> getPimProductTypes() {
		return pimProductTypes;
	}

	public void setPimProductTypes(List<PimProductType> pimProductTypes) {
		this.pimProductTypes = pimProductTypes;
	}

	public String getGraphNumber() {
		return graphNumber;
	}

	public void setGraphNumber(String graphNumber) {
		this.graphNumber = graphNumber;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public PimProductInfo() {
		super();
	}

	public PimProductInfo(String id){
		super(id);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public Long getIsAttached() {
		return isAttached;
	}

	public void setIsAttached(Long isAttached) {
		this.isAttached = isAttached;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getProcessState() {
		return processState;
	}

	public void setProcessState(Long processState) {
		this.processState = processState;
	}
	
	public String getProductSource() {
		return productSource;
	}

	public void setProductSource(String productSource) {
		this.productSource = productSource;
	}
	
	
	public List<PimProductInfo> getPimProductInfoList(){
		return pimProductInfoList;
	}
	
	public void setPimProductInfoList(List<PimProductInfo> pimProductInfoList){
		this.pimProductInfoList = pimProductInfoList;
	}
}