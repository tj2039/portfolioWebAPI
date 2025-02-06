# 1. 사용할 기본 이미지 선택 (OpenJDK 17)
FROM openjdk:17-jdk-slim

# 2. 작업 디렉토리 설정
WORKDIR /portfolioWebAPI

# 3. JAR 파일을 Docker 이미지로 복사
COPY build/libs/*.jar pofol.jar

# 4. 컨테이너 시작 시 실행할 명령어 설정
ENTRYPOINT ["java", "-jar", "pofol.jar"]