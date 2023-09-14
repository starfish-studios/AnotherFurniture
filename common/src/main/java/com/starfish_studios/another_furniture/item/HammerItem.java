package com.starfish_studios.another_furniture.item;

import com.starfish_studios.another_furniture.util.block.HammerableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class HammerItem extends Item {

    public HammerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof HammerableBlock hammerableBlock && hammerableBlock.tryHammerBlock(state, level, pos, context.getPlayer(), context.getHand())) {
            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), (livingEntity) -> {
                livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
}
