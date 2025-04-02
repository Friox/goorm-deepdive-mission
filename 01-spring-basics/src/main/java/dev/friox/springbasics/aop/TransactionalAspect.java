package dev.friox.springbasics.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*
원래 AOP 기능을 사용하기 위해
@EnableAspectJAutoProxy(proxyTargetClass = true)를 애플리케이션 루트에 명시해야한다.
하지만 Spring Boot 2.0 이후 버전부터 기본값으로 설정된다고 하여 생략이 가능하다고 한다.
[삽질기록]
@Component을 통해 Bean으로 등록하지 않아 AOP가 적용되지 않고있었음.
*/

// @Transactional Aspect를 감싸는 커스텀 Aspect
@Aspect
@Component
// @Order(1): 기본적으로 @Transactional Aspect의 Order값은 INT_MAX다. 따라서 설정 불필요
public class TransactionalAspect {

    // M: AOP를 사용한 트랜잭션 관리 구현하기 - 구현부

    // 특정 어노테이션을 JoinPoint로 하여 Advice를 작성합니다.
    @Around("@annotation(org.springframework.transaction.annotation.Transactional)")
    public Object controlTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        // 트랜잭션 시작
        System.out.println("트랜잭션 시작!");
        try {
            // 실제 트랜잭션 동작. 서비스측에서 구현한 로직이 실행 됨.
            Object result = joinPoint.proceed();
            System.out.println("트랜잭션 종료!");
            return result;
        } catch (Exception e) {
            // 에러발생했을 때, 이미 롤백은 @Transactional Aspect에 의해 처리되었음.
            System.out.println("트랜잭션 실패, 롤백했습니다.");
            throw e;
        }
    }

}