/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections.CollectionUtils;
import com.sinux.core.persistence.BatchEntity;

import com.sinux.core.service.BaseCrudService;
import com.sinux.modules.product.entity.PimSoftware;
import com.sinux.modules.product.dao.PimSoftwareDao;

/**
 * 软件Service
 * @author xiaolm
 * @version 2018-07-24
 */
@Service
public class PimSoftwareService extends BaseCrudService<PimSoftwareDao, PimSoftware> {

	/**
	 * 批量保存，添加、修改通用接口
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveBatch(List<PimSoftware> list){
		List<PimSoftware> insertList = new ArrayList<>();
		List<PimSoftware> updtaeList = new ArrayList<>();
		for(PimSoftware tmp : list){
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
}