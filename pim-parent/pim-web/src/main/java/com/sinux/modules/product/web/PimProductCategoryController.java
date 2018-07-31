/**
 * Copyright &copy; 2015-2020 <a href = "http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.web;


import com.sinux.core.dto.Result;
import com.sinux.core.persistence.Page;
import com.sinux.core.web.BaseRestController;
import com.sinux.modules.product.entity.PimProductCategory;
import com.sinux.modules.product.entity.vo.ProdCategoryRespVo;
import com.sinux.modules.product.service.PimProductCategoryService;
import com.sinux.pim.common.entity.CommonContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 产品分类Controller
 *
 * @author lf
 * @version 2018-07-18
 */
@RestController
@RequestMapping(value = "${adminPath}/product/category")
public class PimProductCategoryController extends BaseRestController {

    private static final Logger logger = LoggerFactory.getLogger(PimProductCategoryController.class);

    @Autowired
    private PimProductCategoryService productCategoryService;

    /**
     * 　　* @Description: 产品分类分页获取
     * 　　* @author lf
     * 　　* @date 2018/7/20 13:27
     *
     */
    @RequestMapping("/pageRes")
    public Result<Page<PimProductCategory>> pageList(@RequestBody Page<PimProductCategory> pageDto) throws IllegalAccessException, InstantiationException {
        Page<PimProductCategory> page = null;
        page = productCategoryService.findPimCateByPage(pageDto);
        return success(page);
    }

    /**
     * 　　* @Description: 新增分类
     * 　　* @param productCategory 保存的实体
     * 　　* @author lf
     * 　　* @date 2018/7/19 13:09
     */
    @RequestMapping("/save")
    public Result<String> save(@RequestBody PimProductCategory productCategory) {
        productCategoryService.saveProdCategory(productCategory);
        return success(CommonContent.UPDATE_SUCCESS);
    }

    /**
     * 　　* @Description: 更新分类信息
     * 　　* @param productCategory 更新实体
     * 　　* @author lf
     * 　　* @date 2018/7/19 13:09
     */
    @RequestMapping("/update")
    public Result<String> update(@RequestBody PimProductCategory productCategory) {
        productCategoryService.updateProdCategory(productCategory);
        return success(CommonContent.UPDATE_SUCCESS);
    }

    /**
     * 　　* @Description: 获取表中所有数据（树状结构实体类对象）
     * 　　* @param: id 顶级分类id
     * 　　* @author lf
     * 　　* @date 2018/7/19 9:15
     */
    @RequestMapping("/all")
    public Result<List<ProdCategoryRespVo>> getAll() {
        List<ProdCategoryRespVo> res = null;
        res = productCategoryService.getAll();
        return success(res);
    }

    /**
     * 　　* @Description: 通过产品分类 id 删除信息
     * 　　* @param 产品分类 id
     * 　　* @author lf
     * 　　* @date 2018/7/19 15:30
     *
     */
    @RequestMapping("/delete")
    public Result<String> delete(@RequestBody List<PimProductCategory> prodCategorys) {
        productCategoryService.deleteProCategory(prodCategorys);
        return success(CommonContent.DELETE_SUCCESS);
    }

}