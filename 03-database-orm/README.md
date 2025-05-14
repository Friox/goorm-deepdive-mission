# 03-database-orm

## 미션 요약
* DDL, DML 쿼리를 사용하여 테이블 생성 및 데이터 조작을 수행합니다.
* 인덱스를 활용하여 성능최적화를 수행합니다.
* Spring Boot Framework에서 JPA와 MyBatis를 사용하여 CRUD 작업을 구현합니다.

## 미션 수행
* JPA와 MyBatis를 적절히 활용하여 Book, User, Loan Entity에 대해 CRUD 작업을 구현합니다.
* 복잡한 JOIN 연산이 포함되어있는 작업은 MyBatis로, 단순 조회, 삭제, 업데이트는 JPA를 활용합니다.

## 미션 결과
* JPA를 사용하여 Book Entity에 대한 CRUD를 구현했습니다.
* MyBatis를 사용하여 User Entity에 대한 CRUD를 구현했습니다.
* JPA + MyBatis를 사용하여 Loan Entity에 대한 CRUD를 구현했습니다.
* books 테이블의 isbn 필드에 UNIQUE 제약조건을 추가하여 인덱스를 생성했습니다.
* users 테이블의 username 필드에 UNIQUE 제약조건을 추가하여 인덱스를 생성했습니다.

## 실행
* 프로젝트 최상단에 `dev.env` 파일을 생성하고, 다음 내용을 작성합니다.
```env
DB_HOST=<DB 주소>
DB_PORT=<DB 포트>
DB_NAME=<DB 이름>
DB_USERNAME=<SQL 사용자 이름>
DB_PASSWORD=<SQL 사용자 비밀번호>
```

## Swagger
* Swagger가 구현되어 있습니다.
* http://localhost:8080/swagger-ui/index.html#/ 로 접속하시면 됩니다.