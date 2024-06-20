package com.rsa.bingo.domain.models;

import com.rsa.bingo.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ColorsTest {

    @Test
    void color_ok() {
        assertAll(
                () -> assertDoesNotThrow(Data::COLORS),
                () -> assertDoesNotThrow(
                        () -> new Colors(1, new int[] {255, 0, 0}, new int[] {255, 128, 0})
                ),
                () -> assertDoesNotThrow(
                        () -> new Colors(new int[] {255, 255, 255}, new int[] {128, 128, 128})
                )
        );
    }

    @Test
    void color_ko_1() {
        var primaryColor = new int[0];
        var secondaryColor = new int[] {255, 128, 0};
        var expected = "Error en el formato del color principal";

        var error = assertThrows(VerifyError.class, () -> new Colors(primaryColor, secondaryColor));

        assertEquals(expected, error.getMessage());
    }

    @Test
    void color_ko_2() {
        var primaryColor = new int[] {255, 0, 0};
        var secondaryColor = new int[] {128, 128, 256};
        var expected = "Error en el formato del color secundario";

        var error = assertThrows(VerifyError.class, () -> new Colors(primaryColor, secondaryColor));

        assertEquals(expected, error.getMessage());
    }

    @Test
    void getPrimarySecondaryString() {
        var colors = Data.COLORS();

        assertAll(
                () -> assertEquals("rgb(255,0,0)", colors.getPrimaryString()),
                () -> assertEquals("rgb(255,128,0)", colors.getSecondaryString())
        );
    }

    @Test
    void getPrimarySecondary() {
        var colors = Data.COLORS();

        assertAll(
                () -> assertArrayEquals(new int[] {255, 0, 0}, colors.getPrimary()),
                () -> assertArrayEquals(new int[] {255, 128, 0}, colors.getSecondary())
        );
    }

    @Test
    void getSetId() {
        assertNull(Data.COLORS().getId());
    }
}