# Coding Conventions

**Analysis Date:** 2026-06-06

## Naming Patterns

**Files:**
- Use PascalCase file names for Kotlin singleton entrypoints and Java mixins, matching the declared class or `object`: `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`, `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.
- Keep mod metadata and mixin configuration names tied to the lowercase mod id `dongbeiyujie`: `src/main/resources/fabric.mod.json`, `src/main/resources/dongbeiyujie.mixins.json`, `src/client/resources/dongbeiyujie.client.mixins.json`.
- Keep asset paths under the lowercase mod id namespace: `src/main/resources/assets/dongbeiyujie/icon.png`.

**Functions:**
- Use Kotlin lower camelCase override methods for Fabric entrypoints: `onInitialize()` in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `onInitializeClient()` in `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `onInitializeDataGenerator(...)` in `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`.
- Use Java lower camelCase private injection handlers in mixins: `init(CallbackInfo info)` in `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java` and `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.
- Name new mixin handler methods by behavior when there is more than one injection point in a mixin; `init` is only clear for the single template injection in `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`.

**Variables:**
- Use lower camelCase for Kotlin and Java values: `logger` in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `fabricDataGenerator` in `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`, `projectName` and `version` in `build.gradle`.
- Keep Gradle project properties snake-like with underscores because they are consumed as `project.*` properties: `minecraft_version`, `yarn_mappings`, `loader_version`, `loom_version`, `fabric_kotlin_version`, `fabric_api_version` in `gradle.properties`.

**Types:**
- Use PascalCase for Kotlin `object` declarations and Java classes: `DongbeiYujie` in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `DongbeiYujieClient` in `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `ExampleMixin` in `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`.
- Keep package names lowercase and rooted at `com.columbina.yujie`: `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`.
- Put client-only types under the `.client` package and `src/client`: `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.

## Code Style

**Formatting:**
- No repository formatter is configured in `.prettierrc*`, `biome.json`, `ktlint*`, `detekt*`, `checkstyle*`, or `spotbugs*`; use IDE formatting conservatively and keep edits consistent with the target file paths listed below.
- Kotlin files use package declarations first, then imports, then a single top-level `object`: `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`.
- Java mixin files use package declarations first, then Minecraft/Sponge imports, then annotations immediately above the class and method: `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.
- Gradle build logic uses Groovy DSL blocks with tabs in existing block indentation: `build.gradle`.
- Resource JSON uses tab indentation and Fabric template field ordering: `src/main/resources/fabric.mod.json`, `src/main/resources/dongbeiyujie.mixins.json`, `src/client/resources/dongbeiyujie.client.mixins.json`.

**Linting:**
- No dedicated lint task or static-analysis plugin is configured; `build.gradle` defines compilation, resource processing, Loom remapping, sources JAR generation, and publication.
- Treat `./gradlew build` from `.github/workflows/build.yml` as the enforced quality gate for syntax, compilation, resource expansion, and packaging.
- Java compilation is fixed to release 21 in `build.gradle`; Kotlin bytecode is fixed to JVM 21 through `JvmTarget.JVM_21` in `build.gradle`.

## Import Organization

**Order:**
1. Package declaration at the top of every Kotlin and Java source file: `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`.
2. Framework or Minecraft imports after a blank line: `net.fabricmc.api.ModInitializer` in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `net.minecraft.server.MinecraftServer` in `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`.
3. Supporting library imports after framework imports when needed: `org.slf4j.LoggerFactory` in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, Sponge Mixin imports in `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`.

**Path Aliases:**
- No source path aliases are configured; use normal JVM packages rooted at `com.columbina.yujie` in `src/main/kotlin/com/columbina/yujie`, `src/client/kotlin/com/columbina/yujie/client`, `src/main/java/com/columbina/yujie`, and `src/client/java/com/columbina/yujie/client`.
- Fabric entrypoint class names are referenced by fully qualified string values in `src/main/resources/fabric.mod.json`; keep package moves synchronized with that file.
- Mixin class names are referenced relative to the configured mixin package in `src/main/resources/dongbeiyujie.mixins.json` and `src/client/resources/dongbeiyujie.client.mixins.json`; keep mixin file moves synchronized with those files.

## Error Handling

**Patterns:**
- No explicit `try`/`catch`, thrown exceptions, or custom error wrappers are present in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`, `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, or `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.
- Let Fabric/Loom surface initialization and mixin failures during startup or `./gradlew build`; do not swallow entrypoint or mixin exceptions in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt` or `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`.
- For new code that handles optional game state, prefer explicit guards and early returns in the relevant entrypoint or mixin file rather than broad exception handling; place server-side guards under `src/main/kotlin/com/columbina/yujie` or `src/main/java/com/columbina/yujie/mixin`, and client-side guards under `src/client/kotlin/com/columbina/yujie/client` or `src/client/java/com/columbina/yujie/client/mixin`.

## Logging

**Framework:** SLF4J through `org.slf4j.LoggerFactory`.

**Patterns:**
- Use a module-level private logger in Kotlin entrypoint objects, as shown by `private val logger = LoggerFactory.getLogger("dongbeiyujie")` in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`.
- Use the lowercase mod id string `dongbeiyujie` for logger names to match `src/main/resources/fabric.mod.json` and `gradle.properties`.
- Prefer `logger.info(...)` for lifecycle messages in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`; add warning or error levels only when a condition needs user or developer attention.
- Avoid `println` in mod code; no `println` usage is present in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, or the mixin sources.

## Comments

**When to Comment:**
- Keep short orienting comments around Fabric lifecycle hooks or mixin injection points when they clarify timing: `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.
- Remove template comments when replacing example behavior with concrete mod logic in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, and mixin sources.
- Keep Gradle comments only when they explain Fabric/Loom or publication behavior that affects future edits: `build.gradle`, `gradle.properties`.

**JSDoc/TSDoc:**
- Not applicable; there is no JavaScript or TypeScript source in the repository.
- JavaDoc and KDoc are not used in the current source files; add them only for public APIs that are consumed outside the file, not for Fabric-required overrides in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt` or `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`.

## Function Design

**Size:** Keep Fabric entrypoint override methods small and orchestration-focused, following `onInitialize()` in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `onInitializeClient()` in `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, and `onInitializeDataGenerator(...)` in `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`.

**Parameters:** Use Fabric or Sponge callback signatures exactly as required by the framework: `FabricDataGenerator` in `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`, `CallbackInfo` in `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java` and `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.

**Return Values:** Entrypoint and mixin injection methods return `Unit`/`void`; keep side effects explicit through Fabric registration APIs, logging, or injected Minecraft behavior in the relevant files under `src/main/kotlin`, `src/client/kotlin`, `src/main/java`, and `src/client/java`.

## Module Design

**Exports:** The mod exposes Fabric entrypoints through `src/main/resources/fabric.mod.json`; keep entrypoint values synchronized with Kotlin `object` names in `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, and `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`.

**Barrel Files:** Not applicable; there are no barrel modules. Add new JVM classes directly under the package path that matches their side and responsibility: server/common code in `src/main/kotlin/com/columbina/yujie` or `src/main/java/com/columbina/yujie`, client-only code in `src/client/kotlin/com/columbina/yujie/client` or `src/client/java/com/columbina/yujie/client`.

**Side Separation:** Keep common/server-safe mod initialization under `src/main` and client-only initialization, data generation entrypoints, and client mixins under `src/client`, matching `loom { splitEnvironmentSourceSets() }` in `build.gradle` and the `main`, `client`, and `fabric-datagen` entrypoint sections in `src/main/resources/fabric.mod.json`.

**Mixin Configuration:** Register common mixins in `src/main/resources/dongbeiyujie.mixins.json` with package `com.columbina.yujie.mixin`; register client mixins in `src/client/resources/dongbeiyujie.client.mixins.json` with package `com.columbina.yujie.client.mixin`.

---

*Convention analysis: 2026-06-06*
