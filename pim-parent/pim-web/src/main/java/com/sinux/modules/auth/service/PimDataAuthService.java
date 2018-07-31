/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.auth.service;

import java.util.*;

import com.sinux.core.persistence.Page;
import com.sinux.core.utils.IdGen;
import com.sinux.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections.CollectionUtils;
import com.sinux.core.persistence.BatchEntity;

import com.sinux.core.service.BaseCrudService;
import com.sinux.modules.auth.entity.PimDataAuth;
import com.sinux.modules.auth.dao.PimDataAuthDao;

import javax.mail.search.SearchTerm;

/**
 * 数据权限Service
 *
 * @author lf
 * @version 2018-07-30
 */
@Service
public class PimDataAuthService extends BaseCrudService<PimDataAuthDao, PimDataAuth> {

    /**
     * 　　* @Description: 保存数据权限
     * 　　* @author lf
     * 　　* @date 2018/7/30 10:32
     *
     */
    @Transactional
    public void saveDataAuth(List<PimDataAuth> pimDataAuths) {
        Set<String> userIds = new HashSet<>();
        if (pimDataAuths != null) {
            for (PimDataAuth pimDataAuth : pimDataAuths) {
                pimDataAuth.setId(IdGen.uuid());
                pimDataAuth.setCreateDate(new Date());
                userIds.add(pimDataAuth.getUserId());
            }
        }
        //先删除后保存
        dao.deleteAuthDatas(userIds);
        dao.insertAuthDatas(pimDataAuths);
    }


    /**
     * 　　* @Description: 分页获取数据权限
     * 　　* @author lf
     * 　　* @date 2018/7/30 13:17
     *
     */
    public Page<PimDataAuth> findAuthDataByPage(Page<PimDataAuth> pageDto) throws IllegalAccessException, InstantiationException {
        return this.findPage(pageDto, PimDataAuth.class);
    }

    /**
    　　* @Description: 批量删除数据权限 通过UserId
    　　* @author lf
    　　* @date 2018/7/30 13:27
    　　*/
    public void deleteDataAuth(List<PimDataAuth> pimDataAuths) {
        Set<String> userIds = new HashSet<>();
        if(pimDataAuths !=null){
            for (PimDataAuth pimDataAuth : pimDataAuths){
                userIds.add(pimDataAuth.getUserId());
            }
        }
        dao.deleteAuthDatas(userIds);
    }
}