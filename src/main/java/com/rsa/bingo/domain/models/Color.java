package com.rsa.bingo.domain.models;

import lombok.Getter;

@Getter
public enum Color {

    AQUA ("AQUA", new int[] {50, 205, 203}),
    BLACK ("BLACK", new int[] {0, 0, 0}),
    BLUE ("BLUE", new int[] {24, 0, 254}),
    BLUE_GREY ("BLUE_GREY", new int[] {101, 103, 153}),
    BRIGHT_GREEN ("BRIGHT_GREEN", new int[] {1, 254, 0}),
    BROWN ("BROWN", new int[] {152, 50, 0}),
    CORAL ("CORAL", new int[] {255, 127, 127}),
    CORNFLOWER_BLUE ("CORNFLOWER_BLUE", new int[] {152, 152, 255}),
    DARK_BLUE ("DARK_BLUE", new int[] {6, 0, 127}),
    DARK_GREEN ("DARK_GREEN", new int[] {0, 50, 0}),
    DARK_RED ("DARK_RED", new int[] {128, 0, 0}),
    DARK_TEAL ("DARK_TEAL", new int[] {0, 50, 101}),
    DARK_YELLOW ("DARK_YELLOW", new int[] {128, 127, 0}),
    GOLD ("GOLD", new int[] {255, 204, 1}),
    GREEN ("GREEN", new int[] {0, 127, 1}),
    GREY_25_PERCENT ("GREY_25_PERCENT", new int[] {191, 192, 192}),
    GREY_40_PERCENT ("GREY_40_PERCENT", new int[] {150, 150, 150}),
    GREY_50_PERCENT ("GREY_50_PERCENT", new int[] {128, 127, 127}),
    GREY_80_PERCENT ("GREY_80_PERCENT", new int[] {50, 51, 51}),
    INDIGO ("INDIGO", new int[] {50, 50, 153}),
    LAVENDER ("LAVENDER", new int[] {204, 153, 254}),
    LEMON_CHIFFON ("LEMON_CHIFFON", new int[] {252, 255, 203}),
    LIGHT_BLUE ("LIGHT_BLUE", new int[] {51, 102, 255}),
    LIGHT_CORNFLOWER_BLUE ("LIGHT_CORNFLOWER_BLUE", new int[] {205, 202, 255}),
    LIGHT_GREEN ("LIGHT_GREEN", new int[] {205, 255, 204}),
    LIGHT_ORANGE ("LIGHT_ORANGE", new int[] {254, 153, 0}),
    LIGHT_TURQUOISE ("LIGHT_TURQUOISE", new int[] {203, 254, 255}),
    LIGHT_YELLOW ("LIGHT_YELLOW", new int[] {254, 255, 153}),
    LIME ("LIME", new int[] {154, 204, 0}),
    MAROON ("MAROON", new int[] {153, 51, 101}),
    OLIVE_GREEN ("OLIVE_GREEN", new int[] {50, 53, 0}),
    ORANGE ("ORANGE", new int[] {255, 101, 1}),
    ORCHID ("ORCHID", new int[] {98, 2, 100}),
    PALE_BLUE ("PALE_BLUE", new int[] {152, 204, 254}),
    PINK ("PINK", new int[] {255, 0, 253}),
    PLUM ("PLUM", new int[] {153, 49, 105}),
    RED ("RED", new int[] {255, 1, 1}),
    ROSE ("ROSE", new int[] {255, 153, 203}),
    ROYAL_BLUE ("ROYAL_BLUE", new int[] {4, 101, 204}),
    SEA_GREEN ("SEA_GREEN", new int[] {51, 153, 103}),
    SKY_BLUE ("SKY_BLUE", new int[] {0, 204, 254}),
    TAN ("TAN", new int[] {254, 204, 153}),
    TEAL ("TEAL", new int[] {1, 127, 129}),
    TURQUOISE ("TURQUOISE", new int[] {2, 254, 255}),
    VIOLET ("VIOLET", new int[] {127, 1, 127}),
    WHITE ("WHITE", new int[] {254, 255, 254}),
    YELLOW ("YELLOW", new int[] {254, 255, 3});

    private final String name;

    private final int[] rgb;

    Color(String name, int[] rgb) {
        this.name = name;
        this.rgb = rgb;
    }
}
