FROM openjdk-20.0.2
LABEL authors="HOUDA"
ADD target/kaddem-0.0.1-SNAPSHOT.jar kaddem-docker.jar
ENTRYPOINT ["java", "-jar", "kaddem-docker.jar"]