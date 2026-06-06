package com.columbina.yujie

import com.columbina.yujie.registry.DongbeiYujieEnchantments
import com.columbina.yujie.registry.DongbeiYujieEntities
import com.columbina.yujie.registry.DongbeiYujieEffects
import com.columbina.yujie.registry.DongbeiYujieItems
import com.columbina.yujie.registry.DongbeiYujieParticles
import com.columbina.yujie.registry.DongbeiYujieSounds
import com.columbina.yujie.world.DongbeiYujieSpawning
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object DongbeiYujie : ModInitializer {
    private val logger = LoggerFactory.getLogger("dongbeiyujie")

	override fun onInitialize() {
		DongbeiYujieItems.register()
		DongbeiYujieEntities.register()
		DongbeiYujieEffects.register()
		DongbeiYujieEnchantments.register()
		DongbeiYujieSounds.register()
		DongbeiYujieParticles.register()
		DongbeiYujieSpawning.register()

		// Configure Daipai interactions for Dongbei Yujie entity
		com.columbina.yujie.effect.DaipaiStatusEffect.isDongbeiYujie = { entity -> 
			entity is com.columbina.yujie.entity.DongbeiYujieEntity 
		}
		com.columbina.yujie.effect.DaipaiLevelCalculator.specialMinimumProvider = { entity ->
			if (entity is com.columbina.yujie.entity.DongbeiYujieEntity) 3 else 0
		}

		logger.info("Dongbei Yujie foundation initialized.")
	}
}
