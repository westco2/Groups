# SpringBoot-Project 코딩 학습관리 솔루션
- 배포 URL : (http://www.groupsone.site/)
- Test ID : coding808
- Test PW : 1234

### 프로젝트 소개 
교육자의 수강생 개별 관리의 어려움때문에 강사와 수강생 질문 게시판
강의 자료 공유,코딩 테스트 숙제 프로세서를 가진 프로젝트

### 개발 기간
##### 24.03.25 ~ 24.04.22
-  03.25 ~ 03.31 : 프로젝트 기획 및 주제선정 기획안 작성
-  04.01 ~ 04.03 : UI 작업
-  04.04 ~ 04.10 : 1차 모델링 구현
-  04.11 ~ 04.17 : 2차 모델링 구현
-  04.18 ~ 04.22 : 배포 및 오류수정

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


### 페이지별 기능
#### [홈페이지]
![홈페이지](https://github.com/westco2/Groups/assets/148563632/ab70c7e1-0f81-4f38-b7fd-b16edc3eabf8)
#### [회원가입]
![회원가입](https://github.com/westco2/Groups/assets/148563632/50914439-d493-455f-a91f-3f531088f4b9)
#### [마이페이지]
- 관리자는 매출, 일일 가입자수, 인기상품 등 통계가 나옵니다.
- 교육자는 기본정보, 내 학습그룹, 새로운 질문이 나옵니다.
- 학생은 기본정보, 내가 푼 숙재내역, 미제출 숙제들이 나옵니다.
![마이](https://github.com/westco2/Groups/assets/148563632/ebad5132-640e-41ac-84df-bb5ebaa84802)
#### [결제]
![결제](https://github.com/westco2/Groups/assets/148563632/5fb4913d-e5d1-4847-a561-ec897ad59834)
#### [그룹]
##### 그룹 생성
![그룹생성](https://github.com/westco2/Groups/assets/148563632/99c388a9-72f9-470b-9085-622851496c30)
##### 상태 변경
- 더이상 학생의 가입을 받고 싶지 않으면 그룹 상태를 비활성화로 바꾸어 신규가입 학생들에게 노출시키지 않습니다.
- 
![그룹상태변경](https://github.com/westco2/Groups/assets/148563632/c1487ddb-57b9-47b7-8644-4f50cd68e9eb)
##### 학생 신청
- 학생이 교육자의 이름을 확인한후 그룹가입신청을 합니다.
![그룹가입신청](https://github.com/westco2/Groups/assets/148563632/3a65e703-2ee4-4500-86c7-8f372191fd69)
##### 교육자 승인
- 해당그룹의 신청자를 확인한 다음 승인을 처리합니다.
![선생 그룹 승인](https://github.com/westco2/Groups/assets/148563632/4266260d-2ecc-4b98-b38c-2151fc1d13b8)
#### [숙제 배포 및 확인]
##### 숙제 푼 이력 확인
![숙제 이력 확인](https://github.com/westco2/Groups/assets/148563632/b179c8fd-ea12-4a9f-b14e-a0d151873aeb)
##### 숙제 배포
- 교육자가 숙제를 레벨에과 학생의 점수에 맞게 배포할수 있습니다.
![숙제 배포](https://github.com/westco2/Groups/assets/148563632/535ba001-8fd7-4269-b69b-e13ff3205e69)

##### 학생 숙제 제출 및 확인
- 학생이 숙제를 확인하고 숙제를 풀고 확인을 할수 있습니다.
![학생숙제 제출](https://github.com/westco2/Groups/assets/148563632/2b26610d-3c4d-42fc-ae46-999a2f45a04a)

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

### 트러블 슈팅
#### [숙제 컴파일러 콘솔창 정지 이슈]
컴파일러를 구현하는 과정에서 학생이 입력한 코드를 가지고 와서 자바 자체적으로 컴파일 해주는 라이브러리를 사용해서 동적으로 컴파일 하고 결과값을 반환받아 교육자가 입력한 정답값과 
비교하는 로직을 설계하였습니다 이 과정에서 사용자가 입력받는 값이 교육자가 정답으로 입력하는 값보다 많을 경우 그 값을 입력받기 위해 대기하는 상태가 되어서 콜솔창이 멈춰버리는 이슈가 생겼었습니다
그래서 시간초를 정해서 그 시간초보다 코드실행시간이 오래걸리는 경우를 예외가 발생하게하여 이슈를 해결 했습니다
#### [union all 정렬 이슈]
차트 라이브러리를 사용하여 8개의 select문을 union all 처리해서 한번에 불러와 데이터를 뿌려주는데 각각 select문의 내용이 다르기때문에 union all을 한 후에 정렬을 해줄수 없었습니다. 
그래서 선택한 방법은 자바스크립트로 값을 가져와서 형식에 맞게 정렬하는 로직을 사용하여 처리하였습니다. 

### 프로젝트 후기

프로젝트를 진행하면서 학생의 입장, 교육자의 입장, 관리자의 입장을 고려하여 다양한 시각으로 접근해야 했습니다. 이로 인해 세부 사항에 대한 조율이 필요했고, 각 역할에 따라 요구되는 사항이 달랐습니다. 이런 점에서 프로젝트 진행 중 발생한 이슈를 해결하기 위해 필요한 시간과 노력은 상당했지만, 동시에 다양한 입장에서 문제를 바라보는 좋은 경험이 되었습니다.
프로젝트를 진행하면서 가장 크게 느낀 점은 팀 간의 커뮤니케이션과 협력의 중요성이었습니다. 회의를 통해 서로의 의견을 공유하고, 때로는 의견 차이를 조율하며, 결국에는 하나의 목표를 향해 나아가는 경험은 소중했습니다. 이러한 경험을 통해 우리는 팀워크의 중요성과 다양한 시각을 수용하는 능력을 배울 수 있었으며, 이것이 프로젝트 성공의 핵심 요소임을 다시 한 번 깨달았습니다.








