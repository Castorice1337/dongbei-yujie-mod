---
status: complete
phase: 05-linked-behavior-and-resources
source:
  - 05-01-SUMMARY.md
  - 05-02-SUMMARY.md
  - 05-03-SUMMARY.md
  - 05-04-SUMMARY.md
started: 2026-06-06T19:12:25+08:00
updated: 2026-06-06T19:29:05+08:00
---

## Current Test

[testing complete]

## Tests

### 1. Yujie Spawn Texture And Spawn Voice
expected: Launch the mod, enter a world, and spawn `dongbeiyujie:dongbei_yujie` with the spawn egg or `/summon`. The entity should render as the supplied Dongbei Yujie billboard image, not a missing texture or old duplicate path. A spawn voice should play once when she appears.
result: pass

### 2. Yujie Combat Voice Hooks
expected: While near a spawned Yujie, idle/ambient voice can play; when she attacks, is hurt, and dies, the corresponding voice sounds play without missing-sound warnings.
result: pass

### 3. Big Sweaty Foot Hit Feedback
expected: Hit an entity with Big Sweaty Foot. The hit sound should play, a foot-hit particle should appear around 3/4 target height, and the attacker-only chat message should still appear. The target should not receive a new Daipai status effect from the melee hit alone.
result: pass

### 4. Daipai Aura Trigger Feedback
expected: Give a living entity Daipai and place eligible targets in range. When aura damage lands, each damaged target should show the foot-hit particle and play the Daipai trigger sound. Dongbei Yujie entities should not damage each other through this aura.
result: pass

### 5. Non-Stacking Yujie BGM
expected: When at least one Yujie is near the local player, the supplied BGM should start. Spawning multiple nearby Yujies should not layer multiple BGM copies. Removing or moving away from all Yujies should stop the BGM.
result: pass

### 6. Resource UI Assets
expected: Big Sweaty Foot should display the supplied item texture, the Daipai status effect should display the supplied icon, and the foot-hit particle should reuse the supplied foot image rather than missing texture.
result: pass

## Summary

total: 6
passed: 6
issues: 0
pending: 0
skipped: 0
blocked: 0

## Gaps

[none yet]
