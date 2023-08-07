package com.starfish_studios.another_furniture.integration.common.create;

import com.starfish_studios.another_furniture.block.CurtainBlock;
import com.starfish_studios.another_furniture.block.ShutterBlock;
import com.starfish_studios.another_furniture.block.properties.VerticalConnectionType;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class CreateCommon {
    public static boolean canStickToContraption(BlockState state, Direction direction) {
        if (state.getBlock() instanceof ShutterBlock) {
            if (state.getValue(ShutterBlock.TYPE) == VerticalConnectionType.MIDDLE && direction.getAxis().isVertical()) return true;
            if (state.getValue(ShutterBlock.TYPE) == VerticalConnectionType.TOP && direction == Direction.DOWN) return true;
            if (state.getValue(ShutterBlock.TYPE) == VerticalConnectionType.BOTTOM && direction == Direction.UP) return true;
        }
        if (state.getBlock() instanceof CurtainBlock) {
            if (direction.getAxis().isVertical() && state.getValue(CurtainBlock.FACING_VERTICAL) == direction) return true;
        }
        return false;
    }
}
