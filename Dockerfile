FROM eclipse-temurin:17-jre-alpine
RUN apk add --no-cache curl
EXPOSE 8080
ADD ./target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
