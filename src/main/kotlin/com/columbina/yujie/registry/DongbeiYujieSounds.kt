package com.columbina.yujie.registry

import com.columbina.yujie.DongbeiYujieIds
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent

object DongbeiYujieSounds {
	private val BIG_SWEATY_FOOT_HIT_ID = DongbeiYujieIds.id("big_sweaty_foot_hit")
	private val YUJIE_SPAWN_ID = DongbeiYujieIds.id("yujie_spawn")
	private val YUJIE_IDLE_ID = DongbeiYujieIds.id("yujie_idle")
	private val YUJIE_ATTACK_ID = DongbeiYujieIds.id("yujie_attack")
	private val YUJIE_HURT_ID = DongbeiYujieIds.id("yujie_hurt")
	private val YUJIE_DEATH_ID = DongbeiYujieIds.id("yujie_death")
	private val YUJIE_BGM_ID = DongbeiYujieIds.id("yujie_bgm")
	private val DAIPAI_TRIGGER_ID = DongbeiYujieIds.id("daipai_trigger")

	val BIG_SWEATY_FOOT_HIT: SoundEvent = SoundEvent.of(BIG_SWEATY_FOOT_HIT_ID)
	val YUJIE_SPAWN: SoundEvent = SoundEvent.of(YUJIE_SPAWN_ID)
	val YUJIE_IDLE: SoundEvent = SoundEvent.of(YUJIE_IDLE_ID)
	val YUJIE_ATTACK: SoundEvent = SoundEvent.of(YUJIE_ATTACK_ID)
	val YUJIE_HURT: SoundEvent = SoundEvent.of(YUJIE_HURT_ID)
	val YUJIE_DEATH: SoundEvent = SoundEvent.of(YUJIE_DEATH_ID)
	val YUJIE_BGM: SoundEvent = SoundEvent.of(YUJIE_BGM_ID)
	val DAIPAI_TRIGGER: SoundEvent = SoundEvent.of(DAIPAI_TRIGGER_ID)

	fun register() {
		register(BIG_SWEATY_FOOT_HIT_ID, BIG_SWEATY_FOOT_HIT)
		register(YUJIE_SPAWN_ID, YUJIE_SPAWN)
		register(YUJIE_IDLE_ID, YUJIE_IDLE)
		register(YUJIE_ATTACK_ID, YUJIE_ATTACK)
		register(YUJIE_HURT_ID, YUJIE_HURT)
		register(YUJIE_DEATH_ID, YUJIE_DEATH)
		register(YUJIE_BGM_ID, YUJIE_BGM)
		register(DAIPAI_TRIGGER_ID, DAIPAI_TRIGGER)
	}

	private fun register(id: net.minecraft.util.Identifier, soundEvent: SoundEvent) {
		Registry.register(Registries.SOUND_EVENT, id, soundEvent)
	}
}
