/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.log.entity.ext;



import com.sinux.modules.log.entity.PimOperLog;
import java.util.List;

/**
 * 操作日志Entity
 * @author lf
 * @version 2018-07-26
 */
public class PimOperLogExt extends PimOperLog {
	
	private static final long serialVersionUID = 1L;
	
	private List<String> ids;
	
	public List<String> getIds(){
		return ids;
	}
	
	public void setIds(List<String> ids){
		this.ids = ids;
	}
}