package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.properties.HorizontalConnectionType;
import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.util.block.HammerableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BenchBlock extends SeatBlock implements SimpleWaterloggedBlock, HammerableBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<HorizontalConnectionType> TYPE = ModBlockStateProperties.HORIZONTAL_CONNECTION_TYPE;
    public static final BooleanProperty HAMMERABLE_ATTACHMENT = ModBlockStateProperties.HAMMERABLE_ATTACHMENT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape AABB_Z = Block.box(0, 0, 2, 16, 7, 14);
    protected static final VoxelShape AABB_X = Block.box(2, 0, 0, 14, 7, 16);

    protected static final VoxelShape AABB_EAST = Shapes.or(AABB_X, Block.box(2.0D, 7.0D, 0.0D, 4.0D, 16.0D, 16.0D));
    protected static final VoxelShape AABB_WEST = Shapes.or(AABB_X, Block.box(12.0D, 7.0D, 0.0D, 14.0D, 16.0D, 16.0D));
    protected static final VoxelShape AABB_SOUTH = Shapes.or(AABB_Z, Block.box(0.0D, 7.0D, 2.0D, 16.0D, 16.0D, 4.0D));
    protected static final VoxelShape AABB_NORTH = Shapes.or(AABB_Z, Block.box(0.0D, 7.0D, 12.0D, 16.0D, 16.0D, 14.0D));

    @Override
    public float seatHeight() {
        return 0.2F;
    }

    public BenchBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(TYPE, HorizontalConnectionType.SINGLE)
                .setValue(HAMMERABLE_ATTACHMENT, true)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        if (state.getValue(HAMMERABLE_ATTACHMENT)) {
            return switch (facing) {
                case EAST -> AABB_EAST;
                case SOUTH -> AABB_SOUTH;
                case WEST -> AABB_WEST;
                default -> AABB_NORTH;
            };
        }
        return facing.getAxis() == Direction.Axis.Z ? AABB_Z : AABB_X;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        boolean waterlogged = pContext.getLevel().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState()
                .setValue(FACING, pContext.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (tryHammerBlock(pPlayer, pLevel, pPos, pState)) {
            return InteractionResult.SUCCESS;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        Direction facing = pState.getValue(FACING);
        BlockState l_state = pLevel.getBlockState(pCurrentPos.relative(facing.getClockWise()));
        BlockState r_state = pLevel.getBlockState(pCurrentPos.relative(facing.getCounterClockWise()));
        boolean l_side = (l_state.getBlock() instanceof BenchBlock && l_state.getValue(FACING) == facing);
        boolean r_side = (r_state.getBlock() instanceof BenchBlock && r_state.getValue(FACING) == facing);
        HorizontalConnectionType type = l_side && r_side ? HorizontalConnectionType.MIDDLE : (r_side ? HorizontalConnectionType.LEFT : (l_side ? HorizontalConnectionType.RIGHT : HorizontalConnectionType.SINGLE));
        return pState.setValue(TYPE, type);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, TYPE, HAMMERABLE_ATTACHMENT, WATERLOGGED);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }
}

