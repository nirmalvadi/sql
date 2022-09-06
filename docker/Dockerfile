####
# This Dockerfile is used in order to build a container that runs the Spring Boot application
#
# Build the image with:
#
# docker build -f docker/Dockerfile -t springboot/sample-demo .
#
# Then run the container using:
#
# docker run -i --rm -p 8081:8081 springboot/sample-demo
####
FROM quay.io/devfile/maven:3.8.1-openjdk-17-slim

WORKDIR /build

RUN pwd


COPY krb5.conf .

COPY krb5.ini .

COPY krb5.keytab .

COPY jaas.conf .

# Build dependency offline to streamline build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:11-jdk
RUN pwd


COPY --from=0 /build/target/demo-0.0.1-SNAPSHOT.jar /app/target/demo-0.0.1-SNAPSHOT.jar
COPY --from=0 /build/krb5.conf /etc/krb5.conf
COPY --from=0 /build/krb5.ini /etc/krb5.ini

COPY --from=0 /build/krb5.keytab /etc/krb5.keytab

COPY --from=0 /build/jaas.conf /etc/jaas.conf

EXPOSE 8081
ENTRYPOINT [ "java", "-Djava.security.auth.login.config=/etc/jaas.conf","-Djava.security.krb5.conf=/etc/krb5.ini","-Dsun.security.krb5.debug=true","-Djavax.security.auth.useSubjectCredsOnly=false","-Dsun.security.jgss.debug=true","-jar", "/app/target/demo-0.0.1-SNAPSHOT.jar", "--server.port=8081"  ]
