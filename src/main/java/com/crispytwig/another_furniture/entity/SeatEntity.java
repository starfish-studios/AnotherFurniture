package com.crispytwig.another_furniture.entity;

import com.crispytwig.another_furniture.init.ModEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;

public class SeatEntity extends Entity
{

    public SeatEntity(Level level)
    {
        super(ModEntity.SEAT.get(), level);
        this.noPhysics = true;
    }

    public SeatEntity(Level level, BlockPos source, double yOffset, float startFacing)
    {
        this(level);
        this.setPos(source.getX() + 0.5, source.getY() + yOffset, source.getZ() + 0.5);
        this.setRot(startFacing, 0F);
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

    //public static InteractionResult create(Level level, BlockPos pos, double yOffset, Player player, Direction direction)
    //{
    //    if(!level.isClientSide())
    //    {
    //        List<SeatEntity> seats = level.getEntitiesOfClass(SeatEntity.class, new AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 1.0));
    //        if(seats.isEmpty())
    //        {
    //            SeatEntity seat = new SeatEntity(level, pos, yOffset, direction);
    //            level.addFreshEntity(seat);
    //            player.startRiding(seat, false);
    //        }
    //    }
    //    return InteractionResult.SUCCESS;
    //}

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

    @Override
    protected void addPassenger(Entity entity)
    {
        super.addPassenger(entity);
        entity.setYRot(this.getYRot());
    }

    //@Override
    //public void positionRider(Entity entity)
    //{
    //    super.positionRider(entity);
    //    this.clampYaw(entity);
    //}

    //@Override
    //public void onPassengerTurned(Entity entity)
    //{
    //    this.clampYaw(entity);
    //}

    //private void clampYaw(Entity passenger)
    //{
    //    passenger.setYBodyRot(this.getYRot());
    //    float wrappedYaw = Mth.wrapDegrees(passenger.getYRot() - this.getYRot());
    //    float clampedYaw = Mth.clamp(wrappedYaw, -100, 100);
    //    passenger.yRotO += clampedYaw - wrappedYaw;
    //    passenger.setYRot(passenger.getYRot() + clampedYaw - wrappedYaw);
    //    passenger.setYHeadRot(passenger.getYRot());
    //}
}