package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.block.properties.ShutterType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ShutterBlock extends Block implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<ShutterType> TYPE = ModBlockStateProperties.SHUTTER_TYPE;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty LEFT = BooleanProperty.create("left");
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape EAST = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
    protected static final VoxelShape NORTH = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{SOUTH, WEST, NORTH, EAST};

    public ShutterBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, ShutterType.NONE).setValue(OPEN, false).setValue(LEFT, false).setValue(POWERED, false).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        // 3 = open + hinge left
        // 1 open + hinge right
        // 0 not open
        int shape = pState.getValue(FACING).get2DDataValue() + (pState.getValue(OPEN) ? (pState.getValue(LEFT) ? 3 : 1) : 0);
        return SHAPES[shape % 4];
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = this.defaultBlockState();
        Direction facing = pContext.getHorizontalDirection().getOpposite();
        blockstate = blockstate.setValue(FACING, facing);
        boolean left;
        if (facing.getAxis() == Direction.Axis.X) {
            left = pContext.getClickLocation().z - (double)pContext.getClickedPos().getZ() > 0.5D;
        } else {
            left = pContext.getClickLocation().x - (double)pContext.getClickedPos().getX() > 0.5D;
        }
        if (pContext.getHorizontalDirection() == Direction.NORTH || pContext.getHorizontalDirection() == Direction.EAST) left = !left;
        blockstate = blockstate.setValue(LEFT, left);

        if (pContext.getLevel().hasNeighborSignal(pContext.getClickedPos())) {
            blockstate = blockstate.setValue(OPEN, true).setValue(POWERED, true);
        }

        blockstate = blockstate.setValue(TYPE, getType(blockstate, pContext.getLevel().getBlockState(pContext.getClickedPos().above()), pContext.getLevel().getBlockState(pContext.getClickedPos().below())));

        return blockstate.setValue(WATERLOGGED, pContext.getLevel().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!pLevel.isClientSide) {
            boolean powered = pLevel.hasNeighborSignal(pPos);
            if (powered != pState.getValue(POWERED)) {
                if (pState.getValue(OPEN) != powered) {
                    pState = pState.setValue(OPEN, powered);
                    pLevel.playSound(null, pPos, shutterSound(powered), SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                pState = pState.setValue(POWERED, powered);
                if (pState.getValue(WATERLOGGED)) {
                    pLevel.scheduleTick(pPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
                }
            }
            ShutterType type = getType(pState, pLevel.getBlockState(pPos.above()), pLevel.getBlockState(pPos.below()));
            if (pState.getValue(TYPE) != type) {
                pState = pState.setValue(TYPE, type);
            }
            pLevel.setBlock(pPos, pState, 3);
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (this.material == Material.METAL) {
            return InteractionResult.PASS;
        } else {
            pState = pState.cycle(OPEN);
            pLevel.setBlock(pPos, pState, 3);
            if (!pPlayer.isCrouching()) {
                toggleShutters(pState, pLevel, pPos, pState.getValue(OPEN));
            }
            pLevel.playSound(null, pPos, shutterSound(pState.getValue(OPEN)), SoundSource.BLOCKS, 1.0F, 1.0F);
            if (pState.getValue(WATERLOGGED)) {
                pLevel.scheduleTick(pPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
            }

            //this.playSound(p_57543_, p_57541_, p_57542_, p_57540_.getValue(OPEN));
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
    }

    public void toggleShutters(BlockState state, Level level, BlockPos pos, boolean open) {
        BlockState updateState = state;
        BlockPos updatePos = pos;
        if (state.getValue(TYPE) == ShutterType.MIDDLE || state.getValue(TYPE) == ShutterType.BOTTOM) {
            int heightUp = level.dimensionType().height() - updatePos.getY();
            for (int i = 0; i < heightUp; i++) {
                BlockState above = level.getBlockState(updatePos.above());
                if (above.is(state.getBlock()) && above.getValue(FACING) == updateState.getValue(FACING) && above.getValue(LEFT) == updateState.getValue(LEFT) && above.getValue(OPEN) != open) {
                    updateState = above;
                    updatePos = updatePos.above();
                    level.setBlock(updatePos, updateState.setValue(OPEN, open), 3);
                } else {
                    break;
                }
            }
        }
        if (state.getValue(TYPE) == ShutterType.MIDDLE || state.getValue(TYPE) == ShutterType.TOP) {
            updateState = state;
            updatePos = pos;
            int heightDown = level.dimensionType().minY() - updatePos.getY();
            heightDown = (heightDown < 0) ? -heightDown : heightDown;
            for (int i = 0; i < heightDown; i++) {
                BlockState below = level.getBlockState(updatePos.below());
                if (below.is(state.getBlock()) && below.getValue(FACING) == updateState.getValue(FACING) && below.getValue(LEFT) == updateState.getValue(LEFT) && below.getValue(OPEN) != open) {
                    updateState = below;
                    updatePos = updatePos.below();
                    level.setBlock(updatePos, updateState.setValue(OPEN, open), 3);
                } else {
                    break;
                }
            }
        }
    }

    public SoundEvent shutterSound(boolean open) {
        if (open) {
            return SoundEvents.WOODEN_TRAPDOOR_OPEN;
        } else {
            return SoundEvents.WOODEN_TRAPDOOR_CLOSE;
        }
    }

    public ShutterType getType(BlockState state, BlockState above, BlockState below) {
        boolean shape_above_same = above.is(state.getBlock()) && above.getValue(FACING) == state.getValue(FACING)
                && above.getValue(OPEN) == state.getValue(OPEN) && above.getValue(LEFT) == state.getValue(LEFT);
        boolean shape_below_same = below.is(state.getBlock()) && below.getValue(FACING) == state.getValue(FACING)
                && below.getValue(OPEN) == state.getValue(OPEN) && below.getValue(LEFT) == state.getValue(LEFT);

        if (shape_above_same && !shape_below_same) {
            return ShutterType.BOTTOM;
        } else if (!shape_above_same && shape_below_same) {
            return ShutterType.TOP;
        } else if (shape_above_same) {
            return ShutterType.MIDDLE;
        }
        return ShutterType.NONE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, TYPE, OPEN, LEFT, POWERED, WATERLOGGED);
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

        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }
}
