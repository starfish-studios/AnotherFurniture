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
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
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

import javax.annotation.Nullable;

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
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, CurtainType.LEFT).setValue(TOP, true).setValue(OPEN, true).setValue(WATERLOGGED, false));
        this.color = color;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            default -> NORTH;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return pState.getValue(OPEN) ? Shapes.empty() : super.getCollisionShape(pState, pLevel, pPos, pContext);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction direction = pContext.getHorizontalDirection().getOpposite();
        BlockPos blockpos = pContext.getClickedPos();
        BlockPos below = blockpos.below();
        Level level = pContext.getLevel();
        CurtainType type;
        if (direction.getAxis() == Direction.Axis.X) {
            type = pContext.getClickLocation().z - (double)pContext.getClickedPos().getZ() > 0.5D ? CurtainType.RIGHT : CurtainType.LEFT;
        } else {
            type = pContext.getClickLocation().x - (double)pContext.getClickedPos().getX() > 0.5D ? CurtainType.RIGHT : CurtainType.LEFT;
        }
        if (pContext.getHorizontalDirection() == Direction.NORTH || pContext.getHorizontalDirection() == Direction.EAST) {
            type = type == CurtainType.LEFT ? CurtainType.RIGHT : CurtainType.LEFT;
        }
        return level.getBlockState(below).canBeReplaced(pContext) ? this.defaultBlockState().setValue(FACING, direction).setValue(TYPE, type) : null;
    }

    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        if (pLevel.isClientSide) {
            return;
        }
        Direction facing = pState.getValue(FACING);
        CurtainType type = pState.getValue(TYPE);
        BlockState l_state = pLevel.getBlockState(pPos.relative(facing.getClockWise()));
        BlockState r_state = pLevel.getBlockState(pPos.relative(facing.getCounterClockWise()));
        boolean l_side = (l_state.getBlock() instanceof CurtainBlock && l_state.getValue(FACING) == facing);
        boolean r_side = (r_state.getBlock() instanceof CurtainBlock && r_state.getValue(FACING) == facing);

        BlockState ll_state = pLevel.getBlockState(pPos.relative(facing.getClockWise(), 2));
        BlockState rr_state = pLevel.getBlockState(pPos.relative(facing.getCounterClockWise(), 2));
        boolean ll_side = (ll_state.getBlock() instanceof CurtainBlock && ll_state.getValue(FACING) == facing);
        boolean rr_side = (rr_state.getBlock() instanceof CurtainBlock && rr_state.getValue(FACING) == facing);

        if (type == CurtainType.RIGHT && l_side && ll_side && l_state.getValue(TYPE) == CurtainType.RIGHT && ll_state.getValue(TYPE) == CurtainType.LEFT) {
            pLevel.setBlockAndUpdate(pPos.relative(facing.getClockWise()), l_state.setValue(TYPE, CurtainType.MIDDLE));
            pLevel.setBlock(pPos, pState.setValue(OPEN, l_state.getValue(OPEN)), 2);
        } else if (type == CurtainType.LEFT && r_side && rr_side && r_state.getValue(TYPE) == CurtainType.LEFT && rr_state.getValue(TYPE) == CurtainType.RIGHT) {
            pLevel.setBlockAndUpdate(pPos.relative(facing.getCounterClockWise()), r_state.setValue(TYPE, CurtainType.MIDDLE));
            pLevel.setBlock(pPos, pState.setValue(OPEN, r_state.getValue(OPEN)), 2);
        }
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }
        CurtainType type = pState.getValue(TYPE);
        Direction facing = pState.getValue(FACING);
        BlockState lState = pLevel.getBlockState(pCurrentPos.relative(facing.getClockWise()));
        BlockState rState = pLevel.getBlockState(pCurrentPos.relative(facing.getCounterClockWise()));
        boolean lSide = (lState.getBlock() instanceof CurtainBlock && lState.getValue(FACING) == facing);
        boolean rSide = (rState.getBlock() instanceof CurtainBlock && rState.getValue(FACING) == facing);

        if (type == CurtainType.MIDDLE && !(lSide && lState.getValue(TYPE) == CurtainType.LEFT && rSide && rState.getValue(TYPE) == CurtainType.RIGHT)) {
            if (lSide && lState.getValue(TYPE) == CurtainType.LEFT) {
                pState = pState.setValue(TYPE, CurtainType.RIGHT);
            } else {
                pState = pState.setValue(TYPE, CurtainType.LEFT);
            }
        }

        return pState;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        pState = pState.cycle(OPEN);
        boolean open = pState.getValue(OPEN);
        boolean top = pState.getValue(TOP);
        CurtainType type = pState.getValue(TYPE);
        Direction facing = pState.getValue(FACING);
        BlockPos startpos = pPos;
        int times = 1;
        if (type == CurtainType.MIDDLE) {
            times = 3;
            startpos = pPos.relative(facing.getClockWise());
        } else if (type == CurtainType.RIGHT) {
            BlockState left = pLevel.getBlockState(startpos.relative(facing.getClockWise()));
            if (left.getBlock() instanceof CurtainBlock && left.getValue(TYPE) == CurtainType.MIDDLE && left.getValue(TOP) == top && left.getValue(FACING) == facing) {
                times = 3;
                startpos = pPos.relative(facing.getClockWise(), 2);
            } else if (left.getBlock() instanceof CurtainBlock && left.getValue(TYPE) == CurtainType.LEFT && left.getValue(TOP) == top && left.getValue(FACING) == facing) {
                times = 2;
                startpos = pPos.relative(facing.getClockWise());
            }
        } else {
            BlockState right = pLevel.getBlockState(startpos.relative(facing.getCounterClockWise()));
            if (right.getBlock() instanceof CurtainBlock && right.getValue(TYPE) == CurtainType.MIDDLE && right.getValue(TOP) == top && right.getValue(FACING) == facing) {
                times = 3;
            } else if (right.getBlock() instanceof CurtainBlock && right.getValue(TYPE) == CurtainType.RIGHT && right.getValue(TOP) == top && right.getValue(FACING) == facing) {
                times = 2;
            }
        }
        for (int i = 0; i < times; i++) {

            BlockState newstate = pLevel.getBlockState(startpos);
            if (newstate.getBlock() instanceof CurtainBlock) {
                //pLevel.addParticle(DustParticleOptions.REDSTONE, (double)startpos.getX() + 0.5, (double)startpos.getY() + 0.5, (double)startpos.getZ() + 0.5, 0.0D, 0.0D, 0.0D);
                pLevel.setBlock(startpos, newstate.setValue(OPEN, open), 2);
            }
            startpos = startpos.relative(facing.getCounterClockWise());
        }

        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }
        pLevel.playSound(null, pPos, AFSoundEvents.CURTAIN.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.DESTROY;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, TYPE, OPEN, TOP, WATERLOGGED);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CurtainBlockEntity(pPos, pState);
    }

    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public String getColor() {
        return this.color.toString();
    }
}
