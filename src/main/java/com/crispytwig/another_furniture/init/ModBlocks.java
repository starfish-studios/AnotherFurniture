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
    public static final RegistryObject<Block> BIOMESOPLENTY_CHERRY_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "cherry_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_DEAD_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "dead_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_FIR_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "fir_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_HELLBARK_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "hellbark_chair", () -> new ChairBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_JACARANDA_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "jacaranda_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_MAGIC_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "magic_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_MAHOGANY_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "mahogany_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_PALM_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "palm_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_REDWOOD_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "redwood_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_UMBRAN_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "umbran_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_WILLOW_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "willow_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ECOLOGICS_AZALEA_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.ECOLOGICS_ID, "azalea_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ECOLOGICS_COCONUT_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.ECOLOGICS_ID, "coconut_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ECOLOGICS_WALNUT_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.ECOLOGICS_ID, "walnut_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMEMAKEOVER_WILLOW_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMEMAKEOVER_ID, "willow_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMEMAKEOVER_SWAMP_CYPRESS_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMEMAKEOVER_ID, "swamp_cypress_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMEMAKEOVER_ANCIENT_OAK_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMEMAKEOVER_ID, "ancient_oak_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMEMAKEOVER_BLIGHTED_BALSA_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMEMAKEOVER_ID, "blighted_balsa_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ENHANCED_MUSHROOMS_BROWN_MUSHROOM_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.ENHANCED_MUSHROOMS_ID, "brown_mushroom_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ENHANCED_MUSHROOMS_RED_MUSHROOM_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.ENHANCED_MUSHROOMS_ID, "red_mushroom_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> OAK_CHAIR = RegistryUtil.createBlockAndItem("oak_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SPRUCE_CHAIR = RegistryUtil.createBlockAndItem("spruce_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIRCH_CHAIR = RegistryUtil.createBlockAndItem("birch_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> JUNGLE_CHAIR = RegistryUtil.createBlockAndItem("jungle_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ACACIA_CHAIR = RegistryUtil.createBlockAndItem("acacia_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> DARK_OAK_CHAIR = RegistryUtil.createBlockAndItem("dark_oak_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CRIMSON_CHAIR = RegistryUtil.createBlockAndItem("crimson_chair", () -> new ChairBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WARPED_CHAIR = RegistryUtil.createBlockAndItem("warped_chair", () -> new ChairBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> QUARK_AZALEA_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.QUARK_ID, "azalea_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> QUARK_BLOSSOM_CHAIR = RegistryUtil.createBlockAndItemCompat(CompatUtil.QUARK_ID, "blossom_chair", () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Shelf
    public static final RegistryObject<Block> BIOMESOPLENTY_CHERRY_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "cherry_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_DEAD_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "dead_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_FIR_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "fir_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_HELLBARK_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "hellbark_shelf", () -> new ShelfBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_JACARANDA_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "jacaranda_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_MAGIC_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "magic_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_MAHOGANY_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "mahogany_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_PALM_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "palm_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_REDWOOD_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "redwood_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_UMBRAN_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "umbran_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_WILLOW_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "willow_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ECOLOGICS_AZALEA_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.ECOLOGICS_ID, "azalea_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ECOLOGICS_COCONUT_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.ECOLOGICS_ID, "coconut_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ECOLOGICS_WALNUT_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.ECOLOGICS_ID, "walnut_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMEMAKEOVER_WILLOW_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMEMAKEOVER_ID, "willow_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMEMAKEOVER_SWAMP_CYPRESS_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMEMAKEOVER_ID, "swamp_cypress_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMEMAKEOVER_ANCIENT_OAK_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMEMAKEOVER_ID, "ancient_oak_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMEMAKEOVER_BLIGHTED_BALSA_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMEMAKEOVER_ID, "blighted_balsa_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ENHANCED_MUSHROOMS_BROWN_MUSHROOM_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.ENHANCED_MUSHROOMS_ID, "brown_mushroom_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ENHANCED_MUSHROOMS_RED_MUSHROOM_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.ENHANCED_MUSHROOMS_ID, "red_mushroom_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> OAK_SHELF = RegistryUtil.createBlockAndItem("oak_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SPRUCE_SHELF = RegistryUtil.createBlockAndItem("spruce_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIRCH_SHELF = RegistryUtil.createBlockAndItem("birch_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> JUNGLE_SHELF = RegistryUtil.createBlockAndItem("jungle_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ACACIA_SHELF = RegistryUtil.createBlockAndItem("acacia_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> DARK_OAK_SHELF = RegistryUtil.createBlockAndItem("dark_oak_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CRIMSON_SHELF = RegistryUtil.createBlockAndItem("crimson_shelf", () -> new ShelfBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WARPED_SHELF = RegistryUtil.createBlockAndItem("warped_shelf", () -> new ShelfBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> QUARK_AZALEA_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.QUARK_ID, "azalea_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> QUARK_BLOSSOM_SHELF = RegistryUtil.createBlockAndItemCompat(CompatUtil.QUARK_ID, "blossom_shelf", () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Table
    public static final RegistryObject<Block> BIOMESOPLENTY_CHERRY_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "cherry_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_DEAD_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "dead_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_FIR_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "fir_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_HELLBARK_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "hellbark_table", () -> new TableBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_JACARANDA_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "jacaranda_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_MAGIC_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "magic_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_MAHOGANY_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "mahogany_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_PALM_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "palm_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_REDWOOD_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "redwood_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_UMBRAN_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "umbran_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMESOPLENTY_WILLOW_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMESOPLENTY_ID, "willow_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ECOLOGICS_AZALEA_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.ECOLOGICS_ID, "azalea_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ECOLOGICS_COCONUT_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.ECOLOGICS_ID, "coconut_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ECOLOGICS_WALNUT_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.ECOLOGICS_ID, "walnut_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMEMAKEOVER_WILLOW_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMEMAKEOVER_ID, "willow_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMEMAKEOVER_SWAMP_CYPRESS_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMEMAKEOVER_ID, "swamp_cypress_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMEMAKEOVER_ANCIENT_OAK_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMEMAKEOVER_ID, "ancient_oak_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMEMAKEOVER_BLIGHTED_BALSA_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.BIOMEMAKEOVER_ID, "blighted_balsa_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ENHANCED_MUSHROOMS_BROWN_MUSHROOM_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.ENHANCED_MUSHROOMS_ID, "brown_mushroom_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ENHANCED_MUSHROOMS_RED_MUSHROOM_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.ENHANCED_MUSHROOMS_ID, "red_mushroom_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> OAK_TABLE = RegistryUtil.createBlockAndItem("oak_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SPRUCE_TABLE = RegistryUtil.createBlockAndItem("spruce_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIRCH_TABLE = RegistryUtil.createBlockAndItem("birch_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> JUNGLE_TABLE = RegistryUtil.createBlockAndItem("jungle_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ACACIA_TABLE = RegistryUtil.createBlockAndItem("acacia_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> DARK_OAK_TABLE = RegistryUtil.createBlockAndItem("dark_oak_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CRIMSON_TABLE = RegistryUtil.createBlockAndItem("crimson_table", () -> new TableBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WARPED_TABLE = RegistryUtil.createBlockAndItem("warped_table", () -> new TableBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> QUARK_AZALEA_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.QUARK_ID, "azalea_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> QUARK_BLOSSOM_TABLE = RegistryUtil.createBlockAndItemCompat(CompatUtil.QUARK_ID, "blossom_table", () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Shutter
    public static final RegistryObject<Block> OAK_SHUTTER = RegistryUtil.createBlockAndItem("oak_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> SPRUCE_SHUTTER = RegistryUtil.createBlockAndItem("spruce_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> BIRCH_SHUTTER = RegistryUtil.createBlockAndItem("birch_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> JUNGLE_SHUTTER = RegistryUtil.createBlockAndItem("jungle_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> ACACIA_SHUTTER = RegistryUtil.createBlockAndItem("acacia_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> DARK_OAK_SHUTTER = RegistryUtil.createBlockAndItem("dark_oak_shutter", () -> new ShutterBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> CRIMSON_SHUTTER = RegistryUtil.createBlockAndItem("crimson_shutter", () -> new ShutterBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> WARPED_SHUTTER = RegistryUtil.createBlockAndItem("warped_shutter", () -> new ShutterBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    // Stool
    public static final RegistryObject<Block> WHITE_STOOL = RegistryUtil.createBlockAndItem("white_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ORANGE_STOOL = RegistryUtil.createBlockAndItem("orange_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> MAGENTA_STOOL = RegistryUtil.createBlockAndItem("magenta_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> LIGHT_BLUE_STOOL = RegistryUtil.createBlockAndItem("light_blue_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> YELLOW_STOOL = RegistryUtil.createBlockAndItem("yellow_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> LIME_STOOL = RegistryUtil.createBlockAndItem("lime_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PINK_STOOL = RegistryUtil.createBlockAndItem("pink_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> GRAY_STOOL = RegistryUtil.createBlockAndItem("gray_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> LIGHT_GRAY_STOOL = RegistryUtil.createBlockAndItem("light_gray_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CYAN_STOOL = RegistryUtil.createBlockAndItem("cyan_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PURPLE_STOOL = RegistryUtil.createBlockAndItem("purple_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BLUE_STOOL = RegistryUtil.createBlockAndItem("blue_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BROWN_STOOL = RegistryUtil.createBlockAndItem("brown_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> GREEN_STOOL = RegistryUtil.createBlockAndItem("green_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> RED_STOOL = RegistryUtil.createBlockAndItem("red_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BLACK_STOOL = RegistryUtil.createBlockAndItem("black_stool", () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // Bell
    public static final RegistryObject<Block> SERVICE_BELL = RegistryUtil.createBlockAndItem("service_bell", () -> new ServiceBellBlock(Block.Properties.of(Material.METAL).strength(2.0F, 3.0F).sound(SoundType.METAL)));

    // Curtain
    public static final RegistryObject<Block> CURTAIN = RegistryUtil.createBlockAndItem("curtain", () -> new CurtainBlock(Block.Properties.of(Material.WOOL).strength(0.1F).sound(SoundType.WOOL).noOcclusion()));

}
