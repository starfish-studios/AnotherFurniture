package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.block.entity.CurtainBlockEntity;
import com.starfish_studios.another_furniture.block.entity.PlanterBoxBlockEntity;
import com.starfish_studios.another_furniture.block.entity.ServiceBellBlockEntity;
import com.starfish_studios.another_furniture.block.entity.ShelfBlockEntity;
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
                    AFBlocks.CRIMSON_SHELF.get(),
                    AFBlocks.WARPED_SHELF.get()
            ));
    public static final Supplier<BlockEntityType<CurtainBlockEntity>> CURTAIN = AFRegistry.registerBlockEntityType("curtain",
            () -> AFRegistry.createBlockEntityType(CurtainBlockEntity::new,
                    AFBlocks.WHITE_CURTAIN.get(),
                    AFBlocks.ORANGE_CURTAIN.get(),
                    AFBlocks.MAGENTA_CURTAIN.get(),
                    AFBlocks.LIGHT_BLUE_CURTAIN.get(),
                    AFBlocks.YELLOW_CURTAIN.get(),
                    AFBlocks.LIME_CURTAIN.get(),
                    AFBlocks.PINK_CURTAIN.get(),
                    AFBlocks.GRAY_CURTAIN.get(),
                    AFBlocks.LIGHT_GRAY_CURTAIN.get(),
                    AFBlocks.CYAN_CURTAIN.get(),
                    AFBlocks.PURPLE_CURTAIN.get(),
                    AFBlocks.BLUE_CURTAIN.get(),
                    AFBlocks.BROWN_CURTAIN.get(),
                    AFBlocks.GREEN_CURTAIN.get(),
                    AFBlocks.RED_CURTAIN.get(),
                    AFBlocks.BLACK_CURTAIN.get()
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
                    AFBlocks.CRIMSON_PLANTER_BOX.get(),
                    AFBlocks.WARPED_PLANTER_BOX.get()
            ));

    public static void init() {}
}
