package com.starfish_studios.another_furniture.client;

import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.client.renderer.blockentity.CurtainRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.PlanterBoxRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.ServiceBellButtonRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.ShelfRenderer;
import com.starfish_studios.another_furniture.client.renderer.entity.SeatRenderer;
import com.starfish_studios.another_furniture.registry.AFBlockEntityTypes;
import com.starfish_studios.another_furniture.registry.AFEntityTypes;
import com.starfish_studios.another_furniture.registry.AFRegistry;
import com.starfish_studios.another_furniture.util.block.WallpaperManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

public class AnotherFurnitureClient {
    public static final ModelResourceLocation GREEN_WALLPAPER = new ModelResourceLocation(AnotherFurniture.MOD_ID + ":green_wallpaper");
    public static final ModelResourceLocation RED_STRIPES_WALLPAPER = new ModelResourceLocation(AnotherFurniture.MOD_ID + ":red_stripes_wallpaper");

    public static void init() {
        AFRegistry.registerEntityRenderers(AFEntityTypes.SEAT, SeatRenderer::new);

        AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.SHELF, ShelfRenderer::new);
        AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.CURTAIN, CurtainRenderer::new);
        AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.SERVICE_BELL, ServiceBellButtonRenderer::new);
        AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.PLANTER_BOX, PlanterBoxRenderer::new);
    }

}
