package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.block.entity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class AFBlockEntityTypes {
    public static final Supplier<BlockEntityType<ShelfBlockEntity>> SHELF = AFRegistry.registerBlockEntityType("shelf",
            () -> AFRegistry.createBlockEntityType(ShelfBlockEntity::new,
                    AFBlocks.OAK_SHELF.get(),
                    AFBlocks.SPRUCE_SHELF.get(),
                    AFBlocks.BIRCH_SHELF.get(),
                    AFBlocks.JUNGLE_SHELF.get(),
                    AFBlocks.ACACIA_SHELF.get(),
                    AFBlocks.DARK_OAK_SHELF.get(),
                    AFBlocks.MANGROVE_SHELF.get(),
                    AFBlocks.CRIMSON_SHELF.get(),
                    AFBlocks.WARPED_SHELF.get()
            ));

    public static final Supplier<BlockEntityType<ServiceBellBlockEntity>> SERVICE_BELL = AFRegistry.registerBlockEntityType("service_bell",
            () -> AFRegistry.createBlockEntityType(ServiceBellBlockEntity::new, AFBlocks.SERVICE_BELL.get()));

    public static final Supplier<BlockEntityType<PlanterBoxBlockEntity>> PLANTER_BOX = AFRegistry.registerBlockEntityType("planter_box",
            () -> AFRegistry.createBlockEntityType(PlanterBoxBlockEntity::new,
                    AFBlocks.OAK_PLANTER_BOX.get(),
                    AFBlocks.SPRUCE_PLANTER_BOX.get(),
                    AFBlocks.BIRCH_PLANTER_BOX.get(),
                    AFBlocks.JUNGLE_PLANTER_BOX.get(),
                    AFBlocks.ACACIA_PLANTER_BOX.get(),
                    AFBlocks.DARK_OAK_PLANTER_BOX.get(),
                    AFBlocks.MANGROVE_PLANTER_BOX.get(),
                    AFBlocks.CRIMSON_PLANTER_BOX.get(),
                    AFBlocks.WARPED_PLANTER_BOX.get()
            ));

    public static final Supplier<BlockEntityType<DrawerBlockEntity>> DRAWER = AFRegistry.registerBlockEntityType("drawer",
            () -> AFRegistry.createBlockEntityType(DrawerBlockEntity::new,
                    AFBlocks.OAK_DRAWER.get(),
                    AFBlocks.SPRUCE_DRAWER.get(),
                    AFBlocks.BIRCH_DRAWER.get(),
                    AFBlocks.JUNGLE_DRAWER.get(),
                    AFBlocks.ACACIA_DRAWER.get(),
                    AFBlocks.DARK_OAK_DRAWER.get(),
                    AFBlocks.MANGROVE_DRAWER.get(),
                    AFBlocks.CRIMSON_DRAWER.get(),
                    AFBlocks.WARPED_DRAWER.get()
            ));

//    public static final Supplier<BlockEntityType<TombstoneBlockEntity>> TOMBSTONE = AFRegistry.registerBlockEntityType("tombstone",
//            () -> AFRegistry.createBlockEntityType(TombstoneBlockEntity::new,
//                    AFBlocks.STONE_TOMBSTONE.get()
//            ));

    public static void init() {}
}
