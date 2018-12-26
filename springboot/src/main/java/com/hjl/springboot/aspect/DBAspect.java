package com.hjl.springboot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: HJL
 * @create: 2018-12-26 17:39
 */
@Aspect
@Component("dbroutaspect")
public class DBAspect {

    @Pointcut("@annotation(com.hjl.springboot.aspect.DBRoute)")
    public void pointCut(){
    }


    @Around("pointCut()")
    public Object doArround(ProceedingJoinPoint pjp) throws Throwable{
        Object result=null;
        try{
            deal(pjp);
            result= pjp.proceed();
        }catch (Exception e){
           e.printStackTrace();
        }finally {
            MultiDataSource.remove();
        }
        return result;
    }

    private void deal(ProceedingJoinPoint pjp){
        Signature signature = pjp.getSignature();
        Method method = ((MethodSignature) signature).getMethod();
        DBRoute dbRoute = method.getAnnotation(DBRoute.class);
        if(dbRoute!=null){
          MultiDataSource.setDataSource(dbRoute.name());
        }
    }

}
