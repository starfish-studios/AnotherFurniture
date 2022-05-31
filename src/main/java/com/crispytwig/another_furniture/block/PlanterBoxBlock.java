package com.crispytwig.another_furniture.block;

import com.crispytwig.another_furniture.block.entity.PlanterBoxBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.extensions.IForgeBlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;

public class PlanterBoxBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ATTACHED = BlockStateProperties.ATTACHED;
    protected static final VoxelShape NORTH_AABB = Block.box(0, 0, 4, 16, 6, 16);
    protected static final VoxelShape SOUTH_AABB = Block.box(0, 0, 0, 16, 6, 12);
    protected static final VoxelShape WEST_AABB = Block.box(4, 0, 0, 16, 6, 16);
    protected static final VoxelShape EAST_AABB = Block.box(0, 0, 0, 12, 6, 16);
    protected static final VoxelShape Z_AXIS_AABB = Block.box(0, 0, 2, 16, 6, 14);
    protected static final VoxelShape X_AXIS_AABB = Block.box(2, 0, 0, 14, 6, 16);

    public PlanterBoxBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(ATTACHED, false));
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

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(ATTACHED, !pContext.getClickedFace().equals(Direction.UP)).setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, ATTACHED);
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
                    Block block = ((BlockItem)stack.getItem()).getBlock();
                    pLevel.playSound(null, pPos, block.getSoundType(block.defaultBlockState()).getPlaceSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
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
