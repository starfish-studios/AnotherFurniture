package com.starfish_studios.another_furniture.integration;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class CreateCompat {
    private CreateCompat() {}

    @ExpectPlatform
    public static void setup() {
        throw new AssertionError();
    }



}
