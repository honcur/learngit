/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.entity.ext;



import com.sinux.modules.product.entity.PimInProductEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 入库单与产品实体关系Entity
 * @author xiaolm
 * @version 2018-07-30
 */
public class PimInProductEntityExt extends PimInProductEntity {
	
	private static final long serialVersionUID = 1L;
	
	private List<String> ids;
	/**
	 * 软件安装记录
	 */
	List<PimSoftwareInstallRecordExt> softwareInstallRecords = new ArrayList<>();
	
	public List<String> getIds(){
		return ids;
	}
	
	public void setIds(List<String> ids){
		this.ids = ids;
	}

	public List<PimSoftwareInstallRecordExt> getSoftwareInstallRecords() {
		return softwareInstallRecords;
	}

	public void setSoftwareInstallRecords(List<PimSoftwareInstallRecordExt> softwareInstallRecords) {
		this.softwareInstallRecords = softwareInstallRecords;
	}
	
}