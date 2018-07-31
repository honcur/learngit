/**
 * Copyright &copy; 2015-2020 <a href = "http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.auth.web;


import com.sinux.pim.common.entity.CommonContent;
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
import com.sinux.modules.auth.entity.PimDataAuth;
import com.sinux.modules.auth.entity.ext.PimDataAuthExt;
import com.sinux.modules.auth.service.PimDataAuthService;

/**
 * 数据权限Controller
 *
 * @author lf
 * @version 2018-07-30
 */
@RestController
@RequestMapping(value = "${adminPath}/auth/data")
public class PimDataAuthController extends BaseRestController {

    private static final Logger logger = LoggerFactory.getLogger(PimDataAuthController.class);

    @Autowired
    private PimDataAuthService pimDataAuthService;

    /**
     * 　　* @Description: 数据权限保存
     * 　　* @author lf
     * 　　* @date 2018/7/30 11:29
     *
     */
    @RequestMapping("save")
    public Result<String> saveDataAuth(@RequestBody List<PimDataAuth> pimDataAuths) {
        pimDataAuthService.saveDataAuth(pimDataAuths);
        return success(CommonContent.UPDATE_SUCCESS);
    }

    /**
     * 　　* @Description: 数据权限分页获取
     * 　　* @author lf
     * 　　* @date 2018/7/20 13:27
     */
    @RequestMapping("pageRes")
    public Result<Page<PimDataAuth>> findByPage(@RequestBody Page<PimDataAuth> pageDto) throws IllegalAccessException, InstantiationException {
        Page<PimDataAuth> page = null;
        page = pimDataAuthService.findAuthDataByPage(pageDto);
        return success(page);
    }

    /**
    　　* @Description: 删除数据权限 通过UserId
    　　* @author lf
    　　* @date 2018/7/30 13:24
    　　*/
    @RequestMapping("delete")
    public Result<String> deleteDataAuth(@RequestBody List<PimDataAuth> pimDataAuths){
        pimDataAuthService.deleteDataAuth(pimDataAuths);
        return success(CommonContent.DELETE_SUCCESS);
    }
}