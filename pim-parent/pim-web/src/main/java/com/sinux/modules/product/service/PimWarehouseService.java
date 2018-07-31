/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections.CollectionUtils;
import com.sinux.core.persistence.BatchEntity;

import com.sinux.core.service.BaseCrudService;
import com.sinux.modules.product.entity.PimWarehouse;
import com.sinux.modules.sys.entity.User;
import com.sinux.modules.sys.service.UserService;
import com.sinux.modules.product.dao.PimWarehouseDao;

/**
 * 仓库Service
 * @author xiaolm
 * @version 2018-07-24
 */
@Service
public class PimWarehouseService extends BaseCrudService<PimWarehouseDao, PimWarehouse> {
	
	@Autowired
	UserService userService;

	/**
	 * 批量保存，添加、修改通用接口
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveBatch(List<PimWarehouse> list){
		List<PimWarehouse> insertList = new ArrayList<>();
		List<PimWarehouse> updtaeList = new ArrayList<>();
		for(PimWarehouse tmp : list){
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
	 * 获取仓库的管理员
	 * 
	 * @param userId 用户ID
	 */
	public User getKeeperByUserId(String userId) {
		
		return userService.getUser(userId);
	}
}