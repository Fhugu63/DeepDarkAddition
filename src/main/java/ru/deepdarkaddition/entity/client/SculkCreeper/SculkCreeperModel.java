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

    public SculkCreeperModel(ModelPart root) {
        this.body = root.getChild("body");
        this.front_leg = this.body.getChild("front_leg");
        this.back_leg = this.body.getChild("back_leg");
        this.idk_right = this.body.getChild("idk_right");
        this.idk_left = this.body.getChild("idk_left");
    }

    public static LayerDefinition createBodyLayer() {
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();

            PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

            PartDefinition front_leg = body.addOrReplaceChild("front_leg", CubeListBuilder.create().texOffs(6, 54).addBox(0.0F, -6.0F, -6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                    .texOffs(6, 54).mirror().addBox(-4.0F, -6.0F, -6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition back_leg = body.addOrReplaceChild("back_leg", CubeListBuilder.create().texOffs(48, 6).addBox(0.0F, -6.0F, -6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                    .texOffs(48, 6).mirror().addBox(-4.0F, -6.0F, -6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 8.0F));

            PartDefinition idk_right = body.addOrReplaceChild("idk_right", CubeListBuilder.create().texOffs(8, 22).addBox(4.0F, -26.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(12, 22).addBox(6.0F, -27.0F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(28, 26).addBox(8.0F, -28.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(24, 0).addBox(9.0F, -28.0F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(24, 4).addBox(11.0F, -30.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(28, 4).addBox(10.0F, -31.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(8, 28).addBox(9.0F, -31.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(16, 26).addBox(10.0F, -26.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(14, 28).addBox(12.0F, -26.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition idk_left = body.addOrReplaceChild("idk_left", CubeListBuilder.create().texOffs(5, 22).addBox(4.0F, -26.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(10, 22).addBox(6.0F, -27.0F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(28, 26).addBox(8.0F, -28.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(24, 0).addBox(9.0F, -28.0F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(24, 4).addBox(11.0F, -30.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(28, 4).addBox(10.0F, -31.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(8, 29).addBox(9.0F, -31.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(16, 26).addBox(10.0F, -26.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(14, 28).addBox(12.0F, -26.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return body;
    }
}