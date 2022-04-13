package com.crispytwig.another_furniture.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StoolBlock extends SeatBlock implements SimpleWaterloggedBlock {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 3.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape LEG1 = Block.box(14.0D, 0.0D, 14.0D, 16.0D, 3.0D, 16.0D);
    protected static final VoxelShape LEG2 = Block.box(0.0D, 0.0D, 14.0D, 2.0D, 3.0D, 16.0D);
    protected static final VoxelShape LEG3 = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 3.0D, 2.0D);
    protected static final VoxelShape LEG4 = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 3.0D, 2.0D);

    protected static final VoxelShape SHAPES = Shapes.or(SHAPE, LEG1, LEG2, LEG3, LEG4);

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public StoolBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }

    public float seatHeight() {
        return 0.2F;
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext ctx) {
        return SHAPES;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        boolean waterlogged = ctx.getLevel().getFluidState(ctx.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState().setValue(WATERLOGGED, waterlogged);
    }

    public FluidState getFluidState(BlockState p_52362_) {
        return p_52362_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_52362_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    public void fallOn(Level p_152169_, BlockState p_152170_, BlockPos p_152171_, Entity p_152172_, float p_152173_) {
        super.fallOn(p_152169_, p_152170_, p_152171_, p_152172_, p_152173_ * 0.5F);
    }

    public void updateEntityAfterFallOn(BlockGetter p_49483_, Entity p_49484_) {
        if (p_49484_.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(p_49483_, p_49484_);
        } else {
            this.bounceUp(p_49484_);
        }

    }

    private void bounceUp(Entity p_49457_) {
        Vec3 vec3 = p_49457_.getDeltaMovement();
        if (vec3.y < 0.0D) {
            double d0 = p_49457_ instanceof LivingEntity ? 1.0D : 0.8D;
            p_49457_.setDeltaMovement(vec3.x, -vec3.y * (double)0.66F * d0, vec3.z);
        }

    }
}
