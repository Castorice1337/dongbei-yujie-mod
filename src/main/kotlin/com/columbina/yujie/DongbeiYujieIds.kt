package com.columbina.yujie

import net.minecraft.util.Identifier

object DongbeiYujieIds {
	const val MOD_ID = "dongbeiyujie"

	fun id(path: String): Identifier = Identifier.of(MOD_ID, path)
}
