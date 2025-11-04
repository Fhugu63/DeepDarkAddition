package maks.deepdarkaddition.entity.client.luiza

import com.mojang.blaze3d.vertex.PoseStack
import maks.deepdarkaddition.MainScript
import maks.deepdarkaddition.entity.client.HungrySoul.HungrySoulModel
import maks.deepdarkaddition.entity.custom.HungrySoulEntity
import maks.deepdarkaddition.entity.client.ModModelLayers
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.ResourceLocation

class HungrySoulRender(pContext: EntityRendererProvider.Context) :
    MobRenderer<HungrySoulEntity, HungrySoulModel<HungrySoulEntity>>(pContext,
        HungrySoulModel(pContext.bakeLayer(ModModelLayers.HUNGRY_SOUL_LAYER)), 0.5f) {
    override fun getTextureLocation(pEntity: HungrySoulEntity): ResourceLocation? {
        return ResourceLocation.tryBuild(MainScript.MOD_ID, "textures/entity/hungrysoul.png")
        //return null;
    }

    override fun render(
        pEntity: HungrySoulEntity, pEntityYaw: Float, pPartialTicks: Float, pMatrixStack: PoseStack,
        pBuffer: MultiBufferSource, pPackedLight: Int
    ) {/*
        if (pEntity.isBaby()) {
            pMatrixStack.scale(1f, 1f, 1f)
        } else {
            pMatrixStack.scale(2.5f, 2.5f, 2.5f)
        }*/

        pMatrixStack.scale(HungrySoulRender.scale, HungrySoulRender.scale, HungrySoulRender.scale)

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight)
    }

    companion object {
        var scale = 1f
            set(value) {
                if (value <= 1F) {
                    field = value
                } else if (value >= 5F) {
                    field = value
                }
            }

    }
}