package com.starfish_studios.another_furniture.registry.fabric;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public class AFFurnaceFuelRegistryImpl {
    public static void register(ItemLike item, int burnTicks) {
        FuelRegistry.INSTANCE.add(item, burnTicks);
    }
    public static void register(TagKey<Item> tag, int burnTicks) {
        FuelRegistry.INSTANCE.add(tag, burnTicks);
    }
}
