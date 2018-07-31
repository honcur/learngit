/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.log.service;

import java.util.List;
import java.util.ArrayList;

import com.sinux.core.persistence.Page;
import com.sinux.modules.product.entity.PimProductCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections.CollectionUtils;
import com.sinux.core.persistence.BatchEntity;

import com.sinux.core.service.BaseCrudService;
import com.sinux.modules.log.entity.PimOperLog;
import com.sinux.modules.log.dao.PimOperLogDao;

/**
 * 操作日志Service
 * @author lf
 * @version 2018-07-26
 */
@Service
public class PimOperLogService extends BaseCrudService<PimOperLogDao, PimOperLog> {

	public Page<PimOperLog> findPimCateByPage(Page<PimOperLog> pageDto) throws IllegalAccessException, InstantiationException {
        Page<PimOperLog> pimOperLogPage = findPage(pageDto, PimOperLog.class);
        Page<PimOperLog> res = translate(pimOperLogPage);
        return  res;
	}

    private Page<PimOperLog> translate(Page<PimOperLog> pimOperLogPage) {
        //读取翻译配置文件
        //进行比对
        List<PimOperLog> list = pimOperLogPage.getList();
        if(list!=null){
            for (PimOperLog operLog : list){

            }
        }
        return null;
    }
}