package com.columbina.yujie.client.datagen

object DongbeiYujieResourceConventions {
	val languageLocales = listOf("zh_cn", "en_us")

	object Items {
		const val MODEL_DIRECTORY = "models/item"
		const val TEXTURE_DIRECTORY = "textures/item"
	}

	object Entities {
		const val TEXTURE_DIRECTORY = "textures/entity"
		const val YUJIE_TEXTURE = "textures/entity/dongbei_yujie.png"
	}

	object LootTables {
		const val ENTITY_DIRECTORY = "loot_tables/entities"
	}

	object Sounds {
		const val METADATA_FILE = "sounds.json"
		const val EVENT_PREFIX = "entity.dongbei_yujie"
	}
}
