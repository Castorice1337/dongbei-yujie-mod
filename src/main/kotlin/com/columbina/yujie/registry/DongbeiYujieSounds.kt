package com.columbina.yujie.registry

import com.columbina.yujie.DongbeiYujieIds
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent

object DongbeiYujieSounds {
	private val BIG_SWEATY_FOOT_HIT_ID = DongbeiYujieIds.id("big_sweaty_foot_hit")
	val BIG_SWEATY_FOOT_HIT: SoundEvent = SoundEvent.of(BIG_SWEATY_FOOT_HIT_ID)

	fun register() {
		Registry.register(Registries.SOUND_EVENT, BIG_SWEATY_FOOT_HIT_ID, BIG_SWEATY_FOOT_HIT)
	}
}
