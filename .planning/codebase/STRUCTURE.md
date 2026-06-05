# Codebase Structure

**Analysis Date:** 2026-06-06

## Directory Layout

```text
dongbei-yujie-mod/
+-- .github/                         # GitHub Actions CI workflow
|   +-- workflows/
|       +-- build.yml                 # Gradle wrapper validation, build, artifact upload
+-- .gradle/                         # Local Gradle cache/output; generated
+-- .idea/                           # IntelliJ IDEA project metadata
+-- .planning/                       # GSD planning and codebase maps
|   +-- codebase/                     # Generated architecture/structure docs
+-- build/                           # Gradle build output; generated
+-- gradle/
|   +-- wrapper/                      # Gradle Wrapper jar and properties
+-- run/                             # Local Minecraft run directory; generated/runtime
|   +-- resources/
+-- src/
|   +-- main/
|   |   +-- java/                     # Shared/server Java sources, currently mixins
|   |   +-- kotlin/                   # Shared/server Kotlin sources and main entrypoint
|   |   +-- resources/                # Fabric metadata, shared mixins, assets
|   +-- client/
|       +-- java/                     # Client-only Java sources, currently mixins
|       +-- kotlin/                   # Client-only Kotlin entrypoints and datagen
|       +-- resources/                # Client-only mixin config
+-- build.gradle                      # Gradle build, Loom, Kotlin, Java, packaging
+-- gradle.properties                 # Minecraft/Fabric/mod version coordinates
+-- gradlew                           # Unix Gradle wrapper
+-- gradlew.bat                       # Windows Gradle wrapper
+-- LICENSE                           # CC0 license text
+-- README.md                         # Template setup notes
+-- settings.gradle                   # Plugin repositories and root project name
```

## Directory Purposes

**`.github/workflows`:**
- Purpose: CI automation for pull requests and pushes.
- Contains: GitHub Actions workflow YAML.
- Key files: `.github/workflows/build.yml`

**`.planning/codebase`:**
- Purpose: GSD codebase mapping output for future planning and execution agents.
- Contains: Markdown architecture, structure, stack, integration, convention, testing, and concern maps when generated.
- Key files: `.planning/codebase/ARCHITECTURE.md`, `.planning/codebase/STRUCTURE.md`

**`gradle/wrapper`:**
- Purpose: Pin and distribute the Gradle runtime used by `gradlew` and `gradlew.bat`.
- Contains: Wrapper jar and wrapper properties.
- Key files: `gradle/wrapper/gradle-wrapper.properties`, `gradle/wrapper/gradle-wrapper.jar`

**`src/main/kotlin/com/columbina/yujie`:**
- Purpose: Shared/server-safe Kotlin code loaded by the main Fabric entrypoint.
- Contains: Main mod initializer object.
- Key files: `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`

**`src/main/java/com/columbina/yujie/mixin`:**
- Purpose: Shared/server-safe Java mixin classes.
- Contains: Mixin classes listed in `src/main/resources/dongbeiyujie.mixins.json`.
- Key files: `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`

**`src/main/resources`:**
- Purpose: Resources packaged into the mod jar for all environments.
- Contains: Fabric mod descriptor, shared mixin descriptor, asset namespace.
- Key files: `src/main/resources/fabric.mod.json`, `src/main/resources/dongbeiyujie.mixins.json`, `src/main/resources/assets/dongbeiyujie/icon.png`

**`src/client/kotlin/com/columbina/yujie/client`:**
- Purpose: Client-only Kotlin code loaded only in client/datagen environments.
- Contains: Client initializer object and Fabric datagen entrypoint object.
- Key files: `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`

**`src/client/java/com/columbina/yujie/client/mixin`:**
- Purpose: Client-only Java mixin classes.
- Contains: Mixin classes listed in `src/client/resources/dongbeiyujie.client.mixins.json`.
- Key files: `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`

**`src/client/resources`:**
- Purpose: Client-only resources packaged through Loom's client source set.
- Contains: Client mixin descriptor.
- Key files: `src/client/resources/dongbeiyujie.client.mixins.json`

**`build`:**
- Purpose: Gradle output directory for compiled classes, remapped jars, reports, and packaged artifacts.
- Contains: Generated build products.
- Key files: `build/libs/` when `./gradlew build` succeeds.

**`run`:**
- Purpose: Local Fabric/Minecraft run directory used by IDE or Loom run tasks.
- Contains: Runtime resources and game-state files produced during local runs.
- Key files: `run/resources/`

## Key File Locations

**Entry Points:**
- `src/main/resources/fabric.mod.json`: Declares Fabric `main`, `client`, and `fabric-datagen` entrypoints.
- `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`: Main Fabric `ModInitializer`.
- `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`: Client Fabric `ClientModInitializer`.
- `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`: Fabric `DataGeneratorEntrypoint`.

**Configuration:**
- `build.gradle`: Loom, Kotlin, Java, resource processing, jar, and publication configuration.
- `settings.gradle`: Plugin repositories and root project name `dongbeiyujie`.
- `gradle.properties`: Minecraft/Fabric versions, mod version, Maven group, Gradle runtime flags.
- `gradle/wrapper/gradle-wrapper.properties`: Gradle Wrapper distribution URL.
- `.github/workflows/build.yml`: CI build configuration.

**Core Logic:**
- `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`: Shared startup logic and shared registry entry point.
- `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`: Client startup logic.
- `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`: Shared/server mixin hook.
- `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`: Client mixin hook.

**Resource Metadata:**
- `src/main/resources/fabric.mod.json`: Mod id, dependencies, entrypoints, mixin registration, icon path.
- `src/main/resources/dongbeiyujie.mixins.json`: Shared mixin package and class list.
- `src/client/resources/dongbeiyujie.client.mixins.json`: Client mixin package and class list.
- `src/main/resources/assets/dongbeiyujie/icon.png`: Mod icon asset.

**Testing:**
- Not detected. No `src/test`, `src/clientTest`, `*.test.*`, or `*.spec.*` files are present.
- CI runs `./gradlew build` in `.github/workflows/build.yml`.

## Naming Conventions

**Files:**
- Kotlin entrypoint files use PascalCase matching the singleton object name: `DongbeiYujie.kt`, `DongbeiYujieClient.kt`, `DongbeiYujieDataGenerator.kt`.
- Java mixin files use PascalCase class names ending in `Mixin`: `ExampleMixin.java`, `ExampleClientMixin.java`.
- Mixin descriptor files use lowercase mod id plus role suffix: `dongbeiyujie.mixins.json`, `dongbeiyujie.client.mixins.json`.
- Fabric metadata file stays exactly `fabric.mod.json`.
- Gradle configuration uses standard Gradle names: `build.gradle`, `settings.gradle`, `gradle.properties`.

**Directories:**
- Source-set roots follow Gradle/Fabric conventions: `src/main` and `src/client`.
- Kotlin package path mirrors package `com.columbina.yujie`: `src/main/kotlin/com/columbina/yujie`.
- Client package path adds `/client`: `src/client/kotlin/com/columbina/yujie/client`.
- Mixin packages live in explicit `mixin` directories: `src/main/java/com/columbina/yujie/mixin`, `src/client/java/com/columbina/yujie/client/mixin`.
- Asset namespace directory matches the mod id: `src/main/resources/assets/dongbeiyujie`.

## Where to Add New Code

**New Shared Feature:**
- Primary code: `src/main/kotlin/com/columbina/yujie`
- Java implementation if required by an API or mixin pattern: `src/main/java/com/columbina/yujie`
- Registration: Call shared registration from `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`
- Resources: `src/main/resources/assets/dongbeiyujie`
- Tests: Add a new `src/test/kotlin` or `src/test/java` tree if the project introduces test dependencies.

**New Client Feature:**
- Primary code: `src/client/kotlin/com/columbina/yujie/client`
- Java implementation if required by a mixin: `src/client/java/com/columbina/yujie/client`
- Registration: Call client registration from `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`
- Resources: Use `src/client/resources` for client-only descriptors and `src/main/resources/assets/dongbeiyujie` for normal Minecraft assets.

**New Shared Mixin:**
- Implementation: `src/main/java/com/columbina/yujie/mixin`
- Descriptor update: `src/main/resources/dongbeiyujie.mixins.json`
- Rule: Keep mixin class names feature-specific and ending in `Mixin`.

**New Client Mixin:**
- Implementation: `src/client/java/com/columbina/yujie/client/mixin`
- Descriptor update: `src/client/resources/dongbeiyujie.client.mixins.json`
- Rule: Keep client-only target classes out of `src/main`.

**New Data Generator Provider:**
- Primary code: `src/client/kotlin/com/columbina/yujie/client`
- Registration: `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`
- Generated output: Use Gradle/Fabric datagen output locations produced by the datagen task.

**Utilities:**
- Shared helpers: `src/main/kotlin/com/columbina/yujie`
- Client-only helpers: `src/client/kotlin/com/columbina/yujie/client`
- Mixin-specific helpers: Prefer regular source-set packages over putting helper logic inside `mixin` classes.

**Configuration:**
- Build/dependency changes: `build.gradle` and `gradle.properties`
- Mod metadata changes: `src/main/resources/fabric.mod.json`
- CI changes: `.github/workflows/build.yml`

## Special Directories

**`.gradle`:**
- Purpose: Gradle local cache and task state.
- Generated: Yes
- Committed: No

**`build`:**
- Purpose: Gradle build outputs, remapped artifacts, reports, and generated packaged files.
- Generated: Yes
- Committed: No

**`run`:**
- Purpose: Local Minecraft/Fabric runtime directory for development runs.
- Generated: Yes
- Committed: No

**`.idea`:**
- Purpose: IntelliJ IDEA project metadata and run configurations.
- Generated: Partly
- Committed: Yes, present in the repository.

**`gradle/wrapper`:**
- Purpose: Gradle Wrapper binary and distribution configuration.
- Generated: No
- Committed: Yes

**`.planning`:**
- Purpose: GSD project planning artifacts and generated codebase maps.
- Generated: Partly
- Committed: Project-dependent

---

*Structure analysis: 2026-06-06*
