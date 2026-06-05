# Codebase Concerns

**Analysis Date:** 2026-06-06

## Tech Debt

**Template mod metadata:**
- Issue: The public metadata still contains Fabric example placeholders: example description, `Me!` author, Fabric homepage, Fabric example source URL, and CC0 template license.
- Files: `src/main/resources/fabric.mod.json`, `README.md`, `LICENSE`
- Impact: Released artifacts identify the mod incorrectly, send users to the wrong support/source location, and may publish under an unintended license.
- Fix approach: Replace `description`, `authors`, `contact`, and `license` in `src/main/resources/fabric.mod.json`; update `README.md`; keep `LICENSE` only if CC0 is the intended project license.

**Required no-op mixins:**
- Issue: `ExampleMixin` and `ExampleClientMixin` inject into Minecraft lifecycle methods but do no work, while both mixin configs mark the mixins as required with `defaultRequire: 1`.
- Files: `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`, `src/main/resources/dongbeiyujie.mixins.json`, `src/client/resources/dongbeiyujie.client.mixins.json`
- Impact: The mod takes on mixin launch risk and injection overhead without behavior. If target method names/signatures change for a Minecraft/Yarn version, startup can fail because the configs require successful injection.
- Fix approach: Remove the example mixin classes and config entries until a real injection is needed. When a mixin is added, target the narrowest method, document the purpose in the class name, and keep `defaultRequire` strict only for injections that are required for correctness.

**Empty feature entrypoints:**
- Issue: Entrypoints contain only placeholder code: `DongbeiYujie` logs `Hello Fabric world!`, `DongbeiYujieClient` has no client setup, and `DongbeiYujieDataGenerator` registers no providers.
- Files: `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`, `src/main/resources/fabric.mod.json`
- Impact: A built jar loads as a mod but provides no domain behavior, data generation output, or client functionality.
- Fix approach: Add real initialization behind these entrypoints or remove unused entrypoints from `src/main/resources/fabric.mod.json` until they are needed.

**Unstable dependency and metadata constraints:**
- Issue: `loom_version=1.16-SNAPSHOT` uses a mutable snapshot plugin, while mod runtime dependencies in `fabric.mod.json` allow any Fabric API and Fabric Kotlin version through `*`.
- Files: `gradle.properties`, `build.gradle`, `src/main/resources/fabric.mod.json`
- Impact: Builds and runtime compatibility can change without a source change, making regressions harder to reproduce.
- Fix approach: Pin Loom to a stable release when available, align `fabric-api` and `fabric-language-kotlin` bounds in `src/main/resources/fabric.mod.json` with the tested versions in `gradle.properties`, and use dependency locking or verification metadata for reproducible builds.

**Placeholder publishing configuration:**
- Issue: `publishing` defines a `mavenJava` publication but has no repository, artifact metadata, signing, or release workflow.
- Files: `build.gradle`
- Impact: Publication behavior is incomplete and can produce jars that lack release metadata expectations for a real distribution channel.
- Fix approach: Add explicit publication repositories, generated POM metadata, and signing only when release publishing is required; otherwise remove the placeholder block.

## Known Bugs

**Published metadata points to the wrong project:**
- Symptoms: Mod metadata reports the homepage as Fabric and the source repository as `https://github.com/FabricMC/fabric-example-mod`.
- Files: `src/main/resources/fabric.mod.json`
- Trigger: Any packaged jar or mod listing that reads `fabric.mod.json`.
- Workaround: Treat `src/main/resources/fabric.mod.json` as non-release-ready until `contact` and descriptive fields are replaced.

**Required sample mixins can break startup across mappings/runtime updates:**
- Symptoms: Minecraft launch can fail during mixin application if `MinecraftServer.loadWorld` or `MinecraftClient.run` cannot be resolved with the expected descriptor.
- Files: `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`, `src/main/resources/dongbeiyujie.mixins.json`, `src/client/resources/dongbeiyujie.client.mixins.json`
- Trigger: Running against a Minecraft/Yarn/Fabric combination where the target methods differ from the source assumptions.
- Workaround: Remove these example mixins or mark them non-required only if they are intentionally optional.

**Data generation entrypoint produces no data:**
- Symptoms: Invoking Fabric data generation runs `DongbeiYujieDataGenerator` but registers no providers.
- Files: `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`, `src/main/resources/fabric.mod.json`
- Trigger: Running the configured Fabric datagen task.
- Workaround: Remove the `fabric-datagen` entrypoint until providers exist, or add providers for generated assets/data.

## Security Considerations

**CI actions are pinned to major tags:**
- Risk: GitHub Actions dependencies are referenced as floating major tags, so upstream action changes affect CI without a repo change.
- Files: `.github/workflows/build.yml`
- Current mitigation: `gradle/actions/wrapper-validation@v6` validates the wrapper before build.
- Recommendations: Pin `actions/checkout`, `gradle/actions/wrapper-validation`, `actions/setup-java`, and `actions/upload-artifact` to immutable SHAs for supply-chain-sensitive releases.

**Dependency verification is not configured:**
- Risk: Gradle downloads Minecraft/Fabric/Kotlin artifacts without committed verification metadata.
- Files: `build.gradle`, `gradle.properties`, `gradle/wrapper/gradle-wrapper.properties`
- Current mitigation: Gradle wrapper validation exists in `.github/workflows/build.yml`; dependency artifact verification is not detected.
- Recommendations: Add Gradle dependency verification metadata and consider dependency locking for Fabric/Kotlin toolchain artifacts.

**No secret-bearing files detected in source tree scan:**
- Risk: Not detected.
- Files: `.gitignore`
- Current mitigation: `.gitignore` excludes build outputs, IDE files, and runtime directories.
- Recommendations: Keep `.env`, credentials, private keys, and generated crash dumps out of the repository; extend `.gitignore` before adding tools that create secret-bearing files.

## Performance Bottlenecks

**Mixin injection into hot/lifecycle methods with no behavior:**
- Problem: `ExampleClientMixin` injects at the head of `MinecraftClient.run`, and `ExampleMixin` injects at the head of `MinecraftServer.loadWorld`, but both handlers are empty.
- Files: `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`, `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`
- Cause: Template mixins remain registered through required mixin configs.
- Improvement path: Delete no-op mixins. Add mixins only when they perform specific work that cannot be handled through Fabric events or APIs.

**CI runs full build on every push and pull request only:**
- Problem: Build coverage is coarse and does not isolate lint/test/datagen steps.
- Files: `.github/workflows/build.yml`, `build.gradle`
- Cause: The workflow has a single `./gradlew build` command and no configured static analysis or test tasks beyond Gradle defaults.
- Improvement path: Add explicit `check`, test, datagen validation, and static analysis tasks once those tools exist so failures identify the slow or broken stage directly.

## Fragile Areas

**Mixin target contracts:**
- Files: `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`, `src/main/resources/dongbeiyujie.mixins.json`, `src/client/resources/dongbeiyujie.client.mixins.json`
- Why fragile: Required mixins depend on obfuscated-game method mappings and descriptors, which are sensitive to Minecraft and Yarn updates.
- Safe modification: Prefer Fabric API events where possible. For required mixins, keep one responsibility per mixin, include a precise target reason in the class name/comment, and verify against the exact Minecraft/Yarn version in `gradle.properties`.
- Test coverage: No automated launch, mixin application, or integration tests are detected.

**Version alignment between Gradle and mod metadata:**
- Files: `gradle.properties`, `build.gradle`, `src/main/resources/fabric.mod.json`
- Why fragile: Build dependencies are pinned in `gradle.properties`, while mod runtime dependency constraints use broad or wildcard bounds in `fabric.mod.json`.
- Safe modification: Update `minecraft_version`, `yarn_mappings`, `loader_version`, `fabric_api_version`, and `fabric_kotlin_version` together; mirror tested minimums in `fabric.mod.json`.
- Test coverage: No compatibility matrix or version-upgrade tests are detected.

**Client/main source split:**
- Files: `build.gradle`, `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`
- Why fragile: `splitEnvironmentSourceSets()` separates client-only code from common code. Accidentally importing client classes from `src/main` can break dedicated server runtime.
- Safe modification: Keep `net.minecraft.client.*` imports under `src/client`; keep common initialization in `src/main`; use the `client` entrypoint for rendering and client-only registrations.
- Test coverage: No dedicated-server launch test is detected.

## Scaling Limits

**Feature surface has no modular boundaries yet:**
- Current capacity: One main initializer, one client initializer, one data generator, and two mixins.
- Limit: Adding registries, config, networking, items, blocks, data providers, and rendering directly into the entrypoint objects will quickly create hard-to-test initialization code.
- Scaling path: Introduce feature-specific Kotlin objects/packages under `src/main/kotlin/com/columbina/yujie/` and client-only packages under `src/client/kotlin/com/columbina/yujie/client/`; keep entrypoints as orchestration only.

**Data generation has no provider structure:**
- Current capacity: `DongbeiYujieDataGenerator` accepts `FabricDataGenerator` but registers nothing.
- Limit: Generated recipes, loot tables, tags, models, language files, and blockstates have no established location or naming pattern.
- Scaling path: Add provider classes under `src/client/kotlin/com/columbina/yujie/client/datagen/` and register them from `DongbeiYujieDataGenerator`.

## Dependencies at Risk

**`net.fabricmc.fabric-loom-remap` / `loom_version=1.16-SNAPSHOT`:**
- Risk: Snapshot plugin coordinates can change behavior or availability.
- Impact: CI and local builds can fail or produce different remapping behavior without source edits.
- Migration plan: Use a stable Fabric Loom plugin coordinate/version when available and document the tested Gradle version in `gradle/wrapper/gradle-wrapper.properties`.

**`fabric-api` and `fabric-language-kotlin` wildcard runtime constraints:**
- Risk: Runtime metadata accepts any installed version even though compile-time versions are specific.
- Impact: Users can launch with an older or incompatible dependency version and hit class/method linkage errors.
- Migration plan: Set explicit lower bounds in `src/main/resources/fabric.mod.json` based on `fabric_api_version` and `fabric_kotlin_version` in `gradle.properties`.

**CI JDK version differs from compile target:**
- Risk: CI installs Java 25 while source and Kotlin targets are Java 21.
- Impact: Build behavior can differ from the minimum runtime required by `fabric.mod.json`, especially for toolchain, Gradle, or plugin compatibility.
- Migration plan: Use Java 21 in `.github/workflows/build.yml` unless Java 25 is intentionally required for Gradle tooling; keep `tasks.withType(JavaCompile)`, Kotlin `jvmTarget`, and CI Java aligned.

## Missing Critical Features

**No implemented mod behavior:**
- Problem: The codebase contains entrypoint and mixin scaffolding but no gameplay, registration, config, networking, assets beyond `icon.png`, or data providers.
- Blocks: Functional UAT, gameplay validation, release readiness, and user-facing documentation.

**No automated tests or launch verification:**
- Problem: No test source sets, `testImplementation` dependencies, unit tests, game tests, or launch smoke tests are detected.
- Blocks: Safe changes to mixins, Minecraft version upgrades, and compatibility verification.

**No linting/static analysis configuration:**
- Problem: No ktlint, detekt, Spotless, Checkstyle, PMD, or equivalent configuration is detected.
- Blocks: Enforcing Kotlin/Java style, catching dead placeholder code, and detecting accidental client imports in common code.

## Test Coverage Gaps

**Entrypoint initialization:**
- What's not tested: `DongbeiYujie.onInitialize`, `DongbeiYujieClient.onInitializeClient`, and `DongbeiYujieDataGenerator.onInitializeDataGenerator`.
- Files: `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`
- Risk: Initialization regressions, missing provider registrations, and client/server environment mistakes can ship unnoticed.
- Priority: High once real feature code is added.

**Mixin application:**
- What's not tested: Whether registered mixins apply successfully to the configured Minecraft/Yarn version.
- Files: `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`, `src/main/resources/dongbeiyujie.mixins.json`, `src/client/resources/dongbeiyujie.client.mixins.json`
- Risk: Startup crashes can appear only during game launch, not during simple compilation.
- Priority: High while required mixins are present.

**Build metadata and release artifact correctness:**
- What's not tested: Expanded `fabric.mod.json` version, contact fields, dependency bounds, jar contents, and published artifact metadata.
- Files: `src/main/resources/fabric.mod.json`, `build.gradle`, `gradle.properties`, `.github/workflows/build.yml`
- Risk: Invalid or misleading jars can pass compilation and CI.
- Priority: Medium.

**Server/client environment separation:**
- What's not tested: Dedicated-server launch compatibility and absence of client-only imports in `src/main`.
- Files: `build.gradle`, `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`
- Risk: Dedicated servers can fail at runtime even when the client build works.
- Priority: Medium.

---

*Concerns audit: 2026-06-06*
