# Plan Summary: 05-03

## Objective
Implement non-stacking client-side Yujie BGM that follows the nearest Yujie and stops when none are nearby.

## What was implemented
1. Added `YujieBgmSoundInstance`, a repeatable `MovingSoundInstance` tracking a `DongbeiYujieEntity`.
2. Added a client tick loop in `DongbeiYujieClientAudio`.
3. Scans for the nearest living Yujie within 64 blocks of the local player.
4. Stops old BGM when no Yujie is nearby or when the nearest tracked Yujie changes.
5. Uses one active sound instance to prevent stacked BGM playback.

## Verification
- `./gradlew.bat build` passed.
- Packaged jar contains `DongbeiYujieClientAudio` and `yujie_bgm.ogg`.

