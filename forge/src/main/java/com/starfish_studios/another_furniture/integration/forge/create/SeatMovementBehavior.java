package com.starfish_studios.another_furniture.integration.forge.create;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.starfish_studios.another_furniture.block.SeatBlock;
import com.starfish_studios.another_furniture.entity.SeatEntity;
import com.starfish_studios.another_furniture.mixin.forge.create.ContraptionMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import com.simibubi.create.foundation.utility.VecHelper;

public class SeatMovementBehavior implements MovementBehaviour {


    @Override
    public void startMoving(MovementContext context) {
        //System.out.println("started moving from".concat(context.localPos.toString()));
        MovementBehaviour.super.startMoving(context);

        int indexOf = context.contraption.getSeats()
                .indexOf(context.localPos);
        context.data.putInt("SeatIndex", indexOf);
        moveSeat(context.contraption, context.world, context.localPos);
        System.out.println(context.data.getInt("SeatIndex"));
    }

    @Override
    public void visitNewPosition(MovementContext context, BlockPos pos) {
        //System.out.println("visitNewPosition");
        MovementBehaviour.super.visitNewPosition(context, pos);
        AbstractContraptionEntity contraptionEntity = context.contraption.entity;
        if (contraptionEntity == null) return;
        int index = context.data.getInt("SeatIndex");
        if (index == -1) return;
        //System.out.println(1);
        Map<UUID, Integer> seatMapping = context.contraption.getSeatMapping();
        BlockState blockState = context.world.getBlockState(pos);

        SeatBlock seatBlock = (SeatBlock)blockState.getBlock();
        //System.out.println(seatBlock.seatHeight());

        // Occupied
        if (!seatMapping.containsValue(index))
            return;

        Entity toDismount = null;
        for (Map.Entry<UUID, Integer> entry : seatMapping.entrySet()) {
            if (entry.getValue() != index)
                continue;
            for (Entity entity : contraptionEntity.getPassengers()) {
                if (!entry.getKey()
                        .equals(entity.getUUID()))
                    continue;
                toDismount = entity;
            }
        }
        if (toDismount == null)
            return;
        toDismount.stopRiding();
        Vec3 position = VecHelper.getCenterOf(pos)
                .add(0, 1, 0);
        toDismount.teleportTo(position.x, position.y, position.z);
        //toDismount.getExtraCustomData().method_10551("ContraptionDismountLocation");
    }

    private void moveSeat(Contraption contraption, Level world, BlockPos pos) {
        System.out.println("move");
        BlockPos globalPos = pos.offset(contraption.anchor);
        BlockPos local = pos;
        contraption.getSeats().add(local);
        List<SeatEntity> seatsEntities = world.getEntitiesOfClass(SeatEntity.class, new AABB(globalPos));
        //world.setBlockAndUpdate(globalPos.above(3), Blocks.GLASS.defaultBlockState());
        if (!seatsEntities.isEmpty()) {
            //System.out.println("notempty");

            SeatEntity seat = seatsEntities.get(0);
            List<Entity> passengers = seat.getPassengers();
            if (!passengers.isEmpty()) {
                Map<BlockPos, Entity> initialPassengers = ((ContraptionMixin)contraption).getInitialPassengers();
                initialPassengers.put(local, passengers.get(0));
                ((ContraptionMixin)contraption).setInitialPassengers(initialPassengers);
                //System.out.println(21231231);
            }
        }

    }

}