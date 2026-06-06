# Roadmap: Dongbei Yujie Funny Mod

**Created:** 2026-06-06
**Granularity:** Coarse
**Mode:** YOLO

## Overview

| Phase | Name | Goal | Requirements |
|-------|------|------|--------------|
| 1 | Project Foundation and Registries | Replace example-mod scaffolding with real mod identity and registry/resource structure. | FOUND-01 through FOUND-04 |
| 2 | Daipai Core System (Complete) | Implement the status effect, final-level calculation, periodic damage, and Daipai enchantment. | DAIP-01 through DAIP-05, ENCH-01 through ENCH-04 |
| 3 | Big Sweaty Foot Item (Planned) | Implement the dual weapon/boots item, combat behavior, enchantment compatibility, and wearer bonuses. | FOOT-01 through FOOT-07 |
| 4 | Dongbei Yujie Entity | Implement the hostile paper-standee entity, AI, spawning, spawn egg, drops, and client billboard rendering. | YUJI-01 through YUJI-07 |
| 5 | Linked Behavior and Resources | Complete Big Sweaty Foot/Daipai/Yujie interactions, placeholders, final resource policy, and BGM behavior. | LINK-01 through LINK-04, RES-01 through RES-04 |

## Phase Details

### Phase 1: Project Foundation and Registries

**Goal:** Replace the Fabric example skeleton with a real Dongbei Yujie mod foundation without adding feature logic yet.

**Requirements:** FOUND-01, FOUND-02, FOUND-03, FOUND-04

**Success Criteria:**
1. `fabric.mod.json` describes Dongbei Yujie Funny Mod and no longer reads like the Fabric example template.
2. Shared registration entrypoints exist for items, entities, effects, enchantments, sounds, and spawning.
3. Client registration has a clear place for renderers and client audio behavior without crossing source-set boundaries.
4. Datagen/resource conventions are established for later items, entity resources, loot, language, and sound metadata.
5. `gradlew.bat build` succeeds.

**UI hint:** no

### Phase 2: Daipai Core System

**Goal:** Make Daipai a real gameplay system with deterministic level calculation and safe periodic damage behavior.

**Requirements:** DAIP-01, DAIP-02, DAIP-03, DAIP-04, DAIP-05, ENCH-01, ENCH-02, ENCH-03, ENCH-04

**Success Criteria:**
1. Daipai effect is registered, visible in-game, and uses player-facing level labels correctly despite Minecraft's 0-based amplifier.
2. A single helper calculates final Daipai level from effect level, Big Sweaty Foot slots, Daipai enchantment level, and special entity minimums.
3. Daipai ticks every 2 seconds and applies `finalLevel` damage inside `finalLevel * 2` blocks.
4. Daipai can damage players and ordinary living entities while excluding the source and Dongbei Yujie entities.
5. Daipai enchantment levels I-V are available for Big Sweaty Foot and contribute +1 per enchantment level.
6. `gradlew.bat build` succeeds, and manual or focused validation confirms damage/range behavior.

**UI hint:** no

### Phase 3: Big Sweaty Foot Item

**Goal:** Make Big Sweaty Foot work as both an absurd weapon and absurd boots, with Daipai-linked bonuses.

**Requirements:** FOOT-01, FOOT-02, FOOT-03, FOOT-04, FOOT-05, FOOT-06, FOOT-07

**Success Criteria:**
1. Big Sweaty Foot is registered and obtainable through dev/test means before the Yujie loot phase.
2. Main-hand use is unbreakable, uses 12 attack damage and 1.8 attack speed, and can apply Daipai to hit targets.
3. Successful hits play the registered hit sound placeholder and send one random attacker-only message.
4. Feet-slot use is unbreakable, grants 6 armor, behaves as Binding Curse equipment, and contributes +1 final Daipai level.
5. Protection, Unbreaking, Mending, Binding Curse, and Daipai enchantment compatibility works for the item.
6. `gradlew.bat build` succeeds, and manual validation confirms weapon and boots behavior.

**UI hint:** no

### Phase 4: Dongbei Yujie Entity

**Goal:** Add the mod's signature encounter: a hostile paper-standee Yujie that spawns at night and drops Big Sweaty Foot.

**Requirements:** YUJI-01, YUJI-02, YUJI-03, YUJI-04, YUJI-05, YUJI-06, YUJI-07

**Success Criteria:**
1. Dongbei Yujie entity and spawn egg are registered and load in-game.
2. Client renderer displays Yujie as a transparent PNG billboard that faces the camera.
3. Yujie tracks players, approaches them, and performs melee attacks.
4. Yujie always calculates as at least Daipai III.
5. Yujie drops Big Sweaty Foot through loot table behavior.
6. Night spawning attempts happen within 24 blocks of players and limit nearby duplicates.
7. `gradlew.bat build` succeeds, and manual client validation confirms spawn egg, night spawn, attack, render, and drop behavior.

**UI hint:** yes

### Phase 5: Linked Behavior and Resources

**Goal:** Finish the cross-system joke: Daipai intimidation, sound hooks, public-safe resources, and non-stacking Yujie BGM.

**Requirements:** LINK-01, LINK-02, LINK-03, LINK-04, RES-01, RES-02, RES-03, RES-04

**Success Criteria:**
1. Main-hand Big Sweaty Foot strengthens Daipai-linked attack behavior without duplicating damage loops.
2. Wearing Big Sweaty Foot while having Daipai reduces or redirects eligible hostile targeting and makes close enemies attempt to move away.
3. The avoidance logic is conservative and does not create excessive pathfinding churn.
4. Yujie entities do not damage each other through Daipai.
5. Placeholder sound and texture paths exist and are wired into code and metadata.
6. Any packaged final PNG, voice, or BGM resource is original, licensed, or explicitly user-supplied.
7. BGM plays while at least one Yujie exists, stops when no Yujie remains, and never stacks for multiple Yujies.
8. `gradlew.bat build` succeeds, and manual multiplayer-like scenarios confirm no chat spam, no BGM stacking, and controlled spawning.

**UI hint:** yes

## Coverage

- v1 requirements: 35
- Requirements mapped: 35
- Unmapped: 0

---
*Roadmap created: 2026-06-06*
