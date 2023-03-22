# First Stage of Build Process: Spring Boot Jar Compilation with Gradle Build Engine
FROM gradle:jdk17-jammy AS build
COPY ./ /home/gradle
RUN gradle bootJar

# Second Stage of Build Process: Setup Slim Container with OpenJDK and Fat Jar File
FROM openjdk:17
COPY --from=build /home/gradle/build/libs/server.jar /usr/src/restaurantbuddyapi/
WORKDIR /usr/src/restaurantbuddyapi/
CMD ["java", "-jar", "server.jar"]
