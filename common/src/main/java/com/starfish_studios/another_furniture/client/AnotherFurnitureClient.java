package com.starfish_studios.another_furniture.client;

import com.starfish_studios.another_furniture.client.renderer.blockentity.CurtainRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.PlanterBoxRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.ServiceBellButtonRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.ShelfRenderer;
import com.starfish_studios.another_furniture.client.renderer.entity.SeatRenderer;
import com.starfish_studios.another_furniture.registry.AFBlockEntityTypes;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import com.starfish_studios.another_furniture.registry.AFEntityTypes;
import com.starfish_studios.another_furniture.registry.AFRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

public class AnotherFurnitureClient {

    public static void init() {
        AFRegistry.setRenderLayer(AFBlocks.OAK_CHAIR, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.SPRUCE_CHAIR, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.BIRCH_CHAIR, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.JUNGLE_CHAIR, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.ACACIA_CHAIR, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.DARK_OAK_CHAIR, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.CRIMSON_CHAIR, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.WARPED_CHAIR, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.OAK_SHUTTER, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.SPRUCE_SHUTTER, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.BIRCH_SHUTTER, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.JUNGLE_SHUTTER, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.ACACIA_SHUTTER, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.DARK_OAK_SHUTTER, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.CRIMSON_SHUTTER, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.WARPED_SHUTTER, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.OAK_TABLE, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.SPRUCE_TABLE, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.BIRCH_TABLE, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.JUNGLE_TABLE, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.ACACIA_TABLE, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.DARK_OAK_TABLE, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.CRIMSON_TABLE, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.WARPED_TABLE, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.OAK_BENCH, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.SPRUCE_BENCH, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.BIRCH_BENCH, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.JUNGLE_BENCH, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.ACACIA_BENCH, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.DARK_OAK_BENCH, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.CRIMSON_BENCH, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.WARPED_BENCH, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.WHITE_SOFA, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.ORANGE_SOFA, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.MAGENTA_SOFA, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.LIGHT_BLUE_SOFA, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.YELLOW_SOFA, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.LIME_SOFA, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.PINK_SOFA, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.GRAY_SOFA, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.LIGHT_GRAY_SOFA, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.CYAN_SOFA, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.PURPLE_SOFA, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.BLUE_SOFA, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.BROWN_SOFA, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.GREEN_SOFA, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.RED_SOFA, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.BLACK_SOFA, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.WHITE_LAMP, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.ORANGE_LAMP, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.MAGENTA_LAMP, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.LIGHT_BLUE_LAMP, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.YELLOW_LAMP, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.LIME_LAMP, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.PINK_LAMP, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.GRAY_LAMP, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.LIGHT_GRAY_LAMP, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.CYAN_LAMP, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.PURPLE_LAMP, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.BLUE_LAMP, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.BROWN_LAMP, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.GREEN_LAMP, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.RED_LAMP, RenderType.cutout());
        AFRegistry.setRenderLayer(AFBlocks.BLACK_LAMP, RenderType.cutout());

        AFRegistry.registerEntityRenderers(AFEntityTypes.SEAT, SeatRenderer::new);

        AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.SHELF, ShelfRenderer::new);
        AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.CURTAIN, CurtainRenderer::new);
        AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.SERVICE_BELL, ServiceBellButtonRenderer::new);
        AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.PLANTER_BOX, PlanterBoxRenderer::new);
    }

}
