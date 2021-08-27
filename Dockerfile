FROM openjdk:11.0.4-jre
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "application.jar"]