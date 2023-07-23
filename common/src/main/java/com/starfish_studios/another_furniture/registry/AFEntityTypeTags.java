package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.AnotherFurniture;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class AFEntityTypeTags {

    public static final TagKey<EntityType<?>> CANNOT_SIT_IN_SEATS = entityTypeTag("cannot_sit_in_seats");

    private static TagKey<EntityType<?>> entityTypeTag(String name) {
        return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(AnotherFurniture.MOD_ID, name));
    }

    public static void init() {}
}
