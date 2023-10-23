package com.starfish_studios.another_furniture.mixin.fabric;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public final class ConditionalMixinPlugin implements IMixinConfigPlugin {
    private static final Supplier<Boolean> TRUE = () -> true;

    private static final Map<String, Supplier<Boolean>> CONDITIONS = ImmutableMap.of(
            "com.starfish_studios.another_furniture.mixin.fabric.create.ContraptionMixin", () -> FabricLoader.getInstance().isModLoaded("create"),
            "com.starfish_studios.another_furniture.mixin.fabric.create.AbstractContraptionEntityMixin", () -> FabricLoader.getInstance().isModLoaded("create"),
            "com.starfish_studios.another_furniture.mixin.fabric.create.BlockMovementChecksMixin", () -> FabricLoader.getInstance().isModLoaded("create")
    );

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return CONDITIONS.getOrDefault(mixinClassName, TRUE).get();
    }

    // Boilerplate

    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}