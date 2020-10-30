FROM openjdk:8u232-jre-slim-buster
VOLUME /app
COPY target/app.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-Duser.timezone=America/Sao_Paulo", "-jar","./app.jar"]
EXPOSE 8080