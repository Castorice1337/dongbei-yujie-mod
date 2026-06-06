package com.columbina.yujie.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld

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
) {
	companion object {
		/**
		 * Extension point to identify Dongbei Yujie entities without hard-coding
		 * their class before Phase 4.
		 */
		var isDongbeiYujie: (LivingEntity) -> Boolean = { _ -> false }
	}

	override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
		// DAIP-03: Run every 2 seconds (40 ticks)
		return duration % 40 == 0
	}

	override fun applyUpdateEffect(world: ServerWorld, entity: LivingEntity, amplifier: Int): Boolean {
		val finalLevel = DaipaiLevelCalculator.calculateFinalLevel(entity)
		if (finalLevel <= 0) return true

		val radius = finalLevel * 2.0
		val damageAmount = finalLevel.toFloat()

		val box = entity.boundingBox.expand(radius)

		// DAIP-04: Find all living entities in range, excluding owner and Yujie
		val targets = world.getEntitiesByClass(LivingEntity::class.java, box) { target ->
			target != entity &&
			target.isAlive &&
			!target.isSpectator &&
			!isDongbeiYujie(target)
		}

		// DAIP-05: Attribute damage to the aura owner
		val damageSource = if (entity is PlayerEntity) {
			entity.damageSources.playerAttack(entity)
		} else {
			entity.damageSources.mobAttack(entity)
		}

		val radiusSquared = radius * radius
		for (target in targets) {
			if (entity.squaredDistanceTo(target) <= radiusSquared) {
				target.damage(world, damageSource, damageAmount)
			}
		}

		return true
	}
}
