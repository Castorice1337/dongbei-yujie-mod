package com.columbina.yujie.item

import com.columbina.yujie.registry.DongbeiYujieEnchantments
import com.columbina.yujie.registry.DongbeiYujieSounds
import net.minecraft.component.type.TooltipDisplayComponent
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.Item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Formatting
import net.minecraft.util.Hand
import net.minecraft.world.World
import java.util.function.Consumer

class BigSweatyFootItem(settings: Settings) : Item(settings) {
	override fun use(world: World, user: PlayerEntity, hand: Hand): ActionResult {
		return ActionResult.PASS
	}

	override fun inventoryTick(stack: ItemStack, world: net.minecraft.server.world.ServerWorld, entity: net.minecraft.entity.Entity, slot: EquipmentSlot?) {
		if (slot == EquipmentSlot.FEET && entity is LivingEntity) {
			ensureBindingCurse(stack, entity)
		}
	}

	override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity) {
		target.playSound(DongbeiYujieSounds.BIG_SWEATY_FOOT_HIT, 1.0f, 1.0f)

		// FOOT-04: Big Sweaty Foot can apply the Daipai effect to hit targets
		target.addStatusEffect(
			net.minecraft.entity.effect.StatusEffectInstance(
				com.columbina.yujie.registry.DongbeiYujieEffects.DAIPAI_ENTRY,
				100, // 5 seconds (100 ticks)
				0,   // Daipai I (amplifier 0)
				false,
				true,
				true
			)
		)

		if (attacker is PlayerEntity) {
			val key = HIT_MESSAGE_KEYS[target.random.nextInt(HIT_MESSAGE_KEYS.size)]
			attacker.sendMessage(Text.translatable(key, target.displayName), false)
		}
	}

	@Suppress("OVERRIDE_DEPRECATION")
	override fun appendTooltip(
		stack: ItemStack,
		context: TooltipContext,
		displayComponent: TooltipDisplayComponent,
		textConsumer: Consumer<Text>,
		type: TooltipType
	) {
		textConsumer.accept(Text.translatable("tooltip.dongbeiyujie.big_sweaty_foot.flavor").formatted(Formatting.GRAY))
		textConsumer.accept(Text.translatable("tooltip.dongbeiyujie.big_sweaty_foot.boots").formatted(Formatting.DARK_AQUA))
	}

	private fun ensureBindingCurse(stack: ItemStack, entity: LivingEntity) {
		val enchantmentRegistry = entity.registryManager.getOptional(RegistryKeys.ENCHANTMENT)
		if (enchantmentRegistry.isEmpty) return

		val bindingEntry = enchantmentRegistry.get().getEntry(Enchantments.BINDING_CURSE.value)
		if (bindingEntry.isEmpty) return
		if (EnchantmentHelper.getLevel(bindingEntry.get(), stack) > 0) return

		EnchantmentHelper.apply(stack) { builder ->
			builder.set(bindingEntry.get(), 1)
		}
	}

	companion object {
		private val HIT_MESSAGE_KEYS = listOf(
			"message.dongbeiyujie.big_sweaty_foot_hit.0",
			"message.dongbeiyujie.big_sweaty_foot_hit.1",
			"message.dongbeiyujie.big_sweaty_foot_hit.2",
			"message.dongbeiyujie.big_sweaty_foot_hit.3",
			"message.dongbeiyujie.big_sweaty_foot_hit.4"
		)
		fun isBigSweatyFoot(stack: ItemStack): Boolean {
			return stack.item is BigSweatyFootItem
		}

		fun getDaipaiEnchantmentLevel(entity: LivingEntity, stack: ItemStack): Int {
			val enchantmentRegistry = entity.registryManager.getOptional(RegistryKeys.ENCHANTMENT)
			if (enchantmentRegistry.isEmpty) return 0

			val daipaiEntry = enchantmentRegistry.get().getEntry(DongbeiYujieEnchantments.DAIPAI_KEY.value)
			if (daipaiEntry.isEmpty) return 0

			return EnchantmentHelper.getLevel(daipaiEntry.get(), stack)
		}
	}
}
