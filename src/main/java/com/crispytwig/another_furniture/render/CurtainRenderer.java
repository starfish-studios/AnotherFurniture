package com.crispytwig.another_furniture.render;

import com.crispytwig.another_furniture.AnotherFurnitureMod;
import com.crispytwig.another_furniture.block.CurtainBlock;
import com.crispytwig.another_furniture.block.entity.CurtainBlockEntity;
import com.crispytwig.another_furniture.block.properties.CurtainType;
import com.crispytwig.another_furniture.init.ModClientSetup;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CurtainRenderer implements BlockEntityRenderer<CurtainBlockEntity> {
    public static final Material CURTAIN_OPEN_RESOURCE_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(AnotherFurnitureMod.MOD_ID, "block/curtain")); //change to open dyeable texturesheet
    public static final Material CURTAIN_CLOSED_RESOURCE_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(AnotherFurnitureMod.MOD_ID, "block/curtain"));//change to closed dyeable texturesheet
    private static final String CURTAIN = "curtain";
    private final ModelPart curtain;

    public CurtainRenderer(BlockEntityRendererProvider.Context pContext) {
        ModelPart modelpart = pContext.bakeLayer(ModClientSetup.CURTAIN_MODEL);
        this.curtain = modelpart.getChild("curtain");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("curtain", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 9.0F, -2.0F, 16.0F, 32.0F, 1.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public void render(CurtainBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        long i;
        pPoseStack.pushPose();
        //pPoseStack.translate(0.5D, 0.5D, 0.5D);
        i = pBlockEntity.getLevel().getGameTime();
        BlockState blockstate = pBlockEntity.getBlockState();
        pPoseStack.translate(0.5D, (double)-0.16666667F, 0.5D);
        float f3 = -blockstate.getValue(CurtainBlock.FACING).toYRot();
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(f3));
        pPoseStack.translate(0.0D, -0.3125D, -0.4375D);

        pPoseStack.pushPose();
        pPoseStack.scale(0.99F, -0.99F, -0.99F);
        float f2 = ((float)Math.floorMod(i, 100L) + pPartialTick) / 100.0F;
        this.curtain.xRot = (-0.0125F + 0.01F * Mth.cos(((float)Math.PI * 2F) * f2)) * (float)Math.PI;
        this.curtain.y = -32.0F;
        this.curtain.visible = true;
        VertexConsumer vertexconsumer;
        if (blockstate.getValue(CurtainBlock.OPEN)) {
            CurtainType type = blockstate.getValue(CurtainBlock.TYPE);
            if (type == CurtainType.RIGHT) {
                pPoseStack.scale(-1.0F, 1F, 1F);
            } else if (type == CurtainType.MIDDLE) {
                this.curtain.visible = false;
            }
            vertexconsumer = CURTAIN_OPEN_RESOURCE_LOCATION.buffer(pBufferSource, RenderType::entityCutoutNoCull);
        } else {
            vertexconsumer = CURTAIN_CLOSED_RESOURCE_LOCATION.buffer(pBufferSource, RenderType::entityCutoutNoCullZOffset);
        }
        this.curtain.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay);
        pPoseStack.popPose();
        pPoseStack.popPose();
    }
}