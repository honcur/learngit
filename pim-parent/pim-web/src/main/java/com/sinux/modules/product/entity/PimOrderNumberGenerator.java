/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.entity;


import com.sinux.core.persistence.DataEntity;
import java.util.List;
import com.sinux.modules.sys.utils.excel.annotation.ExcelField;

/**
 * 订单号生成Entity
 * @author xiaolm
 * @version 2018-07-25
 */
public class PimOrderNumberGenerator extends DataEntity<PimOrderNumberGenerator> {
	
	private static final long serialVersionUID = 1L;
	
	public static final Integer IN_ORDER_TYPE = 0;//入库单
	
	public static final Integer OUT_ORDER_TYPE = 1;//出库单
	
	/**
	 *  类型  0:入库单 1:出库单
	 */
	private Integer orderType;
	/**
	 * 订单KEY,出库入库按创建时间格式“yyyMMdd”的字符串。
	 */
	private String orderKey;
	/**
	 * 数字值，按订单KEY不断增长
	 */
	private Integer orderNumber;
	/**
	 * 对象本身列
	 */
	private List<PimOrderNumberGenerator> pimOrderNumberGeneratorList;
	
	public PimOrderNumberGenerator() {
		super();
	}

	public PimOrderNumberGenerator(String id){
		super(id);
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	public String getOrderKey() {
		return orderKey;
	}

	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}
	
	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
	public List<PimOrderNumberGenerator> getPimOrderNumberGeneratorList(){
		return pimOrderNumberGeneratorList;
	}
	
	public void setPimOrderNumberGeneratorList(List<PimOrderNumberGenerator> pimOrderNumberGeneratorList){
		this.pimOrderNumberGeneratorList = pimOrderNumberGeneratorList;
	}
}