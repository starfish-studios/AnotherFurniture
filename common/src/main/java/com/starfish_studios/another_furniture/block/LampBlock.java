package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class LampBlock extends Block implements SimpleWaterloggedBlock {
    protected static final int LEVEL_MIN = 1;
    protected static final int LEVEL_MAX = 3;

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final DirectionProperty FACING = ModBlockStateProperties.FACING_EXCEPT_DOWN;
    public static final IntegerProperty LEVEL = ModBlockStateProperties.LEVEL_1_3;

    protected static final VoxelShape AABB_UP = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 13.0D, 12.0D);
    protected static final VoxelShape AABB_NORTH = Block.box(3.0D, 4.0D, 6.0D, 13.0D, 16.0D, 16.0D);
    protected static final VoxelShape AABB_EAST = Block.box(0.0D, 4.0D, 3.0D, 10.0D, 16.0D, 13.0D);
    protected static final VoxelShape AABB_SOUTH = Block.box(3.0D, 4.0D, 0.0D, 13.0D, 16.0D, 10.0D);
    protected static final VoxelShape AABB_WEST = Block.box(6.0D, 4.0D, 3.0D, 16.0D, 16.0D, 13.0D);

    public LampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.UP)
                .setValue(LEVEL, LEVEL_MAX)
                .setValue(LIT, true)
                .setValue(POWERED, false)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> AABB_NORTH;
            case EAST -> AABB_EAST;
            case SOUTH -> AABB_SOUTH;
            case WEST -> AABB_WEST;
            default -> AABB_UP;
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        Direction clickedFace = context.getClickedFace();
        BlockState blockstate = this.defaultBlockState();
        if (context.getLevel().hasNeighborSignal(context.getClickedPos())) {
            blockstate = blockstate.setValue(POWERED, true);
        }
        return clickedFace != Direction.DOWN ? blockstate.setValue(FACING, clickedFace).setValue(WATERLOGGED, waterlogged) : null;
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return !this.canSurvive(state, level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos facingPos = pos.relative(direction.getOpposite());
        BlockState facingState = level.getBlockState(facingPos);
        return facingState.isFaceSturdy(level, facingPos, direction);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            boolean powered = level.hasNeighborSignal(pos);
            if (powered != state.getValue(POWERED)) {
                if (state.getValue(LIT) != powered) {
                    state = state.setValue(LIT, powered);
                }
                state = state.setValue(POWERED, powered);
            }
            level.setBlock(pos, state, 3);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.isCrouching()) {
            int light = state.getValue(LEVEL);
            light = light >= LEVEL_MAX ? LEVEL_MIN : light + 1;
            state = state.setValue(LEVEL, light);
        } else {
            state = state.cycle(LIT);
        }
        level.setBlock(pos, state, 3);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, LIT, POWERED, FACING, LEVEL);
    }
}