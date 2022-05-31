package com.crispytwig.another_furniture.init;

import com.crispytwig.another_furniture.block.*;
import com.crispytwig.another_furniture.util.CompatUtil;
import com.crispytwig.another_furniture.util.RegistryUtil;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.crispytwig.another_furniture.AnotherFurnitureMod.MOD_ID;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);


    // Chair
    public static final RegistryObject<Block> OAK_CHAIR = RegistryUtil.createBlockAndItem("oak_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SPRUCE_CHAIR = RegistryUtil.createBlockAndItem("spruce_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIRCH_CHAIR = RegistryUtil.createBlockAndItem("birch_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> JUNGLE_CHAIR = RegistryUtil.createBlockAndItem("jungle_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ACACIA_CHAIR = RegistryUtil.createBlockAndItem("acacia_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> DARK_OAK_CHAIR = RegistryUtil.createBlockAndItem("dark_oak_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CRIMSON_CHAIR = RegistryUtil.createBlockAndItem("crimson_chair", () -> new ChairBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WARPED_CHAIR = RegistryUtil.createBlockAndItem("warped_chair", () -> new ChairBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Shelf
    public static final RegistryObject<Block> OAK_SHELF = RegistryUtil.createBlockAndItem("oak_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> SPRUCE_SHELF = RegistryUtil.createBlockAndItem("spruce_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> BIRCH_SHELF = RegistryUtil.createBlockAndItem("birch_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> JUNGLE_SHELF = RegistryUtil.createBlockAndItem("jungle_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> ACACIA_SHELF = RegistryUtil.createBlockAndItem("acacia_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> DARK_OAK_SHELF = RegistryUtil.createBlockAndItem("dark_oak_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> CRIMSON_SHELF = RegistryUtil.createBlockAndItem("crimson_shelf", () -> new ShelfBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> WARPED_SHELF = RegistryUtil.createBlockAndItem("warped_shelf", () -> new ShelfBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    // Table
    public static final RegistryObject<Block> OAK_TABLE = RegistryUtil.createBlockAndItem("oak_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SPRUCE_TABLE = RegistryUtil.createBlockAndItem("spruce_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIRCH_TABLE = RegistryUtil.createBlockAndItem("birch_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> JUNGLE_TABLE = RegistryUtil.createBlockAndItem("jungle_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ACACIA_TABLE = RegistryUtil.createBlockAndItem("acacia_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> DARK_OAK_TABLE = RegistryUtil.createBlockAndItem("dark_oak_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CRIMSON_TABLE = RegistryUtil.createBlockAndItem("crimson_table", () -> new TableBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WARPED_TABLE = RegistryUtil.createBlockAndItem("warped_table", () -> new TableBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Shutter
    public static final RegistryObject<Block> OAK_SHUTTER = RegistryUtil.createBlockAndItem("oak_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> SPRUCE_SHUTTER = RegistryUtil.createBlockAndItem("spruce_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> BIRCH_SHUTTER = RegistryUtil.createBlockAndItem("birch_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> JUNGLE_SHUTTER = RegistryUtil.createBlockAndItem("jungle_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> ACACIA_SHUTTER = RegistryUtil.createBlockAndItem("acacia_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> DARK_OAK_SHUTTER = RegistryUtil.createBlockAndItem("dark_oak_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> CRIMSON_SHUTTER = RegistryUtil.createBlockAndItem("crimson_shutter", () -> new ShutterBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> WARPED_SHUTTER = RegistryUtil.createBlockAndItem("warped_shutter", () -> new ShutterBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    // Box
    public static final RegistryObject<Block> OAK_PLANTER_BOX = RegistryUtil.createBlockAndItem("oak_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SPRUCE_PLANTER_BOX = RegistryUtil.createBlockAndItem("spruce_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIRCH_PLANTER_BOX = RegistryUtil.createBlockAndItem("birch_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> JUNGLE_PLANTER_BOX = RegistryUtil.createBlockAndItem("jungle_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ACACIA_PLANTER_BOX = RegistryUtil.createBlockAndItem("acacia_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> DARK_OAK_PLANTER_BOX = RegistryUtil.createBlockAndItem("dark_oak_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CRIMSON_PLANTER_BOX = RegistryUtil.createBlockAndItem("crimson_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WARPED_PLANTER_BOX = RegistryUtil.createBlockAndItem("warped_planter_box", () -> new PlanterBoxBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Stool
    public static final RegistryObject<Block> WHITE_STOOL = RegistryUtil.createBlockAndItem("white_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ORANGE_STOOL = RegistryUtil.createBlockAndItem("orange_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> MAGENTA_STOOL = RegistryUtil.createBlockAndItem("magenta_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> LIGHT_BLUE_STOOL = RegistryUtil.createBlockAndItem("light_blue_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> YELLOW_STOOL = RegistryUtil.createBlockAndItem("yellow_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> LIME_STOOL = RegistryUtil.createBlockAndItem("lime_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PINK_STOOL = RegistryUtil.createBlockAndItem("pink_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> GRAY_STOOL = RegistryUtil.createBlockAndItem("gray_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> LIGHT_GRAY_STOOL = RegistryUtil.createBlockAndItem("light_gray_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CYAN_STOOL = RegistryUtil.createBlockAndItem("cyan_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PURPLE_STOOL = RegistryUtil.createBlockAndItem("purple_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BLUE_STOOL = RegistryUtil.createBlockAndItem("blue_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BROWN_STOOL = RegistryUtil.createBlockAndItem("brown_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> GREEN_STOOL = RegistryUtil.createBlockAndItem("green_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> RED_STOOL = RegistryUtil.createBlockAndItem("red_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BLACK_STOOL = RegistryUtil.createBlockAndItem("black_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)));

    // Bell
    public static final RegistryObject<Block> SERVICE_BELL = RegistryUtil.createBlockAndItem("service_bell", () -> new ServiceBellBlock(Block.Properties.of(Material.METAL).strength(2.0F, 3.0F).sound(SoundType.METAL)));

    // Curtain
    public static final RegistryObject<Block> CURTAIN = RegistryUtil.createBlockAndItem("curtain", () -> new CurtainBlock(Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));
}
