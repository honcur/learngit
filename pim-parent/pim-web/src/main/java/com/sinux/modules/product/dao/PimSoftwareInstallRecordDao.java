/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.dao;

import com.sinux.core.persistence.BaseCrudDao;
import com.sinux.core.persistence.annotation.MyBatisDao;
import com.sinux.modules.product.entity.PimSoftwareInstallRecord;

/**
 * 软件安装记录DAO接口
 * @author xiaolm
 * @version 2018-07-30
 */
@MyBatisDao
public interface PimSoftwareInstallRecordDao extends BaseCrudDao<PimSoftwareInstallRecord> {

}