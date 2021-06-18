FROM openjdk:11.0.11-jre

WORKDIR /usr/app/
COPY target/live*.jar /usr/app/

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ls live*.jar |while read fn;do java -Dserver.port=8080 -jar $fn;done

EXPOSE 8080