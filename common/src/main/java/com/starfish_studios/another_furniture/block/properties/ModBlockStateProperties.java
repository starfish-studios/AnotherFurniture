package com.starfish_studios.another_furniture.block.properties;

import net.minecraft.world.level.block.state.properties.EnumProperty;

public class ModBlockStateProperties {
    public static final EnumProperty<HorizontalConnectionType> HORIZONTAL_CONNECTION_TYPE = EnumProperty.create("type", HorizontalConnectionType.class);
    public static final EnumProperty<ShutterType> SHUTTER_TYPE = EnumProperty.create("type", ShutterType.class);
    public static final EnumProperty<CurtainType> CURTAIN_TYPE = EnumProperty.create("type", CurtainType.class);
}