package maks.deepdarkaddition.entity.client.SculkCreeper

import com.mojang.blaze3d.vertex.PoseStack
import maks.deepdarkaddition.MainScript
import maks.deepdarkaddition.entity.client.ModModelLayers
import maks.deepdarkaddition.entity.client.luiza.HungrySoulRender
import maks.deepdarkaddition.entity.custom.HungrySoulEntity
import maks.deepdarkaddition.entity.custom.SculkCreeperEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.ResourceLocation

class SculkCreeperRenderer(pContext: EntityRendererProvider.Context) :
    MobRenderer<SculkCreeperEntity, SculkCreeperModel<SculkCreeperEntity>>(pContext,
    SculkCreeperModel(pContext.bakeLayer(ModModelLayers.SCULK_CREEPER_LAYER)), 0.5f) {

    override fun getTextureLocation(p0: SculkCreeperEntity): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(MainScript.MOD_ID, "textures/entity/sculkcreeper.png")
    }

    override fun render(
        pEntity: SculkCreeperEntity, pEntityYaw: Float, pPartialTicks: Float, pMatrixStack: PoseStack,
        pBuffer: MultiBufferSource, pPackedLight: Int
    ) {
        if (pEntity.isBaby()) {
            pMatrixStack.scale(1f, 1.8f, 1f)
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight)
    }
}