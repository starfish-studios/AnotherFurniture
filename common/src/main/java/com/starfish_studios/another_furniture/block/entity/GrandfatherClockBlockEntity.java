package com.starfish_studios.another_furniture.block.entity;

//import com.starfish_studios.another_furniture.registry.AFBlockEntityTypes;
//import dev.architectury.injectables.annotations.PlatformOnly;
//import net.minecraft.core.BlockPos;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.util.Mth;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.phys.AABB;
//import org.jetbrains.annotations.NotNull;
//
//public class GrandfatherClockBlockEntity extends BlockEntity {
//    private float minuteRoll = 0;
//    private float hourRoll = 0;
//    private float minuteRollPre = 0;
//    private float hourRollPre = 0;
//
//    public GrandfatherClockBlockEntity(BlockPos blockPos, BlockState blockState) {
//        super(AFBlockEntityTypes.GRANDFATHER_CLOCK.get(), blockPos, blockState);
//    }
//
//    public static void tick(Level level, BlockPos pos, BlockState state, GrandfatherClockBlockEntity blockEntity) {
//        long gameTime = level.getGameTime();
//        if (level.dimensionType().natural()) {
//            if (gameTime % 20 == 0) {
//                if (level.dimensionType().natural()) {
//                    blockEntity.updateTime(level, state, pos);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void load(@NotNull CompoundTag tag) {
//        super.load(tag);
//        this.hourRoll = tag.getFloat("HourRoll");
//        this.hourRollPre = this.hourRoll;
//        //this.targetRoll = this.roll;
//
//        this.minuteRoll = tag.getFloat("MinuteRoll");
//        this.minuteRollPre = this.minuteRoll;
//        //this.sTargetRoll = this.sRoll;
//    }
//
//    @Override
//    public void saveAdditional(CompoundTag tag) {
//        super.saveAdditional(tag);
//        tag.putFloat("HourRoll", this.hourRoll);
//        tag.putFloat("MinuteRoll", this.minuteRoll);
//    }
//
//    public void updateTime(Level level, BlockState state, BlockPos pos) {
//        long timeTicks = (int) (level.getDayTime() % 24000);
//
//
//        float minute = Mth.clamp((timeTicks % 1000) / 20, 0, 50);
//        this.minuteRoll = ((minute * 7.2f + 180) % 360f) + 180f;
//
//
//        float hour = Mth.clamp(timeTicks / 1000, 0, 24);
//        this.hourRoll = (hour * 30) % 360;
//    }
//
//    public float getHourRoll(float partialTicks) {
//        return this.hourRoll;
//    }
//
//    public float getMinuteRoll(float partialTicks) {
//        return this.minuteRoll;
//    }
//
//    @PlatformOnly(PlatformOnly.FORGE)
//    public AABB getRenderBoundingBox() {
//        return new AABB(worldPosition.offset(0, 1, 0), worldPosition.offset(1, -1, 1));
//    }
//}
