# syntax=docker/dockerfile:1

################################################################################

# Create a stage for resolving and downloading dependencies.
FROM eclipse-temurin:17-jdk-jammy as deps

WORKDIR /build

# Copy the gradlew wrapper with executable permissions.
COPY ./gradlew ./gradlew
RUN chmod +x ./gradlew
RUN ls -l gradlew

COPY gradle/ gradle/
COPY build.gradle settings.gradle ./

# Download dependencies as a separate step to take advantage of Docker's caching.
# Leverage a cache mount to /root/.gradle so that subsequent builds don't have to
# re-download packages.
RUN --mount=type=cache,target=/root/.gradle ./gradlew dependencies --no-daemon

################################################################################

# Create a stage for building the application based on the stage with downloaded dependencies.
FROM deps as package

WORKDIR /build

COPY ./src src/
RUN --mount=type=cache,target=/root/.gradle \
    ./gradlew build -x test --no-daemon && \
    mv build/libs/$(./gradlew properties -q | grep "^name:" | awk '{print $2}')-$(./gradlew properties -q | grep "^version:" | awk '{print $2}').jar build/libs/app.jar

################################################################################

# Create a new stage for running the application that contains the minimal
# runtime dependencies for the application.
FROM eclipse-temurin:17-jre-jammy AS final

# Create a non-privileged user that the app will run under.
ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser
USER appuser

# Copy the executable from the "package" stage.
COPY --from=package /build/build/libs/app.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]
