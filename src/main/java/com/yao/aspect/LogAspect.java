package com.yao.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.yao.web.*.*(..))")
    public  void  log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        String url=request.getRequestURI().toString();
        String ip=request.getRemoteAddr();
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object args[]=joinPoint.getArgs();
        RequestLog requestLog=new RequestLog(url,ip,classMethod,args);

        logger.info("Request:{}",requestLog);
    }
    @After("log()")
    public void doAfter(){
        logger.info("-----doafter-----");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("returning result: ",result);

    }
    public  class  RequestLog{
        private String ip;
        private String url;
        private  String classMethod;
        private  Object[] args;

        public RequestLog(String ip, String url, String classMethod, Object[] args) {
            this.ip = ip;
            this.url = url;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "ip='" + ip + '\'' +
                    ", url='" + url + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}