package com.starfish_studios.another_furniture.util.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.phys.Vec3;

import java.util.LinkedHashMap;

public class WallpaperManager {
    private final LinkedHashMap<BlockPos, Direction> data = new LinkedHashMap<>();

    public void addWallpaper(Level level, BlockPos pos, Direction face) {
        data.put(pos, face);
    }

    public void removeWallpaper(Level level, BlockPos pos) {
        data.remove(pos);
    }

    public final void renderWallpapers(PoseStack pose) {


        final Minecraft mc = Minecraft.getInstance();
        for (var entry : data.entrySet()) {
            BlockPos pos = entry.getKey();
            Direction dir = entry.getValue();

            BlockPos pos1 = pos.relative(dir);
            Vec3 camera = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
            Vec3 camera1 = camera.relative(dir, -0.01);

            int darkModified = Math.min(15, mc.level.getSkyDarken() * 2);
            int light = mc.level.getRawBrightness(pos1, darkModified);

            final MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
            final VertexConsumer builder = buffer.getBuffer(RenderType.solid());

            pose.pushPose();

            double model_x = pos1.getX() - camera1.x;
            double model_y = pos1.getY() - camera1.y;
            double model_z = pos1.getZ() - camera1.z;
            pose.translate(model_x, model_y, model_z);
            pose.translate(0.5, 0.0, 0.5);
            pose.mulPose(Vector3f.YP.rotationDegrees(-dir.get2DDataValue() * 90));
            pose.translate(0.5, 0.0, 0.5);
            pose.scale(-1.0F, 1.0F, -1.0F);

            mc.getBlockRenderer().getModelRenderer().renderModel(pose.last(), builder, (BlockState)null,
                    mc.getModelManager().getModel(new ModelResourceLocation("another_furniture:green_wallpaper")),
                    1.0F, 1.0F, 1.0F, light * 15, OverlayTexture.NO_OVERLAY);

            pose.popPose();
        }
    }

    public static ListTag getLoadedWallpaperData(ServerLevel level, ChunkAccess chunk) {
        ListTag wallpaperData = new ListTag();
        CompoundTag wallpaperInstance = new CompoundTag();

        int x = 2;
        int y = 2;
        int z = 2;
        CompoundTag pos = new CompoundTag();
        pos.putInt("x", x);
        pos.putInt("y", y);
        pos.putInt("z", z);

        wallpaperInstance.put("pos", pos);

        wallpaperData.add(wallpaperInstance);
        return wallpaperData;
    }
}
