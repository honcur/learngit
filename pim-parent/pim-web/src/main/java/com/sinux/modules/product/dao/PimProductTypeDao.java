/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.dao;

import com.sinux.core.persistence.BaseCrudDao;
import com.sinux.core.persistence.annotation.MyBatisDao;
import com.sinux.modules.product.entity.PimProductType;
import org.apache.ibatis.annotations.Param;

/**
 * 产品型号DAO接口
 * @author gebp
 * @version 2018-07-24
 */
@MyBatisDao
public interface PimProductTypeDao extends BaseCrudDao<PimProductType> {

    /**
     * 根据产品型号id删除一条产品型号记录
     * @param id 产品型号id
     */
    void deleteById(@Param("id") String id);

    /**
     * 根据型号名称查询产品型号记录
     * @param pimProductType 产品型号
     * @return 返回值
     */
    PimProductType selectByTypeName(PimProductType pimProductType);

}