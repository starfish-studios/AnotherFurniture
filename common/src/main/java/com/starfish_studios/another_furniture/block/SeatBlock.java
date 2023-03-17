package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.entity.SeatEntity;
import com.starfish_studios.another_furniture.registry.AFBlockTags;
import com.starfish_studios.another_furniture.registry.AFRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

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
        if (AFRegistry.isFakePlayer(player)) return InteractionResult.PASS;
        if (!level.mayInteract(player, pos)) return InteractionResult.PASS;

        if (!isSittable(state) || player.isPassenger() || player.isCrouching())
            return InteractionResult.PASS;

        if (!level.getBlockState(pos.above()).getCollisionShape(level, pos).isEmpty() && !level.getBlockState(pos.above()).is(AFBlockTags.NO_SEAT_COLLISION_CHECK))
            return InteractionResult.PASS;

        List<SeatEntity> seats = level.getEntitiesOfClass(SeatEntity.class, new AABB(pos, pos.offset(1, 1, 1)));
        if (!seats.isEmpty()) return InteractionResult.PASS;

        SeatEntity seat = new SeatEntity(level, pos, this.seatHeight());
        level.addFreshEntity(seat);
        player.startRiding(seat);
        return InteractionResult.CONSUME;
    }
}
