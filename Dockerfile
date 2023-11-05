FROM ubuntu:latest
LABEL authors="FEDI"
COPY targett/kaddem-0.0.1-SNAPSHOT.jar kaddem-docker.jar
ENTRYPOINT ["java", "-jar", "kaddem-docker.jar"]
