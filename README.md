# Edufy Media Service
### Media catalogue and metadata for the Edufy backend

Stores media entries and exposes REST endpoints for media details, artists and genres. User Service calls this service when recording plays and assembling listening history.

[Project overview](https://github.com/ErikEdman1996/Edufy_UserService_ErikE) · [API controller](src/main/java/org/example/edufy_mediaservice/controllers/MediaController.java)

## Features

- Create, read, update and delete media.
- Retrieve detailed media information.
- Filter by media type, genre or artist.
- Persist the catalogue with Spring Data JPA and MySQL.
- Validate Keycloak-issued JWTs through Spring Security.

**Stack:** Java 21, Spring Boot 3.5, Spring Data JPA, MySQL, Spring Security.

## Related services

| Repository | Responsibility | Local port |
| --- | --- | --- |
| [User Service](https://github.com/ErikEdman1996/Edufy_UserService_ErikE) | Users, Keycloak integration and play history | 9999 |
| [Media Service](https://github.com/ErikEdman1996/Edufy_MediaService_ErikE) | Media catalogue, artists and genres | 6666 |
| [Gateway](https://github.com/ErikEdman1996/Edufy_Gateway_ErikE) | Routing to User Service and Dummy Service | 4646 |
| [Dummy Service](https://github.com/ErikEdman1996/Edufy_DummyService_ErikE) | Service-to-service authentication example | 8888 |

## Local prerequisites

Use **JDK 21** and the included Maven Wrapper. Keycloak runs at `http://localhost:8080` with realm `Edufy_Realm`, client `Edufy_Client`, and the roles required by each service's security configuration.

The project spans separate repositories. A Keycloak realm export and a complete Compose stack are not included. Start infrastructure first, then Media Service and User Service, followed by Dummy Service and Gateway.

## Run locally

Create an empty MySQL database named `media_db`, then:

```bash
git clone https://github.com/ErikEdman1996/Edufy_MediaService_ErikE.git
cd Edufy_MediaService_ErikE
cp src/main/resources/application-local.properties.example src/main/resources/application-local.properties
# Fill in your database credentials.
bash mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

On PowerShell, use `Copy-Item` and replace `bash mvnw` with `.\mvnw.cmd`. The service starts on **http://localhost:6666**. The local example uses schema update and disables automatic seed loading. See [data.sql](src/main/resources/data.sql) for the included sample data.

## API highlights

Base path: `/edufy/v1/media`. Supply a Keycloak bearer token according to the [security configuration](src/main/java/org/example/edufy_mediaservice/configs/SecurityConfig.java).

| Method | Path | Purpose |
| --- | --- | --- |
| POST | base path | Create media |
| GET / PUT / DELETE | `/{id}` | Read, update or delete media |
| GET | `/{id}/details` | Retrieve media details |
| GET | `/by-type?type=...` | Filter by the media type enum |
| GET | `/by-genre?genre=...` | Filter by genre |
| GET | `/by-artist?artist=...` | Filter by artist |

## Build and test

```bash
bash mvnw test
bash mvnw package
```

The repository includes a Spring application-context test, which requires suitable database and security configuration. No dedicated media unit-test suite is currently included.

## Configuration notes

The default active profile is `dev`; use the explicit `local` profile above for the checked-in example. Production credentials are supplied through `EDUFY_MEDIA_DB_USERNAME` and `EDUFY_MEDIA_DB_PASSWORD`. The database URL and JWT issuer can also be overridden; see [application-prod.properties](src/main/resources/application-prod.properties).

The existing production profile recreates the schema and loads seed data at startup. Use disposable data until this behaviour has been deliberately changed. Previously committed database credentials must be rotated if they are still in use.

## Project context

Edufy is an educational microservices project from my Java developer training. These repositories demonstrate backend structure and integrations; they are not a packaged production platform.

**Erik Edman** · [GitHub](https://github.com/ErikEdman1996) · [LinkedIn](https://www.linkedin.com/in/erik-edman-b98a12395/)
