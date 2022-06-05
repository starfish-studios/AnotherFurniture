package com.crispytwig.another_furniture.integration.everycomp;

import com.crispytwig.another_furniture.AnotherFurnitureMod;
import com.crispytwig.another_furniture.block.PlanterBoxBlock;
import com.crispytwig.another_furniture.block.ShutterBlock;
import com.crispytwig.another_furniture.init.ModBlocks;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.WoodGoodAPI;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static com.crispytwig.another_furniture.AnotherFurnitureMod.LOGGER;
import static com.crispytwig.another_furniture.AnotherFurnitureMod.res;

public class EveryCompatRegistry extends SimpleModule {

    public EveryCompatRegistry(String modId, String shortId) {
        super(modId, shortId);
    }

    static {
        SimpleModule mod = new SimpleModule(AnotherFurnitureMod.MOD_ID, "af");
        SimpleEntrySet<?, ?> shutters = SimpleEntrySet.builder("shutter", ModBlocks.OAK_SHUTTER, ()->WoodType.OAK_WOOD_TYPE,
                        w -> new ShutterBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(res("shutters"), Registry.BLOCK_REGISTRY)
                .useLootFromBase()
                .setTab(AnotherFurnitureMod.TAB)
                .addTexture(res("block/shutter/oak_none"))
                .addTexture(res("block/shutter/oak_top"))
                .addTexture(res("block/shutter/oak_middle"))
                .addTexture(res("block/shutter/oak_bottom"))
                .build();
        mod.addEntry(shutters);

        SimpleEntrySet<?, ?> planter_boxes = SimpleEntrySet.builder("planter_box", ModBlocks.OAK_PLANTER_BOX, ()->WoodType.OAK_WOOD_TYPE,
                        w -> new PlanterBoxBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(res("planter_boxes"), Registry.BLOCK_REGISTRY)
                .useLootFromBase()
                .setRenderType(() -> RenderType::cutout)
                .setTab(AnotherFurnitureMod.TAB)
                .addTexture(res("block/planter_box/oak"))
                .build();
        mod.addEntry(planter_boxes);


        WoodGoodAPI.registerModule(mod);
    }

    public static void registerStuff() {

    }
}
