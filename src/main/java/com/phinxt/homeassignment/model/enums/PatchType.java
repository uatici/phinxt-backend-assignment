package com.phinxt.homeassignment.model.enums;

import java.util.Arrays;

public enum PatchType {

    DUST("Dust"),
    CRUMB("Crumb"),
    LIQUID("Liquid");
    private final String name;

    PatchType(String name) {
        this.name = name;
    }

    public static PatchType getPatchType(String pathType) {
        return Arrays.stream(PatchType.values())
                .filter(i -> i.name.equals(pathType)).findFirst()
                .orElse(DUST);
    }
}
