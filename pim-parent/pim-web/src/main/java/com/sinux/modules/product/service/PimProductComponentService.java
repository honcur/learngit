/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections.CollectionUtils;
import com.sinux.core.persistence.BatchEntity;
import com.sinux.core.persistence.Page;
import com.sinux.core.service.BaseCrudService;
import com.sinux.modules.product.entity.PimProductComponent;
import com.sinux.modules.product.entity.PimProductInfo;
import com.sinux.modules.product.entity.vo.ProdComponentRespVo;
import com.sinux.modules.product.entity.vo.ProductComponentVo;
import com.sinux.modules.exception.entity.PimBusException;
import com.sinux.modules.product.dao.PimProductComponentDao;
import com.sinux.modules.product.dao.PimProductInfoDao;

/**
 * 产品组成Service
 * 
 * @author ygy
 * @version 2018-07-24
 */
@Service
public class PimProductComponentService extends BaseCrudService<PimProductComponentDao, PimProductComponent> {

	@Autowired
	private PimProductComponentDao pimProductComponentDao;
	
	@Autowired
	private PimProductInfoDao pimProductInfoDao;

	/**
	 * 批量保存，添加、修改通用接口
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveBatch(List<PimProductComponent> list) {
		List<PimProductComponent> insertList = new ArrayList<>();
		List<PimProductComponent> updtaeList = new ArrayList<>();
		for (PimProductComponent tmp : list) {
			int insertStatus = 0;
			if (insertStatus == 1) {
				tmp.preInsert();
				insertList.add(tmp);
			} else {
				tmp.preUpdate();
				updtaeList.add(tmp);
			}
		}
		if (CollectionUtils.isNotEmpty(insertList)) {
			dao.insertBatch(new BatchEntity<>(insertList));
		}
		if (CollectionUtils.isNotEmpty(updtaeList)) {
			dao.updateBatchByPrimaryKey(new BatchEntity<>(updtaeList));
		}
	}

	/**
	 * @author ygy
	 * @date 2018年7月24日
	 * @param pageDto
	 * @param clazz
	 * @return Page<ProductComponentVo> Description: 根据产品id查询其对应的组件产品集合
	 */
	public Page<ProductComponentVo> findPageByProductId(Page<ProductComponentVo> pageDto) {
		if (pageDto == null) {
			pageDto = new Page<ProductComponentVo>();
		}
		if (pageDto.getQuery() == null) {
			pageDto.setQuery(new ProductComponentVo());
		}
		ProductComponentVo entity = pageDto.getQuery();
		entity.setPage(pageDto);

		/**
		 * 得到产品id，并根据产品id得到其对应的所有组件id的集合
		 */
		String productId = entity.getId();
		List<String> strList = pimProductComponentDao.getComponentIds(productId);
		/**
		 * 遍历组件id集合得到对应的配套产品ProductComponentVo对象集合
		 */
		List<ProductComponentVo> pcvList = new ArrayList<>();
		for (String componentId : strList) {
			ProductComponentVo component = pimProductComponentDao.selectComponentsByComponentId(componentId);
			if (component == null) {
				throw new PimBusException("查询异常");
			}
			/**
			 * 根据产品id得到对应的类型名称集合,和产品id一起封装到ProductComponentVo对象中
			 */
			component.setProductId(productId);
			List<String> typeNameList = pimProductComponentDao.selectTypeNamesByComponentId(componentId);
			if (typeNameList == null) {
				throw new PimBusException("查询异常");
			}
			component.setTypeNameList(typeNameList);
			pcvList.add(component);
		}
		if (pcvList.size() <= 0) {
			throw new PimBusException("未查询到相关信息");
		}

		pageDto.setList(pcvList);
		return pageDto;
	}

	/**
	 * @author ygy
	 * @date 2018年7月25日
	 * @param pcvList
	 * void  
	 * Description: 保存或修改多个被编辑的ProductComponentVo对象 	
	 */
	@Transactional
	public void saveList(List<ProductComponentVo> pcvList) {
		// TODO Auto-generated method stub
		for (ProductComponentVo productComponentVo : pcvList) {
			/**
			 * 判断该配套关系是否存在，若存在则修改，不存在则新增记录
			 */
			if(isExist(productComponentVo)) {
				//更新组件信息
				pimProductComponentDao.updateComponent(productComponentVo);
			}else {
				//新增组件信息记录
				pimProductComponentDao.addComponent(productComponentVo);
			}
		}
	}

	/**
	 * @author ygy
	 * @date 2018年7月25日
	 * @param productComponentVo
	 * @return
	 * boolean  
	 * Description: 判断该配套关系对象是否存在 	
	 */
	private boolean isExist(ProductComponentVo productComponentVo) {
		// TODO Auto-generated method stub
		String productId=productComponentVo.getProductId();
		String componentId=productComponentVo.getComponentId();
		Map<String,String> map=new HashMap<>();
		map.put("productId", productId);
		map.put("componentId", componentId);
		PimProductComponent ppc=pimProductComponentDao.selectWithPidAndCid(map);
		if(ppc != null) {
			return true;
		}else {
			return false;
		}
		
	}

	/**
	 * @author ygy
	 * @date 2018年7月26日
	 * @param pcvList
	 * void  
	 * Description: 批量删除选中配套关系
	 */
	@Transactional
	public void deleteList(List<ProductComponentVo> pcvList) {
		// TODO Auto-generated method stub
		for (ProductComponentVo productComponentVo : pcvList) {
			/**
			 * 判断要删除的组件信息是否存在，若存在则删除，若不存在，则提示错误信息
			 */
			if(isExist(productComponentVo)) {
				pimProductComponentDao.deleteComponent(productComponentVo);
			}else {
				throw new PimBusException("要删除的信息已经不存在");
			}
		}
	}

	/**
	 * @author ygy
	 * @date 2018年7月26日
	 * @param pageDto
	 * @return
	 * Page<PimProductInfo>  
	 * Description: 查找是附属件的产品的相关信息 	
	 */
	public Page<PimProductInfo> selectAttachment(Page<PimProductInfo> pageDto) {
		// TODO Auto-generated method stub
		if (pageDto == null) {
			pageDto = new Page<PimProductInfo>();
		}
		if (pageDto.getQuery() == null) {
			pageDto.setQuery(new PimProductInfo());
		}
		PimProductInfo entity = pageDto.getQuery();
		entity.setPage(pageDto);
		List<PimProductInfo> ppiList=null;
		ppiList = pimProductInfoDao.selectAttachment(entity);
		
		if (ppiList == null) {
			throw new PimBusException("未查询到相关信息");
		}
		pageDto.setList(ppiList);
		return pageDto;
	}

	/**
	 * @author ygy
	 * @date 2018年7月27日
	 * @param pageDto
	 * @return
	 * Page<PimProductInfo>  
	 * Description: 根据产品id得到其所有组件产品的相关信息 	
	 */
	public Page<PimProductInfo> selectAttachmentByProductId(Page<PimProductInfo> pageDto) {
		// TODO Auto-generated method stub
		
		if (pageDto == null) {
			pageDto = new Page<PimProductInfo>();
		}
		if (pageDto.getQuery() == null) {
			pageDto.setQuery(new PimProductInfo());
		}
		PimProductInfo entity = pageDto.getQuery();
		entity.setPage(pageDto);
		
		List<PimProductInfo> ppiList=null;
	
		ppiList=pimProductInfoDao.selectAttachmentByProductId(entity);
		if(ppiList == null || ppiList.size()<=0) {
			throw new PimBusException("未查询到相关的信息");
		}
		
		pageDto.setList(ppiList);
		return pageDto;
		
	}

	public Page<ProdComponentRespVo> findCompInfos(Page<ProdComponentRespVo> pageDto) {
		// TODO Auto-generated method stub\
		if (pageDto == null) {
			pageDto = new Page<ProdComponentRespVo>();
		}
		if (pageDto.getQuery() == null) {
			pageDto.setQuery(new ProdComponentRespVo());
		}
		ProdComponentRespVo entity = pageDto.getQuery();
		entity.setPage(pageDto);
		
		List<ProdComponentRespVo> ppiList=null;
	
		ppiList=pimProductInfoDao.findCompInfos(entity);
		if(ppiList == null || ppiList.size()<=0) {
			throw new PimBusException("未查询到相关的信息");
		}
		
		pageDto.setList(ppiList);
		return pageDto;
	}

	public List<ProdComponentRespVo> findCompInfosss(ProdComponentRespVo entity) {
		// TODO Auto-generated method stub
		return pimProductInfoDao.findCompInfos(entity);
	}

	
}