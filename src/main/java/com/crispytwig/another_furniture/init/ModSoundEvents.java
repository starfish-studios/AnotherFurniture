package com.crispytwig.another_furniture.init;

import com.crispytwig.another_furniture.AnotherFurnitureMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, AnotherFurnitureMod.MOD_ID);

    public static final RegistryObject<SoundEvent> SERVICE_BELL = register("block.service_bell.use");

    public static RegistryObject<SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(AnotherFurnitureMod.MOD_ID, name)));
    }
}
