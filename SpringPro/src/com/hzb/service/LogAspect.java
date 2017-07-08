package com.hzb.service;
import org.springframework.stereotype.Service;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;

@Service
@Aspect
// 使用 @Aspect 标记
public class LogAspect {
    //用来记录次数
    private int count = 0;
    //定义切点，表示UserService中所有的方法
    @Pointcut("execution(* com.hzb.service.UserService.*(..))")
    public void init(){
    }
    //定义增强，并应用在切点 init() 处，Before表示在方法执行前先执行下面的方法
    @Before("init()")
    public void beforeAdvice(JoinPoint joinPaint){
        System.out.println("总共执行的次数："+(++count));
        //还可以获取其他的信息
        //获取调用的目标类
        Object obj = joinPaint.getTarget();
        if(obj instanceof UserService){
            UserService   userService =  (UserService)obj;
            System.out.println("调用的目标类:"+userService.toString());
        }
        //获取调用的方法名
        System.out.println("调用的方法名:"+joinPaint.getSignature().getName());
        //获取传入的参数
        Object[] args=joinPaint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("传入的参数"+i+";"+args[i]); //输出传入的参数
        }
    }
}
