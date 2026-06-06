package com.columbina.yujie.client.render

import com.columbina.yujie.DongbeiYujieIds
import com.columbina.yujie.entity.DongbeiYujieEntity
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.RenderLayers
import net.minecraft.client.render.command.OrderedRenderCommandQueue
import net.minecraft.client.render.state.CameraRenderState
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.RotationAxis

import net.minecraft.client.render.entity.state.EntityRenderState
import net.minecraft.client.MinecraftClient

class DongbeiYujieRenderState : EntityRenderState() {
    var cameraYaw: Float = 0.0f
}

class DongbeiYujieEntityRenderer(ctx: EntityRendererFactory.Context) : EntityRenderer<DongbeiYujieEntity, DongbeiYujieRenderState>(ctx) {

    private val texture = DongbeiYujieIds.id("textures/entity/dongbeiyujie.png")

    override fun createRenderState(): DongbeiYujieRenderState {
        return DongbeiYujieRenderState()
    }

    override fun updateRenderState(entity: DongbeiYujieEntity, state: DongbeiYujieRenderState, tickDelta: Float) {
        super.updateRenderState(entity, state, tickDelta)
        state.cameraYaw = MinecraftClient.getInstance().gameRenderer.camera.yaw
    }

    override fun render(
        state: DongbeiYujieRenderState,
        matrices: MatrixStack,
        commandQueue: OrderedRenderCommandQueue,
        cameraRenderState: CameraRenderState
    ) {
        matrices.push()
        
        // Translate up by half the height since the origin is bottom-center
        val height = 2.5f
        val width = 2.5f
        matrices.translate(0.0, height / 2.0.toDouble(), 0.0)

        // Cylindrical billboard: rotate around Y-axis to face camera
        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(state.cameraYaw))

        val light = state.light
        val halfWidth = width / 2.0f
        val halfHeight = height / 2.0f

        commandQueue.submitCustom(matrices, RenderLayers.entityCutoutNoCull(texture)) { entry, vertexConsumer ->
            // Draw quad
            vertexConsumer.vertex(entry, -halfWidth, -halfHeight, 0.0f).color(255, 255, 255, 255).texture(1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(entry, 0.0f, 0.0f, -1.0f)
            vertexConsumer.vertex(entry, -halfWidth, halfHeight, 0.0f).color(255, 255, 255, 255).texture(1.0f, 0.0f).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(entry, 0.0f, 0.0f, -1.0f)
            vertexConsumer.vertex(entry, halfWidth, halfHeight, 0.0f).color(255, 255, 255, 255).texture(0.0f, 0.0f).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(entry, 0.0f, 0.0f, -1.0f)
            vertexConsumer.vertex(entry, halfWidth, -halfHeight, 0.0f).color(255, 255, 255, 255).texture(0.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(entry, 0.0f, 0.0f, -1.0f)
        }

        matrices.pop()
        super.render(state, matrices, commandQueue, cameraRenderState)
    }
}
