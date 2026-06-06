package com.columbina.yujie.client

import com.columbina.yujie.client.audio.DongbeiYujieClientAudio
import com.columbina.yujie.client.hud.DaipaiHud
import com.columbina.yujie.client.particle.FootHitParticle
import com.columbina.yujie.client.render.DongbeiYujieRenderers
import com.columbina.yujie.registry.DongbeiYujieParticles
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.api.ClientModInitializer

object DongbeiYujieClient : ClientModInitializer {
	override fun onInitializeClient() {
		DongbeiYujieRenderers.register()
		DaipaiHud.register()
		ParticleFactoryRegistry.getInstance().register(DongbeiYujieParticles.FOOT_HIT) { spriteProvider ->
			FootHitParticle.Factory(spriteProvider)
		}
		DongbeiYujieClientAudio.register()
	}
}
