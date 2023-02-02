package com.whl.zuhaowan.aop.aspect;

import com.alibaba.fastjson.JSON;
import com.whl.zuhaowan.aop.annotation.MyLog;
import com.whl.zuhaowan.contants.Constant;
import com.whl.zuhaowan.entity.SysLog;
import com.whl.zuhaowan.mapper.SysLogMapper;
import com.whl.zuhaowan.utils.HttpContextUtils;
import com.whl.zuhaowan.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: SysLogAspect
 * @Author: liuxl
 * @UpdateUser: liuxl
 * @Version: 0.0.1
 */

@Aspect
@Component
@Slf4j
public class SysLogAspect {
    //环绕增强
    @Autowired
    private SysLogMapper sysLogMapper;
/**
     * 配置织入点(以@MyLog注解为标志)
     * 只要出现 @MyLog注解都会进入
     */

    @Pointcut("@annotation(com.whl.zuhaowan.aop.annotation.MyLog)")
    public void logPointCut(){
        log.debug("logPointCut");
    }

/**
     * 环绕增强
     * @param point
     * @return
     * @throws Throwable
     */

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        MDC.put("TRACE_ID", UUID.randomUUID().toString());
        //执行方法
        Object result = point.proceed();

        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        try {
//            TODO 暂时不写日志
            saveSysLog(point, time);
        } catch (Exception e) {
            log.error("e={}",e);
        }
//        正常请求到这里结束清除请求链路信息
        MDC.remove("TRACE_ID");
        return result;
    }
/**
     * 把日志保存
     * @Author:      liuxl
     * @UpdateUser:
     * @Version:     0.0.1
     * @param joinPoint
     * @param time
     * @return       void
     * @throws
     */

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        MyLog myLog = method.getAnnotation(MyLog.class);
        if(myLog != null){
            //注解上的描述
            sysLog.setOperation(myLog.title()+"-"+myLog.action());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        //打印该方法耗时时间
        log.info("请求{}.{}耗时{}毫秒",className,methodName,time);
        Object[] args = joinPoint.getArgs();
        String params=null;
        try {
            //请求的参数
            if(args.length!=0){
                params= JSON.toJSONString(args);
            }
        } catch (Exception e) {
            params = Arrays.toString(args);
        }
        sysLog.setParams(params);
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        log.info("Ip{}，接口地址{}，请求方式{}，入参：{}",sysLog.getIp(),request.getRequestURL(),request.getMethod(),sysLog.getParams());
        //用户名

        String token = request.getHeader(Constant.ACCESS_TOKEN);
        if(null == token && null !=request.getParameterMap() && null != request.getParameterMap().get(Constant.ACCESS_TOKEN)){
//            尝试从url取一下token
            token = request.getParameterMap().get(Constant.ACCESS_TOKEN)[0];
        }

//        String userId= JwtTokenUtil.getUserId(token);
//        String username= JwtTokenUtil.getUserName(token);
//        sysLog.setUsername(username);
//        sysLog.setUserId(userId);

        sysLog.setTime((int) time);
        sysLog.setId(UUID.randomUUID().toString());
        sysLog.setCreateTime(new Date());
        log.info(sysLog.toString());
        sysLogMapper.insertSelective(sysLog);

    }
}
