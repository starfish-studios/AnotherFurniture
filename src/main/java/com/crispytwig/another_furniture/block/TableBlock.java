package com.crispytwig.another_furniture.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TableBlock extends BaseBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty SHAPE = IntegerProperty.create("shape", 0, 15);
    public static final IntegerProperty CONNECT = IntegerProperty.create("connect", 0, 4);

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
        registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(SHAPE, 15));
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext ctx) {
        return SHAPES[state.getValue(SHAPE)];
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos newPos) {
        boolean n = level.getBlockState(pos.north()).getBlock() instanceof TableBlock;
        boolean e = level.getBlockState(pos.east()).getBlock() instanceof TableBlock;
        boolean s = level.getBlockState(pos.south()).getBlock() instanceof TableBlock;
        boolean w = level.getBlockState(pos.west()).getBlock() instanceof TableBlock;
        int connect = 0;
        if (n) connect += 1;
        if (e) connect += 1;
        if (s) connect += 1;
        if (w) connect += 1;
        int shape = 0;
        if ((!n && !e) || (n && e && !(level.getBlockState(pos.north().east()).getBlock() instanceof TableBlock))) shape += 1;
        if ((!e && !s) || (e && s && !(level.getBlockState(pos.south().east()).getBlock() instanceof TableBlock))) shape += 2;
        if ((!s && !w) || (s && w && !(level.getBlockState(pos.south().west()).getBlock() instanceof TableBlock))) shape += 4;
        if ((!n && !w) || (n && w && !(level.getBlockState(pos.north().west()).getBlock() instanceof TableBlock))) shape += 8;
        return state.setValue(SHAPE, shape).setValue(CONNECT, connect);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SHAPE, CONNECT);
    }
}
