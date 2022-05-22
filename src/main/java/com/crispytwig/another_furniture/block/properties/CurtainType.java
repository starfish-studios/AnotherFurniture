package com.crispytwig.another_furniture.block.properties;

import net.minecraft.util.StringRepresentable;

public enum CurtainType implements StringRepresentable {
    LEFT("left"),
    MIDDLE("middle"),
    RIGHT("right");

    private final String name;

    private CurtainType(String type) {
        this.name = type;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}