package com.starfish_studios.another_furniture;

import com.starfish_studios.another_furniture.registry.*;
import com.starfish_studios.another_furniture.util.block.WallpaperManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnotherFurniture {
    public static final String MOD_ID = "another_furniture";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final CreativeModeTab TAB = AFRegistry.registerCreativeModeTab(new ResourceLocation(MOD_ID, "tab"), () -> new ItemStack(AFBlocks.OAK_CHAIR.get()));
    public static final WallpaperManager wallpaperManager = new WallpaperManager();

    public static void init() {
        AFBlocks.init();
        AFItems.init();
        AFSoundEvents.init();
        AFEntityTypes.init();
        AFBlockEntityTypes.init();
    }

    public static void registerFlammables() {
        AFRegistry.setFlammable(AFBlocks.OAK_CHAIR, 5, 20);
        AFRegistry.setFlammable(AFBlocks.SPRUCE_CHAIR, 5, 20);
        AFRegistry.setFlammable(AFBlocks.BIRCH_CHAIR, 5, 20);
        AFRegistry.setFlammable(AFBlocks.JUNGLE_CHAIR, 5, 20);
        AFRegistry.setFlammable(AFBlocks.ACACIA_CHAIR, 5, 20);
        AFRegistry.setFlammable(AFBlocks.DARK_OAK_CHAIR, 5, 20);
        AFRegistry.setFlammable(AFBlocks.MANGROVE_CHAIR, 5, 20);

        AFRegistry.setFlammable(AFBlocks.OAK_SHELF, 5, 20);
        AFRegistry.setFlammable(AFBlocks.SPRUCE_SHELF, 5, 20);
        AFRegistry.setFlammable(AFBlocks.BIRCH_SHELF, 5, 20);
        AFRegistry.setFlammable(AFBlocks.JUNGLE_SHELF, 5, 20);
        AFRegistry.setFlammable(AFBlocks.ACACIA_SHELF, 5, 20);
        AFRegistry.setFlammable(AFBlocks.DARK_OAK_SHELF, 5, 20);
        AFRegistry.setFlammable(AFBlocks.MANGROVE_SHELF, 5, 20);

        AFRegistry.setFlammable(AFBlocks.OAK_TABLE, 5, 20);
        AFRegistry.setFlammable(AFBlocks.SPRUCE_TABLE, 5, 20);
        AFRegistry.setFlammable(AFBlocks.BIRCH_TABLE, 5, 20);
        AFRegistry.setFlammable(AFBlocks.JUNGLE_TABLE, 5, 20);
        AFRegistry.setFlammable(AFBlocks.ACACIA_TABLE, 5, 20);
        AFRegistry.setFlammable(AFBlocks.DARK_OAK_TABLE, 5, 20);
        AFRegistry.setFlammable(AFBlocks.MANGROVE_TABLE, 5, 20);

        AFRegistry.setFlammable(AFBlocks.OAK_SHUTTER, 5, 20);
        AFRegistry.setFlammable(AFBlocks.SPRUCE_SHUTTER, 5, 20);
        AFRegistry.setFlammable(AFBlocks.BIRCH_SHUTTER, 5, 20);
        AFRegistry.setFlammable(AFBlocks.JUNGLE_SHUTTER, 5, 20);
        AFRegistry.setFlammable(AFBlocks.ACACIA_SHUTTER, 5, 20);
        AFRegistry.setFlammable(AFBlocks.DARK_OAK_SHUTTER, 5, 20);
        AFRegistry.setFlammable(AFBlocks.MANGROVE_SHUTTER, 5, 20);

        AFRegistry.setFlammable(AFBlocks.OAK_PLANTER_BOX, 5, 20);
        AFRegistry.setFlammable(AFBlocks.SPRUCE_PLANTER_BOX, 5, 20);
        AFRegistry.setFlammable(AFBlocks.BIRCH_PLANTER_BOX, 5, 20);
        AFRegistry.setFlammable(AFBlocks.JUNGLE_PLANTER_BOX, 5, 20);
        AFRegistry.setFlammable(AFBlocks.ACACIA_PLANTER_BOX, 5, 20);
        AFRegistry.setFlammable(AFBlocks.DARK_OAK_PLANTER_BOX, 5, 20);
        AFRegistry.setFlammable(AFBlocks.MANGROVE_PLANTER_BOX, 5, 20);

        AFRegistry.setFlammable(AFBlocks.WHITE_STOOL, 5, 20);
        AFRegistry.setFlammable(AFBlocks.ORANGE_STOOL, 5, 20);
        AFRegistry.setFlammable(AFBlocks.MAGENTA_STOOL, 5, 20);
        AFRegistry.setFlammable(AFBlocks.LIGHT_BLUE_STOOL, 5, 20);
        AFRegistry.setFlammable(AFBlocks.YELLOW_STOOL, 5, 20);
        AFRegistry.setFlammable(AFBlocks.LIME_STOOL, 5, 20);
        AFRegistry.setFlammable(AFBlocks.PINK_STOOL, 5, 20);
        AFRegistry.setFlammable(AFBlocks.GRAY_STOOL, 5, 20);
        AFRegistry.setFlammable(AFBlocks.LIGHT_GRAY_STOOL, 5, 20);
        AFRegistry.setFlammable(AFBlocks.CYAN_STOOL, 5, 20);
        AFRegistry.setFlammable(AFBlocks.PURPLE_STOOL, 5, 20);
        AFRegistry.setFlammable(AFBlocks.BLUE_STOOL, 5, 20);
        AFRegistry.setFlammable(AFBlocks.BROWN_STOOL, 5, 20);
        AFRegistry.setFlammable(AFBlocks.GREEN_STOOL, 5, 20);
        AFRegistry.setFlammable(AFBlocks.RED_STOOL, 5, 20);
        AFRegistry.setFlammable(AFBlocks.BLACK_STOOL, 5, 20);

        AFRegistry.setFlammable(AFBlocks.WHITE_CURTAIN, 5, 20);
        AFRegistry.setFlammable(AFBlocks.ORANGE_CURTAIN, 5, 20);
        AFRegistry.setFlammable(AFBlocks.MAGENTA_CURTAIN, 5, 20);
        AFRegistry.setFlammable(AFBlocks.LIGHT_BLUE_CURTAIN, 5, 20);
        AFRegistry.setFlammable(AFBlocks.YELLOW_CURTAIN, 5, 20);
        AFRegistry.setFlammable(AFBlocks.LIME_CURTAIN, 5, 20);
        AFRegistry.setFlammable(AFBlocks.PINK_CURTAIN, 5, 20);
        AFRegistry.setFlammable(AFBlocks.GRAY_CURTAIN, 5, 20);
        AFRegistry.setFlammable(AFBlocks.LIGHT_GRAY_CURTAIN, 5, 20);
        AFRegistry.setFlammable(AFBlocks.CYAN_CURTAIN, 5, 20);
        AFRegistry.setFlammable(AFBlocks.PURPLE_CURTAIN, 5, 20);
        AFRegistry.setFlammable(AFBlocks.BLUE_CURTAIN, 5, 20);
        AFRegistry.setFlammable(AFBlocks.BROWN_CURTAIN, 5, 20);
        AFRegistry.setFlammable(AFBlocks.GREEN_CURTAIN, 5, 20);
        AFRegistry.setFlammable(AFBlocks.RED_CURTAIN, 5, 20);
        AFRegistry.setFlammable(AFBlocks.BLACK_CURTAIN, 5, 20);
    }
}