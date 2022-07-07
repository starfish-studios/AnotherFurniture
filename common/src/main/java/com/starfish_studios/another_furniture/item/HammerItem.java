package com.starfish_studios.another_furniture.item;

import com.starfish_studios.another_furniture.registry.AFBlockTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
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

public class HammerItem extends Item {

    public HammerItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(new TranslatableComponent("item.another_furniture.hammer.tooltip.1").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(new TranslatableComponent("item.another_furniture.hammer.tooltip.2").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = context.getLevel().getBlockState(pos);
        Rotation rotation = context.getPlayer().isCrouching() ? Rotation.COUNTERCLOCKWISE_90 : Rotation.CLOCKWISE_90;
        if (!isInvalid(state)) {
            BlockState new_state = state;
            boolean can_rotate = true;
            if (state.hasProperty(BlockStateProperties.FACING)) {
                Direction direction = state.getValue(BlockStateProperties.FACING);
                new_state = state.setValue(BlockStateProperties.FACING, rotation.rotate(direction));

            } else if (state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
                new_state = state.setValue(BlockStateProperties.HORIZONTAL_FACING, rotation.rotate(direction));

            } else if (state.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
                new_state = state.cycle(BlockStateProperties.HORIZONTAL_AXIS);

            } else {
                can_rotate = false;
            }

            if (can_rotate && new_state != state) {
                if (new_state.canSurvive(level, pos)) {
                    level.setBlock(pos, Block.updateFromNeighbourShapes(new_state, level, pos), 3);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.useOn(context);
    }

    public boolean isInvalid(BlockState state) {
        if (state.hasProperty(BlockStateProperties.EXTENDED)) {
            if (state.getValue(BlockStateProperties.EXTENDED)) return true;
        }
        return state.is(AFBlockTags.HAMMER_INVALID);
    }
}
