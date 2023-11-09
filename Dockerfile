FROM openjdk:17
LABEL authors="FEDI"
ADD target/kaddem-1.0.jar kaddem-docker.jar
ENTRYPOINT ["java", "-jar", "kaddem-docker.jar"]
