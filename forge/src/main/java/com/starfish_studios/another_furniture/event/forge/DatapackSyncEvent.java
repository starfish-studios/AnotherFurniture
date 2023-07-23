package com.starfish_studios.another_furniture.event.forge;

import com.starfish_studios.another_furniture.registry.AFFurnaceFuelRegistry;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DatapackSyncEvent {

    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        AFFurnaceFuelRegistry.init();
    }
}
