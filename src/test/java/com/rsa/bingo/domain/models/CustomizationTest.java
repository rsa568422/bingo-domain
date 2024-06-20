package com.rsa.bingo.domain.models;

import com.rsa.bingo.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomizationTest {

    @Test
    void testEquals() {
        var card = Data.CARD();
        var colors = Data.COLORS();
        var otherCard = new Card(2, Data.OTHER_VALUES());
        var otherColors = new Colors(new int[] {0, 128, 0}, new int[] {0, 255, 0});
        var expected = Data.CUSTOMIZATION();

        assertAll(
                () -> assertEquals(expected, new Customization(card, colors)),
                () -> assertNotEquals(expected, new Customization(null, null)),
                () -> assertNotEquals(expected, new Customization(card, null)),
                () -> assertNotEquals(expected, new Customization(null, colors)),
                () -> assertNotEquals(expected, new Customization(card, otherColors)),
                () -> assertNotEquals(expected, new Customization(otherCard, colors)),
                () -> assertNotEquals(expected, new Customization(otherCard, otherColors))
        );
    }

    @Test
    void testHashCode() {
        var card = Data.CARD();
        var colors = Data.COLORS();
        var otherCard = new Card(2, Data.OTHER_VALUES());
        var otherColors = new Colors(new int[] {0, 128, 0}, new int[] {0, 255, 0});
        var expected = Data.CUSTOMIZATION().hashCode();

        assertAll(
                () -> assertEquals(expected, new Customization(card, colors).hashCode()),
                () -> assertNotEquals(expected, new Customization(null, null).hashCode()),
                () -> assertNotEquals(expected, new Customization(card, null).hashCode()),
                () -> assertNotEquals(expected, new Customization(null, colors).hashCode()),
                () -> assertNotEquals(expected, new Customization(card, otherColors).hashCode()),
                () -> assertNotEquals(expected, new Customization(otherCard, colors).hashCode()),
                () -> assertNotEquals(expected, new Customization(otherCard, otherColors).hashCode())
        );
    }

    @Test
    void getterSetter() {
        var actual = new Customization();
        var expected = Data.CUSTOMIZATION();

        assertAll(
                () -> assertNull(actual.getCard()),
                () -> assertNull(actual.getColors()),
                () -> assertDoesNotThrow(() -> actual.setCard(Data.CARD())),
                () -> assertDoesNotThrow(() -> actual.setColors(Data.COLORS())),
                () -> assertEquals(expected, actual)
        );
    }
}