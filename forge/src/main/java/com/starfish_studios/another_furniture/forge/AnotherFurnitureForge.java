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

}