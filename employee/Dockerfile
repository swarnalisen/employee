FROM gradle:7.4.2-jdk17-alpine as build-stage
WORKDIR /app
COPY . .
RUN gradle build -x test

FROM openjdk:17-jdk-alpine3.14 as layering-stage
COPY --from=build-stage /app/build/libs/employee-*-SNAPSHOT.jar employee.jar
RUN mkdir target
RUN java -Djarmode=layertools -jar employee.jar extract --destination target

FROM openjdk:17-jdk-alpine3.14
WORKDIR /app
COPY --from=layering-stage target/dependencies .
COPY --from=layering-stage target/spring-boot-loader .
COPY --from=layering-stage target/snapshot-dependencies .
COPY --from=layering-stage target/application .
EXPOSE 8080
CMD exec java org.springframework.boot.loader.JarLauncher