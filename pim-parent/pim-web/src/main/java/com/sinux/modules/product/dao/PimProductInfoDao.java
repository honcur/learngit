/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.dao;

import java.util.List;
import java.util.Map;

import com.sinux.core.persistence.BaseCrudDao;
import com.sinux.core.persistence.annotation.MyBatisDao;
import com.sinux.modules.product.entity.PimProductInfo;
import com.sinux.modules.product.entity.vo.ProdComponentRespVo;

/**
 * 产品基本信息DAO接口
 * @author ygy
 * @version 2018-07-20
 */
@MyBatisDao
public interface PimProductInfoDao extends BaseCrudDao<PimProductInfo> {

	PimProductInfo selectWithNameAndNum(Map<String, String> map);

	PimProductInfo isOriginateFromErp(Map<String, String> map);

	List<PimProductInfo> selectByKeywords(String keywords);

	
	/**
	 * @author ygy
	 * @date 2018年7月26日
	 * @param entity
	 * @return
	 * List<PimProductInfo>  
	 * Description: 根据产品名称或产品类型模糊查询	
	 */
	List<PimProductInfo> selectAttachment(PimProductInfo entity);

	/**
	 * @author ygy
	 * @date 2018年7月27日
	 * @param entity
	 * @return
	 * List<PimProductInfo>  
	 * Description: 根据产品id得到对应的组件对象集合	
	 */
	List<PimProductInfo> selectComponentByProductId(PimProductInfo entity);

	/**
	 * @author ygy
	 * @date 2018年7月27日
	 * @return
	 * List<PimProductInfo>  
	 * Description: 根据组件基本信息得到组件其他相关信息 	
	 */
	List<PimProductInfo> selectAttachmentByProductId(PimProductInfo entity);

	List<ProdComponentRespVo> findCompInfos(ProdComponentRespVo entity);
	
	
	

}