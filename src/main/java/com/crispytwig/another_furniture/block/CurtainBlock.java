package com.crispytwig.another_furniture.block;

import com.crispytwig.another_furniture.AnotherFurnitureMod;
import com.crispytwig.another_furniture.block.entity.ShelfBlockEntity;
import com.crispytwig.another_furniture.block.properties.CurtainType;
import com.crispytwig.another_furniture.block.properties.ModBlockStateProperties;
import com.crispytwig.another_furniture.block.properties.ShelfType;
import com.crispytwig.another_furniture.block.properties.ShutterType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
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
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class CurtainBlock extends BaseBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<CurtainType> TYPE = ModBlockStateProperties.CURTAIN_TYPE;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty TOP = BooleanProperty.create("top");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape EAST = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    protected static final VoxelShape NORTH = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);

    public CurtainBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, CurtainType.RIGHT).setValue(TOP, true).setValue(OPEN, true).setValue(WATERLOGGED, false));
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            default -> NORTH;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return state.getValue(OPEN) ? Shapes.empty() : super.getCollisionShape(state, blockGetter, pos, context);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getHorizontalDirection().getOpposite();
        BlockPos blockpos = context.getClickedPos();
        BlockPos below = blockpos.below();
        Level level = context.getLevel();
        return level.getBlockState(below).canBeReplaced(context) ? this.defaultBlockState().setValue(FACING, direction) : null;
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack item) {
        super.setPlacedBy(level, pos, state, entity, item);
        if (!level.isClientSide) {
            BlockPos blockpos = pos.below();
            level.setBlock(blockpos, state.setValue(TOP, false), 3);
            level.blockUpdated(pos, Blocks.AIR);
            state.updateNeighbourShapes(level, pos, 3);
        }

    }

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
            startpos = pos.relative(facing.getCounterClockWise());
        } else if (type == CurtainType.RIGHT) {
            BlockState left = level.getBlockState(startpos.relative(facing.getCounterClockWise()));
            if (left.getBlock() instanceof CurtainBlock && left.getValue(TYPE) == CurtainType.MIDDLE && left.getValue(TOP) == top && left.getValue(FACING) == facing) {
                times = 3;
                startpos = pos.relative(facing.getCounterClockWise(), 2);
            } else if (left.getBlock() instanceof CurtainBlock && left.getValue(TYPE) == CurtainType.LEFT && left.getValue(TOP) == top && left.getValue(FACING) == facing) {
                times = 2;
                startpos = pos.relative(facing.getCounterClockWise());
            }
        } else {
            BlockState right = level.getBlockState(startpos.relative(facing.getClockWise()));
            if (right.getBlock() instanceof CurtainBlock && right.getValue(TYPE) == CurtainType.MIDDLE && right.getValue(TOP) == top && right.getValue(FACING) == facing) {
                times = 3;
            } else if (right.getBlock() instanceof CurtainBlock && right.getValue(TYPE) == CurtainType.RIGHT && right.getValue(TOP) == top && right.getValue(FACING) == facing) {
                times = 2;
            }
        }
        for (int i = 0; i < times; i++) {

            BlockState newstate = level.getBlockState(startpos);
            BlockPos updownPos = top ? startpos.below() : startpos.above();
            BlockState updownstate = level.getBlockState(updownPos);
            if (newstate.getBlock() instanceof CurtainBlock && updownstate.getBlock() instanceof CurtainBlock) {
                level.addParticle(DustParticleOptions.REDSTONE, (double)startpos.getX() + 0.5, (double)startpos.getY() + 0.5, (double)startpos.getZ() + 0.5, 0.0D, 0.0D, 0.0D);
                level.addParticle(DustParticleOptions.REDSTONE, (double)updownPos.getX() + 0.5, (double)updownPos.getY() + 0.5, (double)updownPos.getZ() + 0.5, 0.0D, 0.0D, 0.0D);
                level.setBlock(startpos, newstate.setValue(OPEN, open), 2);
                level.setBlock(updownPos, updownstate.setValue(OPEN, open), 2);
            }
            startpos = startpos.relative(facing.getClockWise());
        }

        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, OPEN, TOP, WATERLOGGED);
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

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos p_57551_, boolean p_57552_) {
        if (level.isClientSide) {
            return;
        }
        BlockPos elevation = state.getValue(TOP) ? pos.below() : pos.above();
        if (!(level.getBlockState(elevation).getBlock() instanceof CurtainBlock)) {
            level.destroyBlock(pos, true);
        } else {
            Direction facing = state.getValue(FACING);
            boolean top = state.getValue(TOP);
            BlockState l_state = level.getBlockState(pos.relative(facing.getClockWise()));
            BlockState r_state = level.getBlockState(pos.relative(facing.getCounterClockWise()));
            boolean l_side = (l_state.getBlock() instanceof CurtainBlock && l_state.getValue(FACING) == facing && l_state.getValue(TOP) == top);
            boolean r_side = (r_state.getBlock() instanceof CurtainBlock && r_state.getValue(FACING) == facing && r_state.getValue(TOP) == top);

            BlockState ll_state = level.getBlockState(pos.relative(facing.getClockWise(), 2));
            BlockState rr_state = level.getBlockState(pos.relative(facing.getCounterClockWise(), 2));
            boolean ll_side = (ll_state.getBlock() instanceof CurtainBlock && ll_state.getValue(FACING) == facing && ll_state.getValue(TOP) == top);
            boolean rr_side = (rr_state.getBlock() instanceof CurtainBlock && rr_state.getValue(FACING) == facing && rr_state.getValue(TOP) == top);

            boolean debug = (level.getBlockState(pos.above()).getBlock() == Blocks.GOLD_BLOCK);
            CurtainType type = CurtainType.LEFT;


            if (l_side && r_side) {
                if (debug) {
                    AnotherFurnitureMod.LOGGER.info("l_side && r_side");
                }

                //if (ll_side && ll_state.getValue(TYPE) == CurtainType.MIDDLE) {
                //    if (debug) {
                //        AnotherFurnitureMod.LOGGER.info("2 blocks left is middle");
                //    }
                //    type = l_state.getValue(TYPE) == CurtainType.RIGHT ? CurtainType.LEFT : CurtainType.RIGHT;
                //} else if (rr_side && rr_state.getValue(TYPE) == CurtainType.MIDDLE) {
                //    if (debug) {
                //        AnotherFurnitureMod.LOGGER.info("2 blocks right is middle");
                //    }
                //    type = r_state.getValue(TYPE) == CurtainType.LEFT ? CurtainType.RIGHT : CurtainType.LEFT;
                //} else {
                //    if (debug) {
                //        AnotherFurnitureMod.LOGGER.info("else. set to middle");
                //    }
                //    type = CurtainType.MIDDLE;
                //}
            } else if (r_side) {
                if (debug) {
                    AnotherFurnitureMod.LOGGER.info("only right");
                }
                if (r_state.getValue(TYPE) == CurtainType.MIDDLE) {
                    return;
                }
                type = r_state.getValue(TYPE) == CurtainType.LEFT ? CurtainType.RIGHT : CurtainType.LEFT;
            } else if (l_side) {
                if (debug) {
                    AnotherFurnitureMod.LOGGER.info("only left");
                }
                if (l_state.getValue(TYPE) == CurtainType.MIDDLE) {
                    return;
                }
                type = l_state.getValue(TYPE) == CurtainType.RIGHT ? CurtainType.RIGHT : CurtainType.LEFT;
            }
            level.setBlockAndUpdate(pos, state.setValue(TYPE, type));
            level.setBlockAndUpdate(elevation, state.cycle(TOP).setValue(TYPE, type));
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide && player.isCreative()) {
            preventCreativeDrop(level, pos, state, player);
        }
        super.playerWillDestroy(level, pos, state, player);
    }

    protected static void preventCreativeDrop(Level level, BlockPos pos, BlockState state, Player player) {
        if (!state.getValue(TOP)) {
            BlockPos up = pos.above();
            BlockState up_state = level.getBlockState(up);
            if (up_state.is(state.getBlock()) && up_state.getValue(TOP)) {
                BlockState replace = up_state.hasProperty(BlockStateProperties.WATERLOGGED) && up_state.getValue(BlockStateProperties.WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                level.setBlock(up, replace, 35);
            }
        }
    }
}
