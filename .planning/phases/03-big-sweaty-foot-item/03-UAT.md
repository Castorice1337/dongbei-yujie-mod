---
status: complete
phase: 03-big-sweaty-foot-item
source:
  - manual-gsd-plan
started: "2026-06-06T14:03:30.4687840+08:00"
updated: "2026-06-06T15:40:00+08:00"
---

## Current Test

number: complete
name: Phase 3 UAT Complete
expected: |
  All Phase 3 user acceptance tests have passed.
awaiting: none

## Tests

### 1. Obtain Big Sweaty Foot
expected: In a dev client, `/give @s dongbeiyujie:big_sweaty_foot` gives you Big Sweaty Foot (大汗脚), and it also appears in the Creative Combat (创造模式战斗) item group.
result: pass

### 2. Main-Hand Weapon Stats
expected: Holding Big Sweaty Foot (大汗脚) in the main hand shows or behaves with 12 attack damage, 1.8 attack speed, max stack count 1, high enchantability, and Unbreakable (不可破坏) behavior. Sharpness (锋利) follows Minecraft 1.21.11 vanilla display behavior.
result: pass

### 3. Hit Feedback
expected: Hitting a living target with Big Sweaty Foot (大汗脚) plays the registered `big_sweaty_foot_hit` placeholder sound event and sends exactly one random attacker-only chat message with the target name.
result: pass

### 4. Boots Equipment and Binding Curse
expected: Manually placing Big Sweaty Foot (大汗脚) in the feet slot grants 6 armor and automatically adds visible Binding Curse (绑定诅咒). Right-clicking the item in hand does not auto-equip it.
result: pass

### 5. Boots Grant Daipai I
expected: Wearing Big Sweaty Foot (大汗脚) with no existing Daipai (带派) effect grants infinite Daipai I while worn, removes that boot-granted effect after taking the boots off, and does not increase an existing Daipai effect level.
result: pass

### 6. Fear Behavior
expected: While wearing Big Sweaty Foot (大汗脚) and carrying Daipai (带派), nearby path-aware living mobs inside the Daipai range stop targeting the wearer and close mobs try to flee, similar to creepers avoiding cats. Players and Dongbei Yujie (东北雨姐) are excluded.
result: pass
notes: Initial retests found target re-acquisition and flee interruption issues; both were fixed and the final user retest passed.

### 7. Enchantment Compatibility
expected: Big Sweaty Foot (大汗脚) accepts boot-side enchantments, weapon-side enchantments, Protection family (保护类), Unbreaking (耐久), Mending (经验修补), Binding Curse (绑定诅咒), and Daipai (带派) through the enchantment table or enchanted book plus anvil where vanilla allows.
result: pass

### 8. Public-Safe Resource Placeholders
expected: The item has language/model/sound metadata paths wired, but no unlicensed PNG, OGG, voice, or BGM file is packaged by default.
result: pass

## Summary

total: 8
passed: 8
issues: 0
pending: 0
skipped: 0
blocked: 0

## Gaps

- timestamp: "2026-06-06T15:12:00+08:00"
  test: 6
  severity: medium
  issue: Fear behavior clears existing target but vanilla target goals can re-acquire the wearer.
  status: fixed
  fix: Added a MobEntity.setTarget mixin gate plus brain memory cleanup/avoid refresh so mobs cannot immediately re-acquire a repelled wearer.
- timestamp: "2026-06-06T15:24:00+08:00"
  test: 6
  severity: medium
  issue: Mobs flee, pause in place, then flee again because the target-rejection mixin stops navigation when vanilla AI retries target acquisition.
  status: fixed
  fix: Removed navigation stopping from the target-rejection mixin so it only blocks target acquisition and does not interrupt flee movement.
