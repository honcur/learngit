/**
 * Copyright &copy; 2015-2020 <a href = "http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.sinux.core.dto.Result;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import com.sinux.core.utils.excel.ExportExcel;
import com.sinux.core.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import com.sinux.modules.product.entity.PimProductInfo;
import com.sinux.modules.product.entity.ext.PimProductInfoExt;
import com.sinux.modules.product.service.PimProductInfoService;

/**
 * 产品基本信息Controller
 * @author ygy
 * @version 2018-07-20
 */
@RestController
@RequestMapping(value = "${adminPath}/product/pimProductInfo")
public class PimProductInfoController extends BaseRestController {

	private static final Logger  logger = LoggerFactory.getLogger(PimProductInfoController.class);
	
	@Autowired
	private PimProductInfoService pimProductInfoService;
	


	/**
     * 产品基本信息根据ids查询
     */
    @RequiresPermissions("product.baseinfo:pimProductInfo:view")
    @RequestMapping(value = "getByIds", method = RequestMethod.POST)
	public Result<List<PimProductInfo>> findListByIds(@RequestBody List<String> ids){
	    if(CollectionUtils.isEmpty(ids)) {
	        return error("参数为空");
	    }
	    PimProductInfoExt extEntity = new PimProductInfoExt();
	    extEntity.setIds(ids);
	    List<PimProductInfo> list = null;
	    try {
	        list = pimProductInfoService.findListByExtEntity(extEntity);
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
	 * 多条数据新增产品基本信息
	 */
	@RequiresPermissions(value = {"product.baseinfo:pimProductInfo:add","product.baseinfo:pimProductInfo:edit"},logical = Logical.OR)
	@RequestMapping(value = "saveBatch", method = RequestMethod.POST)
	public Result<String> saveBatch(@RequestBody PimProductInfo pimProductInfo){
		try{
			pimProductInfoService.saveBatch(pimProductInfo.getPimProductInfoList());
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("保存成功");
	}
	
	/**
	 * 逻辑删除产品基本信息
	 */
	@RequiresPermissions("product.baseinfo:pimProductInfo:del")
	@RequestMapping(value = "deleteByLogic", method = RequestMethod.POST)
	public Result<String> deleteByLogic(@RequestBody PimProductInfo pimProductInfo) {
		try{
			pimProductInfo.setDelFlag("1");
			pimProductInfoService.deleteByLogic(pimProductInfo);
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("删除成功");
	}
	
	/**
	 * 批量删除产品基本信息
	 */
	@RequiresPermissions("product.baseinfo:pimProductInfo:del")
	@RequestMapping(value = "deleteBatch", method = RequestMethod.POST)
	public Result<String> deleteBatch(@RequestBody PimProductInfo pimProductInfo) {
		try{
			pimProductInfoService.deleteBatchByPrimaryKey(pimProductInfo.getPimProductInfoList());
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("删除成功");
	}
	
	/**
	 * 批量逻辑删除产品基本信息
	 */
	@RequiresPermissions("product.baseinfo:pimProductInfo:del")
	@RequestMapping(value = "deleteBatchByLogic", method = RequestMethod.POST)
	public Result<String> deleteBatchByLogic(@RequestBody PimProductInfo pimProductInfo) {
		try{
			for (PimProductInfo tmp : pimProductInfo.getPimProductInfoList()) {
				tmp.setDelFlag("1");
			}
			pimProductInfoService.deleteBatchByLogic(new BatchEntity<>(pimProductInfo.getPimProductInfoList()));
		}catch(Exception e){
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("删除成功");
	}
	
	/**
	 * 初始化下拉列表、单选、复选框产品基本信息
	 */
	@RequiresPermissions(value = { "product.baseinfo:pimProductInfo:view", "product.baseinfo:pimProductInfo:add", "product.baseinfo:pimProductInfo:edit" }, logical = Logical.OR)
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
	@RequiresPermissions("product.baseinfo:pimProductInfo:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public void exportFile(HttpServletRequest request, HttpServletResponse response) {
		try {
            String fileName = "产品基本信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            PimProductInfo pimProductInfo = new PimProductInfo();
            List<PimProductInfo> resultList = pimProductInfoService.findListByEntity(pimProductInfo);
    		new ExportExcel("产品基本信息", PimProductInfo.class).setDataList(resultList).write(response, fileName).dispose();
		} catch (Exception e) {
			throw new ServerInternalException("导出产品基本信息记录失败！失败信息：", e);
		}
    }
	
	/**
	 * @author ygy
	 * @date 2018年7月19日
	 * @param pageDto
	 * @return
	 * Description:根据分类ID得到产品基本信息并分页
	 */
	@RequestMapping("/listByCategoryId")
	public Result<Page<PimProductInfo>> pageListByCategoryId(@RequestBody Page<PimProductInfo> pageDto) {

		Page<PimProductInfo> page = pimProductInfoService.findPageByCategoryId(pageDto);

		return success(page);
	}

	/**
	 * @author ygy
	 * @date 2018年7月19日
	 * @param id
	 * @return
	 * Description: 根据id得到产品基本信息
	 */
	@RequestMapping("/findById")
	public Result<PimProductInfo> findById(@RequestBody PimProductInfo pimProductInfo) {
		return success(pimProductInfoService.findById(pimProductInfo.getId()).get(0));
	}

	/**
	 * @author ygy
	 * @date 2018年7月19日
	 * @param pimProductInfo
	 * @return Result<String>
	 * Description:添加单条产品基本信息记录
	 */
	@RequestMapping("/save")
	public Result<String> saveSingle(@RequestBody PimProductInfo pimProductInfo) {

		pimProductInfoService.saveSingle(pimProductInfo);

		return success("添加成功");
	}

	/**
	 * @author ygy
	 * @date 2018年7月19日
	 * @param pimProductInfo
	 * @return Result<String>
	 * Description:删除单条产品基本信息记录
	 */
	@RequestMapping("/delete")
	public Result<String> delete(@RequestBody PimProductInfo pimProductInfo) {

		pimProductInfoService.deleteSingle(pimProductInfo);

		return success("删除成功");
	}

	/**
	 * @author ygy
	 * @date 2018年7月20日
	 * @param pageDto
	 * @return Result<Page<PimProductInfo>>
	 * Description: 所有产品基本信息分页查询列表
	 */
	@RequestMapping("/list")
	public Result<Page<PimProductInfo>> pageList(@RequestBody Page<PimProductInfo> pageDto) {
		
		Page<PimProductInfo> page = pimProductInfoService.findPageAll(pageDto);

		

		return success(page);
	}
	
	
	/**
	 * @author ygy
	 * @date 2018年7月20日
	 * @param pageDto
	 * @return
	 * Result<Page<PimProductInfo>>  
	 * Description: 根据产品名称、编码、代号模糊查询	
	 */
	@RequestMapping("/searchLike")
	public Result<Page<PimProductInfo>> findPageByKeywords(@RequestBody Page<PimProductInfo> pageDto) {
		Page<PimProductInfo> page = null;

		page = pimProductInfoService.findPageByKeywords(pageDto);

		return success(page);
	}
	
	/**
	 * @author ygy
	 * @date 2018年7月20日
	 * @param pimProductInfo
	 * @return
	 * Result<String>  
	 * Description: 修改单条基本信息记录 	
	 */
	@RequestMapping("/update")
	public Result<String> updateSingle(@RequestBody PimProductInfo pimProductInfo) {

		pimProductInfoService.updateSingle(pimProductInfo);

		return success("修改成功");
	}

}