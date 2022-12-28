package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.entity.ShelfBlockEntity;
import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.block.properties.HorizontalConnectionType;
import com.starfish_studios.another_furniture.util.block.ShapeUtil;
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

    protected static final VoxelShape T_NL = Shapes.or(TOP, NL);
    protected static final VoxelShape T_NR = Shapes.or(TOP, NR);
    protected static final VoxelShape T_NLR = Shapes.or(TOP, NL, NR);
    protected static final VoxelShape T_EL = ShapeUtil.rotateShape(T_NL, Direction.EAST);
    protected static final VoxelShape T_ER = ShapeUtil.rotateShape(T_NR, Direction.EAST);
    protected static final VoxelShape T_ELR = ShapeUtil.rotateShape(T_NLR, Direction.EAST);
    protected static final VoxelShape T_SL = ShapeUtil.rotateShape(T_NL, Direction.SOUTH);
    protected static final VoxelShape T_SR = ShapeUtil.rotateShape(T_NR, Direction.SOUTH);
    protected static final VoxelShape T_SLR = ShapeUtil.rotateShape(T_NLR, Direction.SOUTH);
    protected static final VoxelShape T_WL = ShapeUtil.rotateShape(T_NL, Direction.WEST);
    protected static final VoxelShape T_WR = ShapeUtil.rotateShape(T_NLR, Direction.WEST);
    protected static final VoxelShape T_WLR = ShapeUtil.rotateShape(T_NLR, Direction.WEST);

    public ShelfBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(TYPE, HorizontalConnectionType.SINGLE)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (hit.getDirection() != Direction.UP) {
            return InteractionResult.PASS;
        }
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (!(blockentity instanceof ShelfBlockEntity shelfblockentity)) {
            return InteractionResult.PASS;
        }

        // Place
        ItemStack itemstack = player.getItemInHand(hand);
        if (!itemstack.isEmpty()) {
            if (!level.isClientSide && shelfblockentity.placeItem(player.getAbilities().instabuild ? itemstack.copy() : itemstack, this.getPosition(hit, pos))) {
                level.playSound(null, pos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        }

        // Remove
        if (shelfblockentity.removeItem(this.getPosition(hit, pos), player, level)) {
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof ShelfBlockEntity shelfBlockEntity) {
                Containers.dropContents(level, pos, shelfBlockEntity.getItems());
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        HorizontalConnectionType type = state.getValue(TYPE);

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
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        BlockState above = level.getBlockState(currentPos.above());
        if (direction == Direction.UP && (above.isFaceSturdy(level, currentPos, Direction.DOWN) && !above.getVisualShape(level, currentPos.above(), CollisionContext.empty()).isEmpty())) {
            BlockEntity blockentity = level.getBlockEntity(currentPos);
            if (blockentity instanceof ShelfBlockEntity shelfblockentity) {
                shelfblockentity.removeAllItems();
            }
        }

        Direction facing = state.getValue(FACING);
        BlockState l_state = level.getBlockState(currentPos.relative(facing.getClockWise()));
        BlockState r_state = level.getBlockState(currentPos.relative(facing.getCounterClockWise()));
        boolean l_side = (l_state.getBlock() instanceof ShelfBlock && l_state.getValue(FACING) == facing);
        boolean r_side = (r_state.getBlock() instanceof ShelfBlock && r_state.getValue(FACING) == facing);
        HorizontalConnectionType type = l_side && r_side ? HorizontalConnectionType.MIDDLE : (r_side ? HorizontalConnectionType.LEFT : (l_side ? HorizontalConnectionType.RIGHT : HorizontalConnectionType.SINGLE));
        return state.setValue(TYPE, type);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ShelfBlockEntity(pos, state);
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, WATERLOGGED);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }
}

