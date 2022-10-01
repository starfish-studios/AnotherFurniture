package com.starfish_studios.another_furniture.mixin.fabric;

import net.minecraft.world.level.chunk.storage.ChunkSerializer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChunkSerializer.class)
public class ChunkSerializerMixin {

//    @Inject(method = "write", at = @At("RETURN"))
//    private static void onWrite(ServerLevel level, ChunkAccess chunk, CallbackInfoReturnable<CompoundTag> cir) {
//        ListTag wallpaperData = AnotherFurniture.wallpaperManager.saveWallpaperChunk(chunk);
//
//        cir.getReturnValue().put("wallpaper_data", wallpaperData);
//    }
}