package com.crispytwig.another_furniture.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SeatBlock extends BaseBlock {

    public SeatBlock(Properties properties) {
        super(properties);
    }

    public float seatHeight() {
        return 0.25F;
    }

}
