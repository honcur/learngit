package com.sinux.pim.common.util;

import com.sinux.pim.common.enums.DateFormatPattern;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lf
 * @Title: DateUtil
 * @Description: 日期工具类
 * @date 2018/7/25 9:16
 */
public class DateUtil {

    /**
     * 　　* @Description: 格式化当前日期
     * 　　* @author lf
     * 　　* @date 2018/7/25 9:19
     */
    public static String dateFormat(Date date, DateFormatPattern formatPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern.getPattern());
        return sdf.format(date);
    }

    /**
     * 　　* @Description: 获取当前时间
     * 　　* @param 日期格式
     * 　　* @author lf
     * 　　* @date 2018/7/25 11:01
     *
     */
    public static String currentDate(DateFormatPattern formatPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern.getPattern());
        return sdf.format(new Date());
    }
    
    /**
  　　* @Description: 格式化日期
  　　* @author xiaolm
  　　* @date 2018/7/25 9:19
  　　*/
   public static String dateFormat(Date date,String formatPattern){
      SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
      return  sdf.format(date);
   }

    /**
     * 　　* @Description: 当前时间
     * 　　* @author lf
     * 　　* @date 2018/7/25 10:59
     */
    public static String currentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormatPattern.YEAR_MONTH_DAY_H_M_S.getPattern());
        return sdf.format(new Date());
    }

}
