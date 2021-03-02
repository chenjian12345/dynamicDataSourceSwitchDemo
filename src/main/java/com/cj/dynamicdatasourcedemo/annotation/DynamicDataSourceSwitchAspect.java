package com.cj.dynamicdatasourcedemo.annotation;

import com.cj.dynamicdatasourcedemo.config.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class DynamicDataSourceSwitchAspect {

    @Pointcut("@annotation(com.cj.dynamicdatasourcedemo.annotation.DynamicDataSourceSwitch)")
    public void addAdvice()
    {

    }

    @SuppressWarnings("unchecked")
    @Around("addAdvice()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint)
    {
        try
        {
            Object[] args = proceedingJoinPoint.getArgs();
            Signature signature = proceedingJoinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;

            // 获取到方法的所有参数名称的字符串数组
            String[] parameterNames = methodSignature.getParameterNames();
            // 参数名和方法名进行组装
            String customerCode = getCustomerCode(args, parameterNames);

            System.out.println(DynamicDataSourceContextHolder.getDataSourceType());
            if (customerCode != null && DynamicDataSourceContextHolder.containsDataSource(customerCode))
            {
                DynamicDataSourceContextHolder.setDataSourceType(customerCode);
            }
            System.out.println(DynamicDataSourceContextHolder.getDataSourceType());

            // 执行目标方法
            return proceedingJoinPoint.proceed();
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
        }
        return null;
    }

    private String getCustomerCode(Object[] args, String[] parameterNames) throws Exception
    {
        for (int i = 0, len = parameterNames.length; i < len; i++)
        {
            if (parameterNames[i].equals("customerCode") && args[i] != null && args[i].toString().length() > 0)
            {
                return (String) args[i];
            }
        }

        return null;
    }
}