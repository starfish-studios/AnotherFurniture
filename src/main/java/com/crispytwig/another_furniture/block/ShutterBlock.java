package com.crispytwig.another_furniture.block;

import com.crispytwig.another_furniture.block.properties.ModBlockStateProperties;
import com.crispytwig.another_furniture.block.properties.ShutterType;
import com.crispytwig.another_furniture.init.ModSoundEvents;
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

public class ShutterBlock extends BaseBlock implements SimpleWaterloggedBlock {
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

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        // 3 = open + hinge left
        // 1 open + hinge right
        // 0 not open
        int shape = state.getValue(FACING).get2DDataValue() + (state.getValue(OPEN) ? (state.getValue(LEFT) ? 3 : 1) : 0);
        return SHAPES[shape % 4];
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = this.defaultBlockState();
        Direction facing = context.getHorizontalDirection().getOpposite();
        blockstate = blockstate.setValue(FACING, facing);
        boolean left;
        if (facing.getAxis() == Direction.Axis.X) {
            left = context.getClickLocation().z - (double) context.getClickedPos().getZ() > 0.5D;
        } else {
            left = context.getClickLocation().x - (double) context.getClickedPos().getX() > 0.5D;
        }
        if (context.getHorizontalDirection() == Direction.NORTH || context.getHorizontalDirection() == Direction.EAST) left = !left;
        blockstate = blockstate.setValue(LEFT, left);

        if (context.getLevel().hasNeighborSignal(context.getClickedPos())) {
            blockstate = blockstate.setValue(OPEN, true).setValue(POWERED, true);
        }

        blockstate = blockstate.setValue(TYPE, getType(blockstate, context.getLevel().getBlockState(context.getClickedPos().above()), context.getLevel().getBlockState(context.getClickedPos().below())));

        return blockstate.setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
    }

    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos p_57551_, boolean p_57552_) {
        if (!level.isClientSide) {
            boolean powered = level.hasNeighborSignal(pos);
            if (powered != state.getValue(POWERED)) {
                if (state.getValue(OPEN) != powered) {
                    state = state.setValue(OPEN, powered);
                    level.playSound(null, pos, shutterSound(powered), SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                state = state.setValue(POWERED, powered);
                if (state.getValue(WATERLOGGED)) {
                    level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
                }
            }
            ShutterType type = getType(state, level.getBlockState(pos.above()), level.getBlockState(pos.below()));
            if (state.getValue(TYPE) != type) {
                state = state.setValue(TYPE, type);
            }
            level.setBlock(pos, state, 3);
        }
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (this.material == Material.METAL) {
            return InteractionResult.PASS;
        } else {
            state = state.cycle(OPEN);
            level.setBlock(pos, state, 3);
            if (!player.isCrouching()) {
                toggleShutters(state, level, pos, state.getValue(OPEN));
            }
            level.playSound(null, pos, shutterSound(state.getValue(OPEN)), SoundSource.BLOCKS, 1.0F, 1.0F);
            if (state.getValue(WATERLOGGED)) {
                level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
            }

            //this.playSound(p_57543_, p_57541_, p_57542_, p_57540_.getValue(OPEN));
            return InteractionResult.sidedSuccess(level.isClientSide);
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
        int shape = (state.getValue(FACING).get2DDataValue() + (state.getValue(OPEN) ? (state.getValue(LEFT) ? 3 : 1) : 0)) % 4;
        boolean shape_above_same = above.is(state.getBlock()) && (above.getValue(FACING).get2DDataValue() + (above.getValue(OPEN) ? (above.getValue(LEFT) ? 3 : 1) : 0)) % 4 == shape;
        boolean shape_below_same = below.is(state.getBlock()) && (below.getValue(FACING).get2DDataValue() + (below.getValue(OPEN) ? (below.getValue(LEFT) ? 3 : 1) : 0)) % 4 == shape;

        if (shape_above_same && !shape_below_same) {
            return ShutterType.BOTTOM;
        } else if (!shape_above_same && shape_below_same) {
            return ShutterType.TOP;
        } else if (shape_above_same) {
            return ShutterType.MIDDLE;
        }
        return ShutterType.NONE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_57561_) {
        p_57561_.add(FACING, TYPE, OPEN, LEFT, POWERED, WATERLOGGED);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos newPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, direction, newState, level, pos, newPos);
    }
}
