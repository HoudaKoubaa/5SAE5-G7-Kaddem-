FROM openjdk:8-jdk-alpine

# Install wget (if not already installed)
RUN apk add --no-cache wget

# Define the URL of the Nexus repository where the JAR file is located
ARG NEXUS_URL=http://192.168.223.128:8081/repository/maven-releases/tn/esprit/spring/kaddem/1.0/kaddem-1.0.jar

# Download the JAR file from the Nexus repository
RUN wget $NEXUS_URL -O /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
