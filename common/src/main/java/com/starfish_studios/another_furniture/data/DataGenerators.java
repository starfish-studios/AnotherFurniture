package com.starfish_studios.another_furniture.data;

//import net.minecraft.data.DataGenerator;
//import net.minecraftforge.common.data.ExistingFileHelper;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
//
//@SuppressWarnings("unused")
//@Mod.EventBusSubscriber(modid = AnotherFurniture.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
//public class DataGenerators {
//    @SubscribeEvent
//    public static void gatherData(GatherDataEvent event) {
//        DataGenerator generator = event.getGenerator();
//        ExistingFileHelper helper = event.getExistingFileHelper();
//        if (event.includeServer()) {
//            BlockTags blockTags = new BlockTags(generator, AnotherFurniture.MODID, helper);
//            generator.addProvider(blockTags);
//            generator.addProvider(new ItemTags(generator, blockTags, AnotherFurniture.MODID, helper));
//            generator.addProvider(new EntityTags(generator, AnotherFurniture.MODID, helper));
//            generator.addProvider(new Recipes(generator));
//            generator.addProvider(new Advancements(generator));
//        }
//        if (event.includeClient()) {
//            BlockStates blockStates = new BlockStates(generator, helper);
//            generator.addProvider(blockStates);
//            generator.addProvider(new ItemModels(generator, blockStates.models().existingFileHelper));
//        }
//    }
//}