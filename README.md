# backend

# 개요
대학생의, 대학생에 의한, 대학생을 위한 시험지 제작 웹

전공,교양,시사 등을 공부 하기 위해 대학생을 위한 시험지 제작 웹 사이트입니다.

분야에 맞는 다양한 문제들로 구성된 시험지를 제공하며, 인기있는 시험지 또한 분류하여 보여줍니다.

서적에 해당되는 문제에만 국한되지 않고, 대학 별로 다양한 문제를 접할 수 있습니다.

# 개발 환경

|도구|버전|
|---|---|
|Framework|Spring Boot 2.7|
|OS|Window 10, CentOS|
|IDE|IntelliJ IDEA|
|DataBase|H2 Database, Mysql|
|Build Tool|Gradle 7.4.1|


# Dependencies
<details>
    <summary>자세히</summary>

<!-- summary 아래 한칸 공백 두고 내용 삽입 -->
<li> Spring Security
<li> Javax.mail
 <li> jjwt
 <li> JPA
  <li> Redis</li>
  
  </details>
  
  
# 주의

application-local.yml
```
spring:
  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:tcp://localhost/~/study/h2/makeHere/jpashop
    username: sa
    password: 123
  h2:
    console:
      enabled: true
  jpa:
    hibernate:
      ddl-auto: create
    open-in-view: false
    properties:
      hibernate:
        show_sql: true
        format_sql: true
file:
  dir: "로컬에서 이미지 파일이 저장될 경로를 지정해주세요"
```
  
  
# API 명세
<li>인증은 HTTP 헤더의 JWT를 통해 이루어집니다.</li>

|Key|Value|
|---|---|
|Content-Type|application/json|
|Authorization|Json Web Token|

<li></li>

|이름|HTTP-Method|URL|Request-Parameter|Response|Auth|
|---|---|---|---|---|---|
|홈|GET|/api/home||{</br>“organization” : “학과”, </br> “num” : “학번”, </br> “nickName” : “이름”, </br> “advice” : “명언”, </br> “adviser” : “명언가” </br>}|NO|
|회원가입|POST|/api/member/email| email </br> password </br> nickName </br> organization||NO|
|로그인|POST|/api/member/login| email </br> password||NO|
|시험지 업로드|POST|/api/exam/upload|questionDtos[].question </br> questionDtos[].questionImage </br> questionDtos[].answer </br> questionDtos[].answerImage </br> schoolName </br> lessonName </br> freeName||YES|
|시험지 조회|GET|/api/exam/{id}||{ </br> “num” : “학번”, </br> "nickName" : "닉네임", </br> “title” : “제목”, </br> “like” : (int) 좋아요수 </br> “questions” : [ { </br> “QOrA” : “문제 또는 답”, </br> “QOrAImage” : “문제 또는 답 이미지" </br> } ] </br> }|YES|
 |시험지 좋아요|POST|/api/exam/{id}/favorite|||YES|
 |마이 시험지|GET|/api/myExam||{ </br> "count": "내가 등록한 시험지 개수", </br> "httpStatus": "HTTP 상태값(OK,Forbidden등)", </br> "message": "마이 시험지", </br> "exams": [  </br> { </br> "id": "시험지 아이디", </br> "title": "시험지 제목", </br> "created": "2022-06-23T00:08:30.086096", </br> "writer": "시험지 작성자 닉네임" </br> }, </br> ..... </br> ] </br> }|YES|
|시험지 검색|GET|/api/exam| { </br> "title" : "검색내용" </br> } | { </br> "total": "현재 페이지 아이템 개수", </br> "currentPage": "현재 페이지", </br> "totalPage": "전체 페이지 몇개", </br> "exams": [ </br> { </br> "num": "시험지 작성자 학번", </br> "title": "시험지 이름", </br> "like": "좋아요 몇개", </br>  "nickName": "시험지작성한유저" </br> }, </br> ] </br> } |YES|
|시험지 랭킹|GET|/api/exam/ranks|| { </br> "total": "랭킹에 포함되는 개수", </br> "currentPage": 1(고정), </br> "totalPage": 1(고정), </br> "exams": [ </br> { </br> "num": "시험지 작성자 학번", </br> "title": "시험지 이름", </br> "like": 좋아요 몇개, </br> "nickName": "시험지작성한유저" </br> }, </br> ] </br> } |YES|
|시험지 댓글|POST|/api/exam/{id}/comment| { </br> "content" : "value" </br> } |  | YES|


  
   
   

