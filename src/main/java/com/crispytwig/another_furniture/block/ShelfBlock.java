package com.crispytwig.another_furniture.block;

import com.crispytwig.another_furniture.block.entity.ShelfBlockEntity;
import com.crispytwig.another_furniture.block.properties.CurtainType;
import com.crispytwig.another_furniture.block.properties.ModBlockStateProperties;
import com.crispytwig.another_furniture.block.properties.ShelfType;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShelfBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<ShelfType> TYPE = ModBlockStateProperties.SHELF_TYPE;
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

    public ShelfBlock(Properties p_49795_) {
        super(p_49795_);
        registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(TYPE, ShelfType.SINGLE)
                .setValue(WATERLOGGED, false)
        );
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (hit.getDirection() == Direction.UP) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof ShelfBlockEntity shelfblockentity) {
                ItemStack itemstack = player.getItemInHand(hand);
                if (!itemstack.isEmpty()) {
                    if (!level.isClientSide && shelfblockentity.placeItem(player.getAbilities().instabuild ? itemstack.copy() : itemstack, this.getPosition(hit, pos))) {
                        level.playSound(null, pos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
                        return InteractionResult.SUCCESS;
                    }
                    return InteractionResult.CONSUME;
                }
                if (!level.isClientSide && shelfblockentity.removeItem(this.getPosition(hit, pos), player)) {
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    public void onRemove(BlockState p_51281_, Level p_51282_, BlockPos p_51283_, BlockState p_51284_, boolean p_51285_) {
        if (!p_51281_.is(p_51284_.getBlock())) {
            BlockEntity blockentity = p_51282_.getBlockEntity(p_51283_);
            if (blockentity instanceof ShelfBlockEntity) {
                Containers.dropContents(p_51282_, p_51283_, ((ShelfBlockEntity)blockentity).getItems());
            }

            super.onRemove(p_51281_, p_51282_, p_51283_, p_51284_, p_51285_);
        }
    }

    public boolean useShapeForLightOcclusion(BlockState p_60576_) {
        return true;
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        ShelfType type = state.getValue(TYPE);

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

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        boolean waterlogged = ctx.getLevel().getFluidState(ctx.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos newPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        if (level.getBlockState(pos.above()).isFaceSturdy(level, pos, Direction.DOWN)) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof ShelfBlockEntity shelfblockentity) {
                shelfblockentity.removeAllItems();
            }
        }

        Direction facing = state.getValue(FACING);
        BlockState l_state = level.getBlockState(pos.relative(facing.getClockWise()));
        BlockState r_state = level.getBlockState(pos.relative(facing.getCounterClockWise()));
        boolean l_side = (l_state.getBlock() instanceof ShelfBlock && l_state.getValue(FACING) == facing);
        boolean r_side = (r_state.getBlock() instanceof ShelfBlock && r_state.getValue(FACING) == facing);
        ShelfType type = l_side && r_side ? ShelfType.MIDDLE : (r_side ? ShelfType.LEFT : (l_side ? ShelfType.RIGHT : ShelfType.SINGLE));
        return state.setValue(TYPE, type);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, WATERLOGGED);
    }

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
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}
