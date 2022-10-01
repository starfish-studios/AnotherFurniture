package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.AnotherFurniture;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class AFItems {

    public static final Supplier<Item> FURNITURE_HAMMER = AFRegistry.registerItem("furniture_hammer", () -> new Item(new Item.Properties().durability(892).tab(AnotherFurniture.TAB)));

    public static void init() {}
}
