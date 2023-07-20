# 스레드형 커뮤니티 플랫폼 프로젝트. 담(談).
Dam: A threaded forum platform
--
서구권의 Reddit과 같은 스레드 플로트 형태의 게시판을 만들 수 있는 커뮤니티 플랫폼 프로젝트입니다.

## Tech Stack
* Java
* Spring
* Spring Boot
* Spring Security
* MySQL
* Data JPA
* Json Web Token
* Git

## 프로젝트 기능 및 설계
* 회원 가입 기능
  - [x] 사용자는 회원가입을 할 수 있다. 일반적으로 모든 사용자는 회원가입시 USER 권한 (일반 권한)을 지닌다.
  - [x] 회원가입시 이메일과 패스워드, 닉네임을 입력받으며, 이메일은 unique 해야한다.

* 회원 탈퇴 기능
  - [x] 회원이 스스로 탈퇴하는 API를 만든다.

* 로그인 기능
  - [x] 사용자는 로그인을 할 수 있다. 로그인시 회원가입때 사용한 이메일과 패스워드가 일치해야한다.

* 게시글 작성 기능
  - [ ] 로그인한 사용자는 권한에 관계 없이 글을 작성할 수 있다.
  - [ ] 사용자는 게시글 제목(텍스트), 게시글 내용(텍스트)를 작성할 수 있다.

* 통합 게시판 목록 조회 기능
  - [ ] 로그인하지 않은 사용자를 포함한 모든 사용자는 major 유형 게시판의 게시글을 메인에서 조회할 수 있다.
  - [ ] 게시글은 최신순으로 기본 정렬되며, 댓글수(많은순 / 적은순), 댓글 작성일, 추천/비추천비율을 구한 특정한 값(비율)으로도 정렬이 가능하다.
  - [ ] 통합 게시글 목록 조회시 응답에는 게시글 제목, 게시판 이름과 작성일, 댓글 수, 댓글 작성일, 추천 수, 비추천 수 의 정보가 필요하다.
  - [ ] 게시글은 종류가 많을수 있으므로 paging 처리를 한다.

* 개별 게시판 목록 조회 기능
  - [ ] 로그인하지 않은 사용자를 포함한 모든 사용자는 개별 게시판의 게시글을 조회할 수 있다.
  - [ ] 게시글은 최신순으로 기본 정렬되며, 댓글수(많은순 / 적은순), 댓글 작성일, 추천/비추천비율을 구한 특정한 값(비율)으로도 정렬이 가능하다.
  - [ ] 특정한 조건을 달성한 게시글의 경우 최신순으로 먼저 정렬된다. 단, 해당 게시글은 통합 게시판 목록에선 조회되지 않는다. (공지사항 등을 구현)
  - [ ] 게시글 목록 조회시 응답에는 게시글 제목과 작성일, 댓글 수, 댓글 작성일, 추천 수, 비추천 수 의 정보가 필요하다.
  - [ ] 게시글은 종류가 많을수 있으므로 paging 처리를 한다.

* 특정 게시글 조회 기능
  - [ ] 로그인하지 않은 사용자를 포함한 모든 사용자는 게시글을 조회할 수 있다.
  - [ ] 게시글 제목, 게시글 내용, 작성자, 작성일이 조회된다.

* 댓글 목록 조회 기능
  - [ ] 특정 게시글 조회시 댓글목록도 함께 조회가 된다. 다만 댓글은 많을 수 있기 때문에 별도의 API로 구성한다. 이 또한 로그인하지 않은 사용자를 포함한 모든 사용자가 댓글을 조회할 수 있다.
  - [ ] 댓글은 최신순으로만 정렬되며, paging 처리를 한다.
  - [ ] 댓글 목록 조회시에는 댓글 작성자와 댓글 내용, 댓글 작성일, 댓글 추천 수, 댓글 비추천 수의 정보가 필요하다.

* 댓글 작성 기능
  - [ ] 로그인한 사용자는 권한에 관계 없이 댓글을 작성할 수 있다.
  - [ ] 사용자는 댓글 내용(게시글과 똑같이)을 작성할 수 있다.
  - [ ] 댓글에 댓글 또한 달 수 있다.

* 추천, 비추천 기능
  - [ ] 로그인 한 사용자는 게시물과 댓글에 추천, 비추천을 할 수 있다.
  - [ ] 1게시물에서 1IP 당 추천/비추천은 한번밖에 할 수 없다.

* 게시판 추가 / 삭제 / 관리 기능
  - [x] 요청이 있을 시 게시판을 추가/삭제할 수 있는 API를 만든다.
  - [x] 게시판 추가 시 mini 유형의 게시판이 만들어진다.
  - [ ] 게시판 관리 API 에서는 게시판 이름, 게시판 url, 게시판 유형(mini, minor, major)을 관리한다.

* 게시글 관리 기능
  - [ ] 게시글, 댓글을 수정 및 삭제할 수 있는 API를 만든다.
  - [ ] 게시글에 대한 접근을 막거나 푸는 API를 만든다.

* 회원 관리 기능
  - [ ] 회원의 상태를 변경, 회원을 삭제하는 API를 만든다.

## ERD
![image](https://github.com/peppone-choi/dam/assets/4508765/d351c607-90e6-4265-af12-64b020a86a74)


## Trouble Shooting
