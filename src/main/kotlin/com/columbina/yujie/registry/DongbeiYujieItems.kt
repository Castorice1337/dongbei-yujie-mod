package com.columbina.yujie.registry

import com.columbina.yujie.DongbeiYujieIds
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys

object DongbeiYujieItems {
	private val BIG_SWEATY_FOOT_KEY = RegistryKey.of(
		RegistryKeys.ITEM,
		DongbeiYujieIds.id("big_sweaty_foot")
	)

	/**
	 * Minimal command-only Big Sweaty Foot placeholder.
	 * Obtainable only via `/give dongbeiyujie:big_sweaty_foot`.
	 * Phase 3 will add weapon/boot behavior, creative tab, recipes, and loot.
	 */
	val BIG_SWEATY_FOOT: Item = Item(Item.Settings().registryKey(BIG_SWEATY_FOOT_KEY))

	fun register() {
		Registry.register(Registries.ITEM, BIG_SWEATY_FOOT_KEY, BIG_SWEATY_FOOT)
	}
}
