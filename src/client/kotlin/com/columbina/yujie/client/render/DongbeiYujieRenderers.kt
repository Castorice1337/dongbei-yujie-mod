package com.columbina.yujie.client.render

import com.columbina.yujie.registry.DongbeiYujieEntities
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

object DongbeiYujieRenderers {
    fun register() {
        EntityRendererRegistry.register(DongbeiYujieEntities.DONGBEI_YUJIE, ::DongbeiYujieEntityRenderer)
    }
}
