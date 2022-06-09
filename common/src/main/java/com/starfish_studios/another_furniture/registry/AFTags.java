package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.AnotherFurniture;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class AFTags {

    public static final TagKey<Block> CHAIRS_TUCKABLE_UNDER = blockTag("chairs_tuckable_under");
    public static final TagKey<Block> NO_SEAT_COLLISION_CHECK = blockTag("no_seat_collision_check");
    public static final TagKey<Block> CURTAINS = blockTag("curtains");

    public static final TagKey<Item> PLANTER_BOX_PLACEABLES = itemTag("planter_box_placeables");
    public static final TagKey<Item> PLANTER_BOX_BANNED = itemTag("planter_box_banned");

    private static TagKey<Block> blockTag(String name) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(AnotherFurniture.MOD_ID, name));
    }

    private static TagKey<Item> itemTag(String name) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(AnotherFurniture.MOD_ID, name));
    }
}
