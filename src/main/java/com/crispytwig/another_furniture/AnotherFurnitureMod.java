package com.crispytwig.another_furniture;

import com.crispytwig.another_furniture.init.*;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("another_furniture")
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class AnotherFurnitureMod
{
    public static final String MOD_ID = "another_furniture";

    public AnotherFurnitureMod()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModEntities.ENTITIES.register(bus);
        ModSoundEvents.SOUND_EVENTS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static final CreativeModeTab TAB = new CreativeModeTab(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.OAK_CHAIR.get().asItem());
        }
    };
}
