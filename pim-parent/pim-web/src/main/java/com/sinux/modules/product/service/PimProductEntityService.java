/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sinux.core.persistence.BatchEntity;
import com.sinux.core.service.BaseCrudService;
import com.sinux.modules.exception.entity.PimBusException;
import com.sinux.modules.product.dao.PimProductEntityDao;
import com.sinux.modules.product.entity.PimProductEntity;

/**
 * 产品实体Service
 * @author xiaolm
 * @version 2018-07-24
 */
@Service
public class PimProductEntityService extends BaseCrudService<PimProductEntityDao, PimProductEntity> {

	/**
	 * 批量保存，添加、修改通用接口
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveBatch(List<PimProductEntity> list){
		List<PimProductEntity> insertList = new ArrayList<>();
		List<PimProductEntity> updtaeList = new ArrayList<>();
		for(PimProductEntity tmp : list){
			int insertStatus = 0;
			if(tmp.getId() == null){
				insertStatus = 1;
			}
			if(insertStatus == 1){
                tmp.preInsert();
				insertList.add(tmp);
			}else{
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
	 * 
	 */
	@Override
	public Integer save(PimProductEntity entity) {
		// TODO Auto-generated method stub
		checkSaveOrUpdate(entity);
		return super.save(entity);
	}
	
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public Integer update(PimProductEntity entity) {
		// TODO Auto-generated method stub
		checkSaveOrUpdate(entity);
		return super.save(entity);
	}
	/**
	 * 保存验证数据
	 * @param entity 将要保存的产品实体
	 */
	private void checkSaveOrUpdate(PimProductEntity entity) {
		// TODO Auto-generated method stub
		String entityNumber = entity.getEntityNumber();
		Long count = dao.selectByEntityNumber(entityNumber, entity.getId());
		if(count>0) {
			throw new PimBusException("产品编号已经存在。");
		}
		
		
	}
}