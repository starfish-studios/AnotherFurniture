package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.properties.HorizontalConnectionType;
import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.block.properties.SofaType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SofaBlock extends SeatBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<SofaType> TYPE = ModBlockStateProperties.SOFA_TYPE;

    protected static final VoxelShape BOTTOM_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

    protected static final VoxelShape AABB_EAST = Shapes.or(BOTTOM_AABB, Block.box(0.0D, 8.0D, 0.0D, 5.0D, 16.0D, 16.0D));
    protected static final VoxelShape AABB_WEST = Shapes.or(BOTTOM_AABB, Block.box(11.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D));
    protected static final VoxelShape AABB_SOUTH = Shapes.or(BOTTOM_AABB, Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 5.0D));
    protected static final VoxelShape AABB_NORTH = Shapes.or(BOTTOM_AABB, Block.box(0.0D, 8.0D, 11.0D, 16.0D, 16.0D, 16.0D));

    protected static final VoxelShape BOTTOM_AABB_RENDER = Block.box(0.0D, 3.0D, 0.0D, 16.0D, 8.0D, 16.0D);

    protected static final VoxelShape AABB_EAST_RENDER = Shapes.or(BOTTOM_AABB_RENDER, Block.box(0.0D, 8.0D, 0.0D, 5.0D, 16.0D, 16.0D));
    protected static final VoxelShape AABB_WEST_RENDER = Shapes.or(BOTTOM_AABB_RENDER, Block.box(11.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D));
    protected static final VoxelShape AABB_SOUTH_RENDER = Shapes.or(BOTTOM_AABB_RENDER, Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 5.0D));
    protected static final VoxelShape AABB_NORTH_RENDER = Shapes.or(BOTTOM_AABB_RENDER, Block.box(0.0D, 8.0D, 11.0D, 16.0D, 16.0D, 16.0D));

    public SofaBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockPos = context.getClickedPos();
        FluidState fluidState = context.getLevel().getFluidState(blockPos);
        BlockState blockState = this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        return blockState.setValue(TYPE, getConnection(blockState, context.getLevel(), blockPos));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return direction.getAxis().isHorizontal() ? state.setValue(TYPE, getConnection(state, level, currentPos)) : super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        return switch (facing) {
            case EAST -> AABB_EAST;
            case SOUTH -> AABB_SOUTH;
            case WEST -> AABB_WEST;
            default -> AABB_NORTH;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, TYPE, WATERLOGGED);
    }

    private static SofaType getConnection(BlockState state, BlockGetter level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockState blockState = level.getBlockState(pos.relative(direction));
        if (state.is(blockState.getBlock())) {
            Direction direction2 = blockState.getValue(FACING);
            if (direction2.getAxis() != (state.getValue(FACING)).getAxis() && canTakeShape(state, level, pos, direction2.getOpposite())) {
                return direction2 == direction.getCounterClockWise() ? SofaType.INNER_LEFT : SofaType.INNER_RIGHT;
            }
        }

        BlockState blockState2 = level.getBlockState(pos.relative(direction.getOpposite()));
        if (state.is(blockState2.getBlock())) {
            Direction direction3 = blockState2.getValue(FACING);
            if (direction3.getAxis() != (state.getValue(FACING)).getAxis() && canTakeShape(state, level, pos, direction3)) {
                return direction3 == direction.getCounterClockWise() ? SofaType.OUTER_LEFT : SofaType.OUTER_RIGHT;
            }
        }

        BlockState l_state = level.getBlockState(pos.relative(direction.getClockWise()));
        BlockState r_state = level.getBlockState(pos.relative(direction.getCounterClockWise()));
        boolean l_side = (l_state.getBlock() instanceof SofaBlock);
        boolean r_side = (r_state.getBlock() instanceof SofaBlock);
        return l_side && r_side ? SofaType.MIDDLE : (r_side ? SofaType.LEFT : (l_side ? SofaType.RIGHT : SofaType.SINGLE));
    }

    private static boolean canTakeShape(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        BlockState blockState = level.getBlockState(pos.relative(face));
        return !state.is(blockState.getBlock()) || blockState.getValue(FACING) != state.getValue(FACING);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }
}
