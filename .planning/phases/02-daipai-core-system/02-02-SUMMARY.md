---
phase: 02
plan: 02
subsystem: "Daipai Effect"
tags:
  - periodic-damage
  - status-effect
requires: []
provides:
  - "Periodic Daipai aura tick behavior"
affects:
  - "Living entities around the aura owner"
tech-stack:
  added: []
  patterns: []
key-files:
  modified:
    - "src/main/kotlin/com/columbina/yujie/effect/DaipaiStatusEffect.kt"
key-decisions:
  - "Tick cadence is hard-coded to 40 ticks (2 seconds)."
  - "Target finding queries all living entities in range and iterates them to check spherical distance."
  - "Damage uses `playerAttack` or `mobAttack` based on the owner type, applied via `target.damage(world, source, amount)`."
requirements-completed:
  - DAIP-03
  - DAIP-04
  - DAIP-05
duration: "10 min"
completed: "2026-06-06T03:41:00Z"
---

# Phase 02 Plan 02: Daipai periodic range damage Summary

Daipai status effect now periodically damages living entities in a scaled radius every 2 seconds.

## Implementation Details

- **Tick Cadence:** Overrode `canApplyUpdateEffect` to trigger every 40 ticks.
- **Target Selection:** Added a bounding box search for `LivingEntity` within `finalLevel * 2.0` radius. Further filtered targets inside a spherical radius of `radius * radius`.
- **Damage Attribution:** Overrode `applyUpdateEffect` to take `ServerWorld`. Uses standard Minecraft `DamageSources` to attribute damage to the aura owner via `mobAttack` or `playerAttack`.

## Deviations from Plan

**[Rule 1 - API Mapping] `applyUpdateEffect` signature and Damage API mismatch** 
- Found during: Task 1 & 3
- Issue: In Minecraft 1.21.11 Yarn, `applyUpdateEffect` has a different signature `(world: ServerWorld, entity: LivingEntity, amplifier: Int)` compared to earlier versions. Also `Entity.world` is private, and `Entity.damage` requires `world` as its first argument.
- Fix: Updated the method override signature and used the injected `world` parameter for entity queries and damage calls.
- Files modified: `src/main/kotlin/com/columbina/yujie/effect/DaipaiStatusEffect.kt`
- Verification: `./gradlew.bat build` passed successfully.

**Total deviations:** 1 auto-fixed (API update). **Impact:** Safe and compatible with 1.21.11 Yarn.

## Next Step
Ready for 02-03.
