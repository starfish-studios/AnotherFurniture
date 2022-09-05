package com.starfish_studios.another_furniture.forge;

import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.client.AnotherFurnitureClient;
import com.starfish_studios.another_furniture.client.renderer.blockentity.CurtainRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.ServiceBellButtonRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AnotherFurniture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AnotherFurnitureForgeClient {

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        AnotherFurnitureClient.init();
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CurtainRenderer.CURTAIN_MODEL, CurtainRenderer::createBodyLayer);
        event.registerLayerDefinition(ServiceBellButtonRenderer.SERVICE_BELL_MODEL, ServiceBellButtonRenderer::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerSpecialModels(ModelEvent.RegisterAdditional event) {
        event.register(new ModelResourceLocation(AnotherFurniture.MOD_ID + ":green_wallpaper"));
    }
}
