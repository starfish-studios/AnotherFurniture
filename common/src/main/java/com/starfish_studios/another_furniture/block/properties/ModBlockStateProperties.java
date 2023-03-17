package com.starfish_studios.another_furniture.block.properties;

import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockStateProperties {
    public static final EnumProperty<HorizontalConnectionType> HORIZONTAL_CONNECTION_TYPE = EnumProperty.create("type", HorizontalConnectionType.class);
    public static final EnumProperty<HorizontalConnectionType> HORIZONTAL_CONNECTION_TYPE_1 = EnumProperty.create("type_1", HorizontalConnectionType.class);
    public static final EnumProperty<HorizontalConnectionType> HORIZONTAL_CONNECTION_TYPE_2 = EnumProperty.create("type_2", HorizontalConnectionType.class);
    public static final EnumProperty<VerticalConnectionType> VERTICAL_CONNECTION_TYPE = EnumProperty.create("type", VerticalConnectionType.class);
    public static final EnumProperty<DyeColor> COLOR = EnumProperty.create("color", DyeColor.class);
    public static final EnumProperty<ShutterType> SHUTTER_TYPE = EnumProperty.create("type", ShutterType.class);
    public static final EnumProperty<SofaType> SOFA_TYPE = EnumProperty.create("type", SofaType.class);
    public static final DirectionProperty FACING_EXCEPT_DOWN = DirectionProperty.create("facing", (direction) -> direction != Direction.DOWN);
    public static final DirectionProperty FACING_VERTICAL = DirectionProperty.create("facing_vertical", (direction) -> direction.getAxis().isVertical());
    public static final IntegerProperty LEVEL_1_3 = IntegerProperty.create("level", 1, 3);
    public static final BooleanProperty BACK = BooleanProperty.create("back");
    public static final BooleanProperty BASE = BooleanProperty.create("base");
}