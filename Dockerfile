FROM openjdk
ADD target/*.jar /
EXPOSE 8000
ENTRYPOINT ["jdocava", "-jar", "Kaddem.jar" ]
