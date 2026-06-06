package com.columbina.yujie.registry

import com.columbina.yujie.DongbeiYujieIds
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.particle.SimpleParticleType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

object DongbeiYujieParticles {
	private val FOOT_HIT_ID = DongbeiYujieIds.id("foot_hit")

	val FOOT_HIT: SimpleParticleType = FabricParticleTypes.simple()

	fun register() {
		Registry.register(Registries.PARTICLE_TYPE, FOOT_HIT_ID, FOOT_HIT)
	}
}
