package com.columbina.yujie.registry

import com.columbina.yujie.DongbeiYujieIds
import net.minecraft.enchantment.Enchantment
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys

object DongbeiYujieEnchantments {
	val DAIPAI_KEY: RegistryKey<Enchantment> = RegistryKey.of(
		RegistryKeys.ENCHANTMENT,
		DongbeiYujieIds.id("daipai")
	)

	fun register() {
		// Phase 2: Daipai enchantment is data-driven in 1.21.11.
		// Key is exposed here for logic lookups. JSON is at:
		// src/main/resources/data/dongbeiyujie/enchantment/daipai.json
	}
}
