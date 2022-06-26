package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.properties.HorizontalConnectionType;
import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BenchBlock extends SeatBlock implements SimpleWaterloggedBlock {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    public static final EnumProperty<HorizontalConnectionType> TYPE = ModBlockStateProperties.HORIZONTAL_CONNECTION_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape AABB_Z = Block.box(0, 0, 2, 16, 7, 14);
    protected static final VoxelShape AABB_X = Block.box(2, 0, 0, 14, 7, 16);

    @Override
    public float seatHeight() {
        return 0.2F;
    }

    public BenchBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(AXIS, Direction.Axis.Z)
                .setValue(TYPE, HorizontalConnectionType.SINGLE)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(AXIS) == Direction.Axis.Z ? AABB_Z : AABB_X;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        boolean waterlogged = pContext.getLevel().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState()
                .setValue(AXIS, pContext.getHorizontalDirection().getAxis())
                .setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        Direction.Axis axis = pState.getValue(AXIS);
        Direction facing = axis == Direction.Axis.Z ? Direction.NORTH : Direction.EAST;
        BlockState l_state = pLevel.getBlockState(pCurrentPos.relative(facing.getClockWise()));
        BlockState r_state = pLevel.getBlockState(pCurrentPos.relative(facing.getCounterClockWise()));
        boolean l_side = (l_state.getBlock() instanceof BenchBlock && l_state.getValue(AXIS) == axis);
        boolean r_side = (r_state.getBlock() instanceof BenchBlock && r_state.getValue(AXIS) == axis);
        HorizontalConnectionType type = l_side && r_side ? HorizontalConnectionType.MIDDLE : (r_side ? HorizontalConnectionType.LEFT : (l_side ? HorizontalConnectionType.RIGHT : HorizontalConnectionType.SINGLE));
        return pState.setValue(TYPE, type);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        if (rotation == Rotation.CLOCKWISE_90 || rotation == Rotation.COUNTERCLOCKWISE_90) {
            if (state.getValue(AXIS) == Direction.Axis.Z) return state.setValue(AXIS, Direction.Axis.X);
            else return state.setValue(AXIS, Direction.Axis.Z);
        }
        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AXIS, TYPE, WATERLOGGED);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }
}

