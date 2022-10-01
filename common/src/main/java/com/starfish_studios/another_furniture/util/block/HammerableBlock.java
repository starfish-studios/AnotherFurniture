package com.starfish_studios.another_furniture.util.block;

import com.starfish_studios.another_furniture.registry.AFItemTags;
import com.starfish_studios.another_furniture.registry.AFSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

public interface HammerableBlock {

    default boolean tryHammerBlock(Property<?> property, BlockState state, LevelAccessor level, BlockPos pos, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!stack.is(AFItemTags.FURNITURE_HAMMER) || property == null || !state.hasProperty(property)) return false;

        if (property instanceof BooleanProperty booleanProperty) {
            modifyBoolean(booleanProperty, state, level, pos, player);
        }
        stack.hurtAndBreak(1, player, (playerx) -> playerx.broadcastBreakEvent(hand));
        return true;
    }

    default SoundEvent getUseSound() {
        return AFSoundEvents.HAMMER_USE.get();
    }


    default void modifyBoolean(BooleanProperty property, BlockState state, LevelAccessor level, BlockPos pos, Player player) {
        level.setBlock(pos, state.cycle(property), 3);
        level.playSound(null, pos, getUseSound(), SoundSource.BLOCKS, 1.0f, 1.0f);
    }
}
