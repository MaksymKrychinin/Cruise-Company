FROM maven:3.8.3-openjdk-17

WORKDIR /Cruise-Company/cs-ses-beckend
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run -e