package com.starfish_studios.another_furniture.event;

import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TargetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BlockInteractionEvent {
    public static InteractionResult use(Player player, Level level, InteractionHand hand, BlockHitResult pos) {
        BlockState state = level.getBlockState(pos.getBlockPos());

        //if (state.is(Blocks.WATER_CAULDRON)) return cauldronWashing(player, level, state, hand, pos);

        player.pick(1, 1, false);

        return InteractionResult.PASS;

    }


}
