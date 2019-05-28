FROM gradle:5.4.1-jdk8-alpine as compile
LABEL MAINTAINER "misrahariom@gmail.com"
USER root
WORKDIR /opt/shops
ADD --chown=gradle:gradle . /opt/shops
RUN ls -lhtr /opt/shops
RUN gradle build --stacktrace
RUN ls -lhtr /opt/shops/build

FROM openjdk:8-jre-alpine as apps
RUN mkdir -p /apps
WORKDIR /apps
RUN cd /
COPY --from=compile /opt/shops/build/libs/shops-0.0.1-SNAPSHOT.jar /apps
EXPOSE 8083
ENTRYPOINT [ "java", "-jar", "/apps/shops-0.0.1-SNAPSHOT.jar"]