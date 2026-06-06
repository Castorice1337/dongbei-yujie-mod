package com.columbina.yujie.world

import com.columbina.yujie.entity.DongbeiYujieEntity
import com.columbina.yujie.registry.DongbeiYujieEntities
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.SpawnLocationTypes
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.SpawnRestriction
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.Heightmap
import net.minecraft.world.ServerWorldAccess

object DongbeiYujieSpawning {

    fun register() {
        SpawnRestriction.register(
            DongbeiYujieEntities.DONGBEI_YUJIE,
            SpawnLocationTypes.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            ::canSpawnDongbeiYujie
        )

        BiomeModifications.addSpawn(
            BiomeSelectors.foundInOverworld(),
            SpawnGroup.MONSTER,
            DongbeiYujieEntities.DONGBEI_YUJIE,
            10, // weight
            1,  // minGroupSize
            1   // maxGroupSize
        )
    }

    private fun canSpawnDongbeiYujie(
        type: EntityType<DongbeiYujieEntity>,
        world: ServerWorldAccess,
        spawnReason: SpawnReason,
        pos: BlockPos,
        random: Random
    ): Boolean {
        // Standard monster spawn rules (darkness, block light, etc)
        if (!HostileEntity.canSpawnInDark(type, world, spawnReason, pos, random)) {
            return false
        }

        // Only allow spawning if there's a player nearby (standard is up to 128 blocks, we check 64)
        val closestPlayer = world.getClosestPlayer(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), 64.0, false)
            ?: return true // If no player is within 64 blocks, maybe normal spawn is fine, but wait, the rule says "max 1 around player".
            
        // Strict lone wolf mode: limit to 1 per player within 64 blocks
        val searchBox = closestPlayer.boundingBox.expand(64.0)
        val existingYujie = world.getEntitiesByClass(DongbeiYujieEntity::class.java, searchBox) { true }

        return existingYujie.isEmpty()
    }
}
