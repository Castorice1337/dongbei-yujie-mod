---
status: complete
phase: 04-dongbei-yujie-entity
source: [04-SUMMARY.md]
started: 2026-06-06T15:56:00+08:00
updated: 2026-06-06T16:23:00+08:00
---

## Current Test

[testing complete]

## Tests

### 1. Verify Paper Standee Rendering
expected: Spawn the Dongbei Yujie entity (using spawn egg or commands). The entity should appear as a huge 2.5m x 2.5m flat paper standee using the `dongbeiyujie_dahanjiao.png` image. As you move around it, it should rotate ONLY on the Y-axis to always face your camera horizontally.
result: pass

### 2. Verify Combat and Stats
expected: Switch to survival mode and let the entity attack you. It should have standard zombie-like movement and melee attack behavior, but deal heavy damage (12). Attack it back and verify it takes a lot of hits to die (100 max health).
result: pass

### 3. Verify Strict Lone Wolf Night Spawning
expected: Set time to night in survival mode. Explore the overworld. A Dongbei Yujie should spawn naturally, but you should NEVER see more than 1 at the same time within a 64-block radius.
result: pass

### 4. Verify Loot Drop
expected: Kill the Dongbei Yujie entity. It should drop exactly 1 Big Sweaty Foot item.
result: pass

## Summary

total: 4
passed: 4
issues: 0
pending: 0
skipped: 0

## Gaps

