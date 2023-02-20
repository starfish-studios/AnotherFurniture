package com.starfish_studios.another_furniture.integration.forge;

import com.simibubi.create.content.contraptions.components.structureMovement.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.components.structureMovement.MovementBehaviour;
import com.simibubi.create.content.contraptions.components.structureMovement.MovementContext;
import com.starfish_studios.another_furniture.block.SeatBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.AttachedStemBlock;
import net.minecraft.world.level.block.MelonBlock;
import net.minecraft.world.level.block.StemBlock;

import java.util.Map;
import java.util.UUID;

public class AFSeatMovementBehaviour implements MovementBehaviour {
    AttachedStemBlock
    @Override
    public void startMoving(MovementContext context) {
        MovementBehaviour.super.startMoving(context);

        BlockPos local = context.localPos;
        if (context.state.getBlock() instanceof SeatBlock seatBlock) {
            context.contraption.getSeats().add(local.offset(0, ()seatBlock.seatHeight(), 0));
        }

        //BlockPos worldPos = local.offset(context.contraption.anchor);
//        List<SeatEntity> seatsEntities = context.world.getEntitiesOfClass(SeatEntity.class, new AABB(worldPos, worldPos.offset(1, 1, 1)));
//        if (!seatsEntities.isEmpty()) {
//            System.out.println(seatsEntities.toString());
//
//            SeatEntity seat = seatsEntities.get(0);
//            List<Entity> passengers = seat.getPassengers();
//            if (!passengers.isEmpty()) {
//                Entity passenger = passengers.get(0);
//                if (context.contraption.entity != null) {
//
//
//                    int size = context.contraption.getSeatMapping().size() + 1;
//                    context.contraption.getSeatMapping().put(passenger.getUUID(), size);
//                    System.out.println(context.contraption.getSeatMapping());
//                    context.contraption.entity.addSittingPassenger(seat, size);
//                }
//
//            }
//        }

        int indexOf = context.contraption.getSeats().indexOf(context.localPos);
        context.data.putInt("SeatIndex", indexOf);
        System.out.println(indexOf);
        System.out.println(context.contraption.getSeats().toString());

    }

    @Override
    public void visitNewPosition(MovementContext context, BlockPos pos) {
        MovementBehaviour.super.visitNewPosition(context, pos);
        System.out.println(context.contraption.getSeatMapping());
        //System.out.println(context.contraption.getSeatMapping().toString());
        AbstractContraptionEntity contraptionEntity = context.contraption.entity;
        if (contraptionEntity == null)
            return;
        int index = context.data.getInt("SeatIndex");
        if (index == -1)
            return;

        Map<UUID, Integer> seatMapping = context.contraption.getSeatMapping();
//        BlockState blockState = context.world.getBlockState(pos);
//        boolean slab =
//                blockState.getBlock() instanceof SlabBlock && blockState.getValue(SlabBlock.TYPE) == SlabType.BOTTOM;
//        boolean solid = blockState.canOcclude() || slab;

        // Occupied
        if (!seatMapping.containsValue(index))
            return;
//        if (!solid)
//            return;
        Entity toDismount = null;
        for (Map.Entry<UUID, Integer> entry : seatMapping.entrySet()) {
            if (entry.getValue() != index)
                continue;
            for (Entity entity : contraptionEntity.getPassengers()) {
                if (!entry.getKey()
                        .equals(entity.getUUID()))
                    continue;
                toDismount = entity;
                toDismount.teleportTo(0, 9, 0);
            }
        }
        if (toDismount == null)
            return;
        //toDismount.stopRiding();
        //Vec3 position = VecHelper.getCenterOf(pos).add(0, 1f, 0);
        //toDismount.teleportTo(position.x, position.y, position.z);
        //toDismount.getPersistentData().remove("ContraptionDismountLocation");
    }

}