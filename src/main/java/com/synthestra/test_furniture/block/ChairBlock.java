package com.synthestra.test_furniture.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChairBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape LEG_1 = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 6.0D, 2.0D);
    protected static final VoxelShape LEG_2 = Block.box(14.0D, 0.0D, 14.0D, 16.0D, 6.0D, 16.0D);
    protected static final VoxelShape LEG_3 = Block.box(0.0D, 0.0D, 14.0D, 2.0D, 6.0D, 16.0D);
    protected static final VoxelShape LEG_4 = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 6.0D, 2.0D);
    protected static final VoxelShape SEAT = Block.box(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape SHAPE_BASE = Shapes.or(LEG_1, LEG_2, LEG_3, LEG_4, SEAT);
    protected static final VoxelShape SHAPE_NORTH = Shapes.or(SHAPE_BASE, Block.box(0.0D, 8.0D, 14.0D, 16.0D, 16.0D, 16.0D));
    protected static final VoxelShape SHAPE_EAST = Shapes.or(SHAPE_BASE, Block.box(0.0D, 8.0D, 0.0D, 2.0D, 16.0D, 16.0D));
    protected static final VoxelShape SHAPE_SOUTH = Shapes.or(SHAPE_BASE, Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 2.0D));
    protected static final VoxelShape SHAPE_WEST = Shapes.or(SHAPE_BASE, Block.box(14.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D));

    public ChairBlock(Properties p_49795_) {
        super(p_49795_);
        registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public VoxelShape getShape(BlockState p_54105_, BlockGetter p_54106_, BlockPos p_54107_, CollisionContext p_54108_) {
        switch((Direction)p_54105_.getValue(FACING)) {
            case NORTH:
                return SHAPE_NORTH;
            case EAST:
                return SHAPE_EAST;
            case SOUTH:
                return SHAPE_SOUTH;
            case WEST:
                return SHAPE_WEST;
        }
        return SHAPE_NORTH;
    }

    public VoxelShape getInteractionShape(BlockState p_54099_, BlockGetter p_54100_, BlockPos p_54101_) {
        switch((Direction)p_54099_.getValue(FACING)) {
            case NORTH:
                return SHAPE_NORTH;
            case EAST:
                return SHAPE_EAST;
            case SOUTH:
                return SHAPE_SOUTH;
            case WEST:
                return SHAPE_WEST;
        }
        return SHAPE_NORTH;
    }

    public RenderShape getRenderShape(BlockState p_54103_) {
        return RenderShape.MODEL;
    }

    public boolean propagatesSkylightDown(BlockState p_49928_, BlockGetter p_49929_, BlockPos p_49930_) {
        return true;
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_56872_) {
        return this.defaultBlockState().setValue(FACING, p_56872_.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

}
