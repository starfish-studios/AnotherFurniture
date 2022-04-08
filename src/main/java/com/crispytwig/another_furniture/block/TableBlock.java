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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TableBlock extends BaseBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    // truly, gomenasorry
    public static final BooleanProperty N = BooleanProperty.create("n");
    public static final BooleanProperty E = BooleanProperty.create("e");
    public static final BooleanProperty S = BooleanProperty.create("s");
    public static final BooleanProperty W = BooleanProperty.create("w");
    public static final BooleanProperty NE = BooleanProperty.create("ne");
    public static final BooleanProperty SE = BooleanProperty.create("se");
    public static final BooleanProperty SW = BooleanProperty.create("sw");
    public static final BooleanProperty NW = BooleanProperty.create("nw");

    protected static final VoxelShape TOP = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape LEG_1 = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 14.0D, 2.0D);
    protected static final VoxelShape LEG_2 = Block.box(14.0D, 0.0D, 14.0D, 16.0D, 14.0D, 16.0D);
    protected static final VoxelShape LEG_3 = Block.box(0.0D, 0.0D, 14.0D, 2.0D, 14.0D, 16.0D);
    protected static final VoxelShape LEG_4 = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 14.0D, 2.0D);
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{TOP, Shapes.or(TOP, LEG_1), Shapes.or(TOP, LEG_2), Shapes.or(TOP, LEG_1, LEG_2),
            Shapes.or(TOP, LEG_3), Shapes.or(TOP, LEG_1, LEG_3), Shapes.or(TOP, LEG_2, LEG_3), Shapes.or(TOP, LEG_1, LEG_2, LEG_3), Shapes.or(TOP, LEG_4),
            Shapes.or(TOP, LEG_1, LEG_4), Shapes.or(TOP, LEG_2, LEG_4), Shapes.or(TOP, LEG_1, LEG_2, LEG_4), Shapes.or(TOP, LEG_3, LEG_4),
            Shapes.or(TOP, LEG_1, LEG_3, LEG_4), Shapes.or(TOP, LEG_2, LEG_3, LEG_4), Shapes.or(TOP, LEG_1, LEG_2, LEG_3, LEG_4)
    };

    public TableBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH)
                .setValue(N, false).setValue(E,false).setValue(S,false).setValue(W,false)
                .setValue(NE, false).setValue(SE, false).setValue(SW, false).setValue(NW, false));
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext ctx) {
        int a = 0;
        boolean n = state.getValue(N);
        boolean e = state.getValue(E);
        boolean s = state.getValue(S);
        boolean w = state.getValue(W);
        boolean ne = state.getValue(NE);
        boolean se = state.getValue(SE);
        boolean sw = state.getValue(SW);
        boolean nw = state.getValue(NW);
        if ((!n && !e) || (n && e && !ne)) a += 1;
        if ((!e && !s) || (e && s && !se)) a += 2;
        if ((!s && !w) || (s && w && !sw)) a += 4;
        if ((!n && !w) || (n && w && !nw)) a += 8;
        return SHAPES[a];
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos newPos) {
        boolean n = level.getBlockState(pos.north()).getBlock() instanceof TableBlock;
        boolean e = level.getBlockState(pos.east()).getBlock() instanceof TableBlock;
        boolean s = level.getBlockState(pos.south()).getBlock() instanceof TableBlock;
        boolean w = level.getBlockState(pos.west()).getBlock() instanceof TableBlock;
        boolean ne = false;
        boolean se = false;
        boolean sw = false;
        boolean nw = false;
        if (n) {
            nw = level.getBlockState(pos.north()).getValue(W);
            ne = level.getBlockState(pos.north()).getValue(E);
        }
        if (e) {
            ne = level.getBlockState(pos.east()).getValue(N);
            se = level.getBlockState(pos.east()).getValue(S);
        }
        if (s) {
            se = level.getBlockState(pos.south()).getValue(E);
            sw = level.getBlockState(pos.south()).getValue(W);
        }
        if (w) {
            sw = level.getBlockState(pos.west()).getValue(S);
            nw = level.getBlockState(pos.west()).getValue(N);
        }
        return state.setValue(N, n).setValue(E, e).setValue(S, s).setValue(W, w)
                .setValue(NE, ne).setValue(SE, se).setValue(SW, sw).setValue(NW, nw);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(N);
        builder.add(E);
        builder.add(S);
        builder.add(W);
        builder.add(NE);
        builder.add(SE);
        builder.add(SW);
        builder.add(NW);
    }
}
