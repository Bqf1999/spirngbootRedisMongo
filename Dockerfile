FROM openjdk:11
MAINTAINER baoqifan
ADD springboot_mongo_redis-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
