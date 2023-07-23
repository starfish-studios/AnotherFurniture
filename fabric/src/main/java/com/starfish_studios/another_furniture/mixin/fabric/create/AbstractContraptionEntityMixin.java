//package com.starfish_studios.another_furniture.mixin.fabric.create;

//import com.simibubi.create.content.contraptions.components.actors.SeatEntity;
//import com.simibubi.create.content.contraptions.components.structureMovement.AbstractContraptionEntity;
//import com.simibubi.create.content.contraptions.components.structureMovement.Contraption;
//import com.starfish_studios.another_furniture.block.SeatBlock;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.level.block.state.BlockState;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Redirect;
//
//@Mixin(AbstractContraptionEntity.class)
//public abstract class AbstractContraptionEntityMixin {
//
//    @Shadow protected Contraption contraption;
//
//    // positionRider(Entity passenger, MoveFunction callback)
//    @Redirect(method = "method_24201", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/contraptions/components/actors/SeatEntity;getCustomEntitySeatOffset(Lnet/minecraft/world/entity/Entity;)D"))
//    private double af$positionRider(Entity entity) {
//        BlockPos localPos = this.contraption.getSeatOf(entity.getUUID());
//        if (this.contraption != null && this.contraption.getBlocks().containsKey(localPos)) {
//            BlockState sittingBlock = this.contraption.getBlocks().get(localPos).state;
//            if (sittingBlock.getBlock() instanceof SeatBlock seatBlock) {
//                return seatBlock.seatHeight() - 0.225;
//            }
//        }
//        return SeatEntity.getCustomEntitySeatOffset(entity);
//    }
//}
