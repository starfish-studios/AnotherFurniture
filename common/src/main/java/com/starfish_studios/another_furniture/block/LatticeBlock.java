package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.properties.ShutterType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class LatticeBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty LEFTBAR = BooleanProperty.create("left_bar");
    public static final BooleanProperty RIGHTBAR = BooleanProperty.create("right_bar");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape EAST = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    protected static final VoxelShape NORTH = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{SOUTH, WEST, NORTH, EAST};


    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public LatticeBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(LEFTBAR, true)
                .setValue(RIGHTBAR, true)
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int shape = state.getValue(FACING).get2DDataValue();
        return SHAPES[shape % 4];
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        boolean e = level.getBlockState(currentPos.east()).getBlock() instanceof LatticeBlock;
        boolean w = level.getBlockState(currentPos.west()).getBlock() instanceof LatticeBlock;
        boolean leftbar = (!w) || (w && !(level.getBlockState(currentPos.west()).getBlock() instanceof LatticeBlock));
        boolean rightbar = (!e) || (e && !(level.getBlockState(currentPos.east()).getBlock() instanceof LatticeBlock));
        return state.setValue(LEFTBAR, leftbar).setValue(RIGHTBAR, rightbar);
        // "UPDATE" is not important here since it's only for corner blocks.
    }


    // I tried making the block climbable, but "canSurvive" is deprecated and does not work. Extending "LadderBlock" does not work
    // because it was having issues with "getPlacementState".

/*    private boolean canAttachTo(BlockGetter blockReader, BlockPos pos, Direction direction) {
        BlockState blockState = blockReader.getBlockState(pos);
        return blockState.isFaceSturdy(blockReader, pos, direction);
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = (Direction)state.getValue(FACING);
        return this.canAttachTo(level, pos.relative(direction.getOpposite()), direction);
    }
*/

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEFTBAR, RIGHTBAR, WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
