/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.dao;

import com.sinux.core.persistence.BaseCrudDao;
import com.sinux.core.persistence.annotation.MyBatisDao;
import com.sinux.modules.product.entity.PimProductCategory;
import com.sinux.modules.product.entity.vo.ProdCategoryRespVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品分类DAO接口
 *
 * @author lf
 * @version 2018-07-18
 */
@MyBatisDao
public interface PimProductCategoryDao extends BaseCrudDao<PimProductCategory> {

    /**
     * 　　* @Description: 通过 条件筛选数据
     * 　　* @author lf
     * 　　* @date 2018/7/18 16:48
     *
     */
    PimProductCategory selectWithNameOrNum(PimProductCategory productCategory);

    /**
     * 　　* @Description: 通过id 筛选数据
     * 　　* @author lf
     * 　　* @date 2018/7/18 17:19
     *
     */
    PimProductCategory selectById(@Param("id") String id);

    /**
     * 　　* @Description: 通过id删除数据
     * 　　* @author lf
     * 　　* @date 2018/7/19 15:44
     *
     */
    void deleteById(@Param("id") String id);


    /**
     * 　　* @Description: 通过上级id 获取下级list
     * 　　* @author lf
     * 　　* @date 2018/7/20 14:22
     *
     */
    List<ProdCategoryRespVo> selectByParentId(@Param("parentId") String id);

    /**
     * 　　* @Description: 获取所有顶级分类
     * 　　* @author lf
     * 　　* @date 2018/7/20 14:21
     *
     */
    List<ProdCategoryRespVo> selectAllParents();
}