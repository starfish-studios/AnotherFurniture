package com.starfish_studios.another_furniture.block.entity;

import com.starfish_studios.another_furniture.registry.AFBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class CurtainBlockEntity extends BlockEntity {
    public CurtainBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(AFBlockEntityTypes.CURTAIN.get(), pWorldPosition, pBlockState);
    }
}
