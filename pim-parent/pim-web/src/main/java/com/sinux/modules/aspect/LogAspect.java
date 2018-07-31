package com.sinux.modules.aspect;

import com.alibaba.fastjson.JSONObject;
import com.sinux.core.persistence.Page;
import com.sinux.core.utils.IdGen;
import com.sinux.modules.annotaion.OperLog;
import com.sinux.modules.annotaion.OperLogFiled;
import com.sinux.modules.log.dao.PimOperLogDao;
import com.sinux.modules.log.entity.PimOperLog;
import com.sinux.modules.sys.entity.User;
import com.sinux.modules.sys.utils.UserUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * @author lf
 * @Title: LogAspect
 * @Description: 日志切面
 * @date 2018/7/24 10:47
 */
@Component
@Aspect
public class LogAspect {

    @Autowired
    private PimOperLogDao pimOperLogDao;

    //定义切点
    @Pointcut("@annotation(com.sinux.modules.annotaion.OperLog)")
    public void methodPointCut() {
    }

    //切面
    @Around("methodPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        PimOperLog operLog = new PimOperLog();
        operLog.setId(IdGen.uuid());
        User user = UserUtils.getUser();
        //获取用户ip
        user.getLoginIp();
        operLog.setUserId(user.getId());
        operLog.setCreateDate(new Date());
        //获取使用注解的方法中注解的属性值
        MethodLogEntity methodLogEntity = getMethodLog(joinPoint);
        operLog.setOperDescription(methodLogEntity.getRemark()); //操作描述
        operLog.setOperType(methodLogEntity.getOperType()); //操作类型
        //获取方法中传入参数 只获取第一个参数（规定service层传输只能是一个对象）
        String operateContent = null;
        operateContent = translateFiled(joinPoint.getArgs()[0]);
        operLog.setOperContent(operateContent); //操作内容（数据）
        Object proceed = joinPoint.proceed();
        pimOperLogDao.insertOperLog(operLog); //保存操作日志
        return proceed;
    }

    private String translateFiled(Object o) {
        JSONObject jsonObject = new JSONObject();
        if(o!=null){
            if (o instanceof Page) {
                try {
                    Field query = o.getClass().getDeclaredField("query");
                    query.setAccessible(true);
                    String s = translateFiled(query.get(o));
                    jsonObject.put("查询数据", s);
                } catch (NoSuchFieldException e) {
                    return "";
                } catch (IllegalAccessException e) {
                    return "";
                }
            } else if (o instanceof List) {
                List list = (List) o;
                for (int i = 0; list != null && i < list.size(); ++i) {
                    String res = translateFiled(list.get(i));
                    jsonObject.put("数据:" + i, res);
                }
            } else {
                Field[] fields = o.getClass().getDeclaredFields();
                for (Field field : fields) {
                    try {
                        OperLogFiled logFiled = field.getAnnotation(OperLogFiled.class);
                        field.setAccessible(true);
                        if (logFiled != null) { //有注解 获取注解注释
                            String key = logFiled.value();
                            Object value = field.get(o);
                            jsonObject.put(key, value);
                        }
                    } catch (IllegalAccessException e) {
                        continue;
                    }
                }

            }
        }else {
            return "";
        }
        return jsonObject.toJSONString();
    }

    /**
     * 　　* @Description: 处理@OperLog注解  获取注解的内容
     * 　　* @author lf
     * 　　* @date 2018/7/24 11:43
     */
    private MethodLogEntity getMethodLog(ProceedingJoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        Class targetClass = Class.forName(targetName);
        Method[] method = targetClass.getMethods();
        String remark = "";
        String operType = "";
        for (Method m : method) {
            if (m.getName().equals(methodName)) {
                Class[] tmpCs = m.getParameterTypes();
                if (tmpCs.length == arguments.length) {
                    OperLog methodCache = m.getAnnotation(OperLog.class);
                    if (methodCache != null) {
                        remark = methodCache.remark();
                        operType = methodCache.operType().getName();
                    }
                    break;
                }
            }
        }
        return new MethodLogEntity(remark, operType);
    }

    /**
     * 　　* @Description: 注解实体类
     * 　　* @author lf
     * 　　* @date 2018/7/24 11:44
     */
    public class MethodLogEntity {
        private String remark;
        private String operType;

        public MethodLogEntity() {
        }

        public MethodLogEntity(String remark, String operType) {
            this.remark = remark;
            this.operType = operType;
        }

        public String getRemark() {
            return remark;
        }

        public String getOperType() {
            return operType;
        }
    }

}