package com.crispytwig.another_furniture.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChairBlock extends SeatBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape SEAT = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 7.0D, 14.0D);
    protected static final VoxelShape SHAPE_NORTH = Shapes.or(SEAT, Block.box(2.0D, 7.0D, 12.0D, 14.0D, 16.0D, 14.0D));
    protected static final VoxelShape SHAPE_EAST = Shapes.or(SEAT, Block.box(2.0D, 7.0D, 2.0D, 4.0D, 16.0D, 14.0D));
    protected static final VoxelShape SHAPE_SOUTH = Shapes.or(SEAT, Block.box(2.0D, 7.0D, 2.0D, 14.0D, 16.0D, 4.0D));
    protected static final VoxelShape SHAPE_WEST = Shapes.or(SEAT, Block.box(12.0D, 7.0D, 2.0D, 14.0D, 16.0D, 14.0D));

    public ChairBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public float seatHeight() {
        return 0.2F;
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext ctx) {
        return switch (state.getValue(FACING)) {
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_NORTH;
        };
    }

    //public VoxelShape getInteractionShape(BlockState state, BlockGetter blockGetter, BlockPos pos) {
    //    return switch (state.getValue(FACING)) {
    //        case EAST -> SHAPE_EAST;
    //        case SOUTH -> SHAPE_SOUTH;
    //        case WEST -> SHAPE_WEST;
    //        default -> SHAPE_NORTH;
    //    };
    //}

    //public RenderShape getRenderShape(BlockState p_54103_) {
     //   return RenderShape.MODEL;
    //}

    //public boolean propagatesSkylightDown(BlockState state, BlockGetter blockGetter, BlockPos pos) {
    //    return true;
    //}

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

}
