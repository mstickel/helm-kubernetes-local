FROM openjdk:14.0.1-slim
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000","-jar","/app.jar"]
EXPOSE 8080
EXPOSE 8000
