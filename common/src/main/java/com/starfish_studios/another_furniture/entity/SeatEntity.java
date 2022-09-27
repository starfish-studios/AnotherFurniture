package com.starfish_studios.another_furniture.entity;

import com.starfish_studios.another_furniture.block.SeatBlock;
import com.starfish_studios.another_furniture.registry.AFEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class SeatEntity extends Entity {
    public SeatEntity(Level level) {
        super(AFEntityTypes.SEAT.get(), level);
        this.noPhysics = true;
    }

    public SeatEntity(Level level, BlockPos source, double yOffset) {
        this(level);
        this.setPos(source.getX() + 0.5, source.getY() + yOffset, source.getZ() + 0.5);
    }

    @Override
    public void tick() {
        super.tick();
        if(!this.level.isClientSide) {
            boolean remove = false;
            BlockState state = this.level.getBlockState(this.blockPosition());
            if (state.getBlock() instanceof SeatBlock seatBlock) {
                remove = !seatBlock.isSittable(state);
            }
            if(this.getPassengers().isEmpty() || !(state.getBlock() instanceof SeatBlock) || remove) {
                this.remove(RemovalReason.DISCARDED);
                this.level.updateNeighbourForOutputSignal(blockPosition(), this.level.getBlockState(blockPosition()).getBlock());
            }
        }
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {}

    @Override
    public double getPassengersRidingOffset()
    {
        return 0.0;
    }

    @Override
    protected boolean canRide(Entity entity)
    {
        return true;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity entity) {
        BlockPos pos = this.blockPosition();
        Vec3 safeVec;
        BlockState state = this.level.getBlockState(pos);
        if (state.getBlock() instanceof SeatBlock seatBlock) {
            //pos = pos.offset(seatBlock.dismountLocationOffset());
            safeVec = DismountHelper.findSafeDismountLocation(entity.getType(), this.level, seatBlock.primaryDismountLocation(this.level, state, pos), false);
            if (safeVec != null) {
                return safeVec.add(0, 0.25, 0);
            }
        }

        Direction original = this.getDirection();
        Direction[] offsets = {original, original.getClockWise(), original.getCounterClockWise(), original.getOpposite()};
        for(Direction dir : offsets) {
            safeVec = DismountHelper.findSafeDismountLocation(entity.getType(), this.level, pos.relative(dir), false);
            if (safeVec != null) {
                return safeVec.add(0, 0.25, 0);
            }
        }
        return super.getDismountLocationForPassenger(entity);
    }

    @Override
    protected void addPassenger(Entity passenger) {
        BlockPos pos = this.blockPosition();
        BlockState state = this.level.getBlockState(pos);
        if (state.getBlock() instanceof SeatBlock seatBlock) {
            passenger.setYRot(seatBlock.setRiderRotation(state, passenger));
        }
        super.addPassenger(passenger);
    }
}