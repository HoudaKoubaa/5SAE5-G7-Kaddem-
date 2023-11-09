FROM openjdk:20
LABEL authors="HOUDA"
ADD target/kaddem-1.0.jar kaddem-docker.jar
ENTRYPOINT ["java", "-jar", "kaddem-docker.jar"]