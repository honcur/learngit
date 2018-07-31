/**
 * Copyright &copy; 2015-2020 <a href = "http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.web;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sinux.core.dto.Result;
import com.sinux.core.persistence.Page;
import com.sinux.core.web.BaseRestController;
import com.sinux.modules.product.entity.PimWarehouse;
import com.sinux.modules.product.service.PimWarehouseService;
import com.sinux.modules.sys.entity.User;
import com.sinux.modules.sys.service.UserService;

/**
 * 仓库Controller
 * @author xiaolm
 * @version 2018-07-24
 */
@RestController
@RequestMapping(value = "${adminPath}/product/pimWarehouse")
public class PimWarehouseController extends BaseRestController {

	private static final Logger  logger = LoggerFactory.getLogger(PimWarehouseController.class);
	
	@Autowired
	private PimWarehouseService pimWarehouseService;
	

	
	
	/**
	 * 仓库分页查询列表
	 */
	@RequiresPermissions("product:pimWarehouse:list")
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public Result<Page<PimWarehouse>> pageList(@RequestBody Page<PimWarehouse> pageDto) {
		Page<PimWarehouse> page = null;
		try{
			page = pimWarehouseService.findPage(pageDto, PimWarehouse.class);
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success(page);
	}

	/**
	 * 仓库根据id查询
	 */
	@RequiresPermissions("product:pimWarehouse:view")
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public Result<PimWarehouse> findById(@PathVariable String id){
	    if(StringUtils.isEmpty(id)) {
	        return error("id为空");
	    }
	    PimWarehouse entity = new PimWarehouse();
	    entity.setId(id);
	    List<PimWarehouse> list = null;
	    try {
	         list = pimWarehouseService.findListByEntity(entity);
	    }catch(Exception e){
            logger.error("系统异常", e);
            return error("系统异常");
        }
	    if(CollectionUtils.isEmpty(list)) {
	        return success();
	    }
	    if(list.size()>1) {
	        logger.error("主键不唯一,id = "+id);
	        return error("数据有误，主键不唯一");
	    }
	    return success(list.get(0));
	}
	
	
	/**
	 * 获取所有仓库
	 */
	@RequiresUser
	@RequestMapping(value = "all")
	public Result<List<PimWarehouse>> getAll() {
		List<PimWarehouse> list = pimWarehouseService.findListByEntity(new PimWarehouse());
		return success(list);
	}
	
	
	/**
	 * 查询仓库管理员
	 */
	@RequestMapping(value = "getKeeper/{userId}")
	public Result<User> getAdministratorByUserId(@PathVariable String userId){
	    if(StringUtils.isEmpty(userId)) {
	        return error("id为空");
	    }
	    User user = pimWarehouseService.getKeeperByUserId(userId);
	    return success(user);
	}

}