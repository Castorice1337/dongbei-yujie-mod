# Testing Patterns

**Analysis Date:** 2026-06-06

## Test Framework

**Runner:**
- No dedicated unit test framework is configured in `build.gradle`; there are no `testImplementation`, JUnit, Kotest, MockK, Mockito, or Fabric GameTest dependencies.
- Gradle is the verification runner through the Java/Kotlin/Fabric Loom build lifecycle in `build.gradle`.
- CI runs `./gradlew build` on push and pull request in `.github/workflows/build.yml`.
- Config: `build.gradle`, `gradle.properties`, `settings.gradle`, `gradle/wrapper/gradle-wrapper.properties`, `.github/workflows/build.yml`.

**Assertion Library:**
- Not detected; no assertion dependency or test source files are present under `src`.

**Run Commands:**
```bash
./gradlew build              # Run the configured CI quality gate on Unix-like systems; used by `.github/workflows/build.yml`
.\gradlew.bat build          # Run the configured quality gate on Windows from the repository root
# Watch mode: Not detected
# Coverage: Not detected
```

## Test File Organization

**Location:**
- No test directories are present; `src/main` contains common/server code and resources, and `src/client` contains client-only code and resources.
- Add common unit tests under `src/test/kotlin` or `src/test/java` only after adding matching test dependencies in `build.gradle`.
- Add client or game-environment tests in the Fabric-supported source set required by the chosen test framework; keep the source-set declaration explicit in `build.gradle`.

**Naming:**
- No repository test naming pattern is established.
- Use JVM test names that mirror the class under test when introducing tests, such as `DongbeiYujieTest.kt` for `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt` or `ExampleMixinTest.java` for `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java`.

**Structure:**
```text
src/
├── main/
│   ├── kotlin/com/columbina/yujie/            # Common Kotlin mod entrypoint code
│   ├── java/com/columbina/yujie/mixin/        # Common Java mixins
│   └── resources/                             # Fabric metadata, common mixin config, assets
└── client/
    ├── kotlin/com/columbina/yujie/client/     # Client Kotlin entrypoint and datagen code
    ├── java/com/columbina/yujie/client/mixin/ # Client Java mixins
    └── resources/                             # Client mixin config
```

## Test Structure

**Suite Organization:**
```kotlin
// No test suite exists in the repository.
// When introduced, keep tests close to the package under test:
// src/test/kotlin/com/columbina/yujie/DongbeiYujieTest.kt
```

**Patterns:**
- Setup pattern: Not detected; no `@BeforeEach`, fixture builders, temporary directories, or Fabric test bootstrap code are present.
- Teardown pattern: Not detected; no `@AfterEach`, cleanup hooks, or mock reset patterns are present.
- Assertion pattern: Not detected; no `assert*`, Kotest matcher, JUnit assertion, or fluent assertion usage is present.
- Build verification pattern: `./gradlew build` compiles Java with release 21, compiles Kotlin to JVM 21, processes `src/main/resources/fabric.mod.json`, remaps outputs, and assembles artifacts according to `build.gradle`.

## Mocking

**Framework:** Not detected.

**Patterns:**
```kotlin
// No mocking pattern exists in the repository.
// Avoid mocking Fabric or Minecraft internals until a concrete test framework is added in `build.gradle`.
```

**What to Mock:**
- Not established by current tests.
- For new unit tests, mock only narrow collaborators owned by this mod after those collaborators exist under `src/main/kotlin/com/columbina/yujie` or `src/client/kotlin/com/columbina/yujie/client`.

**What NOT to Mock:**
- Do not mock Sponge Mixin annotations or callback signatures in isolation; validate mixin declarations through compilation and, when needed, Fabric-compatible runtime or GameTest coverage tied to `src/main/java/com/columbina/yujie/mixin/ExampleMixin.java` and `src/client/java/com/columbina/yujie/client/mixin/ExampleClientMixin.java`.
- Do not mock `fabric.mod.json` entrypoint strings; keep them synchronized with actual classes in `src/main/resources/fabric.mod.json`, `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`, `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`, and `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`.

## Fixtures and Factories

**Test Data:**
```kotlin
// No fixtures or factories are present.
// Build fixture helpers only after production classes need repeatable inputs.
```

**Location:**
- Not detected; there is no `src/test`, `fixtures`, `testdata`, or `resources` test fixture directory.
- Put future JVM test resources under `src/test/resources` if a standard Gradle test source set is added in `build.gradle`.

## Coverage

**Requirements:** None enforced; `build.gradle` does not configure JaCoCo, Kover, coverage thresholds, or coverage reports.

**View Coverage:**
```bash
# Not available until a coverage plugin such as JaCoCo or Kover is added to `build.gradle`.
```

## Test Types

**Unit Tests:**
- Not present; no `src/test/kotlin`, `src/test/java`, or unit test dependencies are configured in `build.gradle`.
- Use unit tests for pure mod-owned logic once logic is extracted from Fabric entrypoints such as `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt` or client classes under `src/client/kotlin/com/columbina/yujie/client`.

**Integration Tests:**
- Not present; no integration test source set, Fabric GameTest dependency, or custom Gradle integration task is configured in `build.gradle`.
- Use integration tests for behavior that needs Fabric Loader, Minecraft classes, resource metadata, or mixin application involving `src/main/resources/fabric.mod.json`, `src/main/resources/dongbeiyujie.mixins.json`, and `src/client/resources/dongbeiyujie.client.mixins.json`.

**E2E Tests:**
- Not used; no external E2E framework or scripted Minecraft client/server validation appears in `.github/workflows/build.yml` or `build.gradle`.

**Data Generation Verification:**
- Fabric data generation is configured with `client = true` in `build.gradle`, and the datagen entrypoint is `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`.
- No generated data assertions or golden-file checks are configured; add them near the datagen task configuration in `build.gradle` if generated assets become part of the mod.

## Common Patterns

**Async Testing:**
```kotlin
// Not detected; current source has no coroutine, thread, scheduler, or async test usage.
```

**Error Testing:**
```kotlin
// Not detected; current source has no explicit thrown exceptions or error branches.
```

**CI Verification:**
```yaml
# `.github/workflows/build.yml`
- name: build
  run: ./gradlew build
```

**Wrapper Validation:**
- CI validates the Gradle wrapper with `gradle/actions/wrapper-validation@v6` in `.github/workflows/build.yml`.
- The wrapper distribution is Gradle 9.4.1 in `gradle/wrapper/gradle-wrapper.properties`.

---

*Testing analysis: 2026-06-06*
