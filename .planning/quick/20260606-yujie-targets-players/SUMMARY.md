---
status: incomplete
slug: yujie-targets-players
updated: 2026-06-06T20:08:00+08:00
---

# Quick Task Summary: Yujie Targets Players

## Completed
- Updated `DongbeiYujieEntity` so `ActiveTargetGoal` allows players again.
- Updated `setTarget` so it only rejects other `DongbeiYujieEntity` targets.
- Left Big Sweaty Foot hit behavior and spawning behavior unchanged.

## Verification
- `./gradlew.bat build`: blocked by full disk while remapping sources jar.
- `./gradlew.bat compileKotlin compileClientKotlin`: blocked because Gradle could not write `C:\Users\27970\.gradle\daemon\9.4.1\registry.bin`.

## Blocker
- Both C: and D: report 0 bytes free via `Get-PSDrive`.

