package com.crispytwig.another_furniture.init;

import com.crispytwig.another_furniture.block.ChairBlock;
import com.crispytwig.another_furniture.block.ShelfBlock;
import com.crispytwig.another_furniture.block.StoolBlock;
import com.crispytwig.another_furniture.block.TableBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.crispytwig.another_furniture.AnotherFurnitureMod.MOD_ID;
import static com.crispytwig.another_furniture.AnotherFurnitureMod.TAB;
import static com.crispytwig.another_furniture.init.ModItems.ITEMS;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    // CHAIRS

    public static final RegistryObject<Block> OAK_CHAIR = registerBlock("oak_chair",
            () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SPRUCE_CHAIR = registerBlock("spruce_chair",
            () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIRCH_CHAIR = registerBlock("birch_chair",
            () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> JUNGLE_CHAIR = registerBlock("jungle_chair",
            () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ACACIA_CHAIR = registerBlock("acacia_chair",
            () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> DARK_OAK_CHAIR = registerBlock("dark_oak_chair",
            () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CRIMSON_CHAIR = registerBlock("crimson_chair",
            () -> new ChairBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WARPED_CHAIR = registerBlock("warped_chair",
            () -> new ChairBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // SHELVES

    public static final RegistryObject<Block> OAK_SHELF = registerBlock("oak_shelf",
            () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SPRUCE_SHELF = registerBlock("spruce_shelf",
            () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIRCH_SHELF = registerBlock("birch_shelf",
            () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> JUNGLE_SHELF = registerBlock("jungle_shelf",
            () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ACACIA_SHELF = registerBlock("acacia_shelf",
            () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> DARK_OAK_SHELF = registerBlock("dark_oak_shelf",
            () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CRIMSON_SHELF = registerBlock("crimson_shelf",
            () -> new ShelfBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WARPED_SHELF = registerBlock("warped_shelf",
            () -> new ShelfBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // TABLES

    public static final RegistryObject<Block> OAK_TABLE = registerBlock("oak_table",
            () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SPRUCE_TABLE = registerBlock("spruce_table",
            () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIRCH_TABLE = registerBlock("birch_table",
            () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> JUNGLE_TABLE = registerBlock("jungle_table",
            () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ACACIA_TABLE = registerBlock("acacia_table",
            () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> DARK_OAK_TABLE = registerBlock("dark_oak_table",
            () -> new TableBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CRIMSON_TABLE = registerBlock("crimson_table",
            () -> new TableBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WARPED_TABLE = registerBlock("warped_table",
            () -> new TableBlock(Block.Properties.of(Material.NETHER_WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    // STOOLS

    public static final RegistryObject<Block> WHITE_STOOL = registerBlock("white_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> ORANGE_STOOL = registerBlock("orange_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> MAGENTA_STOOL = registerBlock("magenta_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> LIGHT_BLUE_STOOL = registerBlock("light_blue_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> YELLOW_STOOL = registerBlock("yellow_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> LIME_STOOL = registerBlock("lime_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> PINK_STOOL = registerBlock("pink_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> GRAY_STOOL = registerBlock("gray_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> LIGHT_GRAY_STOOL = registerBlock("light_gray_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> CYAN_STOOL = registerBlock("cyan_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> PURPLE_STOOL = registerBlock("purple_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> BLUE_STOOL = registerBlock("blue_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> BROWN_STOOL = registerBlock("brown_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> GREEN_STOOL = registerBlock("green_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> RED_STOOL = registerBlock("red_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> BLACK_STOOL = registerBlock("black_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOL)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registryObject = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(TAB)));
        return registryObject;
    }
}
