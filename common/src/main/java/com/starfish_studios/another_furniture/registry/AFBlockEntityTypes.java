package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.block.entity.CurtainBlockEntity;
import com.starfish_studios.another_furniture.block.entity.PlanterBoxBlockEntity;
import com.starfish_studios.another_furniture.block.entity.ServiceBellBlockEntity;
import com.starfish_studios.another_furniture.block.entity.ShelfBlockEntity;
import com.starfish_studios.another_furniture.platform.CommonServices;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class AFBlockEntityTypes {
    public static final Supplier<BlockEntityType<ShelfBlockEntity>> SHELF = CommonServices.HELPER.registerBlockEntityType("shelf",
            () -> CommonServices.HELPER.createBlockEntityType(ShelfBlockEntity::new,
                    AFBlocks.OAK_SHELF.get(),
                    AFBlocks.SPRUCE_SHELF.get(),
                    AFBlocks.BIRCH_SHELF.get(),
                    AFBlocks.JUNGLE_SHELF.get(),
                    AFBlocks.ACACIA_SHELF.get(),
                    AFBlocks.DARK_OAK_SHELF.get(),
                    AFBlocks.CRIMSON_SHELF.get(),
                    AFBlocks.WARPED_SHELF.get()
            ));
    public static final Supplier<BlockEntityType<CurtainBlockEntity>> CURTAIN = CommonServices.HELPER.registerBlockEntityType("curtain",
            () -> CommonServices.HELPER.createBlockEntityType(CurtainBlockEntity::new, AFBlocks.CURTAIN.get()));
    public static final Supplier<BlockEntityType<ServiceBellBlockEntity>> SERVICE_BELL = CommonServices.HELPER.registerBlockEntityType("service_bell",
            () -> CommonServices.HELPER.createBlockEntityType(ServiceBellBlockEntity::new, AFBlocks.SERVICE_BELL.get()));
    public static final Supplier<BlockEntityType<PlanterBoxBlockEntity>> PLANTER_BOX = CommonServices.HELPER.registerBlockEntityType("planter_box",
            () -> CommonServices.HELPER.createBlockEntityType(PlanterBoxBlockEntity::new,
                    AFBlocks.OAK_PLANTER_BOX.get(),
                    AFBlocks.SPRUCE_PLANTER_BOX.get(),
                    AFBlocks.BIRCH_PLANTER_BOX.get(),
                    AFBlocks.JUNGLE_PLANTER_BOX.get(),
                    AFBlocks.ACACIA_PLANTER_BOX.get(),
                    AFBlocks.DARK_OAK_PLANTER_BOX.get(),
                    AFBlocks.CRIMSON_PLANTER_BOX.get(),
                    AFBlocks.WARPED_PLANTER_BOX.get()
            ));

    public static void init() {}
}
