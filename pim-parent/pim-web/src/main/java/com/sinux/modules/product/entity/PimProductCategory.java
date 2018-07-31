/**
 * Copyright &copy; 2015-2020 <a href="http://www.sinux.com.cn/">JFusion</a> All rights reserved.
 */
package com.sinux.modules.product.entity;

import com.sinux.core.persistence.DataEntity;
import com.sinux.modules.annotaion.OperLog;
import com.sinux.modules.annotaion.OperLogFiled;

import java.util.List;

/**
 * 产品分类Entity
 *
 * @author lf
 * @version 2018-07-18
 */
public class PimProductCategory extends DataEntity<PimProductCategory> {

    private static final long serialVersionUID = 1L;

    //搜索字段
    @OperLogFiled(value="查询条件")
    private String search;

    private String parentId;

    /**
     * category_name
     */
    @OperLogFiled(value = "产品分类名称")
    private String categoryName;
    /**
     * category_number
     */
    @OperLogFiled(value = "分类产品编号")
    private String categoryNumber;
    /**
     * category_source
     */
    @OperLogFiled(value = "分类产品数据来源")
    private String categorySource;
    /**
     * description
     */
    private String description;
    /**
     * is_show
     */
    private Long isShow;
    /**
     * sort
     */
    private Long sort;
    /**
     * parent_id
     */
    private PimProductCategory parent;
    /**
     * 对象本身列
     */
    private List<PimProductCategory> pimProductCategoryList;

    public PimProductCategory() {
        super();
    }

    public PimProductCategory(String id) {
        super(id);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(String categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getCategorySource() {
        return categorySource;
    }

    public void setCategorySource(String categorySource) {
        this.categorySource = categorySource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIsShow() {
        return isShow;
    }

    public void setIsShow(Long isShow) {
        this.isShow = isShow;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public PimProductCategory getParent() {
        return parent;
    }

    public void setParent(PimProductCategory parent) {
        this.parent = parent;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<PimProductCategory> getPimProductCategoryList() {
        return pimProductCategoryList;
    }

    public void setPimProductCategoryList(List<PimProductCategory> pimProductCategoryList) {
        this.pimProductCategoryList = pimProductCategoryList;
    }
}