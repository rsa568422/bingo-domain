package com.rsa.bingo.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.regex.Pattern;

@Getter
public final class Colors {

    private static final String RGB_PATTERN = "^rgb\\(\\d{1,3},\\d{1,3},\\d{1,3}\\)$";

    @Setter
    private Integer id;

    @Setter
    private Card card;

    private final String primaryColor;

    private final String secondaryColor;

    public Colors(String primaryColor, String secondaryColor) {
        if (isBadColorString(primaryColor)) {
            throw new VerifyError("Error en el formato del color principal");
        }
        if (isBadColorString(secondaryColor)) {
            throw new VerifyError("Error en el formato del color secundario");
        }
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    public Colors(Integer id, String primaryColor, String secondaryColor) {
        this(primaryColor, secondaryColor);
        this.id = id;
    }

    public Colors(Integer id, String primaryColor, String secondaryColor, Card card) {
        this(id, primaryColor, secondaryColor);
        this.card = card;
    }

    public int[] getPrimaryRGB() {
        return toRGB(primaryColor);
    }

    public int[] getSecondaryRGB() {
        return toRGB(secondaryColor);
    }

    private static boolean isBadColorString(String color) {
        return (color.length() > 16) || !Pattern.compile(RGB_PATTERN).asPredicate().test(color);
    }

    private static int[] toRGB(String color) {
        var aux = color.replace("rgb(", "");
        aux = aux.replace(")", "");
        var rgb = aux.split(",");
        return new int[] { Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]) };
    }
}
