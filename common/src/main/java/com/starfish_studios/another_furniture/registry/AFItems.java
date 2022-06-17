package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.block.*;
import com.starfish_studios.another_furniture.item.HammerItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

import java.util.function.Supplier;

public class AFItems {

    public static final Supplier<HammerItem> HAMMER = AFRegistry.registerItem("hammer", () -> new HammerItem(new Item.Properties().tab(AnotherFurniture.TAB)));

    public static void init() {}
}
