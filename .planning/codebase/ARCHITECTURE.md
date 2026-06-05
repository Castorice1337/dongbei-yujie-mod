<!-- refreshed: 2026-06-06 -->
# Architecture

**Analysis Date:** 2026-06-06

## System Overview

```text
+-------------------------------------------------------------+
|                  Fabric Loader / Mod Metadata               |
|              `src/main/resources/fabric.mod.json`           |
+----------------------+----------------------+---------------+
| Main entrypoint      | Client entrypoint    | Datagen entry |
| `src/main/kotlin/...`| `src/client/kotlin`  | `src/client/kotlin` |
+----------+-----------+----------+-----------+-------+-------+
           |                      |                   |
           v                      v                   v
+-------------------------------------------------------------+
|                  Runtime Source Sets                         |
| `src/main` shared/server code + resources                    |
| `src/client` client-only code + client resources             |
+----------------------+----------------------+---------------+
| Main mixins          | Client mixins        | Assets        |
| `src/main/java/...`  | `src/client/java/...`| `src/main/resources/assets` |
+----------+-----------+----------+-----------+---------------+
           |                      |
           v                      v
+-------------------------------------------------------------+
|                Minecraft / Fabric Runtime                    |
| `net.minecraft.server.MinecraftServer`                       |
| `net.minecraft.client.MinecraftClient`                       |
+-------------------------------------------------------------+
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

**Overall:** Fabric mod with metadata-routed entrypoints, Loom split environment source sets, Kotlin singleton entrypoint objects, and Sponge Mixin injection hooks.

**Key Characteristics:**
- `build.gradle` enables `loom.splitEnvironmentSourceSets()` and registers both `sourceSets.main` and `sourceSets.client` as the `dongbeiyujie` mod (`build.gradle:20`).
- `src/main/resources/fabric.mod.json` is the runtime routing table for entrypoints and mixins (`src/main/resources/fabric.mod.json:17`).
- Kotlin `object` declarations are used for Fabric entrypoint singletons (`src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt:6`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt:5`).
- Java classes are used for mixins under dedicated `mixin` packages (`src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`).
- Main and client code are separated by source set, package path, resource path, and mixin descriptor environment.

## Layers

**Build and Packaging:**
- Purpose: Assemble the mod jar, remap Minecraft classes, compile Kotlin/Java to Java 21, expand `${version}` in metadata, and attach source artifacts.
- Location: `build.gradle`, `settings.gradle`, `gradle.properties`, `gradle/wrapper/gradle-wrapper.properties`
- Contains: Loom plugin setup, dependency coordinates, JVM targets, resource processing, jar packaging, publication configuration.
- Depends on: Gradle Wrapper, Fabric Loom, Kotlin JVM plugin, Fabric Maven repositories from `settings.gradle`.
- Used by: Local `gradlew` tasks and `.github/workflows/build.yml`.

**Metadata Routing:**
- Purpose: Tell Fabric Loader what mod this is, what dependencies it needs, what entrypoints to invoke, and which mixin configs to load.
- Location: `src/main/resources/fabric.mod.json`
- Contains: Mod id `dongbeiyujie`, metadata, dependency constraints, entrypoint class names, mixin config names, icon path.
- Depends on: Compiled classes from `src/main/kotlin` and `src/client/kotlin`, resources from `src/main/resources` and `src/client/resources`.
- Used by: Fabric Loader at runtime and Loom during resource processing.

**Shared/Main Runtime:**
- Purpose: Host code that can load in any environment, including dedicated server.
- Location: `src/main/kotlin/com/columbina/yujie`, `src/main/java/com/columbina/yujie`
- Contains: Main initializer object and shared/server mixin classes.
- Depends on: Fabric API, Fabric Loader API, Minecraft server classes, SLF4J.
- Used by: Fabric Loader main entrypoint and main mixin config.

**Client Runtime:**
- Purpose: Host code that must only load in a Minecraft client environment.
- Location: `src/client/kotlin/com/columbina/yujie/client`, `src/client/java/com/columbina/yujie/client`
- Contains: Client initializer, data generator entrypoint, client-only mixin classes.
- Depends on: Fabric client APIs, Fabric datagen APIs, Minecraft client classes.
- Used by: Fabric Loader client entrypoint, Fabric datagen entrypoint, and client mixin config.

**Resource and Asset Layer:**
- Purpose: Provide Fabric metadata, mixin configs, and mod assets included in the jar.
- Location: `src/main/resources`, `src/client/resources`
- Contains: `fabric.mod.json`, mixin descriptors, `assets/dongbeiyujie/icon.png`.
- Depends on: Matching class packages and names for registered entrypoints and mixins.
- Used by: Fabric Loader, Mixin, Minecraft resource loading, Loom resource processing.

**Generated/Runtime Output:**
- Purpose: Hold Gradle build outputs and local run output.
- Location: `build/`, `run/`
- Contains: Compiled/remapped artifacts, local run resources, transient development outputs.
- Depends on: Gradle tasks and Fabric Loom run tasks.
- Used by: Local development, CI artifact upload, and Minecraft run configurations.

## Data Flow

### Primary Runtime Load Path

1. Gradle compiles and packages the mod with Loom, including `sourceSets.main` and `sourceSets.client` (`build.gradle:20`).
2. `processResources` expands the Gradle `project.version` into `fabric.mod.json` (`build.gradle:49`).
3. Fabric Loader reads `src/main/resources/fabric.mod.json` from the jar and resolves dependencies (`src/main/resources/fabric.mod.json:44`).
4. Fabric Loader invokes `com.columbina.yujie.DongbeiYujie` through the `main` Kotlin adapter entrypoint (`src/main/resources/fabric.mod.json:18`).
5. `DongbeiYujie.onInitialize()` runs shared startup logic and writes an SLF4J log line (`src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt:9`).
6. Mixin loads `dongbeiyujie.mixins.json` and applies `ExampleMixin` to `MinecraftServer.loadWorld` (`src/main/resources/dongbeiyujie.mixins.json:5`, `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java:11`).

### Client Load Path

1. Fabric Loader sees the client entrypoint in `fabric.mod.json` (`src/main/resources/fabric.mod.json:24`).
2. Fabric Loader invokes `com.columbina.yujie.client.DongbeiYujieClient` through the Kotlin adapter (`src/main/resources/fabric.mod.json:26`).
3. `DongbeiYujieClient.onInitializeClient()` runs client-only setup (`src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt:6`).
4. Mixin loads `dongbeiyujie.client.mixins.json` only for the client environment (`src/main/resources/fabric.mod.json:39`).
5. `ExampleClientMixin` injects at the head of `MinecraftClient.run` (`src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java:11`).

### Data Generation Path

1. `fabricApi.configureDataGeneration { client = true }` enables client datagen in Loom (`build.gradle:32`).
2. Fabric datagen resolves the `fabric-datagen` entrypoint in `fabric.mod.json` (`src/main/resources/fabric.mod.json:30`).
3. `DongbeiYujieDataGenerator.onInitializeDataGenerator()` receives a `FabricDataGenerator` instance (`src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt:7`).
4. Datagen providers should be registered from `DongbeiYujieDataGenerator.kt`; no providers are registered in the repository.

**State Management:**
- No custom persistent state, configuration objects, registries, or capability-style stores are defined.
- The only module-level state is `private val logger` in `DongbeiYujie.kt` (`src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt:7`).
- Minecraft and Fabric own runtime lifecycle state; this repo currently participates through entrypoint callbacks and mixin hooks.

## Key Abstractions

**Fabric Entrypoint Object:**
- Purpose: Provide lifecycle callbacks that Fabric Loader invokes by class name from `fabric.mod.json`.
- Examples: `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`
- Pattern: Kotlin singleton object implements a Fabric entrypoint interface and overrides the lifecycle method.

**Mixin Class:**
- Purpose: Modify Minecraft runtime behavior by injecting code into targeted Minecraft classes and methods.
- Examples: `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`
- Pattern: Java class annotated with `@Mixin(TargetClass.class)` and private `@Inject` method receiving `CallbackInfo`.

**Mixin Descriptor:**
- Purpose: Bind a mixin package and class list to a runtime environment.
- Examples: `src/main/resources/dongbeiyujie.mixins.json`, `src/client/resources/dongbeiyujie.client.mixins.json`
- Pattern: JSON descriptor lists simple class names relative to `package`, with `compatibilityLevel` set to `JAVA_21`.

**Source Set Boundary:**
- Purpose: Keep dedicated-server-safe code apart from client-only code.
- Examples: `src/main`, `src/client`
- Pattern: `build.gradle` calls `splitEnvironmentSourceSets()` and registers both source sets in the same mod.

**Mod Resource Namespace:**
- Purpose: Keep assets under the mod id namespace.
- Examples: `src/main/resources/assets/dongbeiyujie/icon.png`
- Pattern: Resource paths use `assets/<modid>/...`, matching id `dongbeiyujie` from `fabric.mod.json`.

## Entry Points

**Main Mod Entrypoint:**
- Location: `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`
- Triggers: Fabric Loader `main` entrypoint from `src/main/resources/fabric.mod.json`
- Responsibilities: Register shared/server-safe systems, common events, blocks, items, networking handlers safe for both physical sides, and startup logging.

**Client Mod Entrypoint:**
- Location: `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`
- Triggers: Fabric Loader `client` entrypoint from `src/main/resources/fabric.mod.json`
- Responsibilities: Register client-only rendering, screens, HUD callbacks, keybindings, model predicates, client networking handlers, and client events.

**Data Generator Entrypoint:**
- Location: `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`
- Triggers: Fabric datagen `fabric-datagen` entrypoint from `src/main/resources/fabric.mod.json`
- Responsibilities: Register Fabric data providers for generated assets and data.

**Shared Mixin Entrypoint:**
- Location: `src/main/resources/dongbeiyujie.mixins.json`
- Triggers: Mixin subsystem after Fabric Loader loads the mod descriptor.
- Responsibilities: Register shared/server-safe mixin classes under `com.columbina.yujie.mixin`.

**Client Mixin Entrypoint:**
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

**What happens:** The architecture separates shared/server code in `src/main` from client code in `src/client`; placing `net.minecraft.client.*` imports in `src/main` violates this boundary.
**Why it's wrong:** Dedicated server loads can fail when shared classes reference client-only classes.
**Do this instead:** Put client-only initializers, renderers, keybindings, and client mixins under `src/client/kotlin/com/columbina/yujie/client` or `src/client/java/com/columbina/yujie/client/mixin`, following `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`.

### Unregistered Mixin Classes

**What happens:** A mixin class exists under a mixin package but is not listed in the matching descriptor.
**Why it's wrong:** Mixin does not apply classes just because they are on the classpath; the JSON descriptor is the registration source.
**Do this instead:** Add shared mixins to `src/main/resources/dongbeiyujie.mixins.json` and client mixins to `src/client/resources/dongbeiyujie.client.mixins.json`, matching the package pattern in `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java` and `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.

### Feature Logic Inside Placeholder Hooks

**What happens:** The repository contains example mixins and empty lifecycle callbacks, including `ExampleMixin`, `ExampleClientMixin`, and empty client/datagen entrypoint methods.
**Why it's wrong:** Adding unrelated feature behavior directly into placeholder classes makes responsibilities unclear and makes mixin targets harder to audit.
**Do this instead:** Rename or replace placeholder classes with feature-specific names and route registration from `DongbeiYujie.kt`, `DongbeiYujieClient.kt`, or `DongbeiYujieDataGenerator.kt`.

## Error Handling

**Strategy:** No custom error-handling layer is present.

**Patterns:**
- Entrypoint callbacks do not catch exceptions; failures propagate to Fabric/Minecraft startup.
- Mixin injection methods do not cancel or handle exceptions.
- Logging uses SLF4J through `LoggerFactory.getLogger("dongbeiyujie")` in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`.

## Cross-Cutting Concerns

**Logging:** Use SLF4J in shared runtime code, following `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`. Prefer logger names based on the mod id `dongbeiyujie`.

**Validation:** No project-level validation helpers are defined. Fabric dependency constraints live in `src/main/resources/fabric.mod.json`, and build/JVM compatibility is enforced by `build.gradle`.

**Authentication:** Not applicable. This is a Minecraft mod with no authentication provider or user identity layer.

**Configuration:** No mod configuration file or config API integration is present. Add configuration under a dedicated package in `src/main/kotlin/com/columbina/yujie` and register client-only config UI under `src/client` if needed.

**Registries:** No block, item, entity, screen, command, networking, or event registries are defined. Add shared registry code under `src/main/kotlin/com/columbina/yujie` and invoke it from `DongbeiYujie.onInitialize()`.

---

*Architecture analysis: 2026-06-06*
