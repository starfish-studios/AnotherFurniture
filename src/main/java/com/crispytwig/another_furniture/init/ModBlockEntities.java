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
                    ModBlocks.BIOMESOPLENTY_CHERRY_SHELF.get(),
                    ModBlocks.BIOMESOPLENTY_DEAD_SHELF.get(),
                    ModBlocks.BIOMESOPLENTY_FIR_SHELF.get(),
                    ModBlocks.BIOMESOPLENTY_HELLBARK_SHELF.get(),
                    ModBlocks.BIOMESOPLENTY_JACARANDA_SHELF.get(),
                    ModBlocks.BIOMESOPLENTY_MAGIC_SHELF.get(),
                    ModBlocks.BIOMESOPLENTY_MAHOGANY_SHELF.get(),
                    ModBlocks.BIOMESOPLENTY_PALM_SHELF.get(),
                    ModBlocks.BIOMESOPLENTY_REDWOOD_SHELF.get(),
                    ModBlocks.BIOMESOPLENTY_UMBRAN_SHELF.get(),
                    ModBlocks.BIOMESOPLENTY_WILLOW_SHELF.get(),
                    ModBlocks.ECOLOGICS_AZALEA_SHELF.get(),
                    ModBlocks.ECOLOGICS_COCONUT_SHELF.get(),
                    ModBlocks.ECOLOGICS_WALNUT_SHELF.get(),
                    ModBlocks.BIOMEMAKEOVER_WILLOW_SHELF.get(),
                    ModBlocks.BIOMEMAKEOVER_ANCIENT_OAK_SHELF.get(),
                    ModBlocks.BIOMEMAKEOVER_BLIGHTED_BALSA_SHELF.get(),
                    ModBlocks.BIOMEMAKEOVER_SWAMP_CYPRESS_SHELF.get(),
                    ModBlocks.ENHANCED_MUSHROOMS_BROWN_MUSHROOM_SHELF.get(),
                    ModBlocks.ENHANCED_MUSHROOMS_RED_MUSHROOM_SHELF.get(),
                    ModBlocks.OAK_SHELF.get(),
                    ModBlocks.SPRUCE_SHELF.get(),
                    ModBlocks.BIRCH_SHELF.get(),
                    ModBlocks.JUNGLE_SHELF.get(),
                    ModBlocks.ACACIA_SHELF.get(),
                    ModBlocks.DARK_OAK_SHELF.get(),
                    ModBlocks.CRIMSON_SHELF.get(),
                    ModBlocks.WARPED_SHELF.get(),
                    ModBlocks.QUARK_AZALEA_SHELF.get(),
                    ModBlocks.QUARK_BLOSSOM_SHELF.get()
            ).build(null));

}
