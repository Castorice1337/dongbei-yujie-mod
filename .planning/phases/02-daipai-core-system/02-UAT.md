---
status: complete
phase: 02-daipai-core-system
source:
  - 02-01-SUMMARY.md
  - 02-02-SUMMARY.md
  - 02-03-SUMMARY.md
started: "2026-06-06T04:15:00Z"
updated: "2026-06-06T04:15:00Z"
---

## Current Test

[testing complete]

## Tests

### 1. Obtain Big Sweaty Foot and apply Daipai effect
expected: Player can obtain the placeholder item via `/give @s dongbeiyujie:big_sweaty_foot`. You can also give yourself the Daipai effect via `/effect give @s dongbeiyujie:daipai`.
result: pass

### 2. Daipai Periodic Damage and Range
expected: With Daipai effect active, every 2 seconds, nearby living entities (e.g., mobs, other players) take damage. The owner does not take damage.
result: pass

### 3. Equipment Level Bonus
expected: Holding Big Sweaty Foot in the main hand or wearing it in the feet slot increases the damage amount and the radius of the Daipai aura damage.
result: pass

### 4. Daipai Enchantment
expected: Big Sweaty Foot can be enchanted with "带派" (Daipai) up to level V. This enchantment further increases the aura's damage radius and amount when held/worn.
result: pass

### 5. Daipai Particle Feedback
expected: When the Daipai aura pulses every 2 seconds, green star particles appear around the aura owner, and heart particles appear around any entities that took damage.
result: pass

## Summary

total: 5
passed: 5
issues: 0
pending: 0
skipped: 0

## Gaps

