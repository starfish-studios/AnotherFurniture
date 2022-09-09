package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.block.properties.SofaType;
import com.starfish_studios.another_furniture.util.block.ShapeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
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

    public static final VoxelShape SEAT_BASE = Block.box(0, 3, 0, 16, 7, 16);
    public static final VoxelShape SEAT_BACK = Block.box(0, 7, 12, 16, 16, 16);

    public static final VoxelShape LEG_BL = Block.box(14.0D, 0.0D, 14.0D, 16.0D, 3.0D, 16.0D);
    public static final VoxelShape LEG_BR = Block.box(0.0D, 0.0D, 14.0D, 2.0D, 3.0D, 16.0D);
    public static final VoxelShape LEG_FR = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 3.0D, 2.0D);
    public static final VoxelShape LEG_FL = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 3.0D, 2.0D);

    public static final VoxelShape ARM_L = Block.box(14, 7, 0, 16, 12, 12);
    public static final VoxelShape ARM_R = Block.box(0, 7, 0, 2, 12, 12);

    public static final VoxelShape SINGLE = Shapes.or(SEAT_BASE, SEAT_BACK, ARM_L, ARM_R, LEG_BL, LEG_BR, LEG_FL, LEG_FR);
    public static final VoxelShape SINGLE_EAST = ShapeUtil.rotateShape(SINGLE, Direction.EAST);
    public static final VoxelShape SINGLE_SOUTH = ShapeUtil.rotateShape(SINGLE, Direction.SOUTH);
    public static final VoxelShape SINGLE_WEST = ShapeUtil.rotateShape(SINGLE, Direction.WEST);

    public static final VoxelShape MIDDLE = Shapes.or(SEAT_BASE, SEAT_BACK);
    public static final VoxelShape MIDDLE_EAST = ShapeUtil.rotateShape(MIDDLE, Direction.EAST);
    public static final VoxelShape MIDDLE_SOUTH = ShapeUtil.rotateShape(MIDDLE, Direction.SOUTH);
    public static final VoxelShape MIDDLE_WEST = ShapeUtil.rotateShape(MIDDLE, Direction.WEST);

    public static final VoxelShape OUTER = Shapes.or(SEAT_BASE, Block.box(12, 6, 12, 16, 16, 16));
    public static final VoxelShape OUTER_EAST = ShapeUtil.rotateShape(OUTER, Direction.EAST);
    public static final VoxelShape OUTER_SOUTH = ShapeUtil.rotateShape(OUTER, Direction.SOUTH);
    public static final VoxelShape OUTER_WEST = ShapeUtil.rotateShape(OUTER, Direction.WEST);

    public static final VoxelShape INNER = Shapes.or(SEAT_BASE, SEAT_BACK, Block.box(12, 7, 0, 16, 16, 12));
    public static final VoxelShape INNER_EAST = ShapeUtil.rotateShape(INNER, Direction.EAST);
    public static final VoxelShape INNER_SOUTH = ShapeUtil.rotateShape(INNER, Direction.SOUTH);
    public static final VoxelShape INNER_WEST = ShapeUtil.rotateShape(INNER, Direction.WEST);

    public static final VoxelShape RIGHT = Shapes.or(SEAT_BASE, SEAT_BACK, ARM_R, LEG_BR, LEG_FR);
    public static final VoxelShape RIGHT_EAST = ShapeUtil.rotateShape(RIGHT, Direction.EAST);
    public static final VoxelShape RIGHT_SOUTH = ShapeUtil.rotateShape(RIGHT, Direction.SOUTH);
    public static final VoxelShape RIGHT_WEST = ShapeUtil.rotateShape(RIGHT, Direction.WEST);

    public static final VoxelShape LEFT = Shapes.or(SEAT_BASE, SEAT_BACK, ARM_L, LEG_BL, LEG_FL);
    public static final VoxelShape LEFT_EAST = ShapeUtil.rotateShape(LEFT, Direction.EAST);
    public static final VoxelShape LEFT_SOUTH = ShapeUtil.rotateShape(LEFT, Direction.SOUTH);
    public static final VoxelShape LEFT_WEST = ShapeUtil.rotateShape(LEFT, Direction.WEST);


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
        SofaType shape = state.getValue(TYPE);
        return switch (shape) {
            case SINGLE -> switch (facing) {
                case EAST -> SINGLE_EAST;
                case SOUTH -> SINGLE_SOUTH;
                case WEST -> SINGLE_WEST;
                default -> SINGLE;
            };
            case MIDDLE -> switch (facing) {
                case EAST -> MIDDLE_EAST;
                case SOUTH -> MIDDLE_SOUTH;
                case WEST -> MIDDLE_WEST;
                default -> MIDDLE;
            };
            case OUTER_LEFT -> switch (facing) {
                case EAST -> OUTER_EAST;
                case SOUTH -> OUTER_SOUTH;
                case WEST -> OUTER_WEST;
                default -> OUTER;
            };
            case OUTER_RIGHT -> switch (facing) {
                case EAST -> OUTER_SOUTH;
                case SOUTH -> OUTER_WEST;
                case WEST -> OUTER;
                default -> OUTER_EAST;
            };
            case LEFT -> switch (facing) {
                case EAST -> LEFT_EAST;
                case SOUTH -> LEFT_SOUTH;
                case WEST -> LEFT_WEST;
                default -> LEFT;
            };
            case RIGHT -> switch (facing) {
                case EAST -> RIGHT_EAST;
                case SOUTH -> RIGHT_SOUTH;
                case WEST -> RIGHT_WEST;
                default -> RIGHT;
            };
            case INNER_RIGHT -> switch (facing) {
                case EAST -> INNER_SOUTH;
                case SOUTH -> INNER_WEST;
                case WEST -> INNER;
                default -> INNER_EAST;
            };
            case INNER_LEFT -> switch (facing) {
                case EAST -> INNER_EAST;
                case SOUTH -> INNER_SOUTH;
                case WEST -> INNER_WEST;
                default -> INNER;
            };
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
