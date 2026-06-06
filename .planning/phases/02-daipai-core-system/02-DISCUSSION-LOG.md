# Phase 2: Daipai Core System - Discussion Log

**Gathered:** 2026-06-06
**Status:** Ready for planning

## Areas Discussed

### Damage Behavior

**Question:** Which Phase 2 gray areas should be discussed?

**Selected:** All major gray areas.

**Notes:** User chose to discuss damage behavior, enchantment acquisition, and later-phase integration boundaries.

**Question:** Who should Daipai periodic range damage be attributed to?

**Selected:** Attribute damage to the Buff holder.

**Options considered:**
- Attribute to Buff holder - clearer death/stat/interaction semantics.
- Attribute to environment - less aggro coupling but less expressive.
- No explicit attribution - simpler but weak for later linked behavior.

**Question:** Should a standalone Daipai Buff trigger range damage without Big Sweaty Foot?

**Selected:** Yes.

**Notes:** Buff itself should be dangerous. Items and enchantments add levels; they do not gate the effect.

**Question:** What should Daipai range damage target?

**Selected:** All living entities.

**Notes:** Players, animals, villagers, and hostile mobs can be affected. Exclusions remain the owner and Dongbei Yujie entities.

**Question:** Does Daipai require line of sight?

**Selected:** No.

**Notes:** It should be a spherical range aura that can damage through walls.

### Enchantment Acquisition

**Question:** Which acquisition paths should Daipai enchantment support?

**Selected:** Enchantment table plus enchanted book and anvil.

**Notes:** This matches the original requirement and gives Phase 2 a real enchantment path.

**Question:** Before full Big Sweaty Foot exists, what item should Daipai enchantment apply to?

**Selected:** Register a minimal placeholder item.

**Notes:** User suggested registering an item first and only writing the enchantment, then implementing concrete behavior later.

**Question:** Should Phase 2 allow a minimal Big Sweaty Foot placeholder item?

**Selected:** Yes, minimal placeholder item.

**Notes:** The placeholder may exist only for enchantment applicability and level calculation testing. Full weapon and boot behavior stays deferred.

**Question:** How should the placeholder item be obtained?

**Selected:** Command only.

**Notes:** Use `/give` for testing. Do not add recipes, loot, or creative tab exposure.

**Question:** How common should Daipai enchantment be?

**Selected:** Relatively common.

**Notes:** This supports the mod's high-enchantability joke balance.

### Future Integration Boundary

**Question:** Is a Daipai Buff required before equipment or enchantment bonuses trigger periodic damage?

**Selected:** Yes.

**Notes:** Equipment and enchantments add to final level only when the entity has Daipai Buff.

**Question:** How should Phase 2 prepare the future "Yujie is at least Daipai III" rule?

**Selected:** Generic special-minimum hook.

**Notes:** Do not hard-code a nonexistent Phase 4 Yujie class.

**Question:** Who is the Daipai aura owner?

**Selected:** Buff holder.

**Notes:** Avoid storing the original applier; the entity with the Buff owns the aura.

**Question:** Should Phase 2 add sound or particle feedback?

**Selected:** Vanilla particle feedback is allowed.

**Notes:** User clarified that vanilla Minecraft particles are acceptable because they do not count as custom resources.

**Question:** How visible should particle feedback be?

**Selected:** Clear and visible, using vanilla heart particles and green star particles.

**Notes:** Keep the effect readable without adding custom resources.

## Deferred Ideas

- Full Big Sweaty Foot weapon, boot, curse, durability, hit-message, and hit-sound behavior remains Phase 3.
- Dongbei Yujie entity and hard entity-type exclusion wiring remains Phase 4.
- Custom sounds, custom textures, BGM, and resource files remain Phase 5.

## Implementation Preferences

- User explicitly requested that later planning use the main agent and avoid subagents because of cost.

---

*Discussion log for Phase 2: Daipai Core System*
