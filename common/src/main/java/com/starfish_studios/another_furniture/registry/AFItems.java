package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.AnotherFurniture;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class AFItems {

    public static final Supplier<Item> FURNITURE_HAMMER = AFRegistry.registerItem("furniture_hammer", () -> new Item(new Item.Properties().durability(892)), "tab");

    public static void init() {}
}
