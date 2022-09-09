package com.starfish_studios.another_furniture.forge;

import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.registry.forge.AFRegistryImpl;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AnotherFurniture.MOD_ID)
public class AnotherFurnitureForge {
    public AnotherFurnitureForge() {
        AnotherFurniture.init();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        AFRegistryImpl.BLOCKS.register(bus);
        AFRegistryImpl.ITEMS.register(bus);
        AFRegistryImpl.SOUND_EVENTS.register(bus);
        AFRegistryImpl.ENTITY_TYPES.register(bus);
        AFRegistryImpl.BLOCK_ENTITY_TYPES.register(bus);

        bus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            AnotherFurniture.registerFlammables();
        });
    }

//    @SubscribeEvent
//    public void onChunkLoad(ChunkDataEvent.Load e){
//        CompoundTag nbt = e.getData();
//        ListTag wallpaperData = nbt.getList("wallpaper_data", 10);
//        AnotherFurniture.wallpaperManager.loadWallpaperChunk(e.getLevel(), e.getChunk(), wallpaperData);
//    }
//
//    @SubscribeEvent
//    public void onChunkUnload(ChunkDataEvent.Unload e){
//        ChunkAccess chunk = e.getChunk();
//        AnotherFurniture.wallpaperManager.unloadWallpaperChunk(chunk);
//    }
//
//    @SubscribeEvent
//    public void onChunkSave(ChunkDataEvent.Save e){
//        ChunkAccess chunk = e.getChunk();
//        if (chunk.getStatus().isOrAfter(ChunkStatus.FULL)) {
//            ListTag wallpaper_data = AnotherFurniture.wallpaperManager.saveWallpaperChunk(chunk);
//            e.getData().put("wallpaper_data", wallpaper_data);
//        }
//    }
}