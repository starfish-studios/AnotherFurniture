package com.starfish_studios.another_furniture.item;

import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.registry.AFBlockTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FurnitureHammerItem extends Item {

    public FurnitureHammerItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(Component.translatable("item.another_furniture.furniture_hammer.tooltip.1").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.translatable("item.another_furniture.furniture_hammer.tooltip.2").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        BlockState state = context.getLevel().getBlockState(pos);

        if (state.hasProperty(ModBlockStateProperties.HAMMERABLE_ATTACHMENT)) {
            boolean attachment = state.getValue(ModBlockStateProperties.HAMMERABLE_ATTACHMENT);
            if (attachment) {
                level.setBlock(pos, state.setValue(ModBlockStateProperties.HAMMERABLE_ATTACHMENT, false), 3);
                level.playSound(player, pos, SoundEvents.BAMBOO_BREAK, SoundSource.BLOCKS, 1.0f, 1.0f);
            } else {
                level.setBlock(pos, state.setValue(ModBlockStateProperties.HAMMERABLE_ATTACHMENT, true), 3);
                level.playSound(player, pos, SoundEvents.BAMBOO_PLACE, SoundSource.BLOCKS, 1.0f, 1.0f);
            }
            if (player != null) {
                context.getItemInHand().hurtAndBreak(1, player, (playerx) -> playerx.broadcastBreakEvent(context.getHand()));
            }
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
}
