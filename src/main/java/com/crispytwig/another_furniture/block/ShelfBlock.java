package com.crispytwig.another_furniture.block;

import com.crispytwig.another_furniture.block.entity.ShelfBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShelfBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 3);
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
                .setValue(TYPE, 0)
                .setValue(WATERLOGGED, false)
        );
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext ctx) {
        Direction direction = state.getValue(FACING);
        int type = state.getValue(TYPE);

        if (direction == Direction.NORTH) {
            return switch (type) {
                case 0 -> T_NLR;
                case 1 -> T_NL;
                case 3 -> T_NR;
                default -> TOP;
            };
        } else if (direction == Direction.EAST) {
            return switch (type) {
                case 0 -> T_ELR;
                case 1 -> T_EL;
                case 3 -> T_ER;
                default -> TOP;
            };
        } else if (direction == Direction.SOUTH) {
            return switch (type) {
                case 0 -> T_SLR;
                case 1 -> T_SL;
                case 3 -> T_SR;
                default -> TOP;
            };
        } else if (direction == Direction.WEST) {
            return switch (type) {
                case 0 -> T_WLR;
                case 1 -> T_WL;
                case 3 -> T_WR;
                default -> TOP;
            };
        }
        return TOP;
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (hit.getDirection() == Direction.UP) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof ShelfBlockEntity shelfblockentity) {
                ItemStack itemstack = player.getItemInHand(hand);
                if (!itemstack.isEmpty()) {
                    if (!level.isClientSide && shelfblockentity.placeItem(player.getAbilities().instabuild ? itemstack.copy() : itemstack, this.getPosition(hit, pos))) {
                        return InteractionResult.SUCCESS;
                    }
                    return InteractionResult.CONSUME;
                }
                if (!level.isClientSide && shelfblockentity.removeItem(this.getPosition(hit, pos))) {
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
        Direction facing = state.getValue(FACING);
        BlockState l_state = level.getBlockState(pos.relative(facing.getClockWise()));
        BlockState r_state = level.getBlockState(pos.relative(facing.getCounterClockWise()));
        boolean l_side = (l_state.getBlock() instanceof ShelfBlock && l_state.getValue(FACING) == facing);
        boolean r_side = (r_state.getBlock() instanceof ShelfBlock && r_state.getValue(FACING) == facing);
        int type = 0;
        if (l_side && r_side) {
            type = 2;
        } else if (r_side) {
            type = 1;
        } else if (l_side) {
            type = 3;
        }
        return state.setValue(TYPE, type);
    }

    public FluidState getFluidState(BlockState p_52362_) {
        return p_52362_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_52362_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, WATERLOGGED);
    }

    public BlockEntity newBlockEntity(BlockPos p_152759_, BlockState p_152760_) {
        return new ShelfBlockEntity(p_152759_, p_152760_);
    }


    private int getPosition(BlockHitResult hit, BlockPos pos)
    {
        Vec3 hitVec = hit.getLocation().subtract(pos.getX(), pos.getY(), pos.getZ());
        int position = 0;
        if(hitVec.x() > 0.5) position += 1;
        if(hitVec.z() > 0.5) position += 2;
        return position;
    }
}
