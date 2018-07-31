/**
 * Copyright &copy; 2015-2020 <a href = "http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.web;


import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.sinux.modules.product.entity.PimProductEntity;
import com.sinux.modules.product.service.PimProductEntityService;
import com.sinux.pim.common.entity.CommonContent;

/**
 * 产品实体Controller
 * @author xiaolm
 * @version 2018-07-24
 */
@RestController
@RequestMapping(value = "${adminPath}/product/pimProductEntity")
public class PimProductEntityController extends BaseRestController {

	private static final Logger  logger = LoggerFactory.getLogger(PimProductEntityController.class);
	
	@Autowired
	private PimProductEntityService pimProductEntityService;
	
	
	/**
	 * 产品实体分页查询列表
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public Result<Page<PimProductEntity>> pageList(@RequestBody Page<PimProductEntity> pageDto) throws InstantiationException, IllegalAccessException {
		Page<PimProductEntity> page = pimProductEntityService.findPage(pageDto, PimProductEntity.class);
		return success(page);
	}
	
	@RequiresUser
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Result<String> save(@RequestBody PimProductEntity dto) throws InstantiationException, IllegalAccessException {
		pimProductEntityService.save(dto);
		return success(CommonContent.UPDATE_SUCCESS);
	}
	
	/**
	 * 查询返回集合
	 * @param dto
	 * @return
	 */
	@RequiresUser
	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public Result<List<PimProductEntity>> queryList(@RequestBody PimProductEntity dto){
		List<PimProductEntity> list = pimProductEntityService.findListByEntity(dto);
		return success(list);
	}
	
	
	

}