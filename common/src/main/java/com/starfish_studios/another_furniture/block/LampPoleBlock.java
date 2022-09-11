package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.properties.ColorType;
import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LampPoleBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<ColorType> COLOR = ModBlockStateProperties.COLOR;

    protected static final VoxelShape AABB = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);

    public LampPoleBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(COLOR, ColorType.WHITE)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AABB;
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            BlockState up_state = level.getBlockState(pos.above());
            BlockState down_state = level.getBlockState(pos.below());
            boolean up_connect = (up_state.getBlock() instanceof LampBlock && up_state.getValue(LampBlock.FACING) == Direction.UP);
            boolean down_connect = down_state.getBlock() instanceof LampBlock;
            if (!down_connect || !up_connect) {
                level.setBlock(pos, getLamp(state.getValue(COLOR)).defaultBlockState(), 3);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, COLOR);
    }

    public static Block getLamp(ColorType color) {
        return switch (color) {
            case WHITE -> AFBlocks.WHITE_LAMP.get();
            case ORANGE -> AFBlocks.ORANGE_LAMP.get();
            case MAGENTA -> AFBlocks.MAGENTA_LAMP.get();
            case LIGHT_BLUE -> AFBlocks.LIGHT_BLUE_LAMP.get();
            case YELLOW -> AFBlocks.YELLOW_LAMP.get();
            case LIME -> AFBlocks.LIME_LAMP.get();
            case PINK -> AFBlocks.PINK_LAMP.get();
            case GRAY -> AFBlocks.GRAY_LAMP.get();
            case LIGHT_GRAY -> AFBlocks.LIGHT_GRAY_LAMP.get();
            case CYAN -> AFBlocks.CYAN_LAMP.get();
            case PURPLE -> AFBlocks.PURPLE_LAMP.get();
            case BLUE -> AFBlocks.BLUE_LAMP.get();
            case BROWN -> AFBlocks.BROWN_LAMP.get();
            case GREEN -> AFBlocks.GREEN_LAMP.get();
            case RED -> AFBlocks.RED_LAMP.get();
            case BLACK -> AFBlocks.BLACK_LAMP.get();
        };
    }
}