package com.rsa.bingo.domain.models;

import lombok.Getter;

@Getter
public enum Color {

    AQUA ("AQUA", new int[] {0, 0, 0}),
    BLACK ("BLACK", new int[] {0, 0, 0}),
    BLUE ("BLUE", new int[] {0, 0, 0}),
    BLUE_GREY ("BLUE_GREY", new int[] {0, 0, 0}),
    BRIGHT_GREEN ("BRIGHT_GREEN", new int[] {0, 0, 0}),
    BROWN ("BROWN", new int[] {0, 0, 0}),
    CORAL ("CORAL", new int[] {0, 0, 0}),
    CORNFLOWER_BLUE ("CORNFLOWER_BLUE", new int[] {0, 0, 0}),
    DARK_BLUE ("DARK_BLUE", new int[] {0, 0, 0}),
    DARK_GREEN ("DARK_GREEN", new int[] {0, 0, 0}),
    DARK_RED ("DARK_RED", new int[] {0, 0, 0}),
    DARK_TEAL ("DARK_TEAL", new int[] {0, 0, 0}),
    DARK_YELLOW ("DARK_YELLOW", new int[] {0, 0, 0}),
    GOLD ("GOLD", new int[] {0, 0, 0}),
    GREEN ("GREEN", new int[] {0, 0, 0}),
    GREY_25_PERCENT ("GREY_25_PERCENT", new int[] {0, 0, 0}),
    GREY_40_PERCENT ("GREY_40_PERCENT", new int[] {0, 0, 0}),
    GREY_50_PERCENT ("GREY_50_PERCENT", new int[] {0, 0, 0}),
    GREY_80_PERCENT ("GREY_80_PERCENT", new int[] {0, 0, 0}),
    INDIGO ("INDIGO", new int[] {0, 0, 0}),
    LAVENDER ("LAVENDER", new int[] {0, 0, 0}),
    LEMON_CHIFFON ("LEMON_CHIFFON", new int[] {0, 0, 0}),
    LIGHT_BLUE ("LIGHT_BLUE", new int[] {0, 0, 0}),
    LIGHT_CORNFLOWER_BLUE ("LIGHT_CORNFLOWER_BLUE", new int[] {0, 0, 0}),
    LIGHT_GREEN ("LIGHT_GREEN", new int[] {0, 0, 0}),
    LIGHT_ORANGE ("LIGHT_ORANGE", new int[] {0, 0, 0}),
    LIGHT_TURQUOISE ("LIGHT_TURQUOISE", new int[] {0, 0, 0}),
    LIGHT_YELLOW ("LIGHT_YELLOW", new int[] {0, 0, 0}),
    LIME ("LIME", new int[] {0, 0, 0}),
    MAROON ("MAROON", new int[] {0, 0, 0}),
    OLIVE_GREEN ("OLIVE_GREEN", new int[] {0, 0, 0}),
    ORANGE ("ORANGE", new int[] {0, 0, 0}),
    ORCHID ("ORCHID", new int[] {0, 0, 0}),
    PALE_BLUE ("PALE_BLUE", new int[] {0, 0, 0}),
    PINK ("PINK", new int[] {0, 0, 0}),
    PLUM ("PLUM", new int[] {0, 0, 0}),
    RED ("RED", new int[] {0, 0, 0}),
    ROSE ("ROSE", new int[] {0, 0, 0}),
    ROYAL_BLUE ("ROYAL_BLUE", new int[] {0, 0, 0}),
    SEA_GREEN ("SEA_GREEN", new int[] {0, 0, 0}),
    SKY_BLUE ("SKY_BLUE", new int[] {0, 0, 0}),
    TAN ("TAN", new int[] {0, 0, 0}),
    TEAL ("TEAL", new int[] {0, 0, 0}),
    TURQUOISE ("TURQUOISE", new int[] {0, 0, 0}),
    VIOLET ("VIOLET", new int[] {0, 0, 0}),
    WHITE ("WHITE", new int[] {0, 0, 0}),
    YELLOW ("YELLOW", new int[] {0, 0, 0});

    private final String name;

    private final int[] rgb;

    Color(String name, int[] rgb) {
        this.name = name;
        this.rgb = rgb;
    }
}
