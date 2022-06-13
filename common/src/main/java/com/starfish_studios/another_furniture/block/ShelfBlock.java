package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.entity.ShelfBlockEntity;
import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.block.properties.HorizontalConnectionType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
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
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ShelfBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<HorizontalConnectionType> TYPE = ModBlockStateProperties.HORIZONTAL_CONNECTION_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape TOP = Block.box(0, 14, 0.0, 16, 16, 16);
    protected static final VoxelShape NL = Block.box(12, 6, 6, 16, 14, 16);
    protected static final VoxelShape NR = Block.box(0, 6, 6, 4, 14, 16);
    protected static final VoxelShape EL = Block.box(0, 6, 12, 10, 14, 16);
    protected static final VoxelShape ER = Block.box(0, 6, 0, 10, 14, 4);
    protected static final VoxelShape SL = Block.box(0, 6, 0, 4, 14, 10);
    protected static final VoxelShape SR = Block.box(12, 6, 0, 16, 14, 10);
    protected static final VoxelShape WL = Block.box(6, 6, 0, 16, 14, 4);
    protected static final VoxelShape WR = Block.box(6, 6, 12, 16, 14, 16);

    protected static final VoxelShape T_NL = Shapes.or(TOP, NL);
    protected static final VoxelShape T_NR = Shapes.or(TOP, NR);
    protected static final VoxelShape T_NLR = Shapes.or(TOP, NL, NR);
    protected static final VoxelShape T_EL = Shapes.or(TOP, EL);
    protected static final VoxelShape T_ER = Shapes.or(TOP, ER);
    protected static final VoxelShape T_ELR = Shapes.or(TOP, EL, ER);
    protected static final VoxelShape T_SL = Shapes.or(TOP, SL);
    protected static final VoxelShape T_SR = Shapes.or(TOP, SR);
    protected static final VoxelShape T_SLR = Shapes.or(TOP, SL, SR);
    protected static final VoxelShape T_WL = Shapes.or(TOP, WL);
    protected static final VoxelShape T_WR = Shapes.or(TOP, WR);
    protected static final VoxelShape T_WLR = Shapes.or(TOP, WL, WR);

    public ShelfBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(TYPE, HorizontalConnectionType.SINGLE)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pHit.getDirection() == Direction.UP) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof ShelfBlockEntity shelfblockentity) {
                ItemStack itemstack = pPlayer.getItemInHand(pHand);
                if (!itemstack.isEmpty()) {
                    if (!pLevel.isClientSide && shelfblockentity.placeItem(pPlayer.getAbilities().instabuild ? itemstack.copy() : itemstack, this.getPosition(pHit, pPos))) {
                        pLevel.playSound(null, pPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
                        return InteractionResult.SUCCESS;
                    }
                    return InteractionResult.CONSUME;
                }
                if (!pLevel.isClientSide && shelfblockentity.removeItem(this.getPosition(pHit, pPos), pPlayer)) {
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof ShelfBlockEntity) {
                Containers.dropContents(pLevel, pPos, ((ShelfBlockEntity)blockentity).getItems());
            }

            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        HorizontalConnectionType type = pState.getValue(TYPE);

        if (direction == Direction.NORTH) {
            return switch (type) {
                case SINGLE -> T_NLR;
                case LEFT -> T_NL;
                case RIGHT -> T_NR;
                default -> TOP;
            };
        } else if (direction == Direction.EAST) {
            return switch (type) {
                case SINGLE -> T_ELR;
                case LEFT -> T_EL;
                case RIGHT -> T_ER;
                default -> TOP;
            };
        } else if (direction == Direction.SOUTH) {
            return switch (type) {
                case SINGLE -> T_SLR;
                case LEFT -> T_SL;
                case RIGHT -> T_SR;
                default -> TOP;
            };
        } else {
            return switch (type) {
                case SINGLE -> T_WLR;
                case LEFT -> T_WL;
                case RIGHT -> T_WR;
                default -> TOP;
            };
        }
    }

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
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
        if (pLevel.getBlockState(pCurrentPos.above()).isFaceSturdy(pLevel, pCurrentPos, Direction.DOWN)) {
            BlockEntity blockentity = pLevel.getBlockEntity(pCurrentPos);
            if (blockentity instanceof ShelfBlockEntity shelfblockentity) {
                shelfblockentity.removeAllItems();
            }
        }

        Direction facing = pState.getValue(FACING);
        BlockState l_state = pLevel.getBlockState(pCurrentPos.relative(facing.getClockWise()));
        BlockState r_state = pLevel.getBlockState(pCurrentPos.relative(facing.getCounterClockWise()));
        boolean l_side = (l_state.getBlock() instanceof ShelfBlock && l_state.getValue(FACING) == facing);
        boolean r_side = (r_state.getBlock() instanceof ShelfBlock && r_state.getValue(FACING) == facing);
        HorizontalConnectionType type = l_side && r_side ? HorizontalConnectionType.MIDDLE : (r_side ? HorizontalConnectionType.LEFT : (l_side ? HorizontalConnectionType.RIGHT : HorizontalConnectionType.SINGLE));
        return pState.setValue(TYPE, type);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ShelfBlockEntity(pPos, pState);
    }

    private int getPosition(BlockHitResult hit, BlockPos pos)
    {
        Vec3 hitVec = hit.getLocation().subtract(pos.getX(), pos.getY(), pos.getZ());
        int position = 0;
        if(hitVec.x() > 0.5) position += 1;
        if(hitVec.z() > 0.5) position += 2;
        return position;
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
        pBuilder.add(FACING, TYPE, WATERLOGGED);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }
}

