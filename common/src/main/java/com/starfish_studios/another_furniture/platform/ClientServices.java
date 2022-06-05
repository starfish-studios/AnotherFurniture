package com.starfish_studios.another_furniture.platform;

import com.starfish_studios.another_furniture.platform.services.ClientPlatformHelper;

public class ClientServices {
    public static final ClientPlatformHelper HELPER = CommonServices.load(ClientPlatformHelper.class);
}
