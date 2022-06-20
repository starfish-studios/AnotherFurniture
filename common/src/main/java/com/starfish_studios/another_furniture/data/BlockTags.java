package com.starfish_studios.another_furniture.data;

import com.starfish_studios.another_furniture.registry.AFBlockTags;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;

public class BlockTags extends BlockTagsProvider {
    public BlockTags(DataGenerator dataGenerator) {
        super(dataGenerator);
    }
    //public BlockTags(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
    //    super(generatorIn, modId, existingFileHelper);
    //}

    @Override
    protected void addTags() {
        this.registerModTags();
        this.registerMinecraftTags();

        this.registerBlockMineables();
    }

    protected void registerBlockMineables() {
        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE)
                .addTag(AFBlockTags.CHAIRS)
                .addTag(AFBlockTags.PLANTER_BOXES)
                .addTag(AFBlockTags.SHELVES)
                .addTag(AFBlockTags.SHUTTERS)
                .addTag(AFBlockTags.STOOLS)
                .addTag(AFBlockTags.TABLES);
        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE).add(
                AFBlocks.SERVICE_BELL.get()
        );
    }

    protected void registerMinecraftTags() {
        tag(net.minecraft.tags.BlockTags.NON_FLAMMABLE_WOOD).add(
                AFBlocks.CRIMSON_CHAIR.get(),
                AFBlocks.CRIMSON_PLANTER_BOX.get(),
                AFBlocks.CRIMSON_SHELF.get(),
                AFBlocks.CRIMSON_SHUTTER.get(),
                AFBlocks.CRIMSON_TABLE.get(),
                AFBlocks.WARPED_CHAIR.get(),
                AFBlocks.WARPED_PLANTER_BOX.get(),
                AFBlocks.WARPED_SHELF.get(),
                AFBlocks.WARPED_SHUTTER.get(),
                AFBlocks.WARPED_TABLE.get()
        );
        tag(net.minecraft.tags.BlockTags.UNSTABLE_BOTTOM_CENTER)
                .addTag(AFBlockTags.CHAIRS)
                .addTag(AFBlockTags.STOOLS);
    }

    protected void registerModTags() {
        tag(AFBlockTags.CHAIRS).add(
                AFBlocks.OAK_CHAIR.get(),
                AFBlocks.SPRUCE_CHAIR.get(),
                AFBlocks.BIRCH_CHAIR.get(),
                AFBlocks.JUNGLE_CHAIR.get(),
                AFBlocks.ACACIA_CHAIR.get(),
                AFBlocks.DARK_OAK_CHAIR.get(),
                AFBlocks.CRIMSON_CHAIR.get(),
                AFBlocks.WARPED_CHAIR.get()
        );
        tag(AFBlockTags.PLANTER_BOXES).add(
                AFBlocks.OAK_PLANTER_BOX.get(),
                AFBlocks.SPRUCE_PLANTER_BOX.get(),
                AFBlocks.BIRCH_PLANTER_BOX.get(),
                AFBlocks.JUNGLE_PLANTER_BOX.get(),
                AFBlocks.ACACIA_PLANTER_BOX.get(),
                AFBlocks.DARK_OAK_PLANTER_BOX.get(),
                AFBlocks.CRIMSON_PLANTER_BOX.get(),
                AFBlocks.WARPED_PLANTER_BOX.get()
        );
        tag(AFBlockTags.SHELVES).add(
                AFBlocks.OAK_SHELF.get(),
                AFBlocks.SPRUCE_SHELF.get(),
                AFBlocks.BIRCH_SHELF.get(),
                AFBlocks.JUNGLE_SHELF.get(),
                AFBlocks.ACACIA_SHELF.get(),
                AFBlocks.DARK_OAK_SHELF.get(),
                AFBlocks.CRIMSON_SHELF.get(),
                AFBlocks.WARPED_SHELF.get()
        );
        tag(AFBlockTags.SHUTTERS).add(
                AFBlocks.OAK_SHUTTER.get(),
                AFBlocks.SPRUCE_SHUTTER.get(),
                AFBlocks.BIRCH_SHUTTER.get(),
                AFBlocks.JUNGLE_SHUTTER.get(),
                AFBlocks.ACACIA_SHUTTER.get(),
                AFBlocks.DARK_OAK_SHUTTER.get(),
                AFBlocks.CRIMSON_SHUTTER.get(),
                AFBlocks.WARPED_SHUTTER.get()
        );
        tag(AFBlockTags.TABLES).add(
                AFBlocks.OAK_TABLE.get(),
                AFBlocks.SPRUCE_TABLE.get(),
                AFBlocks.BIRCH_TABLE.get(),
                AFBlocks.JUNGLE_TABLE.get(),
                AFBlocks.ACACIA_TABLE.get(),
                AFBlocks.DARK_OAK_TABLE.get(),
                AFBlocks.CRIMSON_TABLE.get(),
                AFBlocks.WARPED_TABLE.get()
        );
        tag(AFBlockTags.CURTAINS).add(
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
        );
        tag(AFBlockTags.STOOLS).add(
                AFBlocks.WHITE_STOOL.get(),
                AFBlocks.ORANGE_STOOL.get(),
                AFBlocks.MAGENTA_STOOL.get(),
                AFBlocks.LIGHT_BLUE_STOOL.get(),
                AFBlocks.YELLOW_STOOL.get(),
                AFBlocks.LIME_STOOL.get(),
                AFBlocks.PINK_STOOL.get(),
                AFBlocks.GRAY_STOOL.get(),
                AFBlocks.LIGHT_GRAY_STOOL.get(),
                AFBlocks.CYAN_STOOL.get(),
                AFBlocks.PURPLE_STOOL.get(),
                AFBlocks.BLUE_STOOL.get(),
                AFBlocks.BROWN_STOOL.get(),
                AFBlocks.GREEN_STOOL.get(),
                AFBlocks.RED_STOOL.get(),
                AFBlocks.BLACK_STOOL.get()
        );
        tag(AFBlockTags.HAMMER_INVALID).add(
                        Blocks.END_PORTAL_FRAME,
                        Blocks.PISTON_HEAD,
                        Blocks.MOVING_PISTON,
                        Blocks.ATTACHED_MELON_STEM,
                        Blocks.ATTACHED_PUMPKIN_STEM,
                        Blocks.SMALL_DRIPLEAF,
                        Blocks.BIG_DRIPLEAF,
                        Blocks.BIG_DRIPLEAF_STEM
                )
                .addTag(net.minecraft.tags.BlockTags.DOORS)
                .addTag(net.minecraft.tags.BlockTags.BEDS)
                .addOptionalTag(new ResourceLocation("c:chests"))
                .addOptionalTag(new ResourceLocation("forge:chests"));
        tag(AFBlockTags.CHAIRS_TUCKABLE_UNDER)
                .addTag(AFBlockTags.CURTAINS);
    }
}