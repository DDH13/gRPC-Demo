# Start with a base image containing Java runtime
FROM eclipse-temurin

# Add a volume pointing to /tmp
VOLUME /tmp


# Make port 6565 available to the world outside this container
EXPOSE 6565

# The application's jar file
ARG JAR_FILE=target/gRPCDemo-1.0-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} gRPCDemo-1.0-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gRPCDemo-1.0-SNAPSHOT.jar"]
