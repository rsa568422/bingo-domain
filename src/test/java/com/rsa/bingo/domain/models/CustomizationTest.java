package com.rsa.bingo.domain.models;

import com.rsa.bingo.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomizationTest {

    @Test
    void testEquals() {
        var black = Color.BLACK;
        var grey = Color.GREY_50_PERCENT;
        var white = Color.WHITE;
        assertAll(
                () -> assertEquals(
                        new Customization(1, black, grey),
                        new Customization(1, Color.BLACK, Color.GREY_50_PERCENT)
                ),
                () -> assertNotEquals(
                        new Customization(null, black, grey),
                        new Customization(1, black, grey)
                ),
                () -> assertNotEquals(
                        new Customization(1, black, grey),
                        new Customization(2, black, grey)
                ),
                () -> assertNotEquals(
                        new Customization(1, black, white),
                        new Customization(1, black, grey)
                ),
                () -> assertNotEquals(
                        new Customization(1, white, grey),
                        new Customization(1, black, grey)
                ),
                () -> assertNotEquals(
                        new Customization(1, black, null),
                        new Customization(1, black, grey)
                ),
                () -> assertNotEquals(
                        new Customization(1, null, grey),
                        new Customization(1, black, grey)
                ),
                () -> assertNotEquals(
                        new Customization(1, black, grey),
                        new Customization(1, null, grey)
                ),
                () -> assertNotEquals(
                        new Customization(1, black, grey),
                        new Customization(1, black, null)
                )
        );
    }

    @Test
    void testHashCode() {
        var black = Color.BLACK;
        var grey = Color.GREY_50_PERCENT;
        var white = Color.WHITE;
        assertAll(
                () -> assertEquals(
                        new Customization(1, black, grey).hashCode(),
                        new Customization(1, Color.BLACK, Color.GREY_50_PERCENT).hashCode()
                ),
                () -> assertNotEquals(
                        new Customization(null, black, grey).hashCode(),
                        new Customization(1, black, grey).hashCode()
                ),
                () -> assertNotEquals(
                        new Customization(1, black, grey).hashCode(),
                        new Customization(2, black, grey).hashCode()
                ),
                () -> assertNotEquals(
                        new Customization(1, black, white).hashCode(),
                        new Customization(1, black, grey).hashCode()
                ),
                () -> assertNotEquals(
                        new Customization(1, white, grey).hashCode(),
                        new Customization(1, black, grey).hashCode()
                ),
                () -> assertNotEquals(
                        new Customization(1, black, null).hashCode(),
                        new Customization(1, black, grey).hashCode()
                ),
                () -> assertNotEquals(
                        new Customization(1, null, grey).hashCode(),
                        new Customization(1, black, grey).hashCode()
                ),
                () -> assertNotEquals(
                        new Customization(1, black, grey).hashCode(),
                        new Customization(1, null, grey).hashCode()
                ),
                () -> assertNotEquals(
                        new Customization(1, black, grey).hashCode(),
                        new Customization(1, black, null).hashCode()
                )
        );
    }

    @Test
    void getterSetter() {
        var actual = Data.CUSTOMIZATION();

        assertAll(
                () -> assertNull(actual.getCardId()),
                () -> assertNotNull(actual.getPrimary()),
                () -> assertNotNull(actual.getSecondary()),
                () -> assertDoesNotThrow(() -> actual.setCardId(1)),
                () -> assertEquals(new Customization(1, Color.BLACK, Color.GREY_50_PERCENT), actual)
        );
    }
}