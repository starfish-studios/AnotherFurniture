package com.starfish_studios.another_furniture.block.entity;

import com.starfish_studios.another_furniture.registry.AFBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ServiceBellBlockEntity extends BlockEntity {
    public int ticks;
    public boolean shaking;

    public ServiceBellBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(AFBlockEntityTypes.SERVICE_BELL.get(), pWorldPosition, pBlockState);
    }

    public void onHit() {
        if (this.shaking) {
            this.ticks = 0;
        } else {
            this.shaking = true;
        }
    }

    public static void clientTick(Level pLevel, BlockPos pPos, BlockState pState, ServiceBellBlockEntity pBlockEntity) {
        if (pBlockEntity.shaking) {
            ++pBlockEntity.ticks;
        }

        if (pBlockEntity.ticks >= 5) {
            pBlockEntity.shaking = false;
            pBlockEntity.ticks = 0;
        }
    }


}
