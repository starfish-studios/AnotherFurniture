package com.starfish_studios.another_furniture.block.properties;

import net.minecraft.util.StringRepresentable;

public enum HorizontalConnectionType implements StringRepresentable {
    SINGLE("single"),
    LEFT("left"),
    MIDDLE("middle"),
    RIGHT("right");

    private final String name;

    private HorizontalConnectionType(String type) {
        this.name = type;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}