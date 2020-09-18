# eezer-java-backend

This is the code for the Eezer java backend application.

This application holds all the transports data and is the backend endpoint for the [eezer-app](https://github.com/eezer-admin/eezer-app)
as well as the data source for the [eezer-dashboard](https://github.com/eezer-admin/eezer-dashboard).

The service is build with Spring Boot 2 and is backed by a MongoDB database.

### How to run?

#### Requirements:

* JDK 1.8
* Maven
* Local mongodb installation is preferred (or use external url)

Build project with: `mvn clean install` from the command line.

Run the compiled jar from the `eezer-backend-service/target` folder with `java -jar eezer-backend-service-1.0-SNAPSHOT.jar`
or run with spring using `mvn spring-boot:run` from the `eezer-backend-service`-folder.

Happy coding!
