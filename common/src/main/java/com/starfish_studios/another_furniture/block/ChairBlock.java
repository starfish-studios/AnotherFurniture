package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.properties.HorizontalConnectionType;
import com.starfish_studios.another_furniture.entity.SeatEntity;
import com.starfish_studios.another_furniture.registry.AFSoundEvents;
import com.starfish_studios.another_furniture.registry.AFBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
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

public class ChairBlock extends SeatBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty TUCKED = BooleanProperty.create("tucked");
    protected static final VoxelShape SEAT = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 7.0D, 14.0D);
    protected static final VoxelShape SHAPE_NORTH = Shapes.or(SEAT, Block.box(2.0D, 7.0D, 12.0D, 14.0D, 16.0D, 14.0D));
    protected static final VoxelShape SHAPE_EAST = Shapes.or(SEAT, Block.box(2.0D, 7.0D, 2.0D, 4.0D, 16.0D, 14.0D));
    protected static final VoxelShape SHAPE_SOUTH = Shapes.or(SEAT, Block.box(2.0D, 7.0D, 2.0D, 14.0D, 16.0D, 4.0D));
    protected static final VoxelShape SHAPE_WEST = Shapes.or(SEAT, Block.box(12.0D, 7.0D, 2.0D, 14.0D, 16.0D, 14.0D));

    protected static final VoxelShape SHAPE_NORTH_TUCKED = Shapes.or(Block.box(2.0D, 7.0D, 3.0D, 14.0D, 16.0D, 5.0D), Block.box(2.0D, 0.0D, 0.0D, 14.0D, 7.0D, 5.0D));
    protected static final VoxelShape SHAPE_EAST_TUCKED = Shapes.or(Block.box(11.0D, 7.0D, 2.0D, 13.0D, 16.0D, 14.0D), Block.box(11.0D, 0.0D, 2.0D, 16.0D, 7.0D, 14.0D));
    protected static final VoxelShape SHAPE_SOUTH_TUCKED = Shapes.or(Block.box(2.0D, 7.0D, 11.0D, 14.0D, 16.0D, 13.0D), Block.box(2.0D, 0.0D, 11.0D, 14.0D, 7.0D, 16.0D));
    protected static final VoxelShape SHAPE_WEST_TUCKED = Shapes.or(Block.box(3.0D, 7.0D, 2.0D, 5.0D, 16.0D, 14.0D), Block.box(0.0D, 0.0D, 2.0D, 5.0D, 7.0D, 14.0D));
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{
            SHAPE_SOUTH, SHAPE_WEST, SHAPE_NORTH, SHAPE_EAST,
            SHAPE_SOUTH_TUCKED, SHAPE_WEST_TUCKED, SHAPE_NORTH_TUCKED, SHAPE_EAST_TUCKED
    };

    public ChairBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(TUCKED, false));
    }

    @Override
    public float seatHeight() {
        return 0.2F;
    }

    public boolean isSittable(BlockState state) {
        return !state.getValue(TUCKED);
    }
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        int shape = pState.getValue(FACING).get2DDataValue();
        if (pState.getValue(TUCKED)) shape += 4;
        return SHAPES[shape];
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
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }
        if (pState.getValue(TUCKED) && pLevel instanceof Level level) {
            if (!canTuckUnderFacing(pState, level, pCurrentPos)) {
                return pState.setValue(TUCKED, false);
            }
        }
        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        boolean tucked = pState.getValue(TUCKED);
        if ((pPlayer.isCrouching() || tucked) && pPlayer.getMainHandItem().isEmpty() && pPlayer.getOffhandItem().isEmpty() &&
                canTuckUnderFacing(pState, pLevel, pPos)) {
            if (tucked) {
                pLevel.setBlockAndUpdate(pPos, pState.setValue(TUCKED, false));
                pLevel.playSound(null, pPos, AFSoundEvents.CHAIR_UNTUCK.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;

            } else if (isChairBlocking(pState, pLevel, pPos)) {
                pLevel.setBlockAndUpdate(pPos, pState.setValue(TUCKED, true));
                pLevel.playSound(null, pPos, AFSoundEvents.CHAIR_TUCK.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.FAIL;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
        return pState.getValue(TUCKED) ? PushReaction.BLOCK : super.getPistonPushReaction(pState);
    }

    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, TUCKED, WATERLOGGED);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
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
}
