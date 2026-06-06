package com.columbina.yujie.effect

import com.columbina.yujie.item.BigSweatyFootItem
import com.columbina.yujie.registry.DongbeiYujieEffects
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack

/**
 * Calculates the final Daipai level for a living entity.
 *
 * The final level is composed of:
 * 1. Base level from the Daipai Buff amplifier (amplifier + 1 → player-facing level)
 * 2. +1 if Big Sweaty Foot is equipped in the feet slot
 * 3. +1 if Big Sweaty Foot is held in the main hand
 * 4. Daipai enchantment level from relevant Big Sweaty Foot stacks
 * 5. A generic special minimum for specific entity types (Phase 4 extension point)
 *
 * Returns 0 when the entity does not have the Daipai Buff.
 */
object DaipaiLevelCalculator {

	/**
	 * Implementation of Daipai enchantment level lookup.
	 * Returns the total enchantment bonus from the given Big Sweaty Foot stacks.
	 */
	var enchantmentLevelProvider: (entity: LivingEntity, stacks: List<ItemStack>) -> Int = { entity, stacks ->
		val registryManager = entity.registryManager
		val enchantmentRegistry = registryManager.getOptional(net.minecraft.registry.RegistryKeys.ENCHANTMENT)
		
		var total = 0
		if (enchantmentRegistry.isPresent) {
			val daipaiEntryOpt = enchantmentRegistry.get().getEntry(com.columbina.yujie.registry.DongbeiYujieEnchantments.DAIPAI_KEY.value)
			if (daipaiEntryOpt.isPresent) {
				val daipaiEntry = daipaiEntryOpt.get()
				for (stack in stacks) {
					total += net.minecraft.enchantment.EnchantmentHelper.getLevel(daipaiEntry, stack)
				}
			}
		}
		total
	}

	/**
	 * Extension point for entity-specific special minimum Daipai level.
	 * Phase 4 will register a provider that returns the special minimum
	 * for Dongbei Yujie entities without hard-coding entity classes here.
	 * Returns 0 for entities without a special minimum.
	 */
	var specialMinimumProvider: (entity: LivingEntity) -> Int = { _ -> 0 }

	/**
	 * Calculate the final Daipai level for the given living entity.
	 *
	 * @param entity the living entity to calculate for
	 * @return the final Daipai level, or 0 if the entity has no Daipai Buff
	 */
	fun calculateFinalLevel(entity: LivingEntity): Int {
		val effectInstance = entity.getStatusEffect(DongbeiYujieEffects.DAIPAI_ENTRY)
			?: return 0

		// D-09: Convert 0-based amplifier to player-facing base level
		val baseLevel = effectInstance.amplifier + 1

		// Collect Big Sweaty Foot stacks in relevant slots
		val feetStack = entity.getEquippedStack(EquipmentSlot.FEET)
		val mainHandStack = entity.getEquippedStack(EquipmentSlot.MAINHAND)

		val isBigSweatyFootInFeet = isBigSweatyFoot(feetStack)
		val isBigSweatyFootInMainHand = isBigSweatyFoot(mainHandStack)

		// +1 for Big Sweaty Foot in feet slot
		val feetBonus = if (isBigSweatyFootInFeet) 1 else 0

		// +1 for Big Sweaty Foot in main hand
		val mainHandBonus = if (isBigSweatyFootInMainHand) 1 else 0

		// Collect relevant Big Sweaty Foot stacks for enchantment lookup
		val bigSweatyFootStacks = buildList {
			if (isBigSweatyFootInFeet) add(feetStack)
			if (isBigSweatyFootInMainHand) add(mainHandStack)
		}

		// D-14: Enchantment bonus from Daipai enchantment on Big Sweaty Foot stacks
		val enchantmentBonus = enchantmentLevelProvider(entity, bigSweatyFootStacks)

		// Calculate total from Buff, equipment, and enchantment
		val calculatedLevel = baseLevel + feetBonus + mainHandBonus + enchantmentBonus

		// D-10: Apply generic special minimum without hard-coding entity classes
		val specialMinimum = specialMinimumProvider(entity)

		return maxOf(calculatedLevel, specialMinimum)
	}

	private fun isBigSweatyFoot(stack: ItemStack): Boolean {
		return BigSweatyFootItem.isBigSweatyFoot(stack)
	}
}
