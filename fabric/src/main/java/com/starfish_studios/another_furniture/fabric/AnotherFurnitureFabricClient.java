package com.starfish_studios.another_furniture.fabric;

import com.starfish_studios.another_furniture.client.AnotherFurnitureClient;
import com.starfish_studios.another_furniture.client.renderer.blockentity.CurtainRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.ServiceBellButtonRenderer;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.renderer.RenderType;

public class AnotherFurnitureFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AnotherFurnitureClient.init();
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                AFBlocks.OAK_CHAIR.get(), AFBlocks.SPRUCE_CHAIR.get(), AFBlocks.BIRCH_CHAIR.get(), AFBlocks.JUNGLE_CHAIR.get(), AFBlocks.ACACIA_CHAIR.get(), AFBlocks.DARK_OAK_CHAIR.get(), AFBlocks.MANGROVE_CHAIR.get(), AFBlocks.CRIMSON_CHAIR.get(), AFBlocks.WARPED_CHAIR.get(),
                //AFBlocks.OAK_BENCH.get(), AFBlocks.SPRUCE_BENCH.get(), AFBlocks.BIRCH_BENCH.get(), AFBlocks.JUNGLE_BENCH.get(), AFBlocks.ACACIA_BENCH.get(), AFBlocks.DARK_OAK_BENCH.get(), AFBlocks.MANGROVE_BENCH.get(), AFBlocks.CRIMSON_BENCH.get(), AFBlocks.WARPED_BENCH.get(),
                AFBlocks.OAK_SHUTTER.get(), AFBlocks.SPRUCE_SHUTTER.get(), AFBlocks.BIRCH_SHUTTER.get(), AFBlocks.JUNGLE_SHUTTER.get(), AFBlocks.ACACIA_SHUTTER.get(), AFBlocks.DARK_OAK_SHUTTER.get(), AFBlocks.MANGROVE_SHUTTER.get(), AFBlocks.CRIMSON_SHUTTER.get(), AFBlocks.WARPED_SHUTTER.get(), AFBlocks.OAK_PLANTER_BOX.get()//,
                //AFBlocks.WHITE_LAMP.get(), AFBlocks.ORANGE_LAMP.get(), AFBlocks.MAGENTA_LAMP.get(), AFBlocks.LIGHT_BLUE_LAMP.get(), AFBlocks.YELLOW_LAMP.get(), AFBlocks.LIME_LAMP.get(), AFBlocks.PINK_LAMP.get(), AFBlocks.GRAY_LAMP.get(), AFBlocks.LIGHT_GRAY_LAMP.get(), AFBlocks.CYAN_LAMP.get(), AFBlocks.PURPLE_LAMP.get(), AFBlocks.BLUE_LAMP.get(), AFBlocks.BROWN_LAMP.get(), AFBlocks.GREEN_LAMP.get(), AFBlocks.RED_LAMP.get(), AFBlocks.BLACK_LAMP.get()
                );
        EntityModelLayerRegistry.registerModelLayer(CurtainRenderer.CURTAIN_MODEL, CurtainRenderer::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ServiceBellButtonRenderer.SERVICE_BELL_MODEL, ServiceBellButtonRenderer::createBodyLayer);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                AFBlocks.OAK_CHAIR.get(), AFBlocks.SPRUCE_CHAIR.get(), AFBlocks.BIRCH_CHAIR.get(), AFBlocks.JUNGLE_CHAIR.get(), AFBlocks.ACACIA_CHAIR.get(), AFBlocks.DARK_OAK_CHAIR.get(), AFBlocks.MANGROVE_CHAIR.get(), AFBlocks.CRIMSON_CHAIR.get(), AFBlocks.WARPED_CHAIR.get(),
                AFBlocks.OAK_SHUTTER.get(), AFBlocks.SPRUCE_SHUTTER.get(), AFBlocks.BIRCH_SHUTTER.get(), AFBlocks.JUNGLE_SHUTTER.get(), AFBlocks.ACACIA_SHUTTER.get(), AFBlocks.DARK_OAK_SHUTTER.get(), AFBlocks.MANGROVE_SHUTTER.get(), AFBlocks.CRIMSON_SHUTTER.get(), AFBlocks.WARPED_SHUTTER.get()
        );
    }
}
