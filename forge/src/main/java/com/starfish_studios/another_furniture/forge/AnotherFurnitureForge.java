package com.starfish_studios.another_furniture.forge;

import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.forge.platform.ForgeCommonPlatformHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AnotherFurniture.MOD_ID)
public class AnotherFurnitureForge {
    public AnotherFurnitureForge() {
        AnotherFurniture.init();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ForgeCommonPlatformHelper.BLOCKS.register(bus);
        ForgeCommonPlatformHelper.ITEMS.register(bus);
        ForgeCommonPlatformHelper.SOUND_EVENTS.register(bus);
        ForgeCommonPlatformHelper.ENTITY_TYPES.register(bus);
        ForgeCommonPlatformHelper.BLOCK_ENTITY_TYPES.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}