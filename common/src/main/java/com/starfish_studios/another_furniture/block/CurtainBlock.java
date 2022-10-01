package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.entity.CurtainBlockEntity;
import com.starfish_studios.another_furniture.block.properties.CurtainType;
import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.registry.AFSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
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

public class CurtainBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<CurtainType> TYPE = ModBlockStateProperties.CURTAIN_TYPE;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty TOP = BooleanProperty.create("top");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape EAST = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    protected static final VoxelShape NORTH = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);

    private final DyeColor color;
    public CurtainBlock(DyeColor color, Properties properties) {
        super(properties);
        this.color = color;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(TYPE, CurtainType.LEFT)
                .setValue(TOP, true)
                .setValue(OPEN, true)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            default -> NORTH;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(OPEN) ? Shapes.empty() : super.getCollisionShape(state, level, pos, context);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getHorizontalDirection().getOpposite();
        CurtainType type;
        if (direction.getAxis() == Direction.Axis.X) {
            type = context.getClickLocation().z - (double)context.getClickedPos().getZ() > 0.5D ? CurtainType.RIGHT : CurtainType.LEFT;
        } else {
            type = context.getClickLocation().x - (double)context.getClickedPos().getX() > 0.5D ? CurtainType.RIGHT : CurtainType.LEFT;
        }
        if (context.getHorizontalDirection() == Direction.NORTH || context.getHorizontalDirection() == Direction.EAST) {
            type = type == CurtainType.LEFT ? CurtainType.RIGHT : CurtainType.LEFT;
        }
        return this.defaultBlockState().setValue(FACING, direction).setValue(TYPE, type);
        //return level.getBlockState(below).canBeReplaced(pContext) ? this.defaultBlockState().setValue(FACING, direction).setValue(TYPE, type) : null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (level.isClientSide) {
            return;
        }
        Direction facing = state.getValue(FACING);
        CurtainType type = state.getValue(TYPE);
        BlockState l_state = level.getBlockState(pos.relative(facing.getClockWise()));
        BlockState r_state = level.getBlockState(pos.relative(facing.getCounterClockWise()));
        boolean l_side = (l_state.getBlock() instanceof CurtainBlock && l_state.getValue(FACING) == facing);
        boolean r_side = (r_state.getBlock() instanceof CurtainBlock && r_state.getValue(FACING) == facing);

        BlockState ll_state = level.getBlockState(pos.relative(facing.getClockWise(), 2));
        BlockState rr_state = level.getBlockState(pos.relative(facing.getCounterClockWise(), 2));
        boolean ll_side = (ll_state.getBlock() instanceof CurtainBlock && ll_state.getValue(FACING) == facing);
        boolean rr_side = (rr_state.getBlock() instanceof CurtainBlock && rr_state.getValue(FACING) == facing);

        if (type == CurtainType.RIGHT && l_side && ll_side && l_state.getValue(TYPE) == CurtainType.RIGHT && ll_state.getValue(TYPE) == CurtainType.LEFT) {
            level.setBlockAndUpdate(pos.relative(facing.getClockWise()), l_state.setValue(TYPE, CurtainType.MIDDLE));
            level.setBlock(pos, state.setValue(OPEN, l_state.getValue(OPEN)), 2);
        } else if (type == CurtainType.LEFT && r_side && rr_side && r_state.getValue(TYPE) == CurtainType.LEFT && rr_state.getValue(TYPE) == CurtainType.RIGHT) {
            level.setBlockAndUpdate(pos.relative(facing.getCounterClockWise()), r_state.setValue(TYPE, CurtainType.MIDDLE));
            level.setBlock(pos, state.setValue(OPEN, r_state.getValue(OPEN)), 2);
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        CurtainType type = state.getValue(TYPE);
        Direction facing = state.getValue(FACING);
        BlockState lState = level.getBlockState(currentPos.relative(facing.getClockWise()));
        BlockState rState = level.getBlockState(currentPos.relative(facing.getCounterClockWise()));
        boolean lSide = (lState.getBlock() instanceof CurtainBlock && lState.getValue(FACING) == facing);
        boolean rSide = (rState.getBlock() instanceof CurtainBlock && rState.getValue(FACING) == facing);

        if (type == CurtainType.MIDDLE && !(lSide && lState.getValue(TYPE) == CurtainType.LEFT && rSide && rState.getValue(TYPE) == CurtainType.RIGHT)) {
            if (lSide && lState.getValue(TYPE) == CurtainType.LEFT) {
                state = state.setValue(TYPE, CurtainType.RIGHT);
            } else {
                state = state.setValue(TYPE, CurtainType.LEFT);
            }
        }

        return state;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        state = state.cycle(OPEN);
        boolean open = state.getValue(OPEN);
        boolean top = state.getValue(TOP);
        CurtainType type = state.getValue(TYPE);
        Direction facing = state.getValue(FACING);
        BlockPos startpos = pos;
        int times = 1;
        if (type == CurtainType.MIDDLE) {
            times = 3;
            startpos = pos.relative(facing.getClockWise());
        } else if (type == CurtainType.RIGHT) {
            BlockState left = level.getBlockState(startpos.relative(facing.getClockWise()));
            if (left.getBlock() instanceof CurtainBlock && left.getValue(TYPE) == CurtainType.MIDDLE && left.getValue(TOP) == top && left.getValue(FACING) == facing) {
                times = 3;
                startpos = pos.relative(facing.getClockWise(), 2);
            } else if (left.getBlock() instanceof CurtainBlock && left.getValue(TYPE) == CurtainType.LEFT && left.getValue(TOP) == top && left.getValue(FACING) == facing) {
                times = 2;
                startpos = pos.relative(facing.getClockWise());
            }
        } else {
            BlockState right = level.getBlockState(startpos.relative(facing.getCounterClockWise()));
            if (right.getBlock() instanceof CurtainBlock && right.getValue(TYPE) == CurtainType.MIDDLE && right.getValue(TOP) == top && right.getValue(FACING) == facing) {
                times = 3;
            } else if (right.getBlock() instanceof CurtainBlock && right.getValue(TYPE) == CurtainType.RIGHT && right.getValue(TOP) == top && right.getValue(FACING) == facing) {
                times = 2;
            }
        }
        for (int i = 0; i < times; i++) {

            BlockState newstate = level.getBlockState(startpos);
            if (newstate.getBlock() instanceof CurtainBlock) {
                //level.addParticle(DustParticleOptions.REDSTONE, (double)startpos.getX() + 0.5, (double)startpos.getY() + 0.5, (double)startpos.getZ() + 0.5, 0.0D, 0.0D, 0.0D);
                level.setBlock(startpos, newstate.setValue(OPEN, open), 2);
            }
            startpos = startpos.relative(facing.getCounterClockWise());
        }

        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        level.playSound(null, pos, AFSoundEvents.CURTAIN.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, OPEN, TOP, WATERLOGGED);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CurtainBlockEntity(pos, state);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public String getColor() {
        return this.color.toString();
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
