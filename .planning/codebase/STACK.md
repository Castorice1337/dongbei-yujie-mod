# Technology Stack

**Analysis Date:** 2026-06-06

## Languages

**Primary:**
- Kotlin 2.4.0 - Main mod entrypoints and client/data generation entrypoints in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, and `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`; configured by `build.gradle`.
- Java 21 - Mixin classes in `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java` and `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`; `build.gradle` sets `options.release = 21`, `sourceCompatibility = JavaVersion.VERSION_21`, and `targetCompatibility = JavaVersion.VERSION_21`.

**Secondary:**
- JSON - Fabric and Mixin metadata in `src/main/resources/fabric.mod.json`, `src/main/resources/dongbeiyujie.mixins.json`, and `src/client/resources/dongbeiyujie.client.mixins.json`.
- YAML - GitHub Actions workflow in `.github/workflows/build.yml`.
- Groovy DSL - Gradle build configuration in `build.gradle` and `settings.gradle`.

## Runtime

**Environment:**
- JVM 21+ - Required by `src/main/resources/fabric.mod.json` and Java/Kotlin compiler settings in `build.gradle`.
- Minecraft 1.21.11 - Declared by `minecraft_version=1.21.11` in `gradle.properties` and dependency metadata in `src/main/resources/fabric.mod.json`.
- Fabric Loader 0.19.3+ - Declared by `loader_version=0.19.3` in `gradle.properties` and `fabricloader >=0.19.3` in `src/main/resources/fabric.mod.json`.
- Fabric mod runtime - The mod id is `dongbeiyujie` in `src/main/resources/fabric.mod.json`; runtime entrypoints use Fabric Kotlin adapters.

**Package Manager:**
- Gradle Wrapper 9.4.1 - Wrapper distribution is configured in `gradle/wrapper/gradle-wrapper.properties`; wrapper scripts are `gradlew` and `gradlew.bat`.
- Lockfile: missing - No `gradle.lockfile`, `*.lockfile`, or other package lockfile is present in the repo scan.

## Frameworks

**Core:**
- Fabric Loom Remap 1.16-SNAPSHOT - Gradle plugin `net.fabricmc.fabric-loom-remap` in `build.gradle`; handles Minecraft/Fabric development, source sets, remapping, and run tasks.
- Fabric API 0.141.4+1.21.11 - Mod API dependency in `build.gradle` and required dependency in `src/main/resources/fabric.mod.json`.
- Fabric Language Kotlin 1.13.12+kotlin.2.4.0 - Kotlin entrypoint adapter dependency in `build.gradle` and required dependency in `src/main/resources/fabric.mod.json`.
- SpongePowered Mixin - Mixin configuration files are `src/main/resources/dongbeiyujie.mixins.json` and `src/client/resources/dongbeiyujie.client.mixins.json`; mixin classes target `net.minecraft.server.MinecraftServer` and `net.minecraft.client.MinecraftClient`.

**Testing:**
- Gradle test task - `.github/workflows/build.yml` runs `./gradlew build`, which includes configured Gradle verification tasks.
- Dedicated test framework: Not detected - No test dependencies, test source sets, `*.test.*`, or `*.spec.*` files are present in the committed source scan.

**Build/Dev:**
- Kotlin Gradle plugin 2.4.0 - Declared in `build.gradle`; compiler target is `JvmTarget.JVM_21`.
- Maven Publish plugin - Declared in `build.gradle`; publication `mavenJava` is configured, but no publishing repository is defined.
- Fabric data generation - Enabled by `fabricApi.configureDataGeneration { client = true }` in `build.gradle`; entrypoint is `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`.
- GitHub Actions - CI workflow `.github/workflows/build.yml` validates the Gradle wrapper, sets up Java, runs `./gradlew build`, and uploads `build/libs/` artifacts.

## Key Dependencies

**Critical:**
- `com.mojang:minecraft:1.21.11` - Core game dependency declared in `build.gradle` using `minecraft_version` from `gradle.properties`.
- `net.fabricmc:yarn:1.21.11+build.6:v2` - Deobfuscation mappings declared in `build.gradle` using `yarn_mappings` from `gradle.properties`.
- `net.fabricmc:fabric-loader:0.19.3` - Mod loader dependency declared in `build.gradle` and `src/main/resources/fabric.mod.json`.
- `net.fabricmc.fabric-api:fabric-api:0.141.4+1.21.11` - Fabric API dependency declared in `build.gradle` and `src/main/resources/fabric.mod.json`.
- `net.fabricmc:fabric-language-kotlin:1.13.12+kotlin.2.4.0` - Required for Kotlin object entrypoints declared in `src/main/resources/fabric.mod.json`.

**Infrastructure:**
- `net.fabricmc.fabric-loom-remap:1.16-SNAPSHOT` - Build and remapping plugin declared in `build.gradle`.
- `org.jetbrains.kotlin.jvm:2.4.0` - Kotlin compiler plugin declared in `build.gradle`.
- `maven-publish` - Gradle publishing plugin declared in `build.gradle`.
- SLF4J runtime logger - `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt` imports `org.slf4j.LoggerFactory`; logging is provided by the Minecraft/Fabric runtime dependency graph.

## Configuration

**Environment:**
- No `.env` files detected at the repository root; configuration is stored in Gradle properties and Fabric metadata.
- Project and dependency versions live in `gradle.properties`: `minecraft_version`, `yarn_mappings`, `loader_version`, `loom_version`, `fabric_kotlin_version`, `fabric_api_version`, `mod_version`, and `maven_group`.
- Mod identity, entrypoints, dependencies, icon, license, and contact metadata live in `src/main/resources/fabric.mod.json`.
- Common mixin package and injector defaults live in `src/main/resources/dongbeiyujie.mixins.json`.
- Client-only mixin package and injector defaults live in `src/client/resources/dongbeiyujie.client.mixins.json`.

**Build:**
- `settings.gradle` configures plugin repositories: Fabric Maven, Maven Central, and Gradle Plugin Portal.
- `build.gradle` configures plugins, dependencies, Fabric Loom source sets, resource version expansion, Java/Kotlin compiler targets, sources JAR generation, JAR license inclusion, data generation, and Maven publication.
- `gradle/wrapper/gradle-wrapper.properties` pins Gradle Wrapper 9.4.1.
- `.github/workflows/build.yml` configures CI build, wrapper validation, JDK setup, and artifact upload.

## Platform Requirements

**Development:**
- JDK 21+ for local builds because `build.gradle` targets Java 21 and `src/main/resources/fabric.mod.json` requires Java `>=21`.
- Gradle Wrapper scripts `gradlew` or `gradlew.bat`; no global Gradle installation is required.
- Network access to Fabric Maven, Maven Central, Gradle Plugin Portal, Gradle distribution services, and Minecraft/Fabric artifact repositories during dependency resolution.
- IntelliJ IDEA compatibility note: `gradle.properties` sets `org.gradle.configuration-cache=false`.

**Production:**
- Fabric-compatible Minecraft 1.21.11 environment with Java 21+.
- Required runtime mods are Fabric Loader `>=0.19.3`, Fabric API, and Fabric Language Kotlin as declared in `src/main/resources/fabric.mod.json`.
- Build output is a remapped mod JAR under `build/libs/` generated by `./gradlew build`; CI uploads this directory in `.github/workflows/build.yml`.

---

*Stack analysis: 2026-06-06*
