package com.rsa.bingo.domain.models;

import com.rsa.bingo.Data;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CardTest {

    @Test
    void card_ok() {
        assertAll(
                () -> assertDoesNotThrow(() -> Data.CARD()),
                () -> assertDoesNotThrow(() -> Data.CARD(1, null)),
                () -> assertDoesNotThrow(() -> Data.CARD(1, Data.PLAYER()))
        );
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
        var empties = new LinkedList<>();
        var values = new LinkedList<>();

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 9; j++) {
                var value = card.get(i, j);
                if (value.isPresent())
                    values.add(Triple.of(i, j, value.get()));
                else
                    empties.add(Pair.of(i, j));
            }

        assertAll(
                () -> assertNotNull(card),
                () -> assertArrayEquals(Data.VALUES(), card.getValues()),
                () -> assertIterableEquals(empties, Data.EMPTIES()),
                () -> assertIterableEquals(values, Data.CARD_VALUES())
        );
    }

    @Test
    void testEquals() {
        var card = Data.CARD();
        var cardWithId = Data.CARD(1, null);
        var cardWithOtherId = Data.CARD(2, null);
        var cardWithPlayer = Data.CARD(1, Data.PLAYER());
        var cardWithOtherPlayer = Data.CARD(1, new Player(2, "Other Player"));
        var cardWithOtherValues = new Card(Data.OTHER_VALUES());

        assertAll(
                () -> assertEquals(card, cardWithId),
                () -> assertEquals(card, cardWithId),
                () -> assertEquals(card, cardWithOtherId),
                () -> assertEquals(card, cardWithPlayer),
                () -> assertEquals(card, cardWithOtherPlayer),
                () -> assertNotEquals(card, cardWithOtherValues),
                () -> assertEquals(cardWithPlayer, card),
                () -> assertEquals(cardWithPlayer, cardWithId),
                () -> assertEquals(cardWithPlayer, cardWithOtherId),
                () -> assertEquals(cardWithPlayer, cardWithOtherPlayer),
                () -> assertNotEquals(cardWithPlayer, cardWithOtherValues)
        );
    }

    @Test
    void testHashCode() {
        var card = Data.CARD();
        var cardWithId = Data.CARD(1, null);
        var cardWithOtherId = Data.CARD(2, null);
        var cardWithPlayer = Data.CARD(1, Data.PLAYER());
        var cardWithOtherPlayer = Data.CARD(1, new Player(2, "Other Player"));
        var cardWithOtherValues = new Card(Data.OTHER_VALUES());

        assertAll(
                () -> assertEquals(card, cardWithId),
                () -> assertEquals(card, cardWithId),
                () -> assertEquals(card, cardWithOtherId),
                () -> assertEquals(card, cardWithPlayer),
                () -> assertEquals(card, cardWithOtherPlayer),
                () -> assertNotEquals(card, cardWithOtherValues),
                () -> assertEquals(cardWithPlayer, card),
                () -> assertEquals(cardWithPlayer, cardWithId),
                () -> assertEquals(cardWithPlayer, cardWithOtherId),
                () -> assertEquals(cardWithPlayer, cardWithOtherPlayer),
                () -> assertNotEquals(cardWithPlayer, cardWithOtherValues)
        );
    }

    @Test
    void getterSetter() {
        var card = Data.CARD();
        var player = Data.PLAYER();

        assertAll(
                () -> assertNull(card.getId()),
                () -> assertNull(card.getPlayer()),
                () -> assertNotNull(card.getValues()),
                () -> assertDoesNotThrow(() -> card.setId(1)),
                () -> assertEquals(1, card.getId()),
                () -> assertDoesNotThrow(() -> card.setPlayer(player)),
                () -> assertEquals(player, card.getPlayer())
        );
    }
}