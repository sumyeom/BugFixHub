# **Newsfeed Project BY EightByte**

---

## 팀원

| 이름              | Github 프로필  | 블로그     | 역할 |
| ----------------- | -------------- | ---------- | ---- |
| 김민범 | [alsqja]      | [블로그](https://velog.io/@alsqja2626/posts)  | 팀장 |
| 조현지 | [chohyuun]    | [블로그](https://something-do-it.tistory.com) | 팀원 |
| 백은영 | [beunyeong]   | [블로그](https://beunyeong.tistory.com)       | 팀원 |
| 김동건 | [gimdonggeon] | [블로그](https://codinggeony.tistory.com)     | 팀원 |

[chohyuun]: https://github.com/chohyuun
[alsqja]: https://github.com/alsqja
[beunyeong]: https://github.com/beunyeong
[gimdonggeon]: https://github.com/gimdonggeon

## 수행한 일

### 김민범

- ㅁㅇㄴ

### 조현지

- ㅁㄴㅇ

### 백은영

- ㅁㄴㅇ

### 김동건

- ㅁㄴㅇ

---

## 프로젝트 설계 시 목적 및 목표

---

저희는,

한국형 스택오버플로우라는 뼈대를 세워보자는 목적을 가지고  프로젝트를 진행했습니다.

저희 프로젝트의 목표는 프로그래밍 및 소프트웨어 개발 관련 질문과 답변을 공유하고, 해결책을 제공하는 커뮤니티를 형성하는 것이기에,  처음 설계 단계부터 도전 과제인 댓글 기능을 처음부터 구현하고자 하였습니다.

---

## 특징

---

### 세션 기반 인증으로 인한 자동 인증 처리(JSESSIONID 활용)

로그인 후 서버는 클라이언트에게 JSESSIONID를 쿠키에 저장하고,  이후 클라이언트는 서버로 요청을 보낼 때마다 해당 쿠키를 자동으로 서버에 포함하게 하여, 요청마다 자동으로 인증이 이루어지게 하였습니다.

### 게시글의 주제에 따라 카테고리 분류

게시글 작성 시 요청 정보에 type요청을 추가하여,  정보글과 질문글로 카테고리를 구분하여 게시글을 볼 유저들의 편의성을 고려하였습니다.



### **API 명세서 URL :**

---

### ERD URL :
