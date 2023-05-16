FROM openjdk:17-oracle

MAINTAINER altonchan.com

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]
