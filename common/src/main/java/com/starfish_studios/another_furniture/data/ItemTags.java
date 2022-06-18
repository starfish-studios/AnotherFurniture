package com.starfish_studios.another_furniture.data;

import com.starfish_studios.another_furniture.registry.AFBlockTags;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import com.starfish_studios.another_furniture.registry.AFItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;

import javax.annotation.Nullable;

public class ItemTags extends ItemTagsProvider {
    public ItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider) {
        super(dataGenerator, blockTagsProvider);
    }

//    public ItemTags(DataGenerator generatorIn, BlockTagsProvider blockTagProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
//        super(generatorIn, blockTagProvider, modId, existingFileHelper);
//    }

    @Override
    protected void addTags() {
        this.registerModTags();
    }


    private void registerModTags() {
        tag(AFItemTags.CHAIRS).add(
                AFBlocks.OAK_CHAIR.get().asItem(),
                AFBlocks.SPRUCE_CHAIR.get().asItem(),
                AFBlocks.BIRCH_CHAIR.get().asItem(),
                AFBlocks.JUNGLE_CHAIR.get().asItem(),
                AFBlocks.ACACIA_CHAIR.get().asItem(),
                AFBlocks.DARK_OAK_CHAIR.get().asItem(),
                AFBlocks.MANGROVE_CHAIR.get().asItem(),
                AFBlocks.CRIMSON_CHAIR.get().asItem(),
                AFBlocks.WARPED_CHAIR.get().asItem()
        );
        tag(AFItemTags.PLANTER_BOXES).add(
                AFBlocks.OAK_PLANTER_BOX.get().asItem(),
                AFBlocks.SPRUCE_PLANTER_BOX.get().asItem(),
                AFBlocks.BIRCH_PLANTER_BOX.get().asItem(),
                AFBlocks.JUNGLE_PLANTER_BOX.get().asItem(),
                AFBlocks.ACACIA_PLANTER_BOX.get().asItem(),
                AFBlocks.DARK_OAK_PLANTER_BOX.get().asItem(),
                AFBlocks.MANGROVE_PLANTER_BOX.get().asItem(),
                AFBlocks.CRIMSON_PLANTER_BOX.get().asItem(),
                AFBlocks.WARPED_PLANTER_BOX.get().asItem()
        );
        tag(AFItemTags.SHELVES).add(
                AFBlocks.OAK_SHELF.get().asItem(),
                AFBlocks.SPRUCE_SHELF.get().asItem(),
                AFBlocks.BIRCH_SHELF.get().asItem(),
                AFBlocks.JUNGLE_SHELF.get().asItem(),
                AFBlocks.ACACIA_SHELF.get().asItem(),
                AFBlocks.DARK_OAK_SHELF.get().asItem(),
                AFBlocks.MANGROVE_SHELF.get().asItem(),
                AFBlocks.CRIMSON_SHELF.get().asItem(),
                AFBlocks.WARPED_SHELF.get().asItem()
        );
        tag(AFItemTags.SHUTTERS).add(
                AFBlocks.OAK_SHUTTER.get().asItem(),
                AFBlocks.SPRUCE_SHUTTER.get().asItem(),
                AFBlocks.BIRCH_SHUTTER.get().asItem(),
                AFBlocks.JUNGLE_SHUTTER.get().asItem(),
                AFBlocks.ACACIA_SHUTTER.get().asItem(),
                AFBlocks.DARK_OAK_SHUTTER.get().asItem(),
                AFBlocks.MANGROVE_SHUTTER.get().asItem(),
                AFBlocks.CRIMSON_SHUTTER.get().asItem(),
                AFBlocks.WARPED_SHUTTER.get().asItem()
        );
        tag(AFItemTags.TABLES).add(
                AFBlocks.OAK_TABLE.get().asItem(),
                AFBlocks.SPRUCE_TABLE.get().asItem(),
                AFBlocks.BIRCH_TABLE.get().asItem(),
                AFBlocks.JUNGLE_TABLE.get().asItem(),
                AFBlocks.ACACIA_TABLE.get().asItem(),
                AFBlocks.DARK_OAK_TABLE.get().asItem(),
                AFBlocks.MANGROVE_TABLE.get().asItem(),
                AFBlocks.CRIMSON_TABLE.get().asItem(),
                AFBlocks.WARPED_TABLE.get().asItem()
        );
        tag(AFItemTags.CURTAINS).add(
                AFBlocks.WHITE_CURTAIN.get().asItem(),
                AFBlocks.ORANGE_CURTAIN.get().asItem(),
                AFBlocks.MAGENTA_CURTAIN.get().asItem(),
                AFBlocks.LIGHT_BLUE_CURTAIN.get().asItem(),
                AFBlocks.YELLOW_CURTAIN.get().asItem(),
                AFBlocks.LIME_CURTAIN.get().asItem(),
                AFBlocks.PINK_CURTAIN.get().asItem(),
                AFBlocks.GRAY_CURTAIN.get().asItem(),
                AFBlocks.LIGHT_GRAY_CURTAIN.get().asItem(),
                AFBlocks.CYAN_CURTAIN.get().asItem(),
                AFBlocks.PURPLE_CURTAIN.get().asItem(),
                AFBlocks.BLUE_CURTAIN.get().asItem(),
                AFBlocks.BROWN_CURTAIN.get().asItem(),
                AFBlocks.GREEN_CURTAIN.get().asItem(),
                AFBlocks.RED_CURTAIN.get().asItem(),
                AFBlocks.BLACK_CURTAIN.get().asItem()
        );
        tag(AFItemTags.STOOLS).add(
                AFBlocks.WHITE_STOOL.get().asItem(),
                AFBlocks.ORANGE_STOOL.get().asItem(),
                AFBlocks.MAGENTA_STOOL.get().asItem(),
                AFBlocks.LIGHT_BLUE_STOOL.get().asItem(),
                AFBlocks.YELLOW_STOOL.get().asItem(),
                AFBlocks.LIME_STOOL.get().asItem(),
                AFBlocks.PINK_STOOL.get().asItem(),
                AFBlocks.GRAY_STOOL.get().asItem(),
                AFBlocks.LIGHT_GRAY_STOOL.get().asItem(),
                AFBlocks.CYAN_STOOL.get().asItem(),
                AFBlocks.PURPLE_STOOL.get().asItem(),
                AFBlocks.BLUE_STOOL.get().asItem(),
                AFBlocks.BROWN_STOOL.get().asItem(),
                AFBlocks.GREEN_STOOL.get().asItem(),
                AFBlocks.RED_STOOL.get().asItem(),
                AFBlocks.BLACK_STOOL.get().asItem()
        );
    }
}