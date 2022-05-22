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

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        if(player.isPassenger() || player.isCrouching())
            return InteractionResult.PASS;

        if(!level.getBlockState(pos.above()).getCollisionShape(level, pos).isEmpty() && !level.getBlockState(pos.above()).is(ModTags.BYPASS_SEAT_COLLISION_CHECK))
            return InteractionResult.PASS;

        Vec3 vec = new Vec3(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        double maxDist = 3;
        if((vec.x - player.getX()) * (vec.x - player.getX()) + (vec.y - player.getY()) * (vec.y - player.getY()) + (vec.z - player.getZ()) * (vec.z - player.getZ()) > maxDist * maxDist)
            return InteractionResult.PASS;

        ItemStack stack1 = player.getMainHandItem();
        ItemStack stack2 = player.getOffhandItem();
        if(!stack1.isEmpty() || !stack2.isEmpty())
            return InteractionResult.PASS;

        List<SeatEntity> seats = level.getEntitiesOfClass(SeatEntity.class, new AABB(pos, pos.offset(1, 1, 1)));
        if(seats.isEmpty()) {
            SeatEntity seat = new SeatEntity(level, pos, this.seatHeight());
            level.addFreshEntity(seat);
            player.startRiding(seat);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
