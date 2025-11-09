package ru.deepdarkaddition.entity.client.HungrySoul;// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class HungrySoulModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart body;
	private final ModelPart SecondsSphere;
	private final ModelPart a;
	private final ModelPart c;
	private final ModelPart d;
	private final ModelPart e;
	private final ModelPart b;
	private final ModelPart f;

	public HungrySoulModel(ModelPart root) {
		this.body = root.getChild("body");
		this.SecondsSphere = this.body.getChild("SecondsSphere");
		this.a = this.SecondsSphere.getChild("a");
		this.c = this.SecondsSphere.getChild("c");
		this.d = this.SecondsSphere.getChild("d");
		this.e = this.SecondsSphere.getChild("e");
		this.b = this.SecondsSphere.getChild("b");
		this.f = this.SecondsSphere.getChild("f");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition SecondsSphere = body.addOrReplaceChild("SecondsSphere", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition a = SecondsSphere.addOrReplaceChild("a", CubeListBuilder.create().texOffs(16, 8).addBox(1.0F, 0.0F, -2.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(8, 14).addBox(-1.0F, 0.0F, 1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 15).addBox(-2.0F, 0.0F, 1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 9).addBox(-2.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 10).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition c = SecondsSphere.addOrReplaceChild("c", CubeListBuilder.create().texOffs(16, 13).addBox(1.0F, 0.0F, -2.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(16, 1).addBox(-1.0F, 0.0F, 1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 2).addBox(-2.0F, 0.0F, 1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 14).addBox(-2.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 15).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition d = SecondsSphere.addOrReplaceChild("d", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 3).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(16, 3).addBox(-1.0F, 0.0F, 1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 4).addBox(-2.0F, 0.0F, 1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 16).addBox(-2.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -2.0F, -1.5708F, 0.0F, 1.5708F));

		PartDefinition e = SecondsSphere.addOrReplaceChild("e", CubeListBuilder.create().texOffs(8, 12).addBox(0.0F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 6).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(16, 5).addBox(-1.0F, 0.0F, 1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 6).addBox(-2.0F, 0.0F, 1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 17).addBox(-2.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 17).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 2.0F, -1.5708F, 0.0F, 1.5708F));

		PartDefinition b = SecondsSphere.addOrReplaceChild("b", CubeListBuilder.create().texOffs(0, 10).addBox(0.0F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 7).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-1.0F, 0.0F, 1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 0).addBox(-2.0F, 0.0F, 1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 11).addBox(-2.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 12).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -2.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition f = SecondsSphere.addOrReplaceChild("f", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 9).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(16, 7).addBox(-1.0F, 0.0F, 1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 16).addBox(-2.0F, 0.0F, 1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 17).addBox(-2.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 17).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -2.0F, 0.0F, 0.0F, -1.5708F, 1.5708F));

		return LayerDefinition.create(meshdefinition, 32, 32);
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