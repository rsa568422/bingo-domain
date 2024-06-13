package com.rsa.bingo.domain.models;

import com.rsa.bingo.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ColorsTest {

    @Test
    void color_ok() {
        assertAll(
                () -> assertDoesNotThrow(Data::COLORS),
                () -> assertDoesNotThrow(
                        () -> new Colors(1, "rgb(255,0,0)", "rgb(255,128,0)")
                ),
                () -> assertDoesNotThrow(
                        () -> new Colors("rgb(255,255,255)", "rgb(128,128,128)")
                )
        );
    }

    @Test
    void color_ko_1() {
        var primaryColor = "";
        var secondaryColor = "rgb(255,128,0)";
        var expected = "Error en el formato del color principal";

        var error = assertThrows(VerifyError.class, () -> new Colors(primaryColor, secondaryColor));

        assertEquals(expected, error.getMessage());
    }

    @Test
    void color_ko_2() {
        var primaryColor = "rgb(255,0,0)";
        var secondaryColor = "intento de ataque de recursos con expresión regular";
        var expected = "Error en el formato del color secundario";

        var error = assertThrows(VerifyError.class, () -> new Colors(primaryColor, secondaryColor));

        assertEquals(expected, error.getMessage());
    }

    @Test
    void getPrimarySecondaryColor() {
        var colors = Data.COLORS();

        assertAll(
                () -> assertEquals("rgb(255,0,0)", colors.getPrimaryColor()),
                () -> assertEquals("rgb(255,128,0)", colors.getSecondaryColor())
        );
    }

    @Test
    void getPrimarySecondaryRGB() {
        var colors = Data.COLORS();

        assertAll(
                () -> assertArrayEquals(new int[] {255, 0, 0}, colors.getPrimaryRGB()),
                () -> assertArrayEquals(new int[] {255, 128, 0}, colors.getSecondaryRGB())
        );
    }

    @Test
    void getSetId() {
        var colors = Data.COLORS();

        assertAll(
                () -> assertNull(colors.getId()),
                () -> assertDoesNotThrow(() -> colors.setId(1)),
                () -> assertNotNull(colors.getId()),
                () -> assertEquals(1, colors.getId())
        );
    }
}