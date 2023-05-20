package com.starfish_studios.another_furniture.integration.forge.create;

import com.simibubi.create.AllInteractionBehaviours;
import com.simibubi.create.AllMovementBehaviours;
import com.simibubi.create.content.contraptions.components.actors.SeatEntity;
import com.simibubi.create.content.contraptions.components.structureMovement.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.components.structureMovement.Contraption;
import com.starfish_studios.another_furniture.registry.AFBlockTags;

public class CreateCompat {
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