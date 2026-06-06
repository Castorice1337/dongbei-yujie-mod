---
phase: 02
status: issues_found
depth: standard
files_reviewed: 8
findings:
  critical: 2
  warning: 0
  info: 1
  total: 3
reviewed: 2026-06-06
reviewer: codex-inline
---

# Phase 02 Code Review: Daipai Core System

## Scope

Reviewed source files changed during Phase 02:

- `src/main/kotlin/com/columbina/yujie/registry/DongbeiYujieItems.kt`
- `src/main/kotlin/com/columbina/yujie/registry/DongbeiYujieEffects.kt`
- `src/main/kotlin/com/columbina/yujie/registry/DongbeiYujieEnchantments.kt`
- `src/main/kotlin/com/columbina/yujie/effect/DaipaiStatusEffect.kt`
- `src/main/kotlin/com/columbina/yujie/effect/DaipaiLevelCalculator.kt`
- `src/main/resources/data/dongbeiyujie/enchantment/daipai.json`
- `src/main/resources/assets/dongbeiyujie/lang/en_us.json`
- `src/main/resources/assets/dongbeiyujie/lang/zh_cn.json`

## Findings

### CR-01: Infinite Daipai effects never tick

**Severity:** Critical

**File:** `src/main/kotlin/com/columbina/yujie/effect/DaipaiStatusEffect.kt`

**Lines:** 29-31

`canApplyUpdateEffect` uses `duration % 40 == 0`. In Minecraft 1.21.11, `StatusEffectInstance.INFINITE` is `-1`; `-1 % 40` is never `0`. Any permanent Daipai effect, including the planned permanent Yujie Daipai III behavior, will never call `applyUpdateEffect`, so the aura will not damage or spawn particles.

**Suggested fix:** Make `canApplyUpdateEffect` return `true` and gate the 40-tick cadence inside `applyUpdateEffect` with an entity/world tick value, or explicitly handle infinite duration without triggering every tick.

### CR-02: Placeholder Big Sweaty Foot is not enchantable

**Severity:** Critical

**File:** `src/main/kotlin/com/columbina/yujie/registry/DongbeiYujieItems.kt`

**Lines:** 21

The placeholder item is created with plain `Item.Settings().registryKey(...)`. In Minecraft 1.21.11, item enchantability is component-based; `ItemStack.isEnchantable()` checks for the `ENCHANTABLE` component. Without `Item.Settings.enchantable(...)`, the command-only placeholder can exist and the enchantment JSON can support it, but the enchantment table path is unlikely to offer Daipai on that item, breaking ENCH-02.

**Suggested fix:** Give the placeholder item an enchantability component, for example via the current Yarn `Item.Settings.enchantable(int)` API, using a high value consistent with the "偏常见" decision.

### IN-01: Stale comments after Plan 02-02/03

**Severity:** Info

**File:** `src/main/kotlin/com/columbina/yujie/effect/DaipaiStatusEffect.kt`

**Lines:** 13-14

The class comment still says periodic tick damage logic will be added in Plan 02-02, but the logic now exists. This can confuse future phase work.

**Suggested fix:** Update the comment to describe the current aura behavior.

## Verification

- `./gradlew.bat build` passed after allowing Gradle wrapper/dependency network access.
- JSON parsing passed for `en_us.json`, `zh_cn.json`, and `daipai.json`.
- Local bytecode/mapping inspection confirmed `StatusEffectInstance.INFINITE` is `-1` and `ItemStack.isEnchantable()` depends on the item enchantable component.

## Summary

Two issues should be fixed before marking Phase 02 fully safe for downstream Phase 3/4 work: permanent Daipai effects currently do not tick, and the placeholder Big Sweaty Foot likely cannot use the enchantment table path required by Phase 2.
