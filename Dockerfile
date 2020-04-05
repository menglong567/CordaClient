FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/corda-client-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xmx512m","-jar","/app.jar"]