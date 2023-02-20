package com.starfish_studios.another_furniture.integration.forge;

import com.simibubi.create.AllInteractionBehaviours;
import com.simibubi.create.AllMovementBehaviours;
import com.starfish_studios.another_furniture.block.SeatBlock;
import com.starfish_studios.another_furniture.registry.AFBlockTags;

public class CreateCompatImpl {
    public static void setup() {
        AFSeatMovementBehaviour movementBehaviour = new AFSeatMovementBehaviour();
        AFSeatInteractionBehaviour interactionBehaviour = new AFSeatInteractionBehaviour();

        AllMovementBehaviours.registerBehaviourProvider(state -> {
            if (state.getBlock() instanceof SeatBlock) {
                return movementBehaviour;
            }
            return null;
        });

        AllInteractionBehaviours.registerBehaviourProvider(state -> {
            if (state.getBlock() instanceof SeatBlock) {
                return interactionBehaviour;
            }
            return null;
        });

        ShutterMovingInteraction shutterBehaviour = new ShutterMovingInteraction();
        AllInteractionBehaviours.registerBehaviourProvider(state -> {
            if (state.is(AFBlockTags.SHUTTERS)) {
                return shutterBehaviour;
            }
            return null;
        });
    }
}
