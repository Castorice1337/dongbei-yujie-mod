<!-- GSD:project-start source:PROJECT.md -->
## Project

**Dongbei Yujie Funny Mod**

Dongbei Yujie Funny Mod is a Fabric Minecraft 1.21.11 mod that turns the Dongbei Yujie meme concept into a playable joke mod. The first release focuses on a complete in-game loop: a paper-standee Dongbei Yujie entity, the Big Sweaty Foot item, the Daipai status effect and enchantment, controlled night spawning, drops, and placeholder resource hooks.

The mod is planned for public-release-safe packaging. Code registers the expected resource paths and behavior, while final PNG, voice, and BGM assets must be original, licensed, or supplied later in the final resource phase.

**Core Value:** Players should encounter Dongbei Yujie at night, fight or flee from her Daipai pressure, and obtain Big Sweaty Foot gear that makes the joke mechanically useful.

### Constraints

- **Tech stack**: Fabric/Kotlin on Minecraft 1.21.11 with Java 21 — fixed by `gradle.properties`, `build.gradle`, and `fabric.mod.json`.
- **Client/server split**: Paper billboard rendering and client audio handling must stay in `src/client`; shared registries, effects, items, entities, spawning, and gameplay logic belong in `src/main`.
- **Public release**: Do not include unlicensed PNG, voice, or BGM assets by default.
- **Performance**: Night spawning must limit nearby Yujie count and avoid annoying or laggy repeated spawns.
- **Chat noise**: Big Sweaty Foot hit messages are visible only to the attacker by default.
- **Balance**: V1 constants should be conservative and not require a config system.
<!-- GSD:project-end -->

<!-- GSD:stack-start source:codebase/STACK.md -->
## Technology Stack

## Languages
- Kotlin 2.4.0 - Main mod entrypoints and client/data generation entrypoints in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, and `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`; configured by `build.gradle`.
- Java 21 - Mixin classes in `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java` and `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`; `build.gradle` sets `options.release = 21`, `sourceCompatibility = JavaVersion.VERSION_21`, and `targetCompatibility = JavaVersion.VERSION_21`.
- JSON - Fabric and Mixin metadata in `src/main/resources/fabric.mod.json`, `src/main/resources/dongbeiyujie.mixins.json`, and `src/client/resources/dongbeiyujie.client.mixins.json`.
- YAML - GitHub Actions workflow in `.github/workflows/build.yml`.
- Groovy DSL - Gradle build configuration in `build.gradle` and `settings.gradle`.
## Runtime
- JVM 21+ - Required by `src/main/resources/fabric.mod.json` and Java/Kotlin compiler settings in `build.gradle`.
- Minecraft 1.21.11 - Declared by `minecraft_version=1.21.11` in `gradle.properties` and dependency metadata in `src/main/resources/fabric.mod.json`.
- Fabric Loader 0.19.3+ - Declared by `loader_version=0.19.3` in `gradle.properties` and `fabricloader >=0.19.3` in `src/main/resources/fabric.mod.json`.
- Fabric mod runtime - The mod id is `dongbeiyujie` in `src/main/resources/fabric.mod.json`; runtime entrypoints use Fabric Kotlin adapters.
- Gradle Wrapper 9.4.1 - Wrapper distribution is configured in `gradle/wrapper/gradle-wrapper.properties`; wrapper scripts are `gradlew` and `gradlew.bat`.
- Lockfile: missing - No `gradle.lockfile`, `*.lockfile`, or other package lockfile is present in the repo scan.
## Frameworks
- Fabric Loom Remap 1.16-SNAPSHOT - Gradle plugin `net.fabricmc.fabric-loom-remap` in `build.gradle`; handles Minecraft/Fabric development, source sets, remapping, and run tasks.
- Fabric API 0.141.4+1.21.11 - Mod API dependency in `build.gradle` and required dependency in `src/main/resources/fabric.mod.json`.
- Fabric Language Kotlin 1.13.12+kotlin.2.4.0 - Kotlin entrypoint adapter dependency in `build.gradle` and required dependency in `src/main/resources/fabric.mod.json`.
- SpongePowered Mixin - Mixin configuration files are `src/main/resources/dongbeiyujie.mixins.json` and `src/client/resources/dongbeiyujie.client.mixins.json`; mixin classes target `net.minecraft.server.MinecraftServer` and `net.minecraft.client.MinecraftClient`.
- Gradle test task - `.github/workflows/build.yml` runs `./gradlew build`, which includes configured Gradle verification tasks.
- Dedicated test framework: Not detected - No test dependencies, test source sets, `*.test.*`, or `*.spec.*` files are present in the committed source scan.
- Kotlin Gradle plugin 2.4.0 - Declared in `build.gradle`; compiler target is `JvmTarget.JVM_21`.
- Maven Publish plugin - Declared in `build.gradle`; publication `mavenJava` is configured, but no publishing repository is defined.
- Fabric data generation - Enabled by `fabricApi.configureDataGeneration { client = true }` in `build.gradle`; entrypoint is `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`.
- GitHub Actions - CI workflow `.github/workflows/build.yml` validates the Gradle wrapper, sets up Java, runs `./gradlew build`, and uploads `build/libs/` artifacts.
## Key Dependencies
- `com.mojang:minecraft:1.21.11` - Core game dependency declared in `build.gradle` using `minecraft_version` from `gradle.properties`.
- `net.fabricmc:yarn:1.21.11+build.6:v2` - Deobfuscation mappings declared in `build.gradle` using `yarn_mappings` from `gradle.properties`.
- `net.fabricmc:fabric-loader:0.19.3` - Mod loader dependency declared in `build.gradle` and `src/main/resources/fabric.mod.json`.
- `net.fabricmc.fabric-api:fabric-api:0.141.4+1.21.11` - Fabric API dependency declared in `build.gradle` and `src/main/resources/fabric.mod.json`.
- `net.fabricmc:fabric-language-kotlin:1.13.12+kotlin.2.4.0` - Required for Kotlin object entrypoints declared in `src/main/resources/fabric.mod.json`.
- `net.fabricmc.fabric-loom-remap:1.16-SNAPSHOT` - Build and remapping plugin declared in `build.gradle`.
- `org.jetbrains.kotlin.jvm:2.4.0` - Kotlin compiler plugin declared in `build.gradle`.
- `maven-publish` - Gradle publishing plugin declared in `build.gradle`.
- SLF4J runtime logger - `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt` imports `org.slf4j.LoggerFactory`; logging is provided by the Minecraft/Fabric runtime dependency graph.
## Configuration
- No `.env` files detected at the repository root; configuration is stored in Gradle properties and Fabric metadata.
- Project and dependency versions live in `gradle.properties`: `minecraft_version`, `yarn_mappings`, `loader_version`, `loom_version`, `fabric_kotlin_version`, `fabric_api_version`, `mod_version`, and `maven_group`.
- Mod identity, entrypoints, dependencies, icon, license, and contact metadata live in `src/main/resources/fabric.mod.json`.
- Common mixin package and injector defaults live in `src/main/resources/dongbeiyujie.mixins.json`.
- Client-only mixin package and injector defaults live in `src/client/resources/dongbeiyujie.client.mixins.json`.
- `settings.gradle` configures plugin repositories: Fabric Maven, Maven Central, and Gradle Plugin Portal.
- `build.gradle` configures plugins, dependencies, Fabric Loom source sets, resource version expansion, Java/Kotlin compiler targets, sources JAR generation, JAR license inclusion, data generation, and Maven publication.
- `gradle/wrapper/gradle-wrapper.properties` pins Gradle Wrapper 9.4.1.
- `.github/workflows/build.yml` configures CI build, wrapper validation, JDK setup, and artifact upload.
## Platform Requirements
- JDK 21+ for local builds because `build.gradle` targets Java 21 and `src/main/resources/fabric.mod.json` requires Java `>=21`.
- Gradle Wrapper scripts `gradlew` or `gradlew.bat`; no global Gradle installation is required.
- Network access to Fabric Maven, Maven Central, Gradle Plugin Portal, Gradle distribution services, and Minecraft/Fabric artifact repositories during dependency resolution.
- IntelliJ IDEA compatibility note: `gradle.properties` sets `org.gradle.configuration-cache=false`.
- Fabric-compatible Minecraft 1.21.11 environment with Java 21+.
- Required runtime mods are Fabric Loader `>=0.19.3`, Fabric API, and Fabric Language Kotlin as declared in `src/main/resources/fabric.mod.json`.
- Build output is a remapped mod JAR under `build/libs/` generated by `./gradlew build`; CI uploads this directory in `.github/workflows/build.yml`.
<!-- GSD:stack-end -->

<!-- GSD:conventions-start source:CONVENTIONS.md -->
## Conventions

## Naming Patterns
- Use PascalCase file names for Kotlin singleton entrypoints and Java mixins, matching the declared class or `object`: `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`, `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.
- Keep mod metadata and mixin configuration names tied to the lowercase mod id `dongbeiyujie`: `src/main/resources/fabric.mod.json`, `src/main/resources/dongbeiyujie.mixins.json`, `src/client/resources/dongbeiyujie.client.mixins.json`.
- Keep asset paths under the lowercase mod id namespace: `src/main/resources/assets/dongbeiyujie/icon.png`.
- Use Kotlin lower camelCase override methods for Fabric entrypoints: `onInitialize()` in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `onInitializeClient()` in `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `onInitializeDataGenerator(...)` in `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`.
- Use Java lower camelCase private injection handlers in mixins: `init(CallbackInfo info)` in `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java` and `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.
- Name new mixin handler methods by behavior when there is more than one injection point in a mixin; `init` is only clear for the single template injection in `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`.
- Use lower camelCase for Kotlin and Java values: `logger` in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `fabricDataGenerator` in `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`, `projectName` and `version` in `build.gradle`.
- Keep Gradle project properties snake-like with underscores because they are consumed as `project.*` properties: `minecraft_version`, `yarn_mappings`, `loader_version`, `loom_version`, `fabric_kotlin_version`, `fabric_api_version` in `gradle.properties`.
- Use PascalCase for Kotlin `object` declarations and Java classes: `DongbeiYujie` in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `DongbeiYujieClient` in `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `ExampleMixin` in `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`.
- Keep package names lowercase and rooted at `com.columbina.yujie`: `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`.
- Put client-only types under the `.client` package and `src/client`: `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.
## Code Style
- No repository formatter is configured in `.prettierrc*`, `biome.json`, `ktlint*`, `detekt*`, `checkstyle*`, or `spotbugs*`; use IDE formatting conservatively and keep edits consistent with the target file paths listed below.
- Kotlin files use package declarations first, then imports, then a single top-level `object`: `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`.
- Java mixin files use package declarations first, then Minecraft/Sponge imports, then annotations immediately above the class and method: `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.
- Gradle build logic uses Groovy DSL blocks with tabs in existing block indentation: `build.gradle`.
- Resource JSON uses tab indentation and Fabric template field ordering: `src/main/resources/fabric.mod.json`, `src/main/resources/dongbeiyujie.mixins.json`, `src/client/resources/dongbeiyujie.client.mixins.json`.
- No dedicated lint task or static-analysis plugin is configured; `build.gradle` defines compilation, resource processing, Loom remapping, sources JAR generation, and publication.
- Treat `./gradlew build` from `.github/workflows/build.yml` as the enforced quality gate for syntax, compilation, resource expansion, and packaging.
- Java compilation is fixed to release 21 in `build.gradle`; Kotlin bytecode is fixed to JVM 21 through `JvmTarget.JVM_21` in `build.gradle`.
## Import Organization
- No source path aliases are configured; use normal JVM packages rooted at `com.columbina.yujie` in `src/main/kotlin/com/columbina/yujie`, `src/client/kotlin/com/columbina/yujie/client`, `src/main/java/com/columbina/yujie`, and `src/client/java/com/columbina/yujie/client`.
- Fabric entrypoint class names are referenced by fully qualified string values in `src/main/resources/fabric.mod.json`; keep package moves synchronized with that file.
- Mixin class names are referenced relative to the configured mixin package in `src/main/resources/dongbeiyujie.mixins.json` and `src/client/resources/dongbeiyujie.client.mixins.json`; keep mixin file moves synchronized with those files.
## Error Handling
- No explicit `try`/`catch`, thrown exceptions, or custom error wrappers are present in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`, `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, or `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.
- Let Fabric/Loom surface initialization and mixin failures during startup or `./gradlew build`; do not swallow entrypoint or mixin exceptions in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt` or `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`.
- For new code that handles optional game state, prefer explicit guards and early returns in the relevant entrypoint or mixin file rather than broad exception handling; place server-side guards under `src/main/kotlin/com/columbina/yujie` or `src/main/java/com/columbina/yujie/mixin`, and client-side guards under `src/client/kotlin/com/columbina/yujie/client` or `src/client/java/com/columbina/yujie/client/mixin`.
## Logging
- Use a module-level private logger in Kotlin entrypoint objects, as shown by `private val logger = LoggerFactory.getLogger("dongbeiyujie")` in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`.
- Use the lowercase mod id string `dongbeiyujie` for logger names to match `src/main/resources/fabric.mod.json` and `gradle.properties`.
- Prefer `logger.info(...)` for lifecycle messages in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`; add warning or error levels only when a condition needs user or developer attention.
- Avoid `println` in mod code; no `println` usage is present in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, or the mixin sources.
## Comments
- Keep short orienting comments around Fabric lifecycle hooks or mixin injection points when they clarify timing: `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.
- Remove template comments when replacing example behavior with concrete mod logic in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, and mixin sources.
- Keep Gradle comments only when they explain Fabric/Loom or publication behavior that affects future edits: `build.gradle`, `gradle.properties`.
- Not applicable; there is no JavaScript or TypeScript source in the repository.
- JavaDoc and KDoc are not used in the current source files; add them only for public APIs that are consumed outside the file, not for Fabric-required overrides in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt` or `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`.
## Function Design
## Module Design
<!-- GSD:conventions-end -->

<!-- GSD:architecture-start source:ARCHITECTURE.md -->
## Architecture

## System Overview
```text
|                  Fabric Loader / Mod Metadata               |
|              `src/main/resources/fabric.mod.json`           |
| Main entrypoint      | Client entrypoint    | Datagen entry |
| `src/main/kotlin/...`| `src/client/kotlin`  | `src/client/kotlin` |
|                  Runtime Source Sets                         |
| `src/main` shared/server code + resources                    |
| `src/client` client-only code + client resources             |
| Main mixins          | Client mixins        | Assets        |
| `src/main/java/...`  | `src/client/java/...`| `src/main/resources/assets` |
|                Minecraft / Fabric Runtime                    |
| `net.minecraft.server.MinecraftServer`                       |
| `net.minecraft.client.MinecraftClient`                       |
```
## Component Responsibilities
| Component | Responsibility | File |
|-----------|----------------|------|
| Gradle/Loom build | Configures Fabric Loom, Kotlin JVM, Java 21 bytecode, split source sets, resource expansion, sources jar, and Maven publication. | `build.gradle` |
| Project coordinates | Supplies Minecraft, Yarn, Fabric Loader, Fabric API, Fabric Kotlin, mod version, and Maven group versions consumed by `build.gradle`. | `gradle.properties` |
| Mod descriptor | Declares mod id, version token, metadata, runtime dependencies, entrypoints, icon, and mixin configuration files. | `src/main/resources/fabric.mod.json` |
| Main initializer | Runs shared mod initialization through Fabric `ModInitializer`; owns shared startup logging. | `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt` |
| Client initializer | Runs client-only setup through Fabric `ClientModInitializer`; place rendering, keybind, HUD, screen, and client event registration here. | `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt` |
| Data generator entrypoint | Runs Fabric data generation; place generated model, blockstate, loot, recipe, tag, and language providers here. | `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt` |
| Shared/server mixin config | Registers mixins in package `com.columbina.yujie.mixin` for non-client runtime targets. | `src/main/resources/dongbeiyujie.mixins.json` |
| Shared/server mixin | Injects into `MinecraftServer.loadWorld` at method head. | `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java` |
| Client mixin config | Registers client-only mixins in package `com.columbina.yujie.client.mixin`. | `src/client/resources/dongbeiyujie.client.mixins.json` |
| Client mixin | Injects into `MinecraftClient.run` at method head. | `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java` |
| CI build | Validates Gradle wrapper, sets up JDK, runs `./gradlew build`, and uploads `build/libs/`. | `.github/workflows/build.yml` |
## Pattern Overview
- `build.gradle` enables `loom.splitEnvironmentSourceSets()` and registers both `sourceSets.main` and `sourceSets.client` as the `dongbeiyujie` mod (`build.gradle:20`).
- `src/main/resources/fabric.mod.json` is the runtime routing table for entrypoints and mixins (`src/main/resources/fabric.mod.json:17`).
- Kotlin `object` declarations are used for Fabric entrypoint singletons (`src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt:6`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt:5`).
- Java classes are used for mixins under dedicated `mixin` packages (`src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`).
- Main and client code are separated by source set, package path, resource path, and mixin descriptor environment.
## Layers
- Purpose: Assemble the mod jar, remap Minecraft classes, compile Kotlin/Java to Java 21, expand `${version}` in metadata, and attach source artifacts.
- Location: `build.gradle`, `settings.gradle`, `gradle.properties`, `gradle/wrapper/gradle-wrapper.properties`
- Contains: Loom plugin setup, dependency coordinates, JVM targets, resource processing, jar packaging, publication configuration.
- Depends on: Gradle Wrapper, Fabric Loom, Kotlin JVM plugin, Fabric Maven repositories from `settings.gradle`.
- Used by: Local `gradlew` tasks and `.github/workflows/build.yml`.
- Purpose: Tell Fabric Loader what mod this is, what dependencies it needs, what entrypoints to invoke, and which mixin configs to load.
- Location: `src/main/resources/fabric.mod.json`
- Contains: Mod id `dongbeiyujie`, metadata, dependency constraints, entrypoint class names, mixin config names, icon path.
- Depends on: Compiled classes from `src/main/kotlin` and `src/client/kotlin`, resources from `src/main/resources` and `src/client/resources`.
- Used by: Fabric Loader at runtime and Loom during resource processing.
- Purpose: Host code that can load in any environment, including dedicated server.
- Location: `src/main/kotlin/com/columbina/yujie`, `src/main/java/com/columbina/yujie`
- Contains: Main initializer object and shared/server mixin classes.
- Depends on: Fabric API, Fabric Loader API, Minecraft server classes, SLF4J.
- Used by: Fabric Loader main entrypoint and main mixin config.
- Purpose: Host code that must only load in a Minecraft client environment.
- Location: `src/client/kotlin/com/columbina/yujie/client`, `src/client/java/com/columbina/yujie/client`
- Contains: Client initializer, data generator entrypoint, client-only mixin classes.
- Depends on: Fabric client APIs, Fabric datagen APIs, Minecraft client classes.
- Used by: Fabric Loader client entrypoint, Fabric datagen entrypoint, and client mixin config.
- Purpose: Provide Fabric metadata, mixin configs, and mod assets included in the jar.
- Location: `src/main/resources`, `src/client/resources`
- Contains: `fabric.mod.json`, mixin descriptors, `assets/dongbeiyujie/icon.png`.
- Depends on: Matching class packages and names for registered entrypoints and mixins.
- Used by: Fabric Loader, Mixin, Minecraft resource loading, Loom resource processing.
- Purpose: Hold Gradle build outputs and local run output.
- Location: `build/`, `run/`
- Contains: Compiled/remapped artifacts, local run resources, transient development outputs.
- Depends on: Gradle tasks and Fabric Loom run tasks.
- Used by: Local development, CI artifact upload, and Minecraft run configurations.
## Data Flow
### Primary Runtime Load Path
### Client Load Path
### Data Generation Path
- No custom persistent state, configuration objects, registries, or capability-style stores are defined.
- The only module-level state is `private val logger` in `DongbeiYujie.kt` (`src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt:7`).
- Minecraft and Fabric own runtime lifecycle state; this repo currently participates through entrypoint callbacks and mixin hooks.
## Key Abstractions
- Purpose: Provide lifecycle callbacks that Fabric Loader invokes by class name from `fabric.mod.json`.
- Examples: `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`
- Pattern: Kotlin singleton object implements a Fabric entrypoint interface and overrides the lifecycle method.
- Purpose: Modify Minecraft runtime behavior by injecting code into targeted Minecraft classes and methods.
- Examples: `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`
- Pattern: Java class annotated with `@Mixin(TargetClass.class)` and private `@Inject` method receiving `CallbackInfo`.
- Purpose: Bind a mixin package and class list to a runtime environment.
- Examples: `src/main/resources/dongbeiyujie.mixins.json`, `src/client/resources/dongbeiyujie.client.mixins.json`
- Pattern: JSON descriptor lists simple class names relative to `package`, with `compatibilityLevel` set to `JAVA_21`.
- Purpose: Keep dedicated-server-safe code apart from client-only code.
- Examples: `src/main`, `src/client`
- Pattern: `build.gradle` calls `splitEnvironmentSourceSets()` and registers both source sets in the same mod.
- Purpose: Keep assets under the mod id namespace.
- Examples: `src/main/resources/assets/dongbeiyujie/icon.png`
- Pattern: Resource paths use `assets/<modid>/...`, matching id `dongbeiyujie` from `fabric.mod.json`.
## Entry Points
- Location: `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`
- Triggers: Fabric Loader `main` entrypoint from `src/main/resources/fabric.mod.json`
- Responsibilities: Register shared/server-safe systems, common events, blocks, items, networking handlers safe for both physical sides, and startup logging.
- Location: `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`
- Triggers: Fabric Loader `client` entrypoint from `src/main/resources/fabric.mod.json`
- Responsibilities: Register client-only rendering, screens, HUD callbacks, keybindings, model predicates, client networking handlers, and client events.
- Location: `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`
- Triggers: Fabric datagen `fabric-datagen` entrypoint from `src/main/resources/fabric.mod.json`
- Responsibilities: Register Fabric data providers for generated assets and data.
- Location: `src/main/resources/dongbeiyujie.mixins.json`
- Triggers: Mixin subsystem after Fabric Loader loads the mod descriptor.
- Responsibilities: Register shared/server-safe mixin classes under `com.columbina.yujie.mixin`.
- Location: `src/client/resources/dongbeiyujie.client.mixins.json`
- Triggers: Mixin subsystem only when `environment` is `client`.
- Responsibilities: Register client-only mixin classes under `com.columbina.yujie.client.mixin`.
## Architectural Constraints
- **Threading:** No custom threads or asynchronous workers are defined. Fabric invokes entrypoint callbacks on its lifecycle threads, and mixin injections run on the target Minecraft method call path.
- **Global state:** `DongbeiYujie` and `DongbeiYujieClient` are Kotlin singleton objects. `DongbeiYujie` owns a module-level SLF4J logger in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`.
- **Circular imports:** Not detected. No source file imports another project source file.
- **Environment boundary:** Code in `src/main` must not reference client-only Minecraft classes such as `net.minecraft.client.MinecraftClient`; client-only references belong under `src/client`.
- **Mixin registration:** Every mixin class must be listed in the matching JSON descriptor, and descriptor `package` values must match the Java package declarations.
- **JVM target:** Java and Kotlin source must compile for Java 21 as configured in `build.gradle`.
- **Resource namespace:** Assets must live under `src/main/resources/assets/dongbeiyujie` unless a new namespace is intentionally introduced in `fabric.mod.json`.
## Anti-Patterns
### Client APIs In Main Source Set
### Unregistered Mixin Classes
### Feature Logic Inside Placeholder Hooks
## Error Handling
- Entrypoint callbacks do not catch exceptions; failures propagate to Fabric/Minecraft startup.
- Mixin injection methods do not cancel or handle exceptions.
- Logging uses SLF4J through `LoggerFactory.getLogger("dongbeiyujie")` in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`.
## Cross-Cutting Concerns
<!-- GSD:architecture-end -->

<!-- GSD:skills-start source:skills/ -->
## Project Skills

No project skills found. Add skills to any of: `.claude/skills/`, `.agents/skills/`, `.cursor/skills/`, `.github/skills/`, or `.codex/skills/` with a `SKILL.md` index file.
<!-- GSD:skills-end -->

<!-- GSD:workflow-start source:GSD defaults -->
## GSD Workflow Enforcement

Before using Edit, Write, or other file-changing tools, start work through a GSD command so planning artifacts and execution context stay in sync.

Use these entry points:
- `/gsd-quick` for small fixes, doc updates, and ad-hoc tasks
- `/gsd-debug` for investigation and bug fixing
- `/gsd-execute-phase` for planned phase work

Do not make direct repo edits outside a GSD workflow unless the user explicitly asks to bypass it.
<!-- GSD:workflow-end -->



<!-- GSD:profile-start -->
## Developer Profile

> Profile not yet configured. Run `/gsd-profile-user` to generate your developer profile.
> This section is managed by `generate-claude-profile` -- do not edit manually.
<!-- GSD:profile-end -->
