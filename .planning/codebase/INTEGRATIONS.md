# External Integrations

**Analysis Date:** 2026-06-06

## APIs & External Services

**Minecraft/Fabric Runtime APIs:**
- Minecraft game API - Used by mixins targeting `net.minecraft.server.MinecraftServer` in `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java` and `net.minecraft.client.MinecraftClient` in `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.
  - SDK/Client: `com.mojang:minecraft:${project.minecraft_version}` declared in `build.gradle`.
  - Auth: None in application code.
- Fabric Loader API - Used for mod lifecycle entrypoints in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt` and `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`.
  - SDK/Client: `net.fabricmc:fabric-loader:${project.loader_version}` declared in `build.gradle`.
  - Auth: None.
- Fabric API data generation - Used by `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`.
  - SDK/Client: `net.fabricmc.fabric-api:fabric-api:${project.fabric_api_version}` declared in `build.gradle`.
  - Auth: None.
- Fabric Language Kotlin adapter - Used by Kotlin entrypoints declared in `src/main/resources/fabric.mod.json`.
  - SDK/Client: `net.fabricmc:fabric-language-kotlin:${project.fabric_kotlin_version}` declared in `build.gradle`.
  - Auth: None.

**Build Artifact and Dependency Services:**
- Fabric Maven - Plugin repository configured in `settings.gradle`; Fabric artifacts are also resolved by Fabric Loom in `build.gradle`.
  - SDK/Client: Gradle Wrapper from `gradle/wrapper/gradle-wrapper.properties`.
  - Auth: None.
- Maven Central - Plugin repository configured in `settings.gradle`.
  - SDK/Client: Gradle Wrapper from `gradle/wrapper/gradle-wrapper.properties`.
  - Auth: None.
- Gradle Plugin Portal - Plugin repository configured in `settings.gradle`.
  - SDK/Client: Gradle Wrapper from `gradle/wrapper/gradle-wrapper.properties`.
  - Auth: None.
- Gradle Distribution Service - Wrapper downloads Gradle 9.4.1 from `services.gradle.org` as configured in `gradle/wrapper/gradle-wrapper.properties`.
  - SDK/Client: `gradlew` and `gradlew.bat`.
  - Auth: None.

**CI Services:**
- GitHub Actions - `.github/workflows/build.yml` runs on `push` and `pull_request`, builds with `./gradlew build`, and uploads `build/libs/`.
  - SDK/Client: Workflow actions `actions/checkout@v6`, `gradle/actions/wrapper-validation@v6`, `actions/setup-java@v5`, and `actions/upload-artifact@v7`.
  - Auth: GitHub Actions default repository token only; no explicit secret variables are referenced in `.github/workflows/build.yml`.

**Metadata Links:**
- Fabric homepage and Fabric example source metadata - `src/main/resources/fabric.mod.json` contains `contact.homepage` and `contact.sources` values for published mod metadata.
  - SDK/Client: Not applicable.
  - Auth: None.

## Data Storage

**Databases:**
- Not detected - No JDBC, SQLite, MySQL, PostgreSQL, Redis, ORM, or database client imports are present in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`, `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, or `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.
  - Connection: Not applicable.
  - Client: Not applicable.

**File Storage:**
- Local filesystem only - Gradle build outputs are written to `build/`, local game/dev runtime data is ignored under `run/`, and committed static mod assets live under `src/main/resources/assets/dongbeiyujie/`.

**Caching:**
- Gradle local cache only - `.gradle/` is ignored by `.gitignore`; no application cache service is detected.

## Authentication & Identity

**Auth Provider:**
- None detected.
  - Implementation: No auth provider dependencies, login flows, token handling, or identity callbacks are present in `build.gradle`, `gradle.properties`, `src/main/resources/fabric.mod.json`, or `src/**`.

## Monitoring & Observability

**Error Tracking:**
- None detected - No Sentry, Datadog, OpenTelemetry, Prometheus, or external error tracking dependencies are declared in `build.gradle`.

**Logs:**
- Minecraft/Fabric runtime logging through SLF4J - `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt` uses `org.slf4j.LoggerFactory` and logs from the main mod initializer.

## CI/CD & Deployment

**Hosting:**
- Not configured for deployment - `build.gradle` configures a `mavenJava` publication, but the publishing `repositories` block has no target repository.
- CI artifact storage - `.github/workflows/build.yml` uploads `build/libs/` as a GitHub Actions artifact named `Artifacts`.

**CI Pipeline:**
- GitHub Actions - `.github/workflows/build.yml` validates the wrapper, uses Microsoft JDK 25 for CI, runs `./gradlew build`, and captures build artifacts on `push` and `pull_request`.

## Environment Configuration

**Required env vars:**
- Not detected - No `.env` files are present at the repository root, and `.github/workflows/build.yml` does not reference custom `env` or `secrets` entries.

**Secrets location:**
- Not applicable - No secret files are present in the committed file scan, and no secret variables are referenced by `build.gradle`, `settings.gradle`, `gradle.properties`, or `.github/workflows/build.yml`.

## Webhooks & Callbacks

**Incoming:**
- None detected - No HTTP server, webhook endpoint, or callback route exists in `src/**`.

**Outgoing:**
- None detected in application code - External network use is limited to Gradle dependency/build services configured in `settings.gradle`, `build.gradle`, and `gradle/wrapper/gradle-wrapper.properties`.

---

*Integration audit: 2026-06-06*
