FROM openjdk20
EXPOSE:8080
ADD target/kaddem-0.0.1-SNAPSHOT.jar kaddem-docker.jar
ENTRYPOINT ["java", "-jar", "kaddem-docker.jar"]