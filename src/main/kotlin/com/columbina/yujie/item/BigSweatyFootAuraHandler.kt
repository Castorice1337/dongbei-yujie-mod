package com.columbina.yujie.item

import com.columbina.yujie.effect.DaipaiLevelCalculator
import com.columbina.yujie.effect.DaipaiStatusEffect
import com.columbina.yujie.registry.DongbeiYujieEffects
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.brain.MemoryModuleType
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Vec3d
import java.util.UUID

object BigSweatyFootAuraHandler {
	private const val FEAR_TICK_INTERVAL = 5
	private const val FLEE_DISTANCE_FACTOR = 0.75
	private const val FLEE_SPEED = 1.1
	private val bootGrantedDaipai = mutableSetOf<UUID>()

	fun register() {
		ServerTickEvents.END_SERVER_TICK.register { server ->
			if (server.ticks % FEAR_TICK_INTERVAL != 0) return@register

			for (player in server.playerManager.playerList) {
				tickWearer(player)
			}
		}
	}

	private fun tickWearer(wearer: PlayerEntity) {
		val boots = wearer.getEquippedStack(EquipmentSlot.FEET)
		val wearingBigSweatyFoot = BigSweatyFootItem.isBigSweatyFoot(boots)
		val uuid = wearer.uuid

		if (!wearingBigSweatyFoot) {
			removeBootGrantedDaipai(wearer, uuid)
			return
		}

		ensureMinimumDaipai(wearer, uuid)
		applyFear(wearer)
	}

	private fun ensureMinimumDaipai(wearer: PlayerEntity, uuid: UUID) {
		val current = wearer.getStatusEffect(DongbeiYujieEffects.DAIPAI_ENTRY)
		if (current == null) {
			wearer.addStatusEffect(
				StatusEffectInstance(
					DongbeiYujieEffects.DAIPAI_ENTRY,
					StatusEffectInstance.INFINITE,
					0,
					false,
					true,
					true
				)
			)
			bootGrantedDaipai.add(uuid)
		}
	}

	private fun removeBootGrantedDaipai(wearer: PlayerEntity, uuid: UUID) {
		if (!bootGrantedDaipai.remove(uuid)) return

		val current = wearer.getStatusEffect(DongbeiYujieEffects.DAIPAI_ENTRY)
		if (current != null && current.amplifier == 0 && current.isInfinite) {
			wearer.removeStatusEffect(DongbeiYujieEffects.DAIPAI_ENTRY)
		}
	}

	private fun applyFear(wearer: PlayerEntity) {
		val finalLevel = DaipaiLevelCalculator.calculateFinalLevel(wearer)
		if (finalLevel <= 0) return

		val world = wearer.entityWorld as? ServerWorld ?: return
		val radius = finalLevel * 2.0
		val radiusSquared = radius * radius
		val fleeDistanceSquared = radiusSquared * FLEE_DISTANCE_FACTOR * FLEE_DISTANCE_FACTOR
		val sourcePosition = Vec3d(wearer.x, wearer.y, wearer.z)

		val mobs = world.getEntitiesByClass(MobEntity::class.java, wearer.boundingBox.expand(radius)) { mob: MobEntity ->
			shouldRepel(mob, wearer)
		}

		for (mob in mobs) {
			clearWearerAsTarget(mob, wearer)

			if (mob is PathAwareEntity && mob.squaredDistanceTo(wearer) <= fleeDistanceSquared) {
				val fleeTarget = net.minecraft.entity.ai.NoPenaltyTargeting.findFrom(mob, 8, 4, sourcePosition)
				if (fleeTarget != null) {
					mob.navigation.startMovingTo(fleeTarget.x, fleeTarget.y, fleeTarget.z, FLEE_SPEED)
				}
			}
		}
	}

	@JvmStatic
	fun shouldRepel(mob: MobEntity, target: LivingEntity?): Boolean {
		val wearer = target as? PlayerEntity ?: return false
		val boots = wearer.getEquippedStack(EquipmentSlot.FEET)
		if (!BigSweatyFootItem.isBigSweatyFoot(boots)) return false

		val finalLevel = DaipaiLevelCalculator.calculateFinalLevel(wearer)
		if (finalLevel <= 0) return false

		val radius = finalLevel * 2.0
		return mob.isAlive &&
			!mob.isSpectator &&
			!DaipaiStatusEffect.isDongbeiYujie(mob) &&
			mob.squaredDistanceTo(wearer) <= radius * radius
	}

	private fun clearWearerAsTarget(mob: MobEntity, wearer: PlayerEntity) {
		if (mob.target == wearer) {
			mob.target = null
			mob.navigation.stop()
		}

		val brain = mob.brain
		if (brain.hasMemoryModule(MemoryModuleType.ATTACK_TARGET)) {
			val attackTarget = brain.getOptionalRegisteredMemory(MemoryModuleType.ATTACK_TARGET)
			if (attackTarget.isPresent && attackTarget.get() == wearer) {
				brain.forget(MemoryModuleType.ATTACK_TARGET)
			}
		}
		if (brain.hasMemoryModule(MemoryModuleType.HURT_BY_ENTITY)) {
			val hurtByEntity = brain.getOptionalRegisteredMemory(MemoryModuleType.HURT_BY_ENTITY)
			if (hurtByEntity.isPresent && hurtByEntity.get() == wearer) {
				brain.forget(MemoryModuleType.HURT_BY_ENTITY)
			}
		}
		if (brain.hasMemoryModule(MemoryModuleType.ANGRY_AT)) {
			val angryAt = brain.getOptionalRegisteredMemory(MemoryModuleType.ANGRY_AT)
			if (angryAt.isPresent && angryAt.get() == wearer.uuid) {
				brain.forget(MemoryModuleType.ANGRY_AT)
			}
		}
		if (brain.hasMemoryModule(MemoryModuleType.AVOID_TARGET)) {
			brain.remember(MemoryModuleType.AVOID_TARGET, wearer, (FEAR_TICK_INTERVAL + 5).toLong())
		}
	}
}
