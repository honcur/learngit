package com.sinux.pim.common.enums;

/**
 * @author lf
 * @Title: DateFormatPattern
 * @Description: 日期格式匹配枚举类
 * @date 2018/7/25 9:23
 */
public enum  DateFormatPattern {

    C_YEAR_MONTH_DAY_H_M_S("yyyy年MM月dd日 HH:mm:ss"),
    YEAR_MONTH_DAY_H_M_S("yyyy-MM-dd HH:mm:ss"),
    C_YEAR_MONTH("yyyy年MM月dd日"),
    YEAR_MONTH("yyyy-MM-dd");


    private final String pattern;

    DateFormatPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern(){
        return this.pattern;
    }
}
