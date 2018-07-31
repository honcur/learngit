/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.log.entity;


import com.sinux.core.persistence.DataEntity;
import java.util.List;
import com.sinux.modules.sys.utils.excel.annotation.ExcelField;

/**
 * 操作日志Entity
 * @author lf
 * @version 2018-07-26
 */
public class PimOperLog extends DataEntity<PimOperLog> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 操作人id
	 */
	private String userId;
	/**
	 * 操作描述
	 */
	private String operDescription;
	/**
	 * 操作数据
	 */
	private String operContent;
	/**
	 * 操作类型
	 */
	private String operType;
	/**
	 * 操作标识
	 */
	private String operContentIdentify;
	/**
	 * 对象本身列
	 */
	private List<PimOperLog> pimOperLogList;
	
	public PimOperLog() {
		super();
	}

	public PimOperLog(String id){
		super(id);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getOperDescription() {
		return operDescription;
	}

	public void setOperDescription(String operDescription) {
		this.operDescription = operDescription;
	}
	
	public String getOperContent() {
		return operContent;
	}

	public void setOperContent(String operContent) {
		this.operContent = operContent;
	}
	
	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}
	
	public String getOperContentIdentify() {
		return operContentIdentify;
	}

	public void setOperContentIdentify(String operContentIdentify) {
		this.operContentIdentify = operContentIdentify;
	}
	
	
	public List<PimOperLog> getPimOperLogList(){
		return pimOperLogList;
	}
	
	public void setPimOperLogList(List<PimOperLog> pimOperLogList){
		this.pimOperLogList = pimOperLogList;
	}
}