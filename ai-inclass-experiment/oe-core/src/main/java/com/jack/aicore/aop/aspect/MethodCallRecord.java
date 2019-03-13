package com.jack.aicore.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author wangwj
 * @data 2018/12/19
 */
@Aspect
@Component
public class MethodCallRecord {
    //已用interceptor代替
//    Logger logger = LoggerFactory.getLogger(MethodCallRecord.class);
//
//    @Pointcut(value = "@annotation(com.jack.aicore.aop.anno.Record)")
//    public void methodCallRecord() {
//    }
//
//    @Around("methodCallRecord()&&@annotation(anno))")
//    public Object around(ProceedingJoinPoint joinPoint, Record anno) {
//        String url = anno.requestUrl();
//        Class clazz = joinPoint.getTarget().getClass();
//        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
//        String params = Stream.of(joinPoint.getArgs())
//                .map(arg -> arg.toString())
//                .reduce("", (a, b) -> a + b);
//        logger.info("\nparams:"  +params+ "\nclassName：" + clazz + "\nmethod:" + method + "\n请求地址：" + url);
//        try {
//            return joinPoint.proceed();
//        } catch (Throwable e) {
//            return e.getMessage();
//        }
//    }
//
//    @AfterReturning(value = "methodCallRecord()&&@annotation(anno))", returning = "result")
//    public Object afterReturning(JoinPoint point, Record anno, Object result) {
//        logger.info("\n执行结果：" + result);
//        return result;
//    }
}
