# Stage 1: Build the application
FROM maven:3.9.5 as build

WORKDIR /app

COPY pom.xml .

RUN mvn install

COPY . .

RUN mvn clean compile package

# Stage 2: Deploy the application to WildFly
FROM jboss/wildfly:latest AS deploy

# Remove the default standalone.xml file from WildFly
RUN rm /opt/jboss/wildfly/standalone/configuration/standalone.xml

COPY --from=build /app/target/ecommerce.war /opt/jboss/wildfly/standalone/deployments/

COPY --from=build /app/standalone.xml /opt/jboss/wildfly/standalone/configuration/

COPY --from=build /app/postgresql /opt/jboss/wildfly/modules/system/layers/base/com/

RUN mkdir -p /opt/jboss/wildfly/modules/system/layers/base/com/postgresql/main/ \
    && curl -o /opt/jboss/wildfly/modules/system/layers/base/com/postgresql/main/connector-java-42.2.5.jar \
    https://repo1.maven.org/maven2/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar



EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
