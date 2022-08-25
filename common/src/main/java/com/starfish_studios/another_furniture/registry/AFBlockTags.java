package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.AnotherFurniture;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class AFBlockTags {

    public static final TagKey<Block> CHAIRS = blockTag("chairs");
    public static final TagKey<Block> CURTAINS = blockTag("curtains");
    public static final TagKey<Block> PLANTER_BOXES = blockTag("planter_boxes");
    public static final TagKey<Block> SHELVES = blockTag("shelves");
    public static final TagKey<Block> SHUTTERS = blockTag("shutters");
    public static final TagKey<Block> STOOLS = blockTag("stools");
    public static final TagKey<Block> TABLES = blockTag("tables");

    public static final TagKey<Block> CHAIRS_TUCKABLE_UNDER = blockTag("chairs_tuckable_under");
    public static final TagKey<Block> NO_SEAT_COLLISION_CHECK = blockTag("no_seat_collision_check");

    private static TagKey<Block> blockTag(String name) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(AnotherFurniture.MOD_ID, name));
    }
}
