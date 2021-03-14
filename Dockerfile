####docker build-1####
#FROM openjdk:8-jdk-alpine

#ADD /target/spring-boot-rest-1-1.0.jar /dir/spring-boot-rest-1-1.0.jar

#ENTRYPOINT ["java", "-jar", "/dir/spring-boot-rest-1-1.0.jar"]

####docker build-2####
#FROM openjdk:8-jdk-alpine

#ARG DEPENDENCY=target/dependency	

#COPY ${DEPENDENCY}/BOOT-INF/lib dir/lib
#COPY ${DEPENDENCY}/META-INF dir/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes dir

#ENTRYPOINT	["java", "-cp", "dir:dir/lib/*", "com.spring.boot.rest.SpringBootRest1Application"]

####docker build-3####
FROM maven:3.6.1-jdk-8-alpine AS build

WORKDIR /dir

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src src

RUN mvn package

#RUN jar -xf ./target/spring-boot-rest-1-1.0.jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jdk-alpine

ARG DEPENDENCY=dir/target/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib dir/lib
COPY --from=build ${DEPENDENCY}/META-INF dir/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes dir
	
ENTRYPOINT	["java", "-cp", "dir:dir/lib/*", "com.spring.boot.rest.SpringBootRest1Application"]
