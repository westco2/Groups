#SpringBoot-Project
스프링 부트 코딩 학습관리 솔루션

## 프로젝트 소개 
교육자의 수강생 개별 관리의 어려움때문에 강사와 수강생 질문 게시판
강의 자료 공유,코딩 테스트 숙제 프로세서를 가진 프로젝트

## 개발 기간
* 24.03.25 ~ 24.04.22
  03.25 ~ 03.31 : 프로젝트 기획 및 주제선정 기획안 작성
  04.01 ~ 04.03 : UI 작업
  04.04 ~ 04.10 : 1차 모델링 구현
  04.11 ~ 04.17 : 2차 모델링 구현
  04.18 ~ 04.22 : 배포 및 오류수정

### 맴버구성
- 조장 서명인 - 그룹, 숙제, 메인페이지, 마이페이지, UX UI, 통합 및 형상관리, 운영및 배포, aws
- 조원 김연화 - 공지사항, youtube api, 회원조회, 메인페이지, 자료정리
- 조원 장현준 - 로그인, 회원가입, 회원인증, 결제, 본인인증, 권한관리
- 조원 최원준 - 학생 질문 게시판, 수업자료 게시판
- 조원 구진영 - 회원가입, 결제

### 개발 환경
- `Java 11`
- `JDK 11.0.21`
- **IDE** : IntelliJ IDEA, Eclipse
- **Framework** : Springboot(2.7.5)
- **Database** : MySQL(8.0.36)
- **ORM** : Mybatis, JPA

### 서비스 아키텍쳐
![서비스png](https://github.com/westco2/Groups/assets/148563632/fcc3a82d-dd9b-4763-84bc-e50c10b184f5)


## 페이지별 기능
#### [홈페이지]
![홈페이지](https://github.com/westco2/Groups/assets/148563632/ab70c7e1-0f81-4f38-b7fd-b16edc3eabf8)
#### [회원가입]
![회원가입](https://github.com/westco2/Groups/assets/148563632/50914439-d493-455f-a91f-3f531088f4b9)
#### [결제]
![결제](https://github.com/westco2/Groups/assets/148563632/5fb4913d-e5d1-4847-a561-ec897ad59834)
#### [그룹]
##### 그룹 생성
![그룹생성](https://github.com/westco2/Groups/assets/148563632/99c388a9-72f9-470b-9085-622851496c30)
##### 상태 변경
- 더이상 학생의 가입을 받고 싶지 않으면 그룹 상태를 비활성화로 바꾸어 신규가입 학생들에게 노출시키지 않습니다.
![그룹상태변경](https://github.com/westco2/Groups/assets/148563632/c1487ddb-57b9-47b7-8644-4f50cd68e9eb)
##### 학생 신청
- 학생이 교육자의 이름을 확인한후 그룹가입신청을 합니다.
![학생그룹가입](https://github.com/westco2/Groups/assets/148563632/c6ec9fd9-bf89-49e5-8330-e53202cf5c02)
##### 교육자 승인
- 해당그룹의 신청자를 확인한 다음 승인을 처리합니다.
![그룹가입승인](https://github.com/westco2/Groups/assets/148563632/301343f0-e5ff-4326-ae92-1daddd66dbb9)
#### [숙제 배포 및 확인]
##### 숙제 배포
-교육자가 숙제를 레벨에과 학생의 점수에 맞게 배포할수 있습니다.
![숙제배포](https://github.com/westco2/Groups/assets/148563632/4303e483-df05-404a-bc17-23e154990647)
##### 학생 숙제 제출 및 확인
- 학생이 숙제를 확인하고 숙제를 풀고 확인을 할수 있습니다.
![숙제확인](https://github.com/westco2/Groups/assets/148563632/c54c034b-7349-419b-ac02-38cca5d7f5fe)
![숙제풀기](https://github.com/westco2/Groups/assets/148563632/00fb3685-1f5a-4dbb-a753-c2edc37e7f10)
![내가푼 숙제확인](https://github.com/westco2/Groups/assets/148563632/48596921-5314-40be-b15c-c3f429daa497)
##### 교육자 숙제 확인
- 교육자가 학생이 제출한 숙제를 확인할수 있습니다
- 지금까지 학생에게 배포한 숙제 학생이 제출한 숙제갯수로 수행률을 확인할수 있습니다.
![교육자 숙제확인](https://github.com/westco2/Groups/assets/148563632/a3bf61d7-4e13-4fb3-a638-03b9ccc5fe52)
#### [질문등록/ 수업자료확]
- 학생이 궁금한 사항이 있으면 교육자에게 질문을 등록합니다.
- 교육자가 질문을 확인후 답변을 합니다.
![질문등록](https://github.com/westco2/Groups/assets/148563632/23eb947f-9552-4fe6-898d-9b60c6052258)
![질문해결](https://github.com/westco2/Groups/assets/148563632/fb983e22-a77d-40b5-81fc-bb6179c907fc)
#### [수업자료다운]
- 교육자가 해당 그룹에 수업자료를 올리면 해당그룹의 학생이 수업자료를 다운 또는 볼 수 있습니다.
![강의자료 다운](https://github.com/westco2/Groups/assets/148563632/395397a9-68e7-4e89-8125-3ad404655c9a)
#### [youtube api]
- 교육자가 youtube채널을 가지고 있으면 등록할수 있습니다.
![youtube-ezgif com-video-to-gif-converter](https://github.com/westco2/Groups/assets/148563632/b9978d5b-1d1d-479b-ae65-1aeaaa6f47cc)






