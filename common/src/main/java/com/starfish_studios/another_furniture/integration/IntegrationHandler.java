package com.starfish_studios.another_furniture.integration;

import com.starfish_studios.another_furniture.registry.AFRegistry;

public class IntegrationHandler {

    private IntegrationHandler() {}

    public static void init() {
        if (AFRegistry.isModLoaded("create")) {
            CreateCompat.setup();
        }
    }
}
