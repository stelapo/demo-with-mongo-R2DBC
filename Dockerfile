FROM openjdk:17

COPY target/*.jar application.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "application.jar", "--spring.profiles.active=dev"]