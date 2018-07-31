/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.service;

import com.sinux.core.persistence.Page;
import com.sinux.core.service.BaseCrudService;
import com.sinux.modules.annotaion.OperLog;
import com.sinux.modules.annotaion.enums.OperType;
import com.sinux.modules.exception.entity.PimBusException;
import com.sinux.modules.product.dao.PimProductCategoryDao;
import com.sinux.modules.product.entity.PimProductCategory;
import com.sinux.modules.product.entity.vo.ProdCategoryRespVo;
import com.sinux.pim.common.entity.CommonContent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 产品分类Service
 *
 * @author lf
 * @version 2018-07-18
 */
@Service
public class PimProductCategoryService extends BaseCrudService<PimProductCategoryDao, PimProductCategory> {
    /**
     * 　　* @Description: 保存分类信息
     * 　　* @author lf
     * 　　* @date 2018/7/18 16:45
     *
     */
    @Transactional
    @OperLog(remark = "保存产品分类", operType = OperType.SAVE)
    public void saveProdCategory(PimProductCategory productCategory) {
        if (!isRepeat(productCategory)) {
            this.save(productCategory);
        } else {
            throw new PimBusException("分类编号或者名称重复");
        }
    }

    /**
     * 　　* @Description: 更新产品分类信息
     * 　　* @throws
     * 　　* @author lf
     * 　　* @date 2018/7/18 17:15
     *
     */
    @Transactional
    @OperLog(remark = "更新产品分类", operType = OperType.UPDATA)
    public void updateProdCategory(PimProductCategory productCategory) {
        if (isRepeat(productCategory)) {
            throw new PimBusException("分类编号或者名称重复");
        }
        if (CommonContent.PIM_PRO_CATEGORY_SOURCE_ERP.equals(productCategory.getCategorySource())) {
            //数据来源是ERP 则直接更新
            dao.updateByPrimaryKey(productCategory);
        } else { //数据来源是PIM 判断被更新数据是否来自PIM 是 则可以更新 否则不可以
            PimProductCategory res = dao.selectById(productCategory.getId());

            if (CommonContent.PIM_PRO_CATEGORY_SOURCE_PIM.equals(res.getCategorySource())) {
                dao.updateByPrimaryKey(productCategory);
            } else {
                throw new PimBusException("被更新数据来自ERP，不可以更新");
            }
        }
    }

    /**
     * 　　* @Description: 封装表结构所有数据（树状结构） 递归实现
     * 　　* @author lf
     * 　　* @date 2018/7/19 9:19
     *
     */
    public List<ProdCategoryRespVo> getAll() {
        //获取顶级分类
        List<ProdCategoryRespVo> topProdCategorys = dao.selectAllParents();
        //循环获取子分类
        if (topProdCategorys != null && topProdCategorys.size() > 0) {
            for (ProdCategoryRespVo item : topProdCategorys) {
                List<ProdCategoryRespVo> childs = getCategoryChilds(item.getId());
                item.setChildList(childs);
            }
        }
        return topProdCategorys;
    }

    private List<ProdCategoryRespVo> getCategoryChilds(String id) {
        List<ProdCategoryRespVo> prodCategoryRespVos = dao.selectByParentId(id);
        if (prodCategoryRespVos != null && prodCategoryRespVos.size() > 0) {
            for (ProdCategoryRespVo item : prodCategoryRespVos) {
                getCategoryChilds(item.getId());
            }
        }
        return prodCategoryRespVos;
    }

    /**
     * 　　* @Description:  通过分类编号和分类名称 查重
     * 　　* @author lf
     * 　　* @date 2018/7/18 16:47
     *
     */
    private boolean isRepeat(PimProductCategory productCategory) {
        if (dao.selectWithNameOrNum(productCategory) != null) {
            return true;
        }
        return false;
    }

    /**
     * 　　* @Description: 删除产品分类信息
     * 　　* @author lf
     * 　　* @date 2018/7/19 15:34
     *
     */
    @Transactional
    @OperLog(remark = "删除产品分类", operType = OperType.DELETE)
    public void deleteProCategory(List<PimProductCategory> prodCategorys) {
        if (prodCategorys != null) {
            for (PimProductCategory productCategory : prodCategorys) {
                PimProductCategory data = dao.selectById(productCategory.getId());
                if (data == null) {
                    throw new PimBusException("删除数据已经不存在，无需再次删除，可以尝试刷新页面");
                }
                //被删除分类 如果存在子分类则提示不能删除
                List<ProdCategoryRespVo> childs = dao.selectByParentId(productCategory.getId());
                if (childs != null && childs.size() > 0) {
                    throw new PimBusException("此分类下存在其他分类信息，不能删除！");
                }
                dao.deleteById(productCategory.getId());
            }
        }


    }

    @OperLog(remark = "查询分类产品", operType = OperType.SELECT)
    public Page<PimProductCategory> findPimCateByPage(Page<PimProductCategory> pageDto) throws IllegalAccessException, InstantiationException {
        return this.findPage(pageDto, PimProductCategory.class);
    }
}