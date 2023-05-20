package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.properties.HorizontalConnectionType;
import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.entity.SeatEntity;
import com.starfish_studios.another_furniture.registry.AFSoundEvents;
import com.starfish_studios.another_furniture.registry.AFBlockTags;
import com.starfish_studios.another_furniture.util.block.HammerableBlock;
import com.starfish_studios.another_furniture.util.block.ShapeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ChairBlock extends SeatBlock implements SimpleWaterloggedBlock, HammerableBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty TUCKED = BooleanProperty.create("tucked");
    public static final IntegerProperty CHAIR_BACK = ModBlockStateProperties.CHAIR_BACK;

    protected static final VoxelShape SHAPE_NORTH = Shapes.or(Block.box(2.0D, 0.0D, 2.0D, 14.0D, 7.0D, 14.0D), Block.box(2.0D, 7.0D, 12.0D, 14.0D, 16.0D, 14.0D));
    protected static final VoxelShape SHAPE_EAST = ShapeUtil.rotateShape(SHAPE_NORTH, Direction.EAST);
    protected static final VoxelShape SHAPE_SOUTH = ShapeUtil.rotateShape(SHAPE_NORTH, Direction.SOUTH);
    protected static final VoxelShape SHAPE_WEST = ShapeUtil.rotateShape(SHAPE_NORTH, Direction.WEST);

    protected static final VoxelShape SHAPE_NORTH_TUCKED = Shapes.or(Block.box(2.0D, 7.0D, 3.0D, 14.0D, 16.0D, 5.0D), Block.box(2.0D, 0.0D, 0.0D, 14.0D, 7.0D, 5.0D));
    protected static final VoxelShape SHAPE_EAST_TUCKED = ShapeUtil.rotateShape(SHAPE_NORTH_TUCKED, Direction.EAST);
    protected static final VoxelShape SHAPE_SOUTH_TUCKED = ShapeUtil.rotateShape(SHAPE_NORTH_TUCKED, Direction.SOUTH);
    protected static final VoxelShape SHAPE_WEST_TUCKED = ShapeUtil.rotateShape(SHAPE_NORTH_TUCKED, Direction.WEST);
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{
            SHAPE_SOUTH, SHAPE_WEST, SHAPE_NORTH, SHAPE_EAST,
            SHAPE_SOUTH_TUCKED, SHAPE_WEST_TUCKED, SHAPE_NORTH_TUCKED, SHAPE_EAST_TUCKED
    };

    public ChairBlock(int defaultBackVariant, Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(TUCKED, false)
                .setValue(CHAIR_BACK, defaultBackVariant));
    }

    @Override
    public float seatHeight() {
        return 0.2F;
    }

    @Override
    public boolean isSittable(BlockState state) {
        return !state.getValue(TUCKED);
    }

    @Override
    public BlockPos primaryDismountLocation(Level level, BlockState state, BlockPos pos) {
        return pos.relative(state.getValue(FACING));
    }

    @Override
    public float setRiderRotation(BlockState state, Entity entity) {
        return state.getValue(FACING).toYRot();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int shape = state.getValue(FACING).get2DDataValue();
        if (state.getValue(TUCKED)) shape += 4;
        return SHAPES[shape];
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        if (state.getValue(TUCKED) && level instanceof Level level1) {
            if (!canTuckUnderFacing(state, level1, currentPos)) {
                return state.setValue(TUCKED, false);
            }
        }
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        boolean tucked = state.getValue(TUCKED);
        if (player.isCrouching() && canTuckUnderFacing(state, level, pos)) {
            if (tucked) {
                level.setBlockAndUpdate(pos, state.setValue(TUCKED, false));
                level.playSound(null, pos, AFSoundEvents.CHAIR_UNTUCK.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            } else if (isChairBlocking(state, level, pos)) {
                level.setBlockAndUpdate(pos, state.setValue(TUCKED, true));
                level.playSound(null, pos, AFSoundEvents.CHAIR_TUCK.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }

        }
        if (tryHammerBlock(CHAIR_BACK, state, level, pos, player, hand)) return InteractionResult.SUCCESS;
        else if (hand == InteractionHand.MAIN_HAND) return InteractionResult.FAIL;
        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return state.getValue(TUCKED) ? PushReaction.BLOCK : super.getPistonPushReaction(state);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TUCKED, CHAIR_BACK, WATERLOGGED);
    }

    public boolean canTuckUnderFacing(BlockState state, Level level, BlockPos pos) {
        Direction dir = state.getValue(FACING);
        BlockState facing_state = level.getBlockState(pos.relative(dir));
        Block facing_state_block = facing_state.getBlock();

        if (facing_state_block instanceof ShelfBlock) {
            return facing_state.getValue(ShelfBlock.TYPE) == HorizontalConnectionType.MIDDLE || facing_state.getValue(FACING) != dir;
        } else if (facing_state_block instanceof SlabBlock) {
            return facing_state.getValue(SlabBlock.TYPE) == SlabType.TOP;
        } else if (facing_state_block instanceof StairBlock) {
            StairsShape shape = facing_state.getValue(StairBlock.SHAPE);
            Direction facing_dir = facing_state.getValue(FACING);
            return facing_state.getValue(StairBlock.HALF) == Half.TOP && (dir == facing_dir ||
                    shape == StairsShape.OUTER_LEFT && dir == facing_dir.getCounterClockWise() ||
                    shape == StairsShape.OUTER_RIGHT && dir == facing_dir.getClockWise());
        }
        return facing_state.is(AFBlockTags.CHAIRS_TUCKABLE_UNDER);
    }

    public boolean isChairBlocking(BlockState state, Level level, BlockPos pos) {
        if (!level.getEntitiesOfClass(SeatEntity.class, new AABB(pos)).isEmpty()) {
            return false;
        }
        Direction facing = state.getValue(FACING);
        BlockState left = level.getBlockState(pos.relative(facing).relative(facing.getCounterClockWise()));
        BlockState right = level.getBlockState(pos.relative(facing).relative(facing.getClockWise()));
        if (left.getBlock() instanceof ChairBlock) {
            if (left.getValue(TUCKED) && left.getValue(FACING) == facing.getClockWise()) return false;
        }
        if (right.getBlock() instanceof ChairBlock) {
            if (right.getValue(TUCKED) && right.getValue(FACING) == facing.getCounterClockWise()) return false;
        }
        return true;
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
