package com.starfish_studios.another_furniture.client;

import com.starfish_studios.another_furniture.client.renderer.blockentity.CurtainRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.PlanterBoxRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.ServiceBellButtonRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.ShelfRenderer;
import com.starfish_studios.another_furniture.client.renderer.entity.SeatRenderer;
import com.starfish_studios.another_furniture.platform.ClientServices;
import com.starfish_studios.another_furniture.registry.AFBlockEntityTypes;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import com.starfish_studios.another_furniture.registry.AFEntityTypes;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

public class AnotherFurnitureClient {

    public static void init() {
        ClientServices.HELPER.setRenderLayer(AFBlocks.OAK_CHAIR, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.SPRUCE_CHAIR, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.BIRCH_CHAIR, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.JUNGLE_CHAIR, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.ACACIA_CHAIR, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.DARK_OAK_CHAIR, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.CRIMSON_CHAIR, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.WARPED_CHAIR, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.OAK_SHUTTER, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.SPRUCE_SHUTTER, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.BIRCH_SHUTTER, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.JUNGLE_SHUTTER, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.ACACIA_SHUTTER, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.DARK_OAK_SHUTTER, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.CRIMSON_SHUTTER, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.WARPED_SHUTTER, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.OAK_PLANTER_BOX, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.SPRUCE_PLANTER_BOX, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.BIRCH_PLANTER_BOX, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.JUNGLE_PLANTER_BOX, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.ACACIA_PLANTER_BOX, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.DARK_OAK_PLANTER_BOX, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.CRIMSON_PLANTER_BOX, RenderType.cutout());
        ClientServices.HELPER.setRenderLayer(AFBlocks.WARPED_PLANTER_BOX, RenderType.cutout());

        ClientServices.HELPER.registerEntityRenderers(AFEntityTypes.SEAT, SeatRenderer::new);

        ClientServices.HELPER.registerBlockEntityRenderer(AFBlockEntityTypes.SHELF, ShelfRenderer::new);
        ClientServices.HELPER.registerBlockEntityRenderer(AFBlockEntityTypes.CURTAIN, CurtainRenderer::new);
        ClientServices.HELPER.registerBlockEntityRenderer(AFBlockEntityTypes.SERVICE_BELL, ServiceBellButtonRenderer::new);
        ClientServices.HELPER.registerBlockEntityRenderer(AFBlockEntityTypes.PLANTER_BOX, PlanterBoxRenderer::new);
    }
}
