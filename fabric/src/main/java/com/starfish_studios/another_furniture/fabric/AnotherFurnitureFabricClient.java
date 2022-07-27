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
        EntityModelLayerRegistry.registerModelLayer(CurtainRenderer.CURTAIN_MODEL, CurtainRenderer::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ServiceBellButtonRenderer.SERVICE_BELL_MODEL, ServiceBellButtonRenderer::createBodyLayer);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                AFBlocks.OAK_CHAIR.get(), AFBlocks.SPRUCE_CHAIR.get(), AFBlocks.BIRCH_CHAIR.get(), AFBlocks.JUNGLE_CHAIR.get(), AFBlocks.ACACIA_CHAIR.get(), AFBlocks.DARK_OAK_CHAIR.get(), AFBlocks.MANGROVE_CHAIR.get(), AFBlocks.CRIMSON_CHAIR.get(), AFBlocks.WARPED_CHAIR.get(),
                AFBlocks.OAK_SHUTTER.get(), AFBlocks.SPRUCE_SHUTTER.get(), AFBlocks.BIRCH_SHUTTER.get(), AFBlocks.JUNGLE_SHUTTER.get(), AFBlocks.ACACIA_SHUTTER.get(), AFBlocks.DARK_OAK_SHUTTER.get(), AFBlocks.MANGROVE_SHUTTER.get(), AFBlocks.CRIMSON_SHUTTER.get(), AFBlocks.WARPED_SHUTTER.get()
        );
    }
}
