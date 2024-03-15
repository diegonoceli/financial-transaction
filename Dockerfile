# Use a imagem do OpenJDK como base
FROM openjdk:11-jre-slim

# Copie o JAR da aplicação para dentro do contêiner
COPY target/consultation-service.jar /app/consultation-service.jar

# Defina o comando para executar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "/app/consultation-service.jar"]
