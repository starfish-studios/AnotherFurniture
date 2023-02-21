package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.AnotherFurniture;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class AFItems {

    //Legacy item, don't remove until 1.20. until then, its uncraftable
    public static final Supplier<Item> FURNITURE_HAMMER = AFRegistry.registerItem("furniture_hammer", () -> new Item(new Item.Properties().durability(892)));

    public static void init() {}
}
