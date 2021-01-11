#<postgresql docker  설치>
##1. 설치 명령  
docker run -p 5432:5432 -e POSTGRES_PASSWORD=pass -e POSTGRES_USER=seo -e POSTGRES_DB=postgres_jpabook --name postgres_boot -d postgres
##2. 계정정보 
## - username : seo
## - password : pass
##3. postgresql 명령 
###1) postgresql 터미널 들어가기
docker exec -i -t postgres_boot bash
postgres_boot : docker에 생성된 postgresql 의 컨테이너 명 
###2) 루트권한 요청  
su - postgres
###3) 데이터베이스 목록조회
\list 또는 \l
###4) 테이블 조회 
\dt
###5) postgresql 관련 설명은 아래 링크 참고
https://zetawiki.com/wiki/PostgreSQL,_PAS


##4. Docker 명령어
### 1) 이미지조회
 #### - 실행중인 이미지
    docker ps
 #### - 모든 이미지 
   docker ps -all 
### 2) DB start :
 docker start postgres_boot
### 3) DB stop : 
 docker stop postgres_boot
 

# jpabookdemo 작업 내역 
## 2020-09-22 :
 실전!스프링부트와 JPA활용1- 웹 애플리케이션 개발 - 완료 됨.




#<Gradle 빌드>

##1.참고링크

###1)Android Studio Gradle 에서 빌드시 Signed Key 설정하기

Link : https://snowdeer.github.io/android/2017/01/22/android-studio-build-key-setting/

###2) Android apk signing: sign an unsigned apk using command line

Link : https://medium.com/modulotech/how-to-sign-an-unsigned-apk-using-command-line-636a056373a0

###3) Gradle을 사용하여 릴리스 서명 apk 파일을 만드는 방법은 무엇입니까?

Link : https://lottogame.tistory.com/550

