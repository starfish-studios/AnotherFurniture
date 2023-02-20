package com.starfish_studios.another_furniture.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TallStoolBlock extends StoolBlock {
    protected static final VoxelShape AABB = Shapes.or(Block.box(4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D), Block.box(2.0D, 12.0D, 2.0D, 14.0D, 16.0D, 14.0D));

    public TallStoolBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }

    @Override
    public float seatHeight() {
        return 0.7F;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AABB;
    }


}
