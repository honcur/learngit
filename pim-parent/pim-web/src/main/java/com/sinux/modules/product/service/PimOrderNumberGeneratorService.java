/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sinux.core.persistence.BatchEntity;
import com.sinux.core.service.BaseCrudService;
import com.sinux.modules.exception.entity.PimBusException;
import com.sinux.modules.product.dao.PimOrderNumberGeneratorDao;
import com.sinux.modules.product.entity.PimOrderNumberGenerator;
import com.sinux.pim.common.util.DateUtil;

/**
 * 订单号生成Service
 * @author xiaolm
 * @version 2018-07-25
 */
@Service
public class PimOrderNumberGeneratorService extends BaseCrudService<PimOrderNumberGeneratorDao, PimOrderNumberGenerator> {
	/**
	 * 订单号数字最小位数。
	 */
	public static final int MINIMUM_ORDER_NUMBER_DIGITS = 6;
	/**
	 * 批量保存，添加、修改通用接口
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveBatch(List<PimOrderNumberGenerator> list){
		List<PimOrderNumberGenerator> insertList = new ArrayList<>();
		List<PimOrderNumberGenerator> updtaeList = new ArrayList<>();
		for(PimOrderNumberGenerator tmp : list){
			int insertStatus = 0;
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
	 * 
	 * @param type  入库单:PimOrderNumberGenerator.IN_ORDER_TYPE 出库单:PimOrderNumberGenerator.OUT_ORDER_TYPE
	 * @return
	 */
	public synchronized String  generateByType(Integer type) {
		Date currentDate = new Date();
		String oderKey = DateUtil.dateFormat(currentDate, "yyyyMMdd");
		PimOrderNumberGenerator generator = generateByTypeAndKey(type, oderKey);
		if(generator==null) {
			throw new PimBusException();
		}
		
		Integer number = generator.getOrderNumber();
	
		
		if(PimOrderNumberGenerator.IN_ORDER_TYPE.equals(type)) {//入库单
			String orderNumer="IN"+ oderKey + formatterNumber(number);
			return orderNumer;
		}else if(PimOrderNumberGenerator.OUT_ORDER_TYPE.equals(type)){//出库单
			String orderNumer="OUT"+ oderKey + formatterNumber(number);; 
			return orderNumer;
		}
		
		return "";
	}
	
	
	
	/**
	 * 将数字格式化。如果数字位数小于PimOrderNumberGeneratorService.MINIMUM_NUMBER_DIGITS格式化为00005的的样式，如果大于它不做特殊处理。
	 * 
	 * @param number
	 * @return
	 */
	private String formatterNumber(Integer number) {
		int minimumIntegerDigits = MINIMUM_ORDER_NUMBER_DIGITS;
		if(number.toString().length()>MINIMUM_ORDER_NUMBER_DIGITS) {
			minimumIntegerDigits = number.toString().length();
		}
		NumberFormat formatter = NumberFormat.getNumberInstance();   
		formatter.setMinimumIntegerDigits(minimumIntegerDigits);   
		formatter.setGroupingUsed(false);  
		return formatter.format(number);
	}
	
	
	/**
	 * 通过类型和KEY查询
	 * {@link}
	 * @param orderType 订单生成器类型  入库单:PimOrderNumberGenerator.IN_ORDER_TYPE 出库单:PimOrderNumberGenerator.OUT_ORDER_TYPE
	 * @param orderKey  订单KEY 
	 * @return
	 */
	private PimOrderNumberGenerator generateByTypeAndKey(Integer orderType,String orderKey) {
		PimOrderNumberGenerator entity = new PimOrderNumberGenerator();
		entity.setOrderType(orderType);
		entity.setOrderKey(orderKey);
		
		List<PimOrderNumberGenerator> generators = findListByEntity(entity);
		if(generators==null||generators.size()==0) {//如果没有找到对应数据,则新建
			PimOrderNumberGenerator numberGenerator  = new PimOrderNumberGenerator();
			 numberGenerator.setOrderKey(orderKey);
			 numberGenerator.setOrderType(orderType);
			 numberGenerator.setOrderNumber(1);
			 save(numberGenerator);
			 return numberGenerator;
			 
		}else {//如果存在数据，将对应number值加1并持久化数据库。
			PimOrderNumberGenerator numberGenerator = generators.get(0);
			Integer number = numberGenerator.getOrderNumber();
			number++;
			numberGenerator.setOrderNumber(number);
			save(numberGenerator);
			return numberGenerator;
		}
		
	}
}