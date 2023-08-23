package com.starfish_studios.another_furniture.integration.common.create;

import com.starfish_studios.another_furniture.block.ShutterBlock;
import com.starfish_studios.another_furniture.block.properties.VerticalConnectionType;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class CreateCommon {
    public static boolean canStickToContraption(BlockState state, Direction direction) {
        if (state.getBlock() instanceof ShutterBlock) {
            VerticalConnectionType type = state.getValue(ShutterBlock.TYPE);
            if (type == VerticalConnectionType.MIDDLE && direction.getAxis().isVertical()) return true;
            if (type == VerticalConnectionType.TOP && direction == Direction.DOWN) return true;
            if (type == VerticalConnectionType.BOTTOM && direction == Direction.UP) return true;
        }
        return false;
    }
}
