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
import com.sinux.core.utils.excel.ExportExcel;
import com.sinux.core.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import com.sinux.modules.product.entity.PimProductComponent;
import com.sinux.modules.product.entity.PimProductInfo;
import com.sinux.modules.product.entity.ext.PimProductComponentExt;
import com.sinux.modules.product.entity.vo.ProdComponentRespVo;
import com.sinux.modules.product.entity.vo.ProductComponentVo;
import com.sinux.modules.product.service.PimProductComponentService;
import com.sinux.modules.product.service.PimProductInfoService;

/**
 * 产品配套关系Controller
 * 
 * @author ygy
 * @version 2018-07-24
 */
@RestController
@RequestMapping(value = "${adminPath}/product/pimProductComponent")
public class PimProductComponentController extends BaseRestController {

	private static final Logger logger = LoggerFactory.getLogger(PimProductComponentController.class);
	
	@Autowired
	private PimProductInfoService pimProductInfoService;
	
	@Autowired
	private PimProductComponentService pimProductComponentService;

	/**
	 * 产品组成根据id查询
	 */
	@RequiresPermissions("product:pimProductComponent:view")
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public Result<PimProductComponent> findById(@PathVariable String id) {
		if (StringUtils.isEmpty(id)) {
			return error("id为空");
		}
		PimProductComponent entity = new PimProductComponent();
		entity.setId(id);
		List<PimProductComponent> list = null;
		try {
			list = pimProductComponentService.findListByEntity(entity);
		} catch (Exception e) {
			logger.error("系统异常", e);
			return error("系统异常");
		}
		if (CollectionUtils.isEmpty(list)) {
			return success();
		}
		if (list.size() > 1) {
			logger.error("主键不唯一,id = " + id);
			return error("数据有误，主键不唯一");
		}
		return success(list.get(0));
	}

	/**
	 * 产品组成根据ids查询
	 */
	@RequiresPermissions("product:pimProductComponent:view")
	@RequestMapping(value = "getByIds", method = RequestMethod.POST)
	public Result<List<PimProductComponent>> findListByIds(@RequestBody List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return error("参数为空");
		}
		PimProductComponentExt extEntity = new PimProductComponentExt();
		extEntity.setIds(ids);
		List<PimProductComponent> list = null;
		try {
			list = pimProductComponentService.findListByExtEntity(extEntity);
		} catch (Exception e) {
			logger.error("系统异常", e);
			return error("系统异常");
		}
		if (CollectionUtils.isEmpty(list)) {
			return success();
		}
		return success(list);
	}

	/**
	 * 多条数据新增产品组成
	 */
	@RequiresPermissions(value = { "product:pimProductComponent:add",
			"product:pimProductComponent:edit" }, logical = Logical.OR)
	@RequestMapping(value = "saveBatch", method = RequestMethod.POST)
	public Result<String> saveBatch(@RequestBody PimProductComponent pimProductComponent) {
		try {
			pimProductComponentService.saveBatch(pimProductComponent.getPimProductComponentList());
		} catch (Exception e) {
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("保存成功");
	}

	/**
	 * 删除产品组成
	 */
	@RequiresPermissions("product:pimProductComponent:del")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Result<String> delete(@RequestBody PimProductComponent pimProductComponent) {
		try {
			pimProductComponentService.deleteByPrimaryKey(pimProductComponent);
		} catch (Exception e) {
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("删除成功");
	}

	/**
	 * 逻辑删除产品组成
	 */
	@RequiresPermissions("product:pimProductComponent:del")
	@RequestMapping(value = "deleteByLogic", method = RequestMethod.POST)
	public Result<String> deleteByLogic(@RequestBody PimProductComponent pimProductComponent) {
		try {
			pimProductComponent.setDelFlag("1");
			pimProductComponentService.deleteByLogic(pimProductComponent);
		} catch (Exception e) {
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("删除成功");
	}

	/**
	 * 批量删除产品组成
	 */
	@RequiresPermissions("product:pimProductComponent:del")
	@RequestMapping(value = "deleteBatch", method = RequestMethod.POST)
	public Result<String> deleteBatch(@RequestBody PimProductComponent pimProductComponent) {
		try {
			pimProductComponentService.deleteBatchByPrimaryKey(pimProductComponent.getPimProductComponentList());
		} catch (Exception e) {
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("删除成功");
	}

	/**
	 * 批量逻辑删除产品组成
	 */
	@RequiresPermissions("product:pimProductComponent:del")
	@RequestMapping(value = "deleteBatchByLogic", method = RequestMethod.POST)
	public Result<String> deleteBatchByLogic(@RequestBody PimProductComponent pimProductComponent) {
		try {
			for (PimProductComponent tmp : pimProductComponent.getPimProductComponentList()) {
				tmp.setDelFlag("1");
			}
			pimProductComponentService
					.deleteBatchByLogic(new BatchEntity<>(pimProductComponent.getPimProductComponentList()));
		} catch (Exception e) {
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success("删除成功");
	}

	/**
	 * 初始化下拉列表、单选、复选框产品组成
	 */
	@RequiresPermissions(value = { "product:pimProductComponent:view", "product:pimProductComponent:add",
			"product:pimProductComponent:edit" }, logical = Logical.OR)
	@RequestMapping(value = "init", method = RequestMethod.GET)
	public Result<Map<String, Object>> initData() {
		Map<String, Object> map = new HashMap<>();
		try {
		} catch (Exception e) {
			logger.error("系统异常", e);
			return error("系统异常");
		}
		return success(map);
	}

	/**
	 * 导出excel文件
	 */
	@RequestMapping("/export")
	public void exportFile(HttpServletRequest request, HttpServletResponse response) {
		try {
			String fileName = "产品组成" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			PimProductComponent pimProductComponent = new PimProductComponent();
			List<PimProductComponent> resultList = pimProductComponentService.findListByEntity(pimProductComponent);
			new ExportExcel("产品组成", PimProductComponent.class).setDataList(resultList).write(response, fileName)
					.dispose();
		} catch (Exception e) {
			throw new ServerInternalException("导出产品组成记录失败！失败信息：", e);
		}
	}

	/**
	 * @author ygy
	 * @date 2018年7月24日
	 * @param pageDto
	 * @return Result<Page<PimProductComponent>> Description: 根据产品id分页查询配套关系列表
	 */
	@RequestMapping("/list")
	public Result<Page<PimProductInfo>> pageList(@RequestBody Page<PimProductInfo> pageDto) {
		Page<PimProductInfo> page = null;
		page = pimProductComponentService.selectAttachmentByProductId(pageDto);
		return success(page);
	}

	/**
	 * @author ygy
	 * @date 2018年7月25日
	 * @param pimProductComponent
	 * @return Result<String> Description: 保存或修改多个被编辑的ProductComponentVo对象
	 */
	@RequestMapping("/saveList")
	public Result<String> saveList(@RequestBody List<ProductComponentVo> pcvList) {
		pimProductComponentService.saveList(pcvList);

		return success("保存成功");
	}
	
	@RequestMapping("/deleteList")
	public Result<String> deleteList(@RequestBody List<ProductComponentVo> pcvList) {
		pimProductComponentService.deleteList(pcvList);
		
		return success("删除成功");
	}
	
	/**
	 * @author ygy
	 * @date 2018年7月26日
	 * @param pcvList
	 * @return
	 * Result<String>  
	 * Description: 查询是附属件的产品基本信息	
	 */
	@RequestMapping("/selectAttachment")
	public Result<Page<PimProductInfo>> selectAttachment(@RequestBody Page<PimProductInfo> pageDto) {
		Page<PimProductInfo> page = null;
		page = pimProductComponentService.selectAttachment(pageDto);
		return success(page);
	}
	
	/**
	 * @author ygy
	 * @date 2018年7月30日
	 * @param pageDto
	 * @return
	 * Result<Page<PimProductInfo>>  
	 * Description: 根据产品id得到其组件的相关信息 	
	 */
	@RequestMapping("/selectAttachmentByProductId")
	public Result<Page<PimProductInfo>> selectAttachmentByProductId(@RequestBody Page<PimProductInfo> pageDto) {
		Page<PimProductInfo> page = null;
		page = pimProductComponentService.selectAttachmentByProductId(pageDto);
		return success(page);
	}
	
	/**
	 * @author ygy
	 * @date 2018年7月30日
	 * @param pageDto
	 * @return
	 * Result<Page<PimProductInfo>>  
	 * Description: 根据产品id得到其组件的相关信息 	
	 */
	@RequestMapping("/compInfos")
	public Result<Page<ProdComponentRespVo>> compInfos(@RequestBody Page<ProdComponentRespVo> pageDto) {
		Page<ProdComponentRespVo> page = null;
		page = pimProductComponentService.findCompInfos(pageDto);
		return success(page);
	}
	
	
	@RequestMapping("/compInfossss")
	public Result<List<ProdComponentRespVo>> compInfosss(@RequestBody ProdComponentRespVo entity) {
		List<ProdComponentRespVo> page = null;
		page = pimProductComponentService.findCompInfosss(entity);
		return success(page);
	}
	
	
	

}