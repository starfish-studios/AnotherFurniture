package com.crispytwig.another_furniture.init;

import com.crispytwig.another_furniture.AnotherFurnitureMod;
import com.crispytwig.another_furniture.block.entity.CurtainBlockEntity;
import com.crispytwig.another_furniture.block.entity.PlanterBoxBlockEntity;
import com.crispytwig.another_furniture.block.entity.ServiceBellBlockEntity;
import com.crispytwig.another_furniture.block.entity.ShelfBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, AnotherFurnitureMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<ShelfBlockEntity>> SHELF = BLOCK_ENTITIES.register("shelf",
            () -> BlockEntityType.Builder.of(ShelfBlockEntity::new,
                    ModBlocks.OAK_SHELF.get(),
                    ModBlocks.SPRUCE_SHELF.get(),
                    ModBlocks.BIRCH_SHELF.get(),
                    ModBlocks.JUNGLE_SHELF.get(),
                    ModBlocks.ACACIA_SHELF.get(),
                    ModBlocks.DARK_OAK_SHELF.get(),
                    ModBlocks.CRIMSON_SHELF.get(),
                    ModBlocks.WARPED_SHELF.get()
            ).build(null));

    public static final RegistryObject<BlockEntityType<CurtainBlockEntity>> CURTAIN = BLOCK_ENTITIES.register("curtain",
            () -> BlockEntityType.Builder.of(CurtainBlockEntity::new, ModBlocks.CURTAIN.get()).build(null));

    public static final RegistryObject<BlockEntityType<ServiceBellBlockEntity>> SERVICE_BELL = BLOCK_ENTITIES.register("service_bell",
            () -> BlockEntityType.Builder.of(ServiceBellBlockEntity::new, ModBlocks.SERVICE_BELL.get()).build(null));

    public static final RegistryObject<BlockEntityType<PlanterBoxBlockEntity>> PLANTER_BOX = BLOCK_ENTITIES.register("planter_box",
            () -> BlockEntityType.Builder.of(PlanterBoxBlockEntity::new,
                    ModBlocks.OAK_PLANTER_BOX.get(),
                    ModBlocks.SPRUCE_PLANTER_BOX.get(),
                    ModBlocks.BIRCH_PLANTER_BOX.get(),
                    ModBlocks.JUNGLE_PLANTER_BOX.get(),
                    ModBlocks.ACACIA_PLANTER_BOX.get(),
                    ModBlocks.DARK_OAK_PLANTER_BOX.get(),
                    ModBlocks.CRIMSON_PLANTER_BOX.get(),
                    ModBlocks.WARPED_PLANTER_BOX.get()
            ).build(null));
}
