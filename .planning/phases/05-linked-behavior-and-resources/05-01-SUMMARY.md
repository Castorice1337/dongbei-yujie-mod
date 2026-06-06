# Plan Summary: 05-01

## Objective
Register Yujie/Daipai sound events, map them through `sounds.json`, package user-supplied audio resources, and connect Yujie entity sounds.

## What was implemented
1. Added seven new sound events in `DongbeiYujieSounds`: spawn, idle, attack, hurt, death, BGM, and Daipai trigger.
2. Updated `sounds.json` with all eight mod sounds, including the existing `big_sweaty_foot_hit`.
3. Reused user-supplied OGG voice files for Yujie events, Daipai trigger, and Big Sweaty Foot hit.
4. Converted the supplied MP3 BGM into streamed `yujie_bgm.ogg`.
5. Wired Yujie spawn, ambient, attack, hurt, and death sounds in `DongbeiYujieEntity`.

## Verification
- `./gradlew.bat build` passed.
- Packaged jar contains all sound files under `assets/dongbeiyujie/sounds/`.

