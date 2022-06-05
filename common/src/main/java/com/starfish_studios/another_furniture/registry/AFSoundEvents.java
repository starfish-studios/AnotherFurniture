package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.platform.CommonServices;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class AFSoundEvents {
    public static final Supplier<SoundEvent> SERVICE_BELL = register("block.service_bell.use");
    public static final Supplier<SoundEvent> CHAIR_TUCK = register("block.chair.tuck");
    public static final Supplier<SoundEvent> CHAIR_UNTUCK = register("block.chair.untuck");
    public static final Supplier<SoundEvent> CURTAIN = register("block.curtain.use");

    public static Supplier<SoundEvent> register(String name) {
        return CommonServices.HELPER.registerSoundEvent(name, () -> new SoundEvent(new ResourceLocation(AnotherFurniture.MOD_ID, name)));
    }

    public static void init() {}
}
