package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.properties.HorizontalConnectionType;
import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.registry.AFSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CurtainBlock extends Block implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<HorizontalConnectionType> TYPE = ModBlockStateProperties.HORIZONTAL_CONNECTION_TYPE;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final DirectionProperty FACING_VERTICAL = ModBlockStateProperties.FACING_VERTICAL;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape EAST = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    protected static final VoxelShape NORTH = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);

    public CurtainBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(TYPE, HorizontalConnectionType.SINGLE)
                .setValue(FACING_VERTICAL, Direction.DOWN)
                .setValue(OPEN, true)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        HorizontalConnectionType type = state.getValue(TYPE);
        if (state.getValue(OPEN) && (type == HorizontalConnectionType.SINGLE || type == HorizontalConnectionType.MIDDLE)
                && state.getValue(FACING_VERTICAL) == Direction.UP) return Shapes.empty();
        return switch (state.getValue(FACING)) {
            default -> NORTH;
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(OPEN) ? Shapes.empty() : super.getCollisionShape(state, level, pos, context);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        if (!level.getBlockState(pos.below()).canBeReplaced(context) || level.isOutsideBuildHeight(pos.below())) return null;

        Direction facing = context.getClickedFace();
        if (facing.getAxis().isVertical()) facing = context.getHorizontalDirection().getOpposite();


        BlockState l_state = level.getBlockState(pos.relative(facing.getClockWise()));
        BlockState r_state = level.getBlockState(pos.relative(facing.getCounterClockWise()));
        boolean l_side = (l_state.getBlock() instanceof CurtainBlock && l_state.getValue(FACING_VERTICAL) == Direction.DOWN && (l_state.getValue(FACING) == facing));
        boolean r_side = (r_state.getBlock() instanceof CurtainBlock && r_state.getValue(FACING_VERTICAL) == Direction.DOWN && (r_state.getValue(FACING) == facing));

        boolean open = l_side && r_side ? l_state.getValue(OPEN) || r_state.getValue(OPEN) : (r_side ? r_state.getValue(OPEN) : (l_side ? l_state.getValue(OPEN) : true));

        return this.defaultBlockState().setValue(FACING, facing).setValue(OPEN, open);
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (level.isClientSide) return;

        BlockPos blockPos = pos.below();
        level.setBlock(blockPos, state.setValue(FACING_VERTICAL, Direction.UP), 3);
        level.blockUpdated(pos, Blocks.AIR);
        state.updateNeighbourShapes(level, pos, 3);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        Direction facing = state.getValue(FACING);
        Direction facing_vertical = state.getValue(FACING_VERTICAL);
        if (direction.getAxis().isVertical()) {
            BlockState stateOpposite = level.getBlockState(currentPos.relative(facing_vertical));
            if (!stateOpposite.is(this) || stateOpposite.getValue(FACING_VERTICAL) == facing_vertical) return Blocks.AIR.defaultBlockState();
        }
        if (direction != facing.getClockWise() && direction != facing.getCounterClockWise()) return state;

        BlockState l_state = level.getBlockState(currentPos.relative(facing.getClockWise()));
        BlockState r_state = level.getBlockState(currentPos.relative(facing.getCounterClockWise()));
        boolean l_side = (l_state.getBlock() instanceof CurtainBlock && l_state.getValue(FACING_VERTICAL) == facing_vertical && (l_state.getValue(FACING) == facing));
        boolean r_side = (r_state.getBlock() instanceof CurtainBlock && r_state.getValue(FACING_VERTICAL) == facing_vertical && (r_state.getValue(FACING) == facing));
        HorizontalConnectionType type = l_side && r_side ? HorizontalConnectionType.MIDDLE : (r_side ? HorizontalConnectionType.LEFT : (l_side ? HorizontalConnectionType.RIGHT : HorizontalConnectionType.SINGLE));

        return state.setValue(TYPE, type);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        boolean open = !state.getValue(OPEN);
        Direction facing = state.getValue(FACING);
        Direction relativeLeft = facing.getClockWise();
        Direction relativeRight = facing.getCounterClockWise();

        toggleVertical(state, level, pos, open);
        toggleHorizontal(state, level, pos, open, facing, relativeLeft);
        toggleHorizontal(state, level, pos, open, facing, relativeRight);

        level.playSound(null, pos, AFSoundEvents.CURTAIN.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    public void toggleHorizontal(BlockState state, Level level, BlockPos pos, boolean open, Direction facing, Direction dir) {
        BlockPos facingHorizontalPos = pos.relative(dir);
        BlockState facingHorizontalState = level.getBlockState(facingHorizontalPos);

        if (facingHorizontalState.is(this) && facingHorizontalState.getValue(FACING) == facing &&
                state.getValue(FACING_VERTICAL) == facingHorizontalState.getValue(FACING_VERTICAL)) {
            toggleVertical(facingHorizontalState, level, facingHorizontalPos, open);
            toggleHorizontal(facingHorizontalState, level, facingHorizontalPos, open, facing, dir);
        }
    }

    public void toggleVertical(BlockState state, Level level, BlockPos pos, boolean open) {
        Direction facingVertical = state.getValue(FACING_VERTICAL);
        BlockPos facingVerticalPos = pos.relative(facingVertical);
        BlockState facingVerticalState = level.getBlockState(facingVerticalPos);
        level.setBlockAndUpdate(pos, state.setValue(OPEN, open));

        if (facingVerticalState.is(this) && facingVertical == facingVerticalState.getValue(FACING_VERTICAL).getOpposite())
            level.setBlockAndUpdate(facingVerticalPos, facingVerticalState.setValue(OPEN, open));
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, OPEN, FACING_VERTICAL, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}
