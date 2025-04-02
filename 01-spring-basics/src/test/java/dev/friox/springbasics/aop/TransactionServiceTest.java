package dev.friox.springbasics.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TransactionServiceTest {

    // M: 어노테이션을 사용하여 빈 주입하기

    @Autowired
    private TransactionService transactionService;

    @Test
    void transaction() throws Exception {
        // M: AOP를 사용한 트랜잭션 관리 구현하기 - Test
        try {
            // isError를 true로 바꾸면 테스트용 에러 발생
            transactionService.transaction(false);
        } catch (Exception e) {

        }
    }

}