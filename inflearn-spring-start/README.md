- [스프링 입문 스프링부트](#스프링-입문-스프링부트)
  - [빌드 후 실행하기](#빌드-후-실행하기)
  - [노트](#노트)

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
