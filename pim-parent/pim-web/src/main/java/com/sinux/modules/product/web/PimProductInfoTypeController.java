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
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.Logical;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sinux.core.exception.custom.ServerInternalException;

import com.sinux.core.persistence.BatchEntity;
import com.sinux.core.persistence.Page;
import com.sinux.core.web.BaseRestController;
import org.apache.commons.lang3.StringUtils;
import com.sinux.core.dto.Result;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import com.sinux.modules.sys.utils.DictUtils;
import com.sinux.core.utils.excel.ExportExcel;
import com.sinux.core.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import com.sinux.modules.product.entity.PimProductInfoType;
import com.sinux.modules.product.entity.ext.PimProductInfoTypeExt;
import com.sinux.modules.product.service.PimProductInfoTypeService;

/**
 * 产品信息类型Controller
 * @author ygy
 * @version 2018-07-26
 */
@RestController
@RequestMapping(value = "${adminPath}/product/pimProductInfoType")
public class PimProductInfoTypeController extends BaseRestController {

	private static final Logger  logger = LoggerFactory.getLogger(PimProductInfoTypeController.class);
	
	@Autowired
	private PimProductInfoTypeService pimProductInfoTypeService;
	
	
	/**
	 * 产品信息类型分页查询列表
	 */
	@RequiresPermissions("product:pimProductInfoType:list")
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public Result<Page<PimProductInfoType>> pageList(@RequestBody Page<PimProductInfoType> pageDto) {
		Page<PimProductInfoType> page = null;
		try{
			page = pimProductInfoTypeService.findPage(pageDto, PimProductInfoType.class);
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success(page);
	}

	/**
	 * 产品信息类型根据id查询
	 */
	@RequiresPermissions("product:pimProductInfoType:view")
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public Result<PimProductInfoType> findById(@PathVariable String id){
	    if(StringUtils.isEmpty(id)) {
	        return error("id为空");
	    }
	    PimProductInfoType entity = new PimProductInfoType();
	    entity.setId(id);
	    List<PimProductInfoType> list = null;
	    try {
	         list = pimProductInfoTypeService.findListByEntity(entity);
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
     * 产品信息类型根据ids查询
     */
    @RequiresPermissions("product:pimProductInfoType:view")
    @RequestMapping(value = "getByIds", method = RequestMethod.POST)
	public Result<List<PimProductInfoType>> findListByIds(@RequestBody List<String> ids){
	    if(CollectionUtils.isEmpty(ids)) {
	        return error("参数为空");
	    }
	    PimProductInfoTypeExt extEntity = new PimProductInfoTypeExt();
	    extEntity.setIds(ids);
	    List<PimProductInfoType> list = null;
	    try {
	        list = pimProductInfoTypeService.findListByExtEntity(extEntity);
	    }catch(Exception e) {
	        logger.error("系统异常", e);
            return error("系统异常");
	    }
	    if(CollectionUtils.isEmpty(list)) {
            return success();
        }
	    return success(list);
	}
	
	/**
	 * 单条数据保存产品信息类型
	 */
	@RequiresPermissions(value={"product:pimProductInfoType:add","product:pimProductInfoType:edit"},logical=Logical.OR)
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Result<String> save(@RequestBody PimProductInfoType pimProductInfoType) {
		try{
			pimProductInfoTypeService.save(pimProductInfoType);
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("保存成功");
	}
	
	/**
	 * 多条数据新增产品信息类型
	 */
	@RequiresPermissions(value = {"product:pimProductInfoType:add","product:pimProductInfoType:edit"},logical = Logical.OR)
	@RequestMapping(value = "saveBatch", method = RequestMethod.POST)
	public Result<String> saveBatch(@RequestBody PimProductInfoType pimProductInfoType){
		try{
			pimProductInfoTypeService.saveBatch(pimProductInfoType.getPimProductInfoTypeList());
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("保存成功");
	}
	
	/**
	 * 删除产品信息类型
	 */
	@RequiresPermissions("product:pimProductInfoType:del")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Result<String> delete(@RequestBody PimProductInfoType pimProductInfoType) {
		try{
			pimProductInfoTypeService.deleteByPrimaryKey(pimProductInfoType);
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("删除成功");
	}
	
	/**
	 * 逻辑删除产品信息类型
	 */
	@RequiresPermissions("product:pimProductInfoType:del")
	@RequestMapping(value = "deleteByLogic", method = RequestMethod.POST)
	public Result<String> deleteByLogic(@RequestBody PimProductInfoType pimProductInfoType) {
		try{
			pimProductInfoType.setDelFlag("1");
			pimProductInfoTypeService.deleteByLogic(pimProductInfoType);
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("删除成功");
	}
	
	/**
	 * 批量删除产品信息类型
	 */
	@RequiresPermissions("product:pimProductInfoType:del")
	@RequestMapping(value = "deleteBatch", method = RequestMethod.POST)
	public Result<String> deleteBatch(@RequestBody PimProductInfoType pimProductInfoType) {
		try{
			pimProductInfoTypeService.deleteBatchByPrimaryKey(pimProductInfoType.getPimProductInfoTypeList());
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("删除成功");
	}
	
	/**
	 * 批量逻辑删除产品信息类型
	 */
	@RequiresPermissions("product:pimProductInfoType:del")
	@RequestMapping(value = "deleteBatchByLogic", method = RequestMethod.POST)
	public Result<String> deleteBatchByLogic(@RequestBody PimProductInfoType pimProductInfoType) {
		try{
			for (PimProductInfoType tmp : pimProductInfoType.getPimProductInfoTypeList()) {
				tmp.setDelFlag("1");
			}
			pimProductInfoTypeService.deleteBatchByLogic(new BatchEntity<>(pimProductInfoType.getPimProductInfoTypeList()));
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("删除成功");
	}
	
	/**
	 * 初始化下拉列表、单选、复选框产品信息类型
	 */
	@RequiresPermissions(value = { "product:pimProductInfoType:view", "product:pimProductInfoType:add", "product:pimProductInfoType:edit" }, logical = Logical.OR)
    @RequestMapping(value = "init", method = RequestMethod.GET)
	public Result<Map<String, Object>> initData() {
		Map<String, Object> map = new HashMap<>();
		try{
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success(map);
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("product:pimProductInfoType:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public void exportFile(HttpServletRequest request, HttpServletResponse response) {
		try {
            String fileName = "产品信息类型"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            PimProductInfoType pimProductInfoType = new PimProductInfoType();
            List<PimProductInfoType> resultList = pimProductInfoTypeService.findListByEntity(pimProductInfoType);
    		new ExportExcel("产品信息类型", PimProductInfoType.class).setDataList(resultList).write(response, fileName).dispose();
		} catch (Exception e) {
			throw new ServerInternalException("导出产品信息类型记录失败！失败信息：", e);
		}
    }

}