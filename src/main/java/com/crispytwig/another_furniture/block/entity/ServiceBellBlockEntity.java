package com.crispytwig.another_furniture.block.entity;

import com.crispytwig.another_furniture.init.ModBlockEntities;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.ShulkerBoxRenderer;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BellBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import static com.crispytwig.another_furniture.AnotherFurnitureMod.res;

public class ServiceBellBlockEntity extends BlockEntity {
    public int ticks;
    public boolean shaking;

    public ServiceBellBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.SERVICE_BELL.get(), pWorldPosition, pBlockState);
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
