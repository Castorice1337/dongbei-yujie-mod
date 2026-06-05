# Dongbei Yujie Funny Mod

## What This Is

Dongbei Yujie Funny Mod is a Fabric Minecraft 1.21.11 mod that turns the Dongbei Yujie meme concept into a playable joke mod. The first release focuses on a complete in-game loop: a paper-standee Dongbei Yujie entity, the Big Sweaty Foot item, the Daipai status effect and enchantment, controlled night spawning, drops, and placeholder resource hooks.

The mod is planned for public-release-safe packaging. Code registers the expected resource paths and behavior, while final PNG, voice, and BGM assets must be original, licensed, or supplied later in the final resource phase.

## Core Value

Players should encounter Dongbei Yujie at night, fight or flee from her Daipai pressure, and obtain Big Sweaty Foot gear that makes the joke mechanically useful.

## Requirements

### Validated

- ✓ Fabric/Kotlin 1.21.11 mod skeleton exists with main, client, and datagen entrypoints — existing codebase
- ✓ Codebase map exists in `.planning/codebase/` — existing GSD setup

### Active

- [ ] Register the mod's real metadata, registry structure, resource namespace, and datagen conventions.
- [ ] Implement the Daipai status effect with unified final-level calculation.
- [ ] Implement Daipai range damage that can affect players and non-player living entities while excluding the source and Dongbei Yujie entities.
- [ ] Implement the Daipai enchantment for Big Sweaty Foot with levels I through V.
- [ ] Implement Big Sweaty Foot as an unbreakable weapon and boot item with the requested combat, armor, curse, enchantment, and chat behavior.
- [ ] Implement Dongbei Yujie as a transparent PNG billboard entity that tracks and attacks players.
- [ ] Implement controlled night spawning near players, spawn egg creation, and Big Sweaty Foot drops.
- [ ] Register placeholder sound events and later replace placeholders with public-release-safe resources.
- [ ] Implement BGM behavior in the resource phase: play while at least one Yujie exists, stop when none remain, and never stack multiple copies.

### Out of Scope

- Complex 3D Dongbei Yujie model — the desired visual is a PNG paper-standee/billboard.
- Default packaging of copyrighted or unlicensed meme audio/BGM — public release requires original, licensed, or user-supplied resources.
- Full user-facing config system in v1 — spawning, range, and damage use conservative constants first.
- Project-level broad research pass — the codebase is small, the target stack is already fixed, and phase-level plan checks/verifiers are preferred.

## Context

- The current repository is a mostly untouched Fabric example mod using Kotlin 2.4.0, Java 21, Minecraft 1.21.11, Fabric Loader 0.19.3, Fabric API 0.141.4+1.21.11, and Fabric Language Kotlin.
- Shared code lives under `src/main/kotlin/com/columbina/yujie`; client-only rendering and resource client work belongs under `src/client/kotlin/com/columbina/yujie/client`.
- Resource namespace is `dongbeiyujie`, matching `src/main/resources/fabric.mod.json`.
- Testing is currently build-driven; no dedicated JVM test framework exists yet. Use Gradle build and manual Minecraft validation until a phase introduces test infrastructure.
- The desired tone is intentionally absurd and playful, but implementation should stay stable enough for public mod distribution.

## Constraints

- **Tech stack**: Fabric/Kotlin on Minecraft 1.21.11 with Java 21 — fixed by `gradle.properties`, `build.gradle`, and `fabric.mod.json`.
- **Client/server split**: Paper billboard rendering and client audio handling must stay in `src/client`; shared registries, effects, items, entities, spawning, and gameplay logic belong in `src/main`.
- **Public release**: Do not include unlicensed PNG, voice, or BGM assets by default.
- **Performance**: Night spawning must limit nearby Yujie count and avoid annoying or laggy repeated spawns.
- **Chat noise**: Big Sweaty Foot hit messages are visible only to the attacker by default.
- **Balance**: V1 constants should be conservative and not require a config system.

## Key Decisions

| Decision | Rationale | Outcome |
|----------|-----------|---------|
| V1 targets the core gameplay loop first | Entity, item, Buff, enchantment, drops, and placeholder sounds make the mod playable before polishing resources | - Pending |
| Dongbei Yujie renders as a camera-facing PNG billboard | Matches the requested paper-person style without a complex 3D model | - Pending |
| Daipai damage can affect players | The user wants the effect to be a dangerous joke mechanic, not only a mob-control aura | - Pending |
| Daipai excludes the source and Dongbei Yujie entities | Prevents self-damage and Yujie-vs-Yujie attrition from undermining spawning and encounters | - Pending |
| Big Sweaty Foot hit messages are attacker-only | Preserves the joke while preventing public chat spam | - Pending |
| BGM plays while any Yujie exists and does not stack | Gives Yujie presence without all-world looping or multi-entity audio chaos | - Pending |
| Final resources are handled last and must be licensed/original/user-supplied | Keeps the mod public-release-safe | - Pending |
| No full config system in v1 | Constants are enough for the first complete loop; config can be a later milestone | - Pending |

## Evolution

This document evolves at phase transitions and milestone boundaries.

**After each phase transition** (via `$gsd-transition`):
1. Requirements invalidated? Move to Out of Scope with reason.
2. Requirements validated? Move to Validated with phase reference.
3. New requirements emerged? Add to Active.
4. Decisions to log? Add to Key Decisions.
5. "What This Is" still accurate? Update if drifted.

**After each milestone** (via `$gsd-complete-milestone`):
1. Full review of all sections.
2. Core Value check: still the right priority?
3. Audit Out of Scope: reasons still valid?
4. Update Context with current state.

---
*Last updated: 2026-06-06 after initialization*
