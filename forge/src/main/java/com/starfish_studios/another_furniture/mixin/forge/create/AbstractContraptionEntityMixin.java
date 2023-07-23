//package com.starfish_studios.another_furniture.mixin.forge.create;
//
//import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
//import com.simibubi.create.content.contraptions.Contraption;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//
//@Mixin(AbstractContraptionEntity.class)
//public abstract class AbstractContraptionEntityMixin {
//
//    @Shadow
//    Contraption contraption;
//
//    @Inject(method = "m_19956_", at = @At(value = "RETURN"))
//    private void af$positionRider() {
//        System.out.println(21234);
////        BlockPos localPos = this.contraption.getSeatOf(passenger.getUUID());
////        if (this.contraption != null && this.contraption.getBlocks().containsKey(localPos)) {
////            BlockState sittingBlock = this.contraption.getBlocks().get(localPos).state;
////            if (sittingBlock.getBlock() instanceof SeatBlock seatBlock) {
////                return seatBlock.seatHeight(sittingBlock) - 0.225;
////            }
////        }
////        return entity.getMyRidingOffset();
//    }
//}
