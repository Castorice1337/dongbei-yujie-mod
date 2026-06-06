# Requirements: Dongbei Yujie Funny Mod

**Defined:** 2026-06-06
**Core Value:** Players should encounter Dongbei Yujie at night, fight or flee from her Daipai pressure, and obtain Big Sweaty Foot gear that makes the joke mechanically useful.

## v1 Requirements

### Foundation

- [ ] **FOUND-01**: The mod metadata describes Dongbei Yujie Funny Mod instead of the Fabric example mod.
- [ ] **FOUND-02**: Shared registries are organized for items, entities, effects, enchantments, sounds, and spawn behavior under the `dongbeiyujie` namespace.
- [ ] **FOUND-03**: Client-only setup is isolated under the client source set for billboard rendering and client audio behavior.
- [ ] **FOUND-04**: Datagen/resource conventions exist for language, models, item assets, loot, sounds, and entity resources.

### Daipai Core

- [ ] **DAIP-01**: Living entities can receive a Daipai status effect with player-facing levels I and above.
- [ ] **DAIP-02**: The final Daipai level is calculated from base effect level, Big Sweaty Foot boot bonus, Big Sweaty Foot main-hand bonus, Daipai enchantment level, and special entity minimums.
- [ ] **DAIP-03**: Every 2 seconds, Daipai damages living entities in `finalLevel * 2` blocks for `finalLevel` damage.
- [ ] **DAIP-04**: Daipai range damage can affect players and non-player living entities, but excludes the effect source and Dongbei Yujie entities.
- [ ] **DAIP-05**: Daipai damage uses normal Minecraft damage handling so armor, resistance, and related mechanics can mitigate it.

### Daipai Enchantment

- [ ] **ENCH-01**: The Daipai enchantment exists with levels I through V.
- [ ] **ENCH-02**: Daipai enchantment can be applied to Big Sweaty Foot through enchantment table flow when applicable.
- [ ] **ENCH-03**: Daipai enchantment can be applied or upgraded with enchanted books and an anvil.
- [ ] **ENCH-04**: Each Daipai enchantment level contributes +1 to the final Daipai level when the enchanted Big Sweaty Foot is in the relevant equipment slot.

### Big Sweaty Foot

- [ ] **FOOT-01**: Big Sweaty Foot exists as a custom item that can function as a main-hand weapon.
- [ ] **FOOT-02**: As a weapon, Big Sweaty Foot is unbreakable, has 12 base attack damage, and has 1.8 attack speed.
- [ ] **FOOT-03**: Big Sweaty Foot plays its hit sound placeholder and sends a random attacker-only chat message when it hits a target.
- [ ] **FOOT-04**: Big Sweaty Foot can apply the Daipai effect to hit targets.
- [ ] **FOOT-05**: Big Sweaty Foot can be worn in the feet slot as boots.
- [ ] **FOOT-06**: As boots, Big Sweaty Foot is unbreakable, grants 6 armor, automatically carries Binding Curse behavior, and contributes +1 final Daipai level while worn.
- [ ] **FOOT-07**: Big Sweaty Foot supports Protection, Unbreaking, Mending, Binding Curse, and Daipai enchantments.

### Yujie Entity

- [ ] **YUJI-01**: Dongbei Yujie is registered as a custom living hostile entity.
- [ ] **YUJI-02**: Dongbei Yujie renders on the client as a transparent PNG billboard/paper standee.
- [ ] **YUJI-03**: Dongbei Yujie actively tracks players and performs melee attacks after closing distance.
- [ ] **YUJI-04**: Dongbei Yujie always counts as at least Daipai III for final Daipai level calculations.
- [ ] **YUJI-05**: Dongbei Yujie drops Big Sweaty Foot on death according to its loot table.
- [ ] **YUJI-06**: Dongbei Yujie can be spawned with a spawn egg.
- [ ] **YUJI-07**: At night, the mod attempts to spawn one Dongbei Yujie within 24 blocks of a player while limiting nearby duplicates.

### Linked Behavior

- [ ] **LINK-01**: Holding Big Sweaty Foot in the main hand expands or strengthens Daipai-linked attack behavior.
- [ ] **LINK-02**: Wearing Big Sweaty Foot while having Daipai makes eligible nearby mobs avoid or stop targeting the wearer when the effect is active.
- [ ] **LINK-03**: The avoidance behavior is conservative enough to avoid excessive pathfinding churn or server lag.
- [ ] **LINK-04**: Multiple Yujie entities do not stack BGM playback or damage each other through Daipai.

### Resources

- [ ] **RES-01**: Placeholder resource paths exist for Yujie spawn, idle, attack, hurt, death, Big Sweaty Foot hit, and Daipai trigger sounds.
- [ ] **RES-02**: Placeholder or licensed/original PNG assets exist for Big Sweaty Foot, Daipai icon, spawn egg, and Yujie billboard texture.
- [ ] **RES-03**: Final voice/BGM resources are public-release-safe before being packaged by default.
- [ ] **RES-04**: BGM starts while at least one Yujie exists, stops when no Yujie remains, and never plays multiple stacked copies.

## v2 Requirements

### Configuration

- **CONF-01**: Players can configure spawn chance, spawn cooldown, nearby Yujie limit, Daipai damage, Daipai range scaling, chat message frequency, and BGM behavior.

### Polish

- **POLI-01**: Yujie has multiple expression or animation variants for idle, attack, hurt, and death states.
- **POLI-02**: Big Sweaty Foot and Daipai effects have refined particles or visual feedback beyond placeholder behavior.

## Out of Scope

| Feature | Reason |
|---------|--------|
| Complex 3D Yujie model | The requested entity is a PNG paper-person/billboard. |
| Default unlicensed BGM or meme voice packaging | The mod is planned for public release and must avoid unauthorized assets. |
| Full config system in v1 | Conservative constants are enough for the initial playable loop. |
| Global chat hit announcements | Attacker-only messages avoid public chat spam. |
| Yujie-on-Yujie Daipai damage | Same-entity exclusion keeps encounters stable and avoids accidental self-clearing spawns. |

## Traceability

| Requirement | Phase | Status |
|-------------|-------|--------|
| FOUND-01 | Phase 1 | Complete |
| FOUND-02 | Phase 1 | Complete |
| FOUND-03 | Phase 1 | Complete |
| FOUND-04 | Phase 1 | Complete |
| DAIP-01 | Phase 2 | Complete |
| DAIP-02 | Phase 2 | Complete |
| DAIP-03 | Phase 2 | Complete |
| DAIP-04 | Phase 2 | Complete |
| DAIP-05 | Phase 2 | Complete |
| ENCH-01 | Phase 2 | Complete |
| ENCH-02 | Phase 2 | Complete |
| ENCH-03 | Phase 2 | Complete |
| ENCH-04 | Phase 2 | Complete |
| FOOT-01 | Phase 3 | Complete |
| FOOT-02 | Phase 3 | Complete |
| FOOT-03 | Phase 3 | Complete |
| FOOT-04 | Phase 3 | Complete |
| FOOT-05 | Phase 3 | Complete |
| FOOT-06 | Phase 3 | Complete |
| FOOT-07 | Phase 3 | Complete |
| YUJI-01 | Phase 4 | Pending |
| YUJI-02 | Phase 4 | Pending |
| YUJI-03 | Phase 4 | Pending |
| YUJI-04 | Phase 4 | Pending |
| YUJI-05 | Phase 4 | Pending |
| YUJI-06 | Phase 4 | Pending |
| YUJI-07 | Phase 4 | Pending |
| LINK-01 | Phase 5 | Pending |
| LINK-02 | Phase 5 | Pending |
| LINK-03 | Phase 5 | Pending |
| LINK-04 | Phase 5 | Pending |
| RES-01 | Phase 5 | Pending |
| RES-02 | Phase 5 | Pending |
| RES-03 | Phase 5 | Pending |
| RES-04 | Phase 5 | Pending |

**Coverage:**
- v1 requirements: 35 total
- Mapped to phases: 35
- Unmapped: 0

---
*Requirements defined: 2026-06-06*
*Last updated: 2026-06-06 after initial definition*
