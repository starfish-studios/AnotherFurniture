package com.starfish_studios.another_furniture.client;

import com.starfish_studios.another_furniture.client.renderer.blockentity.CurtainRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.PlanterBoxRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.ServiceBellButtonRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.ShelfRenderer;
import com.starfish_studios.another_furniture.client.renderer.entity.SeatRenderer;
import com.starfish_studios.another_furniture.registry.AFBlockEntityTypes;
import com.starfish_studios.another_furniture.registry.AFEntityTypes;
import com.starfish_studios.another_furniture.registry.AFRegistry;

public class AnotherFurnitureClient {

    public static void init() {
<<<<<<< HEAD

=======
>>>>>>> features/2.1.0
        AFRegistry.registerEntityRenderers(AFEntityTypes.SEAT, SeatRenderer::new);

        AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.SHELF, ShelfRenderer::new);
        AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.CURTAIN, CurtainRenderer::new);
        AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.SERVICE_BELL, ServiceBellButtonRenderer::new);
        AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.PLANTER_BOX, PlanterBoxRenderer::new);
    }
}
