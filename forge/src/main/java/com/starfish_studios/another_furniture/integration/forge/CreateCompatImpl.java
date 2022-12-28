package com.starfish_studios.another_furniture.integration.forge;

import com.simibubi.create.AllInteractionBehaviours;
import com.starfish_studios.another_furniture.registry.AFBlockTags;

public class CreateCompatImpl {

    public static void setup() {

        ShutterMovingInteraction shutterBehaviour = new ShutterMovingInteraction();
        AllInteractionBehaviours.registerBehaviourProvider(state -> {
            if (state.is(AFBlockTags.SHUTTERS)) {
                return shutterBehaviour;
            }
            return null;
        });
    }
}
