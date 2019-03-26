package com.hd;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect //声明这是一个切面。必须的！
public class AspectConfig {
	//通知和切入点的混合写法；
    //第一个 * 号表示任意返回类型，第二个 * 号表示Person的所有方法
    @Before("execution(* com.san.spring.aop.Person.*(..))")
    public void showTime1(){
        System.out.println("CurrentTime = " + System.currentTimeMillis());
    }

    @After("execution(* com.san.spring.aop.Person.*(..))")
    public void showTime2(){
        System.out.println("CurrentTime = " + System.currentTimeMillis());
    }
}
