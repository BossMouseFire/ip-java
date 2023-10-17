FROM openjdk:17-oracle
EXPOSE 8082
ADD build/libs/demo-0.0.1-SNAPSHOT.jar lab.jar
ENTRYPOINT ["java", "-jar", "/lab.jar"]