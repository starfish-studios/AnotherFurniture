package com.crispytwig.another_furniture.init;

import com.crispytwig.another_furniture.AnotherFurnitureMod;
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
                    ModBlocks.WARPED_SHELF.get(),
                    ModBlocks.AZALEA_SHELF.get(),
                    ModBlocks.BLOSSOM_SHELF.get()
            ).build(null));

}
