/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sinux.core.persistence.BatchEntity;
import com.sinux.core.service.BaseCrudService;
import com.sinux.modules.annotaion.OperLog;
import com.sinux.modules.annotaion.enums.OperType;
import com.sinux.modules.product.dao.PimInWarehouseOrderDao;
import com.sinux.modules.product.entity.PimInProductEntity;
import com.sinux.modules.product.entity.PimInWarehouseOrder;
import com.sinux.modules.product.entity.PimOrderNumberGenerator;
import com.sinux.modules.product.entity.ext.PimInWarehouseOrderExt;
import com.sinux.modules.product.entity.ext.PimProductEntityExt;
import com.sinux.modules.product.entity.ext.PimSoftwareInstallRecordExt;

/**
 * 产品入库单Service
 * @author xiaolm
 * @version 2018-07-23
 */
@Service
public class PimInWarehouseOrderService extends BaseCrudService<PimInWarehouseOrderDao, PimInWarehouseOrder> {
	
	@Autowired
	PimProductEntityService productEntityService;
	
	@Autowired
	PimInProductEntityService pimInProductEntityService;
	
	@Autowired
	PimOrderNumberGeneratorService numberGeneratorService;
	
	@Autowired
	private PimSoftwareInstallRecordService pimSoftwareInstallRecordService;
	/**
	 * 批量保存，添加、修改通用接口
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveBatch(List<PimInWarehouseOrder> list){
		List<PimInWarehouseOrder> insertList = new ArrayList<>();
		List<PimInWarehouseOrder> updtaeList = new ArrayList<>();
		for(PimInWarehouseOrder tmp : list){
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
	 * 添加入库单
	 * @param inWarehouseOrderExt
	 */
	@OperLog(remark="添加入库单",operType=OperType.SAVE)
	@Transactional
	public void addInWarehouseOrder(PimInWarehouseOrderExt inWarehouseOrderExt) {
		checkAddInWarehouseOrder(inWarehouseOrderExt);
		save(inWarehouseOrderExt);
		List<PimProductEntityExt> productEntitys = inWarehouseOrderExt.getProductEntitys();
		for (PimProductEntityExt pimProductEntity : productEntitys) {
			if(StringUtils.isEmpty(pimProductEntity.getId())) {//如果ID为空,先保存产品实体
				productEntityService.save(pimProductEntity);
			}
			//保存入库单与产品实体关系
			PimInProductEntity inProductEntity = new PimInProductEntity();
			inProductEntity.setOrderId(inWarehouseOrderExt.getId());
			inProductEntity.setProductEntityId(pimProductEntity.getId());
			inProductEntity.setOwnershipId(pimProductEntity.getOwnershipId());
			pimInProductEntityService.save(inProductEntity);
			//处理软件安装的软件记录
			List<PimSoftwareInstallRecordExt> installRecords = pimProductEntity.getSoftwareInstallRecords();
			if(installRecords!=null&&installRecords.size()>0) {
				saveSoftwareInstallRecord(installRecords,pimProductEntity);
			}
		}
		
	}
	
	
	
	/**
	 * 保存软件安装记录
	 * 
	 * @param installRecords 软件安装记录
	 */
	private void saveSoftwareInstallRecord(List<PimSoftwareInstallRecordExt> installRecords,PimProductEntityExt productEntity) {
		// TODO Auto-generated method stub
		for (PimSoftwareInstallRecordExt pimSoftwareInstallRecordExt : installRecords) {
			pimSoftwareInstallRecordExt.setProductEntityId(productEntity.getId());
			try {
				pimSoftwareInstallRecordService.save(pimSoftwareInstallRecordExt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 添加入库单验证
	 * @param inWarehouseOrderExt
	 */
	private void checkAddInWarehouseOrder(PimInWarehouseOrderExt inWarehouseOrderExt) {
		
		
		
	}
	
	/**
	 * 生成订单号
	 * @return
	 */
	public String generateOrderNumber() {
		return numberGeneratorService.generateByType(PimOrderNumberGenerator.IN_ORDER_TYPE);
	}
	

}