/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.auth.dao;

import com.sinux.core.persistence.BaseCrudDao;
import com.sinux.core.persistence.annotation.MyBatisDao;
import com.sinux.modules.auth.entity.PimDataAuth;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 数据权限DAO接口
 * @author lf
 * @version 2018-07-30
 */
@MyBatisDao
public interface PimDataAuthDao extends BaseCrudDao<PimDataAuth> {

    /**
    　　* @Description: 保存数据权限
    　　* @author lf
    　　* @date 2018/7/30 10:40
    　　*/
    void insertAuthDatas(List<PimDataAuth> pimDataAuths);

    /**
    　　* @Description: 删除数据权限
    　　* @author lf
    　　* @date 2018/7/30 11:35
    　　*/
    void deleteAuthDatas(@Param("userIds") Set<String> userIds);
}