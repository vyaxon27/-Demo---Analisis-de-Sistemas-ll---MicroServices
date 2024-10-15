FROM ubuntu:latest

# Instala Java, telnet y netcat
RUN apt-get update && apt-get install -y openjdk-17-jdk telnet netcat

WORKDIR /app

# Copia el archivo JAR de tu aplicación
COPY build/libs/Analisis-Sistemas-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "Analisis-Sistemas-0.0.1-SNAPSHOT.jar"]
