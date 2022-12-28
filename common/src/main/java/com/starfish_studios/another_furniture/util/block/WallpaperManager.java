package com.starfish_studios.another_furniture.util.block;

//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import com.mojang.math.Vector3f;
//import com.starfish_studios.another_furniture.client.AnotherFurnitureClient;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.LevelRenderer;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.culling.Frustum;
//import net.minecraft.client.renderer.texture.OverlayTexture;
//import net.minecraft.client.resources.model.ModelResourceLocation;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.nbt.IntTag;
//import net.minecraft.nbt.ListTag;
//import net.minecraft.nbt.Tag;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.LevelAccessor;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.chunk.ChunkAccess;
//import net.minecraft.world.phys.AABB;
//import net.minecraft.world.phys.Vec3;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.WeakHashMap;
//
//public class WallpaperManager {
//    private final Map<ChunkAccess, Map<BlockPos, Direction>> customData = new WeakHashMap<>();
//
//    public void addWallpaper(Level level, BlockPos pos, Direction face) {
//        if (level == null || pos == null || face == null || !level.getBlockState(pos).isFaceSturdy(level, pos, face)) {
//            return;
//        }
//        ChunkAccess chunk = level.getChunkAt(pos);
//        if (chunk == null) {
//            return;
//        }
//        Map<BlockPos, Direction> wallpapers;
//        if (customData.containsKey(chunk)) {
//            wallpapers = customData.get(chunk);
//        } else {
//            wallpapers = new HashMap<>();
//            customData.put(chunk, wallpapers);
//        }
//        Direction wallpaperBlockData;
//        wallpaperBlockData = wallpapers.getOrDefault(pos, face);
//        wallpapers.put(pos, wallpaperBlockData);
//        chunk.setUnsaved(true);
//    }
//
//    public ListTag saveWallpaperChunk(ChunkAccess chunk) {
//
//        ListTag list = new ListTag();
//        if (customData.containsKey(chunk)) {
//            for (var entry : customData.get(chunk).entrySet()) {
//
//                BlockPos pos = entry.getKey();
//                int x = pos.getX();
//                int y = pos.getY();
//                int z = pos.getZ();
//                CompoundTag posTag = new CompoundTag();
//                posTag.put("x", IntTag.valueOf(x));
//                posTag.put("y", IntTag.valueOf(y));
//                posTag.put("z", IntTag.valueOf(z));
//
//                CompoundTag wallpaperBlockData = new CompoundTag();
//                wallpaperBlockData.put("pos", posTag);
//                list.add(wallpaperBlockData);
//                System.out.println("saved");
//            }
//            return list;
//        }
//        return new ListTag();
//    }
//
//    public void loadWallpaperChunk(LevelAccessor level, ChunkAccess chunk, ListTag wallpaperData) {
//        Map<BlockPos, Direction> a = new HashMap<>();
//        for (Tag tag : wallpaperData) {
//            System.out.println(tag);
//            CompoundTag posData = ((CompoundTag) tag).getCompound("pos");
//            System.out.println(posData);
//            BlockPos pos = new BlockPos(posData.getInt("x"), posData.getInt("y"), posData.getInt("z"));
//            System.out.println(pos);
//            Direction dir = Direction.NORTH;
//            if (chunk != null) {
//                System.out.println("chunk != null");
//
//                a.put(pos, dir);
//
//            }
//        }
//        customData.put(chunk, a);
//
//    }
//
//    public void unloadWallpaperChunk(ChunkAccess chunk) {
//        //
//    }
//
//    public final void renderWallpapers(PoseStack pose, Frustum frustum) {
//        final Minecraft mc = Minecraft.getInstance();
//        Vec3 camera = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
//        int cameraChunkX = (int)(camera.x) >> 4;
//        int cameraChunkZ = (int)(camera.z) >> 4;
//
//        for (var data : customData.entrySet()) {
//            ChunkAccess chunk = data.getKey();
//
//
//            int chunkX = chunk.getPos().x;
//            int chunkZ = chunk.getPos().z;
//
//            if (Math.sqrt((chunkX - cameraChunkX) * (chunkX - cameraChunkX) + (chunkZ - cameraChunkZ) * (chunkZ - cameraChunkZ)) >= 8) {
//                continue;
//            }
//
//            Map<BlockPos, Direction> posToDirs = data.getValue();
//            if (posToDirs == null) {
//                continue;
//            }
//            for (var entry : posToDirs.entrySet()) {
//                BlockPos pos = entry.getKey();
//                if (!frustum.isVisible(new AABB(pos))) {
//                    continue;
//                }
//                Direction dir = entry.getValue();
//
//                BlockPos pos1 = pos.relative(dir);
//                if (mc.level.getBlockState(pos).isFaceSturdy(mc.level, pos, dir) && !mc.level.getBlockState(pos1).isFaceSturdy(mc.level, pos1, dir.getOpposite())) {
//
//                    Vec3 camera1 = camera.relative(dir, -0.004);
//
//                    final MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
//                    final VertexConsumer builder = buffer.getBuffer(RenderType.solid());
//
//                    pose.pushPose();
//
//                    double model_x = pos1.getX() - camera1.x;
//                    double model_y = pos1.getY() - camera1.y;
//                    double model_z = pos1.getZ() - camera1.z;
//                    pose.translate(model_x, model_y, model_z);
//                    pose.translate(0.5, 0.0, 0.5);
//                    pose.mulPose(Vector3f.YP.rotationDegrees(-dir.get2DDataValue() * 90));
//                    pose.translate(0.5, 0.0, 0.5);
//                    pose.scale(-1.0F, 1.0F, -1.0F);
//
//                    int light = LevelRenderer.getLightColor(mc.level, Blocks.AIR.defaultBlockState(), pos1);
//                    ModelResourceLocation model = dir == Direction.NORTH ? AnotherFurnitureClient.GREEN_WALLPAPER : AnotherFurnitureClient.RED_STRIPES_WALLPAPER;
//                    mc.getBlockRenderer().getModelRenderer().renderModel(pose.last(), builder, (BlockState) null,
//                            mc.getModelManager().getModel(model), 1.0F, 1.0F, 1.0F, light, OverlayTexture.NO_OVERLAY);
//
//                    pose.popPose();
//                }
//            }
//        }
//    }
//}
