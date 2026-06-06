package com.columbina.yujie.effect

import com.columbina.yujie.registry.DongbeiYujieParticles
import com.columbina.yujie.registry.DongbeiYujieSounds
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.sound.SoundCategory
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
		// Run every tick so we can spawn walking particles
		return true
	}

	override fun applyUpdateEffect(world: ServerWorld, entity: LivingEntity, amplifier: Int): Boolean {
		val finalLevel = DaipaiLevelCalculator.calculateFinalLevel(entity)
		if (finalLevel <= 0) return true

		// Spawn heart particles when walking
		val velocity = entity.velocity
		val isWalking = (velocity.x * velocity.x + velocity.z * velocity.z) > 0.0001
		if (isWalking && entity.age % 5 == 0) {
			world.spawnParticles(
				net.minecraft.particle.ParticleTypes.HEART,
				entity.x, entity.y + 0.5, entity.z,
				1, 0.3, 0.2, 0.3, 0.0
			)
		}

		// DAIP-03: Run damage logic every 2 seconds (40 ticks)
		if (entity.age % 40 == 0) {
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
					if (target.damage(world, damageSource, damageAmount)) {
						world.spawnParticles(
							DongbeiYujieParticles.FOOT_HIT,
							target.x,
							target.y + target.height * 0.75,
							target.z,
							1,
							0.0,
							0.0,
							0.0,
							0.0
						)
						world.playSound(
							null,
							target.x,
							target.y,
							target.z,
							DongbeiYujieSounds.DAIPAI_TRIGGER,
							SoundCategory.HOSTILE,
							1.0f,
							1.0f
						)
					}
				}
			}

			// Add vanilla green star-like particles (HAPPY_VILLAGER) around the aura owner
			world.spawnParticles(
				net.minecraft.particle.ParticleTypes.HAPPY_VILLAGER,
				entity.x, entity.y + entity.height / 2.0, entity.z,
				10, radius / 2.0, 0.5, radius / 2.0, 0.0
			)
		}

		return true
	}
}
