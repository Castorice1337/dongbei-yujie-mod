---
status: complete
slug: daipai-hud-level
completed: 2026-06-06T19:38:00+08:00
---

# Quick Task Summary: Daipai HUD Level

## Completed
- Added `DaipaiHud`, a client HUD element registered after the vanilla hotbar.
- Displays `带派级别: X` in the lower-right corner when the local player's final Daipai level is greater than 0.
- Uses `DaipaiLevelCalculator.calculateFinalLevel(player)` so equipment, enchantments, and active Daipai Buff all contribute consistently.
- Hides the indicator when the player is not Daipai.

## Verification
- `./gradlew.bat build` passed.

