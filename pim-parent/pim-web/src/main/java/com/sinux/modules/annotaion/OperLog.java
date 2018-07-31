package com.sinux.modules.annotaion;

import com.sinux.modules.annotaion.enums.OperType;

import java.lang.annotation.*;


/**
 * @author lf
 * @Title: MethodLog
 * @Description: 自定义日志注解
 * @date 2018/7/24 10:49
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLog {
    String remark() default ""; //操作描述
    OperType operType(); //操作类型
}
