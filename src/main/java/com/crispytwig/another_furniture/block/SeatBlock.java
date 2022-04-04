package com.crispytwig.another_furniture.block;

import net.minecraft.world.level.block.Block;

public class SeatBlock extends Block {

    public SeatBlock(Properties properties) {
        super(properties);
    }

    public float seatHeight() {
        return 0.5F;
    }
}
