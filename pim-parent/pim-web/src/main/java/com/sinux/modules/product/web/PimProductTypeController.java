/**
 * Copyright &copy; 2015-2020 <a href = "http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sinux.core.exception.custom.ServerInternalException;

import com.sinux.core.persistence.Page;
import com.sinux.core.web.BaseRestController;
import com.sinux.core.dto.Result;
import java.util.List;

import com.sinux.core.utils.excel.ExportExcel;
import com.sinux.core.utils.DateUtils;
import com.sinux.modules.product.entity.PimProductType;
import com.sinux.modules.product.service.PimProductTypeService;

/**
 * 产品型号Controller
 * @author gebp
 * @version 2018-07-24
 */
@RestController
@RequestMapping(value = "${adminPath}/product/pimProductType")
public class PimProductTypeController extends BaseRestController {

	private static final Logger  logger = LoggerFactory.getLogger(PimProductTypeController.class);
	
	@Autowired
	private PimProductTypeService pimProductTypeService;
	
	
	/**
	 * 产品型号分页查询列表
	 */
	@RequestMapping("/list")
	public Result<Page<PimProductType>> pageList(@RequestBody Page<PimProductType> pageDto) throws InstantiationException, IllegalAccessException{
		Page<PimProductType> page = pimProductTypeService.findPage(pageDto, PimProductType.class);
		return success(page);
	}

	/**
	 * 产品型号根据id查询
	 */
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public Result<PimProductType> findById(@PathVariable String id){
		List<PimProductType> list = pimProductTypeService.findListById(id);
	    return success(list.get(0));
	}
	
	/**
	 * 新增一个产品型号
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Result<String> addProductType(@RequestBody PimProductType pimProductType) {
		pimProductTypeService.addProductType(pimProductType);
		return success("保存成功");
	}

	/**
	 * 删除一个产品型号
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Result<String> delete(String id) {
		pimProductTypeService.deleteById(id);
		return success("删除成功");
	}

	/**
	 * 更新一个产品型号
	 */
	@RequestMapping(value = "update",method = RequestMethod.PUT )
	public Result<String> updateProductType(@RequestBody PimProductType pimProductType){
		pimProductTypeService.updateProductType(pimProductType);
		return success("更新成功");
	}

	/**
	 * 批量删除产品型号
	 */
	@RequestMapping(value = "deleteBatch", method = RequestMethod.POST)
	public Result<String> deleteBatch(@RequestBody PimProductType pimProductType) {
		pimProductTypeService.deleteBatchByPrimaryKey(pimProductType.getPimProductTypeList());
		return success("删除成功");
	}
	
	/**
	 * 导出excel文件
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public Result<String> exportFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
		pimProductTypeService.exportFile(request, response);
		return success("导出成功");
    }

}