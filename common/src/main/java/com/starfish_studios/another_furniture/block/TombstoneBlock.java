package com.starfish_studios.another_furniture.block;

////import com.starfish_studios.another_furniture.block.entity.TombstoneBlockEntity;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.context.BlockPlaceContext;
//import net.minecraft.world.level.BlockGetter;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.LevelAccessor;
//import net.minecraft.world.level.block.*;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.block.state.StateDefinition;
//import net.minecraft.world.level.block.state.properties.BlockStateProperties;
//import net.minecraft.world.level.block.state.properties.BooleanProperty;
//import net.minecraft.world.level.block.state.properties.DirectionProperty;
//import net.minecraft.world.level.material.FluidState;
//import net.minecraft.world.level.material.Fluids;
//import net.minecraft.world.level.pathfinder.PathComputationType;
//import net.minecraft.world.phys.BlockHitResult;
//import net.minecraft.world.phys.shapes.CollisionContext;
//import net.minecraft.world.phys.shapes.Shapes;
//import net.minecraft.world.phys.shapes.VoxelShape;
//import org.jetbrains.annotations.Nullable;
//
//public class TombstoneBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
//    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
//    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
//
//    protected static final VoxelShape AABB_X = Shapes.or(Block.box(4.0D, 0.0D, 1.0D, 12.0D, 2.0D, 15.0D), Block.box(6.0D, 2.0D, 2.0D, 10.0D, 16.0D, 14.0D));
//    protected static final VoxelShape AABB_Z = Shapes.or(Block.box(1.0D, 0.0D, 4.0D, 15.0D, 2.0D, 12.0D), Block.box(2.0D, 2.0D, 6.0D, 14.0D, 16.0D, 10.0D));
//
//    public TombstoneBlock(Properties properties) {
//        super(properties);
//        registerDefaultState(this.stateDefinition.any()
//                .setValue(FACING, Direction.NORTH)
//                .setValue(WATERLOGGED, false)
//        );
//    }
//
//    @Override
//    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
//        return state.getValue(FACING).getAxis() == Direction.Axis.X ? AABB_X : AABB_Z;
//    }
//
//    @Nullable
//    @Override
//    public BlockState getStateForPlacement(BlockPlaceContext context) {
//        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
//        return this.defaultBlockState()
//                .setValue(FACING, context.getHorizontalDirection().getOpposite())
//                .setValue(WATERLOGGED, waterlogged);
//    }
//
//    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
//        if (state.getValue(WATERLOGGED)) {
//            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
//        }
//
//        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
//    }
//
//    @Override
//    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
//        return super.use(state, level, pos, player, hand, hit);
//    }
//
//    public RenderShape getRenderShape(BlockState state) {
//        return RenderShape.MODEL;
//    }
//
//    public FluidState getFluidState(BlockState state) {
//        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
//    }
//
//    //@Nullable
//    //@Override
//    //public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
//    //    return new TombstoneBlockEntity(pos, state);
//    //}
//
//    @Override
//    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
//        builder.add(FACING, WATERLOGGED);
//    }
//
//    @Override
//    public BlockState rotate(BlockState state, Rotation rotation) {
//        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
//    }
//
//    @Override
//    public BlockState mirror(BlockState state, Mirror mirror) {
//        return state.rotate(mirror.getRotation(state.getValue(FACING)));
//    }
//
//    @Override
//    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
//        return false;
//    }
//}
//
