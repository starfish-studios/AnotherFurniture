package com.starfish_studios.another_furniture.forge;

import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import com.starfish_studios.another_furniture.registry.AFItemTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.model.pipeline.VertexConsumerWrapper;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TestClientEvent {

//    @SubscribeEvent
//    public static void worldRender(RenderLevelStageEvent event) {
//        final Minecraft mc = Minecraft.getInstance();
//        if (mc.level != null && event.getStage() == RenderLevelStageEvent.Stage.AFTER_SOLID_BLOCKS) {
//            AnotherFurniture.wallpaperManager.renderWallpapers(event.getPoseStack(), event.getFrustum());
//        }
//    }
//
//    @SubscribeEvent
//    public static void rightClick(PlayerInteractEvent.RightClickBlock event) {
//        ItemStack stack = event.getItemStack();
//        Direction face = event.getFace();
//
//        if (stack.is(Items.STICK) && face != Direction.UP && face != Direction.DOWN) {
//            AnotherFurniture.wallpaperManager.addWallpaper(event.getLevel(), event.getPos(), face);
//        }
//    }
    @SubscribeEvent
    public static void anvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        String rename = event.getName();
        if (rename.equals("jeb_")) {
            if (left.is(AFItemTags.STOOLS)) {
                ItemStack output = AFBlocks.RAINBOW_STOOL.get().asItem().getDefaultInstance();
                output.setCount(left.getCount());
                event.setOutput(output);
                int a = event.getCost() == 0 ? 1 : event.getCost();
                System.out.println(a);
                event.setCost(a);
            }
        }
    }
}