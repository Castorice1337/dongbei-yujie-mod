package com.columbina.yujie.registry

import com.columbina.yujie.DongbeiYujieIds
import com.columbina.yujie.item.BigSweatyFootAuraHandler
import com.columbina.yujie.item.BigSweatyFootItem
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.component.type.WeaponComponent
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Unit

object DongbeiYujieItems {
	private val BIG_SWEATY_FOOT_KEY = RegistryKey.of(
		RegistryKeys.ITEM,
		DongbeiYujieIds.id("big_sweaty_foot")
	)

	val BIG_SWEATY_FOOT: Item = BigSweatyFootItem(
		Item.Settings()
			.registryKey(BIG_SWEATY_FOOT_KEY)
			.maxCount(1)
			.enchantable(30)
			.equippable(EquipmentSlot.FEET)
			.component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE)
			.component(DataComponentTypes.WEAPON, WeaponComponent(0))
			.attributeModifiers(createBigSweatyFootAttributes())
	)

	fun register() {
		Registry.register(Registries.ITEM, BIG_SWEATY_FOOT_KEY, BIG_SWEATY_FOOT)
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register { entries ->
			entries.add(BIG_SWEATY_FOOT)
		}
		BigSweatyFootAuraHandler.register()
	}

	private fun createBigSweatyFootAttributes(): AttributeModifiersComponent {
		return AttributeModifiersComponent.builder()
			.add(
				EntityAttributes.ATTACK_DAMAGE,
				EntityAttributeModifier(
					DongbeiYujieIds.id("big_sweaty_foot.attack_damage"),
					12.0,
					EntityAttributeModifier.Operation.ADD_VALUE
				),
				AttributeModifierSlot.MAINHAND
			)
			.add(
				EntityAttributes.ATTACK_SPEED,
				EntityAttributeModifier(
					DongbeiYujieIds.id("big_sweaty_foot.attack_speed"),
					-2.2,
					EntityAttributeModifier.Operation.ADD_VALUE
				),
				AttributeModifierSlot.MAINHAND
			)
			.add(
				EntityAttributes.ARMOR,
				EntityAttributeModifier(
					DongbeiYujieIds.id("big_sweaty_foot.armor"),
					6.0,
					EntityAttributeModifier.Operation.ADD_VALUE
				),
				AttributeModifierSlot.FEET
			)
			.build()
	}
}
