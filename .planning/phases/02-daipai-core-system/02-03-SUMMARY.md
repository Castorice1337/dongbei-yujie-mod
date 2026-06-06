---
phase: 02
plan: 03
subsystem: "Daipai Effect"
tags:
  - enchantment
  - particles
requires:
  - 02-01
  - 02-02
provides:
  - "Daipai enchantment registration"
  - "Enchantment contribution to final Daipai level"
  - "Vanilla particle feedback during Daipai trigger"
affects:
  - "Big Sweaty Foot enchantments"
  - "DaipaiLevelCalculator logic"
tech-stack:
  added:
    - Minecraft 1.21.11 Data-Driven Enchantments
  patterns: []
key-files:
  modified:
    - "src/main/kotlin/com/columbina/yujie/registry/DongbeiYujieEnchantments.kt"
    - "src/main/kotlin/com/columbina/yujie/effect/DaipaiLevelCalculator.kt"
    - "src/main/kotlin/com/columbina/yujie/effect/DaipaiStatusEffect.kt"
  added:
    - "src/main/resources/data/dongbeiyujie/enchantment/daipai.json"
    - "src/main/resources/assets/dongbeiyujie/lang/en_us.json"
    - "src/main/resources/assets/dongbeiyujie/lang/zh_cn.json"
key-decisions:
  - "Used Minecraft 1.21 data-driven enchantment JSON pattern instead of registry code."
  - "Extracted dynamic registry using entity's DynamicRegistryManager to lookup the enchantment level safely."
requirements-completed:
  - ENCH-01
  - ENCH-02
  - ENCH-03
  - ENCH-04
  - DAIP-01
  - DAIP-02
duration: "10 min"
completed: "2026-06-06T03:57:00Z"
---

# Phase 02 Plan 03: Daipai enchantment and vanilla particle feedback

The mod now features the complete Daipai enchantment loop (Levels I-V) applying via `big_sweaty_foot`, integrating into the Daipai level calculation logic, alongside new particle feedback.

## Implementation Details

- **Daipai Enchantment:** Added the `dongbeiyujie:daipai` registry key in `DongbeiYujieEnchantments.kt`. Since enchantments are data-driven in 1.21.11, created `daipai.json` under the data pack path.
- **Enchantment Level Calculation:** Updated `DaipaiLevelCalculator.kt` to use `Entity.getRegistryManager()` for reading the dynamic enchantment registry and calculating the final bonus (+1 per enchant level).
- **Particle Feedback:** Spawned `ParticleTypes.HAPPY_VILLAGER` (green stars) around the aura owner and `ParticleTypes.HEART` around the targets in `DaipaiStatusEffect.kt`.
- **Localization:** Added `en_us.json` and `zh_cn.json` for name translations.

## Deviations from Plan

**[Rule 1 - Data-Driven Enchantment] Data-Driven Enchantment Mapping API mismatches**
- Issue: Plan 03 tasks assumed static code registration. 1.21.11 uses JSON and `DynamicRegistryManager`.
- Fix: Manually created the data-driven `.json` file for the enchantment. Adjusted the level calculator lookup to properly query the dynamic registry manager from `entity.registryManager`.

## Next Step
Phase 02 is Complete! The orchestrator can mark Phase 2 as complete and advance to Phase 3.
