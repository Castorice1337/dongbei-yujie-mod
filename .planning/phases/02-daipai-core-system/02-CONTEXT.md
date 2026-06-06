# Phase 2: Daipai Core System - Context

**Gathered:** 2026-06-06
**Status:** Ready for planning

<domain>
## Phase Boundary

This phase turns Daipai into a real gameplay system. It covers the Daipai status effect, deterministic final-level calculation, periodic range damage, the Daipai I-V enchantment, and a minimal command-only Big Sweaty Foot placeholder item used as an enchantment and level-calculation anchor.

This phase does not implement full Big Sweaty Foot weapon or boot behavior, Dongbei Yujie entity logic, custom sounds, custom textures, BGM, loot tables, spawn eggs, or night spawning. Those remain assigned to later phases.

</domain>

<decisions>
## Implementation Decisions

### Daipai Damage Behavior
- **D-01:** Daipai periodic range damage is attributed to the entity that currently has the Daipai Buff. Whoever carries the Buff is the aura owner.
- **D-02:** A standalone Daipai Buff is enough to trigger periodic range damage. Big Sweaty Foot and enchantments only add levels; they are not required for the Buff to function.
- **D-03:** Daipai damage targets all living entities in range, including players, animals, villagers, and hostile mobs.
- **D-04:** Daipai damage excludes the aura owner and Dongbei Yujie entities of the same mod.
- **D-05:** Daipai range checks do not require line of sight. The effect uses a spherical radius and can damage through walls.
- **D-06:** Daipai damage must use normal Minecraft damage handling so armor, resistance, and related vanilla mitigation can apply.

### Final Level Calculation
- **D-07:** Periodic Daipai damage requires the entity to currently have the Daipai Buff. Equipment and enchantment bonuses add to the final level only when the Buff is present.
- **D-08:** Final Daipai level is calculated from base Buff level plus Big Sweaty Foot boot bonus, Big Sweaty Foot main-hand bonus, Daipai enchantment level, and special entity minimums.
- **D-09:** Minecraft amplifier values must be converted to player-facing levels. Daipai I is final-level base 1, Daipai II is base 2, and so on.
- **D-10:** Phase 2 must provide a generic special-minimum hook for future entities. Do not hard-code Dongbei Yujie classes before Phase 4 introduces them.

### Daipai Enchantment
- **D-11:** Register a Daipai enchantment with levels I through V.
- **D-12:** Daipai enchantment should be available through both enchantment table flow and enchanted book plus anvil flow.
- **D-13:** Daipai enchantment should be relatively common, matching the mod's high-enchantability joke balance.
- **D-14:** Each Daipai enchantment level contributes +1 to the final Daipai level when the enchanted Big Sweaty Foot placeholder is in a relevant slot.

### Minimal Big Sweaty Foot Placeholder
- **D-15:** Phase 2 may register a minimal Big Sweaty Foot placeholder item only as an anchor for Daipai enchantment applicability and final-level testing.
- **D-16:** The placeholder Big Sweaty Foot must be obtainable only by command, such as `/give`. Do not add it to creative tabs, recipes, loot, or normal survival acquisition in Phase 2.
- **D-17:** Do not implement full Big Sweaty Foot weapon stats, boot armor, durability behavior, Binding Curse behavior, hit messages, hit sound, target application, or intimidation behavior in Phase 2. Those remain Phase 3 and Phase 5 work.

### Feedback and Resources
- **D-18:** Phase 2 must not add custom PNG, OGG, WAV, MP3, BGM, voice, or custom sound resources.
- **D-19:** Phase 2 may use vanilla Minecraft particles for Daipai trigger feedback because they do not introduce custom resource licensing concerns.
- **D-20:** Daipai trigger feedback should be clear and visible, using vanilla heart particles and a green star-like vanilla particle. Keep particle counts moderate enough to avoid visual spam and performance issues.

### Planning and Execution Preferences
- **D-21:** Phase 2 planning and execution should stay in the main agent/orchestrator path and avoid subagents unless the user explicitly re-enables them.

### the agent's Discretion
- The planner may choose package and helper names as long as they stay under `com.columbina.yujie`, follow existing registry patterns, and keep `src/main` server-safe.
- The planner may choose the closest available Minecraft 1.21.11 vanilla green star-like particle.
- The planner may choose exact constants or helper APIs for the special-minimum hook as long as Phase 4 can connect Dongbei Yujie without rewriting the final-level system.

</decisions>

<canonical_refs>
## Canonical References

**Downstream agents MUST read these before planning or implementing.**

### Project Planning
- `.planning/PROJECT.md` - Project identity, core value, constraints, and public-release-safe resource decisions.
- `.planning/REQUIREMENTS.md` - Phase 2 requirements DAIP-01 through DAIP-05 and ENCH-01 through ENCH-04.
- `.planning/ROADMAP.md` - Phase 2 goal, success criteria, and phase boundaries.
- `.planning/phases/01-project-foundation-and-registries/01-CONTEXT.md` - Prior decisions on registry skeletons, source-set safety, and resource boundaries.

### Codebase Maps
- `.planning/codebase/STACK.md` - Fabric/Kotlin/Minecraft versions and build toolchain.
- `.planning/codebase/STRUCTURE.md` - Source-set layout and where new shared code belongs.
- `.planning/codebase/CONVENTIONS.md` - Naming, Kotlin object, logging, and build-gate conventions.

### Current Source
- `src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt` - Main registration orchestration.
- `src/main/kotlin/com/columbina/yujie/registry/DongbeiYujieItems.kt` - Existing item registry anchor for the minimal placeholder item.
- `src/main/kotlin/com/columbina/yujie/registry/DongbeiYujieEffects.kt` - Existing effect registry anchor for Daipai.
- `src/main/kotlin/com/columbina/yujie/registry/DongbeiYujieEnchantments.kt` - Existing enchantment registry anchor for Daipai enchantment.

</canonical_refs>

<code_context>
## Existing Code Insights

### Reusable Assets
- `DongbeiYujie.onInitialize()` already calls item, effect, and enchantment registry anchors.
- `DongbeiYujieIds` already centralizes the `dongbeiyujie` namespace and should be reused for new identifiers.
- The current registry skeletons are intentionally empty and ready for concrete Phase 2 registrations.

### Established Patterns
- Shared gameplay logic belongs under `src/main/kotlin/com/columbina/yujie`.
- Client-only rendering and custom resource client work stays under `src/client`, but Phase 2 does not need client-only custom code.
- `./gradlew.bat build` is the current acceptance gate.

### Integration Points
- `DongbeiYujieItems.register()` should register the command-only placeholder Big Sweaty Foot item.
- `DongbeiYujieEffects.register()` should register the Daipai status effect and its periodic behavior.
- `DongbeiYujieEnchantments.register()` should register the Daipai enchantment and compatibility with the placeholder item.

</code_context>

<specifics>
## Specific Ideas

- Daipai should feel like a dangerous aura: it works by Buff alone and can hurt players and ordinary living entities.
- The aura is intentionally unreasonable and meme-like: it can damage through walls.
- The minimal Big Sweaty Foot placeholder exists only to make Phase 2's enchantment and level calculation testable without taking over Phase 3.
- Vanilla heart particles and green star-like particles should make Daipai trigger feedback clear without adding custom assets.

</specifics>

<deferred>
## Deferred Ideas

- Full Big Sweaty Foot weapon and boot behavior belongs to Phase 3.
- Dongbei Yujie entity class, spawn egg, drops, AI, and special minimum integration belongs to Phase 4.
- Intimidation/avoidance behavior, custom sounds, custom textures, BGM, and final resource wiring belong to Phase 5.

</deferred>

---

*Phase: 2-Daipai Core System*
*Context gathered: 2026-06-06*
