# 03-database-orm

## 미션에 포함되어야 하는 것
* JPA, MyBatis 각각 CRUD 구현
* JPA와 MyBatis 동시에 사용하기
* 요구사항을 바탕으로 DB 테이블 설계하기
* 필드에 인덱스 적용하기

## 요구사항
> 미션에 따로 요구사항이 명시되지 않아서, 임의로 요구사항을 생성했습니다.  

**🍀 Spring Boot, JPA, MyBatis, React를 활용한 간단한 도서 관리 시스템 🍀**  
기본적인 도서 정보관리, 회원 관리, 대출 관리 기능을 제공하는 도서관리 시스템

1. 회원 관리
    * 회원 등록, ~~조회~~, ~~수정~~
    * ~~이메일을 통한 회원 검색~~
    * 회원 로그인 및 로그아웃
2. 도서 관리
    * 도서 등록, 조회, 수정
    * 제목, 저자, ISBN 또는 출판일로 도서 검색
3. 대출 관리
    * 특정 회원의 대출 목록 조회
    * 특정 도서의 대출 이력 조회

## 제외사항
* JWT를 사용하지만, Refresh Token은 사용하지 않습니다.

## 요구사항을 바탕으로 한 DB 설계
1. 회원(Member) 테이블
    > 회원고유번호는 유일해야하고, UUID를 BINARY 형태로 저장합니다.  
    > 회원이름과 이메일은 고정길이가 아니기때문에 `VARCHAR` 자료형을 사용합니다.  
    > 비밀번호는 `bcrypt` 사용 시 60자 고정 길이기 때문에 `CHAR` 자료형을 사용합니다.  
    > 이메일은 고유하고, 유저검색과 검색 성능 향상을 위해 `email` 필드에 고유 인덱스를 적용합니다.  
    * uid(회원 고유번호) : BINARY(16), PRIMARY_KEY
    * name(회원이름) : VARCHAR(50)
    * email(이메일) : VARCHAR(100), UNIQUE_INDEX
    * password(비밀번호) : CHAR(60)
2. 도서(Book) 테이블
    > 도서 고유번호는 유일해야 하며, 등록 시 자동증가하는 정수 값을 가집니다.  
    > 도서제목과 저자이름은 고정길이가 아니기때문에 `VARCHAR` 자료형을 사용합니다.  
    > ISBN은 13자 고정길이기 때문에 `CHAR` 자료형을 사용합니다.  
    > 출판일은 시간 정보없이 날짜만 알면되기 때문에 `DATE` 자료형을 사용합니다.  
    > 도서제목, 저자이름, ISBN, 출판일 검색을 위해 인덱스를 적용합니다.  
    > ISBN은 고유하기 때문에 고유 인덱스를 적용합니다.
    * book_id(도서 고유번호) : INT, PRIMARY_KEY, AUTO_INCREMENT
    * title(도서 제목) : VARCHAR(100), INDEX
    * author(저자 이름) : VARCHAR(100), INDEX
    * isbn : CHAR(13), UNIQUE_INDEX
    * publication_date(출판일) : DATE, INDEX
3. 대출(Loan) 테이블
    > 대출번호는 유일해야 하며, 등록 시 자동증가하는 정수 값을 가집니다.  
    > 회원 고유번호는 회원 테이블의 `uid` 필드를 참조합니다.  
    > 도서 고유번호는 도서 테이블의 `book_id` 필드를 참조합니다.
    * loan_id(대출번호) : INT, PRIMARY_KEY, AUTO_INCREMENT
    * member_uid(회원 고유번호) : BINARY(16), FOREIGN_KEY
    * book_id(도서 고유번호) : INT, FOREIGN_KEY

## 수행 과정
* Custom Hook을 생성하여 JWT 인증관련 동작을 수행합니다.
* 역할 별 페이지 접근 여부를 제어할 수 있어야 함.


## 참고 자료
* [[MySQL] 003# MySQL 데이터 타입 (자료형) 정리](https://devdhjo.github.io/mysql/2020/01/30/database-mysql-003.html)
* [mysql uuid to binary, binary to uuid](https://m.blog.naver.com/sung487/222253457746)
* []()
* []()
* []()
* []()
* []()
* []()
* []()
* []()
* []()
* []()
* []()
* []()
* []()