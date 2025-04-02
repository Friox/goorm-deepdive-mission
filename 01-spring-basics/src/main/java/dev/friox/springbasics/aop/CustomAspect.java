package dev.friox.springbasics.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // Aspect임을 명시하고 활성화합니다.
@Component
public class CustomAspect {

    // M: AOP를 사용하여 Aspect 구현하기 - 구현부

    // @Pointcut 어노테이션으로 JoinPoint를 결정합니다. 실행시기를 제어합니다.
    // AspectService Class의 모든 메서드를 대상으로 합니다.
    @Pointcut("execution(* dev.friox.springbasics.aop.AspectService.*(..))")
    public void pointCut() {}

    // 특정 JoinPoint에 대해 메서드 호출 전후에 수행할 수 있는 Advice를 작성합니다.
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("작업 실행 전처리");
        Object result = joinPoint.proceed();
        System.out.println("작업 실행 후처리");
        return result;
    }

}
