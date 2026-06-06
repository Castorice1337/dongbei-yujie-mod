package com.columbina.yujie.client.particle

import net.minecraft.client.particle.BillboardParticle
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleFactory
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.SimpleParticleType
import net.minecraft.util.math.random.Random

class FootHitParticle(
	world: ClientWorld,
	x: Double,
	y: Double,
	z: Double,
	spriteProvider: SpriteProvider,
	random: Random
) : BillboardParticle(world, x, y, z, spriteProvider.getSprite(random)) {
	init {
		maxAge = 20
		gravityStrength = 0.0f
		velocityMultiplier = 1.0f
		scale = 0.65f
		setVelocity(0.0, 0.0, 0.0)
	}

	override fun tick() {
		lastX = x
		lastY = y
		lastZ = z
		if (age++ >= maxAge) {
			markDead()
		}
	}

	override fun getRenderType(): RenderType {
		return RenderType.PARTICLE_ATLAS_TRANSLUCENT
	}

	class Factory(private val spriteProvider: SpriteProvider) : ParticleFactory<SimpleParticleType> {
		override fun createParticle(
			parameters: SimpleParticleType,
			world: ClientWorld,
			x: Double,
			y: Double,
			z: Double,
			velocityX: Double,
			velocityY: Double,
			velocityZ: Double,
			random: Random
		): Particle {
			return FootHitParticle(world, x, y, z, spriteProvider, random)
		}
	}
}
