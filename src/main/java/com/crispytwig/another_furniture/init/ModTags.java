package com.crispytwig.another_furniture.init;

import com.crispytwig.another_furniture.AnotherFurnitureMod;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static final TagKey<Block> CHAIRS_CAN_TUCK_UNDER = blockTag("chairs_can_tuck_under");
    public static final TagKey<Block> BYPASS_SEAT_COLLISION_CHECK = blockTag("bypass_seat_collision_check");

    private static TagKey<Item> itemTag(String name) {
        return TagKey.create(Registry.ITEM_REGISTRY, AnotherFurnitureMod.res(name));
    }

    private static TagKey<Block> blockTag(String name) {
        return TagKey.create(Registry.BLOCK_REGISTRY, AnotherFurnitureMod.res(name));
    }
}
