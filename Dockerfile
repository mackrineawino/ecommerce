# Stage 1: Build the application
FROM maven:3.9.5 as build

WORKDIR /app

COPY . .

RUN mvn clean compile package

# Stage 2: Deploy the application to WildFly
FROM jboss/wildfly:latest AS deploy

COPY --from=build /app/target/ecommerce.war /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
