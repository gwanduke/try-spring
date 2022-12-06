- [스프링 입문 스프링부트](#스프링-입문-스프링부트)
  - [빌드 후 실행하기](#빌드-후-실행하기)
  - [노트](#노트)
  - [실전! 백엔드 어플리케이션 만들기](#실전-백엔드-어플리케이션-만들기)
    - [일반적인 구조](#일반적인-구조)
    - [클래스 의존 관계](#클래스-의존-관계)
    - [Spring 이 뜰 때 각 클래스의 역할을 알 수 있도록 해주어야함](#spring-이-뜰-때-각-클래스의-역할을-알-수-있도록-해주어야함)
    - [JDBC](#jdbc)
    - [통합테스트](#통합테스트)

# 스프링 입문 스프링부트

- Java 11, IntelliJ
- https://start.spring.io/
  - 최근에는 Maven보다는 Gradle을 많이 사용함
  - snapshot 이나 m1 마크는 정식 버전이 아님
- Gradle (`build.gradle`)
  - 버전 설정, 라이브러리, 의존관계 관리
  - `repositories`를 통해 dependencies를 다운로드. mavenCentral은 npm 같은 녀석
  - 실제로는 ExternalLibraries 에 수많은 라이브러리를 확인할 수 있음
- Spring. Spring boot는 스프링을 한번 감싸준 녀석

## 빌드 후 실행하기

```
$ ./gradlew clean
// 클린빌드

$ ./gradlew build
$ cd build
$ cd libs
$ java -jar hello~.jar
```

## 노트

- 정적파일 서빙: 어떤 요청 경로에 따른 별도 controller가 없는 경우 `resources/static/*`를 검색하고 이 아래의 파일은 별도 처리(템플릿엔진 X) 없이 서빙 (http://localhost:8080/\*\_.html)

## 실전! 백엔드 어플리케이션 만들기

### 일반적인 구조

- 컨트롤러
- 서비스: 핵심 비즈니스 로직 구현 -> 리포지토리에서 데이터를 가져오고 어떤 작업을 수행 (회원 가입 등..). 이 구현시에는 실제 비즈니스와 가까운 용어(함수/변수 이름)를 사용하자.
- 리포지토리: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리 -> DB에서 단순 조회, 저장하는 API를 제공 (User 1 가져오기, User 모두 가져오기 ...)
- 도메인: 비즈니스 도메인 객체 (회원, 주문 등등 주로 DB에 저장하고 관리)
- DB

```
컨틀로러 -> 서비스 ---> 리포지토리 ---> DB
    \      |       /
     \     |      /
      \    v     /
       \> 도메인 </
```

### 클래스 의존 관계

> 데이터 저장소가 선정되지 않았다고 가정하고 초기 진행 (인터페이스를 구현 매개체로 활용)

```
MemberService -> MemberRepository (Interface)
                      ^
                      |
                      |
            Memory MemberRepository
```

### Spring 이 뜰 때 각 클래스의 역할을 알 수 있도록 해주어야함

- 어노테이션
  - `@Controller`
  - `@Repository`
  - `@Service`
  - 생성자에 `@Autowired` -> 생성자에 명시된 의존성을 컨테이너에서 자동으로 넣어줌(의존성을 연결 해줌)
  - `@Component`기반 어노테이션을 사용하면 스프링 빈으로 자동 등록됨
- 스프링 빈을 등록하는 2가지 방법
  - 1. (실행하는 패키지 아래에 있는) 컴포넌트(`@Component`) 스캔과 자동 의존관계 설정 (=> 어노테이션 사용)
  - 2. 자바 코드로 직접 스프링 빈 등록하기

기본적으로 주입되는 클래스는 singleton 형태로 컨테이너에 등록한다. (메모리 절약될 것)

### JDBC

JDBC는 고대의 기술

- 라이브러리: spring-boot-starter-jdbc
- application.properties에 DB 경로 설정

대략 다음과 같은식

```java
String sql = "insert into member(name) values (?)";

// ...
connection = dataSource.getConnection();
PreparedStatement pstmt = //...
pstmt.setString(1, 'Gwanduk Kim');

pstmt.executeUpdate();
```

### 통합테스트

```java

class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
}
```

- `@SpringBootTest`: 스프링 컨테이너와 테스트를 함께 실행
- `@Transactional`: 테스트에 달면 테스트 종료시 롤백 (afterEach 같은 곳에서 초기화로직 만들지 않아도 됨)
