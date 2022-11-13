package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.block.*;
import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;

import java.util.function.Supplier;

public class AFBlocks {

    // Version 1 / Release
    // Chair
    public static final Supplier<Block> OAK_CHAIR = registerBlock("oak_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> SPRUCE_CHAIR = registerBlock("spruce_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BIRCH_CHAIR = registerBlock("birch_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> JUNGLE_CHAIR = registerBlock("jungle_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> ACACIA_CHAIR = registerBlock("acacia_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> DARK_OAK_CHAIR = registerBlock("dark_oak_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> MANGROVE_CHAIR = registerBlock("mangrove_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> CRIMSON_CHAIR = registerBlock("crimson_chair", () -> new ChairBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> WARPED_CHAIR = registerBlock("warped_chair", () -> new ChairBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Shelf
    public static final Supplier<Block> OAK_SHELF = registerBlock("oak_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Supplier<Block> SPRUCE_SHELF = registerBlock("spruce_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Supplier<Block> BIRCH_SHELF = registerBlock("birch_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Supplier<Block> JUNGLE_SHELF = registerBlock("jungle_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Supplier<Block> ACACIA_SHELF = registerBlock("acacia_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Supplier<Block> DARK_OAK_SHELF = registerBlock("dark_oak_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Supplier<Block> MANGROVE_SHELF = registerBlock("mangrove_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Supplier<Block> CRIMSON_SHELF = registerBlock("crimson_shelf", () -> new ShelfBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Supplier<Block> WARPED_SHELF = registerBlock("warped_shelf", () -> new ShelfBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    // Table
    public static final Supplier<Block> OAK_TABLE = registerBlock("oak_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> SPRUCE_TABLE = registerBlock("spruce_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BIRCH_TABLE = registerBlock("birch_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> JUNGLE_TABLE = registerBlock("jungle_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> ACACIA_TABLE = registerBlock("acacia_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> DARK_OAK_TABLE = registerBlock("dark_oak_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> MANGROVE_TABLE = registerBlock("mangrove_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> CRIMSON_TABLE = registerBlock("crimson_table", () -> new TableBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> WARPED_TABLE = registerBlock("warped_table", () -> new TableBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Stool
    public static final Supplier<Block> WHITE_STOOL = registerBlock("white_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> ORANGE_STOOL = registerBlock("orange_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> MAGENTA_STOOL = registerBlock("magenta_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> LIGHT_BLUE_STOOL = registerBlock("light_blue_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> YELLOW_STOOL = registerBlock("yellow_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> LIME_STOOL = registerBlock("lime_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> PINK_STOOL = registerBlock("pink_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> GRAY_STOOL = registerBlock("gray_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> LIGHT_GRAY_STOOL = registerBlock("light_gray_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> CYAN_STOOL = registerBlock("cyan_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> PURPLE_STOOL = registerBlock("purple_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BLUE_STOOL = registerBlock("blue_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BROWN_STOOL = registerBlock("brown_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> GREEN_STOOL = registerBlock("green_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> RED_STOOL = registerBlock("red_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BLACK_STOOL = registerBlock("black_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));

    // Version 2
    // Shutter
    public static final Supplier<Block> OAK_SHUTTER = registerBlock("oak_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Supplier<Block> SPRUCE_SHUTTER = registerBlock("spruce_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Supplier<Block> BIRCH_SHUTTER = registerBlock("birch_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Supplier<Block> JUNGLE_SHUTTER = registerBlock("jungle_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Supplier<Block> ACACIA_SHUTTER = registerBlock("acacia_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Supplier<Block> DARK_OAK_SHUTTER = registerBlock("dark_oak_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Supplier<Block> MANGROVE_SHUTTER = registerBlock("mangrove_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Supplier<Block> CRIMSON_SHUTTER = registerBlock("crimson_shutter", () -> new ShutterBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final Supplier<Block> WARPED_SHUTTER = registerBlock("warped_shutter", () -> new ShutterBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    // Planter Box
    public static final Supplier<Block> OAK_PLANTER_BOX = registerBlock("oak_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> SPRUCE_PLANTER_BOX = registerBlock("spruce_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BIRCH_PLANTER_BOX = registerBlock("birch_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> JUNGLE_PLANTER_BOX = registerBlock("jungle_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> ACACIA_PLANTER_BOX = registerBlock("acacia_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> DARK_OAK_PLANTER_BOX = registerBlock("dark_oak_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> MANGROVE_PLANTER_BOX = registerBlock("mangrove_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> CRIMSON_PLANTER_BOX = registerBlock("crimson_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> WARPED_PLANTER_BOX = registerBlock("warped_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Curtain
    public static final Supplier<Block> WHITE_CURTAIN = registerBlock("white_curtain", () -> new CurtainBlock(DyeColor.WHITE, Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));
    public static final Supplier<Block> ORANGE_CURTAIN = registerBlock("orange_curtain", () -> new CurtainBlock(DyeColor.ORANGE, Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));
    public static final Supplier<Block> MAGENTA_CURTAIN = registerBlock("magenta_curtain", () -> new CurtainBlock(DyeColor.MAGENTA, Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));
    public static final Supplier<Block> LIGHT_BLUE_CURTAIN = registerBlock("light_blue_curtain", () -> new CurtainBlock(DyeColor.LIGHT_BLUE, Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));
    public static final Supplier<Block> YELLOW_CURTAIN = registerBlock("yellow_curtain", () -> new CurtainBlock(DyeColor.YELLOW, Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));
    public static final Supplier<Block> LIME_CURTAIN = registerBlock("lime_curtain", () -> new CurtainBlock(DyeColor.LIME, Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));
    public static final Supplier<Block> PINK_CURTAIN = registerBlock("pink_curtain", () -> new CurtainBlock(DyeColor.PINK, Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));
    public static final Supplier<Block> GRAY_CURTAIN = registerBlock("gray_curtain", () -> new CurtainBlock(DyeColor.GRAY, Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));
    public static final Supplier<Block> LIGHT_GRAY_CURTAIN = registerBlock("light_gray_curtain", () -> new CurtainBlock(DyeColor.LIGHT_GRAY, Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));
    public static final Supplier<Block> CYAN_CURTAIN = registerBlock("cyan_curtain", () -> new CurtainBlock(DyeColor.CYAN, Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));
    public static final Supplier<Block> PURPLE_CURTAIN = registerBlock("purple_curtain", () -> new CurtainBlock(DyeColor.PURPLE, Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));
    public static final Supplier<Block> BLUE_CURTAIN = registerBlock("blue_curtain", () -> new CurtainBlock(DyeColor.BLUE, Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));
    public static final Supplier<Block> BROWN_CURTAIN = registerBlock("brown_curtain", () -> new CurtainBlock(DyeColor.BROWN, Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));
    public static final Supplier<Block> GREEN_CURTAIN = registerBlock("green_curtain", () -> new CurtainBlock(DyeColor.GREEN, Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));
    public static final Supplier<Block> RED_CURTAIN = registerBlock("red_curtain", () -> new CurtainBlock(DyeColor.RED, Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));
    public static final Supplier<Block> BLACK_CURTAIN = registerBlock("black_curtain", () -> new CurtainBlock(DyeColor.BLACK, Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));

    // Bell
    public static final Supplier<Block> SERVICE_BELL = registerBlock("service_bell", () -> new ServiceBellBlock(Block.Properties.of(Material.METAL).strength(2.0F, 3.0F).sound(SoundType.METAL)));

    // Version 3
    //Bench
    public static final Supplier<Block> OAK_BENCH = registerBlock("oak_bench", () -> new BenchBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> SPRUCE_BENCH = registerBlock("spruce_bench", () -> new BenchBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BIRCH_BENCH = registerBlock("birch_bench", () -> new BenchBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> JUNGLE_BENCH = registerBlock("jungle_bench", () -> new BenchBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> ACACIA_BENCH = registerBlock("acacia_bench", () -> new BenchBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> DARK_OAK_BENCH = registerBlock("dark_oak_bench", () -> new BenchBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> MANGROVE_BENCH = registerBlock("mangrove_bench", () -> new BenchBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> CRIMSON_BENCH = registerBlock("crimson_bench", () -> new BenchBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> WARPED_BENCH = registerBlock("warped_bench", () -> new BenchBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    public static final Supplier<Block> OAK_DRAWER = registerBlock("oak_drawer", () -> new DrawerBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> SPRUCE_DRAWER = registerBlock("spruce_drawer", () -> new DrawerBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BIRCH_DRAWER = registerBlock("birch_drawer", () -> new DrawerBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> JUNGLE_DRAWER = registerBlock("jungle_drawer", () -> new DrawerBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> ACACIA_DRAWER = registerBlock("acacia_drawer", () -> new DrawerBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> DARK_OAK_DRAWER = registerBlock("dark_oak_drawer", () -> new DrawerBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> MANGROVE_DRAWER = registerBlock("mangrove_drawer", () -> new DrawerBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> CRIMSON_DRAWER = registerBlock("crimson_drawer", () -> new DrawerBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> WARPED_DRAWER = registerBlock("warped_drawer", () -> new DrawerBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Lamp
    public static final Supplier<Block> WHITE_LAMP = registerBlock("white_lamp", () -> new LampBlock(DyeColor.WHITE, Block.Properties.of(Material.WOOD).lightLevel((blockState) -> blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> ORANGE_LAMP = registerBlock("orange_lamp", () -> new LampBlock(DyeColor.ORANGE, Block.Properties.of(Material.WOOD).lightLevel((blockState) -> blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> MAGENTA_LAMP = registerBlock("magenta_lamp", () -> new LampBlock(DyeColor.MAGENTA, Block.Properties.of(Material.WOOD).lightLevel((blockState) -> blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> LIGHT_BLUE_LAMP = registerBlock("light_blue_lamp", () -> new LampBlock(DyeColor.LIGHT_BLUE, Block.Properties.of(Material.WOOD).lightLevel((blockState) -> blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> YELLOW_LAMP = registerBlock("yellow_lamp", () -> new LampBlock(DyeColor.YELLOW, Block.Properties.of(Material.WOOD).lightLevel((blockState) -> blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> LIME_LAMP = registerBlock("lime_lamp", () -> new LampBlock(DyeColor.LIME, Block.Properties.of(Material.WOOD).lightLevel((blockState) -> blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> PINK_LAMP = registerBlock("pink_lamp", () -> new LampBlock(DyeColor.PINK, Block.Properties.of(Material.WOOD).lightLevel((blockState) -> blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> GRAY_LAMP = registerBlock("gray_lamp", () -> new LampBlock(DyeColor.GRAY, Block.Properties.of(Material.WOOD).lightLevel((blockState) -> blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> LIGHT_GRAY_LAMP = registerBlock("light_gray_lamp", () -> new LampBlock(DyeColor.LIGHT_GRAY, Block.Properties.of(Material.WOOD).lightLevel((blockState) -> blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> CYAN_LAMP = registerBlock("cyan_lamp", () -> new LampBlock(DyeColor.CYAN, Block.Properties.of(Material.WOOD).lightLevel((blockState) -> blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> PURPLE_LAMP = registerBlock("purple_lamp", () -> new LampBlock(DyeColor.PURPLE, Block.Properties.of(Material.WOOD).lightLevel((blockState) -> blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BLUE_LAMP = registerBlock("blue_lamp", () -> new LampBlock(DyeColor.BLUE, Block.Properties.of(Material.WOOD).lightLevel((blockState) -> blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BROWN_LAMP = registerBlock("brown_lamp", () -> new LampBlock(DyeColor.BROWN, Block.Properties.of(Material.WOOD).lightLevel((blockState) -> blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> GREEN_LAMP = registerBlock("green_lamp", () -> new LampBlock(DyeColor.GREEN, Block.Properties.of(Material.WOOD).lightLevel((blockState) -> blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> RED_LAMP = registerBlock("red_lamp", () -> new LampBlock(DyeColor.RED, Block.Properties.of(Material.WOOD).lightLevel((blockState) -> blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BLACK_LAMP = registerBlock("black_lamp", () -> new LampBlock(DyeColor.BLACK, Block.Properties.of(Material.WOOD).lightLevel((blockState) -> blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> LAMP_CONNECTOR = registerBlockOnly("lamp_connector", () -> new LampConnectorBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Sofa
    public static final Supplier<Block> WHITE_SOFA = registerBlock("white_sofa", () -> new SofaBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> ORANGE_SOFA = registerBlock("orange_sofa", () -> new SofaBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> MAGENTA_SOFA = registerBlock("magenta_sofa", () -> new SofaBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> LIGHT_BLUE_SOFA = registerBlock("light_blue_sofa", () -> new SofaBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> YELLOW_SOFA = registerBlock("yellow_sofa", () -> new SofaBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> LIME_SOFA = registerBlock("lime_sofa", () -> new SofaBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> PINK_SOFA = registerBlock("pink_sofa", () -> new SofaBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> GRAY_SOFA = registerBlock("gray_sofa", () -> new SofaBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> LIGHT_GRAY_SOFA = registerBlock("light_gray_sofa", () -> new SofaBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> CYAN_SOFA = registerBlock("cyan_sofa", () -> new SofaBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> PURPLE_SOFA = registerBlock("purple_sofa", () -> new SofaBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BLUE_SOFA = registerBlock("blue_sofa", () -> new SofaBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BROWN_SOFA = registerBlock("brown_sofa", () -> new SofaBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> GREEN_SOFA = registerBlock("green_sofa", () -> new SofaBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> RED_SOFA = registerBlock("red_sofa", () -> new SofaBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BLACK_SOFA = registerBlock("black_sofa", () -> new SofaBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));

    // Tall Stool
    public static final Supplier<Block> WHITE_TALL_STOOL = registerBlock("white_tall_stool", () -> new TallStoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> ORANGE_TALL_STOOL = registerBlock("orange_tall_stool", () -> new TallStoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> MAGENTA_TALL_STOOL = registerBlock("magenta_tall_stool", () -> new TallStoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> LIGHT_BLUE_TALL_STOOL = registerBlock("light_blue_tall_stool", () -> new TallStoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> YELLOW_TALL_STOOL = registerBlock("yellow_tall_stool", () -> new TallStoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> LIME_TALL_STOOL = registerBlock("lime_tall_stool", () -> new TallStoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> PINK_TALL_STOOL = registerBlock("pink_tall_stool", () -> new TallStoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> GRAY_TALL_STOOL = registerBlock("gray_tall_stool", () -> new TallStoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> LIGHT_GRAY_TALL_STOOL = registerBlock("light_gray_tall_stool", () -> new TallStoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> CYAN_TALL_STOOL = registerBlock("cyan_tall_stool", () -> new TallStoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> PURPLE_TALL_STOOL = registerBlock("purple_tall_stool", () -> new TallStoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BLUE_TALL_STOOL = registerBlock("blue_tall_stool", () -> new TallStoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BROWN_TALL_STOOL = registerBlock("brown_tall_stool", () -> new TallStoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> GREEN_TALL_STOOL = registerBlock("green_tall_stool", () -> new TallStoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> RED_TALL_STOOL = registerBlock("red_tall_stool", () -> new TallStoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BLACK_TALL_STOOL = registerBlock("black_tall_stool", () -> new TallStoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));

    // Tombstone
    public static final Supplier<Block> STONE_TOMBSTONE = registerBlock("stone_tombstone", () -> new TombstoneBlock(Block.Properties.of(Material.STONE).strength(3.0F, 3.0F).sound(SoundType.STONE)));

    public static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        Supplier<T> supplier = AFRegistry.registerBlock(name, block);
        AFRegistry.registerItem(name, () -> new BlockItem(supplier.get(), new Item.Properties().tab(AnotherFurniture.TAB)));
        return supplier;
    }

    public static <T extends Block> Supplier<T> registerBlockHidden(String name, Supplier<T> block) {
        Supplier<T> supplier = AFRegistry.registerBlock(name, block);
        AFRegistry.registerItem(name, () -> new BlockItem(supplier.get(), new Item.Properties()));
        return supplier;
    }

    public static <T extends Block> Supplier<T> registerBlockOnly(String name, Supplier<T> block) {
        return AFRegistry.registerBlock(name, block);
    }

    public static void init() {}
}
