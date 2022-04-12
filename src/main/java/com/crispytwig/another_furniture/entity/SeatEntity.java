package com.crispytwig.another_furniture.entity;

import com.crispytwig.another_furniture.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class SeatEntity extends Entity
{


    public SeatEntity(Level level)
    {
        super(ModEntities.SEAT.get(), level);
        this.noPhysics = true;
    }

    public SeatEntity(Level level, BlockPos source, double yOffset)
    {
        this(level);
        this.setPos(source.getX() + 0.5, source.getY() + yOffset, source.getZ() + 0.5);
    }

    @Override
    public void tick()
    {
        super.tick();
        if(!this.level.isClientSide)
        {
            if(this.getPassengers().isEmpty() || this.level.isEmptyBlock(this.blockPosition()))
            {
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
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity entity)
    {
        Direction original = this.getDirection();
        Direction[] offsets = {original, original.getClockWise(), original.getCounterClockWise(), original.getOpposite()};
        for(Direction dir : offsets)
        {
            Vec3 safeVec = DismountHelper.findSafeDismountLocation(entity.getType(), this.level, this.blockPosition().relative(dir), false);
            if(safeVec != null)
            {
                return safeVec.add(0, 0.25, 0);
            }
        }
        return super.getDismountLocationForPassenger(entity);
    }

}