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
    //public static final Material CURTAIN_OPEN_RESOURCE_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(AnotherFurnitureMod.MOD_ID, "block/curtain")); //change to open dyeable texturesheet
    //public static final Material CURTAIN_CLOSED_RESOURCE_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(AnotherFurnitureMod.MOD_ID, "block/curtain"));//change to closed dyeable texturesheet
    private static final String CURTAIN = "curtain";
    private final ModelPart curtain;
    private final ModelPart curtain_l;
    private final ModelPart curtain_r;

    public CurtainRenderer(BlockEntityRendererProvider.Context pContext) {
        ModelPart modelpart = pContext.bakeLayer(ModClientSetup.CURTAIN_MODEL);
        this.curtain = modelpart.getChild("curtain");
        this.curtain_l = modelpart.getChild("curtain_l");
        this.curtain_r = modelpart.getChild("curtain_r");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("curtain", CubeListBuilder.create().texOffs(30, 15).addBox(-8.0F, 9.0F, -2.0F, 16.0F, 32.0F, 1.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("curtain_l", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-8.0F, 9.0F, -2.0F, 16.0F, 5.0F, 1.0F)
                        .texOffs(0, 6).addBox(-8.0F, 14.0F, -2.0F, 15.0F, 2.0F, 1.0F)
                        .texOffs(0, 9).addBox(-8.0F, 16.0F, -2.0F, 14.0F, 2.0F, 1.0F)
                        .texOffs(0, 12).addBox(-8.0F, 18.0F, -2.0F, 13.0F, 2.0F, 1.0F)
                        .texOffs(0, 15).addBox(-8.0F, 20.0F, -2.0F, 12.0F, 1.0F, 1.0F)
                        .texOffs(0, 17).addBox(-8.0F, 21.0F, -2.0F, 11.0F, 1.0F, 1.0F)
                        .texOffs(0, 19).addBox(-8.0F, 22.0F, -2.0F, 10.0F, 1.0F, 1.0F)
                        .texOffs(0, 21).addBox(-8.0F, 23.0F, -2.0F, 9.0F, 1.0F, 1.0F)
                        .texOffs(0, 23).addBox(-8.0F, 24.0F, -2.0F, 8.0F, 1.0F, 1.0F)
                        .texOffs(0, 25).addBox(-8.0F, 25.0F, -2.0F, 7.0F, 1.0F, 1.0F)
                        .texOffs(0, 27).addBox(-8.0F, 26.0F, -2.0F, 6.0F, 1.0F, 1.0F)
                        .texOffs(0, 29).addBox(-8.0F, 27.0F, -2.0F, 5.0F, 1.0F, 1.0F)
                        .texOffs(0, 31).addBox(-8.0F, 28.0F, -2.0F, 6.0F, 3.0F, 1.0F)
                        .texOffs(0, 35).addBox(-8.0F, 31.0F, -2.0F, 5.0F, 1.0F, 1.0F)
                        .texOffs(0, 37).addBox(-8.0F, 32.0F, -2.0F, 6.0F, 8.0F, 1.0F)
                        .texOffs(0, 46).addBox(-8.0F, 40.0F, -2.0F, 5.0F, 1.0F, 1.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("curtain_r", CubeListBuilder.create()
                .texOffs(0, 49).addBox(-8.0F, 9.0F, -2.0F, 16.0F, 5.0F, 1.0F)
                .texOffs(2, 55).addBox(-7.0F, 14.0F, -2.0F, 15.0F, 2.0F, 1.0F)
                .texOffs(4, 58).addBox(-6.0F, 16.0F, -2.0F, 14.0F, 2.0F, 1.0F)
                .texOffs(6, 61).addBox(-5.0F, 18.0F, -2.0F, 13.0F, 2.0F, 1.0F)
                .texOffs(38, 48).addBox(-4.0F, 20.0F, -2.0F, 12.0F, 1.0F, 1.0F)
                .texOffs(40, 50).addBox(-3.0F, 21.0F, -2.0F, 11.0F, 1.0F, 1.0F)
                .texOffs(42, 52).addBox(-2.0F, 22.0F, -2.0F, 10.0F, 1.0F, 1.0F)
                .texOffs(44, 54).addBox(-1.0F, 23.0F, -2.0F, 9.0F, 1.0F, 1.0F)
                .texOffs(46, 56).addBox(0.0F, 24.0F, -2.0F, 8.0F, 1.0F, 1.0F)
                .texOffs(48, 58).addBox(1.0F, 25.0F, -2.0F, 7.0F, 1.0F, 1.0F)
                .texOffs(50, 60).addBox(2.0F, 26.0F, -2.0F, 6.0F, 1.0F, 1.0F)
                .texOffs(52, 62).addBox(3.0F, 27.0F, -2.0F, 5.0F, 1.0F, 1.0F)
                .texOffs(35, 0).addBox(2.0F, 28.0F, -2.0F, 6.0F, 3.0F, 1.0F)
                .texOffs(37, 4).addBox(3.0F, 31.0F, -2.0F, 5.0F, 1.0F, 1.0F)
                .texOffs(50, 0).addBox(2.0F, 32.0F, -2.0F, 6.0F, 8.0F, 1.0F)
                .texOffs(52, 9).addBox(3.0F, 40.0F, -2.0F, 5.0F, 1.0F, 1.0F), PartPose.ZERO);

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public void render(CurtainBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        VertexConsumer vertexconsumer = pBufferSource.getBuffer(RenderType.entityCutout(new ResourceLocation(AnotherFurnitureMod.MOD_ID, "textures/block/curtain/red.png")));
        long i;
        pPoseStack.pushPose();
        //pPoseStack.translate(0.5D, 0.5D, 0.5D);
        i = pBlockEntity.getLevel().getGameTime();
        BlockState blockstate = pBlockEntity.getBlockState();
        pPoseStack.translate(0.5D, (double)-0.16666667F, 0.5D);
        float f3 = -blockstate.getValue(CurtainBlock.FACING).toYRot();
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(f3));
        pPoseStack.translate(0.0D, -0.3125D, -0.4575D);

        pPoseStack.pushPose();
        pPoseStack.scale(1.0F, -1.0F, -1.0F);
        float f2 = ((float)Math.floorMod(i, 100L) + pPartialTick) / 100.0F;
        float xRot = (-0.0125F + 0.01F * Mth.cos(((float)Math.PI * 2F) * f2)) * (float)Math.PI;
        this.curtain.visible = false;
        this.curtain_l.visible = false;
        this.curtain_r.visible = false;

        if (blockstate.getValue(CurtainBlock.OPEN)) {
            CurtainType type = blockstate.getValue(CurtainBlock.TYPE);
            if (type == CurtainType.LEFT) {
                this.curtain_l.visible = true;
                this.curtain_l.xRot = xRot;
                this.curtain_l.y = -32.0F;
                this.curtain_l.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay);
            } else if (type == CurtainType.RIGHT) {
                this.curtain_r.visible = true;
                this.curtain_r.xRot = xRot;
                this.curtain_r.y = -32.0F;
                this.curtain_r.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay);
            }
        } else {
            this.curtain.visible = true;
            this.curtain.xRot = xRot;
            this.curtain.y = -32.0F;
            this.curtain.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay);
        }

        pPoseStack.popPose();
        pPoseStack.popPose();
    }
}