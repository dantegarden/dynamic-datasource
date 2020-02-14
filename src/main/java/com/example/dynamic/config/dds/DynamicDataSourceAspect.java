package com.example.dynamic.config.dds;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: lij
 * @create: 2020-02-14 22:04
 */
@Aspect
@Order(1) // 该切面应当先于 @Transactional 执行
@Component
@Slf4j
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(com.example.dynamic.config.dds.DataSource)")
    public void dataSourcePointCut() {
    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSource ds = method.getAnnotation(DataSource.class);
        if (ds == null) {
            DynamicDataSourceContextHolder.setDataSourceKey("master");
            log.debug("set datasource is master");
        } else {
            DynamicDataSourceContextHolder.setDataSourceKey(ds.value());
            log.debug("set datasource is " + ds.value());
        }

        try {
            return point.proceed(); //放行
        } finally {
            DynamicDataSourceContextHolder.clearDataSourceKey();
            log.debug("clean datasource");
        }
    }
}
