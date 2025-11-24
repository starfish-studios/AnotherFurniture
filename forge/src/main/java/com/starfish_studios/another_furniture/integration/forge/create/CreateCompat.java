package com.starfish_studios.another_furniture.integration.forge.create;

import com.simibubi.create.api.behaviour.interaction.MovingInteractionBehaviour;
import com.simibubi.create.api.behaviour.movement.MovementBehaviour;
import com.simibubi.create.api.registry.SimpleRegistry;
import com.starfish_studios.another_furniture.registry.AFBlockTags;

import java.util.List;

public class CreateCompat {
    public static void setup() {

        MovingInteractionBehaviour.REGISTRY.registerProvider(SimpleRegistry.Provider.forBlockTag(AFBlockTags.SHUTTERS, new ShutterMovingInteraction()));

        MovementBehaviour.REGISTRY.registerProvider(SimpleRegistry.Provider.forBlockTag(AFBlockTags.SHUTTERS, new ShutterMovingBehavior()));

        List.of(
                AFBlockTags.BENCHES,
                AFBlockTags.CHAIRS,
                AFBlockTags.SOFAS,
                AFBlockTags.STOOLS,
                AFBlockTags.TALL_STOOLS
        ).forEach(blockTagKey -> {
            MovementBehaviour.REGISTRY.registerProvider(SimpleRegistry.Provider.forBlockTag(blockTagKey, new SeatMovementBehavior()));
        });
    }
}