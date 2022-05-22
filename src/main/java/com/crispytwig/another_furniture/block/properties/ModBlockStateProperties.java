package com.crispytwig.another_furniture.block.properties;

import net.minecraft.world.level.block.state.properties.EnumProperty;

public class ModBlockStateProperties {
    public static final EnumProperty<ShelfType> SHELF_TYPE = EnumProperty.create("type", ShelfType.class);
    public static final EnumProperty<ShutterType> SHUTTER_TYPE = EnumProperty.create("type", ShutterType.class);
    public static final EnumProperty<CurtainType> CURTAIN_TYPE = EnumProperty.create("type", CurtainType.class);
}