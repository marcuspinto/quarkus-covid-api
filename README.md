# covid-api project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using: `./mvnw quarkus:dev`

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `covid-api-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/covid-api-1.0.0-SNAPSHOT-runner.jar`.

## Running the application in Docker

After packaging the application

Build the image using `./docker build -f src/main/docker/Dockerfile.jvm -t quarkus/covid-api-jvm .`

Then run the container using: `./docker run -i --rm -p 8080:8080 quarkus/covid-api-jvm`

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/covid-api-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

## OpenAPI and Swagger documentation

OpenAPI using: `http://localhost:8080/openapi`

Swagger UI using: `http://localhost:8080/swagger-ui`

## How to use the Covid API

To register a new API Client POST to `http://localhost:8080/covid/register`

```sh
{
    "name" : "Governo do Estado de PE",
    "rateLimitPerMinute" : 10
}
```

Result:

```sh
{
    "apiKey": "8fe50dfeb85d380e7c9660c48f866acf96be",
    "createdAt": "2020-08-13T01:26:51.426Z[UTC]",
    "name": "Governo do Estado de PE",
    "rateLimitPerMinute": 10
}
```

To query data from the country GET to `http://localhost:8080/covid/general?api-key=8fe50dfeb85d380e7c9660c48f866acf96be`

To query data by state GET to `http://localhost:8080/covid/state?api-key=8fe50dfeb85d380e7c9660c48f866acf96be`

To query data by state region GET to `http://localhost:8080/covid/stateRegion?api-key=8fe50dfeb85d380e7c9660c48f866acf96be`