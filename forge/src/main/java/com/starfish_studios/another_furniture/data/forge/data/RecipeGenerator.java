package com.starfish_studios.another_furniture.data.forge.data;

import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class RecipeGenerator extends RecipeProvider {
    public RecipeGenerator(DataGenerator generator) {
        super(generator);
    }

    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        // Chairs
        chair(consumer, AFBlocks.OAK_CHAIR.get(), Blocks.OAK_PLANKS);
        chair(consumer, AFBlocks.SPRUCE_CHAIR.get(), Items.SPRUCE_PLANKS);
        chair(consumer, AFBlocks.BIRCH_CHAIR.get(), Items.BIRCH_PLANKS);
        chair(consumer, AFBlocks.JUNGLE_CHAIR.get(), Items.JUNGLE_PLANKS);
        chair(consumer, AFBlocks.ACACIA_CHAIR.get(), Items.ACACIA_PLANKS);
        chair(consumer, AFBlocks.DARK_OAK_CHAIR.get(), Items.DARK_OAK_PLANKS);
        chair(consumer, AFBlocks.MANGROVE_CHAIR.get(), Items.MANGROVE_PLANKS);
        chair(consumer, AFBlocks.CRIMSON_CHAIR.get(), Items.CRIMSON_PLANKS);
        chair(consumer, AFBlocks.WARPED_CHAIR.get(), Items.WARPED_PLANKS);

        // Shelves
        shelf(consumer, AFBlocks.OAK_SHELF.get(), Items.OAK_PLANKS);
        shelf(consumer, AFBlocks.SPRUCE_SHELF.get(), Items.SPRUCE_PLANKS);
        shelf(consumer, AFBlocks.BIRCH_SHELF.get(), Items.BIRCH_PLANKS);
        shelf(consumer, AFBlocks.JUNGLE_SHELF.get(), Items.JUNGLE_PLANKS);
        shelf(consumer, AFBlocks.ACACIA_SHELF.get(), Items.ACACIA_PLANKS);
        shelf(consumer, AFBlocks.DARK_OAK_SHELF.get(), Items.DARK_OAK_PLANKS);
        shelf(consumer, AFBlocks.MANGROVE_SHELF.get(), Items.MANGROVE_PLANKS);
        shelf(consumer, AFBlocks.CRIMSON_SHELF.get(), Items.CRIMSON_PLANKS);
        shelf(consumer, AFBlocks.WARPED_SHELF.get(), Items.WARPED_PLANKS);

        // Tables
        table(consumer, AFBlocks.OAK_TABLE.get(), Items.OAK_PLANKS);
        table(consumer, AFBlocks.SPRUCE_TABLE.get(), Items.SPRUCE_PLANKS);
        table(consumer, AFBlocks.BIRCH_TABLE.get(), Items.BIRCH_PLANKS);
        table(consumer, AFBlocks.JUNGLE_TABLE.get(), Items.JUNGLE_PLANKS);
        table(consumer, AFBlocks.ACACIA_TABLE.get(), Items.ACACIA_PLANKS);
        table(consumer, AFBlocks.DARK_OAK_TABLE.get(), Items.DARK_OAK_PLANKS);
        table(consumer, AFBlocks.MANGROVE_TABLE.get(), Items.MANGROVE_PLANKS);
        table(consumer, AFBlocks.CRIMSON_TABLE.get(), Items.CRIMSON_PLANKS);
        table(consumer, AFBlocks.WARPED_TABLE.get(), Items.WARPED_PLANKS);

        // Benches
        bench(consumer, AFBlocks.OAK_BENCH.get(), Items.OAK_PLANKS);
        bench(consumer, AFBlocks.SPRUCE_BENCH.get(), Items.SPRUCE_PLANKS);
        bench(consumer, AFBlocks.BIRCH_BENCH.get(), Items.BIRCH_PLANKS);
        bench(consumer, AFBlocks.JUNGLE_BENCH.get(), Items.JUNGLE_PLANKS);
        bench(consumer, AFBlocks.ACACIA_BENCH.get(), Items.ACACIA_PLANKS);
        bench(consumer, AFBlocks.DARK_OAK_BENCH.get(), Items.DARK_OAK_PLANKS);
        bench(consumer, AFBlocks.MANGROVE_BENCH.get(), Items.MANGROVE_PLANKS);
        bench(consumer, AFBlocks.CRIMSON_BENCH.get(), Items.CRIMSON_PLANKS);
        bench(consumer, AFBlocks.WARPED_BENCH.get(), Items.WARPED_PLANKS);

        // Shutters
        shutter(consumer, AFBlocks.OAK_SHUTTER.get(), Items.OAK_SLAB);
        shutter(consumer, AFBlocks.SPRUCE_SHUTTER.get(), Items.SPRUCE_SLAB);
        shutter(consumer, AFBlocks.BIRCH_SHUTTER.get(), Items.BIRCH_SLAB);
        shutter(consumer, AFBlocks.JUNGLE_SHUTTER.get(), Items.JUNGLE_SLAB);
        shutter(consumer, AFBlocks.ACACIA_SHUTTER.get(), Items.ACACIA_SLAB);
        shutter(consumer, AFBlocks.DARK_OAK_SHUTTER.get(), Items.DARK_OAK_SLAB);
        shutter(consumer, AFBlocks.MANGROVE_SHUTTER.get(), Items.MANGROVE_SLAB);
        shutter(consumer, AFBlocks.CRIMSON_SHUTTER.get(), Items.CRIMSON_SLAB);
        shutter(consumer, AFBlocks.WARPED_SHUTTER.get(), Items.WARPED_SLAB);

        // Planter Boxes
        planterBox(consumer, AFBlocks.OAK_PLANTER_BOX.get(), Items.OAK_SLAB, Items.OAK_PLANKS);
        planterBox(consumer, AFBlocks.SPRUCE_PLANTER_BOX.get(), Items.SPRUCE_SLAB, Items.SPRUCE_PLANKS);
        planterBox(consumer, AFBlocks.BIRCH_PLANTER_BOX.get(), Items.BIRCH_SLAB, Items.BIRCH_PLANKS);
        planterBox(consumer, AFBlocks.JUNGLE_PLANTER_BOX.get(), Items.JUNGLE_SLAB, Items.JUNGLE_PLANKS);
        planterBox(consumer, AFBlocks.ACACIA_PLANTER_BOX.get(), Items.ACACIA_SLAB, Items.ACACIA_PLANKS);
        planterBox(consumer, AFBlocks.DARK_OAK_PLANTER_BOX.get(), Items.DARK_OAK_SLAB, Items.DARK_OAK_PLANKS);
        planterBox(consumer, AFBlocks.MANGROVE_PLANTER_BOX.get(), Items.MANGROVE_SLAB, Items.MANGROVE_PLANKS);
        planterBox(consumer, AFBlocks.CRIMSON_PLANTER_BOX.get(), Items.CRIMSON_SLAB, Items.CRIMSON_PLANKS);
        planterBox(consumer, AFBlocks.WARPED_PLANTER_BOX.get(), Items.WARPED_SLAB, Items.WARPED_PLANKS);

        // Stools
        stool(consumer, AFBlocks.WHITE_STOOL.get(), Blocks.WHITE_WOOL);
        stool(consumer, AFBlocks.ORANGE_STOOL.get(), Items.ORANGE_WOOL);
        stool(consumer, AFBlocks.MAGENTA_STOOL.get(), Items.MAGENTA_WOOL);
        stool(consumer, AFBlocks.LIGHT_BLUE_STOOL.get(), Items.LIGHT_BLUE_WOOL);
        stool(consumer, AFBlocks.YELLOW_STOOL.get(), Items.YELLOW_WOOL);
        stool(consumer, AFBlocks.LIME_STOOL.get(), Items.LIME_WOOL);
        stool(consumer, AFBlocks.PINK_STOOL.get(), Items.PINK_WOOL);
        stool(consumer, AFBlocks.GRAY_STOOL.get(), Items.GRAY_WOOL);
        stool(consumer, AFBlocks.LIGHT_GRAY_STOOL.get(), Items.LIGHT_GRAY_WOOL);
        stool(consumer, AFBlocks.CYAN_STOOL.get(), Items.CYAN_WOOL);
        stool(consumer, AFBlocks.PURPLE_STOOL.get(), Items.PURPLE_WOOL);
        stool(consumer, AFBlocks.BLUE_STOOL.get(), Items.BLUE_WOOL);
        stool(consumer, AFBlocks.BROWN_STOOL.get(), Items.BROWN_WOOL);
        stool(consumer, AFBlocks.GREEN_STOOL.get(), Items.GREEN_WOOL);
        stool(consumer, AFBlocks.RED_STOOL.get(), Items.RED_WOOL);
        stool(consumer, AFBlocks.BLACK_STOOL.get(), Items.BLACK_WOOL);

        // Stools Dyeing
        coloredStoolFromWhiteStoolAndDye(consumer, AFBlocks.ORANGE_STOOL.get(), Items.ORANGE_DYE);
        coloredStoolFromWhiteStoolAndDye(consumer, AFBlocks.MAGENTA_STOOL.get(), Items.MAGENTA_DYE);
        coloredStoolFromWhiteStoolAndDye(consumer, AFBlocks.LIGHT_BLUE_STOOL.get(), Items.LIGHT_BLUE_DYE);
        coloredStoolFromWhiteStoolAndDye(consumer, AFBlocks.YELLOW_STOOL.get(), Items.YELLOW_DYE);
        coloredStoolFromWhiteStoolAndDye(consumer, AFBlocks.LIME_STOOL.get(), Items.LIME_DYE);
        coloredStoolFromWhiteStoolAndDye(consumer, AFBlocks.PINK_STOOL.get(), Items.PINK_DYE);
        coloredStoolFromWhiteStoolAndDye(consumer, AFBlocks.GRAY_STOOL.get(), Items.GRAY_DYE);
        coloredStoolFromWhiteStoolAndDye(consumer, AFBlocks.LIGHT_GRAY_STOOL.get(), Items.LIGHT_GRAY_DYE);
        coloredStoolFromWhiteStoolAndDye(consumer, AFBlocks.CYAN_STOOL.get(), Items.CYAN_DYE);
        coloredStoolFromWhiteStoolAndDye(consumer, AFBlocks.PURPLE_STOOL.get(), Items.PURPLE_DYE);
        coloredStoolFromWhiteStoolAndDye(consumer, AFBlocks.BLUE_STOOL.get(), Items.BLUE_DYE);
        coloredStoolFromWhiteStoolAndDye(consumer, AFBlocks.BROWN_STOOL.get(), Items.BROWN_DYE);
        coloredStoolFromWhiteStoolAndDye(consumer, AFBlocks.GREEN_STOOL.get(), Items.GREEN_DYE);
        coloredStoolFromWhiteStoolAndDye(consumer, AFBlocks.RED_STOOL.get(), Items.RED_DYE);
        coloredStoolFromWhiteStoolAndDye(consumer, AFBlocks.BLACK_STOOL.get(), Items.BLACK_DYE);

        // Curtains
        curtain(consumer, AFBlocks.WHITE_CURTAIN.get(), Items.WHITE_WOOL);
        curtain(consumer, AFBlocks.ORANGE_CURTAIN.get(), Items.ORANGE_WOOL);
        curtain(consumer, AFBlocks.MAGENTA_CURTAIN.get(), Items.MAGENTA_WOOL);
        curtain(consumer, AFBlocks.LIGHT_BLUE_CURTAIN.get(), Items.LIGHT_BLUE_WOOL);
        curtain(consumer, AFBlocks.YELLOW_CURTAIN.get(), Items.YELLOW_WOOL);
        curtain(consumer, AFBlocks.LIME_CURTAIN.get(), Items.LIME_WOOL);
        curtain(consumer, AFBlocks.PINK_CURTAIN.get(), Items.PINK_WOOL);
        curtain(consumer, AFBlocks.GRAY_CURTAIN.get(), Items.GRAY_WOOL);
        curtain(consumer, AFBlocks.LIGHT_GRAY_CURTAIN.get(), Items.LIGHT_GRAY_WOOL);
        curtain(consumer, AFBlocks.CYAN_CURTAIN.get(), Items.CYAN_WOOL);
        curtain(consumer, AFBlocks.PURPLE_CURTAIN.get(), Items.PURPLE_WOOL);
        curtain(consumer, AFBlocks.BLUE_CURTAIN.get(), Items.BLUE_WOOL);
        curtain(consumer, AFBlocks.BROWN_CURTAIN.get(), Items.BROWN_WOOL);
        curtain(consumer, AFBlocks.GREEN_CURTAIN.get(), Items.GREEN_WOOL);
        curtain(consumer, AFBlocks.RED_CURTAIN.get(), Items.RED_WOOL);
        curtain(consumer, AFBlocks.BLACK_CURTAIN.get(), Items.BLACK_WOOL);

        // Curtains Dyeing
        coloredCurtainFromWhiteCurtainAndDye(consumer, AFBlocks.ORANGE_CURTAIN.get(), Items.ORANGE_DYE);
        coloredCurtainFromWhiteCurtainAndDye(consumer, AFBlocks.MAGENTA_CURTAIN.get(), Items.MAGENTA_DYE);
        coloredCurtainFromWhiteCurtainAndDye(consumer, AFBlocks.LIGHT_BLUE_CURTAIN.get(), Items.LIGHT_BLUE_DYE);
        coloredCurtainFromWhiteCurtainAndDye(consumer, AFBlocks.YELLOW_CURTAIN.get(), Items.YELLOW_DYE);
        coloredCurtainFromWhiteCurtainAndDye(consumer, AFBlocks.LIME_CURTAIN.get(), Items.LIME_DYE);
        coloredCurtainFromWhiteCurtainAndDye(consumer, AFBlocks.PINK_CURTAIN.get(), Items.PINK_DYE);
        coloredCurtainFromWhiteCurtainAndDye(consumer, AFBlocks.GRAY_CURTAIN.get(), Items.GRAY_DYE);
        coloredCurtainFromWhiteCurtainAndDye(consumer, AFBlocks.LIGHT_GRAY_CURTAIN.get(), Items.LIGHT_GRAY_DYE);
        coloredCurtainFromWhiteCurtainAndDye(consumer, AFBlocks.CYAN_CURTAIN.get(), Items.CYAN_DYE);
        coloredCurtainFromWhiteCurtainAndDye(consumer, AFBlocks.PURPLE_CURTAIN.get(), Items.PURPLE_DYE);
        coloredCurtainFromWhiteCurtainAndDye(consumer, AFBlocks.BLUE_CURTAIN.get(), Items.BLUE_DYE);
        coloredCurtainFromWhiteCurtainAndDye(consumer, AFBlocks.BROWN_CURTAIN.get(), Items.BROWN_DYE);
        coloredCurtainFromWhiteCurtainAndDye(consumer, AFBlocks.GREEN_CURTAIN.get(), Items.GREEN_DYE);
        coloredCurtainFromWhiteCurtainAndDye(consumer, AFBlocks.RED_CURTAIN.get(), Items.RED_DYE);
        coloredCurtainFromWhiteCurtainAndDye(consumer, AFBlocks.BLACK_CURTAIN.get(), Items.BLACK_DYE);

        // Service Bell
        ShapedRecipeBuilder.shaped(AFBlocks.SERVICE_BELL.get(), 3)
                .pattern(" N ")
                .pattern("III")
                .define('N', Items.IRON_NUGGET)
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_nugget", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_NUGGET))
                .save(consumer);
    }

    private static void chair(Consumer<FinishedRecipe> consumer, ItemLike chair, ItemLike planks) {
        ShapedRecipeBuilder.shaped(chair, 3)
                .pattern("# ")
                .pattern("##")
                .pattern("//")
                .define('#', planks)
                .define('/', Items.STICK)
                .unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(planks))
                .group("chairs")
                .save(consumer);
    }

    private static void shelf(Consumer<FinishedRecipe> consumer, ItemLike shelf, ItemLike planks) {
        ShapedRecipeBuilder.shaped(shelf, 3)
                .pattern("###")
                .pattern("/  ")
                .define('#', planks)
                .define('/', Items.STICK)
                .unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(planks))
                .group("shelves")
                .save(consumer);
    }

    private static void table(Consumer<FinishedRecipe> consumer, ItemLike table, ItemLike planks) {
        ShapedRecipeBuilder.shaped(table, 3)
                .pattern("###")
                .pattern("/ /")
                .define('#', planks)
                .define('/', Items.STICK)
                .unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(planks))
                .group("tables")
                .save(consumer);
    }

    private static void bench(Consumer<FinishedRecipe> consumer, ItemLike table, ItemLike slab) {
        ShapedRecipeBuilder.shaped(table, 3)
                .pattern("/  ")
                .pattern("SSS")
                .pattern("/ /")
                .define('S', slab)
                .define('/', Items.STICK)
                .unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(slab))
                .group("benches")
                .save(consumer);
    }

    private static void shutter(Consumer<FinishedRecipe> consumer, ItemLike shutter, ItemLike slab) {
        ShapedRecipeBuilder.shaped(shutter, 4)
                .pattern("SS")
                .pattern("SS")
                .pattern("SS")
                .define('S', slab)
                .unlockedBy("has_slab", InventoryChangeTrigger.TriggerInstance.hasItems(slab))
                .group("shutters")
                .save(consumer);
    }

    private static void planterBox(Consumer<FinishedRecipe> consumer, ItemLike planterBox, ItemLike slab, ItemLike planks) {
        ShapedRecipeBuilder.shaped(planterBox, 3)
                .pattern("SDS")
                .pattern("###")
                .define('D', Items.DIRT)
                .define('S', slab)
                .define('#', planks)
                .unlockedBy("has_slab", InventoryChangeTrigger.TriggerInstance.hasItems(slab))
                .group("planter_boxes")
                .save(consumer);
    }

    private static void stool(Consumer<FinishedRecipe> consumer, ItemLike stool, ItemLike wool) {
        ShapedRecipeBuilder.shaped(stool, 3)
                .pattern("#W#")
                .pattern("/ /")
                .define('#', ItemTags.PLANKS)
                .define('W', wool)
                .define('/', Items.STICK)
                .unlockedBy("has_wool", InventoryChangeTrigger.TriggerInstance.hasItems(wool))
                .group("stools")
                .save(consumer);
    }

    private static void curtain(Consumer<FinishedRecipe> consumer, ItemLike curtain, ItemLike wool) {
        ShapedRecipeBuilder.shaped(curtain, 3)
                .pattern("//")
                .pattern("WW")
                .pattern("WW")
                .define('W', wool)
                .define('/', Items.STICK)
                .unlockedBy("has_wool", InventoryChangeTrigger.TriggerInstance.hasItems(wool))
                .group("curtains")
                .save(consumer);
    }

    protected static void coloredCurtainFromWhiteCurtainAndDye(Consumer<FinishedRecipe> consumer, ItemLike coloredCurtain, ItemLike dye) {
        ShapelessRecipeBuilder.shapeless(coloredCurtain).requires(dye).requires(AFBlocks.WHITE_CURTAIN.get()).unlockedBy("has_white_curtain", InventoryChangeTrigger.TriggerInstance.hasItems(AFBlocks.WHITE_CURTAIN.get())).group("curtains").save(consumer, new ResourceLocation(AnotherFurniture.MOD_ID, getItemName(coloredCurtain) + "_dyeing"));
    }

    protected static void coloredStoolFromWhiteStoolAndDye(Consumer<FinishedRecipe> consumer, ItemLike coloredStool, ItemLike dye) {
        ShapelessRecipeBuilder.shapeless(coloredStool).requires(dye).requires(AFBlocks.WHITE_STOOL.get()).unlockedBy("has_white_stool", InventoryChangeTrigger.TriggerInstance.hasItems(AFBlocks.WHITE_STOOL.get())).group("stools").save(consumer, new ResourceLocation(AnotherFurniture.MOD_ID, getItemName(coloredStool) + "_dyeing"));
    }

    protected static String getItemName(ItemLike item) {
        return Registry.ITEM.getKey(item.asItem()).getPath();
    }
}