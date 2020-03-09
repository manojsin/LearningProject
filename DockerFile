FROM openjdk:8
ADD target/.com.groww.jar  com.groww.jar
EXPOSE 8080
ENTRYPOINT ["java" ,"jar","com.groww.jar"]
