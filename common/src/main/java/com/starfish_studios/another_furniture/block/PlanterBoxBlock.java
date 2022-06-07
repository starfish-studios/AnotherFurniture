package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.entity.PlanterBoxBlockEntity;
import com.starfish_studios.another_furniture.block.entity.ShelfBlockEntity;
import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.block.properties.PlanterBoxType;
import com.starfish_studios.another_furniture.block.properties.ShelfType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PlanterBoxBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<PlanterBoxType> TYPE = ModBlockStateProperties.PLANTER_BOX_TYPE;
    public static final BooleanProperty ATTACHED = BlockStateProperties.ATTACHED;
    protected static final VoxelShape NORTH_AABB = Block.box(0, 10, 8, 16, 16, 16);
    protected static final VoxelShape SOUTH_AABB = Block.box(0, 10, 0, 16, 16, 8);
    protected static final VoxelShape WEST_AABB = Block.box(8, 10, 0, 16, 16, 16);
    protected static final VoxelShape EAST_AABB = Block.box(0, 10, 0, 8, 16, 16);
    protected static final VoxelShape Z_AXIS_AABB = Block.box(0, 0, 4, 16, 6, 12);
    protected static final VoxelShape X_AXIS_AABB = Block.box(4, 0, 0, 12, 6, 16);

    public PlanterBoxBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, PlanterBoxType.SINGLE).setValue(ATTACHED, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        boolean attached = pState.getValue(ATTACHED);
        switch (pState.getValue(FACING)) {
            case WEST:
                return attached ? WEST_AABB : X_AXIS_AABB;
            case EAST:
                return attached ? EAST_AABB : X_AXIS_AABB;
            case SOUTH:
                return attached ? SOUTH_AABB : Z_AXIS_AABB;
            default:
                return attached ? NORTH_AABB : Z_AXIS_AABB;
        }
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (state.getValue(ATTACHED)) {
            Direction direction = state.getValue(FACING);
            BlockPos facingPos = pos.relative(direction.getOpposite());
            BlockState facingState = level.getBlockState(facingPos);
            return facingState.isFaceSturdy(level, facingPos, direction);
        }
        return super.canSurvive(state, level, pos);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (pState.getValue(ATTACHED) && pDirection.getOpposite() == pState.getValue(FACING) && !pState.canSurvive(pLevel, pCurrentPos)) {
            return Blocks.AIR.defaultBlockState();
        }
        Direction facing = pState.getValue(FACING);
        BlockState l_state = pLevel.getBlockState(pCurrentPos.relative(facing.getClockWise()));
        BlockState r_state = pLevel.getBlockState(pCurrentPos.relative(facing.getCounterClockWise()));
        boolean l_side = (l_state.getBlock() instanceof PlanterBoxBlock && l_state.getValue(FACING) == facing);
        boolean r_side = (r_state.getBlock() instanceof PlanterBoxBlock && r_state.getValue(FACING) == facing);
        PlanterBoxType type = l_side && r_side ? PlanterBoxType.MIDDLE : (r_side ? PlanterBoxType.LEFT : (l_side ? PlanterBoxType.RIGHT : PlanterBoxType.SINGLE));
        return pState.setValue(TYPE, type);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction clickedFace = pContext.getClickedFace();
        boolean attached = !(clickedFace == Direction.UP || clickedFace == Direction.DOWN);
        Direction facing = attached ? clickedFace : pContext.getHorizontalDirection().getOpposite();
        return this.defaultBlockState().setValue(ATTACHED, attached).setValue(FACING, facing);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, TYPE, ATTACHED);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PlanterBoxBlockEntity(pPos, pState);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof PlanterBoxBlockEntity planterBoxBlockEntity) {
            ItemStack stack = pPlayer.getItemInHand(pHand);
            if (stack.is(ItemTags.SMALL_FLOWERS) || stack.is(ItemTags.SAPLINGS)) {
                if (!pLevel.isClientSide && planterBoxBlockEntity.placeFlower(pPlayer.getAbilities().instabuild ? stack.copy() : stack)) {

                    return InteractionResult.SUCCESS;
                }

                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof PlanterBoxBlockEntity planterBoxBlockEntity) {
                Containers.dropContents(pLevel, pPos, planterBoxBlockEntity.getItems());
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }
}
