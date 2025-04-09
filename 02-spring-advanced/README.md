# 02-spring-advanced

## 📝 요약
* Bootstrap을 이용하여 간단한 게시판 UI를 구현
* Spring Security를 적용, 이메일과 비밀번호로 인증
* 인증 과정을 살펴보기 위해 `formLogin`을 사용하지 않고 직접 구현
* 테스트를 위해 H2 In-memory DB 사용
* 공통된 HTML 요소를 Thymeleaf 기능을 이용하여 분리 (fragment)

## 😣 시행착오

### 1. `@Valid` 어노테이션에 의한 예외 발생 안됨
* 경로 : `/controller/MainController.java`
* 현상 : `@Valid` 어노테이션에 의한 예외 발생이 안되는것처럼 보임
* 원인 : 이후 따라오는 `BindingResult` 가 예외를 처리하고 있었음
* 해결 : `BindingResult` 가 처리한 예외 결과를 사용함

### 2. 애플리케이션 시작 시 DB Table 생성 불가
* 경로 : `/entity/User.java`
* 현상 : 애플리케이션 시작 시 DB Table 생성 오류가 발생함
* 원인 : 테이블 이름이 SQL 예약어인 `USER` 와 겹쳐 생성하지 못함
* 해결 : 명시적으로 예약어가 아닌 다른이름으로 지정하여 해결

### 3. `/signin` 페이지로 접근 시 무한 리다이렉션
* 경로 : `/security/config/SecurityConfig.java`
* 현상 : `/signin`(로그인 페이지)로 GET 방식으로 접근 시 동일 페이지로 무한 리다이렉션
* 원인 : Filter에 의한 인증 실패 시 `/signin`으로 리다이렉션 하도록 했는데,  
의도는 POST 요청에 대해서였지만 단순 접근 GET 요청도 잡아버려 계속 보내버리는 현상 발생
* 해결 : `setRequiresAuthenticationRequestMatcher` 메서드를 사용하여 POST로 한정시킴