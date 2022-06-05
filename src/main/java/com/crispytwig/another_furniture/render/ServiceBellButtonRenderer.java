package com.crispytwig.another_furniture.render;

import com.crispytwig.another_furniture.block.entity.ServiceBellBlockEntity;
import com.crispytwig.another_furniture.init.ModClientSetup;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.crispytwig.another_furniture.AnotherFurnitureMod.res;

@OnlyIn(Dist.CLIENT)
public class ServiceBellButtonRenderer implements BlockEntityRenderer<ServiceBellBlockEntity> {
    public static final Material BELL_TEXTURE = new Material(TextureAtlas.LOCATION_BLOCKS, res("block/service_bell"));
    private final ModelPart button;
    public ServiceBellButtonRenderer(BlockEntityRendererProvider.Context pContext) {
        this.button = pContext.bakeLayer(ModClientSetup.SERVICE_BELL_MODEL);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("button", CubeListBuilder.create().texOffs(19, 11).addBox(-9.0F, -7.0F, 7.0F, 2.0F, 1.0F, 2.0F), PartPose.ZERO);
        partdefinition.getChild("button").addOrReplaceChild("button1", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, -6.0F, 7.5F, 1.0F, 1.0F, 1.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 32, 32);
    }



    @Override
    public void render(ServiceBellBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        float f = (float)pBlockEntity.ticks + pPartialTick;
        float f3 = 0f;
        if (pBlockEntity.shaking) {
            f3 = -(pBlockEntity.ticks / 25.0f);

        }
        pPoseStack.translate(0, f3, 0);


        pPoseStack.pushPose();
        pPoseStack.scale(-1f, -1f, 1f);

        this.button.render(pPoseStack, BELL_TEXTURE.buffer(pBufferSource, RenderType::entitySolid), pPackedLight, pPackedOverlay);

        pPoseStack.popPose();
    }
}
