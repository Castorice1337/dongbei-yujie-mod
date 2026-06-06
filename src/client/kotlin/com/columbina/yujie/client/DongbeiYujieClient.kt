package com.columbina.yujie.client

import com.columbina.yujie.client.audio.DongbeiYujieClientAudio
import com.columbina.yujie.client.render.DongbeiYujieRenderers
import net.fabricmc.api.ClientModInitializer

object DongbeiYujieClient : ClientModInitializer {
	override fun onInitializeClient() {
		// Intentionally not registered in fabric.mod.json until Phase 4 or Phase 5 needs client setup.
		DongbeiYujieRenderers.register()
		DongbeiYujieClientAudio.register()
	}
}
