package com.starfish_studios.another_furniture;

import com.starfish_studios.another_furniture.platform.CommonServices;
import com.starfish_studios.another_furniture.registry.AFBlockEntityTypes;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import com.starfish_studios.another_furniture.registry.AFEntityTypes;
import com.starfish_studios.another_furniture.registry.AFSoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnotherFurniture {
    public static final String MOD_ID = "another_furniture";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final CreativeModeTab TAB = CommonServices.HELPER.registerCreativeModeTab(new ResourceLocation(MOD_ID, "tab"), () -> new ItemStack(AFBlocks.OAK_CHAIR.get()));

    public static void init() {
        AFBlocks.init();
        AFSoundEvents.init();
        AFEntityTypes.init();
        AFBlockEntityTypes.init();
    }
}