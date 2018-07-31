/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.entity.ext;



import com.sinux.modules.product.entity.PimProductEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品实体Entity
 * @author xiaolm
 * @version 2018-07-25
 */
public class PimProductEntityExt extends PimProductEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 软件安装记录
	 */
	private List<PimSoftwareInstallRecordExt> softwareInstallRecords = new ArrayList<>();
	
	private List<String> ids;
	
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