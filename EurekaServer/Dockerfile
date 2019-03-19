FROM openjdk:11-jdk
VOLUME /tmp
COPY target/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/app.jar"]