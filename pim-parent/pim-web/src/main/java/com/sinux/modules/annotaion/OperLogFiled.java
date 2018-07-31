package com.sinux.modules.annotaion;

import java.lang.annotation.*;

/**
 * @author lf
 * @Title: OperLogFiled
 * @Description: TODO
 * @date 2018/7/26 15:06
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperLogFiled {
    String value() default "";
}
