package com.starfish_studios.another_furniture.fabric;

import com.starfish_studios.another_furniture.client.AnotherFurnitureClient;
import com.starfish_studios.another_furniture.client.renderer.blockentity.CurtainRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.ServiceBellButtonRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;

public class AnotherFurnitureFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AnotherFurnitureClient.init();
        EntityModelLayerRegistry.registerModelLayer(CurtainRenderer.CURTAIN_MODEL, CurtainRenderer::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ServiceBellButtonRenderer.SERVICE_BELL_MODEL, ServiceBellButtonRenderer::createBodyLayer);
    }
}
