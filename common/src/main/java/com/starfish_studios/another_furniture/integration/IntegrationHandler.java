package com.starfish_studios.another_furniture.integration;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class IntegrationHandler {

    private IntegrationHandler() {}

    @ExpectPlatform
    public static void init() {
        throw new AssertionError();
    }
}
