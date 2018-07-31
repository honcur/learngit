/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.auth.entity.ext;



import com.sinux.modules.auth.entity.PimDataAuth;
import java.util.List;

/**
 * 数据权限Entity
 * @author lf
 * @version 2018-07-30
 */
public class PimDataAuthExt extends PimDataAuth {
	
	private static final long serialVersionUID = 1L;
	
	private List<String> ids;
	
	public List<String> getIds(){
		return ids;
	}
	
	public void setIds(List<String> ids){
		this.ids = ids;
	}
}