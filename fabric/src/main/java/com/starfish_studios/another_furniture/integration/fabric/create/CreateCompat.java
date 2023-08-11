//package com.starfish_studios.another_furniture.integration.fabric.create;
//
//import com.simibubi.create.AllInteractionBehaviours;
//import com.simibubi.create.AllMovementBehaviours;
//import com.starfish_studios.another_furniture.block.SeatBlock;
//import com.starfish_studios.another_furniture.registry.AFBlockTags;
//
//public class CreateCompat {
//    public static void setup() {
//
//        ShutterMovingInteraction shutterMovingInteraction = new ShutterMovingInteraction();
//        AllInteractionBehaviours.registerBehaviourProvider(state -> {
//            if (state.is(AFBlockTags.SHUTTERS)) {
//                return shutterMovingInteraction;
//            }
//            return null;
//        });
//
//        ShutterMovingBehavior shutterMovingBehavior = new ShutterMovingBehavior();
//        AllMovementBehaviours.registerBehaviourProvider(state -> {
//            if (state.is(AFBlockTags.SHUTTERS)) {
//                return shutterMovingBehavior;
//            }
//            return null;
//        });
//
//        SeatMovementBehavior seatMovementBehavior = new SeatMovementBehavior();
//        AllMovementBehaviours.registerBehaviourProvider(state -> {
//            if (state.getBlock() instanceof SeatBlock) {
//                return seatMovementBehavior;
//            }
//            return null;
//        });
//    }
//}