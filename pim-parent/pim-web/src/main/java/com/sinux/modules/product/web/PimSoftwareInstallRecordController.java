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
import com.sinux.modules.product.entity.PimSoftwareInstallRecord;
import com.sinux.modules.product.entity.ext.PimSoftwareInstallRecordExt;
import com.sinux.modules.product.service.PimSoftwareInstallRecordService;

/**
 * 软件安装记录Controller
 * @author xiaolm
 * @version 2018-07-30
 */
@RestController
@RequestMapping(value = "${adminPath}/product/pimSoftwareInstallRecord")
public class PimSoftwareInstallRecordController extends BaseRestController {

	private static final Logger  logger = LoggerFactory.getLogger(PimSoftwareInstallRecordController.class);
	
	@Autowired
	private PimSoftwareInstallRecordService pimSoftwareInstallRecordService;
	
	
	/**
	 * 软件安装记录分页查询列表
	 */
	@RequiresPermissions("product:pimSoftwareInstallRecord:list")
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public Result<Page<PimSoftwareInstallRecord>> pageList(@RequestBody Page<PimSoftwareInstallRecord> pageDto) {
		Page<PimSoftwareInstallRecord> page = null;
		try{
			page = pimSoftwareInstallRecordService.findPage(pageDto, PimSoftwareInstallRecord.class);
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success(page);
	}

	/**
	 * 软件安装记录根据id查询
	 */
	@RequiresPermissions("product:pimSoftwareInstallRecord:view")
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public Result<PimSoftwareInstallRecord> findById(@PathVariable String id){
	    if(StringUtils.isEmpty(id)) {
	        return error("id为空");
	    }
	    PimSoftwareInstallRecord entity = new PimSoftwareInstallRecord();
	    entity.setId(id);
	    List<PimSoftwareInstallRecord> list = null;
	    try {
	         list = pimSoftwareInstallRecordService.findListByEntity(entity);
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
     * 软件安装记录根据ids查询
     */
    @RequiresPermissions("product:pimSoftwareInstallRecord:view")
    @RequestMapping(value = "getByIds", method = RequestMethod.POST)
	public Result<List<PimSoftwareInstallRecord>> findListByIds(@RequestBody List<String> ids){
	    if(CollectionUtils.isEmpty(ids)) {
	        return error("参数为空");
	    }
	    PimSoftwareInstallRecordExt extEntity = new PimSoftwareInstallRecordExt();
	    extEntity.setIds(ids);
	    List<PimSoftwareInstallRecord> list = null;
	    try {
	        list = pimSoftwareInstallRecordService.findListByExtEntity(extEntity);
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
	 * 单条数据保存软件安装记录
	 */
	@RequiresPermissions(value={"product:pimSoftwareInstallRecord:add","product:pimSoftwareInstallRecord:edit"},logical=Logical.OR)
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Result<String> save(@RequestBody PimSoftwareInstallRecord pimSoftwareInstallRecord) {
		try{
			pimSoftwareInstallRecordService.save(pimSoftwareInstallRecord);
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("保存成功");
	}
	
	/**
	 * 多条数据新增软件安装记录
	 */
	@RequiresPermissions(value = {"product:pimSoftwareInstallRecord:add","product:pimSoftwareInstallRecord:edit"},logical = Logical.OR)
	@RequestMapping(value = "saveBatch", method = RequestMethod.POST)
	public Result<String> saveBatch(@RequestBody PimSoftwareInstallRecord pimSoftwareInstallRecord){
		try{
			pimSoftwareInstallRecordService.saveBatch(pimSoftwareInstallRecord.getPimSoftwareInstallRecordList());
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("保存成功");
	}
	
	/**
	 * 删除软件安装记录
	 */
	@RequiresPermissions("product:pimSoftwareInstallRecord:del")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Result<String> delete(@RequestBody PimSoftwareInstallRecord pimSoftwareInstallRecord) {
		try{
			pimSoftwareInstallRecordService.deleteByPrimaryKey(pimSoftwareInstallRecord);
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("删除成功");
	}
	
	/**
	 * 逻辑删除软件安装记录
	 */
	@RequiresPermissions("product:pimSoftwareInstallRecord:del")
	@RequestMapping(value = "deleteByLogic", method = RequestMethod.POST)
	public Result<String> deleteByLogic(@RequestBody PimSoftwareInstallRecord pimSoftwareInstallRecord) {
		try{
			pimSoftwareInstallRecord.setDelFlag("1");
			pimSoftwareInstallRecordService.deleteByLogic(pimSoftwareInstallRecord);
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("删除成功");
	}
	
	/**
	 * 批量删除软件安装记录
	 */
	@RequiresPermissions("product:pimSoftwareInstallRecord:del")
	@RequestMapping(value = "deleteBatch", method = RequestMethod.POST)
	public Result<String> deleteBatch(@RequestBody PimSoftwareInstallRecord pimSoftwareInstallRecord) {
		try{
			pimSoftwareInstallRecordService.deleteBatchByPrimaryKey(pimSoftwareInstallRecord.getPimSoftwareInstallRecordList());
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("删除成功");
	}
	
	/**
	 * 批量逻辑删除软件安装记录
	 */
	@RequiresPermissions("product:pimSoftwareInstallRecord:del")
	@RequestMapping(value = "deleteBatchByLogic", method = RequestMethod.POST)
	public Result<String> deleteBatchByLogic(@RequestBody PimSoftwareInstallRecord pimSoftwareInstallRecord) {
		try{
			for (PimSoftwareInstallRecord tmp : pimSoftwareInstallRecord.getPimSoftwareInstallRecordList()) {
				tmp.setDelFlag("1");
			}
			pimSoftwareInstallRecordService.deleteBatchByLogic(new BatchEntity<>(pimSoftwareInstallRecord.getPimSoftwareInstallRecordList()));
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("删除成功");
	}
	
	/**
	 * 初始化下拉列表、单选、复选框软件安装记录
	 */
	@RequiresPermissions(value = { "product:pimSoftwareInstallRecord:view", "product:pimSoftwareInstallRecord:add", "product:pimSoftwareInstallRecord:edit" }, logical = Logical.OR)
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
	@RequiresPermissions("product:pimSoftwareInstallRecord:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public void exportFile(HttpServletRequest request, HttpServletResponse response) {
		try {
            String fileName = "软件安装记录"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            PimSoftwareInstallRecord pimSoftwareInstallRecord = new PimSoftwareInstallRecord();
            List<PimSoftwareInstallRecord> resultList = pimSoftwareInstallRecordService.findListByEntity(pimSoftwareInstallRecord);
    		new ExportExcel("软件安装记录", PimSoftwareInstallRecord.class).setDataList(resultList).write(response, fileName).dispose();
		} catch (Exception e) {
			throw new ServerInternalException("导出软件安装记录记录失败！失败信息：", e);
		}
    }

}