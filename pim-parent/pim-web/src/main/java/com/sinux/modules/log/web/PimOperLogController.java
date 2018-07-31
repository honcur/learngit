/**
 * Copyright &copy; 2015-2020 <a href = "http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.log.web;


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
import com.sinux.modules.log.entity.PimOperLog;
import com.sinux.modules.log.entity.ext.PimOperLogExt;
import com.sinux.modules.log.service.PimOperLogService;

/**
 * 操作日志Controller
 * @author lf
 * @version 2018-07-26
 */
@RestController
@RequestMapping(value = "${adminPath}/log/operLog")
public class PimOperLogController extends BaseRestController {

	private static final Logger  logger = LoggerFactory.getLogger(PimOperLogController.class);
	
	@Autowired
	private PimOperLogService pimOperLogService;


	/**
	 　　* @Description: 产品分类分页获取
	 　　* @author lf
	 　　* @date 2018/7/20 13:27
	 　　*/
	@RequestMapping("/pageRes")
	public Result<Page<PimOperLog>> pageList(@RequestBody Page<PimOperLog> pageDto) throws IllegalAccessException, InstantiationException {
		Page<PimOperLog> page = null;
		page = pimOperLogService.findPimCateByPage(pageDto);
		return success(page);
	}

}