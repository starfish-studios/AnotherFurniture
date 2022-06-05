package com.starfish_studios.another_furniture.block.properties;

import net.minecraft.util.StringRepresentable;

public enum ShutterType implements StringRepresentable {
    TOP("top"),
    MIDDLE("middle"),
    BOTTOM("bottom"),
    NONE("none");

    private final String name;

    private ShutterType(String type) {
        this.name = type;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}