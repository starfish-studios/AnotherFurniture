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
import org.jetbrains.annotations.Nullable;

public class TableBlock extends BaseBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
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

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        int shape = 0;
        if (pState.getValue(LEG1)) shape += 1;
        if (pState.getValue(LEG2)) shape += 2;
        if (pState.getValue(LEG3)) shape += 4;
        if (pState.getValue(LEG4)) shape += 8;
        return SHAPES[shape];
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        boolean waterlogged = pContext.getLevel().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState()
                .setValue(FACING, pContext.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }
        boolean n = pLevel.getBlockState(pCurrentPos.north()).getBlock() instanceof TableBlock;
        boolean e = pLevel.getBlockState(pCurrentPos.east()).getBlock() instanceof TableBlock;
        boolean s = pLevel.getBlockState(pCurrentPos.south()).getBlock() instanceof TableBlock;
        boolean w = pLevel.getBlockState(pCurrentPos.west()).getBlock() instanceof TableBlock;
        boolean leg1 = (!n && !e) || (n && e && !(pLevel.getBlockState(pCurrentPos.north().east()).getBlock() instanceof TableBlock));
        boolean leg2 = (!e && !s) || (e && s && !(pLevel.getBlockState(pCurrentPos.south().east()).getBlock() instanceof TableBlock));
        boolean leg3 = (!s && !w) || (s && w && !(pLevel.getBlockState(pCurrentPos.south().west()).getBlock() instanceof TableBlock));
        boolean leg4 = (!n && !w) || (n && w && !(pLevel.getBlockState(pCurrentPos.north().west()).getBlock() instanceof TableBlock));
        boolean update = ((n ? 1 : 0) + (e ? 1 : 0) + (s ? 1 : 0) + (w ? 1 : 0)) % 2 == 0;
        return pState.setValue(LEG1, leg1).setValue(LEG2, leg2).setValue(LEG3, leg3).setValue(LEG4, leg4).setValue(UPDATE, update);
    }

    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, LEG1, LEG2, LEG3, LEG4, UPDATE, WATERLOGGED);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        boolean leg1 = pState.getValue(LEG1);
        boolean leg2 = pState.getValue(LEG2);
        boolean leg3 = pState.getValue(LEG3);
        boolean leg4 = pState.getValue(LEG4);
        return switch(pRotation) {
            case NONE -> pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
            case CLOCKWISE_90 -> pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING))).setValue(LEG1, leg4).setValue(LEG2, leg1).setValue(LEG3, leg2).setValue(LEG4, leg3);
            case CLOCKWISE_180 -> pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING))).setValue(LEG1, leg3).setValue(LEG2, leg4).setValue(LEG3, leg1).setValue(LEG4, leg2);
            case COUNTERCLOCKWISE_90 -> pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING))).setValue(LEG1, leg2).setValue(LEG2, leg3).setValue(LEG3, leg4).setValue(LEG4, leg1);
        };
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }
}
