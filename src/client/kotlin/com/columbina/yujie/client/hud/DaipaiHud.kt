package com.columbina.yujie.client.hud

import com.columbina.yujie.DongbeiYujieIds
import com.columbina.yujie.effect.DaipaiLevelCalculator
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.render.RenderTickCounter

object DaipaiHud {
	private val HUD_ID = DongbeiYujieIds.id("daipai_level")
	private const val TEXT_COLOR = 0xFF55FF55.toInt()
	private const val BACKGROUND_COLOR = 0x88000000.toInt()
	private const val MARGIN = 8
	private const val PADDING_X = 5
	private const val PADDING_Y = 3
	private const val HOTBAR_CLEARANCE = 38

	fun register() {
		HudElementRegistry.attachElementAfter(VanillaHudElements.HOTBAR, HUD_ID, ::render)
	}

	private fun render(context: DrawContext, tickCounter: RenderTickCounter) {
		val client = MinecraftClient.getInstance()
		val player = client.player ?: return
		val level = DaipaiLevelCalculator.calculateFinalLevel(player)
		if (level <= 0) return

		val text = "带派级别: $level"
		val textRenderer = client.textRenderer
		val textWidth = textRenderer.getWidth(text)
		val x = context.scaledWindowWidth - textWidth - MARGIN - PADDING_X * 2
		val y = context.scaledWindowHeight - HOTBAR_CLEARANCE - textRenderer.fontHeight - PADDING_Y * 2

		context.fill(
			x,
			y,
			x + textWidth + PADDING_X * 2,
			y + textRenderer.fontHeight + PADDING_Y * 2,
			BACKGROUND_COLOR
		)
		context.drawTextWithShadow(textRenderer, text, x + PADDING_X, y + PADDING_Y, TEXT_COLOR)
	}
}
