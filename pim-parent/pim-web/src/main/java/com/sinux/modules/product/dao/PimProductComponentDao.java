/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.dao;

import java.util.List;
import java.util.Map;

import com.sinux.core.persistence.BaseCrudDao;
import com.sinux.core.persistence.annotation.MyBatisDao;
import com.sinux.modules.product.entity.PimProductComponent;
import com.sinux.modules.product.entity.vo.ProductComponentVo;

/**
 * 产品组成DAO接口
 * @author ygy
 * @version 2018-07-24
 */
@MyBatisDao
public interface PimProductComponentDao extends BaseCrudDao<PimProductComponent> {

	ProductComponentVo selectComponentsByComponentId(String componentId);

	/**
	 * @author ygy
	 * @date 2018年7月26日
	 * @param productId
	 * @return
	 * List<String>  
	 * Description: 根据产品id得到其对应的所有的组件id	
	 */
	List<String> getComponentIds(String productId);

	List<String> selectTypeNamesByComponentId(String componentId);

	/**
	 * @author ygy
	 * @date 2018年7月26日
	 * @param map
	 * @return
	 * PimProductComponent  
	 * Description: 通过组件id和产品id得到对应的组件
	 */
	PimProductComponent selectWithPidAndCid(Map<String, String> map);

	void addComponent(ProductComponentVo productComponentVo);

	void updateComponent(ProductComponentVo productComponentVo);

	void deleteComponent(ProductComponentVo productComponentVo);

}