package com.rsa.bingo.domain.models;

import com.rsa.bingo.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PlayerTest {

    @Test
    void gettersSetters_1() {
        var player = Data.PLAYER();

        assertAll(
                () -> assertNotNull(player.getId()),
                () -> assertEquals(1, player.getId()),
                () -> assertNotNull(player.getName()),
                () -> assertEquals("Player", player.getName()),
                () -> assertDoesNotThrow(() -> player.setId(null)),
                () -> assertDoesNotThrow(() -> player.setName("Player 2")),
                () -> assertNull(player.getId()),
                () -> assertNotNull(player.getName()),
                () -> assertEquals("Player 2", player.getName())

        );
    }

    @Test
    void gettersSetters_2() {
        var player = new Player();

        assertAll(
                () -> assertNull(player.getId()),
                () -> assertNull(player.getName()),
                () -> assertDoesNotThrow(() -> player.setId(1)),
                () -> assertDoesNotThrow(() -> player.setName("Player")),
                () -> assertNotNull(player.getId()),
                () -> assertEquals(1, player.getId()),
                () -> assertNotNull(player.getName()),
                () -> assertEquals("Player", player.getName())

        );
    }
}