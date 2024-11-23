# **Newsfeed Project BY EightByte**
![image](https://github.com/user-attachments/assets/4468113b-03a3-4c30-973f-ebed6c8250bd)


---
## Tech Stack
JAVA : JDK 17

Spring Boot : 3.3.5

IDE : IntelliJ

MySQL : Ver 8+

---

## **API 명세서 URL :**
[API URL](https://documenter.getpostman.com/view/18429295/2sAYBUCXTm)
---

## ERD :
![image](https://github.com/user-attachments/assets/a0cd3722-6cf4-4425-b269-6bcae2c39856)
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

- API, ERD 기획 및 설계
- Entity 초기 설정 및 연관관계 생성
- 인증 / 인가 관련 처리 (로그인, 로그아웃, 필터를 사용한 로그인 검증)
- ControllerAdvice 를 활용한 GlobalExceptionHandler 구현
- User 관련 로직 구현
- 리팩토링

### 조현지

- API, ERD 기획 및 설계
- 와이어 프레임
- Post 관련 로직 구현

### 백은영

- API, ERD 기획 및 설계
- Friend 관련 로직 구현
- CommentLike 관련 로직 구현

### 김동건

- API, ERD 기획 및 설계
- Comment 관련 로직 구현
- PostLike 관련 로직 구현

---

## 프로젝트 설계 시 목적 및 목표

---

저희는,

한국형 스택오버플로우라는 뼈대를 세워보자는 목적을 가지고  프로젝트를 진행했습니다.

저희 프로젝트의 목표는 프로그래밍 및 소프트웨어 개발 관련 질문과 답변을 공유하고, 해결책을 제공하는 커뮤니티를 형성하는 것이기에,  처음 설계 단계부터 도전 과제인 댓글 기능을 처음부터 구현하고자 하였습니다.

---

## 특징

---
### User

- 비밀번호는 암호화된 상태로 Database 에 저장됩니다.

### Post

- 작성 시 ask, info 타입을 분리하여 원하는 카테고리의 글을 작성할 수 있고, 원하는 카테고리의 글을 조회할 수 있습니다.
- 친구들이 작성한 게시글들을 따로 조회할 수 있습니다.
- 원하는 기준으로 정렬된 게시글을 볼 수 있고(좋아요, 수정일자) 기간별 게시글을 조회할 수 있습니다.
- 게시글의 제목을 통한 검색이 가능합니다.

### Comment
- 한 게시글의 모든 댓글을 조회할 수 있습니다.
- 댓글 수정, 삭제는 댓글의 작성자 혹은 게시글의 작성자만 가능합니다.

### Friend
- 본인에게 친구요청을 할 수 없습니다.
- 요청의 Status 를 저장해 응답대기(unChecked), 수락(accepted), 거절(rejected) 를 구분하였습니다.
- Status 를 활용해 친구 목록(accepted), 친구 요청 목록(unChecked) 를 조회할 수 있습니다.

### Like
- 본인의 게시글, 댓글에는 좋아요를 할 수 없습니다.
- 같은 사용자는 게시글, 댓글에 한 번만 좋아요가 가능합니다.

---

## 기능

---
### 공통
- 로그인 후 서버는 클라이언트에게 JSESSIONID를 쿠키에 저장하고, 이후 클라이언트는 서버로 요청을 보낼 때마다 해당 쿠키를 자동으로 서버에 포함하게 하여, 요청마다 자동으로 인증이 이루어집니다.
- 수정, 삭제 시 Session 에 저장된 로그인된 유저 정보를 통해 본인만 수정 / 삭제 할 수 있습니다.
- ControllerAdvice를 이용해 GlobalExceptionHandler 를 구현하여 통일된 에러 Response 를 message, status 등을 활용하여 반환할 수 있도록 했습니다.
- 삭제가 진행될 때 완전히 삭제하지 않고 테이블 마다 deleted 컬럼을 사용하여 Soft Delete 를 구현했습니다.
- 영속성 전이를 활용해 데이터가 삭제될 때 관련된 데이터가 같이 삭제될 수 있도록 했습니다.(ex. 유저가 삭제되면 유저가 작성한 게시글, 댓글 등이 함께 삭제됨)

### User
- 사용자를 Create, Read, Update, Delete 할 수 있습니다.
- 프로필 조회 시 민감한 정보(password) 는 조회되지 않습니다.
- 비밀번호 수정 시, 본인 확인을 위해 현재 비밀번호를 입력하여 올바른 경우에만 수정할 수 있습니다.
- 현재 비밀번호와 동일한 비밀번호로는 변경할 수 없습니다.
- 비밀번호는 최소 8자 이상이며, 대문자, 소문자, 숫자, 특수문자를 최소 1개씩 포함해야 합니다.
- 탈퇴한 사용자의 아이디는 재사용할 수 없습니다.

### Post
- 게시글을 Create, Read, Update, Delete 할 수 있습니다.
- 기본 정렬은 생성일자(created_at) 기준으로 내림차순 정렬합니다.
- page, limit 정보를 전달 받아 페이지네이션을 할 수 있습니다.
- 작성 시 ask, info 타입을 분리하여 원하는 카테고리의 글을 작성할 수 있고, 원하는 카테고리의 글을 조회할 수 있습니다.
- 게시글에 추가된 댓글을 모두 볼 수 있습니다.
- Type 을 Enum 을 사용하여 좀 더 안정적으로 관리했습니다.

### Comment
- 댓글을 Create, Read, Update, Delete 할 수 있습니다.
- 내용만 수정이 가능합니다.

### Friend
- 친구 요청을 보낼 수 있고(Create), 요청을 확인(Read) 후 응답(Update, Delete)할 수 있습니다.
- 뉴스피드에 친구들의 게시글을 모아 볼 수 있습니다.
- Status 를 Enum 을 사용하여 좀 더 안정적으로 관리했습니다.

### Like
- 게시글과 댓글에 좋아요를 남기고 취소할 수 있습니다.

