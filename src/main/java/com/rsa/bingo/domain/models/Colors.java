package com.rsa.bingo.domain.models;

import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Arrays;

@Getter
public final class Colors {

    private static final String RGB_PATTERN = "^rgb\\(\\d{1,3},\\d{1,3},\\d{1,3}\\)$";

    private Integer id;

    private final int[] primary;

    private final int[] secondary;

    public Colors(int[] primary, int[] secondary) {
        if (isInvalidColor(primary)) {
            throw new VerifyError("Error en el formato del color principal");
        }
        if (isInvalidColor(secondary)) {
            throw new VerifyError("Error en el formato del color secundario");
        }
        this.primary = primary;
        this.secondary = secondary;
    }

    public Colors(Integer id, int[] primaryColor, int[] secondaryColor) {
        this(primaryColor, secondaryColor);
        this.id = id;
    }

    public String getPrimaryString() {
        return colorToString(primary);
    }

    public String getSecondaryString() {
        return colorToString(secondary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Colors colors = (Colors) o;

        return new EqualsBuilder()
                .append(getPrimary(), colors.getPrimary())
                .append(getSecondary(), colors.getSecondary())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getPrimary())
                .append(getSecondary())
                .toHashCode();
    }

    private static boolean isInvalidColor(int[] color) {
        return (color.length != 3)
                || (Arrays.stream(color).min().orElse(0) < 0)
                || (Arrays.stream(color).max().orElse(0) > 255);
    }

    private static String colorToString(int[] color) {
        return String.format("rgb(%d,%d,%d)", color[0], color[1], color[2]);
    }
}
