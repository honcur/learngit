/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.dao;

import com.sinux.core.persistence.BaseCrudDao;
import com.sinux.core.persistence.annotation.MyBatisDao;
import com.sinux.modules.product.entity.PimInWarehouseOrder;

/**
 * 产品入库单DAO接口
 * @author xiaolm
 * @version 2018-07-23
 */
@MyBatisDao
public interface PimInWarehouseOrderDao extends BaseCrudDao<PimInWarehouseOrder> {

}