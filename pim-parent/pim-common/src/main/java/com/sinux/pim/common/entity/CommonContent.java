package com.sinux.pim.common.entity;

import java.io.Serializable;

/**
 * @author lf
 * @Title: CommonContent
 * @Description: 公用静态常量类
 * @date 2018/7/17 14:23
 */
public class CommonContent implements Serializable {

    //产品分类数据来源 pim
    public static final String PIM_PRO_CATEGORY_SOURCE_PIM = "PIM";
    //产品分类数据来源 erp
    public static final String PIM_PRO_CATEGORY_SOURCE_ERP = "ERP";

    public static final String UPDATE_SUCCESS = "数据更新成功";
    public static final String UPDATE_FAILED = "数据更新失败";
    public static final String DELETE_SUCCESS = "数据删除成功";
    public static final String DELETE_FAILED = "数据删除失败";
    //默认错误信息
    public static final String SYSTEM_ERROR = "系统异常";
    //默认错误代码
    public static final int SYSTEM_ERROR_CODE = -1;
    
    //boolean变量为true
    public static final Integer BOOLEAN_TRUE=1;
    //boolean变量为false
    public static final Integer BOOLEAN_FLASE=0;


}
