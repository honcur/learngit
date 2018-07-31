/**
 * Copyright &copy; 2015-2020 <a href = "http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.web;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.sinux.modules.product.entity.PimInWarehouseOrder;
import com.sinux.modules.product.entity.ext.PimInWarehouseOrderExt;
import com.sinux.modules.product.service.PimInWarehouseOrderService;

/**
 * 产品入库单Controller
 * @author xiaolm
 * @version 2018-07-23
 */
@RestController
@RequestMapping(value = "${adminPath}/product/pimInWarehouseOrder")
public class PimInWarehouseOrderController extends BaseRestController {

	private static final Logger  logger = LoggerFactory.getLogger(PimInWarehouseOrderController.class);
	
	@Autowired
	private PimInWarehouseOrderService pimInWarehouseOrderService;
	
	
	/**
	 * 产品入库单分页查询列表
	 */
	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public Result<Page<PimInWarehouseOrder>> pageList(@RequestBody Page<PimInWarehouseOrder> pageDto)throws IllegalAccessException, InstantiationException  {
		Page<PimInWarehouseOrder> page = pimInWarehouseOrderService.findPage(pageDto, PimInWarehouseOrder.class);
		return success(page);
	}

	/**
	 * 产品入库单根据id查询
	 */
	@RequiresUser
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public Result<PimInWarehouseOrder> findById(@PathVariable String id){
	    if(StringUtils.isEmpty(id)) {
	        return error("id为空");
	    }
	    PimInWarehouseOrder entity = new PimInWarehouseOrder();
	    entity.setId(id);
	    List<PimInWarehouseOrder>  list = pimInWarehouseOrderService.findListByEntity(entity);
	    return success(list.get(0));
	}
	

	/**
	 * 单条数据保存产品入库单
	 */
    @RequiresUser
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Result<String> save(@RequestBody PimInWarehouseOrderExt pimInWarehouseOrder) {
		pimInWarehouseOrderService.addInWarehouseOrder(pimInWarehouseOrder);
		return success("保存成功");
	}
    
	/**
	 * 生产入库订单号
	 */
    @RequiresUser
	@RequestMapping(value = "generateOrderNumber")
	public Result<String> generateOrderNumber() {
		String orderNumber = pimInWarehouseOrderService.generateOrderNumber();
		Result<String> result = success("成功.",orderNumber);
		
		return result;
	}
   
    
    
	

}