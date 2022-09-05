package com.starfish_studios.another_furniture.fabric;

import com.starfish_studios.another_furniture.AnotherFurniture;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.minecraft.world.level.chunk.ChunkAccess;

public class AnotherFurnitureFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        AnotherFurniture.init();
        AnotherFurniture.registerFlammables();
    }
}