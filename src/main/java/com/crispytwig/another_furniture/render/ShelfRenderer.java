package com.crispytwig.another_furniture.render;

import com.crispytwig.another_furniture.block.ShelfBlock;
import com.crispytwig.another_furniture.block.entity.ShelfBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class ShelfRenderer implements BlockEntityRenderer<ShelfBlockEntity> {

    public ShelfRenderer(BlockEntityRendererProvider.Context p_173602_) {
    }

    @Override
    public void render(ShelfBlockEntity tileEntity, float partialTicks, PoseStack poseStack, MultiBufferSource source, int light, int overlay)
    {
        Direction direction = tileEntity.getBlockState().getValue(ShelfBlock.FACING);
        NonNullList<ItemStack> items = tileEntity.getItems();
        for(int j = 0; j < items.size(); j++) {
            ItemStack stack = items.get(j);
            if (!stack.isEmpty()) {
                int renderCount = getAmount(stack.getCount());

                for (int i = 0; i < renderCount; ++i) {
                    float fx = (-0.12375f * (float)(i - 1) * 0.5f) % 0.09f;
                    float fy = (-0.08375f * (float)(i - 1) * 0.5f) % 0.09f;
                    float fz = (-0.09375f * (float)(i - 1) * 0.5f) % 0.09f;

                    poseStack.pushPose();

                    poseStack.translate(0.5, 1.18, 0.5);
                    poseStack.translate(-0.225 + 0.45 * (j % 2), 0.0, -0.225 + 0.45 * (j / 2));
                    poseStack.translate(-0.061875f, 0.0, -0.046875f);
                    poseStack.translate(fx, fy, fz);

                    poseStack.mulPose(Vector3f.YP.rotationDegrees(-90F * direction.get2DDataValue()));
                    poseStack.mulPose(Vector3f.YP.rotationDegrees(180F));

                    poseStack.scale(0.375F, 0.375F, 0.375F);

                    Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, light, overlay, poseStack, source, 0);
                    poseStack.popPose();
                }
            }
        }
    }

    public int getAmount(int count) {
        int renderCount = 1;
        if (count > 48) {
            renderCount = 5;
        } else if (count > 32) {
            renderCount = 4;
        } else if (count > 16) {
            renderCount = 3;
        } else if (count > 1) {
            renderCount = 2;
        }
        return renderCount;
    }
}