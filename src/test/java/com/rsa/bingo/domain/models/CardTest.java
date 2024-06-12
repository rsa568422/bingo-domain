package com.rsa.bingo.domain.models;

import com.rsa.bingo.Data;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardTest {

    @Test
    void card_ok() {
        assertDoesNotThrow(Data::CARD);
    }

    @Test
    void card_ko_1() {
        var values = Arrays.copyOfRange(Data.VALUES(), 0, 1);
        var expected = "Número de filas incorrecto";

        var error = assertThrows(VerifyError.class, () -> new Card(values));

        assertEquals(expected, error.getMessage());
    }

    @Test
    void card_ko_2() {
        var values = Data.VALUES();
        values[0][0] = 1;
        var expected = "Número de elementos incorrecto en la fila 1";

        var error = assertThrows(VerifyError.class, () -> new Card(values));

        assertEquals(expected, error.getMessage());
    }

    @Test
    void card_ko_3() {
        var values = Data.VALUES();
        values[2] = Arrays.copyOfRange(values[2], 0, 8);
        var expected = "Número de elementos incorrecto en la fila 3";

        var error = assertThrows(VerifyError.class, () -> new Card(values));

        assertEquals(expected, error.getMessage());
    }

    @Test
    void card_ko_4() {
        var values = Data.VALUES();
        values[1][7] = 87;
        values[1][8] = 70;
        var expected = "El orden de los elementos es incorrecto en la fila 2";

        var error = assertThrows(VerifyError.class, () -> new Card(values));

        assertEquals(expected, error.getMessage());
    }

    @Test
    void card_ko_5() {
        var values = Data.VALUES();
        values[2][0] = null;
        values[2][1] = 4;
        var expected = "Número de elementos incorrecto en la columna 1";

        var error = assertThrows(VerifyError.class, () -> new Card(values));

        assertEquals(expected, error.getMessage());
    }

    @Test
    void card_ko_6() {
        var values = Data.VALUES();
        values[0][8] = 87;
        values[1][8] = 80;
        var expected = "El orden de los elementos es incorrecto en la columna 9";

        var error = assertThrows(VerifyError.class, () -> new Card(values));

        assertEquals(expected, error.getMessage());
    }

    @Test
    void card_ko_7() {
        var values = Data.VALUES();
        values[2][0] = 0;
        var expected = "Orden de los elementos incorrecto";

        var error = assertThrows(VerifyError.class, () -> new Card(values));

        assertEquals(expected, error.getMessage());
    }

    @Test
    void card_ko_8() {
        var values = Data.VALUES();
        values[1][8] = 100;
        var expected = "Orden de los elementos incorrecto";

        var error = assertThrows(VerifyError.class, () -> new Card(values));

        assertEquals(expected, error.getMessage());
    }

    @Test
    void card_ko_9() {
        var values = Data.VALUES();
        values[2][0] = 19;
        var expected = "Orden de los elementos incorrecto";

        var error = assertThrows(VerifyError.class, () -> new Card(values));

        assertEquals(expected, error.getMessage());
    }

    @Test
    void get() {
        var card = Data.CARD();

        assertAll(
                () -> assertFalse(card.get(0, 0).isPresent()),
                () -> assertTrue(card.get(0, 1).isPresent()),
                () -> assertEquals(18, card.get(0, 1).orElse(null)),
                () -> assertTrue(card.get(0, 2).isPresent()),
                () -> assertEquals(24, card.get(0, 2).orElse(null)),
                () -> assertFalse(card.get(0, 3).isPresent()),
                () -> assertFalse(card.get(0, 4).isPresent()),
                () -> assertTrue(card.get(0, 5).isPresent()),
                () -> assertEquals(54, card.get(0, 5).orElse(null)),
                () -> assertTrue(card.get(0, 6).isPresent()),
                () -> assertEquals(67, card.get(0, 6).orElse(null)),
                () -> assertFalse(card.get(0, 7).isPresent()),
                () -> assertTrue(card.get(0, 8).isPresent()),
                () -> assertEquals(80, card.get(0, 8).orElse(null)),

                () -> assertFalse(card.get(1, 0).isPresent()),
                () -> assertFalse(card.get(1, 1).isPresent()),
                () -> assertTrue(card.get(1, 2).isPresent()),
                () -> assertEquals(26, card.get(1, 2).orElse(null)),
                () -> assertFalse(card.get(1, 3).isPresent()),
                () -> assertTrue(card.get(1, 4).isPresent()),
                () -> assertEquals(41, card.get(1, 4).orElse(null)),
                () -> assertTrue(card.get(1, 5).isPresent()),
                () -> assertEquals(55, card.get(1, 5).orElse(null)),
                () -> assertFalse(card.get(1, 6).isPresent()),
                () -> assertTrue(card.get(1, 7).isPresent()),
                () -> assertEquals(70, card.get(1, 7).orElse(null)),
                () -> assertTrue(card.get(1, 8).isPresent()),
                () -> assertEquals(87, card.get(1, 8).orElse(null)),

                () -> assertTrue(card.get(2, 0).isPresent()),
                () -> assertEquals(4, card.get(2, 0).orElse(null)),
                () -> assertFalse(card.get(2, 1).isPresent()),
                () -> assertFalse(card.get(2, 2).isPresent()),
                () -> assertTrue(card.get(2, 3).isPresent()),
                () -> assertEquals(39, card.get(2, 3).orElse(null)),
                () -> assertTrue(card.get(2, 4).isPresent()),
                () -> assertEquals(42, card.get(2, 4).orElse(null)),
                () -> assertFalse(card.get(2, 5).isPresent()),
                () -> assertTrue(card.get(2, 6).isPresent()),
                () -> assertEquals(69, card.get(2, 6).orElse(null)),
                () -> assertTrue(card.get(2, 7).isPresent()),
                () -> assertEquals(72, card.get(2, 7).orElse(null)),
                () -> assertFalse(card.get(2, 8).isPresent())
        );
    }

    @Test
    void getAndSetId() {
        var card = Data.CARD();

        assertAll(
                () -> assertNull(card.getId()),
                () -> assertDoesNotThrow(() -> card.setId(1)),
                () -> assertEquals(1, card.getId())
        );
    }
}