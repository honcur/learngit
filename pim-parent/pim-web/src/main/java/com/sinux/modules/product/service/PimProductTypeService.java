/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.service;

import java.util.List;
import java.util.ArrayList;

import com.sinux.core.utils.DateUtils;
import com.sinux.core.utils.excel.ExportExcel;
import com.sinux.modules.exception.entity.PimBusException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections.CollectionUtils;
import com.sinux.core.persistence.BatchEntity;

import com.sinux.core.service.BaseCrudService;
import com.sinux.modules.product.entity.PimProductType;
import com.sinux.modules.product.dao.PimProductTypeDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 产品型号Service
 * @author gebp
 * @version 2018-07-24
 */
@Service
public class PimProductTypeService extends BaseCrudService<PimProductTypeDao, PimProductType> {

	/**
	 * 根据型号id查询产品型号信息
	 * @param id 型号id
	 * @return 返回值
	 */
	public List<PimProductType> findListById(String id){
		if(StringUtils.isEmpty(id)) {
			throw new PimBusException("产品型号id为空");
		}
		PimProductType entity = new PimProductType();
		entity.setId(id);
		return findListByEntity(entity);
	}

	/**
	 * 新增一个产品型号
	 * @param pimProductType 产品型号信息
	 */
	@Transactional
	public void addProductType(PimProductType pimProductType){
		if (pimProductType !=null){
			List<PimProductType> list = findListByEntity(pimProductType);
			if (list!= null && list.size()>0){
				throw new PimBusException("该产品型号已经存在");
			}
			save(pimProductType);
		}else {
			throw new PimBusException("新增的产品型号为空");
		}
	}

	/**
	 * 根据型号id删除一条产品型号记录
	 * @param id 型号id
	 */
	@Transactional
	public void deleteById(String id){
		if (StringUtils.isEmpty(id)){
			throw new PimBusException("产品型号为空");
		}
		dao.deleteById(id);
	}

	/**
	 * 更新产品型号信息
	 * @param pimProductType 产品型号
	 */
	@Transactional
	public void updateProductType(PimProductType pimProductType){
		if (dao.selectByTypeName(pimProductType)!=null){
			throw new PimBusException("产品型号名称重复");
		}
		dao.updateByPrimaryKey(pimProductType);
	}

	/**
	 * 导出产品型号记录
	 * @param request 请求信息
	 * @param response 响应信息
	 * @throws Exception
	 */
	public void exportFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String fileName = "产品型号"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		PimProductType pimProductType = new PimProductType();
		List<PimProductType> resultList = findListByEntity(pimProductType);
		if (resultList==null || resultList.size()<=0){
			throw new PimBusException("产品型号信息不存在，无法导出");
		}
		new ExportExcel("产品型号", PimProductType.class).setDataList(resultList).write(response, fileName).dispose();
	}

}