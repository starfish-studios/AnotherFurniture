package com.starfish_studios.another_furniture.forge;

import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.registry.forge.AFRegistryImpl;
import com.starfish_studios.another_furniture.util.block.WallpaperManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.ChunkDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
//        ListTag wallpaperData = nbt.getList("wallpaper_data", 9);
//        //ListTag wallpaperData = WallpaperManager.getWallpaperData((ServerLevel)e.getLevel(), e.getChunk());
//
//        nbt.put("wallpaper_data", wallpaperData);
//    }
//
//    @SubscribeEvent
//    public void onChunkSave(ChunkDataEvent.Save e){
//        CompoundTag nbt = e.getData();
//        ListTag wallpaperData = WallpaperManager.getLoadedWallpaperData((ServerLevel)e.getLevel(), e.getChunk());
//
//        nbt.put("wallpaper_data", wallpaperData);
//    }

}