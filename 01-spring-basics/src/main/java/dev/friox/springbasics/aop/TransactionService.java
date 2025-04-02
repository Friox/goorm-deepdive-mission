package dev.friox.springbasics.aop;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    // M: AOP를 사용한 트랜잭션 관리 구현하기 - 실행부

    @Transactional
    public void transaction(boolean isError) throws Exception {
        System.out.println("트랜잭션 중...");
        if (isError) throw new Exception();
        throw new Exception();
    }

}
