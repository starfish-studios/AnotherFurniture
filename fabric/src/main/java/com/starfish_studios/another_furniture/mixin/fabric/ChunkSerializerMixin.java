package com.starfish_studios.another_furniture.mixin.fabric;

import com.starfish_studios.another_furniture.util.block.WallpaperManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.storage.ChunkSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkSerializer.class)
public class ChunkSerializerMixin {

//    @Inject(method = "write", at = @At("RETURN"))
//    private static void onWrite(ServerLevel level, ChunkAccess chunk, CallbackInfoReturnable<CompoundTag> cir) {
//        ListTag wallpaperData = WallpaperManager.getLoadedWallpaperData(level, chunk);
//
//        cir.getReturnValue().put("wallpaper_data", wallpaperData);
//    }
}