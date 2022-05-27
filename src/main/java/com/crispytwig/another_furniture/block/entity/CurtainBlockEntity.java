package com.crispytwig.another_furniture.block.entity;

import com.crispytwig.another_furniture.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class CurtainBlockEntity extends BlockEntity {


    public CurtainBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.CURTAIN.get(), pWorldPosition, pBlockState);
    }

    @Override
    public AABB getRenderBoundingBox() {
        return new AABB(worldPosition.offset(0, -1, 0), worldPosition.offset(1, 1, 1));
    }
}
