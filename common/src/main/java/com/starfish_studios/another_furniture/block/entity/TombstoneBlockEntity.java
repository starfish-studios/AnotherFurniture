package com.starfish_studios.another_furniture.block.entity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TombstoneBlockEntity extends SignBlockEntity {
    public TombstoneBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(blockPos, blockState);
    }

    public ModelPart getModel() {
        return null;
    }
}
