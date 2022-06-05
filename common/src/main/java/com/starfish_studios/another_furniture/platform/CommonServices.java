package com.starfish_studios.another_furniture.platform;

import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.platform.services.CommonPlatformHelper;

import java.util.ServiceLoader;

public class CommonServices {
    public static final CommonPlatformHelper HELPER = load(CommonPlatformHelper.class);

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        AnotherFurniture.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
