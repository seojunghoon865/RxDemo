# <Gradle 빌드>
## 1.참고링크
### 1)Android Studio Gradle 에서 빌드시 Signed Key 설정하기
Link : https://snowdeer.github.io/android/2017/01/22/android-studio-build-key-setting/

### 2) Android apk signing: sign an unsigned apk using command line
Link : https://medium.com/modulotech/how-to-sign-an-unsigned-apk-using-command-line-636a056373a0

### 3) Gradle을 사용하여 릴리스 서명 apk 파일을 만드는 방법은 무엇입니까?
Link : https://lottogame.tistory.com/550

## 2. Gradle Release 빌드 명령 
 ./gradlew assembleRelease

# <젠킨스 도커 설치> 
Link : https://hub.docker.com/_/jenkins

## 1. 젠킨스 이미지 다운로드
docker pull jenkins/jenkins
## 2. 젠킨스 컨테이너 생성 
docker run --name jenkinsDemo -p 8080:8080 -p 50000:50000 -v /Users/seojunghoon/jenkins_backup/backup_test:/var/jenkins_home jenkins/jenkins

### 1) jenkinsDemo : 컨테이너 이름 
### 2) /Users/seojunghoon/jenkins_backup/backup_test
jenkins사용중에 만들어진 데이터를 로컬디스크에 저장하도록해서 jenkins용 docker를 재시작하더라도 데이터가 남아있게 하기 위한 폴더임.
다른 곳(다른 pc 등 )에서 사용시 해당 폴더 내용만 있으면 현재 사용중인 Jenkins 가 그대로 구성됨.
위 명령 사용하기전에 해당 폴더 생성 해야함. 
<참고>
link : https://arisu1000.tistory.com/27784

### 3) jenkins/jenkins
젠킨스 이미지명 

로그에서 부분 확인

Jenkins initial setup is required. An admin user has been created and a password generated.
Please use the following password to proceed to installation:

60748e759d1b477ca2cf9ba860484615
젠킨스 처음 로그인 페스워드임 

## 3. docker ps -a 
컨테이너 조회

## 4. 젠킨스 실행 


## 5. localhost:8080 
-> 비번 입력 : 60748e759d1b477ca2cf9ba860484615


# <Android sdk install>
## 1. 젠킨스 로컬 접속 
 docker exec -it -u 0 myjenkins /bin/bash
 
## 2. Android-sdk 기본설치
apt update
apt install android-sdk

## 3. 빌드에 필요한 Android-SDK tool 추가설치
wget https://dl.google.com/android/repository/sdk-tools-linux-4333796.zip
mv sdk-tools-linux-4333796.zip /usr/lib/android-sdk/sdk-tools-linux-4333796.zip
cd /usr/lib/android-sdk/
unzip sdk-tools-linux-4333796.zip

## 4. sdkmanager로 필요한 tool 설치
cd tools/bin
./sdkmanager --list
./sdkmanager "build-tools;30.0.2" "build-tools;30.0.2" "platforms;android-30"

## 5. Jenkins 환경변수에 ANDROID_HOME path 등록
 - Jenkins 관리 > 시스템 설정 > Global properties > Environment variables
 - 이름: ANDROID_HOME, 값: /usr/lib/android-sdk


# <GitHub 빌드>
## 1. github 프로젝트 연동.
https://jojoldu.tistory.com/139
## 2. Android 빌드 
Build
    -> Invoke Gradle script
        ->  Use Gradle Wrapper
        ->  Tasks : assembleRelease

빌드 후 조치
: 아래 처럼 만들어야 apk가 생성됨. 
Link : https://kkensu.tistory.com/59
    -> Archive the artifacts
 	    Files to archive : **/*.apk

<슬랙연동>
https://dnight.tistory.com/entry/Jenkins-Slack-%EC%95%8C%EB%A6%BC-%EC%97%B0%EB%8F%99


