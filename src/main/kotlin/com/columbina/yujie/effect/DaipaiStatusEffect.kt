package com.columbina.yujie.effect

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory

/**
 * Daipai status effect — a harmful aura Buff.
 *
 * When a living entity has this Buff, it acts as a Daipai aura owner.
 * Periodic tick damage logic will be added in Plan 02-02.
 * This class is ready for later [canApplyUpdateEffect] / [applyUpdateEffect] overrides.
 */
class DaipaiStatusEffect : StatusEffect(
	StatusEffectCategory.HARMFUL,
	// Sickly green color representing the Daipai aura
	0x4B7A2A
)
