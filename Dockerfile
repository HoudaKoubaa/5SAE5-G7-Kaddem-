FROM openjdk:20

# Define the URL of the Nexus repository where the JAR file is located
ARG NEXUS_URL=http://192.168.33.10:8081//repository/maven-releases/tn/esprit/spring/kaddem/1.0/kaddem-1.0.jar

RUN wget $NEXUS_URL -O /kaddem.jar

ENTRYPOINT ["java", "-jar", "/kaddem.jar"]