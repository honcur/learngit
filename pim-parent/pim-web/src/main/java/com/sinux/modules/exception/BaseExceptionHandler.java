package com.sinux.modules.exception;

import com.sinux.core.dto.Result;
import com.sinux.modules.exception.entity.PimBusException;
import com.sinux.pim.common.entity.CommonContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lf
 * @Title: ExceptionHandler 异常处理
 * @Description: TODO
 * @date 2018/7/19 13:18
 */
@ControllerAdvice
public class BaseExceptionHandler {

    Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler
    @ResponseBody
    public Map<String,String> handlerAndReturnData(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        logger.error(ex.getMessage());
        Map<String,String> resMap = new HashMap<String,String>();

        if (ex instanceof PimBusException) {
            PimBusException pimBusException = (PimBusException) ex;
            resMap.put("code",pimBusException.getErrorCode()+"");
            resMap.put("message",pimBusException.getErrorMsg());
        }else{
            resMap.put("code",CommonContent.SYSTEM_ERROR_CODE+"");
            resMap.put("message",CommonContent.SYSTEM_ERROR);
        }
        return resMap;
    }


}
