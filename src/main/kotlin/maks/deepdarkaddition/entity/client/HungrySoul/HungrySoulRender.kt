package net.maks.deepdarkaddition.entity.client.luiza

import com.mojang.blaze3d.vertex.PoseStack
import maks.deepdarkaddition.MainScript
import maks.deepdarkaddition.entity.client.HungrySoul.HungrySoulModel
import maks.deepdarkaddition.entity.custom.HungrySoulEntity
import net.maks.deepdarkaddition.entity.client.ModModelLayers
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.ResourceLocation

class HungrySoulRender(pContext: EntityRendererProvider.Context) : MobRenderer<HungrySoulEntity, HungrySoulModel<HungrySoulEntity>>(
    pContext,
    HungrySoulModel(pContext.bakeLayer(ModModelLayers.HUNGRY_SOUL_LAYER)), 0.5f //проклятая строка кода
) {
    override fun getTextureLocation(pEntity: HungrySoulEntity): ResourceLocation? {
        return ResourceLocation.tryBuild(MainScript.MOD_ID, "textures/entity/hungrysoul.png")
        //return null;
    }

    override fun render(
        pEntity: HungrySoulEntity, pEntityYaw: Float, pPartialTicks: Float, pMatrixStack: PoseStack,
        pBuffer: MultiBufferSource, pPackedLight: Int
    ) {
        if (pEntity.isBaby()) {
            pMatrixStack.scale(1f, 1.8f, 1f)
        }


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight)
    }
}