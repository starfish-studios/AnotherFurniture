package com.starfish_studios.another_furniture.util.block;

import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.registry.AFItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public interface HammerableBlock {

    default boolean tryHammerBlock(Player player, LevelAccessor level, BlockPos pos, BlockState state) {
        ItemStack mainhand = player.getMainHandItem();
        ItemStack offhand = player.getOffhandItem();
        EquipmentSlot handslot = EquipmentSlot.MAINHAND;
        if (!mainhand.is(AFItemTags.FURNITURE_HAMMER)) {
            if (offhand.is(AFItemTags.FURNITURE_HAMMER)) {
                handslot = EquipmentSlot.OFFHAND;
            } else {
                return false;
            }
        }

        if (state.hasProperty(ModBlockStateProperties.HAMMERABLE_ATTACHMENT)) {
            boolean attachment = state.getValue(ModBlockStateProperties.HAMMERABLE_ATTACHMENT);
            if (attachment) {
                level.setBlock(pos, state.setValue(ModBlockStateProperties.HAMMERABLE_ATTACHMENT, false), 3);
                level.playSound(null, pos, getRemoveSound(), SoundSource.BLOCKS, 1.0f, 1.0f);
            } else {
                level.setBlock(pos, state.setValue(ModBlockStateProperties.HAMMERABLE_ATTACHMENT, true), 3);
                level.playSound(null, pos, getAddSound(), SoundSource.BLOCKS, 1.0f, 1.0f);
            }

            EquipmentSlot finalHandslot = handslot;
            offhand.hurtAndBreak(1, player, (playerx) -> playerx.broadcastBreakEvent(finalHandslot));
            return true;
        }
        return false;
    }

    default SoundEvent getAddSound() {
        return SoundEvents.BAMBOO_SAPLING_PLACE;
    }
    default SoundEvent getRemoveSound() {
        return SoundEvents.BAMBOO_SAPLING_BREAK;
    }

}
