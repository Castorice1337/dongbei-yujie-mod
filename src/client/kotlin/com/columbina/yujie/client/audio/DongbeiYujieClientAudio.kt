package com.columbina.yujie.client.audio

import com.columbina.yujie.entity.DongbeiYujieEntity
import com.columbina.yujie.registry.DongbeiYujieSounds
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.client.sound.MovingSoundInstance
import net.minecraft.client.sound.SoundInstance
import net.minecraft.sound.SoundCategory

object DongbeiYujieClientAudio {
	private const val BGM_RANGE = 64.0
	private const val BGM_RANGE_SQUARED = BGM_RANGE * BGM_RANGE
	private var currentBgm: YujieBgmSoundInstance? = null

	fun register() {
		ClientTickEvents.END_CLIENT_TICK.register { client ->
			tick(client)
		}
	}

	private fun tick(client: MinecraftClient) {
		val player = client.player
		val world = client.world
		if (player == null || world == null) {
			stopCurrent(client)
			return
		}

		val nearestYujie = world.entities
			.asSequence()
			.filterIsInstance<DongbeiYujieEntity>()
			.filter { it.isAlive && !it.isRemoved && player.squaredDistanceTo(it) <= BGM_RANGE_SQUARED }
			.minByOrNull { player.squaredDistanceTo(it) }

		if (nearestYujie == null) {
			stopCurrent(client)
			return
		}

		val current = currentBgm
		if (
			current == null ||
			current.isDone ||
			!client.soundManager.isPlaying(current) ||
			!current.tracks(nearestYujie)
		) {
			stopCurrent(client)
			val next = YujieBgmSoundInstance(nearestYujie)
			currentBgm = next
			client.soundManager.play(next)
		}
	}

	private fun stopCurrent(client: MinecraftClient) {
		currentBgm?.let { sound ->
			sound.stop()
			client.soundManager.stop(sound)
		}
		currentBgm = null
	}
}

class YujieBgmSoundInstance(private val yujie: DongbeiYujieEntity) : MovingSoundInstance(
	DongbeiYujieSounds.YUJIE_BGM,
	SoundCategory.HOSTILE,
	SoundInstance.createRandom()
) {
	init {
		repeat = true
		repeatDelay = 0
		volume = 4.0f
		pitch = 1.0f
		attenuationType = SoundInstance.AttenuationType.LINEAR
		updatePosition()
	}

	override fun tick() {
		if (!yujie.isAlive || yujie.isRemoved) {
			stop()
			return
		}
		updatePosition()
	}

	fun tracks(entity: DongbeiYujieEntity): Boolean {
		return yujie.id == entity.id
	}

	fun stop() {
		setDone()
	}

	private fun updatePosition() {
		x = yujie.x
		y = yujie.y
		z = yujie.z
	}
}
