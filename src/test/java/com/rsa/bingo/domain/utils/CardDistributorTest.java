package com.rsa.bingo.domain.utils;

import com.rsa.bingo.Data;
import com.rsa.bingo.domain.models.Card;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardDistributorTest {

    @Test
    void columnOf_valid_values() {
        assertAll(
                () -> assertEquals(0, CardDistributor.columnOf(1)),
                () -> assertEquals(0, CardDistributor.columnOf(9)),
                () -> assertEquals(1, CardDistributor.columnOf(10)),
                () -> assertEquals(1, CardDistributor.columnOf(19)),
                () -> assertEquals(4, CardDistributor.columnOf(40)),
                () -> assertEquals(4, CardDistributor.columnOf(49)),
                () -> assertEquals(7, CardDistributor.columnOf(70)),
                () -> assertEquals(7, CardDistributor.columnOf(79)),
                () -> assertEquals(8, CardDistributor.columnOf(80)),
                () -> assertEquals(8, CardDistributor.columnOf(90))
        );
    }

    @Test
    void columnOf_invalid_values() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> CardDistributor.columnOf(0)),
                () -> assertThrows(IllegalArgumentException.class, () -> CardDistributor.columnOf(-1)),
                () -> assertThrows(IllegalArgumentException.class, () -> CardDistributor.columnOf(91))
        );
    }

    @Test
    void distribute_known_card() {
        var numbers = extractNumbers(Data.VALUES());
        var results = CardDistributor.distribute(numbers);

        assertFalse(results.isEmpty());
        assertTrue(results.contains(Data.CARD()));
    }

    @Test
    void distribute_all_results_are_valid_cards() {
        var numbers = extractNumbers(Data.VALUES());
        var results = CardDistributor.distribute(numbers);

        for (Card card : results) {
            for (int r = 0; r < Card.ROWS; r++) {
                long count = Arrays.stream(card.getValues()[r]).filter(Objects::nonNull).count();
                assertEquals(5, count, "Cada fila debe tener 5 números");
            }
            for (int c = 0; c < Card.COLUMNS; c++) {
                int col = c;
                long count = Arrays.stream(new Integer[]{
                        card.getValues()[0][col],
                        card.getValues()[1][col],
                        card.getValues()[2][col]
                }).filter(Objects::nonNull).count();
                assertTrue(count >= 1, "Cada columna debe tener al menos 1 número");
            }
        }
    }

    @Test
    void distribute_all_results_contain_same_numbers() {
        var numbers = extractNumbers(Data.VALUES());
        var results = CardDistributor.distribute(numbers);
        var expected = numbers.stream().sorted().toList();

        for (Card card : results) {
            var cardNumbers = extractNumbers(card.getValues()).stream().sorted().toList();
            assertEquals(expected, cardNumbers);
        }
    }

    @Test
    void distribute_triple_columns_are_forced() {
        var values = new Integer[][]{
                {1, 10, 20, null, null, 50, null, null, 80},
                {5, 15, 25, null, 40, null, null, 70, null},
                {9, 19, 29, 30, null, null, 60, null, null}
        };
        var numbers = extractNumbers(values);
        var results = CardDistributor.distribute(numbers);

        assertFalse(results.isEmpty());
        for (Card card : results) {
            assertAll(
                    () -> assertEquals(1, card.getValues()[0][0]),
                    () -> assertEquals(5, card.getValues()[1][0]),
                    () -> assertEquals(9, card.getValues()[2][0]),
                    () -> assertEquals(10, card.getValues()[0][1]),
                    () -> assertEquals(15, card.getValues()[1][1]),
                    () -> assertEquals(19, card.getValues()[2][1]),
                    () -> assertEquals(20, card.getValues()[0][2]),
                    () -> assertEquals(25, card.getValues()[1][2]),
                    () -> assertEquals(29, card.getValues()[2][2])
            );
        }
    }

    @Test
    void distribute_null_input() {
        assertThrows(IllegalArgumentException.class, () -> CardDistributor.distribute(null));
    }

    @Test
    void distribute_wrong_count() {
        var numbers = List.of(1, 10, 20, 30, 40, 50, 60, 70, 80);
        assertThrows(IllegalArgumentException.class, () -> CardDistributor.distribute(numbers));
    }

    @Test
    void distribute_duplicates() {
        var numbers = List.of(1, 1, 10, 20, 30, 40, 50, 60, 70, 80, 81, 82, 83, 84, 85);
        assertThrows(IllegalArgumentException.class, () -> CardDistributor.distribute(numbers));
    }

    @Test
    void distribute_out_of_range() {
        var numbers = List.of(0, 10, 20, 30, 40, 50, 60, 70, 80, 81, 82, 83, 84, 85, 86);
        assertThrows(IllegalArgumentException.class, () -> CardDistributor.distribute(numbers));
    }

    @Test
    void distribute_empty_column() {
        var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 80, 81, 82, 83, 84, 85);
        assertThrows(VerifyError.class, () -> CardDistributor.distribute(numbers));
    }

    @Test
    void distribute_too_many_in_column() {
        var numbers = List.of(1, 2, 3, 4, 10, 20, 30, 40, 50, 60, 70, 80, 81, 82, 83);
        assertThrows(VerifyError.class, () -> CardDistributor.distribute(numbers));
    }

    private static List<Integer> extractNumbers(Integer[][] values) {
        return Arrays.stream(values)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .toList();
    }
}
