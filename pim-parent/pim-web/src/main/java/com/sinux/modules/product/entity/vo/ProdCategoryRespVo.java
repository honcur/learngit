package com.sinux.modules.product.entity.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lf
 * @Title: 产品分类 响应vo
 * @Description: TODO
 * @date 2018/7/19 9:29
 */
public class ProdCategoryRespVo implements Serializable {

    private String id ;
    private String categoryName;
    private String categoryNumber;
    private String categorySource;
    private String description;
    private Long isShow;
    private Long sort;
    private String parentId;
    private List<ProdCategoryRespVo> childList;

    public ProdCategoryRespVo(){
        this.childList = new ArrayList<ProdCategoryRespVo>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<ProdCategoryRespVo> getChildList() {
        return childList;
    }

    public void setChildList(List<ProdCategoryRespVo> childList) {
        this.childList = childList;
    }
}
