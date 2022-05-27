package com.crispytwig.another_furniture.block;

import com.crispytwig.another_furniture.entity.SeatEntity;
import com.crispytwig.another_furniture.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SeatBlock extends BaseBlock {

    public SeatBlock(Properties properties) {
        super(properties);
    }

    public float seatHeight() {
        return 0.25F;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pPlayer.isPassenger() || pPlayer.isCrouching())
            return InteractionResult.PASS;

        if(!pLevel.getBlockState(pPos.above()).getCollisionShape(pLevel, pPos).isEmpty() && !pLevel.getBlockState(pPos.above()).is(ModTags.NO_SEAT_COLLISION_CHECK))
            return InteractionResult.PASS;

        Vec3 vec = new Vec3(pPos.getX() + 0.5, pPos.getY(), pPos.getZ() + 0.5);
        double maxDist = 3;
        if((vec.x - pPlayer.getX()) * (vec.x - pPlayer.getX()) + (vec.y - pPlayer.getY()) * (vec.y - pPlayer.getY()) + (vec.z - pPlayer.getZ()) * (vec.z - pPlayer.getZ()) > maxDist * maxDist)
            return InteractionResult.PASS;

        ItemStack stack1 = pPlayer.getMainHandItem();
        ItemStack stack2 = pPlayer.getOffhandItem();
        if(!stack1.isEmpty() || !stack2.isEmpty())
            return InteractionResult.PASS;

        List<SeatEntity> seats = pLevel.getEntitiesOfClass(SeatEntity.class, new AABB(pPos, pPos.offset(1, 1, 1)));
        if(seats.isEmpty()) {
            SeatEntity seat = new SeatEntity(pLevel, pPos, this.seatHeight());
            pLevel.addFreshEntity(seat);
            pPlayer.startRiding(seat);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
