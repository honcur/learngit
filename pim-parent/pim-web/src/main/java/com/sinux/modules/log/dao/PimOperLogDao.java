/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.log.dao;

import com.sinux.core.persistence.BaseCrudDao;
import com.sinux.core.persistence.annotation.MyBatisDao;
import com.sinux.modules.log.entity.PimOperLog;

/**
 * 操作日志DAO接口
 * @author lf
 * @version 2018-07-26
 */
@MyBatisDao
public interface PimOperLogDao extends BaseCrudDao<PimOperLog> {
    void insertOperLog(PimOperLog operLog);
}