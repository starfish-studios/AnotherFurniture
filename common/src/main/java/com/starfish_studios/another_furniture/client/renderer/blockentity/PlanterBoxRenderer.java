package com.starfish_studios.another_furniture.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.starfish_studios.another_furniture.block.PlanterBoxBlock;
import com.starfish_studios.another_furniture.block.entity.PlanterBoxBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

@Environment(value= EnvType.CLIENT)
public class PlanterBoxRenderer implements BlockEntityRenderer<PlanterBoxBlockEntity> {
    private BlockRenderDispatcher blockRenderer;

    public PlanterBoxRenderer(BlockEntityRendererProvider.Context pContext) {
        this.blockRenderer = pContext.getBlockRenderDispatcher();
    }

    @Override
    public void render(PlanterBoxBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        Direction facing = pBlockEntity.getBlockState().getValue(PlanterBoxBlock.FACING).getOpposite();
        float rotation = -facing.toYRot();
        BlockState lower;
        BlockState upper = null;

        pPoseStack.pushPose();

        pPoseStack.scale(0.7f, 0.7f, 0.7f);
        pPoseStack.translate(0f, 0.4f, 0f);

        for (int i = 0; i < 2; i++) {
            Item item = pBlockEntity.getItemFromSlot(i);

            if (item != Items.AIR) {
                Block block = ((BlockItem)item).getBlock();
                if (block instanceof DoublePlantBlock) {
                    lower = block.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);
                    upper = block.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER);
                }
                else {
                    lower = block.defaultBlockState();
                }

                pPoseStack.pushPose();
                pPoseStack.mulPose(Vector3f.YP.rotationDegrees(rotation)); // rotate based on direction
                pPoseStack.translate( 0.6f - 0.8 * i, 0.001f * i, 0.2f); // position each flower at left and right
                pPoseStack.translate( 0f, 0.001f * i, 0.001f * i); // prevent z-clipping

                switch (pBlockEntity.getBlockState().getValue(PlanterBoxBlock.FACING)) { // correct position based on direction
                    case EAST -> pPoseStack.translate(0f, 0f, -1.4f);
                    case WEST -> pPoseStack.translate(-1.4f, 0f, 0);
                    case SOUTH -> pPoseStack.translate(-1.4f, 0f, -1.4f);
                }
                if (pBlockEntity.getBlockState().getValue(PlanterBoxBlock.ATTACHED)) { // correct position when attached
                    pPoseStack.translate(0f, 0.9f, 0.36f);
                }

                blockRenderer.renderSingleBlock(lower, pPoseStack, pBufferSource, pPackedLight, OverlayTexture.NO_OVERLAY);
                if (upper != null) {
                    pPoseStack.translate(0f, 1.0f, 0f);
                    blockRenderer.renderSingleBlock(upper, pPoseStack, pBufferSource, pPackedLight, OverlayTexture.NO_OVERLAY);
                }
                pPoseStack.popPose();
            }
        }

        pPoseStack.popPose();
    }
}