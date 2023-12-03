FROM amazoncorretto:17.0.9

WORKDIR /app

COPY target/webstore-auth-service-*.jar /app/webstore-auth-service.jar

CMD ["java", "-jar", "webstore-auth-service.jar"]