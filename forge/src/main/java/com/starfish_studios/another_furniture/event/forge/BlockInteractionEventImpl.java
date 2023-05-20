package com.starfish_studios.another_furniture.event.forge;

import com.starfish_studios.another_furniture.event.BlockInteractionEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockInteractionEventImpl {
    @SubscribeEvent
    public static void rightClick(PlayerInteractEvent.RightClickBlock event) {
        BlockInteractionEvent.use(event.getEntity(), event.getLevel(), event.getHand(), event.getHitVec());
    }
}
