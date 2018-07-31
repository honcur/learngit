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
import com.sinux.core.utils.StringUtils;
import com.sinux.modules.product.entity.PimProductInfo;
import com.sinux.modules.exception.entity.PimBusException;
import com.sinux.modules.product.dao.PimProductInfoDao;

/**
 * 产品基本信息Service
 * 
 * @author ygy
 * @version 2018-07-20
 */
@Service
public class PimProductInfoService extends BaseCrudService<PimProductInfoDao, PimProductInfo> {

	@Autowired
	private PimProductInfoDao pimProductInfoDao;

	/**
	 * 批量保存，添加、修改通用接口
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveBatch(List<PimProductInfo> list) {
		List<PimProductInfo> insertList = new ArrayList<>();
		List<PimProductInfo> updtaeList = new ArrayList<>();
		for (PimProductInfo tmp : list) {
			int insertStatus = 0;
			if (tmp.getId() == null) {
				insertStatus = 1;
			}
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
	 * @date 2018年7月19日
	 * @param pageDto
	 * @return Description:通过分类Id查询产品信息并分页
	 */
	public Page<PimProductInfo> findPageByCategoryId(Page<PimProductInfo> pageDto) {
		// TODO Auto-generated method stub
		if (pageDto == null) {
			pageDto = new Page<PimProductInfo>();
		}
		if (pageDto.getQuery() == null) {
			pageDto.setQuery(new PimProductInfo());
		}
		PimProductInfo entity = pageDto.getQuery();
//	        entity.setCategoryId(categoryId);
		entity.setPage(pageDto);
		List<PimProductInfo> ppiList = dao.select(entity);
		if (ppiList == null) {
			throw new PimBusException("未查询到相关信息");
		}
		pageDto.setList(ppiList);
		return pageDto;
	}

	/**
	 * @author ygy
	 * @date 2018年7月19日
	 * @param id
	 * @return Description:通过分类ID查询产品信息
	 */
	public List<PimProductInfo> findById(String id) {
		if (StringUtils.isEmpty(id)) {
			throw new PimBusException("id为空");
		}
		PimProductInfo entity = new PimProductInfo();
		entity.setId(id);
		List<PimProductInfo> list = findListByEntity(entity);
		if (list == null) {
			throw new PimBusException("未查询到相关信息");
		}
		if (list.size() > 1) {
			throw new PimBusException("数据有误，主键不唯一");
		}
		return list;
	}

	/**
	 * @author ygy
	 * @date 2018年7月19日
	 * @param pimProductInfo void Description: 插入单条产品基本信息记录
	 */
	@Transactional
	public void saveSingle(PimProductInfo pimProductInfo) {
		// TODO Auto-generated method stub
		PimProductInfo ppi = getExistPimProductInfo(pimProductInfo);
		
		/**
		 *若基本信息是来自ERP系统，判断PIM系统中是否存在该产品基本信息，若存在则更新，若不存在则插入记录
		 */
		if (pimProductInfo.getProductSource().equals("ERP")) {
			if (!isRepeat(pimProductInfo)) {
				pimProductInfo.preInsert();
				dao.insert(pimProductInfo);
			} else {

				pimProductInfo.setId(ppi.getId());
				pimProductInfo.preUpdate();
				dao.updateByPrimaryKey(pimProductInfo);
			}
		} 
		/**
		 * 若基本信息不来自ERP系统，判断PIM系统中是否存在该产品基本信息：
		 * 		
		 * 		若不存在：则插入一条新的记录
		 * 
		 * 		若存在：判断已存在的PIM中的数据是否来自于ERP。若是，什么都不做、若不是，则更新数据
		 * 
		 */
		else {

			if (!isRepeat(pimProductInfo)) {
				pimProductInfo.preInsert();
				dao.insert(pimProductInfo);
			} else {
				if (ppi.getProductSource().equals("ERP")) {
					throw new PimBusException("产品名称或者编码重复");
				} else {
					pimProductInfo.setId(ppi.getId());
					pimProductInfo.preUpdate();
					dao.updateByPrimaryKey(pimProductInfo);
				}

			}
		}
	}

	/**
	 * @author ygy
	 * @date 2018年7月20日
	 * @param pimProductInfo void Description: 删除单条产品基本信息记录
	 */
	@Transactional
	public void deleteSingle(PimProductInfo pimProductInfo) {
		// TODO Auto-generated method stub
		
		/**
		 * 判断删除的数据是否还存在，若不存在则抛出异常信息“该信息不存在”
		 */
		if (findListByEntity(pimProductInfo).size() > 0) {
			
			/**
			 * 判断操作的数据是否来自ERP，若是则提示异常信息“不可操作来自ERP的产品信息”，若不是则进行删除操作
			 */
			if (!isOriginateFromErp(pimProductInfo)) {
				deleteByPrimaryKey(pimProductInfo);
			} else {
				throw new PimBusException("不可操作来自ERP的产品信息");
			}
		} else {
			throw new PimBusException("该信息不存在");
		}

	}

	/**
	 * @author ygy
	 * @date 2018年7月20日
	 * @param pageDto
	 * @return Page<PimProductInfo> Description: 所有产品基本信息分页查询列表
	 */
	public Page<PimProductInfo> findPageAll(Page<PimProductInfo> pageDto) {
		// TODO Auto-generated method stub

		return findPageByCategoryId(pageDto);
	}

	/**
	 * @author ygy
	 * @date 2018年7月20日
	 * @param pimProductInfo
	 * @return boolean Description:判断产品名称、编码是否重复
	 */
	public boolean isRepeat(PimProductInfo pimProductInfo) {

		if (getExistPimProductInfo(pimProductInfo) != null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @author ygy
	 * @date 2018年7月20日
	 * @param pimProductInfo void Description: 修改单条产品基本信息记录
	 */
	@Transactional
	public void updateSingle(PimProductInfo pimProductInfo) {
		// TODO Auto-generated method stub
		if (!isOriginateFromErp(pimProductInfo)) {
			if (!isRepeat(pimProductInfo)) {
				pimProductInfo.preUpdate();
				dao.updateByPrimaryKey(pimProductInfo);
			} else {
				throw new PimBusException("产品名称或编码已存在");
			}
		} else {
			throw new PimBusException("不可操作来自ERP的产品信息");
		}
	}

	/**
	 * @author ygy
	 * @date 2018年7月20日
	 * @param pimProductInfo
	 * @return boolean Description: 判断产品基本信息是否来自于ERP,来自ERP返回true,否则返回false
	 */
	public boolean isOriginateFromErp(PimProductInfo pimProductInfo) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		map.put("id", pimProductInfo.getId());
		map.put("productSource", "ERP");
		if (pimProductInfoDao.isOriginateFromErp(map) != null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @author ygy
	 * @date 2018年7月20日
	 * @param pageDto
	 * @return Page<PimProductInfo> Description:根据名称、编码、代号模糊查询
	 */
	public Page<PimProductInfo> findPageByKeywords(Page<PimProductInfo> pageDto) {
		// TODO Auto-generated method stub
		if (pageDto == null) {
			pageDto = new Page<PimProductInfo>();
		}
		if (pageDto.getQuery() == null) {
			pageDto.setQuery(new PimProductInfo());
		}
		PimProductInfo entity = pageDto.getQuery();
		entity.setPage(pageDto);
		String keywords = entity.getKeywords();
		List<PimProductInfo> ppiList = pimProductInfoDao.selectByKeywords("%" + keywords + "%");
		if (ppiList == null) {
			throw new PimBusException("未查询到相关信息");
		}
		pageDto.setList(ppiList);
		return pageDto;
	}

	/**
	 * @author ygy
	 * @date 2018年7月23日
	 * @param pimProductInfo
	 * @return PimProductInfo Description:根据传入的产品基本信息的名称和编码，返回已存在的产品基本信息对象
	 */
	public PimProductInfo getExistPimProductInfo(PimProductInfo pimProductInfo) {
		Map<String, String> map = new HashMap<>();
		map.put("productName", pimProductInfo.getProductName());
		map.put("productNumber", pimProductInfo.getProductNumber());
		PimProductInfo ppi = pimProductInfoDao.selectWithNameAndNum(map);
		return ppi;
	}
}