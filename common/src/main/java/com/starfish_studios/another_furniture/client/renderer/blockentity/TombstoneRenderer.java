package com.starfish_studios.another_furniture.client.renderer.blockentity;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.block.TombstoneBlock;
import com.starfish_studios.another_furniture.block.entity.TombstoneBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

import static com.starfish_studios.another_furniture.block.TombstoneBlock.FACING;

@Environment(value= EnvType.CLIENT)
public class TombstoneRenderer implements BlockEntityRenderer<TombstoneBlockEntity> {
    private BlockRenderDispatcher blockRenderer;

    public static final int MAX_LINE_WIDTH = 90;
    private static final int LINE_HEIGHT = 10;
    private static final String STICK = "stick";
    private static final int BLACK_TEXT_OUTLINE_COLOR = -988212;
    private static final int OUTLINE_RENDER_DISTANCE = Mth.square(16);
    private final Font font;

    public TombstoneRenderer(BlockEntityRendererProvider.Context context) {
        this.font = context.getFont();
    }

    public void render(TombstoneBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        BlockState blockState = blockEntity.getBlockState();
        poseStack.pushPose();
        poseStack.translate(0.5, 0.5, 0.5);
        float g = blockState.getValue(FACING).get2DDataValue() * -90;
        poseStack.mulPose(Vector3f.YP.rotationDegrees(g));

        poseStack.pushPose();
        poseStack.scale(0.6666667F, -0.6666667F, -0.6666667F);
        poseStack.popPose();
        poseStack.translate(0.0, 0.1333333432674408, 0.126666666865348816);
        poseStack.scale(0.010416667F, -0.010416667F, 0.010416667F);
        int i = getDarkColor(blockEntity);
        FormattedCharSequence[] formattedCharSequences = blockEntity.getRenderMessages(Minecraft.getInstance().isTextFilteringEnabled(), (component) -> {
            List<FormattedCharSequence> list = this.font.split(component, 80);
            return list.isEmpty() ? FormattedCharSequence.EMPTY : list.get(0);
        });
        int k;
        boolean bl;
        int l;
        if (blockEntity.hasGlowingText()) {
            k = blockEntity.getColor().getTextColor();
            bl = isOutlineVisible(blockEntity, k);
            l = 15728880;
        } else {
            k = i;
            bl = false;
            l = packedLight;
        }

        for(int m = 0; m < 4; ++m) {
            FormattedCharSequence formattedCharSequence = formattedCharSequences[m];
            float n = (float)(-this.font.width(formattedCharSequence) / 2);
            if (bl) {
                this.font.drawInBatch8xOutline(formattedCharSequence, n, (float)(m * 10 - 20), k, i, poseStack.last().pose(), bufferSource, l);
            } else {
                this.font.drawInBatch(formattedCharSequence, n, (float)(m * 10 - 20), k, false, poseStack.last().pose(), bufferSource, false, 0, l);
            }
        }

        poseStack.popPose();
    }

    private static boolean isOutlineVisible(TombstoneBlockEntity blockEntity, int textColor) {
        if (textColor == DyeColor.BLACK.getTextColor()) {
            return true;
        } else {
            Minecraft minecraft = Minecraft.getInstance();
            LocalPlayer localPlayer = minecraft.player;
            if (localPlayer != null && minecraft.options.getCameraType().isFirstPerson() && localPlayer.isScoping()) {
                return true;
            } else {
                Entity entity = minecraft.getCameraEntity();
                return entity != null && entity.distanceToSqr(Vec3.atCenterOf(blockEntity.getBlockPos())) < (double)OUTLINE_RENDER_DISTANCE;
            }
        }
    }

    private static int getDarkColor(TombstoneBlockEntity blockEntity) {
        int i = blockEntity.getColor().getTextColor();
        double d = 0.4;
        int j = (int)((double) NativeImage.getR(i) * 0.4);
        int k = (int)((double)NativeImage.getG(i) * 0.4);
        int l = (int)((double)NativeImage.getB(i) * 0.4);
        return i == DyeColor.BLACK.getTextColor() && blockEntity.hasGlowingText() ? -988212 : NativeImage.combine(0, l, k, j);
    }

    @Override
    public boolean shouldRenderOffScreen(TombstoneBlockEntity blockEntity) {
        return BlockEntityRenderer.super.shouldRenderOffScreen(blockEntity);
    }

    @Override
    public int getViewDistance() {
        return BlockEntityRenderer.super.getViewDistance();
    }


    @Override
    public boolean shouldRender(TombstoneBlockEntity blockEntity, Vec3 cameraPos) {
        return BlockEntityRenderer.super.shouldRender(blockEntity, cameraPos);
    }
}
