package com.crispytwig.another_furniture.util;

import net.minecraftforge.common.crafting.CraftingHelper;

public class CompatUtil {
    public static final String BIOMESOPLENTY_ID = "biomesoplenty";
    public static final String BIOMEMAKEOVER_ID = "biomemakeover";
    public static final String ECOLOGICS_ID = "ecologics";
    public static final String ENHANCED_MUSHROOMS_ID = "enhanced_mushrooms";
    public static final String QUARK_ID = "quark";

    public static void init() {
        CraftingHelper.register(new QuarkFlagRecipeCondition.Serializer());
    }
}
