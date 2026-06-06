# Phase 3: Big Sweaty Foot Item - Context

**Gathered:** 2026-06-06
**Status:** Complete

<domain>
## Phase Boundary

This phase implements the Big Sweaty Foot item (`dongbeiyujie:big_sweaty_foot`) as a dual-purpose equipment: a main-hand weapon and a feet-slot boots item, with integrated Daipai-linked combat behavior, attacker-only hit messages, hit sound, Binding Curse equipment behavior, boot-granted Daipai aura, target repulsion (fear) logic, and enchantment compatibility.

This phase does not implement the Dongbei Yujie entity spawning, rendering, AI, or BGM behavior, which are assigned to Phase 4 and Phase 5.

</domain>

<decisions>
## Implementation Decisions

### Item & Dual Slot Behavior
- **D-FOOT-01:** Register `dongbeiyujie:big_sweaty_foot` as a custom item extending `Item` (or custom subtype) that works as both weapon and boots.
- **D-FOOT-02:** When held in the main hand, it behaves as an unbreakable weapon with 12 base attack damage and 1.8 attack speed.
- **D-FOOT-03:** When equipped in the feet slot, it behaves as unbreakable boots with 6 armor, auto-grants Binding Curse behavior, and contributes +1 to final Daipai level calculation.
- **D-FOOT-04:** Expose the item in the Combat creative tab and ensure it can be enchanted with boot-side and weapon-side enchantments.

### Combat & Hit Feedback
- **D-FOOT-05:** Hitting a target with Big Sweaty Foot applies the Daipai status effect to the target.
- **D-FOOT-06:** Hitting a target plays a custom sound event `dongbeiyujie:big_sweaty_foot_hit` and sends a random attacker-only chat message to the player. The messages must be localizable and only visible to the attacker.

### Wearer Buff & Fear Behavior
- **D-FOOT-07:** Wearing the boots grants the player infinite Daipai I (amplifier 0) if they don't already have it. Removing the boots removes this boot-granted effect.
- **D-FOOT-08:** Nearby mobs within the Daipai range are repelled by the wearer. Their target/attack targets are cleared, and they are prevented from re-acquiring the wearer as a target.
- **D-FOOT-09:** Close mobs within the repulsion range actively flee from the wearer, similar to creepers avoiding cats, to create a tangible "pressure" effect. Mobs must not pause/stutter during flight.
- **D-FOOT-10:** Repulsion and target rejection exclude players and Dongbei Yujie entities.

### Resources & Tooltips
- **D-FOOT-11:** Wire up placeholder paths for languages, sounds, and models without bundling unlicensed files.
- **D-FOOT-12:** Add localizable tooltips to explain the flavor and boot behavior of the item.

</decisions>

<canonical_refs>
## Canonical References

- `.planning/PROJECT.md`
- `.planning/REQUIREMENTS.md` - Requirements FOOT-01 through FOOT-07.
- `.planning/ROADMAP.md`
- `.planning/phases/02-daipai-core-system/02-CONTEXT.md`

</canonical_refs>

<code_context>
## Code Context

- `src/main/kotlin/com/columbina/yujie/item/BigSweatyFootItem.kt`
- `src/main/kotlin/com/columbina/yujie/item/BigSweatyFootAuraHandler.kt`
- `src/main/java/com/columbina/yujie/mixin/MobEntityTargetMixin.java`
- `src/main/kotlin/com/columbina/yujie/registry/DongbeiYujieItems.kt`
- `src/main/kotlin/com/columbina/yujie/registry/DongbeiYujieSounds.kt`

</code_context>
