/**
 * Copyright &copy; 2015-2020 <a href = "http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.web;


import java.util.List;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sinux.core.dto.Result;
import com.sinux.core.persistence.Page;
import com.sinux.core.web.BaseRestController;
import com.sinux.modules.product.entity.PimSoftware;
import com.sinux.modules.product.service.PimSoftwareService;

/**
 * 软件Controller
 * @author xiaolm
 * @version 2018-07-24
 */
@RestController
@RequestMapping(value = "${adminPath}/product/pimSoftware")
public class PimSoftwareController extends BaseRestController {

	private static final Logger  logger = LoggerFactory.getLogger(PimSoftwareController.class);
	
	@Autowired
	private PimSoftwareService pimSoftwareService;
	
	
	/**
	 * 软件分页查询列表
	 */
	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public Result<Page<PimSoftware>> pageList(@RequestBody Page<PimSoftware> pageDto) {
		Page<PimSoftware> page = null;
		try{
			page = pimSoftwareService.findPage(pageDto, PimSoftware.class);
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success(page);
	}
	
	/**
	 * 获取所有软件版本
	 */
	@RequiresUser
	@RequestMapping(value = "all")
	public Result<List<PimSoftware>> getAll() {
		List<PimSoftware> list = pimSoftwareService.findListByEntity(new PimSoftware()); 
		return success(list);
	}


}