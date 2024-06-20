package com.rsa.bingo.domain.models;

import com.rsa.bingo.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PlayerTest {

    @Test
    void testEquals() {
        var expected = Data.PLAYER();

        assertAll(
                () -> assertEquals(expected, new Player(1, "Player")),
                () -> assertNotEquals(expected, new Player(null, null)),
                () -> assertNotEquals(expected, new Player(1, null)),
                () -> assertNotEquals(expected, new Player(null, "Player")),
                () -> assertNotEquals(expected, new Player(1, "Other Player")),
                () -> assertNotEquals(expected, new Player(2, "Player"))
        );
    }

    @Test
    void testHashCode() {
        var expected = Data.PLAYER().hashCode();

        assertAll(
                () -> assertEquals(expected, new Player(1, "Player").hashCode()),
                () -> assertNotEquals(expected, new Player(null, null).hashCode()),
                () -> assertNotEquals(expected, new Player(1, null).hashCode()),
                () -> assertNotEquals(expected, new Player(null, "Player").hashCode()),
                () -> assertNotEquals(expected, new Player(1, "Other Player").hashCode()),
                () -> assertNotEquals(expected, new Player(2, "Player").hashCode())
        );
    }

    @Test
    void getterSetter() {
        var actual = new Player();

        assertAll(
                () -> assertNull(actual.getId()),
                () -> assertNull(actual.getName()),
                () -> assertDoesNotThrow(() -> actual.setId(1)),
                () -> assertDoesNotThrow(() -> actual.setName("Player")),
                () -> assertEquals(Data.PLAYER(), actual)
        );
    }
}