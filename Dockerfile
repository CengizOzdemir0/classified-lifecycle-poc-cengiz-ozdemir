

FROM openjdk:11-jdk-slim
WORKDIR /app
COPY . /app

RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "/app/target/ilanApiProject-0.0.1-SNAPSHOT.jar"]
