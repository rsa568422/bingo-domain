package com.rsa.bingo.domain.models;

import com.rsa.bingo.Data;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParserTest {

    @Test
    void getBytes() throws IOException {
        var bytes = Parser.getBytes(Data.CARD(), Data.COLORS());
        assertAll(
                () -> assertNotNull(bytes),
                () -> assertEquals(2363, bytes.length)/*,
                () -> assertArrayEquals(Data.BYTES(), bytes)*/
        );
    }
}