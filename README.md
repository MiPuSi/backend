<div align="center">
  <a href="https://www.numble.it/b21bf666-045f-478e-8643-927417a448d2"><img src="https://user-images.githubusercontent.com/53372971/175765111-6e50046f-b608-4826-a4f6-66c88b1dbb9a.png" width="30%" alt="badge42 logo" ></a>
  <h1>StudyIn</h1>
  <p>🏆SW중심대학 공동해커톤🏆</p>
</div>

<div align="center">
<img width="80%" alt="스크린샷 2022-06-25 오전 12 15 02" src="https://user-images.githubusercontent.com/53372971/175765155-cff9469b-1955-4ba6-b646-52c0dd1900f6.png">
  </div>

## 목차

- [팀원](#팀원)
- [프로젝트 목표 및 개요](#프로젝트-목표-및-개요)
- [개발 및 배포환경](#개발-및-배포환경)
- [주의](#주의)
- [ER Diagram](#ER-Diagram)
- [유튜브 영상](#유튜브-영상)


# 팀원✨

<table>
  <tr>
    <td align="center"><a href="https://github.com/Lee-seungju"><img src="https://user-images.githubusercontent.com/53372971/175765197-4387f79d-848d-4210-ac6f-b52b081c2922.png" width="100px;" alt=""/><br /><sub><b>Lee-seungju(slee2)</b></sub></a><br /><a>Back End</a></td>
    <td align="center"><a href="https://github.com/rogitun"><img src="https://user-images.githubusercontent.com/53372971/175765298-237aab34-8936-476c-958e-ce7053c30b81.png" width="100px;" alt=""/><br /><sub><b>rogitun(Hansel)</b></sub></a><br /><a>Back End</a></td>
    <td align="center"><a href="https://github.com/josuhee"><img src="https://user-images.githubusercontent.com/53372971/175765220-b0d5a222-0c31-4cf4-b96a-899b2e4544ae.png" width="100px;" alt=""/><br /><sub><b>josuhee</b></sub></a><br /><a>Front End</a></td>
    <td align="center"><a href="https://github.com/millifail"><img src="https://user-images.githubusercontent.com/53372971/175765338-8c365d71-fdf4-47b2-8b0e-c4bde8d55e53.png" width="100px;" alt=""/><br /><sub>millifail(Sehun Hwang)</b></sub></a><br/><a>Front End</a></td>
    <td align="center"><a href="https://github.com/gzero-99"><img src="https://user-images.githubusercontent.com/53372971/175765257-ee390ab3-2ec4-459c-9b42-a1c7c329d021.png" width="100px;" alt=""/><br/><sub><b>gzero-99(gzero)</b></sub></a><br/><a>Designer</a></td>
  </tr>
</table>

# 프로젝트 목표 및 개요
2022.06.21-2022.06.23
3일의 기간동안 아이디어를 생각하고, 해당 아이디어를 구현한 웹/앱 기반 소프트웨어 개발

대학생의, 대학생에 의한, 대학생을 위한 시험지 제작 웹
전공,교양,시사 등을 공부 하기 위해 대학생을 위한 시험지 제작 웹 사이트입니다.
분야에 맞는 다양한 문제들로 구성된 시험지를 제공하며, 인기있는 시험지 또한 분류하여 보여줍니다.
서적에 해당되는 문제에만 국한되지 않고, 대학 별로 다양한 문제를 접할 수 있습니다.

# 개발 및 배포환경

<div align="center">
  <img width="60%" alt="스크린샷 2022-06-25 오전 12 15 02" src="https://user-images.githubusercontent.com/53372971/175765847-6d015ffc-1f92-43c0-b8aa-83c069dcc8cf.jpeg">
</div>

- Infra
  - Naver Cloud(CentOS)
- Back End
  - Spring Boot, Spring Security
  - Redis
  - MySQL, H2
- Front End
  - JSP
  - HTML, CSS, JS
- Design
  - Figma
  
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

# ER Diagram


<div align="center">
  <img width="80%" alt="스크린샷 2022-06-25 오전 12 15 02" src="https://user-images.githubusercontent.com/53372971/175766237-92a272ae-2fb2-40b8-a154-35174c4021b5.png">
</div>
  
# 유튜브 영상

프로젝트 설명 및 시연
https://www.youtube.com/watch?v=qU4zvYzuFiE
   

