package com.rsa.bingo.domain.utils;

import com.rsa.bingo.Data;
import com.rsa.bingo.domain.models.Card;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParserTest {

    @Test
    void getBytes() throws IOException {
        var card = Data.CARD();
        var colors = Data.COLORS();
        var workbook = Mockito.mock(HSSFWorkbook.class);
        var sheet = Mockito.mock(HSSFSheet.class);
        var primaryStyle = Mockito.mock(HSSFCellStyle.class);
        var secondaryStyle = Mockito.mock(HSSFCellStyle.class);
        var primaryCell = Mockito.mock(HSSFCell.class);
        var secondaryCell = Mockito.mock(HSSFCell.class);
        var rows = getRows();
        var columns = getColumns(card, primaryCell, secondaryCell);
        var expected = "PASSED".getBytes();

        for (var i = 0; i < 3; i++) {
            when(sheet.createRow(i + 1)).thenReturn(rows[i]);
            for (var j = 0; j < 9; j++)
                when(rows[i].createCell(j + 1)).thenReturn(columns[i][j]);
        }
        when(workbook.getBytes()).thenReturn(expected);

        final byte[] actual;
        try (MockedStatic<XlsUtils> mockedStatic = Mockito.mockStatic(XlsUtils.class)) {
            mockedStatic.when(XlsUtils::getWorkbook).thenReturn(workbook);
            mockedStatic.when(() -> XlsUtils.getSheet(workbook, "Bingo", 8, 50)).thenReturn(sheet);
            mockedStatic.when(() -> XlsUtils.getPrimaryCellStyle(workbook, colors, 28)).thenReturn(primaryStyle);
            mockedStatic.when(() -> XlsUtils.getSecondaryCellStyle(workbook, colors)).thenReturn(secondaryStyle);

            actual = Parser.getBytes(card, colors);

            mockedStatic.verify(XlsUtils::getWorkbook, times(1));
            mockedStatic.verify(() -> XlsUtils.getSheet(any(), any(), anyInt(), anyInt()), times(1));
            mockedStatic.verify(() -> XlsUtils.getSheet(workbook, "Bingo", 8, 50), times(1));
            mockedStatic.verify(() -> XlsUtils.getPrimaryCellStyle(any(), any(), anyInt()), times(1));
            mockedStatic.verify(() -> XlsUtils.getPrimaryCellStyle(workbook, colors, 28), times(1));
            mockedStatic.verify(() -> XlsUtils.getSecondaryCellStyle(any(), any()), times(1));
            mockedStatic.verify(() -> XlsUtils.getSecondaryCellStyle(workbook, colors), times(1));
            mockedStatic.verifyNoMoreInteractions();
        }

        assertAll(
                () -> assertNotNull(actual),
                () -> assertArrayEquals(expected, actual)
        );

        verify(workbook, times(1)).getBytes();
        verifyNoMoreInteractions(workbook);

        verify(sheet, times(3)).createRow(anyInt());
        verify(sheet, times(1)).createRow(1);
        verify(sheet, times(1)).createRow(2);
        verify(sheet, times(1)).createRow(3);
        verifyNoMoreInteractions(sheet);

        verify(primaryCell, times(15)).setCellValue(anyDouble());
        verify(primaryCell, times(1)).setCellValue(4);
        verify(primaryCell, times(1)).setCellValue(18);
        verify(primaryCell, times(1)).setCellValue(24);
        verify(primaryCell, times(1)).setCellValue(26);
        verify(primaryCell, times(1)).setCellValue(39);
        verify(primaryCell, times(1)).setCellValue(41);
        verify(primaryCell, times(1)).setCellValue(42);
        verify(primaryCell, times(1)).setCellValue(54);
        verify(primaryCell, times(1)).setCellValue(55);
        verify(primaryCell, times(1)).setCellValue(67);
        verify(primaryCell, times(1)).setCellValue(69);
        verify(primaryCell, times(1)).setCellValue(70);
        verify(primaryCell, times(1)).setCellValue(72);
        verify(primaryCell, times(1)).setCellValue(80);
        verify(primaryCell, times(1)).setCellValue(87);
        verify(primaryCell, times(15)).setCellStyle(any());
        verify(primaryCell, times(15)).setCellStyle(primaryStyle);
        verifyNoMoreInteractions(primaryCell);

        verify(secondaryCell, times(12)).setCellStyle(any());
        verify(secondaryCell, times(12)).setCellStyle(secondaryStyle);
        verifyNoMoreInteractions(secondaryCell);

        verifyNoInteractions(primaryStyle);
        verifyNoInteractions(secondaryStyle);
    }

    private static HSSFRow[] getRows() {
        var rows = new HSSFRow[3];
        for (var i = 0; i < 3; i++)
            rows[i] = Mockito.mock(HSSFRow.class);
        return rows;
    }

    private static HSSFCell[][] getColumns(Card card, HSSFCell primaryCell, HSSFCell secondaryCell) {
        var columns = new HSSFCell[3][9];
        for (var i = 0; i < 3; i++) {
            for (var j = 0; j < 9; j++) {
                if (card.get(i, j).isPresent())
                    columns[i][j] = primaryCell;
                else
                    columns[i][j] = secondaryCell;
            }
        }
        return columns;
    }
}