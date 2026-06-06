# Plan Summary: 04-PLAN

## Objective
Implement the Dongbei Yujie entity, her custom "Strict Lone Wolf" night spawning behavior, and a custom 1.21.2+ `EntityRenderState`-based Y-axis locked billboard renderer.

## What was implemented
1. **Dongbei Yujie Entity (`DongbeiYujieEntity`)**: Inherits from `ZombieEntity` to preserve standard melee attacks. Set max health to 100, attack damage to 12, movement speed to 0.25. Set dimensions to 2.5m x 2.5m.
2. **Strict Lone Wolf Spawning (`DongbeiYujieSpawning`)**: Added custom `SpawnRestriction` logic in `DongbeiYujieSpawning.kt` checking that no existing `DongbeiYujieEntity` is within a 64-block radius of the nearest player.
3. **Registry and Data Generation (`DongbeiYujieEntities`)**: Configured entity builders, attributes, tracking ranges, and loot tables dropping `Big Sweaty Foot`.
4. **Custom Billboard Renderer (`DongbeiYujieEntityRenderer`)**: Replaced the 3D model with a 1:1 `submitCustom` quad rotation targeting `OrderedRenderCommandQueue`. Solved `Unresolved reference 'getEntityCutoutNoCull'` by identifying and migrating to `RenderLayers.entityCutoutNoCull(texture)` under the Yarn 1.21.11 API.
5. **Assets Copied**: Successfully integrated `dongbeiyujie_dahanjiao.png` into `textures/entity/dongbeiyujie.png`.
6. **Daipai Interaction**: Filtered the `BigSweatyFootAuraHandler` to ignore `DongbeiYujieEntity` (if applicable) and recognized Yujie as an innate Daipai threat level III.
7. **Hostility & Aura Tweaks (UAT)**: Modified `DongbeiYujieEntity` to exclude players and other Yujies from target selection (via `setTarget` and `ActiveTargetGoal` predicate), and ensured she obtains the `DAIPAI_ENTRY` status effect permanently in her tick loop to emit the Daipai aura.

## State/Dependencies
- All code successfully compiles and re-maps (`./gradlew build` verified).
- No blocking issues or deviations remaining.
- Fully satisfied requirements YUJI-01 through YUJI-07 based on `04-PLAN.md` instructions.
