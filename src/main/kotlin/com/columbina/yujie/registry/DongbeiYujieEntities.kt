package com.columbina.yujie.registry

import com.columbina.yujie.DongbeiYujieIds
import com.columbina.yujie.entity.DongbeiYujieEntity
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys

object DongbeiYujieEntities {

    val DONGBEI_YUJIE_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, DongbeiYujieIds.id("dongbei_yujie"))

    val DONGBEI_YUJIE: EntityType<DongbeiYujieEntity> = Registry.register(
        Registries.ENTITY_TYPE,
        DONGBEI_YUJIE_KEY,
        FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ::DongbeiYujieEntity)
            .dimensions(EntityDimensions.fixed(2.5f, 2.5f))
            .build(DONGBEI_YUJIE_KEY)
    )

    val DONGBEI_YUJIE_SPAWN_EGG_KEY = RegistryKey.of(RegistryKeys.ITEM, DongbeiYujieIds.id("dongbei_yujie_spawn_egg"))

    val DONGBEI_YUJIE_SPAWN_EGG: Item = Registry.register(
        Registries.ITEM,
        DONGBEI_YUJIE_SPAWN_EGG_KEY,
        SpawnEggItem(
            Item.Settings().registryKey(DONGBEI_YUJIE_SPAWN_EGG_KEY)
        )
    )

    fun register() {
        FabricDefaultAttributeRegistry.register(DONGBEI_YUJIE, DongbeiYujieEntity.createDongbeiYujieAttributes())

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register { entries ->
            entries.add(DONGBEI_YUJIE_SPAWN_EGG)
        }
    }
}
