# Golden Raspberry Awards API Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

First run postgres with docker
```shell script
cd docker/postgres/
docker-compose build
docker-compose run
```

Then you can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Swagger will be available at http://localhost:8080/q/swagger-ui/#/.

## Running the tests
```shell script
./mvnw test quarkus:test
```