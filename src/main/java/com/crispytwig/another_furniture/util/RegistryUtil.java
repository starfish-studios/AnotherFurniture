package com.crispytwig.another_furniture.util;

import com.crispytwig.another_furniture.AnotherFurnitureMod;
import com.crispytwig.another_furniture.init.ModBlocks;
import com.crispytwig.another_furniture.init.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.function.Supplier;

public class RegistryUtil {
    private static boolean dev = false;

    private static final String[] hide_items = {"service_bell"};

    public static <T extends Block> RegistryObject<T> createBlockAndItem(String name, Supplier<? extends T> supplier) {
        CreativeModeTab group = !Arrays.stream(hide_items).anyMatch(name::equals) ? AnotherFurnitureMod.TAB : null;
        RegistryObject<T> registryObject = ModBlocks.BLOCKS.register(name, supplier);
        ModItems.ITEMS.register(name, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(group)));
        return registryObject;
    }

    public static <T extends Block> RegistryObject<T> createBlockAndItemCompat(String mod, String name, Supplier<? extends T> supplier) {
        CreativeModeTab group = ModList.get().isLoaded(mod) || dev ? AnotherFurnitureMod.TAB : null;
        RegistryObject<T> registryObject = ModBlocks.BLOCKS.register(mod + "_" + name, supplier);
        ModItems.ITEMS.register(mod + "_" + name, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(group)));
        return registryObject;
    }
}
