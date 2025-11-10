# Usa la imagen oficial de Java 21
FROM eclipse-temurin:21-jdk

# Crea un directorio de trabajo
WORKDIR /app

# Copia el JAR generado
COPY target/mutant-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto (ajusta si usas otro)
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]