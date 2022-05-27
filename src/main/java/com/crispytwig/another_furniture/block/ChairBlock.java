package com.crispytwig.another_furniture.block;

import com.crispytwig.another_furniture.init.ModSoundEvents;
import com.crispytwig.another_furniture.init.ModTags;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
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
        if (pState.getValue(TUCKED)) {
            if (!(pLevel.getBlockState(pCurrentPos.relative(pState.getValue(FACING))).getBlock() instanceof TableBlock)) {
                return pState.setValue(TUCKED, false);
            }
        }
        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        boolean tucked = state.getValue(TUCKED);
        if ((player.isCrouching() || tucked) && player.getMainHandItem().isEmpty() && player.getOffhandItem().isEmpty() &&
                level.getBlockState(pos.relative(state.getValue(FACING))).is(ModTags.CHAIRS_TUCKABLE_UNDER)) {
            if (tucked) {
                level.setBlockAndUpdate(pos, state.setValue(TUCKED, false));
                level.playSound(null, pos, ModSoundEvents.CHAIR_UNTUCK.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            } else {
                boolean push_in = true;
                Direction facing = state.getValue(FACING);
                BlockState left = level.getBlockState(pos.relative(facing).relative(facing.getCounterClockWise()));
                BlockState right = level.getBlockState(pos.relative(facing).relative(facing.getClockWise()));
                if (left.getBlock() instanceof ChairBlock || right.getBlock() instanceof ChairBlock) {
                    if ((left.getValue(TUCKED) && left.getValue(FACING) == facing.getClockWise()) || (right.getValue(TUCKED) && right.getValue(FACING) == facing.getCounterClockWise())) {
                        push_in = false;
                    }
                }
                if (push_in) {
                    level.setBlockAndUpdate(pos, state.setValue(TUCKED, true));
                    level.playSound(null, pos, ModSoundEvents.CHAIR_TUCK.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                    return InteractionResult.SUCCESS;
                }
            }
            return InteractionResult.FAIL;
        }
        return super.use(state, level, pos, player, hand, hit);
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

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 1;
    }
}
