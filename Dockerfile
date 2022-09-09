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