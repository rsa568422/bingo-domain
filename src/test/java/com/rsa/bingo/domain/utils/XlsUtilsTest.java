package com.rsa.bingo.domain.utils;

import com.rsa.bingo.domain.models.Color;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class XlsUtilsTest {

    private HSSFWorkbook workbook;

    @BeforeEach
    void setUp() {
        workbook = XlsUtils.getWorkbook();
    }

    @Test
    void getWorkbook() {
        assertAll(
                () -> assertNotNull(workbook),
                () -> assertInstanceOf(HSSFWorkbook.class, workbook)
        );
    }

    @Test
    void getSheet() {
        var actual = XlsUtils.getSheet(workbook, "Bingo", 12, 50);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertInstanceOf(HSSFSheet.class, actual),
                () -> assertEquals("Bingo", actual.getSheetName()),
                () -> assertEquals(12, actual.getDefaultColumnWidth()),
                () -> assertEquals(50, actual.getDefaultRowHeightInPoints())
        );
    }

    @Test
    void getPrimaryCellStyle() {
        var actual = XlsUtils.getPrimaryCellStyle(workbook, Color.GREY_50_PERCENT.getName(), 28);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertInstanceOf(HSSFCellStyle.class, actual),
                () -> assertAll(
                        () -> assertEquals(FillPatternType.SOLID_FOREGROUND, actual.getFillPattern()),
                        () -> assertEquals(FillPatternType.SOLID_FOREGROUND, actual.getFillPattern()),
                        () -> assertEquals(BorderStyle.MEDIUM, actual.getBorderLeft()),
                        () -> assertEquals(BorderStyle.MEDIUM, actual.getBorderTop()),
                        () -> assertEquals(BorderStyle.MEDIUM, actual.getBorderRight()),
                        () -> assertEquals(BorderStyle.MEDIUM, actual.getBorderBottom()),
                        () -> assertEquals(23, actual.getLeftBorderColor()),
                        () -> assertEquals(23, actual.getTopBorderColor()),
                        () -> assertEquals(23, actual.getRightBorderColor()),
                        () -> assertEquals(23, actual.getBottomBorderColor())
                ),
                () -> {
                    var font = actual.getFont(workbook);
                    assertNotNull(font);
                    assertEquals(23, font.getColor());
                    assertEquals(28, font.getFontHeightInPoints());
                },
                () -> assertEquals(IndexedColors.WHITE.index, actual.getFillForegroundColor()),
                () -> assertEquals(HorizontalAlignment.CENTER, actual.getAlignment()),
                () -> assertEquals(VerticalAlignment.CENTER, actual.getVerticalAlignment())
        );
    }

    @Test
    void getSecondaryCellStyle() {
        var actual = XlsUtils.getSecondaryCellStyle(workbook, "RED", Color.GREY_50_PERCENT.getName());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertInstanceOf(HSSFCellStyle.class, actual),
                () -> assertAll(
                        () -> assertEquals(FillPatternType.SOLID_FOREGROUND, actual.getFillPattern()),
                        () -> assertEquals(FillPatternType.SOLID_FOREGROUND, actual.getFillPattern()),
                        () -> assertEquals(BorderStyle.MEDIUM, actual.getBorderLeft()),
                        () -> assertEquals(BorderStyle.MEDIUM, actual.getBorderTop()),
                        () -> assertEquals(BorderStyle.MEDIUM, actual.getBorderRight()),
                        () -> assertEquals(BorderStyle.MEDIUM, actual.getBorderBottom()),
                        () -> assertEquals(10, actual.getLeftBorderColor()),
                        () -> assertEquals(10, actual.getTopBorderColor()),
                        () -> assertEquals(10, actual.getRightBorderColor()),
                        () -> assertEquals(10, actual.getBottomBorderColor())
                ),
                () -> assertEquals(23, actual.getFillForegroundColor())
        );
    }
}