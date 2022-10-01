package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.entity.SeatEntity;
import com.starfish_studios.another_furniture.registry.AFBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.CollisionGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SeatBlock extends Block {

    public SeatBlock(Properties properties) {
        super(properties);
    }

    public float seatHeight() {
        return 0.25F;
    }

    public boolean isSittable(BlockState state) {
        return true;
    }

    public Vec3i dismountLocationOffset() {
        return new Vec3i(0, seatHeight(), 0);
    }

    public BlockPos primaryDismountLocation(Level level, BlockState state, BlockPos pos) {
        return pos;
    }

    public float setRiderRotation(BlockState state, Entity entity) {
        return entity.getYRot();
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(!isSittable(state) || player.isPassenger() || player.isCrouching())
            return InteractionResult.PASS;

        if(!level.getBlockState(pos.above()).getCollisionShape(level, pos).isEmpty() && !level.getBlockState(pos.above()).is(AFBlockTags.NO_SEAT_COLLISION_CHECK))
            return InteractionResult.PASS;

//        Vec3 vec = new Vec3(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
//        double maxDist = 3;
//        if((vec.x - player.getX()) * (vec.x - player.getX()) + (vec.y - player.getY()) * (vec.y - player.getY()) + (vec.z - player.getZ()) * (vec.z - player.getZ()) > maxDist * maxDist)
//            return InteractionResult.PASS;

//        ItemStack stack1 = pPlayer.getMainHandItem();
//        ItemStack stack2 = pPlayer.getOffhandItem();
//        if(!stack1.isEmpty() || !stack2.isEmpty())
//            return InteractionResult.PASS;

        List<SeatEntity> seats = level.getEntitiesOfClass(SeatEntity.class, new AABB(pos, pos.offset(1, 1, 1)));
        if(seats.isEmpty()) {
            SeatEntity seat = new SeatEntity(level, pos, this.seatHeight());
            level.addFreshEntity(seat);
            player.startRiding(seat);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }
}
