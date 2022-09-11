# Spring Boot "Microservice" Siret Project
This is a sample Java / Maven / Spring Boot (2.1.2.RELEASE) application that can be used as a starter for creating a microservice complete with built-in health check.

# How to Run
This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the java -jar command.

# Clone this repository
Make sure you are using JDK 1.8 and Maven 3.x
You can build the project and run the tests by running mvn clean package
- mvn spring-boot:run 

2022-09-11 14:30:15.009  INFO 7244 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-09-11 14:30:15.018  INFO 7244 --- [  restartedMain] com.example.sirene.SireneApplication     : Started SireneApplication in 4.683 seconds (JVM running for 6.315)

# About the Service
Each call of the bellow service, the data retuned will be inserted in a csv file called etablissement.csv wich will stored in a folder called : data-csv. 

- https://entreprise.data.gouv.fr/api/sirene/v3/etablissements/<VOTRE_SIRET>

## Here is what this little application demonstrates:

- Here is the endpoint you can call / SWAGGER URL : http://localhost:8080/swagger-ui.html

Get information about compagnie by SIRET NUMBER and save data into a csv file .
http://localhost:8080/compagnies/addCompagnieToCsv/31302979500017

 Response body
{
  "nic": "00017",
  "id": "484232781",
  "geo_adresse": "261 Chemin des Colles 06140 Vence",
  "date_creation": "1978-01-01T00:00:00.000+0000",
  "unite_legale": {
    "denomination": "SOC EXPL PEPINIERES GAUDISSART",
    "numero_tva_intra": "FR96313029795"
  }
}
 Response headers
 content-type: application/json;charset=UTF-8 
 date: Sun, 11 Sep 2022 15:59:08 GMT 

# About Spring Boot
Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, Spring Data, etc. Spring Boot comes with the Actuator module that gives the application the following endpoints helpful in monitoring and operating the service:

# Running the project with SWAGGER (Optional)  :
- http://localhost:8080/swagger-ui.html

# Deployment
- This project will be deployed in a docker container
- The structure of the dockerfile used in project is : 
FROM openjdk:8-jdk-alpine
RUN mkdir app	
RUN mkdir /app/data-csv
RUN mkdir /app/etc
COPY ./src/main/resources/application.properties /app/etc/	
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/sirene-project.jar
WORKDIR /app
ENTRYPOINT java -jar sirene-project.jar
EXPOSE 8080/tcp

# Build my image
- docker build -t sirene-project .

# Run image in docker container
- docker run -p 9090:8080  sirene-project:1.0
By default, the application will run using port 8080. Instead of running it in this port, we have the possibility to specify and work with any unused port. (ex. 9090)

# Test my microservice
- Once you send a REQUEST, the csv file will be generated in the folder data-csv as specified in dockerfile
- The location of the folder dara-csv is on : /app/data-csv (Path existed in container) 