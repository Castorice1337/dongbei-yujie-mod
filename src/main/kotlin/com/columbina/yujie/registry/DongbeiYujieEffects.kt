package com.columbina.yujie.registry

import com.columbina.yujie.DongbeiYujieIds
import com.columbina.yujie.effect.DaipaiStatusEffect
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry

object DongbeiYujieEffects {
	private val DAIPAI_KEY = RegistryKey.of(
		RegistryKeys.STATUS_EFFECT,
		DongbeiYujieIds.id("daipai")
	)

	/**
	 * Registered Daipai status effect instance.
	 * Use [DAIPAI_ENTRY] for checks that require a RegistryEntry holder.
	 */
	lateinit var DAIPAI: StatusEffect
		private set

	/**
	 * Registry entry holder for the Daipai status effect.
	 * Use this when checking [net.minecraft.entity.LivingEntity.hasStatusEffect]
	 * or creating [net.minecraft.entity.effect.StatusEffectInstance] values.
	 */
	val DAIPAI_ENTRY: RegistryEntry<StatusEffect>
		get() = Registries.STATUS_EFFECT.getEntry(DAIPAI)

	fun register() {
		DAIPAI = Registry.register(
			Registries.STATUS_EFFECT,
			DAIPAI_KEY,
			DaipaiStatusEffect()
		)
	}
}
