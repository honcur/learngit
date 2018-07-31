/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.entity.ext;



import com.sinux.modules.product.entity.PimWarehouse;
import java.util.List;

/**
 * 仓库Entity
 * @author xiaolm
 * @version 2018-07-30
 */
public class PimWarehouseExt extends PimWarehouse {
	
	private static final long serialVersionUID = 1L;
	
	private List<String> ids;
	
	public List<String> getIds(){
		return ids;
	}
	
	public void setIds(List<String> ids){
		this.ids = ids;
	}
}