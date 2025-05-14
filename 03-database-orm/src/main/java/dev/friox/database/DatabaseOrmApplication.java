package dev.friox.database;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatabaseOrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseOrmApplication.class, args);
    }

}
