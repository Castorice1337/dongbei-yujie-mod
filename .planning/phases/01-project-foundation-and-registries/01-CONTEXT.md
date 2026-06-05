# Phase 1: Project Foundation and Registries - Context

**Gathered:** 2026-06-06
**Status:** Ready for planning

<domain>
## Phase Boundary

This phase replaces the Fabric example skeleton with real Dongbei Yujie Funny Mod foundation work. It covers public metadata, README direction, registry skeletons, entrypoint boundaries, mixin cleanup, and resource/datagen conventions, but it does not implement concrete gameplay content such as the Daipai effect, Big Sweaty Foot item, Yujie entity, AI, spawning, or real media assets.

</domain>

<decisions>
## Implementation Decisions

### Public Metadata
- **D-01:** Use `东北雨姐趣味模组` as the primary display name and `Dongbei Yujie Funny Mod` as the English/public fallback.
- **D-02:** Use `Castorice1337` as the author.
- **D-03:** Use `ilovecastoriceforever@gmail.com` and `bilibili@MyColumbina` as contact references.
- **D-04:** Use `https://github.com/Castorice1337/dongbei-yujie-mod.git` as the source URL.
- **D-05:** Use GPL-3.0 for code. Resource licensing must remain explicit and public-release-safe, and final assets must be original, licensed, or user-supplied.
- **D-06:** Metadata and README copy should be meme-aware but restrained: clear enough for public mod pages while preserving the joke.
- **D-07:** Replace the Fabric example README with a project intro, feature roadmap, build command, resource copyright/licensing notes, and current development status.

### Foundation Skeleton
- **D-08:** Build a full shared registry skeleton for items, entities, effects, enchantments, sounds, spawning, and datagen/resource conventions.
- **D-09:** Do not register concrete gameplay content in Phase 1. Later phases own concrete Daipai, Big Sweaty Foot, Yujie, linked behavior, and resource content.
- **D-10:** Remove or disable the current no-op Fabric example mixins and remove their required mixin risk.
- **D-11:** Keep client and datagen Kotlin files and directory conventions, but remove client and datagen entrypoint registration from `fabric.mod.json` until Phase 4 or Phase 5 needs them.
- **D-12:** Keep `src/main` dedicated-server-safe. Do not import `net.minecraft.client.*` outside `src/client`.

### Resource and Datagen Conventions
- **D-13:** Phase 1 only documents and prepares resource/datagen naming and path conventions.
- **D-14:** Do not add generated PNG, OGG, WAV, MP3, or other media assets in Phase 1.
- **D-15:** Later resource phases may use image2-generated placeholder or initial art, then replace it with final licensed, original, or user-supplied assets.
- **D-16:** Prepare for both `zh_cn` and `en_us` language output. Chinese is primary; English supports public release readability.
- **D-17:** Datagen should be treated as a future provider structure: keep class/package conventions and planner notes, but do not create empty no-op provider code just to satisfy Phase 1.

### the agent's Discretion
- The planner may choose exact package/object names for registry skeletons as long as they remain rooted under `com.columbina.yujie`, are clear, and keep entrypoints orchestration-focused.
- The planner may choose whether to remove mixin config files entirely or leave empty/non-required descriptors, as long as no required no-op mixin remains.
- The planner may choose precise README section wording, but must preserve the decided public metadata, licensing stance, and public-release-safe resource warning.

</decisions>

<canonical_refs>
## Canonical References

**Downstream agents MUST read these before planning or implementing.**

### Project Planning
- `.planning/PROJECT.md` — Project identity, core value, constraints, and public-release-safe resource decisions.
- `.planning/REQUIREMENTS.md` — Phase 1 requirements FOUND-01 through FOUND-04 and downstream requirement boundaries.
- `.planning/ROADMAP.md` — Phase 1 goal, success criteria, and boundary.

### Codebase Maps
- `.planning/codebase/STACK.md` — Fabric/Kotlin/Minecraft versions, runtime requirements, and build toolchain.
- `.planning/codebase/STRUCTURE.md` — Current source-set layout and where new shared/client/datagen code should go.
- `.planning/codebase/CONCERNS.md` — Known template metadata, no-op mixin, source-set, and release-readiness concerns.

### Current Source
- `src/main/resources/fabric.mod.json` — Current metadata, entrypoints, dependency bounds, and mixin registration to update in Phase 1.

</canonical_refs>

<code_context>
## Existing Code Insights

### Reusable Assets
- `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt`: Main initializer should remain the shared orchestration point and call shared registry skeleton init methods.
- `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt`: Keep as the future client registration location, but remove client entrypoint registration from metadata for now.
- `src/client/kotlin/com/columbina/yujie/client/DongbeiYujieDataGenerator.kt`: Keep as the future datagen convention anchor, but remove datagen entrypoint registration from metadata for now.

### Established Patterns
- The repo uses Fabric Loom split source sets: shared/server-safe code in `src/main`, client-only code in `src/client`.
- Kotlin singleton objects are the current entrypoint pattern.
- Resource namespace is `dongbeiyujie` and should remain consistent across metadata, assets, language keys, and future registries.

### Integration Points
- `src/main/resources/fabric.mod.json` must be updated for public metadata, dependency bounds, and entrypoint/mixin cleanup.
- `src/main/resources/dongbeiyujie.mixins.json` and `src/client/resources/dongbeiyujie.client.mixins.json` are tied to the current no-op mixins and should not keep required empty injections.
- `README.md` should stop describing the Fabric example template and start describing this mod's current roadmap and build flow.

</code_context>

<specifics>
## Specific Ideas

- User wants public metadata and README to be "梗但克制": funny, but still understandable and presentable for public release.
- User wants later image2-generated placeholder/initial art, but not as part of Phase 1.
- User chose to keep client/datagen file conventions while temporarily removing their metadata registration.

</specifics>

<deferred>
## Deferred Ideas

- image2-generated placeholder or initial art belongs in the later resource phase, not Phase 1.
- Concrete gameplay registration for Daipai, Big Sweaty Foot, Dongbei Yujie, sounds, spawning, and BGM belongs in Phases 2 through 5.

</deferred>

---

*Phase: 1-Project Foundation and Registries*
*Context gathered: 2026-06-06*
