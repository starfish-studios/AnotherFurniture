package com.starfish_studios.another_furniture.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.RailShape;

public class PathBlock extends RailBlock {
    public static final EnumProperty<RailShape> SHAPE;

    public PathBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(SHAPE, RailShape.NORTH_SOUTH)).setValue(WATERLOGGED, false));
    }

    protected void updateState(BlockState state, Level level, BlockPos pos, Block block) {
        if (block.defaultBlockState().isSignalSource() && (new RailState(level, pos, state)).countPotentialConnections() == 3) {
            this.updateDir(level, pos, state, false);
        }

    }

    public Property<RailShape> getShapeProperty() {
        return SHAPE;
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        switch (rotation) {
            case CLOCKWISE_180:
                switch ((RailShape)state.getValue(SHAPE)) {
                    case SOUTH_EAST:
                        return (BlockState)state.setValue(SHAPE, RailShape.NORTH_WEST);
                    case SOUTH_WEST:
                        return (BlockState)state.setValue(SHAPE, RailShape.NORTH_EAST);
                    case NORTH_WEST:
                        return (BlockState)state.setValue(SHAPE, RailShape.SOUTH_EAST);
                    case NORTH_EAST:
                        return (BlockState)state.setValue(SHAPE, RailShape.SOUTH_WEST);
                }
            case COUNTERCLOCKWISE_90:
                switch ((RailShape)state.getValue(SHAPE)) {
                    case SOUTH_EAST:
                        return (BlockState)state.setValue(SHAPE, RailShape.NORTH_EAST);
                    case SOUTH_WEST:
                        return (BlockState)state.setValue(SHAPE, RailShape.SOUTH_EAST);
                    case NORTH_WEST:
                        return (BlockState)state.setValue(SHAPE, RailShape.SOUTH_WEST);
                    case NORTH_EAST:
                        return (BlockState)state.setValue(SHAPE, RailShape.NORTH_WEST);
                    case NORTH_SOUTH:
                        return (BlockState)state.setValue(SHAPE, RailShape.EAST_WEST);
                    case EAST_WEST:
                        return (BlockState)state.setValue(SHAPE, RailShape.NORTH_SOUTH);
                }
            case CLOCKWISE_90:
                switch ((RailShape)state.getValue(SHAPE)) {
                    case SOUTH_EAST:
                        return (BlockState)state.setValue(SHAPE, RailShape.SOUTH_WEST);
                    case SOUTH_WEST:
                        return (BlockState)state.setValue(SHAPE, RailShape.NORTH_WEST);
                    case NORTH_WEST:
                        return (BlockState)state.setValue(SHAPE, RailShape.NORTH_EAST);
                    case NORTH_EAST:
                        return (BlockState)state.setValue(SHAPE, RailShape.SOUTH_EAST);
                    case NORTH_SOUTH:
                        return (BlockState)state.setValue(SHAPE, RailShape.EAST_WEST);
                    case EAST_WEST:
                        return (BlockState)state.setValue(SHAPE, RailShape.NORTH_SOUTH);
                }
            default:
                return state;
        }
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        RailShape railShape = (RailShape)state.getValue(SHAPE);
        switch (mirror) {
            case LEFT_RIGHT:
                switch (railShape) {
                    case SOUTH_EAST:
                        return (BlockState)state.setValue(SHAPE, RailShape.NORTH_EAST);
                    case SOUTH_WEST:
                        return (BlockState)state.setValue(SHAPE, RailShape.NORTH_WEST);
                    case NORTH_WEST:
                        return (BlockState)state.setValue(SHAPE, RailShape.SOUTH_WEST);
                    case NORTH_EAST:
                        return (BlockState)state.setValue(SHAPE, RailShape.SOUTH_EAST);
                    default:
                        return super.mirror(state, mirror);
                }
            case FRONT_BACK:
                switch (railShape) {
                    default:
                        break;
                    case SOUTH_EAST:
                        return (BlockState)state.setValue(SHAPE, RailShape.SOUTH_WEST);
                    case SOUTH_WEST:
                        return (BlockState)state.setValue(SHAPE, RailShape.SOUTH_EAST);
                    case NORTH_WEST:
                        return (BlockState)state.setValue(SHAPE, RailShape.NORTH_EAST);
                    case NORTH_EAST:
                        return (BlockState)state.setValue(SHAPE, RailShape.NORTH_WEST);
                }
        }

        return super.mirror(state, mirror);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, WATERLOGGED);
    }

    static {
        SHAPE = BlockStateProperties.RAIL_SHAPE;
    }
}