package com.crispytwig.another_furniture.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TableBlock extends BaseBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty LEG1 = BooleanProperty.create("leg_1");
    public static final BooleanProperty LEG2 = BooleanProperty.create("leg_2");
    public static final BooleanProperty LEG3 = BooleanProperty.create("leg_3");
    public static final BooleanProperty LEG4 = BooleanProperty.create("leg_4");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    //exists solely to update corner blocks
    public static final BooleanProperty UPDATE = BooleanProperty.create("update");

    protected static final VoxelShape TOP = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape LEG_1 = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 14.0D, 2.0D);
    protected static final VoxelShape LEG_2 = Block.box(14.0D, 0.0D, 14.0D, 16.0D, 14.0D, 16.0D);
    protected static final VoxelShape LEG_3 = Block.box(0.0D, 0.0D, 14.0D, 2.0D, 14.0D, 16.0D);
    protected static final VoxelShape LEG_4 = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 14.0D, 2.0D);
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{
            TOP, Shapes.or(TOP, LEG_1), Shapes.or(TOP, LEG_2), Shapes.or(TOP, LEG_1, LEG_2),
            Shapes.or(TOP, LEG_3), Shapes.or(TOP, LEG_1, LEG_3), Shapes.or(TOP, LEG_2, LEG_3),
            Shapes.or(TOP, LEG_1, LEG_2, LEG_3), Shapes.or(TOP, LEG_4), Shapes.or(TOP, LEG_1, LEG_4),
            Shapes.or(TOP, LEG_2, LEG_4), Shapes.or(TOP, LEG_1, LEG_2, LEG_4), Shapes.or(TOP, LEG_3, LEG_4),
            Shapes.or(TOP, LEG_1, LEG_3, LEG_4), Shapes.or(TOP, LEG_2, LEG_3, LEG_4), Shapes.or(TOP, LEG_1, LEG_2, LEG_3, LEG_4)
    };

    public TableBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(LEG1, true)
                .setValue(LEG2, true)
                .setValue(LEG3, true)
                .setValue(LEG4, true)
                .setValue(WATERLOGGED, false));
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext ctx) {
        int shape = 0;
        if (state.getValue(LEG1)) shape += 1;
        if (state.getValue(LEG2)) shape += 2;
        if (state.getValue(LEG3)) shape += 4;
        if (state.getValue(LEG4)) shape += 8;
        return SHAPES[shape];
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
        boolean n = level.getBlockState(pos.north()).getBlock() instanceof TableBlock;
        boolean e = level.getBlockState(pos.east()).getBlock() instanceof TableBlock;
        boolean s = level.getBlockState(pos.south()).getBlock() instanceof TableBlock;
        boolean w = level.getBlockState(pos.west()).getBlock() instanceof TableBlock;
        boolean leg1 = (!n && !e) || (n && e && !(level.getBlockState(pos.north().east()).getBlock() instanceof TableBlock));
        boolean leg2 = (!e && !s) || (e && s && !(level.getBlockState(pos.south().east()).getBlock() instanceof TableBlock));
        boolean leg3 = (!s && !w) || (s && w && !(level.getBlockState(pos.south().west()).getBlock() instanceof TableBlock));
        boolean leg4 = (!n && !w) || (n && w && !(level.getBlockState(pos.north().west()).getBlock() instanceof TableBlock));
        boolean update = ((n ? 1 : 0) + (e ? 1 : 0) + (s ? 1 : 0) + (w ? 1 : 0)) % 2 == 0;
        return state.setValue(LEG1, leg1).setValue(LEG2, leg2).setValue(LEG3, leg3).setValue(LEG4, leg4).setValue(UPDATE, update);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEG1, LEG2, LEG3, LEG4, UPDATE, WATERLOGGED);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        boolean leg1 = state.getValue(LEG1);
        boolean leg2 = state.getValue(LEG2);
        boolean leg3 = state.getValue(LEG3);
        boolean leg4 = state.getValue(LEG4);
        return switch(rot) {
            case NONE -> state.setValue(FACING, rot.rotate(state.getValue(FACING)));
            case CLOCKWISE_90 -> state.setValue(FACING, rot.rotate(state.getValue(FACING))).setValue(LEG1, leg4).setValue(LEG2, leg1).setValue(LEG3, leg2).setValue(LEG4, leg3);
            case CLOCKWISE_180 -> state.setValue(FACING, rot.rotate(state.getValue(FACING))).setValue(LEG1, leg3).setValue(LEG2, leg4).setValue(LEG3, leg1).setValue(LEG4, leg2);
            case COUNTERCLOCKWISE_90 -> state.setValue(FACING, rot.rotate(state.getValue(FACING))).setValue(LEG1, leg2).setValue(LEG2, leg3).setValue(LEG3, leg4).setValue(LEG4, leg1);
        };
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}
