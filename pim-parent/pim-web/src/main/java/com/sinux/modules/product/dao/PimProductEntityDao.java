/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sinux.core.persistence.BaseCrudDao;
import com.sinux.core.persistence.annotation.MyBatisDao;
import com.sinux.modules.product.entity.PimProductEntity;

/**
 * 产品实体DAO接口
 * @author xiaolm
 * @version 2018-07-25
 */
@MyBatisDao
public interface PimProductEntityDao extends BaseCrudDao<PimProductEntity> {
	/**
	 * 通过产品编号查询产品实体个数
	 * 
	 * @param entityNumber 产品编号
	 * @param exceptedId  排除在外产品实体ID
	 * @return
	 */
	Long selectByEntityNumber(@Param("entityNumber") String entityNumber, @Param("excludeId")String excludeId);

}