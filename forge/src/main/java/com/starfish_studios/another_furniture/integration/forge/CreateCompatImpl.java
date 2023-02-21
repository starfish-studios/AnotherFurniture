package com.starfish_studios.another_furniture.integration.forge;

import com.simibubi.create.AllInteractionBehaviours;
import com.simibubi.create.AllMovementBehaviours;
import com.starfish_studios.another_furniture.registry.AFBlockTags;

public class CreateCompatImpl {
    public static void setup() {

        ShutterMovingInteraction shutterMovingInteraction = new ShutterMovingInteraction();
        AllInteractionBehaviours.registerBehaviourProvider(state -> {
            if (state.is(AFBlockTags.SHUTTERS)) {
                return shutterMovingInteraction;
            }
            return null;
        });

        ShutterMovingBehavior shutterMovingBehavior = new ShutterMovingBehavior();
        AllMovementBehaviours.registerBehaviourProvider(state -> {
            if (state.is(AFBlockTags.SHUTTERS)) {
                return shutterMovingBehavior;
            }
            return null;
        });
    }
}