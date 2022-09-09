package com.starfish_studios.another_furniture.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TwoTallSeatBlock extends SeatBlock implements SimpleWaterloggedBlock {
    protected static final VoxelShape AABB_BOTTOM = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 20.0D, 13.0D);
    protected static final VoxelShape AABB_TOP = Block.box(3.0D, -16.0D, 3.0D, 13.0D, 4.0D, 13.0D);

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty TOP = BooleanProperty.create("top");

    public TwoTallSeatBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }

    @Override
    public float seatHeight() {
        return 0.0F;
    }

    @Override
    public boolean isSittable(BlockState state) {
        return state.getValue(TOP);
    }

    @Override
    public BlockPos dismountLocationOffset(BlockPos pos) {
        return pos.below();
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return pState.getValue(TOP) ? AABB_TOP : AABB_BOTTOM;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        boolean waterlogged = pContext.getLevel().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER;
        return pContext.getLevel().getBlockState(pContext.getClickedPos().above()).canBeReplaced(pContext) ? this.defaultBlockState().setValue(WATERLOGGED, waterlogged).setValue(TOP, false) : null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            boolean waterlogged = level.getFluidState(pos).getType() == Fluids.WATER;
            BlockPos blockPos = pos.above();
            level.setBlock(blockPos, state.setValue(WATERLOGGED, waterlogged).setValue(TOP, true), 3);
            level.blockUpdated(pos, Blocks.AIR);
            state.updateNeighbourShapes(level, pos, 3);
        }

    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockState top = pState;
        BlockPos pos = pPos;

        if(!pState.getValue(TOP)) {
            BlockState above = pLevel.getBlockState(pPos.above());
            if (above.getBlock() instanceof TwoTallSeatBlock && above.getValue(TOP)) {
                top = above;
                pos = pPos.above();
            }

        }
        return super.use(top, pLevel, pos, pPlayer, pHand, pHit);
    }

    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }
        Direction dir = pState.getValue(TOP) ? Direction.DOWN : Direction.UP;
        if (pDirection == dir) {
            if (!(pNeighborState.is(this) && pState.getValue(TOP) != pNeighborState.getValue(TOP))) {
                return Blocks.AIR.defaultBlockState();
            }
        }
        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(WATERLOGGED, TOP);
    }

}
