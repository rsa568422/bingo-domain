package com.rsa.bingo.domain.utils;

import com.rsa.bingo.domain.models.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public final class CardDistributor {

    private static final int ROWS = Card.ROWS;
    private static final int COLUMNS = Card.COLUMNS;
    private static final int ELEMENTS_PER_ROW = 5;
    private static final int TOTAL_NUMBERS = ROWS * ELEMENTS_PER_ROW;

    private CardDistributor() { }

    public static List<Card> distribute(List<Integer> numbers) {
        validate(numbers);

        List<List<Integer>> columns = groupByColumn(numbers);
        validateColumns(columns);

        List<Card> results = new ArrayList<>();
        backtrack(columns, 0, new Integer[ROWS][COLUMNS], new int[ROWS], results);
        return results;
    }

    private static List<List<Integer>> groupByColumn(List<Integer> numbers) {
        List<List<Integer>> columns = new ArrayList<>();
        for (int c = 0; c < COLUMNS; c++) columns.add(new ArrayList<>());
        for (int number : numbers) columns.get(columnOf(number)).add(number);
        for (List<Integer> col : columns) Collections.sort(col);
        return columns;
    }

    private static void backtrack(List<List<Integer>> columns, int col,
                                  Integer[][] grid, int[] rowCounts,
                                  List<Card> results) {
        if (col == COLUMNS) {
            if (rowCounts[0] == ELEMENTS_PER_ROW
                    && rowCounts[1] == ELEMENTS_PER_ROW
                    && rowCounts[2] == ELEMENTS_PER_ROW) {
                Integer[][] copy = new Integer[ROWS][COLUMNS];
                for (int r = 0; r < ROWS; r++) copy[r] = Arrays.copyOf(grid[r], COLUMNS);
                results.add(new Card(copy));
            }
            return;
        }

        List<Integer> colNumbers = columns.get(col);
        int count = colNumbers.size();

        for (int[] assignment : assignments(count)) {
            if (fits(assignment, rowCounts) && canComplete(columns, col + 1, rowCounts, assignment)) {
                apply(assignment, colNumbers, col, grid, rowCounts);
                backtrack(columns, col + 1, grid, rowCounts, results);
                undo(assignment, col, grid, rowCounts);
            }
        }
    }

    private static boolean fits(int[] assignment, int[] rowCounts) {
        for (int row : assignment)
            if (rowCounts[row] + 1 > ELEMENTS_PER_ROW) return false;
        return true;
    }

    private static boolean canComplete(List<List<Integer>> columns, int fromCol,
                                       int[] rowCounts, int[] currentAssignment) {
        int[] remaining = new int[ROWS];
        for (int r = 0; r < ROWS; r++) remaining[r] = ELEMENTS_PER_ROW - rowCounts[r];
        for (int row : currentAssignment) remaining[row]--;

        int futureColumns = COLUMNS - fromCol;
        int totalNeeded = 0;
        int[] minContrib = new int[ROWS];

        for (int c = fromCol; c < COLUMNS; c++) {
            if (columns.get(c).size() == ROWS)
                for (int r = 0; r < ROWS; r++) minContrib[r]++;
        }

        for (int r = 0; r < ROWS; r++) {
            if (remaining[r] < 0) return false;
            if (remaining[r] > futureColumns) return false;
            if (remaining[r] < minContrib[r]) return false;
            totalNeeded += remaining[r];
        }

        int totalAvailable = 0;
        for (int c = fromCol; c < COLUMNS; c++) totalAvailable += columns.get(c).size();
        return totalAvailable == totalNeeded;
    }

    private static void apply(int[] assignment, List<Integer> colNumbers,
                              int col, Integer[][] grid, int[] rowCounts) {
        for (int idx = 0; idx < assignment.length; idx++) {
            int row = assignment[idx];
            grid[row][col] = colNumbers.get(idx);
            rowCounts[row]++;
        }
    }

    private static void undo(int[] assignment, int col,
                             Integer[][] grid, int[] rowCounts) {
        for (int row : assignment) {
            grid[row][col] = null;
            rowCounts[row]--;
        }
    }

    private static final int[][] ASSIGN_1 = {{0}, {1}, {2}};
    private static final int[][] ASSIGN_2 = {{0, 1}, {0, 2}, {1, 2}};
    private static final int[][] ASSIGN_3 = {{0, 1, 2}};

    private static int[][] assignments(int count) {
        return switch (count) {
            case 1 -> ASSIGN_1;
            case 2 -> ASSIGN_2;
            case 3 -> ASSIGN_3;
            default -> throw new VerifyError("Número de elementos por columna inválido: " + count);
        };
    }

    static int columnOf(int value) {
        if (value < 1 || value > 90)
            throw new IllegalArgumentException("Valor fuera de rango: " + value);
        return Math.min(value / 10, 8);
    }

    private static void validate(List<Integer> numbers) {
        if (numbers == null || numbers.size() != TOTAL_NUMBERS)
            throw new IllegalArgumentException(
                    String.format("Se requieren exactamente %d números", TOTAL_NUMBERS));

        var unique = new HashSet<>(numbers);
        if (unique.size() != TOTAL_NUMBERS)
            throw new IllegalArgumentException("Los números no pueden repetirse");

        for (int n : numbers)
            if (n < 1 || n > 90)
                throw new IllegalArgumentException("Valor fuera de rango: " + n);
    }

    private static void validateColumns(List<List<Integer>> columns) {
        for (int c = 0; c < COLUMNS; c++) {
            int size = columns.get(c).size();
            if (size == 0)
                throw new VerifyError(String.format("No hay números para la columna %d", c + 1));
            if (size > ROWS)
                throw new VerifyError(String.format("Demasiados números para la columna %d", c + 1));
        }
    }
}
