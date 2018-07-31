/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.auth.entity;


import com.sinux.core.persistence.DataEntity;
import java.util.List;
import com.sinux.modules.sys.utils.excel.annotation.ExcelField;

/**
 * 数据权限Entity
 * @author lf
 * @version 2018-07-30
 */
public class PimDataAuth extends DataEntity<PimDataAuth> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 操作人id
	 */
	private String userId;
	/**
	 * 数据权限ID
	 */
	private String authId;
	/**
	 * 数据权限名称
	 */
	private String authName;
	/**
	 * 数据权限类型(1:产品分类 2:产品型号 3:产品基础信息)
	 */
	private String authType;
	/**
	 * 对象本身列
	 */
	private List<PimDataAuth> pimDataAuthList;
	
	public PimDataAuth() {
		super();
	}

	public PimDataAuth(String id){
		super(id);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}
	
	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}
	
	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}
	
	
	public List<PimDataAuth> getPimDataAuthList(){
		return pimDataAuthList;
	}
	
	public void setPimDataAuthList(List<PimDataAuth> pimDataAuthList){
		this.pimDataAuthList = pimDataAuthList;
	}
}