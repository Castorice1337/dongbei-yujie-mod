---
status: planned
slug: daipai-hud-level
created: 2026-06-06T19:34:00+08:00
---

# Quick Task: Daipai HUD Level

Add a small client HUD indicator in the lower-right corner showing the player's final Daipai level as `带派级别: X`.

## Scope
- Register a client HUD element from the existing client initializer.
- Use `DaipaiLevelCalculator.calculateFinalLevel(player)` so the display matches existing gameplay math.
- Hide the indicator when the level is 0.
- Keep the UI compact and non-invasive.

## Verification
- `./gradlew.bat build`

