# Vi hämtar en basimage från Docker Hub
# Detta är en färdig Linux-miljö med Java 21 installerat
FROM eclipse-temurin:21-jdk

# Sätter arbetskatalogen inne i containern
# Allt vi kopierar in hamnar här
WORKDIR /app

# Kopierar hela vårt projekt (Spring Boot + Maven wrapper + kod)
# in i containerns arbetskatalog
# copy . . betyder "kopiera allt från current folder på MIN DATOR till current folder I CONTAINER"
COPY . .

RUN chmod +x mvnw

# Install playwright to image + dependencies
RUN ./mvnw exec:java \
    -Dexec.mainClass=com.microsoft.playwright.CLI \
    -Dexec.args="install --with-deps"

# Bygger vår Spring Boot applikation
# - kompilerar kod
# - skapar en körbar JAR-fil
# - skippar tester i byggsteget
# ./mvnw = Maven Wrapper
# Kör Maven utan att Maven behöver vara installerat i systemet
# Säkerställer samma Maven-version i alla miljöer (lokalt, CI, Docker)
RUN ./mvnw clean package -DskipTests

# Informerar att applikationen använder port 8080
# (detta öppnar inte porten, det är bara metadata)
EXPOSE 8080

# Startar applikationen när containern körs
# Vi kör den färdiga JAR-filen (packaged Java application)
# java = vi kör en java-applikation
# -jar eeftersom spring boot skapar en jar-fil
# i python är den tex CMD ["python", "app.py"] ingen byggfil eftersom python är ett scriptspråk
CMD ["java", "-jar", "target/playwright-demo-0.0.1-SNAPSHOT.jar"]