package com.rsa.bingo.domain.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

@Getter
public final class Card {

    static final int ROWS = 3;
    static final int COLUMNS = 9;
    private static final int ELEMENTS = 5;
    private static final String ROW = "fila";
    private static final String COLUMN = "columna";
    private static final String ERROR_ROWS_NUMBER = "Número de filas incorrecto";
    private static final String ERROR_ELEMENTS_NUMBER_TEMPLATE =
            "Número de elementos incorrecto en la %s %d";
    private static final String ERROR_ELEMENTS_ORDER_TEMPLATE =
            "El orden de los elementos es incorrecto en la %s %d";
    private static final String ERROR_ELEMENT_ORDER = "Orden de los elementos incorrecto";

    @Setter
    private Integer id;

    private final Integer[][] values;

    public Card(Integer[][] values) {
        if (values.length != ROWS)
            throw new VerifyError(ERROR_ROWS_NUMBER);
        for (int i = 0; i < ROWS; i++) {
            if (badRow(getRow(values, i)))
                throw new VerifyError(String.format(ERROR_ELEMENTS_NUMBER_TEMPLATE, ROW, i + 1));
            if (badOrder(getRow(values, i)))
                throw new VerifyError(String.format(ERROR_ELEMENTS_ORDER_TEMPLATE, ROW, i + 1));
        }
        for (int i = 0; i < COLUMNS; i++) {
            if (badColumn(getColumn(values, i)))
                throw new VerifyError(String.format(ERROR_ELEMENTS_NUMBER_TEMPLATE, COLUMN, i + 1));
            if (badOrder(getColumn(values, i)))
                throw new VerifyError(String.format(ERROR_ELEMENTS_ORDER_TEMPLATE, COLUMN, i + 1));
        }
        if (badOrder(values))
            throw new VerifyError(ERROR_ELEMENT_ORDER);
        this.values = values;
    }

    public Card(Integer id, Integer[][] values) {
        this(values);
        this.id = id;
    }

    public Optional<Integer> get(int x, int y) {
        return Optional.ofNullable(values[x][y]);
    }

    private static boolean badRow(Integer[] row) {
        return (row.length != COLUMNS) || (Arrays.stream(row).filter(Objects::nonNull).count() != ELEMENTS);
    }

    private static boolean badColumn(Integer[] column) {
        return Arrays.stream(column).noneMatch(Objects::nonNull);
    }

    private static boolean badOrder(Integer[] row) {
        var original = Arrays.stream(row).filter(Objects::nonNull).toArray();
        var ordered = Arrays.stream(row).filter(Objects::nonNull).sorted().toArray();
        return !Arrays.equals(original, ordered);
    }

    private static boolean badOrder(Integer[][] values) {
        var ranges = Arrays.asList(
                getRange(getColumn(values, 0)),
                getRange(getColumn(values, 1)),
                getRange(getColumn(values, 2)),
                getRange(getColumn(values, 3)),
                getRange(getColumn(values, 4)),
                getRange(getColumn(values, 5)),
                getRange(getColumn(values, 6)),
                getRange(getColumn(values, 7)),
                getRange(getColumn(values, 8))
        );
        if (ranges.get(0).getLeft().compareTo(0) <= 0)
            return true;
        for (int i = 0; i < COLUMNS - 1; i++) {
            if (ranges.get(i).getRight().compareTo(ranges.get(i + 1).getLeft()) >= 0)
                return true;
        }
        return ranges.get(8).getRight().compareTo(99) > 0;
    }

    private static Integer[] getRow(Integer[][] values, int index) {
        return values[index];
    }

    private static Integer[] getColumn(Integer[][] values, int index) {
        return new Integer[] {values[0][index], values[1][index], values[2][index]};
    }

    private static Pair<Integer, Integer> getRange(Integer[] column) {
        var min = Arrays.stream(column).filter(Objects::nonNull).min(Comparator.naturalOrder())
                .orElseThrow(() -> new VerifyError(ERROR_ELEMENT_ORDER));
        var max = Arrays.stream(column).filter(Objects::nonNull).max(Comparator.naturalOrder())
                .orElseThrow(() -> new VerifyError(ERROR_ELEMENT_ORDER));
        return Pair.of(min, max);
    }
}
