<div align="center">
  <img src="https://user-images.githubusercontent.com/53372971/175765111-6e50046f-b608-4826-a4f6-66c88b1dbb9a.png" width="30%" alt="badge42 logo" >
  <h1>StudyIn</h1>
  <p>πSWμ€μ¬λν κ³΅λν΄μ»€ν€π</p>
</div>

<div align="center">
<img width="80%" alt="αα³αα³αα΅α«αα£αΊ 2022-06-25 αα©αα₯α« 12 15 02" src="https://user-images.githubusercontent.com/53372971/175765155-cff9469b-1955-4ba6-b646-52c0dd1900f6.png">
  </div>

## λͺ©μ°¨

- [νμ](#νμ)
- [νλ‘μ νΈ λͺ©ν λ° κ°μ](#νλ‘μ νΈ-λͺ©ν-λ°-κ°μ)
- [κ°λ° λ° λ°°ν¬νκ²½](#κ°λ°-λ°-λ°°ν¬νκ²½)
- [μ£Όμ](#μ£Όμ)
- [ER Diagram](#ER-Diagram)
- [μ νλΈ μμ](#μ νλΈ-μμ)


# νμβ¨

<table>
  <tr>
    <td align="center"><a href="https://github.com/Lee-seungju"><img src="https://user-images.githubusercontent.com/53372971/175765197-4387f79d-848d-4210-ac6f-b52b081c2922.png" width="100px;" alt=""/><br /><sub><b>Lee-seungju(slee2)</b></sub></a><br /><a>Back End</a></td>
    <td align="center"><a href="https://github.com/rogitun"><img src="https://user-images.githubusercontent.com/53372971/175765298-237aab34-8936-476c-958e-ce7053c30b81.png" width="100px;" alt=""/><br /><sub><b>rogitun(Hansel)</b></sub></a><br /><a>Back End</a></td>
    <td align="center"><a href="https://github.com/josuhee"><img src="https://user-images.githubusercontent.com/53372971/175765220-b0d5a222-0c31-4cf4-b96a-899b2e4544ae.png" width="100px;" alt=""/><br /><sub><b>josuhee</b></sub></a><br /><a>Front End</a></td>
    <td align="center"><a href="https://github.com/millifail"><img src="https://user-images.githubusercontent.com/53372971/175765338-8c365d71-fdf4-47b2-8b0e-c4bde8d55e53.png" width="100px;" alt=""/><br /><sub>millifail(Sehun Hwang)</b></sub></a><br/><a>Front End</a></td>
    <td align="center"><a href="https://github.com/gzero-99"><img src="https://user-images.githubusercontent.com/53372971/175765257-ee390ab3-2ec4-459c-9b42-a1c7c329d021.png" width="100px;" alt=""/><br/><sub><b>gzero-99(gzero)</b></sub></a><br/><a>Designer</a></td>
  </tr>
</table>

# νλ‘μ νΈ λͺ©ν λ° κ°μ
2022.06.21-2022.06.23
3μΌμ κΈ°κ°λμ μμ΄λμ΄λ₯Ό μκ°νκ³ , ν΄λΉ μμ΄λμ΄λ₯Ό κ΅¬νν μΉ/μ± κΈ°λ° μννΈμ¨μ΄ κ°λ°

λνμμ, λνμμ μν, λνμμ μν μνμ§ μ μ μΉ
μ κ³΅,κ΅μ,μμ¬ λ±μ κ³΅λΆ νκΈ° μν΄ λνμμ μν μνμ§ μ μ μΉ μ¬μ΄νΈμλλ€.
λΆμΌμ λ§λ λ€μν λ¬Έμ λ€λ‘ κ΅¬μ±λ μνμ§λ₯Ό μ κ³΅νλ©°, μΈκΈ°μλ μνμ§ λν λΆλ₯νμ¬ λ³΄μ¬μ€λλ€.
μμ μ ν΄λΉλλ λ¬Έμ μλ§ κ΅­νλμ§ μκ³ , λν λ³λ‘ λ€μν λ¬Έμ λ₯Ό μ ν  μ μμ΅λλ€.

# κ°λ° λ° λ°°ν¬νκ²½

<div align="center">
  <img width="60%" alt="αα³αα³αα΅α«αα£αΊ 2022-06-25 αα©αα₯α« 12 15 02" src="https://user-images.githubusercontent.com/53372971/175765847-6d015ffc-1f92-43c0-b8aa-83c069dcc8cf.jpeg">
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
  
# μ£Όμ

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
  dir: "λ‘μ»¬μμ μ΄λ―Έμ§ νμΌμ΄ μ μ₯λ  κ²½λ‘λ₯Ό μ§μ ν΄μ£ΌμΈμ"
```

# ER Diagram


<div align="center">
  <img width="80%" alt="αα³αα³αα΅α«αα£αΊ 2022-06-25 αα©αα₯α« 12 15 02" src="https://user-images.githubusercontent.com/53372971/175766237-92a272ae-2fb2-40b8-a154-35174c4021b5.png">
</div>
  
# μ νλΈ μμ

νλ‘μ νΈ μ€λͺ λ° μμ°
https://www.youtube.com/watch?v=qU4zvYzuFiE
   

