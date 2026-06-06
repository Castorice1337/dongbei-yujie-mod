# Phase 4: Dongbei Yujie Entity

This phase introduces the primary antagonist, Dongbei Yujie, as a hostile entity. We'll implement her AI, unique Y-axis locked billboard rendering, strict spawning constraints, and drops.

## User Review Required

- **Image Asset**: The image asset provided at `dongbeiyujie_dahanjiao.png` has been copied to the mod's resource folder (`assets/dongbeiyujie/textures/entity/dongbeiyujie.png`).
- **Billboard Math**: We will implement a custom `EntityRenderer` that avoids drawing a 3D model, instead drawing a flat quad that rotates on the Y-axis to face the player. 
- **Spawning Logic**: We'll register a custom `SpawnRestriction` that ensures no more than 1 Yujie spawns per player within a 64-block radius at night.

## Proposed Changes

---

### Entity Data & Logic (Shared)

#### [NEW] src/main/kotlin/com/columbina/yujie/entity/DongbeiYujieEntity.kt
- Extends `HostileEntity`.
- **Dimensions**: 2.5 x 2.5.
- **Attributes**: Max Health 100, Attack Damage 12, Movement Speed 0.25.
- **AI Goals**: `MeleeAttackGoal` (standard zombie bump), `WanderAroundFarGoal`, `LookAtEntityGoal`, `LookAroundGoal`.
- **Drops**: Configured via loot table to drop Big Sweaty Foot.

#### [NEW] src/main/kotlin/com/columbina/yujie/registry/ModEntities.kt
- Register `DONGBEI_YUJIE` as a `FabricEntityTypeBuilder<DongbeiYujieEntity>`.
- Register the `SpawnEggItem` for the entity.
- Provide a method to register default attributes.

#### [MODIFY] src/main/kotlin/com/columbina/yujie/DongbeiYujie.kt
- Call `ModEntities.register()`.
- Set up biome modifications to allow `DONGBEI_YUJIE` to spawn in overworld biomes at night.
- Register `SpawnRestriction` for `DONGBEI_YUJIE` to enforce the "Strict Lone Wolf" mode (checking nearby player radius for existing Yujie entities before allowing a spawn).

#### [MODIFY] src/main/kotlin/com/columbina/yujie/effect/DaipaiEffect.kt
- *If not already implemented in Phase 2:* Ensure Daipai range damage excludes `DongbeiYujieEntity`.
- Ensure that if `DongbeiYujieEntity` is the source of Daipai, it inherently counts as Daipai level III for calculation purposes.

#### [NEW] src/main/resources/data/dongbeiyujie/loot_table/entities/dongbei_yujie.json
- Loot table that drops 1 `dongbeiyujie:big_sweaty_foot`.

---

### Client Rendering

#### [NEW] src/client/kotlin/com/columbina/yujie/client/render/DongbeiYujieEntityRenderer.kt
- Extends `EntityRenderer<DongbeiYujieEntity>`.
- Instead of using a `LivingEntityRenderer` with a model, this overrides `render` to draw a 2D quad (width 2.5, height 2.5).
- Uses the `MatrixStack` to rotate the quad around the Y-axis so it faces the camera horizontally, achieving the "paper standee" (cylindrical billboard) effect.
- Binds `dongbeiyujie:textures/entity/dongbeiyujie.png`.

#### [MODIFY] src/client/kotlin/com/columbina/yujie/client/DongbeiYujieClient.kt
- Register the `DongbeiYujieEntityRenderer` to the `DONGBEI_YUJIE` entity type using `EntityRendererRegistry.register`.

## Verification Plan

### Manual Verification
1. Launch the client.
2. Verify that the user-provided texture correctly renders as a 2.5x2.5 paper standee that only rotates on the Y-axis.
3. Test melee combat to ensure standard zombie-like attack pacing and damage.
4. Set time to night in survival mode and verify she spawns, but strictly limited to 1 around the player.
5. Kill her to ensure she drops the Big Sweaty Foot.
