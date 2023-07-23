package com.starfish_studios.another_furniture.client.renderer.blockentity;

//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import com.mojang.math.Vector3f;
//import com.starfish_studios.another_furniture.AnotherFurniture;
//import com.starfish_studios.another_furniture.block.GrandfatherClockBlock;
//import com.starfish_studios.another_furniture.block.entity.GrandfatherClockBlockEntity;
//import com.starfish_studios.another_furniture.block.entity.ServiceBellBlockEntity;
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;
//import net.minecraft.client.model.geom.ModelLayerLocation;
//import net.minecraft.client.model.geom.ModelPart;
//import net.minecraft.client.model.geom.PartPose;
//import net.minecraft.client.model.geom.builders.CubeListBuilder;
//import net.minecraft.client.model.geom.builders.LayerDefinition;
//import net.minecraft.client.model.geom.builders.MeshDefinition;
//import net.minecraft.client.model.geom.builders.PartDefinition;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
//import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
//import net.minecraft.client.renderer.texture.TextureAtlas;
//import net.minecraft.client.resources.model.Material;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.util.Mth;
//import net.minecraft.world.level.Level;
//
//@Environment(value=EnvType.CLIENT)
//public class GrandfatherClockRenderer implements BlockEntityRenderer<GrandfatherClockBlockEntity> {
//    public static final Material GRANDFATHER_CLOCK_TEXTURE = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(AnotherFurniture.MOD_ID, "block/grandfather_clock/animated"));
//    public static ModelLayerLocation GRANDFATHER_CLOCK_MODEL = new ModelLayerLocation(new ResourceLocation(AnotherFurniture.MOD_ID, "grandfather_clock"), "grandfather_clock");
//    private final ModelPart hour_hand;
//    private final ModelPart minute_hand;
//    private final ModelPart pendulum;
//
//    public GrandfatherClockRenderer(BlockEntityRendererProvider.Context context) {
//        ModelPart model = context.bakeLayer(GRANDFATHER_CLOCK_MODEL);
//        this.minute_hand = model.getChild("minute_hand");
//        this.hour_hand = model.getChild("hour_hand");
//        this.pendulum = model.getChild("pendulum");
//    }
//
//    public static LayerDefinition createBodyLayer() {
//        MeshDefinition mesh = new MeshDefinition();
//        PartDefinition root = mesh.getRoot();
//        root.addOrReplaceChild("hour_hand", CubeListBuilder.create()
//                        .texOffs(0, 0)
//                        .addBox(-0.5F, 0.0F, 0.0F, 1.0F, 3.0F, 0.0F),
//                PartPose.offset(0, 24, 0));
//
//        root.addOrReplaceChild("minute_hand", CubeListBuilder.create()
//                        .texOffs(2, 0)
//                        .addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 0.0F),
//                PartPose.offset(0, 24, 0));
//        root.addOrReplaceChild("pendulum", CubeListBuilder.create()
//                        .texOffs(2, 0)
//                        .addBox(-0.5F, 0.0F, 0.0F, 3.0F, 11.0F, 0.0F),
//                PartPose.offset(0, 0, 0));
//
//        return LayerDefinition.create(mesh, 16, 16);
//    }
//
//    @Override
//    public void render(GrandfatherClockBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
//        VertexConsumer builder = GRANDFATHER_CLOCK_TEXTURE.buffer(bufferSource, RenderType::entitySolid);
//
//        float hourRot = blockEntity.getHourRoll(partialTick);
//        float minuteRot = blockEntity.getMinuteRoll(partialTick);
//
//        poseStack.pushPose();
//        poseStack.translate(0.5d, 0.375d, 0.5d);
//        poseStack.mulPose(Vector3f.YP.rotationDegrees(-blockEntity.getBlockState().getValue(GrandfatherClockBlock.FACING).toYRot()));
//        poseStack.mulPose(Vector3f.YP.rotationDegrees(180));
//        //poseStack.mulPose(Vector3f.XP.rotationDegrees(90));
//
//        this.pendulum.render(poseStack, builder, packedLight, packedOverlay);
//        //hours
//        poseStack.pushPose();
//
//        poseStack.mulPose(Vector3f.ZP.rotationDegrees(hourRot));
//        poseStack.translate(0, -1.5, -0.283 + 0.02083333);
//
//        this.hour_hand.render(poseStack, builder, packedLight, packedOverlay);
//
//        poseStack.popPose();
//
//        //minutes
//        poseStack.pushPose();
//
//        poseStack.mulPose(Vector3f.ZP.rotationDegrees(minuteRot));
//        poseStack.translate(0, -1.5, -0.3 + 0.04166667);
//
//        this.minute_hand.render(poseStack, builder, packedLight, packedOverlay);
//
//        poseStack.popPose();
//        poseStack.popPose();
//    }
//}
