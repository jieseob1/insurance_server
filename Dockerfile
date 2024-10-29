# openjdk 17 버전 사용
FROM openjdk:17-jdk

# clean build도 넣어줘야함
# 작업 디렉토리를 /app으로 설정
WORKDIR /app

# build/libs에 있는 app JAR 파일을 복사.
COPY build/libs/*.jar app.jar

# app.jar 실행
ENTRYPOINT ["java", "-jar", "app.jar"]