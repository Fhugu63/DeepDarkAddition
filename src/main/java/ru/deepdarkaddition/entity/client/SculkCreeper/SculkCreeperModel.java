package ru.deepdarkaddition.entity.client.SculkCreeper;// Made with Blockbench 5.0.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class SculkCreeperModel<T extends Entity>  extends HierarchicalModel<T> {
    private final ModelPart body;
    private final ModelPart front_leg;
    private final ModelPart back_leg;
    private final ModelPart idk_right;
    private final ModelPart idk_left;
    private final ModelPart sound_wave;

    public SculkCreeperModel(ModelPart root) {
        this.body = root.getChild("body");
        this.front_leg = this.body.getChild("front_leg");
        this.back_leg = this.body.getChild("back_leg");
        this.idk_right = this.body.getChild("idk_right");
        this.idk_left = this.body.getChild("idk_left");
        this.sound_wave = root.getChild("sound_wave");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -18.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, -26.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition front_leg = body.addOrReplaceChild("front_leg", CubeListBuilder.create().texOffs(6, 54).addBox(0.0F, -6.0F, -6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(6, 54).mirror().addBox(-4.0F, -6.0F, -6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition back_leg = body.addOrReplaceChild("back_leg", CubeListBuilder.create().texOffs(48, 6).addBox(0.0F, -6.0F, -6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(48, 6).mirror().addBox(-4.0F, -6.0F, -6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 8.0F));

        PartDefinition idk_right = body.addOrReplaceChild("idk_right", CubeListBuilder.create().texOffs(8, 22).addBox(0.0F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(12, 22).addBox(2.0F, -4.0F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(28, 26).addBox(4.0F, -5.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(24, 0).addBox(5.0F, -5.0F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(24, 4).addBox(7.0F, -7.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(28, 4).addBox(6.0F, -8.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(8, 28).addBox(5.0F, -8.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(16, 26).addBox(6.0F, -3.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(14, 28).addBox(8.0F, -3.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -23.0F, 0.0F));

        PartDefinition idk_left = body.addOrReplaceChild("idk_left", CubeListBuilder.create().texOffs(5, 22).addBox(0.1667F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(10, 22).addBox(3.1667F, -4.0F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(28, 26).addBox(4.1667F, -5.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(24, 0).addBox(5.1667F, -5.0F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(24, 4).addBox(7.1667F, -7.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(28, 4).addBox(6.1667F, -8.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(8, 29).addBox(5.1667F, -8.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(16, 26).addBox(6.1667F, -3.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(14, 28).addBox(8.1667F, -3.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8333F, -23.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition sound_wave = partdefinition.addOrReplaceChild("sound_wave", CubeListBuilder.create().texOffs(13, 46).addBox(-9.0F, 0.0F, -9.0F, 17.0F, 0.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        //sound_wave.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }


    @Override
    public ModelPart root() {
        return body;
    }
}